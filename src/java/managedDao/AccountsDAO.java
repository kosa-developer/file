package managedDao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import managedModal.Accounts;
import managedModal.AccountsPayments;
import managedModal.AccountsTasks;
import managedModal.AccountsPaymentsView;
import managedModal.Error;
import managedModal.TreatmentView;

public class AccountsDAO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private static Date date;
    
    public static boolean addAccounts(boolean inScheme, String schemeName, Integer amountPaid, String frid, String trackID, String uid, String toName)
            throws SQLException {
        
        try {
            Connection con;
            PreparedStatement ps;
            PreparedStatement ps1;
            PreparedStatement ps2;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = new Date();
            con = Apache_Connectionpool.getInstance().getConnection();
            
            ps = con.prepareStatement("insert into accounts_opd(inScheme,schemeName,amountPaid,frid,trackID,uid) values(?,?,?,?,?,?)");
            ps.setBoolean(1, inScheme);
            ps.setString(2, schemeName);
            ps.setInt(3, amountPaid.intValue());
            ps.setString(4, frid);
            ps.setString(5, trackID);
            ps.setString(6, uid);
            
            ps.executeUpdate();

            //------------------------******************-----------------------------
            ps1 = con.prepareStatement("insert into account_opd_track(trackID,uid,status,systemDate,forwardTo) values(?,?,?,?,?)");
            
            ps1.setString(1, trackID);
            ps1.setString(2, uid);
            ps1.setString(3, "Pending");
            ps1.setString(4, dateFormat.format(date = new Date()));
            ps1.setString(5, toName);
            
            ps1.executeUpdate();
            
            ps2 = con.prepareStatement("update fronttrack set status = 'Completed' where trackID = ?");
            ps2.setString(1, trackID);
            ps2.executeUpdate();
            
            con.close();
            ps.close();
            ps1.close();
            ps2.close();

            //-------------------------****************----------------------
            return true;
            
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Accounts DAO", "addAccounts", "Error Code: " + ex.getMessage() + " Message: " + ex.getMessage(), date));
            return false;
        }
    }
    
    public static List<Accounts> getDoneJobs()
            throws SQLException {
        
        try {
            Connection con;
            Accounts accounts;
            
            con = Apache_Connectionpool.getInstance().getConnection();
            
            date = new Date();
            
            PreparedStatement stmt = con.prepareStatement("select inScheme,amountPaid,patientID,at.status,at.forwardTo from accounts_opd a,frontdesk f,account_opd_track at where a.TrackID = f.TrackID and a.TrackID=at.TrackID;");
            
            ResultSet rs = stmt.executeQuery();
            
            List accountsList = new ArrayList();
            
            while (rs.next()) {
                accounts = new Accounts(rs.getBoolean("inScheme"), Integer.valueOf(rs.getInt("amountPaid")), rs.getString("forwardTo"), rs.getString("status"), rs.getString("patientID"));
                
                accountsList.add(accounts);
            }
            
            con.close();
            rs.close();
            return accountsList;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Accounts DAO", "getDoneJobs", " Message: " + ex.getMessage(), date));
            return null;
        }
    }
    
    public static boolean schemeMember(String patientID) throws SQLException {
        
        try {
            Connection con;
            
            con = Apache_Connectionpool.getInstance().getConnection();
            
            date = new Date();
            
            PreparedStatement stmt = con.prepareStatement("select * from member where memberId='" + patientID + "'");
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                con.close();
                return true;
            }
            
            con.close();
            rs.close();
            return false;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Accounts DAO", "schemeMember", " Message: " + ex.getMessage(), date));
            return false;
        }
    }
    
    public static void TaskHistory_Update(String staff_id, String Status, String taskId) throws SQLException {
        
        try {
            Connection con;
            
            con = Apache_Connectionpool.getInstance().getConnection();
            
            date = new Date();
            
            PreparedStatement ps = con.prepareStatement("update task_history set status =? where To_Staff_ID =? and Status='Pending' and Track_ID=? ");
            ps.setString(1, Status);
            ps.setString(2, staff_id);
            ps.setString(3, taskId);
            
            ps.executeUpdate();
            con.close();
            ps.close();
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Accounts DAO", "TaskHistory_Update", " Message: " + ex.getMessage(), date));
        }
    }
    
    public static List<AccountsTasks> Accounts_Get_Pending(String staff_id) throws SQLException {
        
        try {
            Connection con;
            AccountsTasks accountsTasks;
            
            con = Apache_Connectionpool.getInstance().getConnection();
            
            date = new Date();

//            PreparedStatement stmt = con.prepareStatement("select f.Track_Id,a.Operation,f.Patient_Id,f.Patient_Name,f.Age,f.Gender,f.VisitReason,f.Problem,f.Triage_Category,a.Payable_Amount,a.Forward_To,a.Skipped_Accounts,a.Locked,f.Member,f.Subscription_Expired,a.Staff_Id,a.Reason,a.Staff_From from frontdesk_tasks f inner join accounts_pending a on f.Track_Id=a.Track_Id group by f.Track_Id order by f.Record_Date_In desc");
            PreparedStatement stmt = con.prepareStatement("select f.Track_Id,a.Operation,f.Patient_Id,f.Patient_Name,f.Age,f.Gender,f.VisitReason,f.Problem,f.Triage_Category,a.Payable_Amount,a.Forward_To,a.Skipped_Accounts,a.Locked,f.Member,f.Subscription_Expired,a.Staff_Id,a.Reason,a.Staff_From from frontdesk_tasks f inner join accounts_pending a on f.Track_Id=a.Track_Id left join ward_discharge wd on f.Track_Id=wd.Track_Id group by f.Track_Id order by f.Patient_Id desc");
            
            ResultSet rs = stmt.executeQuery();
            
            List pendingTasks = new ArrayList();
            Integer color_code;
            while (rs.next()) {
                color_code = Integer.valueOf(-1);
                
                if (!rs.getBoolean("Locked")) {
                    if ("Gray".equals(rs.getString("Triage_Category"))) {
                        color_code = Integer.valueOf(0);
                    } else if ("Green".equals(rs.getString("Triage_Category"))) {
                        color_code = Integer.valueOf(1);
                    } else if ("Red".equals(rs.getString("Triage_Category"))) {
                        color_code = Integer.valueOf(2);
                    } else if ("Yellow".equals(rs.getString("Triage_Category"))) {
                        color_code = Integer.valueOf(3);
                    } else if ("Blue".equals(rs.getString("Triage_Category"))) {
                        color_code = Integer.valueOf(4);
                    } else if ("Orange".equals(rs.getString("Triage_Category"))) {
                        color_code = Integer.valueOf(5);
                    }
                    accountsTasks = new AccountsTasks(rs.getString("Track_Id"), rs.getString("Operation"), rs.getString("Patient_Id"), rs.getString("Patient_Name"), Integer.valueOf(rs.getInt("Age")), rs.getString("Gender"), rs.getString("VisitReason"), rs.getString("Problem"), rs.getString("Triage_Category"), Integer.valueOf(rs.getInt("Payable_Amount")), rs.getString("Forward_To"), rs.getBoolean("Skipped_Accounts"), rs.getBoolean("Locked"), color_code, rs.getBoolean("Member"), rs.getBoolean("Subscription_Expired"), rs.getString("Reason"), rs.getString("Staff_From"));
                    
                    pendingTasks.add(accountsTasks);
                } else if (staff_id.equals(rs.getString("Staff_Id"))) {
                    if ("Gray".equals(rs.getString("Triage_Category"))) {
                        color_code = Integer.valueOf(0);
                    } else if ("Green".equals(rs.getString("Triage_Category"))) {
                        color_code = Integer.valueOf(1);
                    } else if ("Red".equals(rs.getString("Triage_Category"))) {
                        color_code = Integer.valueOf(2);
                    } else if ("Yellow".equals(rs.getString("Triage_Category"))) {
                        color_code = Integer.valueOf(3);
                    } else if ("Blue".equals(rs.getString("Triage_Category"))) {
                        color_code = Integer.valueOf(4);
                    } else if ("Orange".equals(rs.getString("Triage_Category"))) {
                        color_code = Integer.valueOf(5);
                    }
                    accountsTasks = new AccountsTasks(rs.getString("Track_Id"), rs.getString("Operation"), rs.getString("Patient_Id"), rs.getString("Patient_Name"), Integer.valueOf(rs.getInt("Age")), rs.getString("Gender"), rs.getString("VisitReason"), rs.getString("Problem"), rs.getString("Triage_Category"), Integer.valueOf(rs.getInt("Payable_Amount")), rs.getString("Forward_To"), rs.getBoolean("Skipped_Accounts"), rs.getBoolean("Locked"), color_code, rs.getBoolean("Member"), rs.getBoolean("Subscription_Expired"), rs.getString("Reason"), rs.getString("Staff_From"));
                    
                    pendingTasks.add(accountsTasks);
                }
                
            }
            
            con.close();
            
            return pendingTasks;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Accounts DAO", "Accounts_Get_Pending", " Message: " + ex.getMessage(), date));
            return null;
        }
    }
    
    public static AccountsTasks Accounts_Get_Selected(String task_id) throws SQLException {
        
        try {
            Connection con;
            PreparedStatement stmt;
            
            con = Apache_Connectionpool.getInstance().getConnection();
            
            date = new Date();
            
            stmt = con.prepareStatement("SELECT f.Track_Id,f.Patient_Id,f.Patient_Name,TIMESTAMPDIFF(YEAR, f.DOB, CURDATE()) AS Age,f.Gender,f.VisitReason,f.Problem,f.Triage_Category,f.Member,f.Subscription_Expired FROM  frontdesk_tasks f  WHERE f.Track_Id=?");
            stmt.setString(1, task_id);
            
            ResultSet rs = stmt.executeQuery();
            Integer color_code;
            if (rs.next()) {
                color_code = -1;
                if ("Gray".equals(rs.getString("Triage_Category"))) {
                    color_code = 0;
                } else if ("Green".equals(rs.getString("Triage_Category"))) {
                    color_code = 1;
                } else if ("Red".equals(rs.getString("Triage_Category"))) {
                    color_code = 2;
                } else if ("Yellow".equals(rs.getString("Triage_Category"))) {
                    color_code = 3;
                } else if ("Blue".equals(rs.getString("Triage_Category"))) {
                    color_code = 4;
                } else if ("Orange".equals(rs.getString("Triage_Category"))) {
                    color_code = 5;
                }
                AccountsTasks accountsTasks = new AccountsTasks(rs.getString("Track_Id"), "", rs.getString("Patient_Id"), rs.getString("Patient_Name").toUpperCase(), Integer.valueOf(rs.getInt("Age")), rs.getString("Gender"), rs.getString("VisitReason"), rs.getString("Problem"), rs.getString("Triage_Category"), Integer.valueOf(rs.getInt("Payable_Amount")), rs.getString("Forward_To").toUpperCase(), rs.getBoolean("Skipped_Accounts"), rs.getBoolean("Locked"), color_code, rs.getBoolean("Member"), rs.getBoolean("Subscription_Expired"), rs.getString("Reason").toUpperCase(), rs.getString("Staff_From"), String.valueOf(rs.getBoolean("Subscription_Expired")).toUpperCase(), String.valueOf(rs.getBoolean("Member")).toUpperCase(), String.valueOf(rs.getBoolean("Skipped_Accounts")).toUpperCase(), rs.getString("Staff_Type"));
                
                con.close();
                rs.close();
                return accountsTasks;
            }
            
            con.close();
            rs.close();
            return null;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Accounts DAO", "Accounts_Get_Selected", " Message: " + ex.getMessage(), date));
            return null;
        }
    }
    
    public static boolean Accounts_Task_Lock(String task_id, boolean lock, String staff_id) throws SQLException {
        
        try {
            Connection con;
            
            con = Apache_Connectionpool.getInstance().getConnection();
            
            date = new Date();
            
            PreparedStatement stmt = con.prepareStatement("update accounts_pending set Locked=?,Staff_Id=? where Track_Id=?");
            stmt.setBoolean(1, lock);
            stmt.setString(2, staff_id);
            stmt.setString(3, task_id);
            stmt.executeUpdate();
            
            con.close();
            stmt.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Accounts DAO", "Accounts_Task_Lock", " Message: " + ex.getMessage(), date));
            return false;
        }
    }
    
    public static boolean Accounts_Check_IPD_task(String track_id) throws SQLException {
        
        try {
            Connection con;
            Boolean ret_val = false;
            
            con = Apache_Connectionpool.getInstance().getConnection();
            
            date = new Date();
            
            PreparedStatement stmt = con.prepareStatement("SELECT Staff_Type FROM bwindihospital.accounts_pending WHERE Track_Id=? AND Staff_Type IS NOT NULL");
            stmt.setString(1, track_id);
            
            ResultSet rs = stmt.executeQuery();

//            System.out.println("track_id :: " + track_id);
            if (rs.next()) {
//                System.out.println("track_id :: " + track_id);
//                System.out.println("hhhhhhhhhhhhhhhhh");
//                System.out.println("rs.getString :: " + rs.getString("Staff_Type"));
                if (rs.getString("Staff_Type").equalsIgnoreCase("IPD")) {
                    ret_val = true;
//                    System.out.println("vvvvvvvvvvvvvvvvvvvv");
                }
            }
            
            con.close();
            rs.close();
            
            return ret_val;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Accounts DAO", "Accounts_Check_IPD_task", " Message: " + ex.getMessage(), date));
            return false;
        }
    }
    
    public static boolean Accounts_Check_Ward_Payment_Exists(String track_id) throws SQLException {
        
        try {
            Connection con;
            Boolean ret_val = false;
            
            con = Apache_Connectionpool.getInstance().getConnection();
            
            date = new Date();
            
            PreparedStatement stmt = con.prepareStatement("SELECT Operation FROM bwindihospital.accounts_tasks WHERE Track_Id=? and Operation Is Not Null");
            stmt.setString(1, track_id);
            
            ResultSet rs = stmt.executeQuery();

//            System.out.println("track_id :: " + track_id);
            if (rs.next()) {
//                System.out.println("track_id :: " + track_id);
//                System.out.println("rs.getString :: " + rs.getString("Operation"));
                if (rs.getString("Operation").equalsIgnoreCase("WARD PAYMENT")) {
                    ret_val = true;
//                    System.out.println("okay");
                }
            }
            
            con.close();
            rs.close();
            return ret_val;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Accounts DAO", "Accounts_Check_Ward_Payment_Exists", " Message: " + ex.getMessage(), date));
            return false;
        }
    }
    
    public static boolean Accounts_Add_Task(String trackID, String operation, Integer payableAmount, Integer amountPaid, Integer request_id, String uid) throws SQLException {
        
        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            con = Apache_Connectionpool.getInstance().getConnection();
            
            date = new Date();
            
            PreparedStatement ps = con.prepareStatement("insert into accounts_tasks(Track_Id,Operation,Payable_Amount,Amount_Paid,Staff_Id,Record_Date,Request_id) values(?,?,?,?,?,?,?)");
            ps.setString(1, trackID);
            ps.setString(2, operation);
            ps.setInt(3, payableAmount.intValue());
            ps.setInt(4, amountPaid.intValue());
            ps.setString(5, uid);
            ps.setString(6, dateFormat.format(date));
            ps.setInt(7, request_id);
            
            ps.executeUpdate();
            
            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Accounts DAO", "Accounts_AddTask", " Message: " + ex.getMessage(), date));
            return false;
        }
        
    }
    
    public static boolean Accounts_Consultant_Pending(String trackID, String staffId) throws SQLException {
        
        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            con = Apache_Connectionpool.getInstance().getConnection();
            
            date = new Date();
            
            PreparedStatement ps = con.prepareStatement("insert into consultant_pending(Track_Id,From_Staff,Record_Date) values(?,?,?)");
            ps.setString(1, trackID);
            ps.setString(2, staffId);
            ps.setString(3, dateFormat.format(date));
            
            ps.executeUpdate();
            
            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Accounts DAO", "Accounts_Consultant_Pending", " Message: " + ex.getMessage(), date));
            return false;
        }
        
    }
    
    public static boolean Accounts_Dentist_Pending(String trackID, String staffId) throws SQLException {
        
        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();
            
            con = Apache_Connectionpool.getInstance().getConnection();
            
            PreparedStatement ps = con.prepareStatement("insert into dentist_pending(Track_Id,From_Staff,Record_Date) values(?,?,?)");
            ps.setString(1, trackID);
            ps.setString(2, staffId);
            ps.setString(3, dateFormat.format(date));
            
            ps.executeUpdate();
            
            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Accounts DAO", "Accounts_Dentist_Pending", " Message: " + ex.getMessage(), date));
            return false;
        }
        
    }
    
    public static boolean Accounts_Delete_Pending(Integer request_id) throws SQLException {
        
        try {
            Connection con;
            
            con = Apache_Connectionpool.getInstance().getConnection();
            
            date = new Date();
            
            PreparedStatement ps = con.prepareStatement("delete from accounts_pending where Request_Id=?");
            ps.setInt(1, request_id);
            
            ps.executeUpdate();
            
            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Accounts DAO", "Accounts_Delete_Pending", " Message: " + ex.getMessage(), date));
            return false;
        }
        
    }
    
    public static List<AccountsPayments> Accounts_Get_Payments(String task_id) throws SQLException {
        
        try {
            Connection con;
            
            AccountsPayments payment;
            
            con = Apache_Connectionpool.getInstance().getConnection();
            
            date = new Date();
            
            PreparedStatement stmt = con.prepareStatement("select * from accounts_pending where Request_Id NOT IN (SELECT Request_id FROM accounts_tasks) AND Track_Id=?");
            stmt.setString(1, task_id);
            ResultSet rs = stmt.executeQuery();
            
            List payments = new ArrayList();
            
            while (rs.next()) {
                payment = new AccountsPayments();
                payment.setRequest_id(rs.getInt("Request_Id"));
                payment.setTask_id(rs.getString("Track_Id"));
                payment.setOperation(rs.getString("Operation"));
                payment.setAmount(rs.getInt("Payable_Amount"));
                payment.setReason(rs.getString("Reason"));
                
                payments.add(payment);
            }
            
            con.close();
            rs.close();
            return payments;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Accounts DAO", "Accounts_Get_Payments", " Message: " + ex.getMessage(), date));
            return null;
        }
    }
    
    public static List<AccountsPaymentsView> Accounts_Get_Payments_All(String track_id)
            throws SQLException {
        try {
            Connection con;
            con = Apache_Connectionpool.getInstance().getConnection();
            
            date = new Date();
            
            PreparedStatement stmt = con.prepareStatement("SELECT a.Track_Id, a.Operation, a.Amount_Paid FROM accounts_tasks a WHERE a.Track_Id=?");
            stmt.setString(1, track_id);
            
            ResultSet rs = stmt.executeQuery();
            
            List payment_list = new ArrayList();
            int num = 0;
            while (rs.next()) {
                num++;
                AccountsPaymentsView payment = new AccountsPaymentsView();
                
                payment.setTrack_id(rs.getString("Track_Id"));
                payment.setOperation(rs.getString("Operation"));
                payment.setAmount(rs.getString("Amount_Paid"));
                payment.setNumber(num);
                
                payment_list.add(payment);
            }
            
            con.close();
            rs.close();
            return payment_list;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Accounts DAO", "Accounts_Get_Payments_All", " Message: " + ex.getMessage(), date));
            return null;
        }
        
    }
    
    public static boolean Accounts_Time_Audit_Add(String track_id, String staff_from)
            throws SQLException, ClassNotFoundException {
        
        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();
            
            con = Apache_Connectionpool.getInstance().getConnection();
            
            PreparedStatement ps = con.prepareStatement("insert into accounts_time_audit(Task_Id,Start_Time,Staff_From) values(?,?,?)");
            
            ps.setString(1, track_id);
            ps.setString(2, dateFormat.format(date));
            ps.setString(3, staff_from);
            
            ps.executeUpdate();
            
            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Accounts DAO", "Accounts_Time_Audit_Add", " Message: " + ex.getMessage(), date));
            return false;
        }
        
    }
    
    public static boolean Accounts_Time_Audit_Update(String track_id, String forwarded_to) throws SQLException, ClassNotFoundException {
        
        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();
            
            con = Apache_Connectionpool.getInstance().getConnection();
            
            PreparedStatement ps = con.prepareStatement("update accounts_time_audit set End_Time=?,Forwarded_To=? where Task_Id=? and Forwarded_To Is NULL");
            
            ps.setString(1, dateFormat.format(date));
            ps.setString(2, forwarded_to);
            ps.setString(3, track_id);
            
            ps.executeUpdate();
            
            con.close();
            ps.close();
            
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Accounts DAO", "Accounts_Time_Audit_Update", " Message: " + ex.getMessage(), date));
            return false;
        }
        
    }

    /*
     * 
     * 
     * 
     * 
     */
}
