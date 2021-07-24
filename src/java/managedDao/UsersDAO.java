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
import managedModal.Error;
import managedModal.Loggedin;
import managedModal.Users;

public
        class UsersDAO
        implements Serializable {

    private static final
            long serialVersionUID = 1L;
    private static
            Date date;

    public static
            boolean Users_Add_UserAccount(String recordid, String uid, String fullName, String userName, String password, String sex, Integer rid, Integer did, String status)
            throws SQLException {

        try {
            Connection con;
            con=Apache_Connectionpool.getInstance().getConnection();
           
            date = new Date();
            String query = "Insert Into Users (Record_Id,UID,Status,FullName,UserName,RID,DID,Password,Sex,Deleted) Values (UUID(),'1022','Active','Dr. Alele Tony','aleletOPD',3,(Select DID From Department Where DepartmentName='Consultant'),'8sIe1hVzdGo=','M','N')";
            PreparedStatement ps = con.prepareStatement("insert into users(record_Id,uid,fullName,userName,password,sex,did,status) values(?,?,?,?,?,?,?,?)");

            ps.setString(1, recordid);
            ps.setString(2, uid);
            ps.setString(3, fullName);
            ps.setString(4, userName);
            ps.setString(5, password);
            ps.setString(6, sex);
            //ps.setInt(7, rid.intValue());
            ps.setInt(7, did);
            ps.setString(8, status);

            ps.executeUpdate();
            con.close();
ps.close();
            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "Users_Add_UserAccount", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            boolean User_Exists(String userName)
            throws SQLException {

        try {
            Connection con;

            PreparedStatement stmt;

           con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            stmt = con.prepareStatement("select * from users where userName= ?");
            stmt.setString(1, userName);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                con.close();
                return true;
            }
           con.close();
                rs.close();
            return false;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "User_Exists", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            boolean Consultant_Add_Access(String uid, Boolean opd, Boolean ipd)
            throws SQLException {

        try {
            Connection con;

           con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("insert into consultant_access(uid,opd,ipd) values(?,?,?)");

            ps.setString(1, uid);
            ps.setBoolean(2, opd);
            ps.setBoolean(3, ipd);

            ps.executeUpdate();
            con.close();
            ps.close();

            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "Consultant_Add_Access", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            Users User_Login(String userName, String password)
            throws SQLException {

        try {
            Connection con;

            PreparedStatement stmt;

           con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            String userStatus = "Active";

            stmt = con.prepareStatement("select uid,fullName,userName,password from users where userName= ? and password= ? and status='" + userStatus + "' and Deleted='N'");
            stmt.setString(1, userName);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            Users users = new Users();

            if (rs.next()) {
                users.setFullName(rs.getString("fullName"));
                users.setUid(rs.getString("uid"));
                con.close();
                return users;
            }
          con.close();
                rs.close();
            return null;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "login", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            Loggedin Users_Get_Details(String userName, String password)
            throws SQLException {

        try {
            Connection con;
            PreparedStatement stmt;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            stmt = con.prepareStatement("select u.UID,u.FullName,u.UserName,u.Sex,u.`Status`,d.DepartmentName,r.Name,u.Password from users u inner join department d on u.DID=d.DID inner join roles r on u.RID=r.RID where u.userName= ? and u.password= ?");
            stmt.setString(1, userName);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            Loggedin loggedin_user = new Loggedin();

            if (rs.next()) {
                loggedin_user.setUser_id(rs.getString("UID"));
                loggedin_user.setFullname(rs.getString("FullName"));
                loggedin_user.setUsername(rs.getString("UserName"));
                if ("F".equals(rs.getString("Sex"))) {
                    loggedin_user.setGender("Female");
                }
                else {
                    loggedin_user.setGender("Male");
                }
                loggedin_user.setStatus(rs.getString("Status"));
                loggedin_user.setDepartment(rs.getString("DepartmentName"));
                loggedin_user.setRole(rs.getString("Name"));
                loggedin_user.setCurrent_password(rs.getString("Password"));
                con.close();
                return loggedin_user;
            }
          con.close();
                rs.close();
            return null;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "Users_Get_Details", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            boolean Users_Update_Profile(String user_id, String fullName, String password)
            throws SQLException {

        try {
            Connection con;

           con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("update users set fullName=?,password=? where uid=?");

            ps.setString(1, fullName);
            ps.setString(2, password);
            ps.setString(3, user_id);

            ps.executeUpdate();
            con.close();
            ps.close();

            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "Users_Update_Profile", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            boolean Users_Update_User_Account(String uid, String fullName, String userName, String password, String sex, Integer rid, Integer did, String status)
            throws SQLException {

        try {
            Connection con;

           con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("update users set fullName=?,userName=?,password=?,sex=?,rid=?,did=?,status=? where uid=?");

            ps.setString(1, fullName);
            ps.setString(2, userName);
            ps.setString(3, password);
            ps.setString(4, sex);
            ps.setInt(5, rid.intValue());
            ps.setInt(6, did.intValue());
            ps.setString(7, status);
            ps.setString(8, uid);

            ps.executeUpdate();
            con.close();
            ps.close();
            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "Users_Update_User_Account", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            boolean Users_Update_Pwd(String uid, String password)
            throws SQLException {

        try {
            Connection con;

           con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("update users set password=? where uid=?");

            ps.setString(1, password);
            ps.setString(2, uid);

            ps.executeUpdate();
            con.close();
            ps.close();
            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "Users_Update_Pwd", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            boolean Users_Delete_User_Account(String uid)
            throws SQLException {

        try {
            Connection con;
            System.out.println("uid :: " + uid);
            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("update users set Deleted=? where uid=?");

            ps.setString(1, "Y");
            ps.setString(2, uid);

            ps.executeUpdate();
            con.close();
            ps.close();

            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "Users_Delete_User_Account", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            List<Users> Users_Get_All()
            throws SQLException {

        try {
            Connection con;

            Users users;

           con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("select u.UID, u.FullName,u.UserName,u.Sex,u.`Status`,d.DepartmentName from users u inner join department d on u.DID=d.DID where u.Deleted='N'");

            ResultSet rs = stmt.executeQuery();

            List userList = new ArrayList();

            while (rs.next()) {
                users = new Users(rs.getString("UID"), rs.getString("FullName"), rs.getString("UserName"), rs.getString("Sex"), rs.getString("Status"), rs.getString("DepartmentName"));
                userList.add(users);
            }
            con.close();
            rs.close();
            return userList;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "Users_Get_All", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            List<Users> listAll(String category)
            throws SQLException {

        try {
            Connection con;

            Users users;

          con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("select uid,fullName,userName,sex,status from users where rid=(select rid from roles where name=?) and Deleted='N'");
            stmt.setString(1, category);

            ResultSet rs = stmt.executeQuery();

            List userList = new ArrayList();

            while (rs.next()) {
                users = new Users(rs.getString("uid"), rs.getString("fullName"), rs.getString("userName"), rs.getString("sex"), rs.getString("status"));
                userList.add(users);
            }
            con.close();
            rs.close();

            return userList;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "listAll", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            boolean accountState(String status, String uid)
            throws SQLException {

        try {
            Connection con;

          con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("update users set status=? where uid=?");

            ps.setString(1, status);
            ps.setString(2, uid);

            ps.executeUpdate();

            con.close();
            ps.close();

            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "accountState", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            List<Users> forwardTo(String uid)
            throws SQLException {

        try {
            Connection con;

            Users users;

           con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("select uid,fullName,userName,sex,status from users where uid = (select toID from forwardmatrix where fromID=?)");
            stmt.setString(1, uid);

            ResultSet rs = stmt.executeQuery();
            List userList = new ArrayList();

            while (rs.next()) {
                users = new Users(rs.getString("uid"), rs.getString("fullName"), rs.getString("userName"), rs.getString("sex"), rs.getString("status"));
                userList.add(users);
            }

            con.close();
            rs.close();

            return userList;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "forwardTo", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            Users Users_Getuser(String uid)
            throws SQLException {

        try {
            Connection con;
            PreparedStatement stmt;

           con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            stmt = con.prepareStatement("select * from users where uid=?");
            stmt.setString(1, uid);

            ResultSet rs = stmt.executeQuery();
            Users users;

            if (rs.next()) {
                users = new Users(rs.getString("uid"), rs.getString("fullName"), rs.getString("userName"), rs.getString("userName"), rs.getString("sex"), Integer.valueOf(rs.getInt("rid")), Integer.valueOf(rs.getInt("did")), rs.getString("status"), rs.getString("record_Id"));
                con.close();
                rs.close();
                return users;
            }
            else {
                con.close();
                rs.close();
                return null;
            }

        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "Users_Getuser", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            void loginHistory(String uid)
            throws SQLException {

        try {
            Connection con;

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
            date = new Date();

           con=Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("insert into login_history(Staff_Id,Login_Date,Login_Time) values(?,?,?)");

            ps.setString(1, uid);
            ps.setString(2, dateFormat.format(date));
            ps.setString(3, dateFormat1.format(date));

            ps.executeUpdate();
            con.close();
            ps.close();
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "loginHistory", " Message: " + ex.getMessage(), date));
        }
    }

    public static
            List<Users> getAccoutants()
            throws SQLException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = new Date();

            Users users;

           con=Apache_Connectionpool.getInstance().getConnection();
           
            PreparedStatement stmt = con.prepareStatement("select DISTINCT u.UID,u.FullName from users u inner join login_history l on u.UID=l.Staff_Id and l.Login_Date=? inner join roles r on u.RID = r.RID where u.RID=2");
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
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "getAccoutants", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            List<Users> FowardToActiveStaff_FrontDesk(String uid)
            throws SQLException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = new Date();

            Users users;

           con=Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement stmt = con.prepareStatement("select DISTINCT u.UID,u.FullName from forwardmatrix f inner join users u on f.ToID = u.UID and u.RID!=2 inner join login_history l on u.UID=l.Staff_Id and l.Login_Date=? where f.FromID=?");

            stmt.setString(1, dateFormat.format(date));
            stmt.setString(2, uid);

            ResultSet rs = stmt.executeQuery();
            List userList = new ArrayList();
            while (rs.next()) {
                users = new Users(rs.getString("UID"), rs.getString("FullName"));
                userList.add(users);
            }
            con.close();
            rs.close();

            return userList;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "FowardToActiveStaff_FrontDesk", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            String userRole(String uid)
            throws SQLException {

        try {
            Connection con;
            PreparedStatement stmt;

           con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            stmt = con.prepareStatement("select name from users u,roles r where u.RID=r.RID and u.uid=?");
            stmt.setString(1, uid);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String userRole = rs.getString("name");
                con.close();
                return userRole;
            }
          con.close();
                rs.close();

            return null;
        }
        catch (Exception ex) {

            ErrorDAO.Error_Add(new Error("Users DAO", "userRole", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            String User_Department(String uid)
            throws SQLException {

        try {
            Connection con;
            PreparedStatement stmt;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            stmt = con.prepareStatement("SELECT d.DepartmentName FROM users u inner join department d on u.DID=d.DID where u.UID = ?");
            stmt.setString(1, uid);

            ResultSet rs = stmt.executeQuery();
            String _department = "";

            if (rs.next()) {
                _department = rs.getString("DepartmentName");
            }
con.close();
                rs.close();
            return _department;

        }
        catch (Exception ex) {

            ErrorDAO.Error_Add(new Error("Users DAO", "User_Department", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            Boolean OPD_Enabled(String uid)
            throws SQLException {

        try {
            Connection con;
            PreparedStatement stmt;

           con=Apache_Connectionpool.getInstance().getConnection();

            stmt = con.prepareStatement("SELECT c.opd FROM users u inner join consultant_access c on u.UID=c.uid where u.UID = ?");
            stmt.setString(1, uid);
            System.out.println("eeeeeee");
            ResultSet rs = stmt.executeQuery();
            Boolean _opd_enabled = false;

            if (rs.next()) {
                _opd_enabled = rs.getBoolean("opd");
                System.out.println("X" + _opd_enabled);
            }
con.close();
                rs.close();
            return _opd_enabled;

        }
        catch (Exception ex) {

            ErrorDAO.Error_Add(new Error("Users DAO", "OPD_Enabled", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            Boolean IPD_Enabled(String uid)
            throws SQLException {

        try {
            Connection con;
            PreparedStatement stmt;

            con=Apache_Connectionpool.getInstance().getConnection();

            stmt = con.prepareStatement("SELECT c.ipd FROM users u inner join consultant_access c on u.UID=c.uid where u.UID = ?");
            stmt.setString(1, uid);

            ResultSet rs = stmt.executeQuery();
            Boolean _ipd_enabled = false;

            if (rs.next()) {
                _ipd_enabled = rs.getBoolean("ipd");
                System.out.println("X" + _ipd_enabled);
            }
con.close();
                rs.close();
            return _ipd_enabled;

        }
        catch (Exception ex) {

            ErrorDAO.Error_Add(new Error("Users DAO", "OPD_Enabled", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            boolean User_Exists(String uname, String id)
            throws SQLException {

        try {
            Connection con;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("select * from users where userName=? and uid=?");

            stmt.setString(1, uname);
            stmt.setString(2, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                con.close();
                return true;
            }
          con.close();
                rs.close();
                rs.close();
            return false;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "User_Exists", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            List<Users> Users_Get_Disensary_Staff()
            throws SQLException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = new Date();

            Users users;

            con=Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement stmt = con.prepareStatement("select DISTINCT u.UID,u.FullName from users u inner join login_history l on u.UID=l.Staff_Id and l.Login_Date=? inner join roles r on u.RID = r.RID where u.RID=4");

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
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "Users_Get_Disensary_Staff", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            Users Users_Get_User(String user_id)
            throws SQLException {

        try {
            Connection con;
            PreparedStatement stmt;

            Users users = new Users();

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            stmt = con.prepareStatement("select * from users u where u.UID=?");
            stmt.setString(1, user_id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                users = new Users(rs.getString("UID"), rs.getString("FullName"), rs.getString("UserName"), rs.getString("Password"), rs.getString("Sex"), Integer.valueOf(rs.getInt("RID")), Integer.valueOf(rs.getInt("DID")), rs.getString("Status"));
            }
            con.close();
                rs.close();

            return users;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "Users_Get_User", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            List<Users> Users_Surgery_Get_All(String _type)
            throws SQLException {

        try {
            Connection con;

            Users users;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT sys_id,staff_id,staff_name,staff_gender,staff_status from surgery_staff where staff_type=? and staff_status!='Deleted'");
            stmt.setString(1, _type);

            ResultSet rs = stmt.executeQuery();

            List userList = new ArrayList();

            while (rs.next()) {
                users = new Users();

                users.setUid(rs.getString("staff_id"));
                users.setUserrecordid(rs.getString("sys_id"));
                users.setFullName(rs.getString("staff_name"));
                users.setStatus(rs.getString("staff_status"));
                users.setSex(rs.getString("staff_gender"));

                userList.add(users);
            }
          con.close();
                rs.close();
            return userList;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "Users_Surgery_Get_All", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            String Users_Surgery_Get_Profile_Status(String sys_id)
            throws SQLException {

        try {
            Connection con;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT staff_status from surgery_staff where sys_id=?");
            stmt.setString(1, sys_id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String _status = rs.getString("staff_status");
                con.close();
                return _status;
            }
           con.close();
                rs.close();
            return null;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "Users_Surgery_Get_Profile_Status", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            boolean Users_Surgery_Add_Profile(String sys_id, String staff_id, String staff_name, String staff_gender, String staff_status, String staff_type)
            throws SQLException {

        try {
            Connection con;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("insert into surgery_staff(sys_id,staff_id,staff_name,staff_gender,staff_status,staff_type) values(?,?,?,?,?,?)");

            ps.setString(1, sys_id);
            ps.setString(2, staff_id);
            ps.setString(3, staff_name);
            ps.setString(4, staff_gender);
            ps.setString(5, staff_status);
            ps.setString(6, staff_type);

            ps.executeUpdate();
            con.close();
            ps.close();

            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "Users_Surgery_Add_Profile", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            boolean Users_Surgery_Update_Profile(String staff_status, String sys_id)
            throws SQLException {

        try {
            Connection con;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("update surgery_staff set staff_status=? where sys_id=?");

            ps.setString(1, staff_status);
            ps.setString(2, sys_id);

            ps.executeUpdate();
            con.close();
            ps.close();

            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "Users_Surgery_Update_Profile", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            void UpdatedaysoutofStock()
            throws SQLException {

        try {
            Connection con;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement(" INSERT INTO days_outof_stock(drug_id,qty) select  d.drug_id , (((IFNULL(( SUM(pur.units_received) ),0))+ IFNULL((select SUM(dm.qty) from returnfromdemander dm left join drug_purchase pur on  pur.purchase_id=dm.purchase_id where pur.drug_id=d.drug_id),0)+ IFNULL((select SUM(lkg.qty) from drugstore_adjustments lkg  left join drug_purchase pur on  pur.purchase_id=lkg.purchase_id where pur.drug_id=d.drug_id  AND lkg.adjustmentStatus='Postive'),0) )-(IFNULL((select SUM(lkg.qty) from drugstore_adjustments lkg left join drug_purchase pur on pur.purchase_id=lkg.purchase_id where pur.drug_id=d.drug_id   AND lkg.adjustmentStatus='Negative'),0)+ IFNULL((select SUM(dff.qty) from disposing_off dff left join drug_purchase pur on pur.purchase_id=dff.purchase_id where pur.drug_id=d.drug_id),0) +IFNULL((select SUM(sr.qty) from supplier_returneitems sr left join drug_purchase pur on pur.purchase_id=sr.purchase_id where  pur.drug_id=d.drug_id) ,0) +IFNULL((select SUM(dis.qty) from drugstoreisuing dis left join drug_purchase pur on pur.purchase_id=dis.purchase_id where  pur.drug_id=d.drug_id) ,0))) as stockqty from drug_list d inner join drug_purchase pur on pur.drug_id=d.drug_id WHERE pur.batch_no IS NOT NULL group by d.drug_id");

            PreparedStatement ps2 = con.prepareStatement("delete from days_outof_stock where qty>0");
            ps.executeUpdate();
            ps2.executeUpdate();
            con.close();
            ps2.close();

        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Users DAO", "UpdatedaysoutofStock", " Message: " + ex.getMessage(), date));
        }
    }

    public static
            boolean Check_if_date_exisits(String curdate)
            throws SQLException {

        try {
            Connection con;
            con=Apache_Connectionpool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) as Count FROM days_outof_stock WHERE substr(record_date,1,10)='" + curdate + "'");

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Integer temp = rs.getInt("Count");
                con.close();
                rs.close();
                if (temp > 0) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
               con.close();
                rs.close();
                return false;
            }
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new managedModal.Error("Check_if_date_exsists", "Check if", " Message: " + ex.getMessage(), date));
            return false;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
