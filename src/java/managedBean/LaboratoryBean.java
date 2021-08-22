package managedBean;

import managedDao.ConsultantDAO;
import managedDao.DynamicFunctionsDAO;
import managedDao.LaboratoryDAO;
import managedDao.ReceptionDAO;
//import managedDao.WardDAO;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import managedModal.ExpectedResult;
import managedModal.LabTest;
import managedModal.LabTestResults;
import managedModal.Notes;
import managedModal.Patient;
import managedModal.ReceptionInfo;
import managedModal.ReceptionTask;
import managedModal.SpecificTest;
import managedModal.SubTest;
import managedModal.TestCategory;
import managedModal.TestName;
import managedModal.testresults;

@ManagedBean(name = "laboratoryBean")
@SessionScoped
public class LaboratoryBean
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private String test_name;
    private String test_normal_value;
    private Integer selected_category_id;
    private Patient patient_view;
    private ReceptionInfo reception_info_view;
    private String task_id;
    private boolean assigned = false;
    private List<LabTest> tests_requested;
    private List<testresults> tablesubtest;
    private List<LabTest> tests_samples_taken;
    private LabTest selected_sample_taken;
    private LabTest selected_lab_test;
    private LabTest[] selected_lab_tests;
    private String sample_taken_msg;
    private String note;
    private String test_sample;
    private String expected_result_range;
    private String task_urgency;
    private Date report_date;
    private List<TestCategory> test_categories;
    private List<TestName> test_names;
    private List<SpecificTest> specific_tests;
    private List<SubTest> sub_tests;
    private List<ExpectedResult> expected_results;
    private Integer test_name_id;
    private Integer specific_test_id;
    private Integer confirm_specific_test_id;
    private Integer confirm_sub_test_id;
    private Integer sub_test_id;
    private List<Notes> clinical_notes;
    private String lab_Id;
    private boolean test_started;
    private String forward_to;
    private boolean result_select;
    private boolean result_entry;
    private Integer confirm_result_id;
    private Integer confirm_expected_result_id;
    private String confirm_sample_type;
    private String confirm_otherResults;
    private String confirm_comments;
    private static Date date;
    private String userprivillage;
    private boolean hidedata_normaluser;
    private boolean hidedata_supervisor;
    private boolean hidecountersigned;
    private boolean hidecountersigned_message;

    public boolean isHidecountersigned_message() {
        return hidecountersigned_message;
    }

    public void setHidecountersigned_message(boolean hidecountersigned_message) {
        this.hidecountersigned_message = hidecountersigned_message;
    }

    public boolean isHidecountersigned() {
        return hidecountersigned;
    }

    public void setHidecountersigned(boolean hidecountersigned) {
        this.hidecountersigned = hidecountersigned;
    }

    public void setHidedata_normaluser(boolean hidedata_normaluser) {
        this.hidedata_normaluser = hidedata_normaluser;
    }

    public void setHidedata_supervisor(boolean hidedata_supervisor) {
        this.hidedata_supervisor = hidedata_supervisor;
    }

    public boolean isHidedata_normaluser() {
        return hidedata_normaluser;
    }

    public boolean isHidedata_supervisor() {
        return hidedata_supervisor;
    }

    public String getUserprivillage() {
        return userprivillage;
    }

    public void setUserprivillage(String userprivillage) {
        this.userprivillage = userprivillage;
    }

    public String getConfirm_comments() {
        return confirm_comments;
    }

    public void setConfirm_comments(String confirm_comments) {
        this.confirm_comments = confirm_comments;
    }

    public String getConfirm_otherResults() {
        return confirm_otherResults;
    }

    public void setConfirm_otherResults(String confirm_otherResults) {
        this.confirm_otherResults = confirm_otherResults;
    }

    public String getConfirm_sample_type() {
        return confirm_sample_type;
    }

    public void setConfirm_sample_type(String confirm_sample_type) {
        this.confirm_sample_type = confirm_sample_type;
    }

    public Integer getConfirm_expected_result_id() {
        return confirm_expected_result_id;
    }

    public void setConfirm_expected_result_id(Integer confirm_expected_result_id) {
        this.confirm_expected_result_id = confirm_expected_result_id;
    }

    public Integer getConfirm_result_id() {
        return confirm_result_id;
    }

    public void setConfirm_result_id(Integer confirm_result_id) {
        this.confirm_result_id = confirm_result_id;
    }

    public Integer getConfirm_sub_test_id() {
        return confirm_sub_test_id;
    }

    public void setConfirm_sub_test_id(Integer confirm_sub_test_id) {
        this.confirm_sub_test_id = confirm_sub_test_id;
    }

    public boolean isResult_select() {
        return result_select;
    }

    public boolean isResult_entry() {
        return result_entry;
    }

    public void setResult_select(boolean result_select) {
        this.result_select = result_select;

    }

    public void setResult_entry(boolean result_entry) {
        this.result_entry = result_entry;
    }

    public String getExpected_result_range() {
        return expected_result_range;
    }

    public void setExpected_result_range(String expected_result_range) {
        this.expected_result_range = expected_result_range;
    }

    public String getForward_to() {
        return forward_to;
    }

    public void setForward_to(String forward_to) {
        this.forward_to = forward_to;
    }

    public String getSample_taken_msg() {
        return sample_taken_msg;
    }

    public void setSample_taken_msg(String sample_taken_msg) {
        this.sample_taken_msg = sample_taken_msg;
    }

    public List<LabTest> getTests_samples_taken() {
        return tests_samples_taken;
    }

    public void setTests_samples_taken(List<LabTest> tests_samples_taken) {
        this.tests_samples_taken = tests_samples_taken;
    }

    public LabTest getSelected_sample_taken() {
        return selected_sample_taken;
    }

    public void setSelected_sample_taken(LabTest selected_sample_taken) {
        this.selected_sample_taken = selected_sample_taken;
    }

    public LabTest[] getSelected_lab_tests() {
        return selected_lab_tests;
    }

    public void setSelected_lab_tests(LabTest[] selected_lab_tests) {
        this.selected_lab_tests = selected_lab_tests;
    }

    public boolean isTest_started() {
        return test_started;
    }

    public void setTest_started(boolean test_started) {
        this.test_started = test_started;
    }

    public String getLab_Id() {
        return lab_Id;
    }

    public void setLab_Id(String lab_Id) {
        this.lab_Id = lab_Id;
    }

    public List<Notes> getClinical_notes() {
        return clinical_notes;
    }

    public void setClinical_notes(List<Notes> clinical_notes) {
        this.clinical_notes = clinical_notes;
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

    public Integer getTest_name_id() {
        return test_name_id;
    }

    public void setTest_name_id(Integer test_name_id) {
        this.test_name_id = test_name_id;
    }

    public Integer getSpecific_test_id() {
        return specific_test_id;
    }

    public void setSpecific_test_id(Integer specific_test_id) {
        this.specific_test_id = specific_test_id;
    }

    public void setConfirm_specific_test_id(Integer confirm_specific_test_id) {
        this.confirm_specific_test_id = confirm_specific_test_id;
    }

    public Integer getConfirm_specific_test_id() {
        return confirm_specific_test_id;
    }

    public Integer getSub_test_id() {
        return sub_test_id;
    }

    public void setSub_test_id(Integer sub_test_id) {
        this.sub_test_id = sub_test_id;
    }

    public Date getReport_date() {
        return report_date;
    }

    public void setReport_date(Date report_date) {
        this.report_date = report_date;
    }

    public String getTask_urgency() {
        return task_urgency;
    }

    public void setTask_urgency(String task_urgency) {
        this.task_urgency = task_urgency;
    }

    public String getTest_sample() {
        return test_sample;
    }

    public void setTest_sample(String test_sample) {
        this.test_sample = test_sample;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LabTest getSelected_lab_test() {
        return this.selected_lab_test;
    }

    public void setSelected_lab_test(LabTest selected_lab_test) {
        this.selected_lab_test = selected_lab_test;
    }

    public List<LabTest> getTests_requested() {
        return this.tests_requested;
    }

    public void setTests_requested(List<LabTest> tests_requested) {
        this.tests_requested = tests_requested;
    }

    public String getTask_id() {
        return this.task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public boolean isAssigned() {
        return this.assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public Patient getPatient_view() {
        return this.patient_view;
    }

    public void setPatient_view(Patient patient_view) {
        this.patient_view = patient_view;
    }

    public ReceptionInfo getReception_info_view() {
        return this.reception_info_view;
    }

    public void setReception_info_view(ReceptionInfo reception_info_view) {
        this.reception_info_view = reception_info_view;
    }

    public Integer getSelected_category_id() {
        return this.selected_category_id;
    }

    public void setSelected_category_id(Integer selected_category_id) {
        this.selected_category_id = selected_category_id;
    }

    public String getTest_name() {
        return this.test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public String getTest_normal_value() {
        return this.test_normal_value;
    }

    public void setTest_normal_value(String test_normal_value) {
        this.test_normal_value = test_normal_value;
    }

    public List<testresults> getTablesubtest() {
        return tablesubtest;
    }

    public void setTablesubtest(List<testresults> tablesubtest) {
        this.tablesubtest = tablesubtest;
    }

//constructor laboratoryBean. initialisation of issential variables that activates system behavior in laboratory screen
    public LaboratoryBean() {
        this.task_id = "";
        task_urgency = "";
        this.lab_Id = "";
        this.forward_to = "";
        this.result_entry = false;
        this.result_select = true;
        this.hidedata_supervisor = false;
        this.hidedata_normaluser = true;
        this.hidecountersigned = true;
        this.hidecountersigned_message = false;
    }

    // <editor-fold defaultstate="collapsed" desc="'Method' referred to from reception">        
    public List<TestCategory> laboratory_get_test_categories() {
        try {
            return LaboratoryDAO.Laboratory_Get_Test_Categories();
        } catch (Exception ex) {
            System.err.println("Error Message: " + ex.getMessage());
        }
        return null;
    }

    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="General">        
    public List<ReceptionTask> laboratory_get_pending_tasks(String user_id) {
        try {
            return LaboratoryDAO.Laboratory_Get_Pending(user_id);
        } catch (Exception ex) {
            System.err.println("Error Message: " + ex.getMessage());
        }
        return null;
    }

    public List<ReceptionTask> laboratory_get_completed_tasks(String user_id) {
        try {
            return LaboratoryDAO.Laboratory_Get_Completed(user_id);
        } catch (Exception ex) {
            System.err.println("Error Message: " + ex.getMessage());
        }
        return null;
    }

    /*laboratory get selected task method which returns a screen containing details of a patient to be tested.
    it also returns the privillage of the logged in user to determine which patients to be seen
    
     */
    public String laboratory_get_selected_task(String task_id, String user_id, String lab_id, String forward_to) {

        try {
            this.userprivillage = "LOCAL USER";

            if (this.userprivillage.equalsIgnoreCase("LOCAL USER")) {
                this.hidedata_supervisor = false;
                this.hidedata_normaluser = true;
                String[][] countersignTestarray = {{"Track_Id", "Countersign_status"}, {task_id, "Pending"}};

                if (DynamicFunctionsDAO.Check_if_exsists("lab_tests_requests", countersignTestarray)) {
                    this.hidecountersigned = true;
                    this.hidecountersigned_message = false;
                } else {

                    this.hidecountersigned = false;
                    this.hidecountersigned_message = true;
                }
            } else {
                this.hidedata_supervisor = true;
                this.hidedata_normaluser = false;
                this.hidecountersigned = true;
                this.hidecountersigned_message = false;
            }

            this.task_id = task_id;
            task_urgency = LaboratoryDAO.Laboratory_Task_Get_Urgency(task_id);
            this.lab_Id = lab_id;
            this.forward_to = forward_to;

            this.patient_view = ReceptionDAO.Reception_Retrieve_Patient_Details(task_id);
            this.reception_info_view = ReceptionDAO.Reception_Get_Reception_Info(task_id);
            if (LaboratoryDAO.Laboratory_Task_Lock(task_id, true, user_id)) {
                this.assigned = true;

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "This Task Is Now LOCKED and Assigned To You.", "Successful"));
            } else {
                this.assigned = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "An Error Occurred.Task Could Not Be Assigned To You.", "Failure"));
            }
            return "/pages/lab/details.xhtml";
        } catch (Exception ex) {
            System.err.println("laboratoryBean Error: Method: laboratory_get_selected_task " + ex.getMessage());
        }
        return null;
    }

    public String laboratory_get_selected_task_report(String task_id, String user_id, String lab_id, String forward_to) {

        try {

            this.task_id = task_id;
            task_urgency = LaboratoryDAO.Laboratory_Task_Get_Urgency(task_id);
            this.lab_Id = lab_id;
            this.forward_to = forward_to;

            this.patient_view = ReceptionDAO.Reception_Retrieve_Patient_Details(task_id);
            this.reception_info_view = ReceptionDAO.Reception_Get_Reception_Info(task_id);

            return "/pages/lab/details_report.xhtml";
        } catch (Exception ex) {
            System.err.println("laboratoryBean Error: Method: laboratory_get_selected_task " + ex.getMessage());
        }
        return null;
    }

    /*
    laboratory navigate to method returns the screen where pateint tests and results are recorded from
    it calls a method laboratory_get_requested_tests() which returns tests that has been requested
     */
    public String laboratory_navigate_to() {
        try {
            this.specific_tests = null;
            this.sub_tests = null;
            laboratory_get_requested_tests();
            return "/pages/lab/testrequests.xhtml";
        } catch (Exception ex) {
            System.out.println("Error Message: " + ex.getMessage());
        }
        return null;
    }

    public String laboratory_navigate_to_results() {
        return "/pages/lab/testrequests_report.xhtml";

    }

    public String back() {

        return "/pages/lab/patientvisits.xhtml";

    }

    /*   
    laboratory_navigate_to method with one agument return_val returns the screen containg clinical notes
    it also calls a method laboratory_get_clinical_notes()that returns laboratory clinical notes
     */
//    public String laboratory_navigate_to(String return_val) {
//        try {
//            if (return_val.equals("viewnotes")) {
//                laboratory_get_clinical_notes();
//            }
//            return return_val;
//        } catch (Exception ex) {
//            System.out.println("Error Message: " + ex.getMessage());
//            return null;
//        }
//    }

    /*
    laboratory_unlock_task() method is responsible for unlocking patients to be seen by other users on laboratory screens or other screens
     */
    public String laboratory_unlock_task() {
        try {
            if (LaboratoryDAO.Laboratory_Task_Lock(this.task_id, false, "")) {
                this.assigned = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Task Has Been UNLOCKED.", "Successful"));
                return "/pages/lab/incoming.xhtml";
            } else {
                this.assigned = true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "An Error Occurred.Task Could Not Be UnLocked", "Failure"));
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Error Message" + ex.getMessage());
        }
        return null;
    }

    public String laboratory_unlock_task_report() {
        return "/pages/lab/patientvisits.xhtml";

    }

    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="Submit test samples">           
    public void laboratory_get_requested_tests() {
        try {
            tests_samples_taken = LaboratoryDAO.Laboratory_Get_Tests_Samples_Taken(this.task_id);
            this.tests_requested = LaboratoryDAO.Laboratory_Get_Tests_Requested(this.task_id);
            System.out.println("tests_requested : " + tests_requested.size());

            sample_taken_msg = "";
            if (LaboratoryDAO.Laboratory_Get_Tests_Samples_Taken(this.task_id).size() > 0) {
                sample_taken_msg = "All Test Samples For This Patient Have Been Submitted";
            } else {
                sample_taken_msg = "There Are NO Tests For This Patient.";
            }
        } catch (Exception ex) {
            System.err.println("Error Message: " + ex.getMessage());
        }
    }

    /*
    laboratory_samples_taken() method submits the time and test request to confirm thatsomeone is carryingout the very sample
    it also updates the requested status to sample taken
     */
    public void laboratory_samples_taken() {

        try {

            if (selected_lab_tests.length > 0) {

                for (int i = 0; i < selected_lab_tests.length; i++) {

                    if (LaboratoryDAO.Laboratory_Add_Test_Duration_Start(this.task_id, this.selected_lab_tests[i].getRequest_id())) {
                        LaboratoryDAO.Laboratory_Update_Requested_Test_Status("Sample Taken", this.selected_lab_tests[i].getRequest_id());
                    }
                }
                laboratory_get_requested_tests();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Start Time For Test Samples Saved Successfully", "Success"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "NO Samples Selected", "Failure"));
            }

            /*
             if (selected_lab_tests != null) {
             for (int i = 0; i < selected_lab_tests.length; i++) {
             if (LaboratoryDAO.Laboratory_Add_Test_Duration_Start(this.task_id, this.selected_lab_tests[i].getRequest_id())) {
             LaboratoryDAO.Laboratory_Update_Requested_Test_Status("Sample Taken", this.selected_lab_tests[i].getRequest_id());
             }

             }
             laboratory_get_requested_tests();
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Start Time For Test Samples Saved Successfully", "Success"));
             // dnt 4get to get rid of the datamodel once you hv the full functionality
             }
             */
        } catch (Exception ex) {
            System.err.println("Error in laboratory_samples_taken: " + ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "" + ex.getMessage(), "Success"));

        }

    }

    /*
    laboratory_submitted_test_results() method returns the already submited tests samples on testresult screen for result
    recordings
     */
    public String laboratory_submitted_test_results() {

        try {
            tests_samples_taken = LaboratoryDAO.Laboratory_Get_Tests_Samples_Taken(this.task_id);
            return "/pages/lab/testresults.xhtml";
        } catch (Exception ex) {
            System.err.println("Error in: laboratory_submit_test_results:  " + ex.getMessage());
            return null;
        }

    }

    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="Record & submit test results">            
    public List<SpecificTest> laboratory_get_specific_tests() {

        try {

            if (selected_sample_taken != null) {
                specific_tests = LaboratoryDAO.Laboratory_Get_Specific_Tests(selected_sample_taken.getTest_id());
                return specific_tests;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Exception In Admin Bean:-- laboratory_get_specific_tests: " + ex.getMessage());
            return null;
        }
    }

    /*
    laboratory_get_sub_tests() method returns subtest corresponding to the selected specific test
     */
    public List<SubTest> laboratory_get_sub_tests() {
        try {
            if (specific_test_id != null) {
                sub_tests = LaboratoryDAO.Laboratory_Get_Sub_Tests(specific_test_id);
                return sub_tests;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Exception In Admin Bean: laboratory_get_sub_tests: " + ex.getMessage());
            return null;
        }
    }

    /*
    laboratory_get_expected_results() method returns expected results basing on the selected subtest
     */
    public List<ExpectedResult> laboratory_get_expected_results() {

        try {
            if (sub_test_id != null) {
//                expected_results = LaboratoryDAO.Laboratory_Get_Expected_Results(sub_test_id);
                this.expected_result_range = "";
                List returnedData[] = LaboratoryDAO.Laboratory_Get_Expected_Results(sub_test_id);
                expected_results = returnedData[0];

                expected_result_range(sub_test_id);
                return expected_results;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Exception In Admin Bean: laboratory_get_expected_results: " + ex.getMessage());
            return null;
        }
    }


    /*
    result_units(Integer sub_test_id, String result) method returns test units and result range  basing on age, gender
     */
    public String result_units(Integer sub_test_id, String result) {
        try {
            String agetype, units;
            agetype = "";
            units = "";

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String yy, mm, dd, date;
            int patient_age, patient_age_month, patient_age_days;
            date = format.format(patient_view.getDOB());
            yy = date.substring(0, 4);
            mm = date.substring(5, 7);
            dd = date.substring(8, 10);
            LocalDate l = LocalDate.of(Integer.parseInt(yy), Integer.parseInt(mm), Integer.parseInt(dd)); //specify year, month, date directly
            LocalDate now = LocalDate.now(); //gets localDate
            Period diff = Period.between(l, now); //difference between the dates is calculated

            patient_age = diff.getYears();
            patient_age_month = diff.getMonths();
            patient_age_days = diff.getDays();
            Integer age, month, days;
            age = patient_age;
            month = (age <= 0) ? patient_age_month : 0;
            days = (age <= 0 && month <= 0) ? patient_age_days : 0;

            if (days >= 0 && days <= 14 && month == 0 && age == 0) {
                agetype = "Neonate F/M";
            } else if (days > 14 || month > 0) {
                agetype = "Baby F/M";
            } else if (age > 0) {
                if (age <= 4) {
                    agetype = "Toddler F/M";
                } else if (age > 4 && age <= 14) {
                    agetype = "Child F/M";
                } else if (age > 14 && age <= 59) {
                    if (patient_view.getMemberGender().equals("Male")) {
                        agetype = "Adult Male";
                    } else {
                        agetype = "Adult Female";
                    }
                } else if (age > 59) {

                    if (patient_view.getMemberGender().equals("Male")) {
                        agetype = "Elderly Male";
                    } else {
                        agetype = "Elderly Female";
                    }
                }
            } else {

                agetype = "";
            }
            if (LaboratoryDAO.Laboratory_testresult(sub_test_id)) {
                units = LaboratoryDAO.get_result_units(sub_test_id, agetype, Float.valueOf(result));

            } else {
                units = "";

            }
            return units;

        } catch (Exception ex) {
            System.out.println("Exception In Admin Bean: laboratory_get_expected_result_range: " + ex.getMessage());
            return null;
        }
    }

    /* 
    expected_result_range(Integer sub_test_id) method returns the normal ranges of the patient result basing on patient age and gender
     */
    public String expected_result_range(Integer sub_test_id) {

        try {
            String agetype;
            agetype = "";

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String yy, mm, dd, date;
            int patient_age, patient_age_month, patient_age_days;
            date = format.format(patient_view.getDOB());
            yy = date.substring(0, 4);
            mm = date.substring(5, 7);
            dd = date.substring(8, 10);
            LocalDate l = LocalDate.of(Integer.parseInt(yy), Integer.parseInt(mm), Integer.parseInt(dd)); //specify year, month, date directly
            LocalDate now = LocalDate.now(); //gets localDate
            Period diff = Period.between(l, now); //difference between the dates is calculated

            patient_age = diff.getYears();
            patient_age_month = diff.getMonths();
            patient_age_days = diff.getDays();

            Integer age, month, days;
            age = patient_age;
            month = (age <= 0) ? patient_age_month : 0;
            days = (age <= 0 && month <= 0) ? patient_age_days : 0;

            if (days >= 0 && days <= 14 && month == 0 && age == 0) {
                agetype = "Neonate F/M";
            } else if (days > 14 || month > 0) {
                agetype = "Baby F/M";
            } else if (age > 0) {
                if (age <= 4) {
                    agetype = "Toddler F/M";
                } else if (age > 4 && age <= 14) {
                    agetype = "Child F/M";
                } else if (age > 14 && age <= 59) {
                    if (patient_view.getMemberGender().equals("Male")) {
                        agetype = "Adult Male";
                    } else {
                        agetype = "Adult Female";
                    }
                } else if (age > 59) {

                    if (patient_view.getMemberGender().equals("Male")) {
                        agetype = "Elderly Male";
                    } else {
                        agetype = "Elderly Female";
                    }
                }
            } else {

                agetype = "";
            }
            if (LaboratoryDAO.Laboratory_testresult(sub_test_id)) {
                this.expected_result_range = LaboratoryDAO.Expected_result_range(sub_test_id, agetype);
//                    this.expected_result_range = agetype;
                this.result_entry = true;
                this.result_select = false;
            } else {
                this.expected_result_range = "";
                this.result_entry = false;
                this.result_select = true;
            }
            return expected_result_range;

        } catch (Exception ex) {
            System.out.println("Exception In Admin Bean: laboratory_get_expected_result_range: " + ex.getMessage());
            return null;
        }
    }

    /*
    laboratory_add_test_result(String user_id) records patient test results
     */
    public void laboratory_add_test_result(String user_id) {

        try {
            //Check NULL
            if (selected_sample_taken != null) {
                if (specific_test_id != 0 && !test_sample.equals("")) {
                    Integer submitted = 0;
                    String alredyexits = "", error = "";
                    for (int i = 0; i < tablesubtest.size(); i++) {

                        if (LaboratoryDAO.Laboratory_Check_Requeted_Test_Result(this.selected_sample_taken.getRequest_id(), specific_test_id, tablesubtest.get(i).getSub_test_id())) {
                            alredyexits += "," + tablesubtest.get(i).getSub_test();
                            tablesubtest.get(i).setRequested_test_result("");
                            tablesubtest.get(i).setTest_comment("");
                        } else {

                            if (LaboratoryDAO.Laboratory_testresult(tablesubtest.get(i).getSub_test_id())) {
                                tablesubtest.get(i).setRequested_test_result("" + tablesubtest.get(i).getNumerical_result());
                                tablesubtest.get(i).setExpected_result_id(113);
                            }
                            if (tablesubtest.get(i).getSub_test_id() != 0) {
                                if (LaboratoryDAO.Laboratory_Add_Requeted_Test_Result(this.selected_sample_taken.getRequest_id(), user_id, tablesubtest.get(i).getRequested_test_result(), test_sample, tablesubtest.get(i).getTest_comment(), specific_test_id, tablesubtest.get(i).getSub_test_id(), tablesubtest.get(i).getExpected_result_id())) {
                                    if (LaboratoryDAO.Laboratory_Update_Requested_Test_Status("Pending", this.selected_sample_taken.getRequest_id())) {
                                        laboratory_get_requested_tests();
                                        laboratory_submitted_test_results();
                                        /*
                             if (this.tests_requested.isEmpty()) {
                             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "All Requested Tests Have Been Carried Out. Please Confirm And Submit Results. Thank You", "Success"));
                             } else {
                             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Results Added Successfully. Thank You", "Success"));
                             }
                                         */
                                        submitted++;

                                    } else {
                                       
                                    }
                                    Value_reset();

                                } else {
                                   error += tablesubtest.get(i).getSub_test()+","; 
                                }
                            } else {
                                error+="Specific Test,Sub Test and Sample Type must be selected";
                             
                            }
                        }
                    }

                    if (submitted > 0) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Results for " + submitted + " Tests have been submitted Successfully. Thank You", "Success"));

                    }
                    if (!error.equals("")) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, error+" are missing results, please enter results and try again", "Failure"));

                    }
                    if (!alredyexits.equals("")) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "You Have Already Provided Results For " + alredyexits, "Failure"));
                    }
                }else
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Specific test and test sample must not be empty", "Failure"));
                }
                //Check Duplicity - params: request_id,specific_test_id,sub_testid

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Select the Test For Result Submition", "Failure"));
            }

        } catch (Exception ex) {
            System.err.println("Error Message: " + ex.getMessage());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "" + ex.getMessage(), "Failure"));

        }

    }

    /*
     laboratory_get_performed_tests() method returns performed tests and results which are populated in the table on testrequest
    screen
     */
    public List<LabTestResults> laboratory_get_performed_tests() {

        try {

            return LaboratoryDAO.Laboratory_Get_Performed_Tests(this.task_id);
        } catch (Exception ex) {
            System.err.println("Error Message: " + ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "reached" + ex.getMessage(), "Failure"));

            return null;
        }
    }

    /*
    laboratory_delete_result(Integer result_id) allows the user delete test before forwarding to the countersigning level
     */
    public void laboratory_delete_result(Integer result_id) {
        try {

            LaboratoryDAO.Laboratory_Delete_Result(result_id);
            this.tests_requested = LaboratoryDAO.Laboratory_Get_Tests_Requested(String.valueOf(this.task_id));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Test Result Deleted", "Successful"));

        } catch (Exception ex) {
            System.out.println("Error Message: laboratory_delete_result: " + ex.getMessage());
        }
    }

    /*
    laboratory_submit_test_results(String user_id)  methed submits test results to the database 
    it also contains of a dynamic function for checking if the very result already exisits in tghe database to avoid double insertion
     */
    public String laboratory_submit_test_results(String user_id) {
        String[] tables = {"lab_tests_results l", "lab_tests_requests q"};

        String[] concatenated_fields = {"q.Request_Id=l.Request_Id"};
        String[][] confirmTestarray = {{"q.Track_Id", "l.Status"}, {this.task_id, "0"}};

        try {

            if (DynamicFunctionsDAO.Check_if_exsists(tables, confirmTestarray, concatenated_fields)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "All tests must be reviewed before submittion", "Error"));

                return null;
            } else {

                this.tests_requested = LaboratoryDAO.Laboratory_Get_Tests_Requested(this.task_id, "submit");
                String lab_id = LaboratoryDAO.Laboratory_Get_Lab_Id(this.task_id);

                if (this.tests_requested.isEmpty()) {
                    if (this.forward_to.equalsIgnoreCase("Consultant") || this.forward_to.equalsIgnoreCase("OPD")) {
                        if (LaboratoryDAO.Laboratory_Consultant_Pending(this.task_id, user_id, false)) {
                            if (LaboratoryDAO.Laboratory_Delete_Pending(this.task_id)) {
                                if (LaboratoryDAO.Laboratory_Add_Completed_Task(this.task_id, lab_id)) {
                                    if ("High".equals(task_urgency)) {
                                        LaboratoryDAO.Laboratory_Task_Update_Color_Code(task_id, "Blue");
                                    }
                                    LaboratoryDAO.Laboratory_Time_Audit_Update(task_id, user_id);
                                    ConsultantDAO.Consultant_Time_Audit_Start(task_id, user_id);
                                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "You Have Successfully Completed All Requested Tests For Patient: " + this.patient_view.getFullname() + ". Task Has Been Forwarded Back To Consultant", "Success"));
                                    return "laboratory";
                                } else {
                                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Contact Your Administrator", "Failure"));
                                    return null;
                                }
                            } else {
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Contact Your Administrator", "Failure"));
                                return null;
                            }
                        } else {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Contact Your Administrator", "Failure"));
                            return null;
                        }
                    } else if (this.forward_to.equalsIgnoreCase("Ward") || this.forward_to.equalsIgnoreCase("AIP") || this.forward_to.equalsIgnoreCase("PAED")) {
                        List trans_id_list = LaboratoryDAO.Laboratory_Get_Ward_Trans_Id(this.task_id);
//                        for (int i = 0; i < trans_id_list.size(); i++) {
//                            if (WardDAO.Ward_Update_Transaction(this.task_id, trans_id_list.get(i).toString(), "Laboratory Test Request", user_id)) {
//                                if (LaboratoryDAO.Laboratory_Delete_Pending(this.task_id)) {
//                                    if (LaboratoryDAO.Laboratory_Add_Completed_Task(this.task_id, lab_id)) {
//                                        if ("High".equals(task_urgency)) {
//                                            LaboratoryDAO.Laboratory_Task_Update_Color_Code(task_id, "Blue");
//                                        }
//                                        LaboratoryDAO.Laboratory_Time_Audit_Update(task_id, user_id);
//                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "You Have Successfully Completed All Requested Tests For Patient: " + this.patient_view.getFullname(), "Success"));
//                                    } else {
//                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Contact Your Administrator", "Failure"));
//                                    }
//                                } else {
//                                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Contact Your Administrator", "Failure"));
//                                }
//                            } else {
//                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error. If It Persists,Please Contact Adminstrator.", "Failure"));
//                            }
//                        }
                        return "laboratory";
                    } else {
                        // capture other scenarios - other possible forward scenarios ; if non, then return null
                        return null;
                    }

                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Action Blocked. You Have " + tests_requested.size() + " Pending Requested Test(s). Thank You", "Failure"));
                    laboratory_unlock_task();
                    return "laboratory";
                }
            }

        } catch (Exception ex) {
            System.err.println("Error Message: " + ex.getMessage());
            return null;
        }
    }

    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="Clinical notes">
