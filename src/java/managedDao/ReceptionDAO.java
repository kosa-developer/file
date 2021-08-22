package managedDao;

import java.beans.PropertyVetoException;
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
import managedModal.Error;
import managedModal.Patient;
import managedModal.ReceptionInfo;
import managedModal.ReceptionTask;

public class ReceptionDAO
        implements Serializable {
    
    private static final long serialVersionUID = 1L;
    public static Date date;
    
    public static Integer Reception_Get_Non_Registered_Patient_Last_Index() throws SQLException {
        
        try {
            Connection con;
            
            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();
            
            PreparedStatement stmt = con.prepareStatement("select t.Patien_Last_Index from temp_values t;");
            
            ResultSet rs = stmt.executeQuery();
            Integer temp;
            if (rs.next()) {
                temp = rs.getInt("Patien_Last_Index");
            } else {
                temp = 0;
            }
            con.close();
                rs.close();
            return temp;
            
        } catch (Exception ex) {
//            ErrorDAO.Error_Add(new Error("Reception DAO", "Reception_Get_Non_Registered_Patient_Last_Index", " Message: " + ex.getMessage(), date));
            return null;
        }
    }
    
    
    public static String Reception_Get_BMI_result(Integer Age, String Sex, Double bmi_score) throws SQLException {
        
        try {
            Connection con;
            PreparedStatement stmt;
            
            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();
            
            stmt = con.prepareStatement("Select Severe_UW,Moderate_UW,Mild_UW,OW,Obese From bmi_thresholds Where Sex=? and ?>=minage and ?<maxage");
            
            stmt.setString(1, Sex);
            stmt.setInt(2, Age);
            stmt.setInt(3, Age);
            
            ResultSet rs = stmt.executeQuery();
            
            String bmi_category = null;
            if (rs.next()) {
                if (bmi_score < rs.getFloat("Severe_UW")) {
                    bmi_category = "Severely Underweight";
                } else if (bmi_score < rs.getFloat("Moderate_UW")) {
                    bmi_category = "Moderately Underweight";
                } else if (bmi_score < rs.getFloat("Mild_UW")) {
                    bmi_category = "Mildly Underweight";
                } else if (bmi_score <= rs.getFloat("OW")) {
                    bmi_category = "Normal";
                } else if (bmi_score <= rs.getFloat("Obese")) {
                    bmi_category = "Overweight";
                } else {
                    bmi_category = "Obese";
                }
                con.close();
                return bmi_category;
            }
           con.close();
                rs.close();
            return null;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Reception DAO", "Reception_Get_BMI_result", " Message: " + ex.getMessage(), date));
            return null;
        }
    }
    
    public static String Reception_Get_Zscore(Integer Age, String Sex, Double weight, Double height) throws SQLException {
        
        try {
            System.out.println("arguments: " + Age + " " + Sex + " " + weight + " " + height);
            
            Connection con;
            PreparedStatement stmt;
            
            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();
            
            stmt = con.prepareStatement("Select minus3SDweight,minus2SDweight,minus1SDweight,median,plus1SDweight,plus2SDweight,plus3SDweight From zscore_thresholds Where ?>=MinAge and ?<MaxAge and Sex=? and ?>=HeightLength-0.25 and ?<HeightLength+0.25");
            
            stmt.setInt(1, Age);
            stmt.setInt(2, Age);
            stmt.setString(3, Sex);
            stmt.setDouble(4, height);
            stmt.setDouble(5, height);
            ResultSet rs = stmt.executeQuery();
            
            String zscore_category = null;
            if (rs.next()) {
                if (weight < rs.getFloat("minus3SDweight")) {
                    zscore_category = "< -3 SD";
                } else if (weight < rs.getFloat("minus2SDweight")) {
                    zscore_category = "-2 to -3 SD";
                } else if (weight < rs.getFloat("minus1SDweight")) {
                    zscore_category = "-1 to -2 SD";
                } else if (weight < rs.getFloat("median")) {
                    zscore_category = "0 to -1 SD";
                } else if (weight <= rs.getFloat("plus1SDweight")) {
                    zscore_category = "0 to +1 SD";
                } else if (weight <= rs.getFloat("plus2SDweight")) {
                    zscore_category = "+1 to +2 SD";
                } else if (weight <= rs.getFloat("plus3SDweight")) {
                    zscore_category = "+2 to +3 SD";
                } else {
                    zscore_category = "> +3 SD";
                }
                con.close();
                return zscore_category;
            }
           con.close();
                rs.close();
            return null;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Reception DAO", "Reception_Get_BMI_result", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    // changes by patrick starts
    // zscore_heightforage on 04/09/2019 
    public static String Report_Get_zscore_heightforage(String AgeUnit, float Age, String Sex, String Measurement, Double height) throws SQLException {
        
        try {
            System.out.println("arguments: " + Age + " " + Sex + " " + Measurement + " " + height);
            
            Connection con;
            PreparedStatement stmt;
            
            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();
            
            stmt = con.prepareStatement("Select minus3SDheight,minus2SDheight,minus1SDheight,Median,plus1SDheight,plus2SDheight,plus3SDheight From zscore_heightforage Where AgeUnit=? and ?>=Age and ?<Age+1 and Sex=? and Measurement=?");
            
            stmt.setString(1, AgeUnit);
            stmt.setDouble(2, Age);
            stmt.setDouble(3, Age);
            stmt.setString(4, Sex);
            stmt.setString(5, Measurement);
            ResultSet rs = stmt.executeQuery();
            
            String zscore_category = null;
            if (rs.next()) {
                if ((Math.round(height * 10)) < (Math.round(rs.getFloat("minus3SDheight") * 10))) {
                    zscore_category = "< -3 SD";
                } else if ((Math.round(height * 10)) < (Math.round(rs.getFloat("minus2SDheight") * 10))) {
                    zscore_category = "-2 to -3 SD";
                } else if ((Math.round(height * 10)) < (Math.round(rs.getFloat("minus1SDheight") * 10))) {
                    zscore_category = "-1 to -2 SD";
                } else if ((Math.round(height * 10)) < (Math.round(rs.getFloat("median") * 10))) {
                    
                    zscore_category = "0 to -1 SD";
                } else if ((Math.round(height * 10)) <= (Math.round(rs.getFloat("plus1SDheight") * 10))) {
                    
                    zscore_category = "0 to +1 SD";
                } else if ((Math.round(height * 10)) <= (Math.round(rs.getFloat("plus2SDheight") * 10))) {
                    zscore_category = "+1 to +2 SD";
                } else if ((Math.round(height * 10)) <= (Math.round(rs.getFloat("plus3SDheight") * 10))) {
                    zscore_category = "+2 to +3 SD";
                } else {
                    zscore_category = "> +3 SD";
                }
                con.close();
                return zscore_category;
            }
           con.close();
                rs.close();
            return null;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Report DAO", "Report_Get_zscore_heightforage", " Message: " + ex.getMessage(), date));
            return null;
            
        }
    }

    // zscore_weightforage on 04/09/2019 
    public static String Report_Get_zscore_weightforage(String AgeUnit, float Age, String Sex, Double weight) throws SQLException {
        
        try {
            System.out.println("arguments: " + Age + " " + Sex + " " + weight);
            
            Connection con;
            PreparedStatement stmt;
            
            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();
            
            stmt = con.prepareStatement("Select minus3SDweight,minus2SDweight,minus1SDweight,Median,plus1SDweight,plus2SDweight,plus3SDweight From zscore_weightforage Where AgeUnit=? and ?>=Age and ?<Age+1 and Sex=?");
            
            stmt.setString(1, AgeUnit);
            stmt.setDouble(2, Age);
            stmt.setDouble(3, Age);
            stmt.setString(4, Sex);
            ResultSet rs = stmt.executeQuery();
            
            String zscore_category = null;
            if (rs.next()) {
                if ((Math.round(weight * 10)) < (Math.round(rs.getFloat("minus3SDweight") * 10))) {
                    zscore_category = "< -3 SD";
                } else if ((Math.round(weight * 10)) < (Math.round(rs.getFloat("minus2SDweight") * 10))) {
                    zscore_category = "-2 to -3 SD";
                } else if ((Math.round(weight * 10)) < (Math.round(rs.getFloat("minus1SDweight") * 10))) {
                    zscore_category = "-1 to -2 SD";
                } else if ((Math.round(weight * 10)) < (Math.round(rs.getFloat("median") * 10))) {
                    zscore_category = "0 to -1 SD";
                } else if ((Math.round(weight * 10)) <= (Math.round(rs.getFloat("plus1SDweight") * 10))) {
                    zscore_category = "0 to +1 SD";
                } else if ((Math.round(weight * 10)) <= (Math.round(rs.getFloat("plus2SDweight") * 10))) {
                    zscore_category = "+1 to +2 SD";
                } else if ((Math.round(weight * 10)) <= (Math.round(rs.getFloat("plus3SDweight") * 10))) {
                    zscore_category = "+2 to +3 SD";
                } else {
                    zscore_category = "> +3 SD";
                }
                con.close();
                
                return zscore_category;
            }
           con.close();
                rs.close();
            return null;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Report DAO", "Report_Get_zscore_weightforage", " Message: " + ex.getMessage(), date));
            return null;
            
        }
    }
    

    public static List<ReceptionTask> Reception_Get_Transactions() throws SQLException {
        
        try {
            Connection con;
            
            ReceptionTask receptiontask;
            
            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();
            
            PreparedStatement stmt = con.prepareStatement("select f.Track_Id,f.Patient_Id,f.Patient_Name,TIMESTAMPDIFF(YEAR, f.DOB, CURDATE()) AS Age,f.Gender,f.VisitReason,f.Problem,f.Triage_Category,f.Room_No,f.Record_Date_In from frontdesk_tasks f order by f.Record_Date_In desc limit 50;");
            
            ResultSet rs = stmt.executeQuery();
            List receptiontask_list = new ArrayList();
            Integer color_code;
            while (rs.next()) {
                color_code = Integer.valueOf(-1);
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
                receptiontask = new ReceptionTask(rs.getString("Track_Id"), rs.getString("Patient_Id"), rs.getString("Patient_Name"), Integer.valueOf(rs.getInt("Age")), rs.getString("Gender"), rs.getString("VisitReason"), rs.getString("Problem"), color_code, rs.getString("Room_No"), rs.getString("Record_Date_In"));
                
                receptiontask_list.add(receptiontask);
            }
            con.close();
            rs.close();
            return receptiontask_list;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Reception DAO", "Reception_Get_Transactions", " Message: " + ex.getMessage(), date));
            return null;
        }
    }
    
    
    public static ReceptionInfo Reception_Get_Reception_Info(String task_id) throws SQLException {
        
        try {
            Connection con;
            PreparedStatement stmt;
            
            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();
            
            stmt = con.prepareStatement("select f.Referral_Status,f.Patient_Name,TIMESTAMPDIFF(YEAR, f.DOB, CURDATE()) AS Age,f.Age_Months,f.Age_Days,f.Gender,f.District,f.Subcounty_Name,f.Village_Name,f.Referred_From,f.Weight,f.Height,f.Temperature,f.Oxy_Saturation,f.Heart_Pulse,f.Blood_Pressure,f.Respiratory_Rate,f.FamilyPlanning,f.ItnUse,f.VisitReason,f.Problem,f.muac,f.Triage_Category,f.Skip_Accounts,f.Forward_To_Section,f.Weight_For_Height,f.Weight_for_age,f.Height_for_age,f.Record_Errors from frontdesk_tasks f  where f.Track_Id=?");
            
            stmt.setString(1, task_id);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                ReceptionInfo receptionInfo = new ReceptionInfo();
                receptionInfo.setReferral_status(rs.getString("Referral_Status"));
                receptionInfo.setReferred_from(rs.getString("Referred_From"));
                receptionInfo.setWeight(rs.getDouble("Weight"));
                receptionInfo.setHeight(rs.getDouble("Height"));
                receptionInfo.setTemperature(rs.getString("Temperature"));
                receptionInfo.setOxy_saturation(rs.getString("Oxy_Saturation"));
                receptionInfo.setHeart_pulse(rs.getString("Heart_Pulse"));
                receptionInfo.setBlood_pressure(rs.getString("Blood_Pressure"));
                receptionInfo.setRespiratory_rate(rs.getString("Respiratory_Rate"));
                receptionInfo.setFamily_planning(rs.getBoolean("FamilyPlanning"));
                receptionInfo.setItn_in_use(rs.getBoolean("ItnUse"));
                receptionInfo.setVisit_reason(rs.getString("VisitReason"));
                receptionInfo.setProblem(rs.getString("Problem"));
                receptionInfo.setMuac(rs.getString("muac"));
                receptionInfo.setTriage_category(rs.getString("Triage_Category"));
                receptionInfo.setSkip_accounts(rs.getBoolean("Skip_Accounts"));
                receptionInfo.setForward_to_section(rs.getString("Forward_To_Section"));
//                receptionInfo.setWeight_For_Height(rs.getString("Weight_For_Height"));
                receptionInfo.setWeight_For_Height(rs.getString("Weight_For_Height"));
                receptionInfo.setWeight_for_age(rs.getString("Weight_for_age"));
                receptionInfo.setHeight_for_age(rs.getString("Height_for_age"));
                receptionInfo.setRecord_errors(rs.getString("Record_Errors"));
                receptionInfo.setAge(Integer.valueOf(rs.getInt("Age")));
                receptionInfo.setAge_days(Integer.valueOf(rs.getInt("Age_Days")));
                receptionInfo.setAge_months(Integer.valueOf(rs.getInt("Age_Months")));
                receptionInfo.setFullname(rs.getString("Patient_Name"));
                receptionInfo.setGender(rs.getString("Gender"));
                receptionInfo.setSubcounty(rs.getString("Subcounty_Name"));
                receptionInfo.setVillage(rs.getString("Village_Name"));
                receptionInfo.setParish(rs.getString("District"));
                
                con.close();
                return receptionInfo;
            }
          con.close();
                rs.close();
            return null;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Reception DAO", "Reception_Get_Reception_Info", " Message: " + ex.getMessage(), date));
            return null;
        }
    }
    
    public static boolean Reception_Accounts_Pending(String trackID, boolean skipped, boolean locked, String user,String operation,Integer ammount)
            throws SQLException, ClassNotFoundException, PropertyVetoException, Exception {
        
        try {
            Connection con;
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();
            
            con=Apache_Connectionpool.getInstance().getConnection();
            
            PreparedStatement ps = con.prepareStatement("insert into accounts_pending(Track_Id,Operation,Payable_Amount,Skipped_Accounts,Record_Time,Locked,Staff_From) values(?,?,?,?,?,?,?)");
            
            ps.setString(1, trackID);
            ps.setString(2, operation);
            ps.setInt(3, ammount);
            ps.setBoolean(4, skipped);
            ps.setString(5, dateFormat.format(date));
            ps.setBoolean(6, locked);
            ps.setString(7, user);
            
            ps.executeUpdate();
            
            con.close();
            ps.close();
            
            return true;
        } catch (SQLException ex) {
            ErrorDAO.Error_Add(new Error("Reception DAO", "Reception_Accounts_Pending", " Message: " + ex.getMessage(), date));
            return false;
        }
        
    }
    
    public static boolean Reception_Consultant_Pending(String track_id, String staff_id, boolean locked)
            throws SQLException {
        
        try {
            Connection con;
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();
            
            con=Apache_Connectionpool.getInstance().getConnection();
            
            PreparedStatement ps = con.prepareStatement("insert into consultant_pending(Track_Id,From_Staff,Record_Date,Locked) values(?,?,?,?)");
            ps.setString(1, track_id);
            ps.setString(2, staff_id);
            ps.setString(3, dateFormat.format(date));
            ps.setBoolean(4, locked);
            
            ps.executeUpdate();
            
            con.close();
            ps.close();
            
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Reception DAO", "Reception_Consultant_Pending", " Message: " + ex.getMessage(), date));
            return false;
        }
        
    }
    
    public static boolean Reception_Dentist_Pending(String track_id, String staff_id, boolean locked)
            throws SQLException {
        
        try {
            Connection con;
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();
            
            con=Apache_Connectionpool.getInstance().getConnection();
            
            PreparedStatement ps = con.prepareStatement("insert into dentist_pending(Track_Id,From_Staff,Record_Date,Locked) values(?,?,?,?)");
            ps.setString(1, track_id);
            ps.setString(2, staff_id);
            ps.setString(3, dateFormat.format(date));
            ps.setBoolean(4, locked);
            
            ps.executeUpdate();
            con.close();
            ps.close();
            
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Reception DAO", "Reception_Dentist_Pending", " Message: " + ex.getMessage(), date));
            return false;
        }
        
    }
    
    public static String Reception_Get_Forwarded_To(String task_id) throws SQLException {
        
        try {
            Connection con;
            
            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();
            
            PreparedStatement stmt = con.prepareStatement("select f.Forward_To_Section from frontdesk_tasks f where f.Track_Id=?");
            stmt.setString(1, task_id);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String temp = rs.getString("Forward_To_Section");
               con.close();
                rs.close();
                return temp;
            } else {
                con.close();
                rs.close();
                return null;
            }
            
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Reception DAO", "Reception_Get_Forwarded_To", " Message: " + ex.getMessage(), date));
            return null;
        }
        
    }
    
    public static List<ReceptionTask> Reception_Get_Search_Results(String query_string) throws SQLException {
        
        try {
            Connection con;
            
            ReceptionTask receptiontask;
            
            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();
            
            PreparedStatement stmt = con.prepareStatement(query_string);
            
            ResultSet rs = stmt.executeQuery();
            List receptiontask_list = new ArrayList();
            Integer color_code;
            while (rs.next()) {
                color_code = Integer.valueOf(-1);
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
                receptiontask = new ReceptionTask(rs.getString("Track_Id"), rs.getString("Patient_Id"), rs.getString("Patient_Name"), Integer.valueOf(rs.getInt("Age")), rs.getString("Gender"), rs.getString("VisitReason"), rs.getString("Problem"), color_code, rs.getString("Room_No"), rs.getString("Record_Date_In"));
                
                receptiontask_list.add(receptiontask);
            }
            con.close();
                rs.close();
            return receptiontask_list;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Reception DAO", "Reception_Get_Search_Results", " Message: " + ex.getMessage() + query_string, date));
            return null;
        }
    }
     public static Patient Reception_Retrieve_Patient_Details(String task_id) throws SQLException {
        
        try {
            Connection con;
            PreparedStatement stmt;
            
            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();
            
            stmt = con.prepareStatement("select f.Patient_Id,f.Patient_Name,f.Gender,f.DOB,TIMESTAMPDIFF(YEAR, f.DOB, CURDATE()) AS Age,f.Age_Days,f.Age_Months,f.Weight_For_Height,f.Weight_for_age,f.Height_for_age,f.Village_Name,f.Subcounty_Name,f.District,f.Member,f.Subscription_Expired from frontdesk_tasks f where f.Track_Id=?");
            stmt.setString(1, task_id);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Patient patient = new Patient();
                patient.setMemberId(rs.getString("Patient_Id"));
                patient.setFullname(rs.getString("Patient_Name"));
                patient.setMemberGender(rs.getString("Gender"));
                patient.setAge(Integer.valueOf(rs.getInt("Age")));
                patient.setAge_days(Integer.valueOf(rs.getInt("Age_Days")));
                patient.setAge_months(Integer.valueOf(rs.getInt("Age_Months")));
                patient.setSubcounty(rs.getString("Subcounty_Name"));
                patient.setParish(rs.getString("District"));
                patient.setVillage(rs.getString("Village_Name"));
                patient.setMember_exp(String.valueOf(rs.getBoolean("Member")).toUpperCase());
                patient.setSubscription_exp(String.valueOf(rs.getBoolean("Subscription_Expired")).toUpperCase());
                patient.setWeight_for_age(rs.getString("Weight_for_age"));
                patient.setHeight_for_age(rs.getString("Height_for_age"));
                patient.setDOB(rs.getDate("DOB"));
                patient.setTrack_id(task_id);
                con.close();
                return patient;
            }
          con.close();
                rs.close();
            return null;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Reception DAO", "Reception_Retrieve_Patient_Details", " Message: " + ex.getMessage(), date));
            return null;
        }
    }
    
    /*
     * Class Ends 
     * 
     */
    
}
