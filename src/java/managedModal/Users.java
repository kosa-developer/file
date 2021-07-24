package managedModal;

import java.io.Serializable;

public class Users
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private String uid;
    private String fullName;
    private String userName;
    private String password;
    private String sex;
    private Integer rid;
    private Integer did;
    private String status;
    private String userrecordid;
    private String department_name;
    private String role_name;
    private Integer userid;
    private String userrights;

    public Users() {
    }

    public Users(String uid, String fullName, String userName, String sex, String status, String department_name) {
        this.uid = uid;
        this.fullName = fullName;
        this.userName = userName;
        this.sex = sex;
        this.status = status;
        this.department_name = department_name;
    }

    public Users(String uid, String fullName, String userName, String sex, String status, String department_name, String role_name) {
        this.uid = uid;
        this.fullName = fullName;
        this.userName = userName;
        this.sex = sex;
        this.status = status;
        this.department_name = department_name;
        this.role_name = role_name;
    }

    public Users(String uid, String fullName, String userName, String password, String sex, Integer rid, Integer did, String status) {
        this.uid = uid;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.sex = sex;
        this.rid = rid;
        this.did = did;
        this.status = status;
    }

    public Users(String uid, String fullName, String userName, String sex, String status) {
        this.uid = uid;
        this.fullName = fullName;
        this.userName = userName;
        this.sex = sex;
        this.status = status;
    }
    
  

    public Users(String uid, String fullName, String userName, String password, String sex, Integer rid, Integer did, String status, String userrecordid) {
        this.uid = uid;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.sex = sex;
        this.rid = rid;
        this.did = did;
        this.status = status;
        this.userrecordid = userrecordid;
    }


    public Users(String uid, String fullName) {
        this.uid = uid;
        this.fullName = fullName;
    }

    public String getDepartment_name() {
        return this.department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getRole_name() {
        return this.role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Users(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getRid() {
        return this.rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getDid() {
        return this.did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getUserrecordid() {
        return this.userrecordid;
    }

    public void setUserrecordid(String userrecordid) {
        this.userrecordid = userrecordid;
    }

    public
    Integer getUserid() {
        return userid;
    }

    public
    void setUserid(Integer userid) {
        this.userid = userid;
    }

    public
    void setUserrights(String userrights) {
        this.userrights = userrights;
    }

    public
    String getUserrights() {
        return userrights;
    }

  
    
       public Users(Integer userid,String userrights ,String uid, String fullName) {
        this.uid = uid;
        this.fullName = fullName;
        this.userid=userid;
        this.userrights=userrights;
    }
}