//    private void laboratory_get_clinical_notes() {
//        try {
//            clinical_notes = ConsultantDAO.Consultant_Get_Clinical_Notes(task_id);
//        } catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//        }
//    }
//    public void laboratory_delete_clinical_note(Integer note_id) {
//        try {
//            if (ConsultantDAO.Consultant_Delete_Clinical_Note(note_id)) {
//                clinical_notes = ConsultantDAO.Consultant_Get_Clinical_Notes(task_id);
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Clinical Note Deleted Successfully", "Success"));
//            } else {
//            }
//        } catch (Exception ex) {
//            System.out.println("Error Message: " + ex.getMessage());
//        }
//    }
//    public void laboratory_add_clinical_note(String user_id) {
//        try {
//            if (ConsultantDAO.Consultant_Dentist_Tasks(this.task_id, user_id, this.note)) {
//                this.note = "";
//                clinical_notes = ConsultantDAO.Consultant_Get_Clinical_Notes(task_id);
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Note Added Successfully", "Success"));
//            } else {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Contact The Administrator", "Failure"));
//            }
//        } catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//        }
//    }
    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="Administrator 'method' - Not currently used">    
    public void laboratory_add_test() {
        try {
            if (LaboratoryDAO.Laboratory_Add_Test(this.selected_category_id, this.test_name, this.test_normal_value)) {
                this.test_name = "";
                this.test_normal_value = "";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Added Test Successfully", "Success"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Contact Your Administrator", "Failure"));
            }
        } catch (Exception ex) {
            System.err.println("Error Message: " + ex.getMessage());
        }
    }

    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="Miscellaneous other 'methods' - Not currently used">
    public List<LabTest> laboratory_get_all_tests() {
        try {
            return LaboratoryDAO.Laboratory_Get_Tests();
        } catch (Exception ex) {
            System.err.println("Error Message: " + ex.getMessage());
        }
        return null;
    }

