/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedModal;

import java.io.Serializable;

/**
 *
 * @author romugabi
 */
public class TreatmentView implements Serializable {

    private static final long serialVersionUID = 1L;

    private String trans_id, trans_id_end, track_id, diagnosis, diagnosis_hmis,diagnosis_category,prescription_notes,prescription, loading_dose, dose_unit, 
            times, times_unit, days, start_date, end_date, administer, record_date, action, prescribe_unit;
    

// Following added as test
    private Integer treatment_id;
//    
    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(String trans_id) {
        this.trans_id = trans_id;
    }

    public String getTrans_id_end() {
        return trans_id_end;
    }

    public void setTrans_id_end(String trans_id_end) {
        this.trans_id_end = trans_id_end;
    }

    public String getTrack_id() {
        return track_id;
    }

    public void setTrack_id(String track_id) {
        this.track_id = track_id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDiagnosis_hmis() {
        return diagnosis_hmis;
    }

    public void setDiagnosis_hmis(String diagnosis_hmis) {
        this.diagnosis_hmis = diagnosis_hmis;
    }

    public String getDiagnosis_category() {
        return diagnosis_category;
    }

    public void setDiagnosis_category(String diagnosis_category) {
        this.diagnosis_category = diagnosis_category;
    }

    public String getPrescription_notes() {
        return prescription_notes;
    }

    public void setPrescription_notes(String prescription_notes) {
        this.prescription_notes = prescription_notes;
    }

    public String getLoading_dose() {
        return loading_dose;
    }

    public void setLoading_dose(String loading_dose) {
        this.loading_dose = loading_dose;
    }

    public String getDose_unit() {
        return dose_unit;
    }

    public void setDose_unit(String dose_unit) {
        this.dose_unit = dose_unit;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getTimes_unit() {
        return times_unit;
    }

    public void setTimes_unit(String times_unit) {
        this.times_unit = times_unit;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getAdminister() {
        return administer;
    }

    public void setAdminister(String administer) {
        this.administer = administer;
    }

    public String getRecord_date() {
        return record_date;
    }

    public void setRecord_date(String record_date) {
        this.record_date = record_date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPrescribe_unit() {
        return prescribe_unit;
    }

    public void setPrescribe_unit(String prescribe_unit) {
        this.prescribe_unit = prescribe_unit;
    }

// Following added as test    
    public Integer getTreatment_id() {
        return treatment_id;
    }

    public void setTreatment_id(Integer treatment_id) {
        this.treatment_id = treatment_id;
    }    
//    
    public TreatmentView() {
    }

}
