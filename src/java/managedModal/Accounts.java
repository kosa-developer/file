package managedModal;

import java.io.Serializable;

public class Accounts
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private boolean inScheme;
  private String schemeName;
  private Integer amountPaid;
  private Integer frid;
  private String trackID;
  private Integer uid;
  private String forwardedTo;
  private String currentStatus;
  private String patientID;

  public Accounts(boolean inScheme, String schemeName, Integer amountPaid, Integer frid, String trackID, Integer uid)
  {
    this.inScheme = inScheme;
    this.schemeName = schemeName;
    this.amountPaid = amountPaid;
    this.frid = frid;
    this.trackID = trackID;
    this.uid = uid;
  }

  public Accounts(boolean inScheme, Integer amountPaid, String forwardedTo, String currentStatus, String patientID) {
    this.inScheme = inScheme;
    this.amountPaid = amountPaid;
    this.forwardedTo = forwardedTo;
    this.currentStatus = currentStatus;
    this.patientID = patientID;
  }

  public Accounts()
  {
  }

  public String getForwardedTo()
  {
    return this.forwardedTo;
  }

  public void setForwardedTo(String forwardedTo) {
    this.forwardedTo = forwardedTo;
  }

  public String getCurrentStatus() {
    return this.currentStatus;
  }

  public void setCurrentStatus(String currentStatus) {
    this.currentStatus = currentStatus;
  }

  public String getPatientID() {
    return this.patientID;
  }

  public void setPatientID(String patientID) {
    this.patientID = patientID;
  }

  public boolean isInScheme()
  {
    return this.inScheme;
  }

  public void setInScheme(boolean inScheme) {
    this.inScheme = inScheme;
  }

  public String getSchemeName() {
    return this.schemeName;
  }

  public void setSchemeName(String schemeName) {
    this.schemeName = schemeName;
  }

  public Integer getAmountPaid() {
    return this.amountPaid;
  }

  public void setAmountPaid(Integer amountPaid) {
    this.amountPaid = amountPaid;
  }

  public Integer getFrid() {
    return this.frid;
  }

  public void setFrid(Integer frid) {
    this.frid = frid;
  }

  public String getTrackID() {
    return this.trackID;
  }

  public void setTrackID(String trackID) {
    this.trackID = trackID;
  }

  public Integer getUid() {
    return this.uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }
}