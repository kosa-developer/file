package managedDao;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import managedModal.Drugstore;
import managedModal.ReceptionTask;
import managedModal.Users;
import managedModal.Error;
public class ConsultantDAO
        implements Serializable {

    private static final long serialVersionUID = 1L;
    public static Date date;

    // <editor-fold defaultstate="collapsed" desc="General">  
    public static List<ReceptionTask> Consultant_Get_Pending(String staff_id)
            throws SQLException {

        try {
            Connection con;

            ReceptionTask receptiontask;

            con = Apache_Connectionpool.getInstance().getConnection();

            date = new Date();

//            PreparedStatement stmt = con.prepareStatement("SELECT f.Track_Id,f.Patient_Id,f.Patient_Name,f.Age,f.Gender,f.VisitReason,f.Problem,f.Triage_Category,d.DepartmentName,c.Locked,c.Staff_Id,f.Room_No,c.Record_Date FROM consultant_pending c INNER JOIN frontdesk_tasks f on c.Track_Id=f.Track_Id INNER JOIN users u on c.From_Staff=u.UID INNER JOIN department d on u.DID=d.DID ORDER BY c.Record_Date ASC");
            PreparedStatement stmt = con.prepareStatement("SELECT f.Track_Id,f.Patient_Id,f.Patient_Name,TIMESTAMPDIFF(YEAR, f.DOB, CURDATE()) AS Age,f.Gender,f.VisitReason,f.Problem,f.Triage_Category,d.DepartmentName,c.Locked,c.Staff_Id,f.Room_No,concat(Date(c.Record_Date),' ',Hour(c.Record_Date),':',Lpad(Minute(c.Record_Date),2,'0')) As Record_Date,f.Record_Errors FROM consultant_pending c INNER JOIN frontdesk_tasks f on c.Track_Id=f.Track_Id INNER JOIN users u on c.From_Staff=u.UID INNER JOIN department d on u.DID=d.DID ORDER BY c.Record_Date ASC");

            ResultSet rs = stmt.executeQuery();
            List pending_list = new ArrayList();
            Integer color_code;
            while (rs.next()) {
                color_code = -1;

                if (!rs.getBoolean("Locked")) {
                    if ("laboratory".equalsIgnoreCase(rs.getString("DepartmentName"))) {
                        if ("Blue".equals(rs.getString("Triage_Category"))) {
                            color_code = 4;
                        } else {
                            color_code = 0;
                        }
                    } else if ("Gray".equals(rs.getString("Triage_Category"))) {
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

                    receptiontask = new ReceptionTask(rs.getString("Track_Id"), rs.getString("Patient_Id"), rs.getString("Patient_Name"), rs.getInt("Age"), rs.getString("Gender"), rs.getString("VisitReason"), rs.getString("Problem"), color_code, rs.getString("Room_No"), rs.getString("Record_Date"), rs.getString("DepartmentName"), rs.getString("Record_Errors"));
//                    receptiontask = new ReceptionTask(rs.getString("Track_Id"), rs.getString("Patient_Id"), rs.getString("Patient_Name"), rs.getInt("Age"), rs.getString("Gender"), rs.getString("VisitReason"), rs.getString("Problem"), color_code, rs.getString("Room_No"), rs.getString("Record_Date"));

                    pending_list.add(receptiontask);
                } else if (staff_id.equals(rs.getString("Staff_Id"))) {
                    if ("laboratory".equalsIgnoreCase(rs.getString("DepartmentName"))) {
                        if ("Blue".equals(rs.getString("Triage_Category"))) {
                            color_code = 4;
                        } else {
                            color_code = 3;
                        }
                    } else if ("Gray".equals(rs.getString("Triage_Category"))) {
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

                    receptiontask = new ReceptionTask(rs.getString("Track_Id"), rs.getString("Patient_Id"), rs.getString("Patient_Name"), rs.getInt("Age"), rs.getString("Gender"), rs.getString("VisitReason"), rs.getString("Problem"), color_code, rs.getString("Room_No"), rs.getString("Record_Date"), rs.getString("DepartmentName"), rs.getString("Record_Errors"));
//                    receptiontask = new ReceptionTask(rs.getString("Track_Id"), rs.getString("Patient_Id"), rs.getString("Patient_Name"), rs.getInt("Age"), rs.getString("Gender"), rs.getString("VisitReason"), rs.getString("Problem"), color_code, rs.getString("Room_No"), rs.getString("Record_Date"));

                    pending_list.add(receptiontask);
                }

            }

            con.close();
            rs.close();
            return pending_list;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Pending", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static List<ReceptionTask> Consultant_Get_Sent_To_Ward(String staff_id)
            throws SQLException {

        try {
            Connection con;

            ReceptionTask receptiontask;

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT f.Track_Id,f.Patient_Id,f.Patient_Name,f.Age,f.Gender,f.VisitReason,f.Problem,f.Triage_Category,f.Room_No,concat(Date(f.Record_Date_In),' ',Hour(f.Record_Date_In),':',Lpad(Minute(f.Record_Date_In),2,'0')) As Record_Date_In FROM ward_summary w INNER JOIN frontdesk_tasks f on w.Track_Id=f.Track_Id Where forward_to_section='Consultant' and Record_Date_In>='2017-04-01' ORDER BY f.Record_Date_in DESC LIMIT 50");

            ResultSet rs = stmt.executeQuery();
            List sent_to_ward_list = new ArrayList();
            Integer color_code;
            while (rs.next()) {
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

                receptiontask = new ReceptionTask(rs.getString("Track_Id"), rs.getString("Patient_Id"), rs.getString("Patient_Name"), rs.getInt("Age"), rs.getString("Gender"), rs.getString("VisitReason"), rs.getString("Problem"), color_code, rs.getString("Room_No"), rs.getString("Record_Date_In"));

                sent_to_ward_list.add(receptiontask);

            }

            con.close();
            rs.close();
            return sent_to_ward_list;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Sent_To_Ward", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static boolean Task_Status_Update(String track_id, String consultant_id, String status)
            throws SQLException {

        try {
            Connection con;
            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("update task_history set Status=? WHERE Track_Id=? and To_Staff_ID=?");

            ps.setString(1, status);
            ps.setString(2, track_id);
            ps.setString(3, consultant_id);
            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Task_Status_Update", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static boolean Consultant_Flag_Record_Errors(String Track_Id, String Record_Errors)
            throws SQLException {

        try {
//            System.out.println("Record_Errors = " + Record_Errors);
            Connection con;
            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("update frontdesk_tasks set Record_Errors=? WHERE Track_Id=?");
            ps.setString(1, Record_Errors);
            ps.setString(2, Track_Id);
            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Flag_Record_Errors", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static boolean Consultant_Task_Lock(String task_id, boolean lock, String staff_id)
            throws SQLException {

        try {
            Connection con;

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("update consultant_pending set Locked=?,Staff_Id=? WHERE Track_Id=?");
            stmt.setBoolean(1, lock);
            stmt.setString(2, staff_id);
            stmt.setString(3, task_id);
            stmt.executeUpdate();

            con.close();
            stmt.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Task_Lock", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static boolean Consultant_Time_Audit_Start(String track_id, String staff_FROM)
            throws SQLException, ClassNotFoundException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con = Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO consultant_time_audit(Task_Id,Start_Time,Staff_From) VALUES(?,?,?)");

            ps.setString(1, track_id);
            ps.setString(2, dateFormat.format(date));
            ps.setString(3, staff_FROM);

            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Time_Audit_Start", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static boolean Consultant_Time_Audit_Update(String track_id, String forwarded_to) throws SQLException, ClassNotFoundException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con = Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("update consultant_time_audit set End_Time=?,Forwarded_To=? WHERE Task_Id=? and Forwarded_To Is NULL");

            ps.setString(1, dateFormat.format(date));
            ps.setString(2, forwarded_to);
            ps.setString(3, track_id);

            ps.executeUpdate();

            con.close();
            ps.close();

            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Time_Audit_Update", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static boolean Consultant_Time_Audit_Exists(String task_id) throws SQLException {

        try {
            Connection con;

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM consultant_time_audit WHERE Task_Id=? and Forwarded_To Is NULL");
            stmt.setString(1, task_id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                con.close();
                return true;
            }

            con.close();
            rs.close();
            return false;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Time_Audit_Exists", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static String Consultant_Get_Forwarded_From(String task_id) throws SQLException {

        try {
            Connection con;

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT From_Staff FROM consultant_pending WHERE Track_Id=?");
            stmt.setString(1, task_id);

            ResultSet rs = stmt.executeQuery();
            String staff_FROM = "";

            if (rs.next()) {
                staff_FROM = rs.getString("From_Staff");
                con.close();
            }
            rs.close();
            return staff_FROM;

        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Forwarded_From", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static boolean Consultant_Accounts_Task_Exists(String task_id)
            throws SQLException {

        try {
            Connection con;
            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM accounts_pending WHERE Track_Id=?");
            stmt.setString(1, task_id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                con.close();
                return true;
            }

            con.close();
            rs.close();
            return false;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Accounts_Task_Exists", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static boolean Consultant_Accounts_Pending(String trackID, Integer amount, String extrapay_reason, boolean skipped, String forward_to, boolean locked, String user_id)
            throws SQLException, ClassNotFoundException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con = Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO accounts_pending(Track_Id,Operation,Payable_Amount,Forward_To,Skipped_Accounts,Record_Time,Locked,Reason,Staff_From) VALUES(?,?,?,?,?,?,?,?,?)");

            ps.setString(1, trackID);
            ps.setString(2, "Extra Payment");
            ps.setInt(3, amount.intValue());
            ps.setString(4, forward_to);
            ps.setBoolean(5, skipped);
            ps.setString(6, dateFormat.format(date));
            ps.setBoolean(7, locked);
            ps.setString(8, extrapay_reason);
            ps.setString(9, user_id);

            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Accounts_Pending", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static boolean Consultant_Accounts_Update_Pending(String trackID, Integer amount, String extrapay_reason, boolean skipped, String forward_to, boolean locked, String user_id)
            throws SQLException, ClassNotFoundException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("update accounts_pending set Operation=?,Payable_Amount=?,Forward_To=?,Skipped_Accounts=?,Record_Time=?,Locked=?,Reason=?,Staff_From=? WHERE Track_Id=?");

            ps.setString(1, "Extra Payment");
            ps.setInt(2, amount.intValue());
            ps.setString(3, forward_to);
            ps.setBoolean(4, skipped);
            ps.setString(5, dateFormat.format(date));
            ps.setBoolean(6, locked);
            ps.setString(7, extrapay_reason);
            ps.setString(8, user_id);
            ps.setString(9, trackID);

            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Accounts_Pending", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static boolean Consultant_Accounts_Update_Pending(String track_id, String user_id, String staff_type)
            throws SQLException, ClassNotFoundException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("update accounts_pending set Staff_From=?,Staff_Type=?,Forward_To=?,Record_Time=? WHERE Track_Id=?");

            ps.setString(1, user_id);
            ps.setString(2, staff_type);
            ps.setString(3, "Dispensary");
            ps.setString(4, dateFormat.format(date));
            ps.setString(5, track_id);

            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Accounts_Pending", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static boolean Consultant_Delete_Pending(String trackID)
            throws SQLException {

        try {
            Connection con;

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("delete FROM consultant_pending WHERE Track_Id=?");
            ps.setString(1, trackID);

            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Delete_Pending", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static boolean Consultant_Dispensary_Pending(String track_id, String user_id, String staff_type)
            throws SQLException, ClassNotFoundException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con = Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO dispensary_pending(Track_Id,Staff_From,Staff_Type,Record_Date) VALUES(?,?,?,?)");

            ps.setString(1, track_id);
            ps.setString(2, user_id);
            ps.setString(3, staff_type);
            ps.setString(4, dateFormat.format(date));

            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Dispensary_Pending", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static boolean Consultant_Add_Task(String track_id, String user_id)
            throws SQLException, ClassNotFoundException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con = Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO consultant_tasks(Track_Id,Staff_Id,Record_Date) VALUES(?,?,?)");

            ps.setString(1, track_id);
            ps.setString(2, user_id);
            ps.setString(3, dateFormat.format(date));

            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Add_Task", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

//    public static boolean Consultant_Add_Referal_Note(ReferalNote referal) throws SQLException {
//
//        try {
//            Connection con;
//            PreparedStatement stmt;
//
//            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            date = new Date();
//
//            con = Apache_Connectionpool.getInstance().getConnection();
//
//            stmt = con.prepareStatement("INSERT INTO referal_out(Task_Id,Refered_To,Referal_No,First_Visit_Date,History_Symptoms,Referal_Reason,Refered_By,Referal_Date) VALUES(?,?,?,?,?,?,?,?)");
//
//            stmt.setString(1, referal.getTask_id());
//            stmt.setString(2, referal.getRefered_to());
//            stmt.setString(3, referal.getReferal_no());
//            stmt.setString(4, dateFormat.format(referal.getFirst_visit_date()));
//            stmt.setString(5, referal.getHistory_symptoms());
//            stmt.setString(6, referal.getReferal_reason());
//            stmt.setString(7, referal.getRefered_by());
//            stmt.setString(8, dateFormat.format(date));
//
//            stmt.executeUpdate();
//            con.close();
//            stmt.close();
//            return true;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Add_Referal_Note", " Message: " + ex.getMessage(), date));
//            return false;
//        }
//    }

    public static boolean Consultant_Add_Followup_Date(String track_id, Date follow_up_date) throws SQLException, ClassNotFoundException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = new Date();

            con = Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("update frontdesk_tasks set Follow_Up_Date=? WHERE Track_Id=?");

            ps.setString(1, dateFormat.format(follow_up_date));
            ps.setString(2, track_id);

            ps.executeUpdate();

            con.close();
            ps.close();

            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Add_Followup_Date", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static Date Consultant_Get_Followup_Date(String track_id) throws SQLException, ClassNotFoundException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = new Date();

            con = Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement stmt = con.prepareStatement("SELECT Follow_Up_Date FROM frontdesk_tasks WHERE Track_Id=?");
            stmt.setString(1, track_id);
            ResultSet rs = stmt.executeQuery();

            Date follow_up_date = null;
            if (rs.next()) {
                follow_up_date = rs.getDate("Follow_Up_Date");
            }
            con.close();
            rs.close();
            return follow_up_date;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Followup_Date", " Message: " + ex.getMessage(), date));
            return null;
        }

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="History">      
//    public static List<History> Consultant_Get_Last_Visits(String _value, String _criteria)
//            throws SQLException {
//
//        try {
//            Connection con;
//            con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//
//            PreparedStatement stmt = null;
//
////            if (_criteria.equals("Name")) {
////                stmt = con.prepareStatement("SELECT f.Track_Id,f.Patient_Id,upper(f.Patient_Name) as Patient_Name,f.Age,f.Gender,f.VisitDate FROM frontdesk_tasks f WHERE upper(f.Patient_Name)=? order by f.VisitDate desc limit 5;");
////            } else if (_criteria.equals("Id")) {
////                stmt = con.prepareStatement("SELECT f.Track_Id,f.Patient_Id,upper(f.Patient_Name) as Patient_Name,f.Age,f.Gender,f.VisitDate FROM frontdesk_tasks f WHERE f.Patient_Id=? order by f.VisitDate desc limit 5;");
////            }
//            if (_criteria.equals("Name")) {
//                stmt = con.prepareStatement("SELECT f.Track_Id,f.Patient_Id,upper(f.Patient_Name) as Patient_Name,"
//                        + "f.Age,f.Gender,"
//                        + "Case When f.Track_Id In (Select Track_Id From ward_admit) Then 'Yes' Else 'No' End As Admitted,"
//                        + "Case When f.Track_Id In (Select Track_Id From ward_discharge) Then 'Yes' Else 'No' End As Discharged,"
//                        + "Date(f.VisitDate) As VisitDate FROM frontdesk_tasks f WHERE upper(f.Patient_Name)=? order by f.VisitDate desc;");
//            } else if (_criteria.equals("Id")) {
//                stmt = con.prepareStatement("SELECT f.Track_Id,f.Patient_Id,upper(f.Patient_Name) as Patient_Name,"
//                        + "f.Age,f.Gender,"
//                        + "Case When f.Track_Id In (Select Track_Id From ward_admit) Then 'Yes' Else 'No' End As Admitted,"
//                        + "Case When f.Track_Id In (Select Track_Id From ward_discharge) Then 'Yes' Else 'No' End As Discharged,"
//                        + "Date(f.VisitDate) As VisitDate FROM frontdesk_tasks f WHERE f.Patient_Id=? order by f.VisitDate desc;");
//            }
//
//            stmt.setString(1, _value);
//
//            ResultSet rs = stmt.executeQuery();
//
//            List visits_list = new ArrayList();
//
//            visits_list.clear();
//            while (rs.next()) {
//                History _history = new History();
//
//                _history.setTrack_Id(rs.getString("Track_Id"));
//                _history.setPatient_Id(rs.getString("Patient_Id"));
//                _history.setPatient_Name(rs.getString("Patient_Name"));
//                _history.setAge(rs.getString("Age"));
//                _history.setGender(rs.getString("Gender"));
//                _history.setAdmitted(rs.getString("Admitted"));
//                _history.setDischarged(rs.getString("Discharged"));
//                _history.setVisit_Date(rs.getString("VisitDate"));
//
//                visits_list.add(_history);
//            }
//            con.close();
//            rs.close();
//            return visits_list;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Last_Visits", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//    }
//
//    public static List<HistoryTreatment> Consultant_Get_Prescription_History(String task_id)
//            throws SQLException {
//        try {
//            Connection con;
//            con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//
//            PreparedStatement stmt = con.prepareStatement("SELECT distinct(d.Diagnosis),c.Prescription_Notes,dl.item_name as Prescription,c.Loading_Dose,c.Dose_Unit,c.Times,c.Times_Unit,c.Days,c.Administer,Date(c.Record_Date) as 'Diagnosis Date' "
//                    + "FROM consultant_treatment c "
//                    + "	INNER JOIN clinical_diagnosis d on c.Diagnosis=d.Record_Id "
//                    + "	INNER JOIN drug_list dl on c.Prescription_Id=dl.drug_id "
//                    + "WHERE c.Track_Id=? and dl.item_name<>'NONE'");
//            stmt.setString(1, task_id);
//            ResultSet rs = stmt.executeQuery();
//
//            List<HistoryTreatment> prescription_history_list = new ArrayList();
//            HistoryTreatment prescription_history = new HistoryTreatment();
//            while (rs.next()) {
//                prescription_history = new HistoryTreatment();
//                prescription_history.setDiagnosis(("NONE".equalsIgnoreCase(rs.getString("Diagnosis"))) ? "--------" : (rs.getString("Diagnosis")));
//                if (rs.getString("Prescription_Notes") == null) {
//                    prescription_history.setPrescription_notes("--------");
//                } else {
//                    prescription_history.setPrescription_notes(rs.getString("Prescription_Notes"));
//                }
//                prescription_history.setPrescribed_drug(("NONE".equalsIgnoreCase(rs.getString("Prescription"))) ? "--------" : (rs.getString("Prescription")));
//                prescription_history.setLoading_dose(rs.getString("Loading_Dose"));
//                prescription_history.setDose_unit(rs.getString("Dose_Unit"));
//                prescription_history.setDose_times(rs.getString("Times"));
//                prescription_history.setDose_times_unit(rs.getString("Times_Unit"));
//                prescription_history.setDose_days(rs.getString("Days"));
//                prescription_history.setAdminister(rs.getString("Administer"));
//                prescription_history.setRecord_date(rs.getString("Diagnosis Date"));
//                prescription_history_list.add(prescription_history);
//            }
//
//            con.close();
//            rs.close();
//            return prescription_history_list;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Prescription_History", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//    }
//
//    public static List<HistoryDiagnosis> Consultant_Get_Diagnosis_History(String task_id)
//            throws SQLException {
//
//        try {
//            Connection con;
//            con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//
//            PreparedStatement stmt = con.prepareStatement("SELECT distinct(d.Diagnosis),group_concat(Distinct c.Diagnosis_Category Separator '/') As Diagnosis_Category,"
//                    + "Case "
//                    + "When c.Trans_Id Is Not Null Then (Select u.FullName From ward_transactions w,users u Where c.Trans_Id=w.Trans_Id and w.Completed_By=u.UID) "
//                    + "When c.Track_Id In (Select t.Track_Id From consultant_tasks t) Then (Select u.FullName From consultant_tasks t,users u Where c.Track_Id=t.Track_Id and t.Staff_Id=u.UID) "
//                    + "Else ''"
//                    + "End as 'Diagnised By',"
//                    + "Date(c.Record_Date) as 'Diagnosis Date' "
//                    + "FROM consultant_treatment c INNER JOIN clinical_diagnosis d on c.Diagnosis=d.Record_Id "
//                    + "WHERE c.Track_Id=? and d.Diagnosis<>'NONE'");
//            stmt.setString(1, task_id);
//
//            ResultSet rs = stmt.executeQuery();
//            List<HistoryDiagnosis> diagnosis_history_list = new ArrayList();
//            HistoryDiagnosis diagnosis_history = new HistoryDiagnosis();
//            while (rs.next()) {
//                diagnosis_history = new HistoryDiagnosis();
//
////                diagnosis_history.setDiagnosis(rs.getString("Diagnosis"));
//                diagnosis_history.setDiagnosis(("NONE".equalsIgnoreCase(rs.getString("Diagnosis"))) ? "--------" : (rs.getString("Diagnosis")));
//                diagnosis_history.setDiagnosis_category(rs.getString("Diagnosis_Category"));
//                diagnosis_history.setDiagnosed_by(rs.getString("Diagnised By"));
//                diagnosis_history.setDiagnosis_date(rs.getString("Diagnosis Date"));
//
//                diagnosis_history_list.add(diagnosis_history);
//
//            }
//
//            con.close();
//            rs.close();
//
//            return diagnosis_history_list;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Diagnosis_History", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//    }
//
//    public static List<HistoryDiagnosis> Consultant_Get_Previous_Diagnoses(String Patient_Id, String Track_Id)
//            throws SQLException {
//        try {
//            Connection con;
//            con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//
//            PreparedStatement stmt = con.prepareStatement("SELECT cd.Diagnosis,GROUP_CONCAT(DISTINCT(DATE(ct.Record_Date)) ORDER BY ct.Record_Date DESC SEPARATOR ', ') AS Dates FROM consultant_treatment ct INNER JOIN frontdesk_tasks ft ON ft.Track_Id=ct.Track_Id INNER JOIN clinical_diagnosis cd ON cd.Record_Id=ct.Diagnosis WHERE Patient_Id=? and ft.Track_Id<>? and (ct.Diagnosis_Category='New chronic diagnosis' or ct.Diagnosis_Category='Existing chronic diagnosis') GROUP BY cd.Diagnosis ORDER BY GROUP_CONCAT(DISTINCT(DATE(ct.Record_Date)) ORDER BY ct.Record_Date DESC SEPARATOR ', ') DESC");
////            PreparedStatement stmt = con.prepareStatement("SELECT cd.Diagnosis,GROUP_CONCAT(DISTINCT(DATE(ct.Record_Date)) ORDER BY ct.Record_Date DESC SEPARATOR ', ') AS Dates FROM consultant_treatment ct INNER JOIN frontdesk_tasks ft ON ft.Track_Id=ct.Track_Id INNER JOIN clinical_diagnosis cd ON cd.Record_Id=ct.Diagnosis WHERE Patient_Id=? and ft.Track_Id<>? GROUP BY cd.Diagnosis ORDER BY GROUP_CONCAT(DISTINCT(DATE(ct.Record_Date)) ORDER BY ct.Record_Date DESC SEPARATOR ', ') DESC");            
//            stmt.setString(1, Patient_Id);
//            stmt.setString(2, Track_Id);
//            ResultSet rs = stmt.executeQuery();
//
//            List<HistoryDiagnosis> diagnosis_history_list = new ArrayList();
//            HistoryDiagnosis diagnosis_history;
//            while (rs.next()) {
//                diagnosis_history = new HistoryDiagnosis();
//                diagnosis_history.setDiagnosis(rs.getString("Diagnosis"));
//                System.out.println("Diagnosis: " + diagnosis_history.getDiagnosis());
//                diagnosis_history.setDiagnosis_date(rs.getString("Dates"));
//                diagnosis_history_list.add(diagnosis_history);
//            }
//
//            con.close();
//            rs.close();
//            return diagnosis_history_list;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Previous_Diagnoses", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//    }
//
//    public static List<HistoryTestResults> Consultant_Get_Tests_History(String task_id)
//            throws SQLException {
//
//        try {
//            Connection con;
//            HistoryTestResults test_result;
//
//            con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//
//            PreparedStatement stmt = con.prepareStatement("SELECT q.Request_Id,t.test_name,sp.specific_test,s.sub_test,e.result,l.other_results,l.Comment,l.sub_test_id,l.Sample_Type,u.FullName,(select FullName from users u where u.UID=l.Performed_By_c) as carried_otBy  FROM lab_tests_results l INNER JOIN lab_tests_requests q on q.Request_Id=l.Request_Id INNER JOIN expected_results e on e.result_id=l.expected_result_id INNER JOIN sub_tests s on s.sub_test_id=l.sub_test_id INNER JOIN specific_tests sp on sp.specific_test_id=s.specific_test_id INNER JOIN test_names t on t.test_id=sp.test_id INNER JOIN users u on u.UID=l.Performed_By WHERE q.Track_Id=?");
//            stmt.setString(1, task_id);
//
//            ResultSet rs = stmt.executeQuery();
//            List results = new ArrayList();
//
//            while (rs.next()) {
//                String general_tests = "";
//                String general_results = "";
//                test_result = new HistoryTestResults();
//                String specific = (rs.getString("specific_test").equalsIgnoreCase("None")) ? "" : " : " + rs.getString("specific_test");
//                String subtest = (rs.getString("sub_test").equalsIgnoreCase("None")) ? "" : " / " + rs.getString("sub_test");
//                String testresults = (rs.getString("result").contains("write")) ? "" : rs.getString("result");
//                String comment = (rs.getString("Comment") == null || rs.getString("Comment") == "") ? "" : rs.getString("Comment");
//                String comment_blackets = (rs.getString("Comment") == null || rs.getString("Comment") == "") ? "" : " ( " + rs.getString("Comment") + " )";
//
//                general_tests = general_tests + "" + rs.getString("test_name") + "" + specific + "" + subtest;
//                general_results = testresults + "  " + rs.getString("other_results");
//
//                test_result.setRequest_id(Integer.valueOf(rs.getInt("Request_Id")));
//                test_result.setTest_name(rs.getString("test_name"));
//                test_result.setSpecific_test(rs.getString("specific_test"));
//                test_result.setSub_test(rs.getString("sub_test"));
//                test_result.setExpected_result(rs.getString("result"));
//                test_result.setOther_result(rs.getString("other_results"));
//                test_result.setTest_carriedout_by(rs.getString("carried_otBy"));
//                test_result.setGeneral_tests(general_tests);
//                test_result.setGeneral_results(general_results);
//                test_result.setSubtest_id(Integer.valueOf(rs.getInt("sub_test_id")));
//                test_result.setComment(comment);
//                test_result.setSample_type(rs.getString("Sample_Type"));
//                test_result.setTest_reviewed_by(rs.getString("FullName"));
//                test_result.setComment_blackets(comment_blackets);
//
//                results.add(test_result);
//            }
//            con.close();
//            rs.close();
//            return results;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Tests_History", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//    }
//
//    public static List<HistoryClinicalNotes> Consultant_Get_Clinical_Notes_History(String task_id)
//            throws SQLException {
//
//        try {
//            Connection con;
//            con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//
//            PreparedStatement stmt = con.prepareStatement("SELECT u.FullName as 'Made By',cd.Clinical_Notes as 'Note',Date(cd.Record_Date) as 'Made On' "
//                    + "FROM consultant_dentist cd "
//                    + "	INNER JOIN users u on cd.From_Staff=u.UID "
//                    + "WHERE cd.Track_Id=?");
//            stmt.setString(1, task_id);
//
//            ResultSet rs = stmt.executeQuery();
//            List<HistoryClinicalNotes> notes_history_list = new ArrayList();
//            HistoryClinicalNotes notes_history = new HistoryClinicalNotes();
//
//            while (rs.next()) {
//                notes_history = new HistoryClinicalNotes();
//
//                notes_history.setNote(rs.getString("Note"));
//                notes_history.setStaff_id(rs.getString("Made By"));
//                notes_history.setRecord_date(rs.getString("Made On"));
//
//                notes_history_list.add(notes_history);
//
//            }
//
//            con.close();
//            rs.close();
//
//            return notes_history_list;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Clinical_Notes_History", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="Clinical Notes">          
//    public static List<Notes> Consultant_Get_Clinical_Notes(String track_id)
//            throws SQLException {
//        try {
//            Connection con;
//            con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//
//            PreparedStatement stmt = con.prepareStatement("SELECT c.Note_Id,c.Track_Id,c.Clinical_Notes,u.FullName,c.Record_Date FROM consultant_dentist c INNER JOIN users u on c.From_Staff=u.UID WHERE c.Track_Id=?");
//            stmt.setString(1, track_id);
//
//            ResultSet rs = stmt.executeQuery();
//
//            List clinical_notes = new ArrayList();
//
//            while (rs.next()) {
//                Notes note = new Notes();
//
//                note.setNote_id(rs.getInt("Note_Id"));
//                note.setNote(rs.getString("Clinical_Notes"));
//                note.setNote_by(rs.getString("FullName"));
//                note.setNote_date_time(rs.getString("Record_Date"));
//
//                clinical_notes.add(note);
//            }
//
//            con.close();
//            rs.close();
//            return clinical_notes;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Clinical_Notes", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//
//    }

    public static boolean Consultant_Delete_Clinical_Note(Integer Note_Id)
            throws SQLException {

        try {
            Connection con;

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("delete FROM consultant_dentist WHERE Note_Id=?");
            ps.setInt(1, Note_Id);

            ps.executeUpdate();
            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Delete_Clinical_Note", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

//    public static List<Notes> Consultant_Get_Notes(String track_id)
//            throws SQLException, ClassNotFoundException {
//
//        try {
//            Connection con;
//            con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//
//            PreparedStatement ps = con.prepareStatement("SELECT c.Track_Id,c.Clinical_Notes,u.FullName FROM consultant_dentist c INNER JOIN users u on c.From_Staff=u.UID WHERE c.Track_Id=? order by c.Record_Date desc");
//            ps.setString(1, track_id);
//
//            ResultSet rs = ps.executeQuery();
//
//            List clinical_notes = new ArrayList();
//
//            while (rs.next()) {
//                Notes note = new Notes();
//                note.setStaff_id(rs.getString("FullName"));
//                note.setNote(rs.getString("Clinical_Notes"));
//
//                clinical_notes.add(note);
//            }
//
//            con.close();
//            rs.close();
//            return clinical_notes;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Notes", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//
//    }
//
//    public static Notes Consultant_Get_Note(Integer note_id)
//            throws SQLException, ClassNotFoundException {
//
//        try {
//            Connection con;
//            con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//
//            PreparedStatement ps = con.prepareStatement("SELECT c.Clinical_Notes FROM consultant_dentist c WHERE c.Note_Id=?");
//            ps.setInt(1, note_id);
//
//            ResultSet rs = ps.executeQuery();
//
//            Notes note = new Notes();
//
//            if (rs.next()) {
//                note.setNote(rs.getString("Clinical_Notes"));
//            } else {
//                return null;
//            }
//
//            con.close();
//            rs.close();
//
//            return note;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Note", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//
//    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Screening">      
//    public static AlcoholAudit Consultant_Get_Alcohol_Audit_Qtns()
//            throws SQLException {
//
//        try {
//            Connection con;
//            con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//
//            PreparedStatement stmt = con.prepareStatement("SELECT * FROM alcohol_audit_questions q order by q.Question_Id ASC");
//
//            ResultSet rs = stmt.executeQuery();
//
//            AlcoholAudit audit_qtns = new AlcoholAudit();
//
//            while (rs.next()) {
//                switch (rs.getInt("Question_Id")) {
//                    case 1:
//                        audit_qtns.setQtn_1(rs.getString("Question"));
//                    case 2:
//                        audit_qtns.setQtn_2(rs.getString("Question"));
//                    case 3:
//                        audit_qtns.setQtn_3(rs.getString("Question"));
//                    case 4:
//                        audit_qtns.setQtn_4(rs.getString("Question"));
//                    case 5:
//                        audit_qtns.setQtn_5(rs.getString("Question"));
//                    case 6:
//                        audit_qtns.setQtn_6(rs.getString("Question"));
//                    case 7:
//                        audit_qtns.setQtn_7(rs.getString("Question"));
//                    case 8:
//                        audit_qtns.setQtn_8(rs.getString("Question"));
//                    case 9:
//                        audit_qtns.setQtn_9(rs.getString("Question"));
//                    case 10:
//                        audit_qtns.setQtn_10(rs.getString("Question"));
//
//                }
//
//            }
//
//            con.close();
//            rs.close();
//
//            return audit_qtns;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Alcohol_Audit_Qtns", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//    }

    public static boolean Consultant_Add_Alcohol_Audit_Summary(String track_id, Integer score, String comment)
            throws SQLException, ClassNotFoundException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con = Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO alcohol_audit_summary (Track_Id,Total_Score,Comment,Record_Date) VALUES(?,?,?,?)");

            ps.setString(1, track_id);
            ps.setInt(2, score);
            ps.setString(3, comment);
            ps.setString(4, dateFormat.format(date));

            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Add_Alcohol_Audit_Summary", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static boolean Consultant_Add_Alcohol_Audit_Summary(String track_id, Integer score, String comment, String screened_name)
            throws SQLException, ClassNotFoundException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con = Apache_Connectionpool.getInstance().getConnection();

//            PreparedStatement ps = con.prepareStatement("INSERT INTO alcohol_audit_summary (Track_Id,Total_Score,Comment,Record_Date) VALUES(?,?,?,?)");
            PreparedStatement ps = con.prepareStatement("INSERT INTO alcohol_audit_summary (Track_Id,Total_Score,Comment,Record_Date,Screened_Name) VALUES(?,?,?,?,?)");

            ps.setString(1, track_id);
            ps.setInt(2, score);
            ps.setString(3, comment);
            ps.setString(4, dateFormat.format(date));
            ps.setString(5, screened_name);

            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Add_Alcohol_Audit_Summary", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static boolean Consultant_Add_Alcohol_Audit_Details(String track_id, Integer qtn_id, Integer score)
            throws SQLException, ClassNotFoundException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con = Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO alcohol_audit VALUES(?,?,?,?)");

            ps.setString(1, track_id);
            ps.setInt(2, qtn_id);
            ps.setInt(3, score);
            ps.setString(4, dateFormat.format(date));

            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Add_Alcohol_Audit_Details", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static boolean Consultant_Alcohol_Audit_Exists(String track_id) throws SQLException, ClassNotFoundException {

        try {
            Connection con;

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM alcohol_audit_summary WHERE Track_Id=?");

            ps.setString(1, track_id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                con.close();
                
            rs.close();
                return true;
            } else {
                con.close();
                
            rs.close();
                return false;
            }

        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Alcohol_Audit_Exists", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

//    public static FamilyPlanning Consultant_Get_Family_Planning_Qtns() throws SQLException {
//
//        try {
//            Connection con;
//            con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//
//            PreparedStatement stmt = con.prepareStatement("SELECT * FROM family_planning_questions q order by q.Question_Id ASC");
//
//            ResultSet rs = stmt.executeQuery();
//
//            FamilyPlanning fp_qtns = new FamilyPlanning();
//
//            while (rs.next()) {
//                switch (rs.getInt("Question_Id")) {
//                    case 1:
//                        fp_qtns.setQtn_1(rs.getString("Question"));
//                    case 2:
//                        fp_qtns.setQtn_2(rs.getString("Question"));
//                    case 3:
//                        fp_qtns.setQtn_3(rs.getString("Question"));
//                    case 4:
//                        fp_qtns.setQtn_4(rs.getString("Question"));
//
//                }
//
//            }
//            
//            con.close();
//            rs.close();
//
//            return fp_qtns;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Family_Planning_Qtns", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//    }

    public static boolean Consultant_Family_Planning_Exists(String track_id) throws SQLException, ClassNotFoundException {

        try {
            Connection con;
            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM family_planning_results WHERE Track_Id=?");

            ps.setString(1, track_id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                con.close();
                rs.close();
                return true;
                           } else {
                con.close();
                 rs.close();
                return false;
            }

        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Family_Planning_Exists", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static boolean Consultant_Add_Family_Planning(String track_id, Integer qtn_id, String answer)
            throws SQLException, ClassNotFoundException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con = Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO family_planning_results VALUES(?,?,?,?)");

            ps.setString(1, track_id);
            ps.setInt(2, qtn_id);
            ps.setString(3, answer);
            ps.setString(4, dateFormat.format(date));

            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Add_Family_Planning", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static boolean Consultant_Add_Patient_Screening(String track_id, String decision)
            throws SQLException, ClassNotFoundException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con = Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO patient_screening VALUES(?,?,?)");

            ps.setString(1, track_id);
            ps.setString(2, decision);
            ps.setString(3, dateFormat.format(date));

            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Add_Patient_Screening", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static boolean Consultant_Update_Patient_Screening(String track_id, String decision)
            throws SQLException, ClassNotFoundException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con = Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("update patient_screening set Screen_Decision=?,Screen_Time=? WHERE Track_Id=?");

            ps.setString(1, decision);
            ps.setString(2, dateFormat.format(date));
            ps.setString(3, track_id);

            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Update_Patient_Screening", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static boolean Consultant_Patient_Screening_Exists(String track_id)
            throws SQLException, ClassNotFoundException {

        try {
            Connection con;

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM  patient_screening WHERE Track_Id=?");

            ps.setString(1, track_id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                con.close();
                return true;
            }
            con.close();
            rs.close();
            return false;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Patient_Screening_Exists", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static boolean Consultant_Add_Family_Planning_Action(String track_id, String action_taken)
            throws SQLException, ClassNotFoundException {

        try {
            Connection con;

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("INSERT INTO family_planning_action_taken (Track_Id,Action_Taken) VALUES(?,?)");

            ps.setString(1, track_id);
            ps.setString(2, action_taken);

            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Add_Family_Planning_Action", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static boolean Consultant_Add_Family_Planning_Action(String track_id, String action_taken, String criterion, String notes, String screened_name)
            throws SQLException, ClassNotFoundException {

        try {
            Connection con;

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

//            PreparedStatement ps = con.prepareStatement("INSERT INTO family_planning_action_taken (Track_Id,Action_Taken,Exclusion_Criterion,Notes) VALUES(?,?,?,?)");
            PreparedStatement ps = con.prepareStatement("INSERT INTO family_planning_action_taken (Track_Id,Action_Taken,Exclusion_Criterion,Notes,Screened_Name) VALUES(?,?,?,?,?)");

            ps.setString(1, track_id);
            ps.setString(2, action_taken);
            ps.setString(3, criterion);
            ps.setString(4, notes);
            ps.setString(5, screened_name);

            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Add_Family_Planning_Action", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    /*
     * DAO Method to Save Syphilis and Viral Test Results
     */
    public static boolean Consultant_Save_Viral_Syphilis_Test_Results(String track_id, String staff_id, String viral_results, String syphilis_results)
            throws SQLException, ClassNotFoundException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con = Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO consultant_viral_syphilis(Task_Id,Viral_Result,Syphilis_Result,Staff_Id,Record_Date) VALUES(?,?,?,?,?)");

            ps.setString(1, track_id);
            ps.setString(2, viral_results);
            ps.setString(3, syphilis_results);
            ps.setString(4, staff_id);
            ps.setString(5, dateFormat.format(date));

            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Save_Viral_Syphilis_Test_Results", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static boolean Consultant_Viral_Screening_Exists(String track_id) throws SQLException, ClassNotFoundException {

        try {
            Connection con;

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM consultant_viral_syphilis WHERE Task_Id=?");

            ps.setString(1, track_id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                con.close();
                rs.close();
                return true;
            } else {
                con.close();
                rs.close();
                return false;
            }

        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Viral_Screening_Exists", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    /*
     * DAO Method to Check Whetha Patient Has Been Screened
     * 
     */
    public static boolean Consultant_Get_Patient_Screened(String task_id) throws SQLException {

        try {
            Connection con;

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM patient_screening WHERE Track_Id=?");
            stmt.setString(1, task_id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                con.close();
                
                rs.close();
                return true;
            }
            con.close();
            rs.close();
            return false;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Patient_Screened", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    /*
     * DAO Method to Save Alcohol Referral CLinic
     */
    public static boolean Consultant_Save_Alcohol_Referral(String Track_Id, String Status)
            throws SQLException, ClassNotFoundException {

        try {

            System.out.println("Values: " + Track_Id);

            Connection con;

            con = Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO alcohol_clinic_referral(Track_Id,Status) VALUES(?,?)");

            ps.setString(1, Track_Id);
            ps.setString(2, Status);

            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Save_Alcohol_Referral", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Laboratory">    
    public static boolean Test_Request_Exists(String track_id, String test_name)
            throws SQLException {

        try {
            Connection con;

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM lab_test_results WHERE Track_Id=? and Test_Name=? and Test_Status=?");
            stmt.setString(1, track_id);
            stmt.setString(2, test_name);
            stmt.setString(3, "Pending");

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                con.close();
                rs.close();
                return true;
            }

            con.close();
            rs.close();
            return false;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Test_Request_Exists", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static boolean Test_Request_Add(String track_id, String patient_id, String test_name, String requested_by)
            throws SQLException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con = Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO lab_test_results(Track_Id,Patient_Id,Test_Name,Requested_By,Request_Time,Test_Status) VALUES(?,?,?,?,?,?)");

            ps.setString(1, track_id);
            ps.setString(2, patient_id);
            ps.setString(3, test_name);
            ps.setString(4, requested_by);
            ps.setString(5, dateFormat.format(date));
            ps.setString(6, "Pending");
            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Test_Request_Add", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static boolean Test_Request_Update(String track_id, String to_be_carried_out_by)
            throws SQLException {

        try {
            Connection con;

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("update lab_test_results set Carried_Out_By=? WHERE Track_Id=? and Test_Status='Pending' and Carried_Out_By IS NULL");

            ps.setString(1, to_be_carried_out_by);
            ps.setString(2, track_id);
            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Test_Request_Update", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

//    public static List<LabTestResults> Consultant_Tests_Results(String track_id)
//            throws SQLException {
//
//        try {
//            Connection con;
//            LabTestResults result;
//
//            con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//
//            PreparedStatement stmt = con.prepareStatement("SELECT tn.test_name,e.result,l.other_results as OtherResults,u.FullName as Performed_By FROM lab_tests_results l INNER JOIN lab_tests_requests r on l.Request_Id=r.Request_Id INNER JOIN specific_tests s on l.specific_test_id=s.specific_test_id INNER JOIN sub_tests st on l.sub_test_id=st.sub_test_id INNER JOIN test_names tn on r.test_name_id=tn.test_id INNER JOIN test_categories tc on tn.category_id=tc.category_id INNER JOIN expected_results e on e.result_id=l.expected_result_id INNER JOIN users u on l.Performed_By=u.UID WHERE r.Track_Id=?");
//            stmt.setString(1, track_id);
//
//            ResultSet rs = stmt.executeQuery();
//            List result_list = new ArrayList();
//
//            while (rs.next()) {
//                result = new LabTestResults();
//
//                result.setTest_name(rs.getString("test_name"));
//                result.setTest_normal_values(rs.getString("result"));
//                result.setTest_results(rs.getString("OtherResults"));
//                result.setTest_carriedout_by(rs.getString("Performed_By"));
//
//                result_list.add(result);
//            }
//
//            con.close();
//            rs.close();
//            return result_list;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Tests_Results", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//    }

    public static String Concatenated_Tests_Results(String track_id)
            throws SQLException {

        try {
            Connection con;
            String result;

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

//            PreparedStatement stmt = con.prepareStatement("SELECT group_concat(Distinct(test_name) separator ', ') As investigations From lab_tests_requests,test_names,lab_tests_results Where lab_tests_requests.Track_Id=? and lab_tests_requests.test_name_id=test_names.test_id and lab_tests_requests.Request_Id=lab_tests_results.Request_Id");
//            PreparedStatement stmt = con.prepareStatement("SELECT group_concat(Case When test_name='Blood Sugar' Then concat(sub_test,': ',other_results,REPLACE(result,concat('write what you',char(39),'ve seen(other Results)'),'')) When test_name='RFT' Then concat(test_name,' ',specific_test,': ',other_results,' ',REPLACE(REPLACE(result,concat(', write what you',char(39),'ve seen(other Results)'),''),'..','')) When test_name='LFT' Then concat(test_name,' ',specific_test,' ',REPLACE(sub_test,'None',''),': ',other_results,' ',REPLACE(REPLACE(result,concat('write what you',char(39),'ve seen(other Results)'),''),'..','')) When test_name='HCG' Then concat(test_name,': ',result,' ',other_results) When test_name='Urinalysis' and specific_test='Urine Chemistry' Then concat(specific_test,' - ',sub_test,': ',result,' ',other_results) Else concat(test_name,'/',specific_test,'/',sub_test,': ',result,' ',other_results) End separator ';  ') As investigations From lab_tests_requests,test_names,lab_tests_results,specific_tests,sub_tests,expected_results Where lab_tests_requests.Track_Id=? and lab_tests_requests.test_name_id=test_names.test_id and lab_tests_requests.Request_Id=lab_tests_results.Request_Id  and lab_tests_results.specific_test_id=specific_tests.specific_test_id and lab_tests_results.sub_test_id=sub_tests.sub_test_id and lab_tests_results.expected_result_id=expected_results.result_id");
//            PreparedStatement stmt = con.prepareStatement("SELECT group_concat(Case When test_name='Blood Sugar' Then concat(sub_test,': ',other_results,IfNull((Select Min(Units) From tests_normal_ranges tnr Where tnr.sub_test_id=sub_tests.sub_test_id),REPLACE(result,concat('write what you',char(39),'ve seen(other Results)'),''))) When test_name='RFT' Then concat(test_name,' ',specific_test,': ',other_results,IfNull(Case When other_results RegExp '[0-9]$' Then (Select Min(Units) From tests_normal_ranges tnr Where tnr.sub_test_id=sub_tests.sub_test_id) Else '' End,Concat(' ',REPLACE(REPLACE(result,concat(', write what you',char(39),'ve seen(other Results)'),''),'..','')))) When test_name='LFT' Then concat(test_name,' ',specific_test,' ',REPLACE(sub_test,'None',''),': ',other_results,IfNull(Case When other_results RegExp '[0-9]$' Then (Select Min(Units) From tests_normal_ranges tnr Where tnr.sub_test_id=sub_tests.sub_test_id) Else '' End,Concat(' ',REPLACE(REPLACE(result,concat('write what you',char(39),'ve seen(other Results)'),''),'..','')))) When test_name='HCG' Then concat(test_name,': ',result,' ',other_results,IfNull((Select Min(Units) From tests_normal_ranges tnr Where tnr.sub_test_id=sub_tests.sub_test_id),'')) When test_name='Urinalysis' and specific_test='Urine Chemistry' Then concat(specific_test,' - ',sub_test,': ',result,' ',other_results,IfNull((Select Min(Units) From tests_normal_ranges tnr Where tnr.sub_test_id=sub_tests.sub_test_id),'')) When test_name='HBA1C' Then concat (test_name,': ',other_results,IfNull(Case When other_results RegExp '[0-9]$' Then (Select Min(Units) From tests_normal_ranges tnr Where tnr.sub_test_id=sub_tests.sub_test_id) Else '' End,'')) Else concat(test_name,'/',specific_test,'/',sub_test,': ',Case When (Select Min(Units) From tests_normal_ranges tnr Where tnr.sub_test_id=sub_tests.sub_test_id) Is Null Then concat(REPLACE(result,concat('write what you',char(39),'ve seen(other Results)'),''),' ',other_results) Else concat(other_results,Case When other_results RegExp '[0-9]$' Then (Select Min(Units) From tests_normal_ranges tnr Where tnr.sub_test_id=sub_tests.sub_test_id) Else '' End) End) End separator ';  ') As investigations From lab_tests_requests,test_names,lab_tests_results,specific_tests,sub_tests,expected_results Where lab_tests_requests.Track_Id=? and lab_tests_requests.test_name_id=test_names.test_id and lab_tests_requests.Request_Id=lab_tests_results.Request_Id  and lab_tests_results.specific_test_id=specific_tests.specific_test_id and lab_tests_results.sub_test_id=sub_tests.sub_test_id and lab_tests_results.expected_result_id=expected_results.result_id");
            PreparedStatement stmt = con.prepareStatement("SELECT group_concat(Case When test_name='Blood Sugar' Then concat(sub_test,': ',other_results,IfNull((Select Min(Units) From tests_normal_ranges tnr Where tnr.sub_test_id=sub_tests.sub_test_id),REPLACE(result,concat('write what you',char(39),'ve seen(other Results)'),''))) When test_name Like 'Blood Sugar:%' Then concat(test_name,': ',other_results,IfNull((Select Min(Units) From tests_normal_ranges tnr Where tnr.sub_test_id=sub_tests.sub_test_id),REPLACE(result,concat('write what you',char(39),'ve seen(other Results)'),''))) When test_name In ('RFT','LFT','Electrolytes','Extended Electrolytes') Then concat(test_name,' ',specific_test,REPLACE(concat(' ',sub_test),' None',''),': ',other_results,IfNull(Case When other_results RegExp '[0-9]$' Then (Select Min(Units) From tests_normal_ranges tnr Where tnr.sub_test_id=sub_tests.sub_test_id) Else '' End,Concat(' ',REPLACE(REPLACE(result,concat('write what you',char(39),'ve seen(other Results)'),''),'..','')))) When test_name='HCG' Then concat(test_name,': ',result,' ',other_results,IfNull((Select Min(Units) From tests_normal_ranges tnr Where tnr.sub_test_id=sub_tests.sub_test_id),'')) When test_name='Urinalysis' and specific_test='Urine Chemistry' Then concat(specific_test,' - ',sub_test,': ',result,' ',other_results,IfNull((Select Min(Units) From tests_normal_ranges tnr Where tnr.sub_test_id=sub_tests.sub_test_id),'')) When test_name='HBA1C' Then concat (specific_test,': ',other_results,IfNull(Case When other_results RegExp '[0-9]$' Then (Select Min(Units) From tests_normal_ranges tnr Where tnr.sub_test_id=sub_tests.sub_test_id) Else '' End,'')) Else concat(test_name,'/',specific_test,'/',sub_test,': ',Case When (Select Min(Units) From tests_normal_ranges tnr Where tnr.sub_test_id=sub_tests.sub_test_id) Is Null Then concat(REPLACE(result,concat('write what you',char(39),'ve seen(other Results)'),''),' ',other_results) Else concat(other_results,Case When other_results RegExp '[0-9]$' Then (Select Min(Units) From tests_normal_ranges tnr Where tnr.sub_test_id=sub_tests.sub_test_id) Else '' End) End) End separator ';  ') As investigations From lab_tests_requests,test_names,lab_tests_results,specific_tests,sub_tests,expected_results Where lab_tests_requests.Track_Id=? and lab_tests_requests.test_name_id=test_names.test_id and lab_tests_requests.Request_Id=lab_tests_results.Request_Id  and lab_tests_results.specific_test_id=specific_tests.specific_test_id and lab_tests_results.sub_test_id=sub_tests.sub_test_id and lab_tests_results.expected_result_id=expected_results.result_id");

            stmt.setString(1, track_id);

            ResultSet rs = stmt.executeQuery();
            result = new String();

            while (rs.next()) {
                result = rs.getString("investigations");
            }

            con.close();
            rs.close();
            return result;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Concatenated_Tests_Results", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static String Consultant_Get_Gen_Staff(String staff_id)
            throws SQLException {
        try {
            Connection con;

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT u.FullName FROM bwindihospital.users u where u.UID=?");
            stmt.setString(1, staff_id);

            ResultSet rs = stmt.executeQuery();

            String ret_value = null;

            if (rs.next()) {
                ret_value = rs.getString("FullName");
            }

            con.close();
            rs.close();
            return ret_value;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Gen_Staff", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static List<Users> Consultant_Foward_To_Lab()
            throws SQLException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Users users;

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT DISTINCT u.UID,u.FullName FROM login_history l INNER JOIN users u on l.Staff_Id=u.UID INNER JOIN roles r on u.RID=r.RID WHERE r.RID=5 and l.Login_Date=?");

            stmt.setString(1, dateFormat.format(date));

            ResultSet rs = stmt.executeQuery();
            List userList = new ArrayList();
            while (rs.next()) {
                users = new Users(rs.getString("UID"), rs.getString("FullName"));
                userList.add(users);
            }

            con.close();
            rs.close();
            return userList;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Foward_To_Lab", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static boolean Consultant_Lab_Pending(String trackID, String uid, String forward_FROM, String forward_to)
            throws SQLException, ClassNotFoundException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("INSERT INTO lab_pending(Track_Id,Staff_Id,Staff_From,Forward_To,Record_Date) VALUES(?,?,?,?,?)");

            ps.setString(1, trackID);
            ps.setString(2, uid);
            ps.setString(3, forward_FROM);
            ps.setString(4, forward_to);
            ps.setString(5, dateFormat.format(date));

            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Lab_Pending", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Diagnosis & Treatment">        
//    public static List<Diagnosis> Consultant_Get_Diagnosis()
//            throws SQLException {
//        try {
//            Connection con;
//            con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//
//            PreparedStatement stmt = con.prepareStatement("SELECT * FROM clinical_diagnosis Where status='TRUE' order by Diagnosis ASC");
//            ResultSet rs = stmt.executeQuery();
//
//            Diagnosis diagnosis;
//            List diagnosis_list = new ArrayList();
//            while (rs.next()) {
//                diagnosis = new Diagnosis();
//                diagnosis.setRecord_id(rs.getInt("Record_Id"));
//                diagnosis.setDiagnosis(rs.getString("Diagnosis"));
//                diagnosis_list.add(diagnosis);
//            }
//
//            con.close();
//            return diagnosis_list;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Diagnosis", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//    }

//    public static List<Diagnosis> Consultant_Get_ICD_Diagnosis()
//            throws SQLException {
//        try {
//            Connection con;
//            con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//
//            PreparedStatement stmt = con.prepareStatement("SELECT *,substr(Diagnosis,1,locate(' ',Diagnosis)-1) As Code FROM clinical_diagnosis Where Level>=3 and Level<=3 Order By Record_Id ASC");
//            ResultSet rs = stmt.executeQuery();
//
//            Diagnosis diagnosis;
//            List diagnosis_list = new ArrayList();
//            while (rs.next()) {
//                diagnosis = new Diagnosis();
//                diagnosis.setRecord_id(rs.getInt("Record_Id"));
//                diagnosis.setCode(rs.getString("Code"));
//                diagnosis.setDiagnosis(rs.getString("Diagnosis"));
//                diagnosis_list.add(diagnosis);
//            }
//
//            con.close();
//            return diagnosis_list;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_ICD_Diagnosis", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//    }
//
//    public static List<Diagnosis> Consultant_Get_ICD_Diagnosis_Level1()
//            throws SQLException {
//        try {
//            Connection con;
//            con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//
////            PreparedStatement stmt = con.prepareStatement("SELECT * FROM icd10codes Where level=1 order by recordid ASC");
//            PreparedStatement stmt = con.prepareStatement("SELECT *,substr(Diagnosis,1,locate(' ',Diagnosis)-1) As Code FROM clinical_diagnosis Where Level=1 Order By Record_Id ASC");
//            ResultSet rs = stmt.executeQuery();
//
//            Diagnosis diagnosis;
//            List diagnosis_list = new ArrayList();
//            while (rs.next()) {
//                diagnosis = new Diagnosis();
//                diagnosis.setRecord_id(rs.getInt("record_id"));
//                diagnosis.setCode(rs.getString("code"));
//                diagnosis.setDiagnosis(rs.getString("diagnosis"));
//                diagnosis_list.add(diagnosis);
//            }
//
//            con.close();
//            return diagnosis_list;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_ICD_Diagnosis_Level1", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//    }
//
//    public static List<Diagnosis> Consultant_Get_ICD_Diagnosis_Level2(String Level1)
//            throws SQLException {
//        try {
//            Connection con;
//            con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//
//            PreparedStatement stmt = con.prepareStatement("SELECT *,substr(Diagnosis,1,locate(' ',Diagnosis)-1) As Code FROM clinical_diagnosis Where level=2 and substr(diagnosis,1,3)>=substr(?,1,3) and substr(diagnosis,5,7)<=substr(?,5,7) Order By Record_Id ASC");
//            stmt.setString(1, Level1);
//            stmt.setString(2, Level1);
//            ResultSet rs = stmt.executeQuery();
//
//            Diagnosis diagnosis;
//            List diagnosis_list = new ArrayList();
//            while (rs.next()) {
//                diagnosis = new Diagnosis();
//                diagnosis.setRecord_id(rs.getInt("record_id"));
//                diagnosis.setCode(rs.getString("code"));
//                diagnosis.setDiagnosis(rs.getString("diagnosis"));
//                diagnosis_list.add(diagnosis);
//            }
//
//            con.close();
//            return diagnosis_list;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_ICD_Diagnosis_Level2", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//    }
//
//    public static List<Diagnosis> Consultant_Get_ICD_Diagnosis_Level3(String Level2)
//            throws SQLException {
//        try {
//            Connection con;
//            con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//
//            PreparedStatement stmt = con.prepareStatement("SELECT *,concat(lpad(lastlevel,1,0),Diagnosis) As Code FROM clinical_diagnosis Where Level=3 and substr(diagnosis,1,3)>=substr(?,1,3) and substr(diagnosis,1,3)<=substr(?,5,7) Order By Record_Id ASC");
//            stmt.setString(1, Level2);
//            stmt.setString(2, Level2);
//            ResultSet rs = stmt.executeQuery();
//
//            Diagnosis diagnosis;
//            List diagnosis_list = new ArrayList();
//            while (rs.next()) {
//                diagnosis = new Diagnosis();
//                diagnosis.setRecord_id(rs.getInt("record_id"));
//                diagnosis.setCode(rs.getString("code"));
//                diagnosis.setDiagnosis(rs.getString("diagnosis"));
//                diagnosis.setLastlevel(rs.getInt("lastlevel"));
//                diagnosis_list.add(diagnosis);
//            }
//
//            con.close();
//            return diagnosis_list;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_ICD_Diagnosis_Level3", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//    }
//
//    public static List<Diagnosis> Consultant_Get_ICD_Diagnosis_Level4(String Level3)
//            throws SQLException {
//        try {
//            Connection con;
//            con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//
//            PreparedStatement stmt = con.prepareStatement("SELECT *,concat(lpad(lastlevel,1,0),Diagnosis) As Code FROM clinical_diagnosis Where Level=4 and substr(diagnosis,1,3)=substr(?,2,4) Order By Record_Id ASC");
//            stmt.setString(1, Level3);
//            ResultSet rs = stmt.executeQuery();
//
//            Diagnosis diagnosis;
//            List diagnosis_list = new ArrayList();
//            while (rs.next()) {
//                diagnosis = new Diagnosis();
//                diagnosis.setRecord_id(rs.getInt("record_id"));
//                diagnosis.setCode(rs.getString("code"));
//                diagnosis.setDiagnosis(rs.getString("diagnosis"));
//                diagnosis_list.add(diagnosis);
//            }
//
//            con.close();
//            return diagnosis_list;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_ICD_Diagnosis_Level4", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//    }

    public static Integer Consultant_Get_Diagnosis_RecordId(String Diagnosis)
            throws SQLException {
        try {
            Connection con;
            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT Record_Id From clinical_diagnosis Where substr(Diagnosis,1,254)=?");
            stmt.setString(1, Diagnosis);
            ResultSet rs = stmt.executeQuery();

            Integer Record_Id = 0;
            while (rs.next()) {
                Record_Id = rs.getInt("record_id");
            }

            con.close();
            return Record_Id;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Diagnosis_RecordId", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

//    public static List<Diagnosis> Consultant_Get_Search_ICD_List(String Search_String)
//            throws SQLException {
//        try {
//            Connection con;
//            con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//
//            PreparedStatement stmt = con.prepareStatement("SELECT *,substr(Diagnosis,1,locate(' ',Diagnosis)-1) As Code FROM clinical_diagnosis Where Level>=3 and Level<=7 and Diagnosis Like concat('%',?,'%') Order By Record_Id ASC");
//            stmt.setString(1, Search_String);
//            ResultSet rs = stmt.executeQuery();
//
//            Diagnosis diagnosis;
//            List diagnosis_list = new ArrayList();
//            while (rs.next()) {
//                diagnosis = new Diagnosis();
//                diagnosis.setRecord_id(rs.getInt("Record_Id"));
//                diagnosis.setCode(rs.getString("Code"));
//                diagnosis.setDiagnosis(rs.getString("Diagnosis"));
//                diagnosis_list.add(diagnosis);
//            }
//
//            con.close();
//            return diagnosis_list;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Search_ICD_List", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//    }
//
//    public static List<Diagnosis> Consultant_Get_HMIS_Diagnosis()
//            throws SQLException {
//        try {
//            Connection con;
//            con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//
////            PreparedStatement stmt = con.prepareStatement("SELECT * FROM clinical_diagnosis_hmis_opd order by Heading ASC");
//            PreparedStatement stmt = con.prepareStatement("SELECT * FROM clinical_diagnosis_hmis_opd Where Status='TRUE' order by Section,Heading ASC");
//            ResultSet rs = stmt.executeQuery();
//
//            Diagnosis diagnosis;
//            List diagnosis_list = new ArrayList();
//            while (rs.next()) {
//                diagnosis = new Diagnosis();
//                diagnosis.setRecord_id(rs.getInt("Record_Id"));
//                diagnosis.setDiagnosis(rs.getString("Heading"));
//                diagnosis_list.add(diagnosis);
//            }
//
//            con.close();
//            return diagnosis_list;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_HMIS_Diagnosis", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//    }

    public static String Consultant_Get_Specific_Diagnosis(String diagnosis_id)
            throws SQLException {

        try {
            Connection con;

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT Diagnosis FROM clinical_diagnosis WHERE Record_Id=?");
            stmt.setInt(1, Integer.parseInt(diagnosis_id));

            ResultSet rs = stmt.executeQuery();

            String ret_value = null;

            if (rs.next()) {
                ret_value = rs.getString("Diagnosis");
            }

            con.close();
            return ret_value;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Specific_Diagnosis", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static boolean Consultant_Add_Diagnosis(String diagnosis_name) throws SQLException {

        try {
            Connection con;
            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("INSERT INTO clinical_diagnosis(Diagnosis) VALUES(?)");
            stmt.setString(1, diagnosis_name);

            stmt.executeUpdate();
            con.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Add_Diagnosis", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static boolean Consultant_Update_Diagnosis(Integer diagnosis_id, String diagnosis_name) throws SQLException {

        try {
            Connection con;
            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("update clinical_diagnosis set Diagnosis=? WHERE Record_Id=?");
            stmt.setString(1, diagnosis_name);
            stmt.setInt(2, diagnosis_id.intValue());

            stmt.executeUpdate();
            con.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Update_Diagnosis", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static List<Drugstore> Consultant_Get_Drugs(String DrugSet)
            //    public static List<Drugstore> Consultant_Get_Drugs()
            throws SQLException {

        try {
            Connection con;
            Drugstore drug;
            String query;

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            if (DrugSet.equals("All")) {
                query = "SELECT drug_id,item_name FROM drug_list WHERE status='TRUE' order by item_name ASC";
            } else {
                query = "SELECT drug_id,item_name FROM drug_list WHERE status='TRUE' and prescribed_item='TRUE' order by item_name ASC";
            }
            PreparedStatement stmt = con.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            List drugList = new ArrayList();

            while (rs.next()) {
                drug = new Drugstore(Integer.valueOf(rs.getInt("drug_id")), rs.getString("item_name"));
                drugList.add(drug);
            }

            con.close();
            return drugList;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Drugs", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static String Consultant_Get_Specific_Drug(Integer drug_id)
            throws SQLException {

        try {
            Connection con;
            String ret_val;

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT item_name FROM drug_list WHERE drug_id=?");

            ResultSet rs = stmt.executeQuery();

            List drugList = new ArrayList();
            ret_val = "";
            if (rs.next()) {
                ret_val = rs.getString("item_name");
            }

            con.close();
            return ret_val;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Specific_Drug", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static boolean Consultant_Add_Treatment(String Track_Id, Integer diagnosis_id, String diagnosis_hmis_type, Integer diagnosis_hmis_id, String diagnosis_category, Integer Prescription_Id, String Loading_Dose, String Dose_Unit, String Times, String Times_Unit, String Days, String administer)
            throws SQLException, ClassNotFoundException {
        try {
            Connection con = Apache_Connectionpool.getInstance().getConnection();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            PreparedStatement ps = con.prepareStatement("INSERT INTO consultant_treatment(Track_Id,Diagnosis,Diagnosis_HMIS_Type,Diagnosis_HMIS,Diagnosis_category,Prescription_Id,Loading_Dose,Dose_Unit,Times,Times_Unit,Days,Administer,Record_Date) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, Track_Id);
            ps.setInt(2, diagnosis_id);
            ps.setString(3, diagnosis_hmis_type);
            ps.setInt(4, diagnosis_hmis_id);
            ps.setString(5, diagnosis_category);
            ps.setInt(6, Prescription_Id);
            ps.setString(7, Loading_Dose);
            ps.setString(8, Dose_Unit);
            ps.setString(9, Times);
            ps.setString(10, Times_Unit);
            ps.setString(11, Days);
            ps.setString(12, administer);
            ps.setString(13, dateFormat.format(date));
            ps.executeUpdate();

            con.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Add_Treatment", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

//    public static List<Treatment> Consultant_Get_Treatment(String track_id)
//            throws SQLException {
//        try {
//            Connection con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//
//            PreparedStatement stmt = con.prepareStatement("SELECT t.record_id,t.Track_Id,c.Diagnosis,ch.Heading,t.Diagnosis_Category,d.item_name,t.Loading_Dose,t.Dose_Unit,t.times,t.times_unit,t.Days,t.Administer,d.Prescribe_Unit FROM consultant_treatment t INNER JOIN clinical_diagnosis c on t.Diagnosis=c.Record_Id INNER JOIN drug_list d on t.Prescription_Id=d.drug_id LEFT JOIN clinical_diagnosis_hmis_opd ch on ch.record_id=t.Diagnosis_HMIS WHERE t.Track_Id=?");
//            stmt.setString(1, track_id);
//            ResultSet rs = stmt.executeQuery();
//
//            List treatment_list = new ArrayList();
//            while (rs.next()) {
//                Treatment treatment = new Treatment();
//                treatment.setDiagnosis(rs.getString("Diagnosis"));
//                treatment.setDiagnosis_hmis(rs.getString("Heading"));
//                treatment.setDiagnosis_category(rs.getString("Diagnosis_Category"));
//                treatment.setPrescribed_drug(rs.getString("item_name"));
//                treatment.setLoading_dose(rs.getString("Loading_Dose"));
//                treatment.setDose_unit(rs.getString("Dose_Unit"));
//                treatment.setDose_times(rs.getString("times"));
//                treatment.setDose_times_unit(rs.getString("times_unit"));
//                treatment.setDose_days(rs.getString("Days"));
//                treatment.setAdminister(rs.getString("Administer"));
//                treatment.setPrescribe_unit(rs.getString("Prescribe_Unit"));
//                treatment.setTreatment_id(rs.getInt("record_id"));
//                treatment.setTrans_id(rs.getString("Track_Id"));
//                treatment_list.add(treatment);
//            }
//
//            con.close();
//            return treatment_list;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Treatment", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//
//    }

    public static String Concatenated_Diabetic_Treatments(String track_id)
            throws SQLException {
        try {
            Connection con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

//            PreparedStatement stmt = con.prepareStatement("SELECT group_concat(concat(item_name,' (ld ',loading_dose,', du ',dose_unit,', t ',times,', d ',days,')') separator ';  ') As prescribed_drugs From consultant_treatment,drug_list Where consultant_treatment.Track_Id=? and drug_list.drug_id=consultant_treatment.prescription_id and drug_list.drug_id In (1046,1047,1109,1119)");
            PreparedStatement stmt = con.prepareStatement("SELECT group_concat(concat(item_name,' (ld ',loading_dose,', du ',dose_unit,', t ',times,', d ',days,')') separator ';  ') As prescribed_drugs From consultant_treatment,drug_list Where consultant_treatment.Track_Id=? and drug_list.drug_id=consultant_treatment.prescription_id and drug_list.drug_id In (1046,1047,1109,1119,1926)");
            stmt.setString(1, track_id);

            ResultSet rs = stmt.executeQuery();

            String treatments = new String();

            while (rs.next()) {
                treatments = rs.getString("prescribed_drugs");
            }

            con.close();
            return treatments;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Concatenated_Diabetic_Treatments", " Message: " + ex.getMessage(), date));
            return null;
        }

    }

    public static String Concatenated_Hypertensive_Treatments(String track_id)
            throws SQLException {
        try {
            Connection con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

//            PreparedStatement stmt = con.prepareStatement("SELECT group_concat(concat(item_name,' (ld ',loading_dose,', du ',dose_unit,', t ',times,', d ',days,')') separator ';  ') As prescribed_drugs From consultant_treatment,drug_list Where consultant_treatment.Track_Id=? and drug_list.drug_id=consultant_treatment.prescription_id and drug_list.drug_id In (1077,1126,1141,1108,1042,1496,1428,1074,1100,1073)");
            PreparedStatement stmt = con.prepareStatement("SELECT group_concat(concat(item_name,' (ld ',loading_dose,', du ',dose_unit,', t ',times,', d ',days,')') separator ';  ') As prescribed_drugs From consultant_treatment,drug_list Where consultant_treatment.Track_Id=? and drug_list.drug_id=consultant_treatment.prescription_id and drug_list.drug_id In (1077,1126,1141,1108,1042,1496,1428,1074,1100,1073,1885,1921)");
            stmt.setString(1, track_id);

            ResultSet rs = stmt.executeQuery();

            String treatments = new String();

            while (rs.next()) {
                treatments = rs.getString("prescribed_drugs");
            }

            con.close();
            return treatments;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Concatenated_Hypertensive_Treatments", " Message: " + ex.getMessage(), date));
            return null;
        }

    }

    public static String Concatenated_Other_Prescribed_Treatments(String track_id)
            throws SQLException {
        try {
            Connection con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

//            PreparedStatement stmt = con.prepareStatement("SELECT group_concat(concat(item_name,' (ld ',loading_dose,', du ',dose_unit,', t ',times,', d ',days,')') separator ';  ') As prescribed_drugs From consultant_treatment,drug_list Where consultant_treatment.Track_Id=? and drug_list.drug_id=consultant_treatment.prescription_id and drug_list.drug_id Not In (1046,1047,1109,1119,1077,1126,1141,1108,1042,1496,1428,1074,1100,1073,1499)");
            PreparedStatement stmt = con.prepareStatement("SELECT group_concat(concat(item_name,' (ld ',loading_dose,', du ',dose_unit,', t ',times,', d ',days,')') separator ';  ') As prescribed_drugs From consultant_treatment,drug_list Where consultant_treatment.Track_Id=? and drug_list.drug_id=consultant_treatment.prescription_id and drug_list.drug_id Not In (1046,1047,1109,1119,1926,1077,1126,1141,1108,1042,1496,1428,1074,1100,1073,1499,1885,1921)");
            stmt.setString(1, track_id);

            ResultSet rs = stmt.executeQuery();

            String treatments = new String();

            while (rs.next()) {
                treatments = rs.getString("prescribed_drugs");
            }

            con.close();
            return treatments;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Concatenated_Other_Prescribed_Treatments", " Message: " + ex.getMessage(), date));
            return null;
        }

    }

//    public static List<TreatmentView> Consultant_Get_TreatmentView(String track_id, String trans_id)
//            throws SQLException {
//        try {
//            Connection con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//
////            PreparedStatement stmt = con.prepareStatement("SELECT t.Track_Id,t.Trans_Id,c.Diagnosis,d.item_name,t.Loading_Dose,t.Dose_Unit,t.Times,t.Days,t.Administer,t.Trans_Id_End,t.Start_Date,t.End_Date,t.Record_Id FROM consultant_treatment t INNER JOIN clinical_diagnosis c on t.Diagnosis=c.Record_Id INNER JOIN drug_list d on t.Prescription_Id=d.drug_id WHERE t.Track_Id=? and t.Trans_Id=? Order By t.record_id");
//            PreparedStatement stmt = con.prepareStatement("SELECT t.Track_Id,t.Trans_Id,c.Diagnosis,t.Prescription_Notes,d.item_name,t.Loading_Dose,t.Dose_Unit,t.Times,t.Times_Unit,t.Days,t.Administer,t.Trans_Id_End,t.Start_Date,t.End_Date,d.Prescribe_Unit,t.Record_Id FROM consultant_treatment t INNER JOIN clinical_diagnosis c on t.Diagnosis=c.Record_Id INNER JOIN drug_list d on t.Prescription_Id=d.drug_id WHERE t.Track_Id=? and t.Trans_Id=? Order By t.record_id");
//            stmt.setString(1, track_id);
//            stmt.setString(2, trans_id);
//            ResultSet rs = stmt.executeQuery();
//
//            List treatment_list = new ArrayList();
//            while (rs.next()) {
//                TreatmentView treatment = new TreatmentView();
//                treatment.setTrack_id(rs.getString("Track_Id"));
//                treatment.setTrans_id(rs.getString("Trans_Id"));
//                treatment.setDiagnosis(("NONE".equalsIgnoreCase(rs.getString("Diagnosis"))) ? "--------" : (rs.getString("Diagnosis")));
//                treatment.setPrescription_notes(rs.getString("Prescription_Notes"));
//                treatment.setPrescription(("NONE".equalsIgnoreCase(rs.getString("item_name"))) ? "--------" : (rs.getString("item_name")));
//                treatment.setLoading_dose(rs.getString("Loading_Dose"));
//                treatment.setDose_unit(rs.getString("Dose_Unit"));
//                treatment.setTimes(rs.getString("Times"));
//                treatment.setTimes_unit(rs.getString("Times_Unit"));
//                treatment.setDays(rs.getString("Days"));
//                treatment.setAdminister(rs.getString("Administer"));
//                treatment.setTrans_id_end(rs.getString("Trans_Id_End"));
//                treatment.setStart_date(rs.getString("Start_Date"));
//                treatment.setEnd_date(rs.getString("End_Date"));
//                treatment.setPrescribe_unit(rs.getString("Prescribe_Unit"));
//                treatment.setTreatment_id(rs.getInt("t.Record_Id"));
//                treatment_list.add(treatment);
//            }
//
//            con.close();
//            return treatment_list;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Treatment", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//
//    }

//    public static List<Treatment> Consultant_Get_Drugs_At_Discharge(String track_id)
//    public static List<Treatment> Consultant_Get_Drugs_At_Discharge(String track_id, String origin)
//            throws SQLException {
//        try {
//            Connection con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//            //SELECT t.record_id,t.Track_Id,t.Trans_Id,c.Diagnosis,d.item_name,t.Loading_Dose,t.Dose_Unit,t.Times,t.Days,t.Administer FROM consultant_treatment t INNER JOIN clinical_diagnosis c on t.Diagnosis=c.Record_Id INNER JOIN drug_list d on t.Prescription_Id=d.drug_id WHERE t.Track_Id=?
////            PreparedStatement stmt = con.prepareStatement("SELECT t.record_id,t.Track_Id,c.Diagnosis,d.item_name,t.Loading_Dose,t.Dose_Unit,t.Times,t.Times_Unit,t.Days,t.Administer,d.Prescribe_Unit FROM consultant_treatment t INNER JOIN clinical_diagnosis c on t.Diagnosis=c.Record_Id INNER JOIN drug_list d on t.Prescription_Id=d.drug_id WHERE t.Track_Id=? and (t.trans_id Is Null or (Select trans_name from ward_transactions Where ward_transactions.trans_id=t.trans_id)='Drugs At Discharge Added')");
//            PreparedStatement stmt = con.prepareStatement("SELECT t.record_id,t.Track_Id,c.Diagnosis,d.item_name,t.Loading_Dose,t.Dose_Unit,t.Times,t.Times_Unit,t.Days,t.Administer,d.Prescribe_Unit,d.Unit_Type,Case When loading_dose>0 Then loading_dose+(times-1)*dose_unit+times*dose_unit*((Case When times_unit='per day' Then days When times_unit='per week' Then days/7 Else days/30 End) -1) Else times*dose_unit*(Case When times_unit='per day' Then days When times_unit='per week' Then days/7 Else days/30 End) End As required_prescribe_units,Case When prescribe_unit In ('applications','drops','puffs') Then 1 When drug_id=1164 or drug_id=1564 Then 3 When loading_dose>0 Then (loading_dose+(times-1)*dose_unit+times*dose_unit*((Case When times_unit='per day' Then days When times_unit='per week' Then days/7 Else days/30 End) -1) )/prescribe_units_per_issue Else times*dose_unit*(Case When times_unit='per day' Then days When times_unit='per week' Then days/7 Else days/30 End)/prescribe_units_per_issue End As required_issue_units FROM consultant_treatment t INNER JOIN clinical_diagnosis c on t.Diagnosis=c.Record_Id INNER JOIN drug_list d on t.Prescription_Id=d.drug_id WHERE t.Track_Id=? and (t.trans_id Is Null or (Select trans_name from ward_transactions Where ward_transactions.trans_id=t.trans_id)='Drugs At Discharge Added')");
//
//            stmt.setString(1, track_id);
//
//            ResultSet rs = stmt.executeQuery();
//
//            List treatment_list = new ArrayList();
//
//            while (rs.next()) {
//                Treatment treatment = new Treatment();
//
//                treatment.setDiagnosis(rs.getString("Diagnosis"));
//                treatment.setPrescribed_drug(rs.getString("item_name"));
//                treatment.setLoading_dose(rs.getString("Loading_Dose"));
//                treatment.setDose_unit(rs.getString("Dose_Unit"));
//                treatment.setDose_times(rs.getString("Times"));
//                treatment.setDose_days(rs.getString("Days"));
//                treatment.setAdminister(rs.getString("Administer"));
////                if (origin.equalsIgnoreCase("AIP") || origin.equalsIgnoreCase("PAED")){
//                treatment.setDose_times_unit(rs.getString("Times_Unit"));
//                treatment.setPrescribe_unit(rs.getString("Prescribe_Unit"));
//                treatment.setUnit_type(rs.getString("Unit_Type"));
//                treatment.setRequired_prescribe_units(rs.getString("required_prescribe_units"));
//                treatment.setRequired_issue_units(rs.getString("required_issue_units"));
////                } else {
////                    treatment.setDose_times_unit("");
////                    treatment.setPrescribe_unit("");
////                    treatment.setRequired_prescribe_units("");
////                    treatment.setRequired_issue_units("");
////                }
//                treatment.setTreatment_id(rs.getInt("record_id"));
//                treatment.setTrans_id(rs.getString("Track_Id"));
//
//                treatment_list.add(treatment);
//            }
//
//            con.close();
//            return treatment_list;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Drugs_At_Discharge", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//
//    }

    public static boolean Consultant_Delete_Treatment(Integer treatment_id)
            throws SQLException {

        try {
            Connection con;

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("delete FROM consultant_treatment WHERE record_id=?");
            ps.setInt(1, treatment_id);

            ps.executeUpdate();
            con.close();
            return true;
        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Delete_Treatment", " Message: " + ex.getMessage(), date));
            String temp = "????";
            if (treatment_id.equals(null)) {
                temp = "141677";
            } else {
                temp = "other";
            }
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Delete_Treatment", temp, date));
            return false;
        }

    }

    public static boolean Consultant_Delete_All_Treatments(Integer track_id)
            throws SQLException {

        try {
            Connection con;

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("delete FROM consultant_treatment WHERE Track_Id=?");
            ps.setInt(1, track_id);

            ps.executeUpdate();
            con.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Delete_Treatment", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

 // <editor-fold defaultstate="collapsed" desc="Reports">      

    public static Integer Consultant_Get_Overall(Date specified_date, Date end_date) throws SQLException, ClassNotFoundException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = new Date();

            con = Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT count(*) as Total FROM consultant_tasks c WHERE c.Record_Date between ? and ?");
            ps.setString(1, dateFormat.format(specified_date));
            ps.setString(2, dateFormat.format(end_date));

            ResultSet rs = ps.executeQuery();

            Integer overall_total;

            if (rs.next()) {
                overall_total = rs.getInt("Total");
            } else {
                con.close();
                return Integer.valueOf(0);
            }
            con.close();
            return overall_total;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Overall", " Message: " + ex.getMessage(), date));
            return Integer.valueOf(0);
        }

    }

    public static Integer Consultant_Get_Personal(String staff_id, Date specified_date, Date end_date) throws SQLException, ClassNotFoundException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = new Date();

            con = Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT count(*) as Total FROM consultant_tasks c WHERE c.Record_Date between ? and ? and c.Staff_Id=?");

            ps.setString(1, dateFormat.format(specified_date));
            ps.setString(2, dateFormat.format(end_date));
            ps.setString(3, staff_id);

            ResultSet rs = ps.executeQuery();

            Integer overall_total;

            if (rs.next()) {
                overall_total = rs.getInt("Total");
            } else {
                con.close();
                return Integer.valueOf(0);
            }

            con.close();

            return overall_total;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Personal", " Message: " + ex.getMessage(), date));
            return Integer.valueOf(0);
        }

    }

    public static Integer Consultant_Get_Overall() throws SQLException, ClassNotFoundException {

        try {
            Connection con;
            date = new Date();

            con = Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT count(*) as Total FROM consultant_tasks");

            ResultSet rs = ps.executeQuery();

            Integer overall_total;

            if (rs.next()) {
                overall_total = rs.getInt("Total");
            } else {
                con.close();
                return Integer.valueOf(0);
            }
            con.close();
            return overall_total;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Overall", " Message: " + ex.getMessage(), date));
            return Integer.valueOf(0);
        }

    }

    public static Integer Consultant_Get_Personal(String staff_id) throws SQLException, ClassNotFoundException {

        try {
            Connection con;

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("SELECT count(*) as Total FROM consultant_tasks c WHERE c.Staff_Id=?");
            ps.setString(1, staff_id);

            ResultSet rs = ps.executeQuery();

            Integer overall_total;

            if (rs.next()) {
                overall_total = rs.getInt("Total");
            } else {
                con.close();
                return Integer.valueOf(0);
            }

            con.close();

            return overall_total;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Personal", " Message: " + ex.getMessage(), date));
            return Integer.valueOf(0);
        }

    }
    // </editor-fold>

    public static String get_result_units(Integer sub_test_id, String agetype, float results)
            throws SQLException {

        try {
            Connection con;

            con = Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT L,U,Units FROM tests_normal_ranges WHERE sub_test_id=? and Age_Type=?");
            stmt.setInt(1, sub_test_id);
            stmt.setString(2, agetype);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String result_in_range = (results < Float.valueOf(rs.getString("L")) ? "Low" : (results > Float.valueOf(rs.getString("U"))) ? "High" : "Normal");

                String Units = " " + rs.getString("Units") + "  [" + result_in_range + "]";
                con.close();
                return Units;
            } else {
                con.close();
                return null;
            }

        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Expected_result_range", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

//    public static TB_screening Consultant_Get_tb_Qtns() throws SQLException {
//
//        try {
//            Connection con;
//            con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//
//            PreparedStatement stmt = con.prepareStatement("SELECT * FROM tb_screening q order by q.id ASC");
//
//            ResultSet rs = stmt.executeQuery();
//
//            TB_screening tb_qtns = new TB_screening();
//            while (rs.next()) {
//                switch (rs.getInt("id")) {
//                    case 1:
//                        tb_qtns.setQtn_1(rs.getString("question"));
//                        tb_qtns.setAgegroup_1(rs.getString("age_group"));
//                    case 2:
//                        tb_qtns.setQtn_2(rs.getString("question"));
//                        tb_qtns.setAgegroup_2(rs.getString("age_group"));
//                    case 3:
//                        tb_qtns.setQtn_3(rs.getString("question"));
//
//                        tb_qtns.setAgegroup_3(rs.getString("age_group"));
//                    case 4:
//                        tb_qtns.setQtn_4(rs.getString("question"));
//
//                        tb_qtns.setAgegroup_4(rs.getString("age_group"));
//                    case 5:
//                        tb_qtns.setQtn_5(rs.getString("question"));
//
//                        tb_qtns.setAgegroup_5(rs.getString("age_group"));
//                    case 6:
//                        tb_qtns.setQtn_6(rs.getString("question"));
//                        tb_qtns.setAgegroup_6(rs.getString("age_group"));
//                    case 7:
//                        tb_qtns.setQtn_7(rs.getString("question"));
//                        tb_qtns.setAgegroup_7(rs.getString("age_group"));
//                }
//
//            }
//
//            con.close();
//
//            return tb_qtns;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Family_Planning_Qtns", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//    }
//
//    public static List<disability_screening> Consultant_Get_Disability()
//            throws SQLException {
//        try {
//            Connection con = Apache_Connectionpool.getInstance().getConnection();
//            date = new Date();
//
//            PreparedStatement stmt = con.prepareStatement("SELECT id,disability FROM disability_screening");
//
//            ResultSet rs = stmt.executeQuery();
//
//            List disabilities = new ArrayList();
//
//            while (rs.next()) {
//                disability_screening dis = new disability_screening();
//                dis.setDisability_id(rs.getString("id"));
//                dis.setDisability_name(rs.getString("disability"));
//                disabilities.add(dis);
//            }
//
//            con.close();
//            return disabilities;
//        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Consultant DAO", "Consultant_Get_Disability", " Message: " + ex.getMessage(), date));
//            return null;
//        }
//
//    }

}
