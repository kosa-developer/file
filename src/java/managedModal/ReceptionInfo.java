package managedModal;

import java.io.Serializable;

public class ReceptionInfo
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private String referral_status;
    private String referred_from;
    private String referral_num;
    private double weight;
    private double height;
    private String temperature;
    private String oxy_saturation;
    private String heart_pulse;
    private String blood_pressure;
    private String respiratory_rate;
    private boolean family_planning;
    private boolean itn_in_use;
    private String visit_reason;
    private String problem;
    private String triage_category;
    private boolean skip_accounts = false;
    private String forward_to_section;
    private String next_of_kin;
    private String muac;
//    private String weight_For_Height;
    private String weight_For_Height;
    private String imm_status;
    private String smoker;
    private String alcohol;
    private String record_errors;
    private String height_for_age;
    private String weight_for_age;
    private String fullname;
    private Integer age;
    private Integer age_months;
    private Integer age_days;
    private String village;
    private String parish;
    private String subcounty;
    private String gender;

//    public String getWeight_For_Height() {
//        return weight_For_Height;
//    }
//
//    public void setWeight_For_Height(String weight_For_Height) {
//        this.weight_For_Height = weight_For_Height;
//    }
    public String getWeight_For_Height() {
        return weight_For_Height;
    }

    public void setWeight_For_Height(String weight_For_Height) {
        this.weight_For_Height = weight_For_Height;
    }

    public String getNext_of_kin() {
        return this.next_of_kin;
    }

    public void setNext_of_kin(String next_of_kin) {
        this.next_of_kin = next_of_kin;
    }

    public String getReferral_status() {
        return this.referral_status;
    }

    public void setReferral_status(String referral_status) {
        this.referral_status = referral_status;
    }

    public String getReferred_from() {
        return this.referred_from;
    }

    public void setReferred_from(String referred_from) {
        this.referred_from = referred_from;
    }

    public String getReferral_num() {
        return this.referral_num;
    }

    public void setReferral_num(String referral_num) {
        this.referral_num = referral_num;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getTemperature() {
        return this.temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getOxy_saturation() {
        return this.oxy_saturation;
    }

    public void setOxy_saturation(String oxy_saturation) {
        this.oxy_saturation = oxy_saturation;
    }

    public String getHeart_pulse() {
        return this.heart_pulse;
    }

    public void setHeart_pulse(String heart_pulse) {
        this.heart_pulse = heart_pulse;
    }

    public String getBlood_pressure() {
        return this.blood_pressure;
    }

    public void setBlood_pressure(String blood_pressure) {
        this.blood_pressure = blood_pressure;
    }

    public String getRespiratory_rate() {
        return this.respiratory_rate;
    }

    public void setRespiratory_rate(String respiratory_rate) {
        this.respiratory_rate = respiratory_rate;
    }

    public boolean isFamily_planning() {
        return this.family_planning;
    }

    public void setFamily_planning(boolean family_planning) {
        this.family_planning = family_planning;
    }

    public boolean isItn_in_use() {
        return this.itn_in_use;
    }

    public void setItn_in_use(boolean itn_in_use) {
        this.itn_in_use = itn_in_use;
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

    public boolean isSkip_accounts() {
        return this.skip_accounts;
    }

    public void setSkip_accounts(boolean skip_accounts) {
        this.skip_accounts = skip_accounts;
    }

    public String getForward_to_section() {
        return this.forward_to_section;
    }

    public void setForward_to_section(String forward_to_section) {
        this.forward_to_section = forward_to_section;
    }

    public String getMuac() {
        return muac;
    }

    public void setMuac(String muac) {
        this.muac = muac;
    }

    public String getImm_status() {
        return imm_status;
    }

    public void setImm_status(String imm_status) {
        this.imm_status = imm_status;
    }

    public String getSmoker() {
        return smoker;
    }

    public void setSmoker(String smoker) {
        this.smoker = smoker;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    public String getRecord_errors() {
        return record_errors;
    }

    public void setRecord_errors(String record_errors) {
        this.record_errors = record_errors;
    }

    public String getHeight_for_age() {
        return height_for_age;
    }

    public void setHeight_for_age(String height_for_age) {
        this.height_for_age = height_for_age;
    }

    public String getWeight_for_age() {
        return weight_for_age;
    }

    public void setWeight_for_age(String weight_for_age) {
        this.weight_for_age = weight_for_age;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge_months() {
        return age_months;
    }

    public void setAge_months(Integer age_months) {
        this.age_months = age_months;
    }

    public Integer getAge_days() {
        return age_days;
    }

    public void setAge_days(Integer age_days) {
        this.age_days = age_days;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getParish() {
        return parish;
    }

    public void setParish(String parish) {
        this.parish = parish;
    }

    public String getSubcounty() {
        return subcounty;
    }

    public void setSubcounty(String subcounty) {
        this.subcounty = subcounty;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    

}
