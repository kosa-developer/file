package managedModal;

import java.io.Serializable;

public class Diagnosis
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Integer record_id;
  private String code;
  private String diagnosis;
  private Integer lastlevel;

  public Diagnosis()
  {
  }

  public Diagnosis(String diagnosis)
  {
    this.diagnosis = diagnosis;
  }

  public Diagnosis(Integer record_id, String diagnosis) {
    this.record_id = record_id;
    this.diagnosis = diagnosis;
  }

  public Diagnosis(Integer record_id, String code, String diagnosis, Integer lastlevel) {
    this.record_id = record_id;
    this.code = code;
    this.diagnosis = diagnosis;
    this.lastlevel = lastlevel;
  }

  public Integer getRecord_id() {
    return this.record_id;
  }

  public void setRecord_id(Integer record_id) {
    this.record_id = record_id;
  }

  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDiagnosis() {
    return this.diagnosis;
  }

  public void setDiagnosis(String diagnosis) {
    this.diagnosis = diagnosis;
  }

  public Integer getLastlevel() {
    return this.lastlevel;
  }

  public void setLastlevel(Integer lastlevel) {
    this.lastlevel = lastlevel;
  }
}