//    public List<Notes> laboratory_get_notes() {
//        try {
//            return ConsultantDAO.Consultant_Get_Notes(this.task_id);
//        } catch (Exception ex) {
//            System.out.println("Error Message: " + ex.getMessage());
//        }
//        return null;
//    }
    public void laboratory_test_start_time() {
        try {
            if (!test_started) {
                //Techinician Signals the Start of the Testing
                if (this.task_id != null && this.selected_lab_test.getRequest_id() != null) {
                    //save transaction
                    if (LaboratoryDAO.Laboratory_Add_Test_Duration_Start(this.task_id, this.selected_lab_test.getRequest_id())) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Test Start Time Saved Successfully", "Success"));
                    } else {
                    }
                } else {
                    //Do Nothing
                }

            } else {
                //Unchecking will delete the initial (if any) test start time
                if (LaboratoryDAO.Laboratory_Add_Test_Duration_Delete(this.task_id, this.selected_lab_test.getRequest_id())) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Test Start Time Unset", "Success"));
                } else {
                    // Error in DB transaction But not echo to CLients
                }
            }
            //System.out.println("Test Started -- " + test_started);
        } catch (Exception ex) {
            System.err.println("Error Message: " + ex.getMessage());
        }
    }

    public List<SpecificTest> retrieve_confirm_specificTest(Integer test_id) {
        try {
            List confirm_specific_tests = LaboratoryDAO.Laboratory_Get_Specific_Tests(test_id);
            return confirm_specific_tests;
        } catch (Exception ex) {
            System.err.println("Error Message: " + ex.getMessage());
            return null;

        }
    }

    public List<testresults> tableLaboratory_Get_Sub_Tests(Integer specific_id) {
        try {

            if (specific_id != null) {
                return this.tablesubtest = LaboratoryDAO.tableLaboratory_Get_Sub_Tests(specific_id);

            } else {
                return null;
            }
        } catch (Exception ex) {
            System.err.println("Error Message: " + ex.getMessage());

            return null;
        }
    }

    public void retrieve_confirm_subtest(Integer specific_id) {

        this.confirm_otherResults = null;
        this.confirm_comments = null;
        this.confirm_sample_type = null;
        this.confirm_sub_test_id = null;
        this.expected_result_range = null;
        try {

            if (specific_id != null) {
                this.sub_tests = LaboratoryDAO.Laboratory_Get_Sub_Tests(specific_id);

            } else {

            }
        } catch (Exception ex) {
            System.err.println("Error Message: " + ex.getMessage());

        }
    }

    public void editTestResults(Integer user_id) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = new Date();

        String[][] confirmTestarray = {{"specific_test_id", "sub_test_id", "expected_result_id", "other_results", "Sample_Type", "Comment", "Performed_By", "Result_Date", "Status"},
        {this.confirm_specific_test_id.toString(), this.confirm_sub_test_id.toString(), this.confirm_expected_result_id.toString(),
            this.confirm_otherResults, this.confirm_sample_type, this.confirm_comments, user_id.toString(), dateFormat.format(date), "1"}};
        if (this.confirm_specific_test_id != 0 && this.confirm_sub_test_id != 0 && !this.confirm_sample_type.equalsIgnoreCase("Select Sample")) {
            try {
                if (DynamicFunctionsDAO.Edit("lab_tests_results", confirmTestarray, "Result_Id", this.confirm_result_id.toString())) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Test has been successfully confirmed", "Success"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error occured: Please contact System administrator", "Failure"));
                }
            } catch (Exception ex) {
                System.err.println("Error Message: " + ex.getMessage());

            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Specific Test,Sub Test and Sample Type must be selected", "Failure"));

        }

    }

    public void reviewResults(Integer test_id, Integer result_id, Integer specific_id, Integer subtest_id, Integer expected_result_id, String specific_test, String subtest_name, String expected_results, String other_results, String comments, String sample_type) {
//      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "id= "+subtest_id, "Success"));
        this.confirm_specific_test_id = specific_id;
        this.confirm_sub_test_id = subtest_id;
        this.confirm_result_id = result_id;
        this.confirm_expected_result_id = expected_result_id;
        this.confirm_otherResults = other_results;
        this.confirm_comments = comments;
        this.confirm_sample_type = sample_type;
        try {

            String agetype;
            agetype = "";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String yy, mm, dd, date;
            int patient_age, patient_age_month, patient_age_days;
            date = format.format(patient_view.getDOB());
            yy = date.substring(0, 4);
            mm = date.substring(5, 7);
            dd = date.substring(8, 10);
            LocalDate l = LocalDate.of(Integer.parseInt(yy), Integer.parseInt(mm), Integer.parseInt(dd)); //specify year, month, date directly
            LocalDate now = LocalDate.now(); //gets localDate
            Period diff = Period.between(l, now); //difference between the dates is calculated

            patient_age = diff.getYears();
            patient_age_month = diff.getMonths();
            patient_age_days = diff.getDays();
            Integer age, month, days;
            age = patient_age;
            month = (age <= 0) ? patient_age_month : 0;
            days = (age <= 0 && month <= 0) ? patient_age_days : 0;

            if (days >= 0 && days <= 14 && month == 0 && age == 0) {
                agetype = "Neonate F/M";
            } else if (days > 14 || month > 0) {
                agetype = "Baby F/M";
            } else if (age > 0) {
                if (age <= 4) {
                    agetype = "Toddler F/M";
                } else if (age > 4 && age <= 14) {
                    agetype = "Child F/M";
                } else if (age > 14 && age <= 59) {
                    if (patient_view.getMemberGender().equals("Male")) {
                        agetype = "Adult Male";
                    } else {
                        agetype = "Adult Female";
                    }
                } else if (age > 59) {

                    if (patient_view.getMemberGender().equals("Male")) {
                        agetype = "Elderly Male";
                    } else {
                        agetype = "Elderly Female";
                    }
                }
            } else {

                agetype = "";
            }
            if (LaboratoryDAO.Laboratory_testresult(subtest_id)) {
                this.expected_result_range = LaboratoryDAO.Expected_result_range(subtest_id, agetype);
//                    this.expected_result_range = agetype;
                this.result_entry = true;
                this.result_select = false;
            } else {
                this.expected_result_range = "";
                this.result_entry = false;
                this.result_select = true;
            }

            if (test_id != null) {
                this.specific_tests = LaboratoryDAO.Laboratory_Get_Specific_Tests(test_id);

            } else {
            }

            if (specific_id != null) {
                this.sub_tests = LaboratoryDAO.Laboratory_Get_Sub_Tests(specific_id);

            } else {

            }
            if (subtest_id != null) {
                List returnedData[] = LaboratoryDAO.Laboratory_Get_Expected_Results(subtest_id);
                this.expected_results = returnedData[0];
            } else {
            }

        } catch (Exception ex) {
            System.out.println("Exception In Admin Bean:-- laboratory_get_specific_tests: " + ex.getMessage());

        }

    }

    public void receive_confirm_expected_results(Integer subtest_id) {
        this.confirm_otherResults = null;
        this.confirm_comments = null;
        this.confirm_sample_type = null;
        try {

            if (subtest_id != null) {

                List returnedData[] = LaboratoryDAO.Laboratory_Get_Expected_Results(subtest_id);
                expected_results = returnedData[0];

                this.expected_result_range = expected_result_range(subtest_id);

            } else {

            }
        } catch (Exception ex) {
            System.out.println("Exception In Admin Bean:-- laboratory_get_specific_tests: " + ex.getMessage());

        }
    }

    public String backButtonAfterResultSubmition(String task_id) {
        laboratory_unlock_task();
        try {
            this.patient_view = ReceptionDAO.Reception_Retrieve_Patient_Details(this.task_id);
            this.reception_info_view = ReceptionDAO.Reception_Get_Reception_Info(this.task_id);
        } catch (Exception e) {
        }
        return "/pages/lab/details.xhtml";
    }

    public void submitProvisionaltests(String task_id) {

        String[][] confirmTestarray = {{"Countersign_status"}, {"Completed"}};

        try {
            this.tests_requested = LaboratoryDAO.Laboratory_Get_Tests_Requested(task_id, "submit");
            if (this.tests_requested.isEmpty()) {
                if (DynamicFunctionsDAO.Edit("lab_tests_requests", confirmTestarray, "Track_Id", task_id)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "labtests has been successfully submitted", "Success"));

                    if (LaboratoryDAO.Laboratory_Task_Lock(this.task_id, false, "")) {
                        this.assigned = false;
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Task Has Been UNLOCKED.", "Successful"));

                    } else {
                        this.assigned = true;
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "An Error Occurred.Task Could Not Be UnLocked", "Failure"));

                    }
                    this.hidecountersigned = false;
                    this.hidecountersigned_message = true;
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error occured: Please contact System administrator", "Failure"));
                }
                laboratory_unlock_task();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Action Blocked. You Have " + tests_requested.size() + " Pending Requested Test(s). Thank You", "Failure"));

            }
        } catch (Exception ex) {
            System.out.println("Exception In Admin Bean:-- laboratory_get_lab_request_countasign status: " + ex.getMessage());

        }
    }

    public String countersign_results() {
        try {
            laboratory_get_requested_tests();
            tests_samples_taken = LaboratoryDAO.Laboratory_Get_Tests_Samples_Taken(this.task_id);
            return "testresults";

        } catch (Exception ex) {
            System.err.println("Error Message: " + ex.getMessage());
            return null;
        }
    }

    public boolean countersign_status(String track_id, String user_id) {

        try {
            String[][] countersignTestarraypending = {{"Track_Id", "Countersign_status"}, {track_id, "Pending"}};
            String[][] countersignTestarraycompleted = {{"Track_Id", "Countersign_status"}, {track_id, "Completed"}};

            if (DynamicFunctionsDAO.dynamicgetsingle_variable("Level", "users", "UID", user_id).equalsIgnoreCase("SUPERVISOR")) {
                if (DynamicFunctionsDAO.Check_if_exsists("lab_tests_requests", countersignTestarraypending)) {
                    return false;
                } else {
                    return true;
                }
            } else {
                if (DynamicFunctionsDAO.Check_if_exsists("lab_tests_requests", countersignTestarraypending)) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (Exception e) {
            return true;
        }

    }

    public void Value_reset() {
        this.sub_test_id = null;
        this.expected_result_range = null;
    }

    public String exitlab(String trackid, String user_id) {
        try {
            String[][] updatelabrequest = {{"Status", "Countersign_status"}, {"Completed", "Completed"}};
            if (DynamicFunctionsDAO.Edit("lab_tests_requests", updatelabrequest, "Track_Id", trackid)) {
                laboratory_get_pending_tasks(user_id);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Client Exited", "Success"));
            }

            return "/pages/lab/incoming.xhtml";
        } catch (Exception e) {
            return null;
        }
    }

    public String foward_to_consultation(String trackid, String user_id) {
        try {
            String[][] updatelabrequest = {{"Status", "Countersign_status"}, {"Completed", "Completed"}};
            if (DynamicFunctionsDAO.Edit("lab_tests_requests", updatelabrequest, "Track_Id", trackid)) {
                LaboratoryDAO.Laboratory_Consultant_Pending(this.task_id, user_id, false);
                laboratory_get_pending_tasks(user_id);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Client fowarded to consultation", "Success"));
            }
            return "/pages/lab/incoming.xhtml";
        } catch (Exception e) {

            return null;
        }
    }

    public List<ExpectedResult> laboratory_get_expected_results(Integer sub_test_id) {

        try {
            if (sub_test_id != null) {
//                expected_results = LaboratoryDAO.Laboratory_Get_Expected_Results(sub_test_id);
                this.expected_result_range = "";
                List returnedData[] = LaboratoryDAO.Laboratory_Get_Expected_Results(sub_test_id);
                expected_results = returnedData[0];

                expected_result_range(sub_test_id);
                return expected_results;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Exception In Admin Bean: laboratory_get_expected_results: " + ex.getMessage());
            return null;
        }
    }

    public boolean result_entry(Integer sub_test_id, String type) {

        try {
            if (LaboratoryDAO.Laboratory_testresult(sub_test_id)) {
                this.result_entry = true;
                return (type.equals("result_entry")) ? true : false;
            } else {
                return (type.equals("result_select")) ? true : false;

            }
        } catch (Exception e) {
            return false;
        }
    }

   
}
