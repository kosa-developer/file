package managedModal;

import java.io.Serializable;

public class Treatment
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private String diagnosis;
    private String diagnosis_hmis;
    private String diagnosis_category;
    private String prescription_notes;
    private String prescribed_drug;
    private String loading_dose;
    private String dose_unit;
    private String dose_times;
    private String dose_times_unit;
    private String dose_days;
    private String start_date;
    private String end_date;
    private String administer;
    private String prescribe_unit;
    private String unit_type;
    private String required_prescribe_units;
    private String required_issue_units;
    private Integer treatment_id;
    private String track_id;
    private String trans_id;
    private String trans_id_end;

    public String getTrack_id() {
        return track_id;
    }

    public void setTrack_id(String track_id) {
        this.track_id = track_id;
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

    public Integer getTreatment_id() {
        return treatment_id;
    }

    public void setTreatment_id(Integer treatment_id) {
        this.treatment_id = treatment_id;
    }

    public String getAdminister() {
        return administer;
    }

    public void setAdminister(String administer) {
        this.administer = administer;
    }

    public String getDiagnosis() {
        return this.diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDiagnosis_hmis() {
        return this.diagnosis_hmis;
    }

    public void setDiagnosis_hmis(String diagnosis_hmis) {
        this.diagnosis_hmis = diagnosis_hmis;
    }

    public String getDiagnosis_category() {
        return this.diagnosis_category;
    }

    public void setDiagnosis_category(String diagnosis_category) {
        this.diagnosis_category = diagnosis_category;
    }

    public String getPrescription_notes() {
        return this.prescription_notes;
    }

    public void setPrescription_notes(String prescription_notes) {
        this.prescription_notes = prescription_notes;
    }

    public String getLoading_dose() {
        return this.loading_dose;
    }

    public void setLoading_dose(String loading_dose) {
        this.loading_dose = loading_dose;
    }

    public String getDose_unit() {
        return this.dose_unit;
    }

    public void setDose_unit(String dose_unit) {
        this.dose_unit = dose_unit;
    }

    public String getDose_times() {
        return this.dose_times;
    }

    public void setDose_times(String dose_times) {
        this.dose_times = dose_times;
    }

    public String getDose_times_unit() {
        return this.dose_times_unit;
    }

    public void setDose_times_unit(String dose_times_unit) {
        this.dose_times_unit = dose_times_unit;
    }

    public String getDose_days() {
        return this.dose_days;
    }

    public void setDose_days(String dose_days) {
        this.dose_days = dose_days;
    }

    public String getStart_date() {
        return this.start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return this.end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getPrescribed_drug() {
        return this.prescribed_drug;
    }

    public void setPrescribed_drug(String prescribed_drug) {
        this.prescribed_drug = prescribed_drug;
    }

    public String getPrescribe_unit() {
        return prescribe_unit;
    }

    public void setPrescribe_unit(String prescribe_unit) {
        this.prescribe_unit = prescribe_unit;
    }

    public String getUnit_type() {
        return unit_type;
    }

    public void setUnit_type(String unit_type) {
        this.unit_type = unit_type;
    }

    public String getRequired_prescribe_units() {
        return required_prescribe_units;
    }

    public void setRequired_prescribe_units(String required_prescribe_units) {
        this.required_prescribe_units = required_prescribe_units;
    }

    public String getRequired_issue_units() {
        return required_issue_units;
    }

    public void setRequired_issue_units(String required_issue_units) {
        this.required_issue_units = required_issue_units;
    }

    public Treatment() {
    }
}
