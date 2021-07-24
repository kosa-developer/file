package managedModal;

import java.io.Serializable;

public class ReceptionTask
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private String task_Id;
    private String patient_Id;
    private String patient_name;
    private Integer patient_current_age;
    private String gender;
    private String visit;
    private String problem;
    private Integer color_code;
    private String room_no;
    private String record_time;
    private String urgency;
    private String lab_id;
    private String p_origin;
    private String forward_to;
    private String record_errors;

    public String getForward_to() {
        return forward_to;
    }

    public void setForward_to(String forward_to) {
        this.forward_to = forward_to;
    }

    public String getP_origin() {
        return p_origin;
    }

    public void setP_origin(String p_origin) {
        this.p_origin = p_origin;
    }

    public String getLab_id() {
        return lab_id;
    }

    public void setLab_id(String lab_id) {
        this.lab_id = lab_id;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public String getRecord_time() {
        return record_time;
    }

    public void setRecord_time(String record_time) {
        this.record_time = record_time;
    }

    public String getRoom_no() {
        return this.room_no;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    public String getTask_Id() {
        return this.task_Id;
    }

    public void setTask_Id(String task_Id) {
        this.task_Id = task_Id;
    }

    public String getPatient_Id() {
        return this.patient_Id;
    }

    public void setPatient_Id(String patient_Id) {
        this.patient_Id = patient_Id;
    }

    public String getPatient_name() {
        return this.patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public Integer getPatient_current_age() {
        return this.patient_current_age;
    }

    public void setPatient_current_age(Integer patient_current_age) {
        this.patient_current_age = patient_current_age;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getVisit() {
        return this.visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }

    public String getProblem() {
        return this.problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public Integer getColor_code() {
        return this.color_code;
    }

    public void setColor_code(Integer color_code) {
        this.color_code = color_code;
    }

    public String getRecord_errors() {
        return record_errors;
    }

    public void setRecord_errors(String record_errors) {
        this.record_errors = record_errors;
    }

    public ReceptionTask() {
    }

    public ReceptionTask(String task_Id, String patient_Id, String patient_name, Integer patient_current_age, String gender, String visit, String problem, Integer color_code) {
        this.task_Id = task_Id;
        this.patient_Id = patient_Id;
        this.patient_name = patient_name;
        this.patient_current_age = patient_current_age;
        this.gender = gender;
        this.visit = visit;
        this.problem = problem;
        this.color_code = color_code;
    }

    public ReceptionTask(String task_Id, String patient_Id, String patient_name, Integer patient_current_age, String gender, String visit, String problem, Integer color_code, String room_no, String record_time, String p_origin) {
        this.task_Id = task_Id;
        this.patient_Id = patient_Id;
        this.patient_name = patient_name;
        this.patient_current_age = patient_current_age;
        this.gender = gender;
        this.visit = visit;
        this.problem = problem;
        this.color_code = color_code;
        this.room_no = room_no;
        this.record_time = record_time;
        this.p_origin = p_origin;
    }

    public ReceptionTask(String task_Id, String patient_Id, String patient_name, Integer patient_current_age, String gender, String visit, String problem, Integer color_code, String room_no, String record_time) {
        this.task_Id = task_Id;
        this.patient_Id = patient_Id;
        this.patient_name = patient_name;
        this.patient_current_age = patient_current_age;
        this.gender = gender;
        this.visit = visit;
        this.problem = problem;
        this.color_code = color_code;
        this.room_no = room_no;
        this.record_time = record_time;
    }

    public ReceptionTask(String task_Id, String patient_Id, String patient_name, Integer patient_current_age, String gender, String visit, String problem, Integer color_code, String room_no, String record_time, String p_origin, String record_errors) {
        this.task_Id = task_Id;
        this.patient_Id = patient_Id;
        this.patient_name = patient_name;
        this.patient_current_age = patient_current_age;
        this.gender = gender;
        this.visit = visit;
        this.problem = problem;
        this.color_code = color_code;
        this.room_no = room_no;
        this.record_time = record_time;
        this.p_origin = p_origin;
        this.record_errors = record_errors;
    }

    //rs.getString("Track_Id"), rs.getString("Patient_Id"), rs.getString("Patient_Name"), rs.getInt("Age"), rs.getString("Gender"), rs.getString("VisitReason"), rs.getString("Problem")
    public ReceptionTask(String task_Id, String patient_Id, String patient_name, Integer patient_current_age, String gender, String visit, String problem) {
        this.task_Id = task_Id;
        this.patient_Id = patient_Id;
        this.patient_name = patient_name;
        this.patient_current_age = patient_current_age;
        this.gender = gender;
        this.visit = visit;
        this.problem = problem;
    }

    public ReceptionTask(String task_Id, String patient_Id, String patient_name, Integer patient_current_age, String gender, String visit, String problem, Integer color_code, String room_no, String record_time, String urgency, String lab_id, String forward_to) {
        this.task_Id = task_Id;
        this.patient_Id = patient_Id;
        this.patient_name = patient_name;
        this.patient_current_age = patient_current_age;
        this.gender = gender;
        this.visit = visit;
        this.problem = problem;
        this.color_code = color_code;
        this.room_no = room_no;
        this.record_time = record_time;
        this.urgency = urgency;
        this.lab_id = lab_id;
        this.forward_to = forward_to;
    }
}
