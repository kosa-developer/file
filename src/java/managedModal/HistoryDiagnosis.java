/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedModal;

/**
 *
 * @author romugabi
 */
public class HistoryDiagnosis {
    private String diagnosis;
    private String diagnosis_category;
    private String diagnosed_by;
    private String diagnosis_date;

    public HistoryDiagnosis() {
    }

    public HistoryDiagnosis(String diagnosis, String diagnosed_by, String diagnosis_date) {
        this.diagnosis = diagnosis;
        this.diagnosed_by = diagnosed_by;
        this.diagnosis_date = diagnosis_date;
    }
    
    
    //diagnosis,diagnosis_date,diagnosed_by

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDiagnosis_category() {
        return diagnosis_category;
    }

    public void setDiagnosis_category(String diagnosis_category) {
        this.diagnosis_category = diagnosis_category;
    }

    public String getDiagnosed_by() {
        return diagnosed_by;
    }

    public void setDiagnosed_by(String diagnosed_by) {
        this.diagnosed_by = diagnosed_by;
    }

    public String getDiagnosis_date() {
        return diagnosis_date;
    }

    public void setDiagnosis_date(String diagnosis_date) {
        this.diagnosis_date = diagnosis_date;
    }
}
