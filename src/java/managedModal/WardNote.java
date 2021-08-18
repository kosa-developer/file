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
public class WardNote implements Serializable {

    private static final long serialVersionUID = 1L;

    private String note_id, track_id, history_symptoms, examination_findings, impressions, management_plan, other_notes, note_by, note_date;

    public String getNote_id() {
        return note_id;
    }

    public void setNote_id(String note_id) {
        this.note_id = note_id;
    }

    public String getTrack_id() {
        return track_id;
    }

    public void setTrack_id(String track_id) {
        this.track_id = track_id;
    }

    public String getHistory_symptoms() {
        return history_symptoms;
    }

    public void setHistory_symptoms(String history_symptoms) {
        this.history_symptoms = history_symptoms;
    }

    public String getExamination_findings() {
        return examination_findings;
    }

    public void setExamination_findings(String examination_findings) {
        this.examination_findings = examination_findings;
    }

    public String getImpressions() {
        return impressions;
    }

    public void setImpressions(String impressions) {
        this.impressions = impressions;
    }

    public String getManagement_plan() {
        return management_plan;
    }

    public void setManagement_plan(String management_plan) {
        this.management_plan = management_plan;
    }

    public String getOther_notes() {
        return other_notes;
    }

    public void setOther_notes(String other_notes) {
        this.other_notes = other_notes;
    }

    public String getNote_by() {
        return note_by;
    }

    public void setNote_by(String note_by) {
        this.note_by = note_by;
    }

    public String getNote_date() {
        return note_date;
    }

    public void setNote_date(String note_date) {
        this.note_date = note_date;
    }

}
