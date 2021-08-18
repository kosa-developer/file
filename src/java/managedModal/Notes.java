package managedModal;

import java.io.Serializable;

public class Notes
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private String task_id;
    private String staff_id;
    private String note;
    private String note_date_time;
    private String note_by;
    private Integer note_id;

    public Integer getNote_id() {
        return note_id;
    }

    public void setNote_id(Integer note_id) {
        this.note_id = note_id;
    }

    public String getNote_by() {
        return note_by;
    }

    public void setNote_by(String note_by) {
        this.note_by = note_by;
    }

    public String getNote_date_time() {
        return note_date_time;
    }

    public void setNote_date_time(String note_date_time) {
        this.note_date_time = note_date_time;
    }

    public String getTask_id() {
        return this.task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getStaff_id() {
        return this.staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}