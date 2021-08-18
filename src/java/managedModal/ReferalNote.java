package managedModal;

import java.io.Serializable;
import java.util.Date;

public class ReferalNote
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String task_id;
  private String refered_to;
  private String referal_no;
  private Date first_visit_date;
  private String history_symptoms;
  private String referal_reason;
  private String refered_by;
  private String referal_date;

  public String getTask_id()
  {
    return this.task_id;
  }

  public void setTask_id(String task_id) {
    this.task_id = task_id;
  }

  public String getRefered_to() {
    return this.refered_to;
  }

  public void setRefered_to(String refered_to) {
    this.refered_to = refered_to;
  }

  public String getReferal_no() {
    return this.referal_no;
  }

  public void setReferal_no(String referal_no) {
    this.referal_no = referal_no;
  }

  public Date getFirst_visit_date() {
    return this.first_visit_date;
  }

  public void setFirst_visit_date(Date first_visit_date) {
    this.first_visit_date = first_visit_date;
  }

  public String getHistory_symptoms() {
    return this.history_symptoms;
  }

  public void setHistory_symptoms(String history_symptoms) {
    this.history_symptoms = history_symptoms;
  }

  public String getReferal_reason() {
    return this.referal_reason;
  }

  public void setReferal_reason(String referal_reason) {
    this.referal_reason = referal_reason;
  }

  public String getRefered_by() {
    return this.refered_by;
  }

  public void setRefered_by(String refered_by) {
    this.refered_by = refered_by;
  }

  public String getReferal_date() {
    return this.referal_date;
  }

  public void setReferal_date(String referal_date) {
    this.referal_date = referal_date;
  }
}