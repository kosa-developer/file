/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedModal;

import java.io.Serializable;

/**
 *
 * @author romugabi
 */
public class History implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String track_Id;
    private String patient_Id;
    private String patient_Name;
    private String age;
    private String gender;
    private String admitted;
    private String discharged;
    private String visit_Date;

    public String getTrack_Id() {
        return track_Id;
    }

    public void setTrack_Id(String track_Id) {
        this.track_Id = track_Id;
    }

    public String getPatient_Id() {
        return patient_Id;
    }

    public void setPatient_Id(String patient_Id) {
        this.patient_Id = patient_Id;
    }

    public String getPatient_Name() {
        return patient_Name;
    }

    public void setPatient_Name(String patient_Name) {
        this.patient_Name = patient_Name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAdmitted() {
        return admitted;
    }

    public void setAdmitted(String admitted) {
        this.admitted = admitted;
    }

    public String getDischarged() {
        return discharged;
    }

    public void setDischarged(String discharged) {
        this.discharged = discharged;
    }

    public String getVisit_Date() {
        return visit_Date;
    }

    public void setVisit_Date(String visit_Date) {
        this.visit_Date = visit_Date;
    }
    
    
    
}
