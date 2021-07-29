package managedBean;

import managedDao.DepartmentDAO;
import managedDao.DynamicFunctionsDAO;
import managedDao.LaboratoryDAO;
import managedDao.RolesDAO;
import managedDao.UsersDAO;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import managedModal.Department;
import managedModal.DynamicFunctionModel;
import managedModal.Roles;
import managedModal.Staff;
import managedModal.Users;
import managedModal.TestCategory;
import managedModal.TestName;
import managedModal.SpecificTest;
import managedModal.SubTest;
import managedModal.ExpectedResult;
import sun.misc.BASE64Encoder;

@ManagedBean(name = "adminBean")
@SessionScoped
public class AdminBean
        implements Serializable {

    private String role;
    private String department;
    private String uid;
    private String fullName;
    private String userName;
    private String password;
    private String new_password;
    private String sex;
    private Integer rid;
    private Integer selecteduid;
    private Integer did;
    private String level;
    private String status;
    private String searchid;
    private String userrecordid;
    private Staff staff;
    private List<Department> departmentlist;
    private List<DynamicFunctionModel> privillage_list;
    private Department selectedDepartment;
    private Users selectedUser;
    private String departmen_name;
    private String department_desc;
    private boolean edit_flag = false;
    private Integer test_category_id;
    private String test_category;
    private Integer test_name_id;
    private String test_name;
    private Integer specific_test_id;
    private String specific_test;
    private Integer sub_test_id;
    private String sub_test;
    private Integer testprice;
    private String expected_result;
    private List<TestCategory> test_categories;
    private List<TestName> test_names;
    private List<SpecificTest> specific_tests;
    private List<SubTest> sub_tests;
    private List<ExpectedResult> expected_results;
    private String repassword;
    private boolean show_sel = true;
    private boolean cons_opd = false;
    private boolean cons_ipd = false;
    private String system_update;

    private String surgery_staff_id, surgery_staff_name, surgery_staff_status, surgery_staff_gender, new_department, new_department_desc;

    public AdminBean() {
        this.staff = new Staff("");
        try {
            this.system_update = DynamicFunctionsDAO.dynamicgetsingle_variable_open("Notification", "system_notfications", "id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.test_categories=admin_get_test_categories();
    }

    public String getNew_department() {
        return new_department;
    }

    public void setNew_department(String new_department) {
        this.new_department = new_department;
    }

    public String getNew_department_desc() {
        return new_department_desc;
    }

    public String getRepassword() {
        return repassword;
    }

    public List<DynamicFunctionModel> getPrivillage_list() {
        return privillage_list;
    }

    public void setPrivillage_list(List<DynamicFunctionModel> privillage_list) {
        this.privillage_list = privillage_list;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setNew_department_desc(String new_department_desc) {
        this.new_department_desc = new_department_desc;
    }

    public Users getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Users selectedUser) {
        this.selectedUser = selectedUser;
    }

    public Integer getTestprice() {
        return testprice;
    }

    public void setTestprice(Integer testprice) {
        this.testprice = testprice;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public boolean isCons_opd() {
        return cons_opd;
    }

    public void setCons_opd(boolean cons_opd) {
        this.cons_opd = cons_opd;
    }

    public boolean isCons_ipd() {
        return cons_ipd;
    }

    public void setCons_ipd(boolean cons_ipd) {
        this.cons_ipd = cons_ipd;
    }

    public boolean isShow_sel() {
        return show_sel;
    }

    public void setShow_sel(boolean show_sel) {
        this.show_sel = show_sel;
    }

    public List<TestCategory> getTest_categories() {
        return test_categories;
    }

    public void setTest_categories(List<TestCategory> test_categories) {
        this.test_categories = test_categories;
    }

    public List<TestName> getTest_names() {
        return test_names;
    }

    public void setTest_names(List<TestName> test_names) {
        this.test_names = test_names;
    }

    public List<SpecificTest> getSpecific_tests() {
        return specific_tests;
    }

    public void setSpecific_tests(List<SpecificTest> specific_tests) {
        this.specific_tests = specific_tests;
    }

    public List<SubTest> getSub_tests() {
        return sub_tests;
    }

    public void setSub_tests(List<SubTest> sub_tests) {
        this.sub_tests = sub_tests;
    }

    public List<ExpectedResult> getExpected_results() {
        return expected_results;
    }

    public void setExpected_results(List<ExpectedResult> expected_results) {
        this.expected_results = expected_results;
    }

    public Integer getTest_category_id() {
        return test_category_id;
    }

    public void setTest_category_id(Integer test_category_id) {
        this.test_category_id = test_category_id;
    }

    public String getTest_category() {
        return test_category;
    }

    public void setTest_category(String test_category) {
        this.test_category = test_category;
    }

    public Integer getTest_name_id() {
        return test_name_id;
    }

    public void setTest_name_id(Integer test_name_id) {
        this.test_name_id = test_name_id;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public Integer getSpecific_test_id() {
        return specific_test_id;
    }

    public void setSpecific_test_id(Integer specific_test_id) {
        this.specific_test_id = specific_test_id;
    }

    public String getSpecific_test() {
        return specific_test;
    }

    public void setSpecific_test(String specific_test) {
        this.specific_test = specific_test;
    }

    public Integer getSub_test_id() {
        return sub_test_id;
    }

    public void setSub_test_id(Integer sub_test_id) {
        this.sub_test_id = sub_test_id;
    }

    public String getSub_test() {
        return sub_test;
    }

    public void setSub_test(String sub_test) {
        this.sub_test = sub_test;
    }

    public String getExpected_result() {
        return expected_result;
    }

    public void setExpected_result(String expected_result) {
        this.expected_result = expected_result;
    }

    public boolean isEdit_flag() {
        return this.edit_flag;
    }

    public void setEdit_flag(boolean edit_flag) {
        this.edit_flag = edit_flag;
    }

    public String getDepartmen_name() {
        return this.departmen_name;
    }

    public void setDepartmen_name(String departmen_name) {
        this.departmen_name = departmen_name;
    }

    public String getDepartment_desc() {
        return this.department_desc;
    }

    public void setDepartment_desc(String department_desc) {
        this.department_desc = department_desc;
    }

    public Department getSelectedDepartment() {
        return this.selectedDepartment;
    }

    public void setSelectedDepartment(Department selectedDepartment) {
        this.selectedDepartment = selectedDepartment;
    }

    public List<Department> getDepartmentlist() {
        return this.departmentlist;
    }

    public void setDepartmentlist(List<Department> departmentlist) {
        this.departmentlist = departmentlist;
    }

    public Integer getSelecteduid() {
        return selecteduid;
    }

    public void setSelecteduid(Integer selecteduid) {
        this.selecteduid = selecteduid;
    }

    public Staff getStaff() {
        return this.staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSearchid() {
        return this.searchid;
    }

    public void setSearchid(String searchid) {
        this.searchid = searchid;
    }

    public String getUserrecordid() {
        return this.userrecordid;
    }

    public void setUserrecordid(String userrecordid) {
        this.userrecordid = userrecordid;
    }

    public String getSurgery_staff_id() {
        return surgery_staff_id;
    }

    public void setSurgery_staff_id(String surgery_staff_id) {
        this.surgery_staff_id = surgery_staff_id;
    }

    public String getSurgery_staff_name() {
        return surgery_staff_name;
    }

    public void setSurgery_staff_name(String surgery_staff_name) {
        this.surgery_staff_name = surgery_staff_name;
    }

    public String getSurgery_staff_status() {
        return surgery_staff_status;
    }

    public void setSurgery_staff_status(String surgery_staff_status) {
        this.surgery_staff_status = surgery_staff_status;
    }

    public String getSurgery_staff_gender() {
        return surgery_staff_gender;
    }

    public void setSurgery_staff_gender(String surgery_staff_gender) {
        this.surgery_staff_gender = surgery_staff_gender;
    }

    public String getSystem_update() {
        return system_update;
    }

    public void setSystem_update(String system_update) {
        this.system_update = system_update;
    }

    public List<Roles> getroles() {
        try {
            return RolesDAO.listAll();
        } catch (SQLException ex) {
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Department> alldepartments() {
        try {
            return DepartmentDAO.getAllDepartments();
        } catch (SQLException ex) {
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void show_options() {
        try {

            if (!did.equals(0)) {
                if (DepartmentDAO.getDepartments(did).getDepartmentName().equalsIgnoreCase("Laboratory") || DepartmentDAO.getDepartments(did).getDepartmentName().equalsIgnoreCase("Drug Store")) {
                    show_sel = false;

                } else {
                    show_sel = true;
                    this.level = "LOCAL USER";
                }
            } else {
                show_sel = true;
                this.level = "LOCAL USER";
            }

        } catch (Exception ex) {
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
            show_sel = false;
        }
    }

    public boolean enable_options() {
        try {
            return show_sel;
        } catch (Exception ex) {
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Roles> allroles() {
        try {
            return RolesDAO.listAll();
        } catch (SQLException ex) {
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Users> admin_get_all_users() {
        try {
            return UsersDAO.Users_Get_All();
        } catch (SQLException ex) {
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void retrieveuser() {
        try {
            Users user = UsersDAO.Users_Getuser(this.searchid);
            if (user != null) {
                this.userrecordid = user.getUserrecordid();
                this.uid = user.getUid();
                this.fullName = user.getFullName();
                this.userName = user.getUserName();
                this.password = user.getPassword();
                this.sex = user.getSex();
                this.rid = user.getRid();
                this.did = user.getDid();
                this.status = user.getStatus();
            } else {
                this.userrecordid = null;
                this.uid = null;
                this.fullName = null;
                this.userName = null;
                this.password = null;
                this.sex = null;
                this.rid = null;
                this.did = null;
                this.status = null;

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No Record Found", "Failure"));
            }
        } catch (Exception ex) {
            System.err.println("Error Message: retrieveuser: " + ex.getMessage());
        }
    }

    public String admin_add_user() {
        try {
            if (("".equals(this.uid)) || ("".equals(this.fullName)) || ("".equals(this.userName)) || ("".equals(this.password)) || ("".equals(this.sex)) || (this.did == null) || ("".equals(this.status))) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Provide All Values", "Failure"));
                return null;
            } else if (UsersDAO.User_Exists(this.userName, this.uid)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Staff Already Exists In The System.", "Failure"));
                return null;
            } else {
                UUID idOne = UUID.randomUUID();

                if (UsersDAO.User_Exists(userName)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Username Already Exists.", "Failure"));
                    return null;
                } else {
                    if (DepartmentDAO.getDepartments(did).getDepartmentName().equalsIgnoreCase("Consultant")) {

                        if (cons_ipd == false && cons_ipd == false) {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Please Choose One Of The Consultant Options.Thank You.", "Failure"));
                            return null;
                        } else {
                            if (UsersDAO.Users_Add_UserAccount(String.valueOf(idOne), this.uid, this.fullName, this.userName, encryptpass(this.password), this.sex, this.rid, this.did, this.status)) {
                                UsersDAO.Consultant_Add_Access(uid, cons_opd, cons_ipd);
                                clear();
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Staff Added Successfully.", "Successful"));
                                return "ViewUsers";
                            } else {
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Transaction Failure.Please Contact Your System Administrator. Thank You.", "Failure"));
                                return null;
                            }
                        }
                    } else {
                        if (UsersDAO.Users_Add_UserAccount(String.valueOf(idOne), this.uid, this.fullName, this.userName, encryptpass(this.password), this.sex, this.rid, this.did, this.status)) {
                            clear();
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Staff Added Successfully.", "Successful"));
                            return "ViewUsers";
                        } else {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Transaction Failure.Please Contact Your System Administrator. Thank You.", "Failure"));
                            return null;
                        }
                    }
                }

            }
        } catch (Exception ex) {
            System.err.println("Error Message: admin_add_user: " + ex.getMessage());
            return null;
        }
    }

    private void clear() {
        try {
            this.fullName = "";
            this.uid = "";
            this.userName = "";
            this.password = "";
            this.cons_opd = false;
            this.cons_ipd = false;
        } catch (Exception ex) {
            System.err.println("Error Message: " + ex.getMessage());
        }
    }

    private String encryptpass(String pwd) {
        String dataToEncrypt = pwd;
        String encryptionKey = "healthsolution";
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes(), "Blowfish");

        //String encryptedData = "";
        try {
            Cipher cipher = Cipher.getInstance("Blowfish");

            cipher.init(1, key);

            byte[] encryptedBytes = cipher.doFinal(dataToEncrypt.getBytes("UTF-8"));

            return new BASE64Encoder().encode(encryptedBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Department> view_department() {
        try {
            return DepartmentDAO.getAllDepartments();
        } catch (Exception ex) {
            System.out.println("Error Message: view_department: " + ex.getMessage());
            return null;
        }
    }

    public void add_department() {
        try {

            if (departmen_name.isEmpty() || department_desc.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Provide Needed Information.Thank You", "Failure"));
            } else {
                if (DepartmentDAO.Department_Find(this.departmen_name)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Department Already Exists", "Failure"));
                } else {

                    if (DepartmentDAO.Department_Add(this.departmen_name, this.department_desc)) {
                        this.departmen_name = null;
                        this.department_desc = null;
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Department Added Successfully", "Success"));
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error. Please Contact Your Administrator", "Failure"));
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Error Message: add_department: " + ex.getMessage());
        }
    }

    public String admin_go_to(String _page) {
        try {
            return _page;
        } catch (Exception ex) {
            System.out.println("Error Message: admin_go_to: " + ex.getMessage());
            return "";
        }
    }

    public void admin_update_department() {
        try {
            if (this.selectedDepartment.getDid() != null) {

                if (new_department.isEmpty() || new_department_desc.isEmpty()) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Department Name OR Description Missing.Please provide all information.Thank You.", "Failure"));
                } else {
                    if (DepartmentDAO.Department_Update(this.selectedDepartment.getDid(), new_department, new_department_desc)) {
                        new_department = "";
                        new_department_desc = "";
                        this.selectedDepartment = new Department();
                        view_department();
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Department Updated Successfully", "Success"));
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error. Try Again", "Failure"));
                    }
                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "NULL Values Provided", "Failure"));
            }
        } catch (Exception ex) {
            System.out.println("Error Message: admin_update_department: " + ex.getMessage());
        }
    }

    public void delete_department() {
        try {
            if (this.selectedDepartment.getDid() != null) {
                if (DepartmentDAO.Department_Delete(this.selectedDepartment.getDid())) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Department Deleted Successfully", "Success"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error. Try Again", "Failure"));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "NULL Values Provided", "Failure"));
            }
        } catch (Exception ex) {
            System.err.println("Error Message: delete_department: " + ex.getMessage());
        }
    }

    public void admin_edit_user() {
        try {
            this.edit_flag = true;
            Users user = new Users();
            user = UsersDAO.Users_Get_User(this.uid);
            this.fullName = user.getFullName();
            this.sex = user.getSex();
            this.did = user.getDid();
            this.rid = user.getRid();
            this.userName = user.getUserName();
            this.status = user.getStatus();
        } catch (Exception ex) {
            System.out.println("admin_edit_user" + ex.getMessage());
        }
    }

    public void admin_update_user() {
        try {
            if (this.edit_flag) {
                if (("".equals(this.fullName)) || ("".equals(this.userName)) || ("".equals(this.password))) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.All Fields Are Mandatory", "Failure"));
                } else if (UsersDAO.Users_Update_User_Account(this.uid, this.fullName, this.userName, encryptpass(this.password), this.sex, this.rid, this.did, this.status)) {
                    clear();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Updated User", "Success"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error. Try Again", "Failure"));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Update Failed: Because Initial Values To Be Queried By System", "Failure"));
            }
        } catch (Exception ex) {
            System.out.println("admin_update_user: " + ex.getMessage());
        }
    }

    public void admin_delete_user() {
        try {
            if (selectedUser.getUid().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "There is NO USER selected.", "Failure"));
            } else {
                if (DynamicFunctionsDAO.delete_dataTablestatic("users", "UID", selectedUser.getUid().toString())) {
                    clear();
                    selectedUser = new Users();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Deleted User", "Success"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Check Error Log.", "Failure"));
                }
            }
        } catch (Exception ex) {
            System.out.println("admin_delete_user: " + ex.getMessage());
        }
    }

    public void admin_change_user_pwd() {
        try {
            if (selectedUser.getUid().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "There is NO USER selected.", "Failure"));
            } else {
                if (UsersDAO.Users_Update_Pwd(selectedUser.getUid(), encryptpass(this.new_password))) {
                    clear();
                    selectedUser = new Users();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Changed User Password", "Success"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Check Error Log.", "Failure"));
                }
            }
        } catch (Exception ex) {
            System.out.println("admin_change_user_pwd: " + ex.getMessage());
        }
    }

    public void admin_deactivate_user() {
        try {

            if (selectedUser.getUid().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "There is NO USER selected.", "Failure"));
            } else {
                if (UsersDAO.accountState("Deactivated", selectedUser.getUid())) {
                    selectedUser = new Users();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "User Account Deactivated Successfully", "Success"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Please contact Your Systems Administrator.", "Failure"));
                }
            }
        } catch (Exception ex) {
            System.out.println("admin_change_user_pwd: " + ex.getMessage());
        }
    }

    public void admin_set_user(String uid, String _fname) {
        try {
            this.uid = uid;
            this.fullName = _fname;
            this.edit_flag = true;
            System.out.println("----=========== here ===============----");
        } catch (Exception ex) {
            System.out.println("admin_set_user: " + ex.getMessage());
        }
    }

    public void admin_add_test_category() {
        try {
            if (LaboratoryDAO.Laboratory_Add_Test_Category(test_category)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Success"));
                test_category = "";
                admin_get_test_categories();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Failure", "Failure"));
            }
        } catch (Exception ex) {
            System.out.println("Exception In Admin Bean: admin_add_test_category: " + ex.getMessage());
        }
    }

    public void admin_add_test_name() {
        try {
            if (LaboratoryDAO.Laboratory_Add_Test_Name(test_category_id, test_name,this.testprice)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Success"));
                this.test_name="";
                this.test_category_id=null;
                this.testprice=null;
                
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Failure", "Failure"));
            }
        } catch (Exception ex) {
            System.out.println("Exception In Admin Bean: admin_add_test_name: " + ex.getMessage());
        }
    }

    public void admin_add_specific_test() {
        try {
            if (LaboratoryDAO.Laboratory_Add_Specific_Test(test_name_id, specific_test)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Success"));
                specific_test = "";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Failure", "Failure"));
            }
        } catch (Exception ex) {
            System.out.println("Exception In Admin Bean: admin_add_specific_test: " + ex.getMessage());
        }
    }

    public void admin_add_sub_test() {
        try {
            if (LaboratoryDAO.Laboratory_Add_Sub_Test(specific_test_id, sub_test)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Success"));
                sub_test = "";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Failure", "Failure"));
            }
        } catch (Exception ex) {
            System.out.println("Exception In Admin Bean: admin_add_sub_test: " + ex.getMessage());
        }
    }

    public void admin_add_expected_result() {
        try {
             
            if (LaboratoryDAO.Laboratory_Add_Expected_Result(sub_test_id, expected_result)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Success"));
                expected_result = "";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Failure", "Failure"));
            }
        } catch (Exception ex) {
            System.out.println("Exception In Admin Bean: admin_add_expected_result: " + ex.getMessage());
        }
    }

    public List<TestCategory> admin_get_test_categories() {
        try {
            test_categories = LaboratoryDAO.Laboratory_Get_Test_Category();
            return test_categories;
        } catch (Exception ex) {
            System.out.println("Exception In Admin Bean: admin_get_test_categories: " + ex.getMessage());
            return null;
        }
    }

    public List<TestName> admin_get_test_names() {
        try {
            test_names = LaboratoryDAO.Laboratory_Get_Test_Names();
            return test_names;
        } catch (Exception ex) {
            System.out.println("Exception In Admin Bean: admin_get_test_names: " + ex.getMessage());
            return null;
        }
    }

    public List<SpecificTest> admin_get_specific_tests() {
        try {
            specific_tests = LaboratoryDAO.Laboratory_Get_Specific_Tests(test_name_id);
            return specific_tests;
        } catch (Exception ex) {
            System.out.println("Exception In Admin Bean: admin_get_specific_tests: " + ex.getMessage());
            return null;
        }
    }

    public List<SubTest> admin_get_sub_tests() {
        try {
            sub_tests = LaboratoryDAO.Laboratory_Get_Sub_Tests(specific_test_id);
            return sub_tests;
        } catch (Exception ex) {
            System.out.println("Exception In Admin Bean: admin_get_sub_tests: " + ex.getMessage());
            return null;
        }
    }

    public List<ExpectedResult> admin_get_expected_results() {
        try {
            expected_results = LaboratoryDAO.Laboratory_Get_Expected_Results();
            return expected_results;
        } catch (Exception ex) {
            System.out.println("Exception In Admin Bean: admin_get_expected_results :" + ex.getMessage());
            return null;
        }
    }

    public List<Users> admin_surgery_users(String _type) {
        try {
            return UsersDAO.Users_Surgery_Get_All(_type);

        } catch (SQLException ex) {
            Logger.getLogger(AdminBean.class
                    .getName()).log(Level.SEVERE, null, ex);

            return null;
        }
    }

    public void admin_surgery_add_user(String _type) {
        try {
            if (UsersDAO.Users_Surgery_Add_Profile(surgery_staff_id, surgery_staff_id, surgery_staff_name, surgery_staff_gender, "Active", _type)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Staff Addition  Succeded", "Success"));
                surgery_staff_id = "";
                surgery_staff_name = "";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Staff Addition Failed", "Failure"));

            }
        } catch (Exception ex) {
            Logger.getLogger(AdminBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String admin_surgery_status(String sys_id) {
        try {
            String _status = UsersDAO.Users_Surgery_Get_Profile_Status(sys_id);

            if (_status.equalsIgnoreCase("Active")) {
                return "Deactivate";
            } else if (_status.equalsIgnoreCase("Deactive")) {
                return "Activate";
            } else if (_status.equals(null)) {
                return "No Status";
            } else {
                return null;

            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminBean.class
                    .getName()).log(Level.SEVERE, null, ex);

            return null;
        }

    }

    public void admin_surgery_change_status(String sys_id) {
        try {
            String _status = UsersDAO.Users_Surgery_Get_Profile_Status(sys_id);

            if (_status.equalsIgnoreCase("Active")) {
                UsersDAO.Users_Surgery_Update_Profile("Deactive", sys_id);
            } else if (_status.equalsIgnoreCase("Deactive")) {
                UsersDAO.Users_Surgery_Update_Profile("Active", sys_id);

            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void admin_surgery_soft_del(String sys_id) {
        try {
            if (UsersDAO.Users_Surgery_Update_Profile("Deleted", sys_id)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Staff Deleted Successfully", "Success"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Staff Deletion Failed", "Failure"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void submit_updates_notification() {

        try {

            String[][] field = {{"Notification"}, {this.system_update}};

            if (DynamicFunctionsDAO.Check_if_exsists(this.system_update, "system_notfications", "Notification")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data already exisits", "Failure"));
            } else {

                if (DynamicFunctionsDAO.Insert("system_notfications", field)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data entered successfully", "Success"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "error", "Success"));

                }

            }

        } catch (Exception ex) {
            System.out.println("Exception In Admin Bean: admin_add_system_notfications: " + ex.getMessage());
        }
    }

    public void registeruser() {
        try {
            if (!this.password.equals(this.repassword)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password dont match:  ", null));

            } else {

                if (!DynamicFunctionsDAO.returnexisitDataID("users", "UserName", this.userName).isEmpty()) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "User already exisits try another username:  ", null));
                    cleardata();
                } else {

                    Integer uid_ = Integer.parseInt((DynamicFunctionsDAO.returnlastID("users", "UID").equals("") || DynamicFunctionsDAO.returnlastID("users", "UID") == null) ? "0" : DynamicFunctionsDAO.returnlastID("users", "UID")) + 1;

                    String[][] users = {{"Record_Id", "UID", "Status", "FullName", "UserName", "RID", "DID", "Password", "Sex", "Level"},
                    {UUID.randomUUID().toString(), uid_.toString(), "Active", this.fullName, this.userName, this.rid.toString(), this.did.toString(), encryptpass(this.password), this.sex, this.level}};
                    if (DynamicFunctionsDAO.Insert("users", users)) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "User registered successfully ", null));

                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error occured contact Administrator", null));

                    }
                }
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error occured contact Administrator" + e, null));

        }
    }

    public void edituser() {
        if (selectedUser.getFullName().isEmpty() || selectedUser.getDid() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Both Name and Department must be filled", "Error"));
        } else {
            String[][] userslist = {{"FullName", "DID"},
            {selectedUser.getFullName(), selectedUser.getDid().toString()}};

            try {
                if (DynamicFunctionsDAO.Edit("users", userslist, "UID", selectedUser.getUid().toString())) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data updated successfully", "Successful"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "select supplier and try again", "Successful"));
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error: select a supplier and try again", "Successful"));

            }
        }
    }

    public void cleardata() {
        this.fullName = "";
        this.sex = "";
        this.did = null;
        this.rid = null;
        this.level = "";
        this.password = "";
        this.repassword = "";
        this.userName = "";
    }

    public void viewprivillages(Integer id) {
        this.selecteduid = id;
        try {
            this.privillage_list = DynamicFunctionsDAO.dropDowns("privillages", "Id", "Privillage", "UID", this.selecteduid.toString());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error occured, contact the admininistrator", "Error"));
        }

    }

    public void saveprivillage(String priv) {
        try {
            if (priv.equals("") || priv == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Select privillage:  ", null));

            } else {

                String[][] privillagelist = {{"UID", "Privillage"}, {this.selecteduid.toString(), priv}};

                if (DynamicFunctionsDAO.Check_if_exsists("privillages", privillagelist)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Duplicate privilllage:  ", null));
                    cleardata();
                } else {

                    if (DynamicFunctionsDAO.Insert("privillages", privillagelist)) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Privillage saved ", null));
                        viewprivillages(this.selecteduid);
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error occured contact Administrator", null));

                    }
                }
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error occured contact Administrator" + e, null));

        }
    }

    public void deletprivillage(String id) {
     
    
        try {
            if (id.equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error contact administrator.", "Failure"));
            } else {
                if (DynamicFunctionsDAO.delete_dataTablestatic("privillages", "Id", id)) {
                   viewprivillages(this.selecteduid);
                  } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Check Error Log.", "Failure"));
                }
            }
        } catch (Exception ex) {
             }
    
    //--------------------------------------------------------------------------------------------
}}
