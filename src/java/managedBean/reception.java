/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

/**
 *
 * @author Programmer
 */
import org.primefaces.event.CaptureEvent;

import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import managedDao.ConsultantDAO;
import managedDao.DynamicFunctionsDAO;
import managedDao.LaboratoryDAO;
import managedDao.ReceptionDAO;
import managedModal.LabTest;
import managedModal.ReceptionTask;
import managedModal.TestCategory;
import menu.AppMenu;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "reception")
@SessionScoped
public class reception implements Serializable {

    private String Height_for_age, Weight_for_age, lab_urgency, referred_from, referral_num, referral_status, alcohol, smoker, imm_status, weight_For_Height, muac, weight_for_height_label, triage_category, problem, visit_reason, temperature, oxy_saturation, heart_pulse, blood_pressure, respiratory_rate, filename, memberID, visittype, category, patientName, patientCategory, nin, ConvertedAge, gender, phone, district, subcounty, village, nxtname, relationship, nxtphone;
    private boolean itn_in_use, lab, xray, ward, surgery, consult, skip_accounts = false;
    private Integer Age, test_category_id, test_name_id, price;
    private Date dob;
    private double weight, height, bmi_zscore;
    private List<TestCategory> test_categories;
    private UUID trans_id, generated_task_id;
    private List<LabTest> tests_requested;

    public boolean isLab() {
        return lab;
    }

    public void setLab(boolean lab) {
        this.lab = lab;
    }

    public boolean isXray() {
        return xray;
    }

    public String getWeight_for_age() {
        return Weight_for_age;
    }

    public String getHeight_for_age() {
        return Height_for_age;
    }

    public void setHeight_for_age(String Height_for_age) {
        this.Height_for_age = Height_for_age;
    }

    public void setWeight_for_age(String Weight_for_age) {
        this.Weight_for_age = Weight_for_age;
    }

    public void setXray(boolean xray) {
        this.xray = xray;
    }

    public boolean isWard() {
        return ward;
    }

    public void setWard(boolean ward) {
        this.ward = ward;
    }

    public boolean isSurgery() {
        return surgery;
    }

    public void setSurgery(boolean surgery) {
        this.surgery = surgery;
    }

    public boolean isConsult() {
        return consult;
    }

    public void setConsult(boolean consult) {
        this.consult = consult;
    }

