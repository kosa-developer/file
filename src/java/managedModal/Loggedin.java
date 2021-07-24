package managedModal;

import java.io.Serializable;

public class Loggedin
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private String user_id;
    private String fullname;
    private String username;
    private String gender;
    private String status;
    private String department;
    private String role;
    private String current_password;
    private String new_password;
    private String new_password_retyped;
    private String level;

    public String getUser_id() {
        return this.user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCurrent_password() {
        return this.current_password;
    }

    public void setCurrent_password(String current_password) {
        this.current_password = current_password;
    }

    public String getNew_password() {
        return this.new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getNew_password_retyped() {
        return this.new_password_retyped;
    }

    public void setNew_password_retyped(String new_password_retyped) {
        this.new_password_retyped = new_password_retyped;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Loggedin() {
    }

    public Loggedin(String user_id, String fullname, String username, String gender, String status, String department, String role, String current_password, String new_password, String new_password_retyped,String level) {
        this.user_id = user_id;
        this.fullname = fullname;
        this.username = username;
        this.gender = gender;
        this.status = status;
        this.department = department;
        this.role = role;
        this.current_password = current_password;
        this.new_password = new_password;
        this.new_password_retyped = new_password_retyped;
        this.level=level;
    }
}
