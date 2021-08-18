package managedModal;

import java.io.Serializable;

public class Consultant
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String patientID;
  private String patientName;
  private String visitReason;
  private String problem;
  private String taskId;
  private String status;
  private String consultant_name;
  private Integer age;
  private Integer weight;
  private Integer temperature;
  private String staff_from;
  private String staff_to;
  private Integer color_flag;

  public Consultant(String patientID, String patientName, String visitReason, String problem)
  {
    this.patientID = patientID;
    this.patientName = patientName;
    this.visitReason = visitReason;
    this.problem = problem;
  }

  public Consultant(String patientID, String patientName, String visitReason, String problem, String taskId) {
    this.patientID = patientID;
    this.patientName = patientName;
    this.visitReason = visitReason;
    this.problem = problem;
    this.taskId = taskId;
  }

  public Consultant(String patientID, String patientName, String visitReason, String problem, String taskId, String status) {
    this.patientID = patientID;
    this.patientName = patientName;
    this.visitReason = visitReason;
    this.problem = problem;
    this.taskId = taskId;
    this.status = status;
  }

  public Consultant(String patientID, String patientName, String visitReason, String problem, String taskId, String status, String consultant_name) {
    this.patientID = patientID;
    this.patientName = patientName;
    this.visitReason = visitReason;
    this.problem = problem;
    this.taskId = taskId;
    this.status = status;
    this.consultant_name = consultant_name;
  }

  public Consultant(String taskId, String patientID, String patientName, Integer age, Integer weight, Integer temperature, String visitReason, String problem, Integer color_flag) {
    this.patientID = patientID;
    this.patientName = patientName;
    this.visitReason = visitReason;
    this.problem = problem;
    this.taskId = taskId;
    this.age = age;
    this.weight = weight;
    this.temperature = temperature;
    this.color_flag = color_flag;
  }

  public Consultant(String taskId, String patientID, String patientName, Integer age, Integer weight, Integer temperature, String visitReason, String problem, String staff_from, String staff_to) {
    this.patientID = patientID;
    this.patientName = patientName;
    this.visitReason = visitReason;
    this.problem = problem;
    this.taskId = taskId;
    this.age = age;
    this.weight = weight;
    this.temperature = temperature;
    this.staff_from = staff_from;
    this.staff_to = staff_to;
  }

  public Integer getColor_flag() {
    return this.color_flag;
  }

  public void setColor_flag(Integer color_flag) {
    this.color_flag = color_flag;
  }

  public String getConsultant_name()
  {
    return this.consultant_name;
  }

  public void setConsultant_name(String consultant_name) {
    this.consultant_name = consultant_name;
  }

  public String getStatus()
  {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTaskId()
  {
    return this.taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public String getPatientID()
  {
    return this.patientID;
  }

  public void setPatientID(String patientID) {
    this.patientID = patientID;
  }

  public String getPatientName() {
    return this.patientName;
  }

  public void setPatientName(String patientName) {
    this.patientName = patientName;
  }

  public String getVisitReason() {
    return this.visitReason;
  }

  public void setVisitReason(String visitReason) {
    this.visitReason = visitReason;
  }

  public String getProblem() {
    return this.problem;
  }

  public void setProblem(String problem) {
    this.problem = problem;
  }

  public Integer getAge() {
    return this.age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Integer getWeight() {
    return this.weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  public Integer getTemperature() {
    return this.temperature;
  }

  public void setTemperature(Integer temperature) {
    this.temperature = temperature;
  }

  public String getStaff_from() {
    return this.staff_from;
  }

  public void setStaff_from(String staff_from) {
    this.staff_from = staff_from;
  }

  public String getStaff_to() {
    return this.staff_to;
  }

  public void setStaff_to(String staff_to) {
    this.staff_to = staff_to;
  }
}