    public Integer getTest_category_id() {
        return test_category_id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<TestCategory> getTest_categories() {
        return test_categories;
    }

    public UUID getTrans_id() {
        return trans_id;
    }

    public String getLab_urgency() {
        return lab_urgency;
    }

    public void setLab_urgency(String lab_urgency) {
        this.lab_urgency = lab_urgency;
    }

    public void setTrans_id(UUID trans_id) {
        this.trans_id = trans_id;
    }

    public void setTest_categories(List<TestCategory> test_categories) {
        this.test_categories = test_categories;
    }

    public void setTest_category_id(Integer test_category_id) {
        this.test_category_id = test_category_id;
    }

    public List<LabTest> getTests_requested() {
        return tests_requested;
    }

    public void setTests_requested(List<LabTest> tests_requested) {
        this.tests_requested = tests_requested;
    }

    public Integer getTest_name_id() {
        return test_name_id;
    }

    public void setTest_name_id(Integer test_name_id) {
        this.test_name_id = test_name_id;
    }

    public String getReferred_from() {
        return referred_from;
    }

    public void setReferred_from(String referred_from) {
        this.referred_from = referred_from;
    }

    public UUID getGenerated_task_id() {
        return generated_task_id;
    }

    public void setGenerated_task_id(UUID generated_task_id) {
        this.generated_task_id = generated_task_id;
    }

    public String getReferral_num() {
        return referral_num;
    }

    public void setReferral_num(String referral_num) {
        this.referral_num = referral_num;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public String getReferral_status() {
        return referral_status;
    }

    public void setReferral_status(String referral_status) {
        this.referral_status = referral_status;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    public String getTriage_category() {
        return triage_category;
    }

    public String getSmoker() {
        return smoker;
    }

    public void setSmoker(String smoker) {
        this.smoker = smoker;
    }

    public String getImm_status() {
        return imm_status;
    }

    public void setImm_status(String imm_status) {
        this.imm_status = imm_status;
    }

    public String getMuac() {
        return muac;
    }

    public void setMuac(String muac) {
        this.muac = muac;
    }

    public double getBmi_zscore() {
        return bmi_zscore;
    }

    public void setBmi_zscore(double bmi_zscore) {
        this.bmi_zscore = bmi_zscore;
    }

    public void setTriage_category(String triage_category) {
        this.triage_category = triage_category;
    }

    public String getWeight_For_Height() {
        return weight_For_Height;
    }

    public void setWeight_For_Height(String weight_For_Height) {
        this.weight_For_Height = weight_For_Height;
    }

    public String getWeight_for_height_label() {
        return weight_for_height_label;
    }

    public boolean isSkip_accounts() {
        return skip_accounts;
    }

    public void setSkip_accounts(boolean skip_accounts) {
        this.skip_accounts = skip_accounts;
    }

    public void setWeight_for_height_label(String weight_for_height_label) {
        this.weight_for_height_label = weight_for_height_label;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getVisit_reason() {
        return visit_reason;
    }

    public void setVisit_reason(String visit_reason) {
        this.visit_reason = visit_reason;
    }

    public boolean isItn_in_use() {
        return itn_in_use;
    }

    public void setItn_in_use(boolean itn_in_use) {
        this.itn_in_use = itn_in_use;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getOxy_saturation() {
        return oxy_saturation;
    }

    public void setOxy_saturation(String oxy_saturation) {
        this.oxy_saturation = oxy_saturation;
    }

    public String getHeart_pulse() {
        return heart_pulse;
    }

    public void setHeart_pulse(String heart_pulse) {
        this.heart_pulse = heart_pulse;
    }

    public String getBlood_pressure() {
        return blood_pressure;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public void setBlood_pressure(String blood_pressure) {
        this.blood_pressure = blood_pressure;
    }

    public String getRespiratory_rate() {
        return respiratory_rate;
    }

    public void setRespiratory_rate(String respiratory_rate) {
        this.respiratory_rate = respiratory_rate;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getVisittype() {
        return visittype;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSubcounty() {
        return subcounty;
    }

    public void setSubcounty(String subcounty) {
        this.subcounty = subcounty;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getNxtname() {
        return nxtname;
    }

    public void setNxtname(String nxtname) {
        this.nxtname = nxtname;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getNxtphone() {
        return nxtphone;
    }

    public void setNxtphone(String nxtphone) {
        this.nxtphone = nxtphone;
    }

    public String getConvertedAge() {
        return ConvertedAge;
    }

    public void setConvertedAge(String ConvertedAge) {
        this.ConvertedAge = ConvertedAge;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer Age) {
        this.Age = Age;
    }

    public void setVisittype(String visittype) {
        this.visittype = visittype;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientCategory() {
        return patientCategory;
    }

    public void setPatientCategory(String patientCategory) {
        this.patientCategory = patientCategory;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    private String getRandomImageName() {
        int i = (int) (Math.random() * 10000000);

        return String.valueOf(i);
    }

    public String getFilename() {
        return filename;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public reception() {
        try {

            this.memberID = DynamicFunctionsDAO.autoID_many("frontdesk_tasks", "Patient_Id", 5);

        } catch (Exception e) {
            this.memberID = memberID;
        }

        lab = false;
        xray = false;
        ward = false;
        surgery = false;
        consult = false;
    }

    public void fowardingbutton(String buttonclicked) {
        if (buttonclicked.equals("lab")) {
            lab = true;
            xray = false;
            ward = false;
            surgery = false;
            consult = false;
        } else if (buttonclicked.equals("xray")) {
            lab = false;
            xray = true;
            ward = false;
            surgery = false;
            consult = false;
        } else if (buttonclicked.equals("ward")) {
            lab = false;
            xray = false;
            ward = true;
            surgery = false;
            consult = false;
        } else if (buttonclicked.equals("surgery")) {
            lab = false;
            xray = false;
            ward = false;
            surgery = true;
            consult = false;
        } else if (buttonclicked.equals("consult")) {
            lab = false;
            xray = false;
            ward = false;
            surgery = false;
            consult = true;
        } else {
            lab = false;
            xray = false;
            ward = false;
            surgery = false;
            consult = false;
        }

    }

    public void oncapture(CaptureEvent captureEvent) {
        this.filename = getRandomImageName();
        byte[] data = captureEvent.getData();

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String newFileName = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "demo"
                + File.separator + "images" + File.separator + "photocam" + File.separator + filename + ".jpeg";

        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        } catch (IOException e) {
            throw new FacesException("Error in writing captured image.", e);
        }

    }

    public void returndob() {
        try {

            Integer aggediff, age = Integer.parseInt(ConvertedAge);
            aggediff = 0;
            this.Age = age;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date now = new Date();
            String yy, mm, dd, newdob, date = format.format(now);
            yy = date.substring(0, 4);
            mm = date.substring(5, 7);
            dd = date.substring(8, 10);

            aggediff = Integer.parseInt(yy) - age;
            newdob = aggediff + "-" + mm + "-" + dd;
            this.dob = new SimpleDateFormat("yyyy-MM-dd").parse(newdob);

            calculate_bmi_or_zscore();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "please enter figures only", "Successful"));

        }
    }

    public void returnAge(SelectEvent event) {

        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String yy, mm, dd, date;
            date = format.format(event.getObject());
            yy = date.substring(0, 4);
            mm = date.substring(5, 7);
            dd = date.substring(8, 10);

            LocalDate l = LocalDate.of(Integer.parseInt(yy), Integer.parseInt(mm), Integer.parseInt(dd)); //specify year, month, date directly
            LocalDate now = LocalDate.now(); //gets localDate
            Period diff = Period.between(l, now); //difference between the dates is calculated
            this.Age = diff.getYears();
            calculate_bmi_or_zscore();
            calculateAge(diff.getYears() + "", diff.getDays() + "", diff.getMonths() + "");
            ConvertedAge = (diff.getYears() == 0 && diff.getMonths() <= 12) ? diff.getMonths() + " Months, " + diff.getDays() + " Days"
                    : (diff.getYears() < 5 && diff.getMonths() != 0) ? diff.getMonths() + " Yrs, " + diff.getMonths() + " Months"
                    : (diff.getYears() >= 5) ? diff.getYears() + " Yrs" : diff.getYears() + " Yrs";

        } catch (Exception e) {

        }

    }

    public void calculateAge(String age_, String days_, String month_) {
        ConvertedAge = (age_.equals("")) ? "" : (Integer.parseInt(age_) == 0 && Integer.parseInt(month_) <= 12) ? month_ + " Months, " + days_ + " Days"
                : (Integer.parseInt(age_) < 5 && Integer.parseInt(age_) != 0) ? age_ + " Yrs, " + month_ + " Months"
                : (Integer.parseInt(age_) >= 5) ? age_ + " Yrs" : "";
    }

    public void calculate_weightForAge_or_HeightForAge_zscore() {
        try {
            if (this.Age == null || this.Age > 5) {
                this.Weight_for_age = "";
                this.setHeight_for_age("");
            } else {
                float ConvertedAge = 0;
                String AgeUnit = "";
                String Measurement = "";
                String gender = "";
                gender = (this.gender.equals("Male")) ? "M" : ((this.gender.equals("Female")) ? "F" : "");

                int patient_age, patient_age_month, patient_age_days;

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String yy, mm, dd, date;
                date = format.format(dob);
                yy = date.substring(0, 4);
                mm = date.substring(5, 7);
                dd = date.substring(8, 10);
                LocalDate l = LocalDate.of(Integer.parseInt(yy), Integer.parseInt(mm), Integer.parseInt(dd)); //specify year, month, date directly
                LocalDate now = LocalDate.now(); //gets localDate
                Period diff = Period.between(l, now); //difference between the dates is calculated

                patient_age = diff.getYears();
                patient_age_month = diff.getMonths();
                patient_age_days = diff.getDays();

                Measurement = "Length";
                AgeUnit = "Week";
                ConvertedAge = 0;

                if (patient_age > 0) {
                    Measurement = (patient_age >= 2) ? "Height" : "Length";
                    AgeUnit = "Month";

                    if (patient_age_month > 0) {
                        ConvertedAge = (patient_age * 12) + patient_age_month;
                    } else {
                        ConvertedAge = (patient_age * 12);
                    }
                } else if (patient_age_month > 0) {
                    Measurement = "Length";
                    AgeUnit = "Month";
                    if ((patient_age_month) < 3) {
                        AgeUnit = "Week";
                        if (((patient_age_days / 30.4375) * 4) <= 13) {
                            AgeUnit = "Week";
                            ConvertedAge = (patient_age_days / 7);
                        } else {
                            AgeUnit = "Month";
                            ConvertedAge = patient_age_month;
                        }

                    } else {
                        AgeUnit = "Month";
                        ConvertedAge = patient_age_month;
                    }
                } else if (patient_age_days > 0) {
                    Measurement = "Length";
                    AgeUnit = "Week";
                    ConvertedAge = (patient_age_days / 7);
                } else {
                    Measurement = "Length";
                    AgeUnit = "Week";
                    ConvertedAge = 0;
                }
                this.Weight_for_age = ReceptionDAO.Report_Get_zscore_weightforage(AgeUnit, ConvertedAge, gender, this.weight);
                this.Height_for_age = ReceptionDAO.Report_Get_zscore_heightforage(AgeUnit, ConvertedAge, gender, Measurement, this.height);

//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AgeUnit=" + AgeUnit + " ,age=" + ConvertedAge + ", gender=" + gender + ", Measurement=" + Measurement + ", height_for_age=" + reception_info.getHeight_for_age() + ", temp_id_extension=" + this.patient_id_extension, "Successful"));
            }

        } catch (Exception ex) {
            System.err.println("ReceptionBean Error: Method: calculate_weightForAge_or_HeightForAge_zscore " + ex.getMessage());
        }
    }

    public void calculate_bmi_or_zscore() {
        try {

            calculate_weightForAge_or_HeightForAge_zscore();
            if (this.Age == null) {
                this.weight_For_Height = "";
                this.weight_for_height_label = "";
            } else if (this.Age >= 5) {
                this.weight_for_height_label = "BMI";
                if (this.weight == 0 || this.height == 0) {
                    this.weight_For_Height = "";
                } else {
                    bmi_zscore = this.weight / (this.height * this.height / (100 * 100));
                    float f = (Math.round(bmi_zscore * 10));
                    this.weight_For_Height = f / 10 + " (" + ReceptionDAO.Reception_Get_BMI_result(this.Age, this.gender, bmi_zscore) + ")";
                }
            } else {
                this.weight_for_height_label = "Weight for Height(Z-score)";
                if (this.weight == 0 || this.height == 0) {
                    this.weight_For_Height = "";
                } else {
                    if (this.height < 45 || this.Age >= 2 & this.height < 65
                            || this.height > 120 || this.Age < 2 & this.height > 110) {
                        this.weight_For_Height = "Height out of z-score range";
                    } else {
                        this.weight_For_Height = ReceptionDAO.Reception_Get_Zscore(this.Age, this.gender, this.weight, this.height);
                    }
                }
            }

        } catch (Exception ex) {
            System.err.println("ReceptionBean Error: Method: calculate_bmi_or_zscore " + ex.getMessage());
        }
    }

    public Date getMinAge() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.YEAR, 0);
        return currentDate.getTime();
    }

    public Date getMaxAge() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.YEAR, -150);
        return currentDate.getTime();
    }

    public List<TestCategory> reception_get_test_categoryz() {
        try {
            test_categories = LaboratoryDAO.Laboratory_Get_Test_Category();
            return test_categories;
        } catch (Exception ex) {
            System.out.println("Exception In Consultant Bean: " + ex.getMessage());
            return null;
        }
    }

    public void priceReturn() {
        try {
            this.price = (DynamicFunctionsDAO.dynamicgetsingle_variable("price", "test_names", "test_id", this.test_name_id.toString()).equals("")) ? null : Integer.parseInt(DynamicFunctionsDAO.dynamicgetsingle_variable("price", "test_names", "test_id", this.test_name_id.toString()));
        } catch (Exception ex) {
            this.price = null;
            System.out.println("Exception In Consultant Bean: " + ex.getMessage());
        }
    }

    public void reception_add_test_request() {

        HttpSession session = SessionUtils.getSession();

        String user_id = session.getAttribute("userid").toString();

        try {
            if (generated_task_id == null) {
                generated_task_id = UUID.randomUUID();
            } else {
                //-- do nothing
            }
            if (trans_id == null) {
                trans_id = UUID.randomUUID();
            }
            String[][] field = {{"Patient_Id", "Track_Id"}, {memberID, trans_id.toString()}};
            if (DynamicFunctionsDAO.Check_if_exsists(memberID, "frontdesk_tasks", "Patient_Id")) {

                if (LaboratoryDAO.Laboratory_Add_Test_Request(this.test_name_id, this.trans_id.toString(), user_id, "OPD", generated_task_id.toString(), this.price)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Test Request Added Succesfully", "Successful"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Please Contact Your Administrator", "Failure"));
                }

                this.tests_requested = LaboratoryDAO.Laboratory_Get_Tests_Requested(String.valueOf(this.trans_id));

            } else {
                if (DynamicFunctionsDAO.Insert("frontdesk_tasks", field)) {
                    if (this.test_category_id == null || this.test_name_id == null || this.price == null) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Missing Compulsory Information", "Failure"));
                    } else {
                        if (LaboratoryDAO.Laboratory_Add_Test_Request(this.test_name_id, this.trans_id.toString(), user_id, "OPD", generated_task_id.toString(), this.price)) {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Test Request Added Succesfully", "Successful"));
                        } else {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Please Contact Your Administrator", "Failure"));
                        }

                        this.tests_requested = LaboratoryDAO.Laboratory_Get_Tests_Requested(String.valueOf(this.trans_id));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "error", "Success"));

                }
            }

            this.test_category_id = null;
            this.test_name_id = null;
            this.price = null;
        } catch (Exception ex) {
            System.err.println("Error Message: " + ex.getMessage());
        }
    }

    public void reception_laboratory_delete_pending(Integer request_id) {
        try {
            if (!LaboratoryDAO.Laboratory_Delete_Pending(request_id)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Please Contact Your Administrator", "Failure"));
            } else {
                this.tests_requested = LaboratoryDAO.Laboratory_Get_Tests_Requested(String.valueOf(this.trans_id));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Test Request Delete", "Successful"));
            }
        } catch (Exception ex) {
            System.out.println("Error Message: " + ex.getMessage());
        }
    }

    public void renamephoto() {

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String oldFileName = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "demo"
                + File.separator + "images" + File.separator + "photocam" + File.separator + filename + ".jpeg";
        String newFileName = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "demo"
                + File.separator + "images" + File.separator + "photocam" + File.separator + this.memberID + ".jpeg";
        File sourceFile = new File(oldFileName);
        File destFile = new File(newFileName);

        if (sourceFile.renameTo(destFile)) {

        } else {

        }
    }

    public void savedata() {
        calculate_bmi_or_zscore();
        renamephoto();
        String date, nowdate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.format(dob);
        HttpSession session = SessionUtils.getSession();
        String user_id = session.getAttribute("userid").toString();
        Date now = new Date();
        nowdate = format.format(now);
        if (trans_id == null) {
            trans_id = UUID.randomUUID();
        }

        String[][] recptiondata = {{"Patient_Id", "Track_Id", "Patient_Name", "Patient_Category", "Patient_NIN", "DOB", "Age",
            "Patient_phone", "Gender", "Village_Name", "Subcounty_Name", "District", "Next_Kin", "Next_Kin_Relationship", "Next_Kin_Phone",
            "Referral_Status", "Referred_From", "Referral_Num", "Weight", "Height", "Weight_For_Height", "Weight_for_age", "Height_for_age",
            "Temperature", "Oxy_Saturation", "Heart_Pulse", "Blood_Pressure", "Respiratory_Rate", "ItnUse",
            "Problem", "muac", "Immunisation", "Smoker", "Alcohol", "Triage_Category",
            "VisitDate", "Reception_User_Id"},
        {memberID, trans_id.toString(), patientName, patientCategory, nin, date, Age.toString(), phone, gender, village, subcounty, district, nxtname, relationship,
            nxtphone, referral_status, referred_from, referral_num, String.valueOf(weight), String.valueOf(height), weight_For_Height, Weight_for_age,
            Height_for_age, temperature, oxy_saturation, heart_pulse, blood_pressure, respiratory_rate, String.valueOf(itn_in_use),
            problem, muac, imm_status, smoker, alcohol, triage_category, nowdate, user_id}};

        try {
            if (DynamicFunctionsDAO.Check_if_exsists(memberID, "frontdesk_tasks", "Patient_Id")) {
                if (DynamicFunctionsDAO.Edit("frontdesk_tasks", recptiondata, "Patient_Id", this.memberID)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data has been Updated successfully", "Success"));
                }

            } else {
                if (DynamicFunctionsDAO.Insert("frontdesk_tasks", recptiondata)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data has been saved successfully", "Success"));

                }
            }

        } catch (Exception e) {

        }
    }

    public void forwardpatient() {
        HttpSession session = SessionUtils.getSession();
        String user_id = session.getAttribute("userid").toString();
        String fowardsection = (lab) ? "Laboratory" : (xray) ? "X-ray" : (ward) ? "Ward" : (surgery) ? "Surgery" : (consult) ? "Consultant" : "";

        if (fowardsection.equals("") || fowardsection.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please choose where to foward. and try again", "Success"));

        } else {
            String[][] fowardingdata = {{"Forward_To_Section", "Skip_Accounts"},
            {fowardsection, String.valueOf(skip_accounts)}};

            try {
                if (DynamicFunctionsDAO.Check_if_exsists(this.memberID, "frontdesk_tasks", "Patient_Id")) {
                    if (DynamicFunctionsDAO.Edit("frontdesk_tasks", fowardingdata, "Patient_Id", this.memberID)) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Patient has been fowarded", "Success"));
                        if ("Consultant".equalsIgnoreCase(fowardsection)) {
                            if (ReceptionDAO.Reception_Consultant_Pending(trans_id.toString(), user_id, false)) {
                                ConsultantDAO.Consultant_Time_Audit_Start(trans_id.toString(), user_id);
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Infomation Added Successfully", "Successful"));
                            } else {
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Task Saved Successfully BUT has Not Been Forward.Contact The Administrator To Finf Out The Possible Cause of the Error", "Failure"));
                            }
                        } else if ("Laboratory".equalsIgnoreCase(fowardsection)) {

                            if (LaboratoryDAO.Laboratory_Add_Pending(String.valueOf(trans_id), user_id, "", lab_urgency, reception_generate_lab_id(String.valueOf(trans_id)))) {
                                LaboratoryDAO.Laboratory_Time_Audit_Add(String.valueOf(trans_id), user_id);
                            }
                        }

                        clearfields();
                        this.tests_requested = LaboratoryDAO.Laboratory_Get_Tests_Requested(String.valueOf(this.trans_id));

                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occured please contact the administrator", "Success"));

                    }

                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occured please contact the administrator", "Success"));

            }

        }
    }

    public void confirmrequest() {
        HttpSession session = SessionUtils.getSession();
        String user_id = session.getAttribute("userid").toString();

        String[][] fowardingdata = {{"Forward_To_Section", "Skip_Accounts"},
        {"Laboratory", String.valueOf(skip_accounts)}};

        try {
            if (DynamicFunctionsDAO.Check_if_exsists(this.memberID, "frontdesk_tasks", "Patient_Id")) {
                if (DynamicFunctionsDAO.Edit("frontdesk_tasks", fowardingdata, "Patient_Id", this.memberID)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Patient has been fowarded", "Success"));

                    if (LaboratoryDAO.Laboratory_Add_Pending(String.valueOf(trans_id), user_id, "", lab_urgency, reception_generate_lab_id(String.valueOf(trans_id)))) {
                        LaboratoryDAO.Laboratory_Time_Audit_Add(String.valueOf(trans_id), user_id);
                    }

                    clearfields();
                    this.tests_requested = LaboratoryDAO.Laboratory_Get_Tests_Requested(String.valueOf(this.trans_id));

                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occured please contact the administrator", "Success"));

                }

            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occured please contact the administrator", "Success"));

        }

    }

    public void clearfields() {
        lab_urgency = "";
        referred_from = "";
        referral_num = "";
        referral_status = "";
        alcohol = "";
        smoker = "";
        imm_status = "";
        weight_For_Height = "";
        muac = "";
        weight_for_height_label = "";
        triage_category = "";
        problem = "";
        visit_reason = "";
        temperature = "";
        oxy_saturation = "";
        heart_pulse = "";
        blood_pressure = "";
        respiratory_rate = "";
        filename = "";

        try {

            this.memberID = DynamicFunctionsDAO.autoID_many("frontdesk_tasks", "Patient_Id", 5);

        } catch (Exception e) {
            this.memberID = memberID;
        }
        visittype = "";
        category = "";
        patientName = "";
        patientCategory = "";
        nin = "";
        ConvertedAge = "";
        gender = "";
        phone = "";
        district = "";
        subcounty = "";
        village = "";
        nxtname = "";
        relationship = "";
        nxtphone = "";
        itn_in_use = false;
        lab = false;
        xray = false;
        ward = false;
        surgery = false;
        consult = false;
        Age = null;
        this.trans_id = null;

        price = null;
        dob = null;
        weight = Double.NaN;
        height = Double.NaN;
        bmi_zscore = Double.NaN;
        Height_for_age = "";
        Weight_for_age = "";
        skip_accounts = false;
    }

    public List<ReceptionTask> reception_get_transactions() {
        try {
            return ReceptionDAO.Reception_Get_Transactions();
        } catch (Exception ex) {
            System.err.println("ReceptionBean Error: Method: reception_get_transactions " + ex.getMessage());
        }
        return null;
    }

    private String reception_generate_lab_id(String task_id) {
        try {
            Integer pending_count = LaboratoryDAO.Laboratory_Get_Pending_Count();
            Integer completed_count = LaboratoryDAO.Laboratory_Get_Completed_Count();
            String lab_id = LaboratoryDAO.Laboratory_Get_Lab_Id(task_id);
            Calendar cal = Calendar.getInstance();
            Integer year = cal.get(Calendar.YEAR);

            if (lab_id == null) {
                Integer total_count = pending_count + completed_count;
                if (total_count < 10) {
                    //format :: 000000-current_year
                    return "00000" + total_count.toString() + "-" + year.toString();
                } else if ((total_count < 100) && (total_count > 9)) {
                    return "0000" + total_count.toString() + "-" + year.toString();
                } else if ((total_count < 1000) && (total_count > 99)) {
                    return "000" + total_count.toString() + "-" + year.toString();
                } else if (total_count < 10000 && total_count > 999) {
                    return "00" + total_count.toString() + "-" + year.toString();
                } else if (total_count < 100000 && total_count > 9999) {
                    return "0" + total_count.toString() + "-" + year.toString();
                } else if (total_count > 99999) {
                    return total_count.toString() + "-" + year.toString();
                }
                return total_count.toString() + "-" + year.toString();
            } else {
                return lab_id;
            }

        } catch (Exception ex) {
            System.err.println("ReceptionBean Error: Method: reception_get_non_registered_patient_id " + ex.getMessage());
            return "";
        }

    }
}
