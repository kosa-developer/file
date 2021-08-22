package managedModal;

import java.io.Serializable;

public class AccountsTasks
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private String track_id;
    private String patient_id;
    private String patient_name;
    private Integer age;
    private String gender;
    private String visit_reason;
    private String problem;
    private String triage_category;
    private Integer payable_amount;
    private String forward_to;
    private boolean skipped_accounts;
    private String skipped_acc;
    private boolean locked;
    private Integer color_code;
    private boolean member;
    private String member_exp;
    private boolean subscription_expired;
    private String subscription_exp;
    private String operation;
    private String reason;
    private String from_staff;
    private String staff_type;

    public String getStaff_type() {
        return staff_type;
    }

    public void setStaff_type(String staff_type) {
        this.staff_type = staff_type;
    }

    public String getSkipped_acc() {
        return skipped_acc;
    }

    public void setSkipped_acc(String skipped_acc) {
        this.skipped_acc = skipped_acc;
    }

    public String getMember_exp() {
        return member_exp;
    }

    public void setMember_exp(String member_exp) {
        this.member_exp = member_exp;
    }

    public String getSubscription_exp() {
        return subscription_exp;
    }

    public void setSubscription_exp(String subscription_exp) {
        this.subscription_exp = subscription_exp;
    }

    public String getFrom_staff() {
        return this.from_staff;
    }

    public void setFrom_staff(String from_staff) {
        this.from_staff = from_staff;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public boolean isMember() {
        return this.member;
    }

    public void setMember(boolean member) {
        this.member = member;
    }

    public boolean isSubscription_expired() {
        return this.subscription_expired;
    }

    public void setSubscription_expired(boolean subscription_expired) {
        this.subscription_expired = subscription_expired;
    }

    public Integer getColor_code() {
        return this.color_code;
    }

    public void setColor_code(Integer color_code) {
        this.color_code = color_code;
    }

    public String getTrack_id() {
        return this.track_id;
    }

    public void setTrack_id(String track_id) {
        this.track_id = track_id;
    }

    public String getPatient_id() {
        return this.patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_name() {
        return this.patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getVisit_reason() {
        return this.visit_reason;
    }

    public void setVisit_reason(String visit_reason) {
        this.visit_reason = visit_reason;
    }

    public String getProblem() {
        return this.problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getTriage_category() {
        return this.triage_category;
    }

    public void setTriage_category(String triage_category) {
        this.triage_category = triage_category;
    }

    public Integer getPayable_amount() {
        return this.payable_amount;
    }

    public void setPayable_amount(Integer payable_amount) {
        this.payable_amount = payable_amount;
    }

    public String getForward_to() {
        return this.forward_to;
    }

    public void setForward_to(String forward_to) {
        this.forward_to = forward_to;
    }

    public boolean isSkipped_accounts() {
        return this.skipped_accounts;
    }

    public void setSkipped_accounts(boolean skipped_accounts) {
        this.skipped_accounts = skipped_accounts;
    }

    public boolean isLocked() {
        return this.locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public AccountsTasks() {
    }

    public AccountsTasks(String track_id, String operation, String patient_id, String patient_name, Integer age, String gender, String visit_reason, String problem, String triage_category, Integer payable_amount, String forward_to, boolean skipped_accounts, boolean locked, Integer color_code, boolean member, boolean subscription_expired, String reason, String from_staff) {
        this.track_id = track_id;
        this.operation = operation;
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        this.age = age;
        this.gender = gender;
        this.visit_reason = visit_reason;
        this.problem = problem;
        this.triage_category = triage_category;
        this.payable_amount = payable_amount;
        this.forward_to = forward_to;
        this.skipped_accounts = skipped_accounts;
        this.locked = locked;
        this.color_code = color_code;
        this.member = member;
        this.subscription_expired = subscription_expired;
        this.reason = reason;
        this.from_staff = from_staff;
    }

    public AccountsTasks(String track_id, String operation, String patient_id, String patient_name, Integer age, String gender, String visit_reason, String problem, String triage_category, Integer payable_amount, String forward_to, boolean skipped_accounts, boolean locked, Integer color_code, boolean member, boolean subscription_expired, String reason, String from_staff, String subscription_exp, String member_exp, String skipped_acc,String staff_type) {
        this.track_id = track_id;
        this.operation = operation;
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        this.age = age;
        this.gender = gender;
        this.visit_reason = visit_reason;
        this.problem = problem;
        this.triage_category = triage_category;
        this.payable_amount = payable_amount;
        this.forward_to = forward_to;
        this.skipped_accounts = skipped_accounts;
        this.locked = locked;
        this.color_code = color_code;
        this.member = member;
        this.subscription_expired = subscription_expired;
        this.reason = reason;
        this.from_staff = from_staff;
        this.subscription_exp = subscription_exp;
        this.member_exp = member_exp;
        this.skipped_acc = skipped_acc;
        this.staff_type = staff_type;
    }

}
