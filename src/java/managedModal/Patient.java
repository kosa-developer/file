package managedModal;

import java.io.Serializable;
import java.util.Date;

public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;
    private String memberId;
    private String householdId;
    private String fullname;
    private Integer age;
    private Integer age_months;
    private Integer age_days;
    private Date subscriptionDate;
    private Integer duration;
    private String memberGender;
    private String village;
    private String parish;
    private String subcounty;
    private String vhp;
    private String patientphoto;
    private String member_exp;
    private boolean subscription_expired;
    private String subscription_exp;
    private String height_for_age;
    private String weight_for_age;
    private String track_id;

    public String getMember_exp() {
        return member_exp;
    }

    public void setMember_exp(String member_exp) {
        this.member_exp = member_exp;
    }

    public boolean isSubscription_expired() {
        return subscription_expired;
    }

    public void setSubscription_expired(boolean subscription_expired) {
        this.subscription_expired = subscription_expired;
    }

    public String getSubscription_exp() {
        return subscription_exp;
    }

    public void setSubscription_exp(String subscription_exp) {
        this.subscription_exp = subscription_exp;
    }

    public String getPatientphoto() {
        return this.patientphoto;
    }

    public void setPatientphoto(String patientphoto) {
        this.patientphoto = patientphoto;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getHouseholdId() {
        return this.householdId;
    }

    public void setHouseholdId(String householdId) {
        this.householdId = householdId;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge_months() {
        return this.age_months;
    }

    public void setAge_months(Integer age_months) {
        this.age_months = age_months;
    }

    public Integer getAge_days() {
        return this.age_days;
    }

    public void setAge_days(Integer age_days) {
        this.age_days = age_days;
    }

    public Date getSubscriptionDate() {
        return this.subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getMemberGender() {
        return this.memberGender;
    }

    public void setMemberGender(String memberGender) {
        this.memberGender = memberGender;
    }

    public String getVillage() {
        return this.village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getParish() {
        return this.parish;
    }

    public void setParish(String parish) {
        this.parish = parish;
    }

    public String getSubcounty() {
        return this.subcounty;
    }

    public void setSubcounty(String subcounty) {
        this.subcounty = subcounty;
    }

    public String getVhp() {
        return this.vhp;
    }

    public void setVhp(String vhp) {
        this.vhp = vhp;
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

    public String getTrack_id() {
        return track_id;
    }

    public void setTrack_id(String track_id) {
        this.track_id = track_id;
    }
    

    public Patient() {
    }

    public Patient(String memberId, String householdId, String fullname, Integer age,Integer age_days,Integer age_months, Date subscriptionDate, Integer duration, String memberGender, String village, String parish, String subcounty, String vhp, String patientphoto, String weight_for_age, String height_for_age) {
        this.memberId = memberId;
        this.householdId = householdId;
        this.fullname = fullname;
        this.age = age;
        this.subscriptionDate = subscriptionDate;
        this.duration = duration;
        this.memberGender = memberGender;
        this.village = village;
        this.parish = parish;
        this.subcounty = subcounty;
        this.vhp = vhp;
        this.patientphoto = patientphoto;
        this.weight_for_age = weight_for_age;
        this.height_for_age = height_for_age;
        this.age_days=age_days;
        this.age_months=age_months;
    }

    public Patient(String memberId, String householdId, String fullname, Integer age, Integer age_months, Integer age_days, Date subscriptionDate, Integer duration, String memberGender, String village, String parish, String subcounty, String vhp, String patientphoto) {
        this.memberId = memberId;
        this.householdId = householdId;
        this.fullname = fullname;
        this.age = age;
        this.age_months = age_months;
        this.age_days = age_days;
        this.subscriptionDate = subscriptionDate;
        this.duration = duration;
        this.memberGender = memberGender;
        this.village = village;
        this.parish = parish;
        this.subcounty = subcounty;
        this.vhp = vhp;
        this.patientphoto = patientphoto;
    }

}
