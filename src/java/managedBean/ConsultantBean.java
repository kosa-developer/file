package managedBean;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import managedDao.ConsultantDAO;
import managedDao.DynamicFunctionsDAO;
import managedDao.ReceptionDAO;
import managedModal.Consultant;
import managedModal.Diagnosis;
import managedModal.Drugstore;
import managedModal.History;
import managedModal.HistoryDiagnosis;
import managedModal.LabTest;
import managedModal.LabTestResults;
import managedModal.Notes;
import managedModal.ReceptionInfo;
import managedModal.Treatment;
import managedModal.Users;
import managedModal.ReferalNote;
import managedModal.TestCategory;
import managedModal.TestName;
import managedModal.WardNote;
import managedModal.HistoryTreatment;
import managedModal.HistoryClinicalNotes;
import managedModal.HistoryTestResults;
import managedModal.ReceptionTask;


import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "consultantBean")
@SessionScoped
public
        class ConsultantBean implements Serializable {

    private static final
            long serialVersionUID = 1L;
    private static
            Date date;
    private
            String time_zone;
    private
            TimeZone time_zone_default;
    private
            List<Consultant> forwadedJobs;
    private
            Consultant selectedTask;
    private static
            String addedTests;
    private
            String technicianId;
    private
            String consultantId;
    private
            List<LabTestResults> results_for_tests;
    private
            List<HistoryDiagnosis> previous_diagnoses;
    private
            String diagnosis_name;
    private
            String drug_prescribed;
    private
            String diagnosis_type;
    private
            Integer extrapayment;
    private
            String extrapayment_reason;
    private
            String selAcct;
    private
            String client_status;
    private
            List<Users> users;
    private
            List<Diagnosis> diagnosis;
    private
            List<Drugstore> alldrugs;
    private
            List<Drugstore> all_prescribed_drugs;
    private
            Integer drugid;
    private
            String loading_Dose;
    private
            String prescribe_unit;
    private
            String times;
    private
            String times_unit;
    private
            String days;
    private
            String staff_From;
    private
            String staff_To;
    private
            List<Treatment> prescription;
    private
            String dispensary_staff_id;
    private
            List<Users> activedentists;
    private
            String dentist_forward_reason;
    private
            ReceptionInfo reception_info_view;
    private
            String task_id;
    private
            boolean assigned = false;
    private
            List<Notes> clinical_notes;
    private
            String note;
    private
            Integer note_id;
    private
            Integer test_category_id;
    private
            List<LabTest> tests_list;
    private
            List<LabTest> tests_requested;
    private
            Integer test_id;
    private
            Date forward_to_ward_date;
    private
            Integer diagnosis_id;
    private
            List<Diagnosis> diagnosis_icd_list;
    private
            String diagnosis_icdl1;
    private
            List<Diagnosis> diagnosis_icdl2_list;
    private
            String diagnosis_icdl2;
    private
            boolean diagnosis_icdl2_show = false;
    private
            List<Diagnosis> diagnosis_icdl3_list;
    private
            String diagnosis_icdl3;
    private
            boolean diagnosis_icdl3_show = false;
    private
            List<Diagnosis> diagnosis_icdl4_list;
    private
            String diagnosis_icdl4;
    private
            boolean diagnosis_icdl4_show = false;
    private
            String diagnosis_icd_search_string;
    private
            List<Diagnosis> diagnosis_icd_search_list;
    private
            boolean diagnosis_icd_search_flag;
    private
            Integer diagnosis_hmis_id;
    private
            List<Diagnosis> diagnosis_hmis_list;
    private
            List<Diagnosis> diagnosis_hmis_ward_list;
    private
            String diagnosis_category;
    private
            String drug_dose_unit;
    private
            boolean show_investigations = false;
    private
            boolean show_previous_diagnoses = false;
    private
            boolean extra_pay = false;
    private
            boolean exit_without_medication;
    private
            boolean refer_out = false;
    private
            Date follow_up_date;
    private
            ReferalNote referal_note = new ReferalNote();
    private
            boolean forward_to_dentist = false;
    private
            String administer;
    private
            String lab_urgency;
    private
            boolean graphical_show = false;
    private
            boolean tabular_show = false;
    private
            Date report_date;
    private
            String report_type;
    private
            boolean registered;
    private
            String patient_id_extension;
    private
            String pclassification;
    private
            Integer test_name_id;
    private
            String test_category;
    private
            String test_name;
    private
            List<TestCategory> test_categories;
    private
            List<TestName> test_names;
    private
            boolean detail_show;
    private
            boolean fp_screening_show;
    private
            boolean alc_screening_show;
    private
            String patient_screened;
    private
            WardNote wnote;
    private
            String provisional_diagnosis;
    private
            List<Diagnosis> provisional_diagnosis_list;
    //History Variables
    private
            String history_search;
    private
            String gen_history_search;
    private
            String history_search_value;
    private
            String gen_history_search_value;
    private
            List<History> history_Visits;
    private
            List<History> gen_history_Visits;
    private
            ReceptionInfo history_patient_info;
    private
            List<HistoryTreatment> history_prescription;
    private
            List<HistoryDiagnosis> history_diagnosis;
    private
            List<HistoryClinicalNotes> history_clinical_notes;
    private
            List<HistoryTestResults> history_tests_results;
    private
            ReceptionInfo gen_history_patient_info;
    private
            List<HistoryTreatment> gen_history_prescription;
    private
            List<HistoryDiagnosis> gen_history_diagnosis;
    private
            List<HistoryClinicalNotes> gen_history_clinical_notes;
    private
            List<HistoryTestResults> gen_history_tests_results;
    //Syphilis and Viral
    private
            String viral_test_result;
    private
            String syphilis_test_result;
    //HIV
    private
            String hiv_tested_in_6months;
    private
            String hiv_test_today;
    //navigate
    private
            String nagigate_to_page;
    private
            String nagigate_to_page_2;
    private
            String navigate_to_clinic_label;
    private
            String navigate_to_clinic_value;
    private
            String selected_clinic;
    private
            String ipd_comments;
    //xray
  
//    private List<SurgeryView> view_surgery;  This should replace the above line, once rest of code amended to reflect this.
    private
            StreamedContent scanned_image;
    private
            String scanned_image_id;
    //surgery
    private
            String surgery_note;
    //clinics
    private
            String chronic_clinic;
    private
            String clinic_comments;
    private
            String clinic_referral_contact_num;
    private
            Date clinic_referral_proposed_return_date;
    private
            String selected_clinic_id;
    private
            boolean show_alcohol_patients;
    private
            boolean show_alcohol_pending;
    private
            boolean show_alcohol_enrolled;
 
    private
            boolean show_diabetes_patients;
    private
            boolean show_diabetes_pending;
    private
            boolean show_diabetes_enrolled;
  
    private
            boolean show_hyper_patients;
    private
            boolean show_hyper_pending;
    private
            boolean show_hyper_enrolled;
 

//    private String selected_task_id;
    private
            String new_clinic_id;
    private
            String new_serial;
//    private String current_track_id;
   
    private
            String clinics_view_specific_visit_origin;
    private
            boolean alcohol_audit_exists;
    private
            boolean disability_exisits;
    private
            boolean tb_screening_exisits;
    private
            boolean family_planning_screening_exists;
    private
            boolean viral_screening_exists;
    private
            boolean z_score_rendering = false;
    private
            boolean note_medication_changes;

    private
            String clinic_patient_name;

    private
            UUID trans_id;

    private
            boolean need_for_pilliative_care;
    private
            String Pilliatve_comment;
    private
            String assastive_devices;

    private
            String weight_for_height_label;
    private
            StreamedContent Hospital_logo;
    private
            String expected_result_range;
    private
            String resultPresentationDate;
   
    private
            boolean hideHistoryPannel;
    private
            boolean hideclinivisttable;
    private
            boolean examination_for_sign_of_target;
    private
            boolean cvd_risk;
    private
            boolean Screening_diabetic_neuropathy;
    private
            boolean Foot_examination_and_education;
    private
            boolean Current_antidiabetics;
    private
            boolean Current_antihypertensives;
    private
            boolean Other_prescribed_drugs;
    private
            boolean Advice_weight;
    private
            boolean Advice_diet;
    private
            boolean Adherence_Medication;
    private
            boolean Adherence_Footcare;
    private
            boolean Foot_examination_Adherence;
    private
            boolean Adherence_Weight;
    private
            boolean Adherence_Diet;
    private
            boolean Adherence_Appointment;
    private
            boolean medication_prescribed;
    private
            String visitheading;

    public
            String getClient_status() {
        return client_status;
    }

    public
            String getVisitheading() {
        return visitheading;
    }

    public
            void setVisitheading(String visitheading) {
        this.visitheading = visitheading;
    }

    public
            void setClient_status(String client_status) {
        this.client_status = client_status;
    }

    public
            String getResultPresentationDate() {
        return resultPresentationDate;
    }

    public
            void setResultPresentationDate(String resultPresentationDate) {
        this.resultPresentationDate = resultPresentationDate;
    }

    public
            String getExpected_result_range() {
        return expected_result_range;
    }

    public
            void setExpected_result_range(String expected_result_range) {
        this.expected_result_range = expected_result_range;
    }

    public
            StreamedContent getHospital_logo() {
        return Hospital_logo;
    }

    public
            void setHospital_logo(StreamedContent Hospital_logo) {
        this.Hospital_logo = Hospital_logo;
    }

    public
            boolean isNeed_for_pilliative_care() {
        return need_for_pilliative_care;
    }

    public
            void setNeed_for_pilliative_care(boolean need_for_pilliative_care) {
        this.need_for_pilliative_care = need_for_pilliative_care;
    }

    public
            String getPilliatve_comment() {
        return Pilliatve_comment;
    }

    public
            void setPilliatve_comment(String Pilliatve_comment) {
        this.Pilliatve_comment = Pilliatve_comment;
    }

    public
            String getAssastive_devices() {
        return assastive_devices;
    }

    public
            void setAssastive_devices(String assastive_devices) {
        this.assastive_devices = assastive_devices;
    }

    public
            boolean isAlcohol_audit_exists() {
        return alcohol_audit_exists;
    }

    public
            boolean isNote_medication_changes() {
        return note_medication_changes;
    }

    public
            void setNote_medication_changes(boolean note_medication_changes) {
        this.note_medication_changes = note_medication_changes;
    }

    public
            void setAlcohol_audit_exists(boolean alcohol_audit_exists) {
        this.alcohol_audit_exists = alcohol_audit_exists;
    }

    public
            boolean isMedication_prescribed() {
        return medication_prescribed;
    }

    public
            void setMedication_prescribed(boolean medication_prescribed) {
        this.medication_prescribed = medication_prescribed;
    }

    public
            boolean isExamination_for_sign_of_target() {
        return examination_for_sign_of_target;
    }

    public
            void setExamination_for_sign_of_target(boolean examination_for_sign_of_target) {
        this.examination_for_sign_of_target = examination_for_sign_of_target;
    }

    public
            boolean isCvd_risk() {
        return cvd_risk;
    }

    public
            void setCvd_risk(boolean cvd_risk) {
        this.cvd_risk = cvd_risk;
    }

    public
            boolean isScreening_diabetic_neuropathy() {
        return Screening_diabetic_neuropathy;
    }

    public
            void setScreening_diabetic_neuropathy(boolean Screening_diabetic_neuropathy) {
        this.Screening_diabetic_neuropathy = Screening_diabetic_neuropathy;
    }

    public
            boolean isFoot_examination_and_education() {
        return Foot_examination_and_education;
    }

    public
            void setFoot_examination_and_education(boolean Foot_examination_and_education) {
        this.Foot_examination_and_education = Foot_examination_and_education;
    }

    public
            boolean isCurrent_antidiabetics() {
        return Current_antidiabetics;
    }

    public
            void setCurrent_antidiabetics(boolean Current_antidiabetics) {
        this.Current_antidiabetics = Current_antidiabetics;
    }

    public
            boolean isCurrent_antihypertensives() {
        return Current_antihypertensives;
    }

    public
            void setCurrent_antihypertensives(boolean Current_antihypertensives) {
        this.Current_antihypertensives = Current_antihypertensives;
    }

    public
            boolean isOther_prescribed_drugs() {
        return Other_prescribed_drugs;
    }

    public
            void setOther_prescribed_drugs(boolean Other_prescribed_drugs) {
        this.Other_prescribed_drugs = Other_prescribed_drugs;
    }

    public
            boolean isAdvice_weight() {
        return Advice_weight;
    }

    public
            void setAdvice_weight(boolean Advice_weight) {
        this.Advice_weight = Advice_weight;
    }

    public
            boolean isAdvice_diet() {
        return Advice_diet;
    }

    public
            void setAdvice_diet(boolean Advice_diet) {
        this.Advice_diet = Advice_diet;
    }

    public
            boolean isAdherence_Medication() {
        return Adherence_Medication;
    }

    public
            void setAdherence_Medication(boolean Adherence_Medication) {
        this.Adherence_Medication = Adherence_Medication;
    }

    public
            boolean isAdherence_Footcare() {
        return Adherence_Footcare;
    }

    public
            void setAdherence_Footcare(boolean Adherence_Footcare) {
        this.Adherence_Footcare = Adherence_Footcare;
    }

    public
            boolean isFoot_examination_Adherence() {
        return Foot_examination_Adherence;
    }

    public
            void setFoot_examination_Adherence(boolean Foot_examination_Adherence) {
        this.Foot_examination_Adherence = Foot_examination_Adherence;
    }

    public
            boolean isAdherence_Weight() {
        return Adherence_Weight;
    }

    public
            void setAdherence_Weight(boolean Adherence_Weight) {
        this.Adherence_Weight = Adherence_Weight;
    }

    public
            boolean isAdherence_Diet() {
        return Adherence_Diet;
    }

    public
            void setAdherence_Diet(boolean Adherence_Diet) {
        this.Adherence_Diet = Adherence_Diet;
    }

    public
            boolean isAdherence_Appointment() {
        return Adherence_Appointment;
    }

    public
            void setAdherence_Appointment(boolean Adherence_Appointment) {
        this.Adherence_Appointment = Adherence_Appointment;
    }

    public
            boolean isDisability_exisits() {
        return disability_exisits;
    }

    public
            void setDisability_exisits(boolean disability_exisits) {
        this.disability_exisits = disability_exisits;
    }

    public
            boolean isTb_screening_exisits() {
        return tb_screening_exisits;
    }

    public
            void setTb_screening_exisits(boolean tb_screening_exisits) {
        this.tb_screening_exisits = tb_screening_exisits;
    }

    public
            boolean isFamily_planning_screening_exists() {
        return family_planning_screening_exists;
    }

    public
            void setFamily_planning_screening_exists(boolean family_planning_screening_exists) {
        this.family_planning_screening_exists = family_planning_screening_exists;
    }

    public
            boolean isViral_screening_exists() {
        return viral_screening_exists;
    }

    public
            void setViral_screening_exists(boolean viral_screening_exists) {
        this.viral_screening_exists = viral_screening_exists;
    }



  
    public
            ConsultantBean() {
        medication_prescribed = false;
        hideclinivisttable = false;
        note_medication_changes = false;
        examination_for_sign_of_target = false;
        cvd_risk = false;
        Screening_diabetic_neuropathy = false;
        Foot_examination_and_education = false;
        Current_antidiabetics = false;
        Current_antihypertensives = false;
        Other_prescribed_drugs = false;
        Advice_weight = false;
        Advice_diet = false;
        Adherence_Medication = false;
        Adherence_Footcare = false;
        Foot_examination_Adherence = false;
        Adherence_Weight = false;
        Adherence_Diet = false;
        Adherence_Appointment = false;
        visitheading = "Today's Clinic Visit:";
        time_zone = "GMT";
        time_zone_default = TimeZone.getDefault();
       
        history_Visits = new ArrayList();
        history_patient_info = new ReceptionInfo();
        history_prescription = new ArrayList();
        history_diagnosis = new ArrayList();
        history_clinical_notes = new ArrayList();
        history_tests_results = new ArrayList();
        gen_history_Visits = new ArrayList();
        gen_history_patient_info = new ReceptionInfo();
        gen_history_prescription = new ArrayList();
        gen_history_diagnosis = new ArrayList();
        gen_history_clinical_notes = new ArrayList();
        gen_history_tests_results = new ArrayList();
        this.referal_note = new ReferalNote();
        patient_id_extension = "TEMP";
        alc_screening_show = false;
        exit_without_medication = false;
        history_search_value = "n/a";
        surgery_note = "";
        nagigate_to_page = "";
        nagigate_to_page_2 = "";
     
        wnote = new WardNote();
        provisional_diagnosis = "";
        provisional_diagnosis_list = new ArrayList<Diagnosis>();
        trans_id = null;
        show_alcohol_pending = false;
        show_alcohol_enrolled = false;
        show_diabetes_pending = false;
        show_diabetes_enrolled = false;
        show_hyper_pending = false;
        show_hyper_enrolled = false;
        

        clinic_patient_name = null;

        this.need_for_pilliative_care = false;
        this.Pilliatve_comment = "";
        this.assastive_devices = "";
        this.hideHistoryPannel = false;
    }

    public
            String getWeight_for_height_label() {
        return weight_for_height_label;
    }

    public
            void setWeight_for_height_label(String weight_for_height_label) {
        this.weight_for_height_label = weight_for_height_label;
    }

    public
            TimeZone getTime_zone_default() {
        return time_zone_default;
    }

    public
            void setTime_zone_default(TimeZone time_zone_default) {
        this.time_zone_default = time_zone_default;
    }

    public
            String getTime_zone() {
        return time_zone;
    }

    public
            void setTime_zone(String time_zone) {
        this.time_zone = time_zone;
    }

   

    public
            String getScanned_image_id() {
        return scanned_image_id;
    }

    public
            void setScanned_image_id(String scanned_image_id) {
        this.scanned_image_id = scanned_image_id;
    }


    public
            String getIpd_comments() {
        return ipd_comments;
    }

    public
            void setIpd_comments(String ipd_comments) {
        this.ipd_comments = ipd_comments;
    }

    public
            String getNagigate_to_page() {
        return nagigate_to_page;
    }

    public
            void setNagigate_to_page(String nagigate_to_page) {
        this.nagigate_to_page = nagigate_to_page;
    }

    public
            String getNagigate_to_page_2() {
        return nagigate_to_page_2;
    }

    public
            void setNagigate_to_page_2(String nagigate_to_page_2) {
        this.nagigate_to_page_2 = nagigate_to_page_2;
    }

    public
            String getNavigate_to_clinic_label() {
        return navigate_to_clinic_label;
    }

    public
            void setNavigate_to_clinic_label(String navigate_to_clinic_label) {
        this.navigate_to_clinic_label = navigate_to_clinic_label;
    }

    public
            String getNavigate_to_clinic_value() {
        return navigate_to_clinic_value;
    }

    public
            void setNavigate_to_clinic_value(String navigate_to_clinic_value) {
        this.navigate_to_clinic_value = navigate_to_clinic_value;
    }

    public
            String getSelected_clinic() {
        return selected_clinic;
    }

    public
            void setSelected_clinic(String selected_clinic) {
        this.selected_clinic = selected_clinic;
    }

    public
            String getChronic_clinic() {
        return chronic_clinic;
    }

    public
            void setChronic_clinic(String chronic_clinic) {
        this.chronic_clinic = chronic_clinic;
    }

    public
            String getClinic_comments() {
        return clinic_comments;
    }

    public
            void setClinic_comments(String clinic_comments) {
        this.clinic_comments = clinic_comments;
    }

    public
            String getClinic_referral_contact_num() {
        return clinic_referral_contact_num;
    }

    public
            void setClinic_referral_contact_num(String clinic_referral_contact_num) {
        this.clinic_referral_contact_num = clinic_referral_contact_num;
    }

    public
            Date getClinic_referral_proposed_return_date() {
        return clinic_referral_proposed_return_date;
    }

    public
            void setClinic_referral_proposed_return_date(Date clinic_referral_proposed_return_date) {
        this.clinic_referral_proposed_return_date = clinic_referral_proposed_return_date;
    }
// **    

    public
            String getSelected_clinic_id() {
        return selected_clinic_id;
    }

    public
            void setSelected_clinic_id(String selected_clinic_id) {
        this.selected_clinic_id = selected_clinic_id;
    }

    public
            String getSurgery_note() {
        return surgery_note;
    }

    public
            void setSurgery_note(String surgery_note) {
        this.surgery_note = surgery_note;
    }

    public
            Integer getNote_id() {
        return note_id;
    }

    public
            void setNote_id(Integer note_id) {
        this.note_id = note_id;
    }

    public
            String getHiv_tested_in_6months() {
        return hiv_tested_in_6months;
    }

    public
            void setHiv_tested_in_6months(String hiv_tested_in_6months) {
        this.hiv_tested_in_6months = hiv_tested_in_6months;
    }

    public
            String getHiv_test_today() {
        return hiv_test_today;
    }

    public
            void setHiv_test_today(String hiv_test_today) {
        this.hiv_test_today = hiv_test_today;
    }

    public
            String getViral_test_result() {
        return viral_test_result;
    }

    public
            void setViral_test_result(String viral_test_result) {
        this.viral_test_result = viral_test_result;
    }

    public
            String getSyphilis_test_result() {
        return syphilis_test_result;
    }

    public
            void setSyphilis_test_result(String syphilis_test_result) {
        this.syphilis_test_result = syphilis_test_result;
    }

    public
            String getHistory_search() {
        return history_search;
    }

    public
            void setHistory_search(String history_search) {
        this.history_search = history_search;
    }

    public
            String getHistory_search_value() {
        return history_search_value;
    }

    public
            void setHistory_search_value(String history_search_value) {
        this.history_search_value = history_search_value;
    }

    public
            String getGen_history_search() {
        return gen_history_search;
    }

    public
            void setGen_history_search(String gen_history_search) {
        this.gen_history_search = gen_history_search;
    }

    public
            String getGen_history_search_value() {
        return gen_history_search_value;
    }

    public
            void setGen_history_search_value(String gen_history_search_value) {
        this.gen_history_search_value = gen_history_search_value;
    }

    public
            List<History> getHistory_Visits() {
        return history_Visits;
    }

    public
            void setHistory_Visits(List<History> history_Visits) {
        this.history_Visits = history_Visits;
    }

    public
            List<History> getGen_history_Visits() {
        return gen_history_Visits;
    }

    public
            void setGen_history_Visits(List<History> gen_history_Visits) {
        this.gen_history_Visits = gen_history_Visits;
    }

    public
            ReceptionInfo getHistory_patient_info() {
        return history_patient_info;
    }

    public
            void setHistory_patient_info(ReceptionInfo history_patient_info) {
        this.history_patient_info = history_patient_info;
    }

    public
            List<HistoryClinicalNotes> getHistory_clinical_notes() {
        return history_clinical_notes;
    }

    public
            void setHistory_clinical_notes(List<HistoryClinicalNotes> history_clinical_notes) {
        this.history_clinical_notes = history_clinical_notes;
    }

    public
            List<HistoryTestResults> getHistory_tests_results() {
        return history_tests_results;
    }

    public
            void setHistory_tests_results(List<HistoryTestResults> history_tests_results) {
        this.history_tests_results = history_tests_results;
    }

    public
            List<HistoryDiagnosis> getHistory_diagnosis() {
        return history_diagnosis;
    }

    public
            void setHistory_diagnosis(List<HistoryDiagnosis> history_diagnosis) {
        this.history_diagnosis = history_diagnosis;
    }

    public
            List<HistoryTreatment> getHistory_prescription() {
        return history_prescription;
    }

    public
            void setHistory_prescription(List<HistoryTreatment> history_prescription) {
        this.history_prescription = history_prescription;
    }

    public
            ReceptionInfo getGen_history_patient_info() {
        return gen_history_patient_info;
    }

    public
            void setGen_history_patient_info(ReceptionInfo gen_history_patient_info) {
        this.gen_history_patient_info = gen_history_patient_info;
    }

    public
            List<HistoryClinicalNotes> getGen_history_clinical_notes() {
        return gen_history_clinical_notes;
    }

    public
            void setGen_history_clinical_notes(List<HistoryClinicalNotes> gen_history_clinical_notes) {
        this.gen_history_clinical_notes = gen_history_clinical_notes;
    }

    public
            List<HistoryTestResults> getGen_history_tests_results() {
        return gen_history_tests_results;
    }

    public
            void setGen_history_tests_results(List<HistoryTestResults> gen_history_tests_results) {
        this.gen_history_tests_results = gen_history_tests_results;
    }

    public
            List<HistoryDiagnosis> getGen_history_diagnosis() {
        return gen_history_diagnosis;
    }

    public
            void setGen_gistory_diagnosis(List<HistoryDiagnosis> gen_history_diagnosis) {
        this.gen_history_diagnosis = gen_history_diagnosis;
    }

    public
            List<HistoryTreatment> getGen_history_prescription() {
        return gen_history_prescription;
    }

    public
            void setGen_history_prescription(List<HistoryTreatment> gen_history_prescription) {
        this.gen_history_prescription = gen_history_prescription;
    }

    public
            String getPatient_screened() {
        return patient_screened;
    }

    public
            void setPatient_screened(String patient_screened) {
        this.patient_screened = patient_screened;
    }

    public
            boolean isDetail_show() {
        return detail_show;
    }

    public
            void setDetail_show(boolean detail_show) {
        this.detail_show = detail_show;
    }

    public
            boolean isFp_screening_show() {
        return fp_screening_show;
    }

    public
            void setFp_screening_show(boolean fp_screening_show) {
        this.fp_screening_show = fp_screening_show;
    }

    public
            boolean isAlc_screening_show() {
        return alc_screening_show;
    }

    public
            void setAlc_screening_show(boolean alc_screening_show) {
        this.alc_screening_show = alc_screening_show;
    }

    public
            List<TestCategory> getTest_categories() {
        return test_categories;
    }

    public
            void setTest_categories(List<TestCategory> test_categories) {
        this.test_categories = test_categories;
    }

    public
            List<TestName> getTest_names() {
        return test_names;
    }

    public
            void setTest_names(List<TestName> test_names) {
        this.test_names = test_names;
    }

    public
            String getTest_category() {
        return test_category;
    }

    public
            void setTest_category(String test_category) {
        this.test_category = test_category;
    }

    public
            String getTest_name() {
        return test_name;
    }

    public
            void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public
            Integer getTest_name_id() {
        return test_name_id;
    }

    public
            void setTest_name_id(Integer test_name_id) {
        this.test_name_id = test_name_id;
    }

    public
            String getPatient_id_extension() {
        return patient_id_extension;
    }

    public
            void setPatient_id_extension(String patient_id_extension) {
        this.patient_id_extension = patient_id_extension;
    }

    public
            boolean isRegistered() {
        return registered;
    }

    public
            void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public
            String getReport_type() {
        return report_type;
    }

    public
            void setReport_type(String report_type) {
        this.report_type = report_type;
    }

    public
            Date getReport_date() {
        return report_date;
    }

    public
            void setReport_date(Date report_date) {
        this.report_date = report_date;
    }

    public
            boolean isGraphical_show() {
        return graphical_show;
    }

    public
            void setGraphical_show(boolean graphical_show) {
        this.graphical_show = graphical_show;
    }

    public
            boolean isTabular_show() {
        return tabular_show;
    }

    public
            void setTabular_show(boolean tabular_show) {
        this.tabular_show = tabular_show;
    }

    public
            String getLab_urgency() {
        return lab_urgency;
    }

    public
            void setLab_urgency(String lab_urgency) {
        this.lab_urgency = lab_urgency;
    }

    public
            Date getForward_to_ward_date() {
        return forward_to_ward_date;
    }

    public
            void setForward_to_ward_date(Date forward_to_ward_date) {
        this.forward_to_ward_date = forward_to_ward_date;
    }

    public
            String getAdminister() {
        return administer;
    }

    public
            void setAdminister(String administer) {
        this.administer = administer;
    }

    public
            boolean isExtra_pay() {
        return this.extra_pay;
    }

    public
            void setExtra_pay(boolean extra_pay) {
        this.extra_pay = extra_pay;
    }

    public
            boolean isExit_without_medication() {
        return exit_without_medication;
    }

    public
            void setExit_without_medication(boolean exit_without_medication) {
        this.exit_without_medication = exit_without_medication;
    }

    public
            boolean isShow_investigations() {
        return this.show_investigations;
    }

    public
            void setShow_investigations(boolean show_investigations) {
        this.show_investigations = show_investigations;
    }

    public
            boolean isShow_previous_diagnoses() {
        return this.show_previous_diagnoses;
    }

    public
            void setShow_previous_diagnoses(boolean show_previous_diagnoses) {
        this.show_previous_diagnoses = show_previous_diagnoses;
    }

    public
            boolean isForward_to_dentist() {
        return forward_to_dentist;
    }

    public
            void setForward_to_dentist(boolean forward_to_dentist) {
        this.forward_to_dentist = forward_to_dentist;
    }

    public
            ReferalNote getReferal_note() {
        return this.referal_note;
    }

    public
            void setReferal_note(ReferalNote referal_note) {
        this.referal_note = referal_note;
    }

    public
            Date getFollow_up_date() {
        return this.follow_up_date;
    }

    public
            void setFollow_up_date(Date follow_up_date) {
        this.follow_up_date = follow_up_date;
    }

    public
            boolean isRefer_out() {
        return this.refer_out;
    }

    public
            void setRefer_out(boolean refer_out) {
        this.refer_out = refer_out;
    }

    public
            String getDrug_dose_unit() {
        return this.drug_dose_unit;
    }

    public
            void setDrug_dose_unit(String drug_dose_unit) {
        this.drug_dose_unit = drug_dose_unit;
    }

    public
            Integer getDiagnosis_id() {
        return this.diagnosis_id;
    }

    public
            void setDiagnosis_id(Integer diagnosis_id) {
        this.diagnosis_id = diagnosis_id;
    }

    public
            List<Diagnosis> getDiagnosis_icd_list() {
        return this.diagnosis_icd_list;
    }

    public
            void setDiagnosis_icd_list(List<Diagnosis> diagnosis_icd_list) {
        this.diagnosis_icd_list = diagnosis_icd_list;
    }

    public
            String getDiagnosis_icdl1() {
        return this.diagnosis_icdl1;
    }

    public
            void setDiagnosis_icdl1(String diagnosis_icdl1) {
        this.diagnosis_icdl1 = diagnosis_icdl1;
    }

    public
            List<Diagnosis> getDiagnosis_icdl2_list() {
        return this.diagnosis_icdl2_list;
    }

    public
            void setDiagnosis_icdl2_list(List<Diagnosis> diagnosis_icdl2_list) {
        this.diagnosis_icdl2_list = diagnosis_icdl2_list;
    }

    public
            String getDiagnosis_icdl2() {
        return this.diagnosis_icdl2;
    }

    public
            void setDiagnosis_icdl2(String diagnosis_icdl2) {
        this.diagnosis_icdl2 = diagnosis_icdl2;
    }

    public
            boolean isDiagnosis_icdl2_show() {
        return this.diagnosis_icdl2_show;
    }

    public
            void setDiagnosis_icdl2_show(boolean notdiagnosis_icdl2_show) {
        this.diagnosis_icdl2_show = notdiagnosis_icdl2_show;
    }

    public
            List<Diagnosis> getDiagnosis_icdl3_list() {
        return this.diagnosis_icdl3_list;
    }

    public
            void setDiagnosis_icdl3_list(List<Diagnosis> diagnosis_icdl3_list) {
        this.diagnosis_icdl3_list = diagnosis_icdl3_list;
    }

    public
            String getDiagnosis_icdl3() {
        return this.diagnosis_icdl3;
    }

    public
            void setDiagnosis_icdl3(String diagnosis_icdl3) {
        this.diagnosis_icdl3 = diagnosis_icdl3;
    }

    public
            boolean isDiagnosis_icdl3_show() {
        return this.diagnosis_icdl3_show;
    }

    public
            void setDiagnosis_icdl3_show(boolean notdiagnosis_icdl3_show) {
        this.diagnosis_icdl3_show = notdiagnosis_icdl3_show;
    }

    public
            List<Diagnosis> getDiagnosis_icdl4_list() {
        return this.diagnosis_icdl4_list;
    }

    public
            void setDiagnosis_icdl4_list(List<Diagnosis> diagnosis_icdl4_list) {
        this.diagnosis_icdl4_list = diagnosis_icdl4_list;
    }

    public
            String getDiagnosis_icdl4() {
        return this.diagnosis_icdl4;
    }

    public
            void setDiagnosis_icdl4(String diagnosis_icdl4) {
        this.diagnosis_icdl4 = diagnosis_icdl4;
    }

    public
            boolean isDiagnosis_icdl4_show() {
        return this.diagnosis_icdl4_show;
    }

    public
            void setDiagnosis_icdl4_show(boolean notdiagnosis_icdl4_show) {
        this.diagnosis_icdl4_show = notdiagnosis_icdl4_show;
    }

    public
            String getDiagnosis_icd_search_string() {
        return this.diagnosis_icd_search_string;
    }

    public
            void setDiagnosis_icd_search_string(String diagnosis_icd_search_string) {
        this.diagnosis_icd_search_string = diagnosis_icd_search_string;
    }

    public
            List<Diagnosis> getDiagnosis_icd_search_list() {
        return this.diagnosis_icd_search_list;
    }

    public
            void setDiagnosis_icd_search_list(List<Diagnosis> diagnosis_icd_search_list) {
        this.diagnosis_icd_search_list = diagnosis_icd_search_list;
    }

    public
            boolean isDiagnosis_icd_search_flag() {
        return this.diagnosis_icd_search_flag;
    }

    public
            void setDiagnosis_icd_search_flag(boolean notdiagnosis_icd_search_flag) {
        this.diagnosis_icd_search_flag = notdiagnosis_icd_search_flag;
    }

    public
            List<Diagnosis> getDiagnosis_hmis_list() {
        return this.diagnosis_hmis_list;
    }

    public
            void setDiagnosis_hmis_list(List<Diagnosis> diagnosis_hmis_list) {
        this.diagnosis_hmis_list = diagnosis_hmis_list;
    }

    public
            List<Diagnosis> getDiagnosis_hmis_ward_list() {
        return this.diagnosis_hmis_ward_list;
    }

    public
            void setDiagnosis_hmis_ward_list(List<Diagnosis> diagnosis_hmis_ward_list) {
        this.diagnosis_hmis_ward_list = diagnosis_hmis_ward_list;
    }

    public
            Integer getDiagnosis_hmis_id() {
        return this.diagnosis_hmis_id;
    }

    public
            void setDiagnosis_hmis_id(Integer diagnosis_hmis_id) {
        this.diagnosis_hmis_id = diagnosis_hmis_id;
    }

    public
            String getDiagnosis_category() {
        return this.diagnosis_category;
    }

    public
            void setDiagnosis_category(String diagnosis_category) {
        this.diagnosis_category = diagnosis_category;
    }

    public
            List<HistoryDiagnosis> getPrevious_diagnoses() {
        return this.previous_diagnoses;
    }

    public
            void setPrevious_diagnoses(List<HistoryDiagnosis> previous_diagnoses) {
        this.previous_diagnoses = previous_diagnoses;
    }

    public
            List<LabTestResults> getResults_for_tests() {
        return this.results_for_tests;
    }

    public
            void setResults_for_tests(List<LabTestResults> results_for_tests) {
        this.results_for_tests = results_for_tests;
    }

    public
            Integer getTest_id() {
        return this.test_id;
    }

    public
            void setTest_id(Integer test_id) {
        this.test_id = test_id;
    }

    public
            List<LabTest> getTests_requested() {
        return this.tests_requested;
    }

    public
            void setTests_requested(List<LabTest> tests_requested) {
        this.tests_requested = tests_requested;
    }

    public
            List<LabTest> getTests_list() {
        return this.tests_list;
    }

    public
            void setTests_list(List<LabTest> tests_list) {
        this.tests_list = tests_list;
    }

    public
            Integer getTest_category_id() {
        return this.test_category_id;
    }

    public
            void setTest_category_id(Integer test_category_id) {
        this.test_category_id = test_category_id;
    }

    public
            String getExtrapayment_reason() {
        return this.extrapayment_reason;
    }

    public
            void setExtrapayment_reason(String extrapayment_reason) {
        this.extrapayment_reason = extrapayment_reason;
    }

    public
            String getNote() {
        return this.note;
    }

    public
            void setNote(String note) {
        this.note = note;
    }

    public
            List<Notes> getClinical_notes() {
        return this.clinical_notes;
    }

    public
            void setClinical_notes(List<Notes> clinical_notes) {
        this.clinical_notes = clinical_notes;
    }

    public
            boolean isAssigned() {
        return this.assigned;
    }

    public
            void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

  

    public
            ReceptionInfo getReception_info_view() {
        return this.reception_info_view;
    }

    public
            void setReception_info_view(ReceptionInfo reception_info_view) {
        this.reception_info_view = reception_info_view;
    }

    public
            String getTask_id() {
        return this.task_id;
    }

    public
            void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public
            String getDentist_forward_reason() {
        return this.dentist_forward_reason;
    }

    public
            void setDentist_forward_reason(String dentist_forward_reason) {
        this.dentist_forward_reason = dentist_forward_reason;
    }

    public
            String getDrug_prescribed() {
        return this.drug_prescribed;
    }

    public
            void setDrug_prescribed(String drug_prescribed) {
        this.drug_prescribed = drug_prescribed;
    }

    public
            String getDiagnosis_name() {
        return this.diagnosis_name;
    }

    public
            void setDiagnosis_name(String diagnosis_name) {
        this.diagnosis_name = diagnosis_name;
    }

    public
            String getDiagnosis_type() {
        return this.diagnosis_type;
    }

    public
            void setDiagnosis_type(String disgnosis_type) {
        this.diagnosis_type = disgnosis_type;
    }

    public
            String getConsultantId() {
        return this.consultantId;
    }

    public
            void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    public
            String getTechnicianId() {
        return this.technicianId;
    }

    public
            void setTechnicianId(String technicianId) {
        this.technicianId = technicianId;
    }

    public static
            String getAddedTests() {
        return addedTests;
    }

    public static
            void setAddedTests(String addedTests) {
        addedTests = addedTests;
    }

    public
            Consultant getSelectedTask() {
        return this.selectedTask;
    }

    public
            void setSelectedTask(Consultant selectedTask) {
        this.selectedTask = selectedTask;
    }

    public
            Integer getExtrapayment() {
        return this.extrapayment;
    }

    public
            void setExtrapayment(Integer extrapayment) {
        this.extrapayment = extrapayment;
    }

    public
            String getSelAcct() {
        return this.selAcct;
    }

    public
            void setSelAcct(String selAcct) {
        this.selAcct = selAcct;
    }

    public
            List<Diagnosis> getDiagnosis() {
        return this.diagnosis;
    }

    public
            void setDiagnosis(List<Diagnosis> diagnosis) {
        this.diagnosis = diagnosis;
    }

    public
            String getPclassification() {
        return pclassification;
    }

    public
            void setPclassification(String pclassification) {
        this.pclassification = pclassification;
    }

    public
            String getLoading_Dose() {
        return this.loading_Dose;
    }

    public
            void setLoading_Dose(String loading_Dose) {
        this.loading_Dose = loading_Dose;
    }

    public
            String getPrescribe_unit() {
        return prescribe_unit;
    }

    public
            void setPrescribe_unit(String prescribe_unit) {
        this.prescribe_unit = prescribe_unit;
    }

    public
            String getTimes() {
        return this.times;
    }

    public
            void setTimes(String times) {
        this.times = times;
    }

    public
            String getTimes_unit() {
        return times_unit;
    }

    public
            void setTimes_unit(String times_unit) {
        this.times_unit = times_unit;
    }

    public
            String getDays() {
        return this.days;
    }

    public
            void setDays(String days) {
        this.days = days;
    }

    public
            String getStaff_From() {
        return this.staff_From;
    }

    public
            void setStaff_From(String staff_From) {
        this.staff_From = staff_From;
    }

    public
            String getStaff_To() {
        return this.staff_To;
    }

    public
            void setStaff_To(String staff_To) {
        this.staff_To = staff_To;
    }

    public
            void setAlldrugs(List<Drugstore> alldrugs) {
        this.alldrugs = alldrugs;
    }

    public
            Integer getDrugid() {
        return this.drugid;
    }

    public
            void setDrugid(Integer drugid) {
        this.drugid = drugid;
    }

    public
            List<Treatment> getPrescription() {
        return this.prescription;
    }

    public
            void setPrescription(List<Treatment> prescription) {
        this.prescription = prescription;
    }

    public
            String getDispensary_staff_id() {
        return this.dispensary_staff_id;
    }

    public
            void setDispensary_staff_id(String dispensary_staff_id) {
        this.dispensary_staff_id = dispensary_staff_id;
    }

    public
            List<Users> getActivedentists() {
        return this.activedentists;
    }

    public
            void setActivedentists(List<Users> activedentists) {
        this.activedentists = activedentists;
    }

 

    public
            StreamedContent getScanned_image() {
        return scanned_image;
    }

    public
            WardNote getWnote() {
        return wnote;
    }

    public
            void setWnote(WardNote wnote) {
        this.wnote = wnote;
    }

    public
            String getProvisional_diagnosis() {
        return provisional_diagnosis;
    }

    public
            void setProvisional_diagnosis(String provisional_diagnosis) {
        this.provisional_diagnosis = provisional_diagnosis;
    }

    public
            List<Diagnosis> getProvisional_diagnosis_list() {
        return provisional_diagnosis_list;
    }

    public
            void setProvisional_diagnosis_list(List<Diagnosis> provisional_diagnosis_list) {
        this.provisional_diagnosis_list = provisional_diagnosis_list;
    }

    public
            boolean isShow_alcohol_patients() {
        return show_alcohol_patients;
    }

    public
            void setShow_alcohol_patients(boolean notshow_alcohol_patients) {
        this.show_alcohol_patients = notshow_alcohol_patients;
    }

    public
            boolean isShow_alcohol_pending() {
        return show_alcohol_pending;
    }

    public
            void setShow_alcohol_pending(boolean notshow_alcohol_pending) {
        this.show_alcohol_pending = notshow_alcohol_pending;
    }

    public
            boolean isShow_alcohol_enrolled() {
        return show_alcohol_enrolled;
    }

    public
            void setShow_alcohol_enrolled(boolean notshow_alcohol_enrolled) {
        this.show_alcohol_enrolled = notshow_alcohol_enrolled;
    }



    public
            boolean isShow_diabetes_patients() {
        return show_diabetes_patients;
    }

    public
            void setShow_diabetes_patients(boolean notshow_diabetes_patients) {
        this.show_diabetes_patients = notshow_diabetes_patients;
    }

    public
            boolean isShow_diabetes_pending() {
        return show_diabetes_pending;
    }

    public
            void setShow_diabetes_pending(boolean notshow_diabetes_pending) {
        this.show_diabetes_pending = notshow_diabetes_pending;
    }

    public
            boolean isShow_diabetes_enrolled() {
        return show_diabetes_enrolled;
    }

    public
            void setShow_diabetes_enrolled(boolean notshow_diabetes_enrolled) {
        this.show_diabetes_enrolled = notshow_diabetes_enrolled;
    }
 

    public
            boolean isShow_hyper_patients() {
        return show_hyper_patients;
    }

    public
            void setShow_hyper_patients(boolean notshow_hyper_patients) {
        this.show_hyper_patients = notshow_hyper_patients;
    }

    public
            boolean isShow_hyper_pending() {
        return show_hyper_pending;
    }

    public
            void setShow_hyper_pending(boolean notshow_hyper_pending) {
        this.show_hyper_pending = notshow_hyper_pending;
    }

    public
            boolean isShow_hyper_enrolled() {
        return show_hyper_enrolled;
    }

    public
            void setShow_hyper_enrolled(boolean notshow_hyper_enrolled) {
        this.show_hyper_enrolled = notshow_hyper_enrolled;
    }

    

    public
            String getClinic_patient_name() {
        return clinic_patient_name;
    }

    public
            void setClinic_patient_name(String clinic_patient_name) {
        this.clinic_patient_name = clinic_patient_name;
    }

    public
            String getNew_serial() {
        return new_serial;
    }

    public
            void setNew_serial(String new_serial) {
        this.new_serial = new_serial;
    }

    public
            boolean isHideclinivisttable() {
        return hideclinivisttable;
    }

    public
            void setHideclinivisttable(boolean hideclinivisttable) {
        this.hideclinivisttable = hideclinivisttable;
    }

    public
            String getNew_clinic_id() {
        return new_clinic_id;
    }

    public
            void setNew_clinic_id(String new_clinic_id) {
        this.new_clinic_id = new_clinic_id;
    }

    public
            boolean isZ_score_rendering() {
        return z_score_rendering;
    }

    public
            void setZ_score_rendering(boolean z_score_rendering) {
        this.z_score_rendering = z_score_rendering;
    }

    public
            List<Drugstore> getAlldrugs() {
        try {
            this.alldrugs = ConsultantDAO.Consultant_Get_Drugs("All");
            return this.alldrugs;
        }
        catch (Exception ex) {
            System.err.println("Error Message: " + ex.getMessage());
            return null;
        }
    }

    public
            boolean isHideHistoryPannel() {
        return hideHistoryPannel;
    }

    public
            void setHideHistoryPannel(boolean hideHistoryPannel) {
        this.hideHistoryPannel = hideHistoryPannel;
    }

//    public
//            List<Drugstore> getAll_prescribed_drugs() {
//        try {
//            this.all_prescribed_drugs = ConsultantDAO.Consultant_Get_Drugs("Prescribed");
//            return this.all_prescribed_drugs;
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//            return null;
//        }
//    }

    // <editor-fold defaultstate="collapsed" desc="General">        
    public
            List<ReceptionTask> consultant_get_pending_tasks(String user_id) {
        try {
            return ConsultantDAO.Consultant_Get_Pending(user_id);
        }
        catch (SQLException ex) {
//            Logger.getLogger(AccountsBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

//    public
//            String consultant_get_selected_task(String task_id, String user_id) {
//        try {
//            this.task_id = task_id;
//              if (ReceptionDAO.Reception_Retrieve_Patient_Details(task_id).getAge() <= 5) {
//                this.weight_for_height_label = "Weight For Height";
//                this.z_score_rendering = true;
//            }
//            else {
//                this.weight_for_height_label = "BMI";
//                this.z_score_rendering = false;
//            }
//
//            this.reception_info_view = ReceptionDAO.Reception_Get_Reception_Info(task_id);
//            clinical_notes = ConsultantDAO.Consultant_Get_Clinical_Notes(task_id);
//            nagigate_to_page = "Select Action";
//            nagigate_to_page_2 = "Select Action";
//            navigate_to_clinic_label = "Attach existing Clinic File";
//            navigate_to_clinic_value = "chronic_clinic_retrieve_file";
//            selected_clinic = "Select";
//            history_search = "Nothing";
////            history_search_value = "n/a";            
//            history_Visits.clear();
//            gen_history_Visits.clear();
//            history_patient_info = null;
//            history_prescription.clear();
//            history_diagnosis.clear();
//            history_clinical_notes.clear();
//            history_tests_results.clear();
//            chronic_clinic = "Select";
//            clinic_comments = "";
//            clinic_referral_contact_num = "";
//            clinic_referral_proposed_return_date = null;
//
//            wnote = new WardNote();
//            provisional_diagnosis = "";
//            provisional_diagnosis_list = new ArrayList<Diagnosis>();
//
//            show_alcohol_patients = false;
//            show_alcohol_pending = false;
//            show_alcohol_enrolled = false;
//            show_diabetes_patients = false;
//            show_diabetes_pending = false;
//            show_diabetes_enrolled = false;
//            show_hyper_patients = false;
//            show_hyper_pending = false;
//            show_hyper_enrolled = false;
//
//            show_investigations = false;
//            show_previous_diagnoses = false;
//            extra_pay = false;
//            exit_without_medication = false;
//            refer_out = false;
//            forward_to_dentist = false;
//
//            if (ConsultantDAO.Consultant_Task_Lock(task_id, true, user_id)) {
//                this.assigned = true;
//                if (!ConsultantDAO.Consultant_Time_Audit_Exists(task_id)) {
//                    ConsultantDAO.Consultant_Time_Audit_Start(task_id, ConsultantDAO.Consultant_Get_Forwarded_From(task_id));
//                }
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "This Task Is Now LOCKED and Assigned To You.", "Successful"));
//            }
//            else {
//                this.assigned = false;
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "An Error Occurred.Task Could Not Be Assigned To You.", "Failure"));
//            }
//            return "/consultant/opd/viewdetails";
//        }
//        catch (Exception ex) {
//            System.err.println("ConsultantBean Error: Method: consultant_get_selected_task " + ex.getMessage());
//            return null;
//        }
//    }

    public
            List<ReceptionTask> consultant_get_sent_to_ward_tasks(String user_id) {
        try {
            return ConsultantDAO.Consultant_Get_Sent_To_Ward(user_id);
        }
        catch (SQLException ex) {
//            Logger.getLogger(AccountsBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

//    public
//            String consultant_navigate_to(String nav) {
//        this.hideHistoryPannel = false;
//        try {
//            if ("dispensary".equalsIgnoreCase(nav)) {
//                previous_diagnoses = ConsultantDAO.Consultant_Get_Previous_Diagnoses(patient_view.getMemberId(), this.task_id);
//                results_for_tests = consultant_get_performed_tests();
//
//                history_tests_results.clear();
//
//                history_tests_results.addAll(ConsultantDAO.Consultant_Get_Tests_History(this.task_id));
//
//                view_xrays = WardDAO.Ward_View_Xray(this.task_id);
//                view_surgery = WardDAO.Ward_View_Surgery(this.task_id);
//                diagnosis_icd_list = consultant_get_icd_diagnosis();
//                diagnosis_icd_search_string = null;
//                diagnosis_icd_search_list = null;
//                diagnosis_icd_search_flag = false;
//                diagnosis_hmis_list = consultant_get_hmis_diagnosis();
//                get_all_prescribed_treatment();
//                pclassification = ReceptionDAO.Reception_Get_Patient_Classification(this.task_id);
//                follow_up_date = ConsultantDAO.Consultant_Get_Followup_Date(this.task_id);
//            }
//            else if ("dispensary_v2".equalsIgnoreCase(nav)) {
//                results_for_tests = consultant_get_performed_tests();
//                view_xrays = WardDAO.Ward_View_Xray(this.task_id);
//                view_surgery = WardDAO.Ward_View_Surgery(this.task_id);
//                diagnosis_hmis_list = consultant_get_hmis_diagnosis();
//                get_all_prescribed_treatment();
//                pclassification = ReceptionDAO.Reception_Get_Patient_Classification(this.task_id);
//                follow_up_date = ConsultantDAO.Consultant_Get_Followup_Date(this.task_id);
//            }
//            else if ("ward".equalsIgnoreCase(nav)) {
//                diagnosis_hmis_ward_list = consultant_get_hmis_ward_diagnosis();
//            }
//            else if ("viewnotes".equalsIgnoreCase(nav)) {
//                consultant_get_clinical_notes();
//                this.alcohol_audit_exists = ConsultantDAO.Consultant_Alcohol_Audit_Exists(this.task_id);
//                String[][] disfields = {{"Track_Id"}, {this.task_id}};
//                String[][] tbfields = {{"Track_Id"}, {this.task_id}};
//
//                this.disability_exisits = DynamicFunctionsDAO.Check_if_exsists("disability_results", disfields);
//                this.tb_screening_exisits = DynamicFunctionsDAO.Check_if_exsists("tb_results", tbfields);
//                this.family_planning_screening_exists = ConsultantDAO.Consultant_Family_Planning_Exists(this.task_id);
//                this.viral_screening_exists = ConsultantDAO.Consultant_Viral_Screening_Exists(this.task_id);
//
//            }
//            else if ("cancel".equalsIgnoreCase(nav)) {
//                consultant_unlock_task();
//            }
//            else if ("history".equalsIgnoreCase(nav)) {
//
//            }
//            else if ("clinics/diabetes/details_and_add_visit.xhtml".equalsIgnoreCase(nav)) {
//                diabetes_clinic_visit.setInvestigations(ConsultantDAO.Concatenated_Tests_Results(this.task_id));
//                diabetes_clinic_visit.setCur_med(ConsultantDAO.Concatenated_Diabetic_Treatments(this.task_id));
//                diabetes_clinic_visit.setCur_antihypertensives(ConsultantDAO.Concatenated_Hypertensive_Treatments(this.task_id));
//                diabetes_clinic_visit.setPrescribed_drugs(ConsultantDAO.Concatenated_Other_Prescribed_Treatments(this.task_id));
//            }
//            return nav;
//        }
//        catch (Exception ex) {
//            System.out.println("Error Message: " + ex.getMessage());
//            return null;
//        }
//    }

//    public
//            String consultant_navigate_to() {
//        try {
//            if ("dispensary".equalsIgnoreCase(nagigate_to_page)) {
//                cosultant_get_lab_results();
//                get_all_prescribed_treatment();
//                pclassification = ReceptionDAO.Reception_Get_Patient_Classification(this.task_id);
//                follow_up_date = ConsultantDAO.Consultant_Get_Followup_Date(this.task_id);
//            }
//            else if ("viewnotes".equalsIgnoreCase(nagigate_to_page)) {
//                consultant_get_clinical_notes();
//            }
//            else if ("cancel".equalsIgnoreCase(nagigate_to_page)) {
//                consultant_unlock_task();
//            }
//            else if ("history".equalsIgnoreCase(nagigate_to_page)) {
//
//            }
//            else if ("clinics/diabetes/details_and_add_visit.xhtml".equalsIgnoreCase(nagigate_to_page)) {
//                diabetes_clinic_visit.setInvestigations(ConsultantDAO.Concatenated_Tests_Results(this.task_id));
//                diabetes_clinic_visit.setCur_med(ConsultantDAO.Concatenated_Diabetic_Treatments(this.task_id));
//                diabetes_clinic_visit.setCur_antihypertensives(ConsultantDAO.Concatenated_Hypertensive_Treatments(this.task_id));
//                diabetes_clinic_visit.setPrescribed_drugs(ConsultantDAO.Concatenated_Other_Prescribed_Treatments(this.task_id));
//            }
//            return nagigate_to_page;
//        }
//        catch (Exception ex) {
//            System.out.println("Error Message: " + ex.getMessage());
//            return null;
//        }
//    }

    public
            void consultant_show_clinic_patients() {
        try {
            if ("diabetes".equalsIgnoreCase(selected_clinic)) {
                show_diabetes_patients = true;
                show_hyper_patients = false;
                show_alcohol_patients = false;
            }
            else if ("hypertension".equalsIgnoreCase(selected_clinic)) {
                show_diabetes_patients = false;
                show_hyper_patients = true;
                show_alcohol_patients = false;
            }
            else if ("alcohol".equalsIgnoreCase(selected_clinic)) {
                show_diabetes_patients = false;
                show_hyper_patients = false;
                show_alcohol_patients = true;
            }
        }
        catch (Exception ex) {
            System.out.println("Error Message: " + ex.getMessage());
        }
    }

//    public
//            void consultant_show_clinic_patients(String _clinic) {
//        try {
//            if ("diabetes".equalsIgnoreCase(_clinic)) {
//                show_diabetes_patients = true;
//                show_hyper_patients = false;
//                show_alcohol_patients = false;
//                clinic_all_files = clinics_get_all_files("Diabetes Clinic",clinic_patient_name);
//            }
//            else if ("hypertension".equalsIgnoreCase(_clinic)) {
//                show_diabetes_patients = false;
//                show_hyper_patients = true;
//                show_alcohol_patients = false;
//            }
//            else if ("alcohol".equalsIgnoreCase(_clinic)) {
//                show_diabetes_patients = false;
//                show_hyper_patients = false;
//                show_alcohol_patients = true;
//            }
//        }
//        catch (Exception ex) {
//            System.out.println("Error Message: " + ex.getMessage());
//        }
//    }
//    public					
//            void consultant_show_clinic_patients_specific(String _clinic) {					
//        try {					
//            if ("diabetes".equalsIgnoreCase(_clinic)) {					
//                show_diabetes_patients = true;					
//                show_hyper_patients = false;					
//                show_alcohol_patients = false;					
//                clinic_files = clinics_get_files("Diabetes Clinic");					
//            }					
//            else if ("hypertension".equalsIgnoreCase(_clinic)) {					
//                show_diabetes_patients = false;					
//                show_hyper_patients = true;					
//                show_alcohol_patients = false;					
//            }					
//            else if ("alcohol".equalsIgnoreCase(_clinic)) {					
//                show_diabetes_patients = false;					
//                show_hyper_patients = false;					
//                show_alcohol_patients = true;					
//            }					
//        }					
//        catch (Exception ex) {					
//            System.out.println("Error Message: " + ex.getMessage());					
//        }					
//    }					

    public
            String consultant_back_to_main_patient_screen() {
        try {
            return "viewdetails";
        }
        catch (Exception ex) {
            System.err.println("Error Message::" + ex.getMessage());
            return null;
        }
    }

    public
            String consultant_unlock_task() {
        try {
            if (ConsultantDAO.Consultant_Task_Lock(this.task_id, false, "")) {
                this.assigned = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Task Has Been UNLOCKED.", "Successful"));
                return "consultant";
            }
            else {
                this.assigned = true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "An Error Occurred.Task Could Not Be UnLocked", "Failure"));
                return null;
            }
        }
        catch (Exception ex) {
            System.out.println("Error Message" + ex.getMessage());
            return null;
        }
    }

    public
            void consultant_flag_record_errors() {
        try {
//            System.out.println("record_errors = " + reception_info_view.getRecord_errors() + "task_id = " +this.task_id);
            if (ConsultantDAO.Consultant_Flag_Record_Errors(this.task_id, reception_info_view.getRecord_errors())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Record Has Been Flagged With Errors.", "Successful"));
            }
            else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Transaction Error.Contact The Administrator", "Failure"));
            }
        }
        catch (Exception ex) {
            System.out.println("Error Message" + ex.getMessage());
        }
    }

    public
            String consultant_forward_to_dispensary(String staff_id) {
        try {

            String[][] pillative_care_assitivearray = {{"Need_for_pilliative_care", "Pilliative_care_comment", "Assistive_device"}, {this.need_for_pilliative_care + "", this.Pilliatve_comment, this.assastive_devices}};

            if (DynamicFunctionsDAO.Edit("frontdesk_tasks", pillative_care_assitivearray, "Track_Id", this.task_id)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pillative care patient infor is updated successfully confirmed", "Success"));

                this.Pilliatve_comment = "";
                this.need_for_pilliative_care = false;
                this.assastive_devices = "";
            }
            else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error occured: Please contact System administrator", "Failure"));
            }
            //If Screening Option Not Saved

            String[][] disfields = {{"Track_Id"}, {this.task_id}};
            String[][] tbfields = {{"Track_Id"}, {this.task_id}};

            if (!DynamicFunctionsDAO.Check_if_exsists("disability_results", disfields)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Disability screening results have not been recorded. The patient can not be forwarded before this is done.", "Successful"));

            }
            else if (!DynamicFunctionsDAO.Check_if_exsists("tb_results", tbfields)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "TB screening results have not been recorded. The patient can not be forwarded before this is done.", "Successful"));

            }
            else {

                if (ConsultantDAO.Consultant_Get_Patient_Screened(this.task_id)) {
                    //If 'New Attendance' or 'Re-Attendance' Not Selected
                    if (!pclassification.equals("Select") || forward_to_dentist) {
                        //If Exist Patient Without Forwarding To Dispensary
                        if (exit_without_medication && !forward_to_dentist) {
                            //complete task at consultant room
//                            if (consultant_complete_task(staff_id)) {
//                                //If Patient is reffered out, perform the transaction
//                                if (refer_out) {
//                                    if (consultant_refferal_out(staff_id)) {
//                                        //then exit patient
//                                        consultant_exit_patient();
//                                        ConsultantDAO.Consultant_Time_Audit_Update(task_id, staff_id);
//                                        consultant_clear_values();
//                                        return "consultant";
//                                    }
//                                    else {
//                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Contact The Administrator", "Failure"));
//                                        return null;
//                                    }
//                                }
//                                else {
//                                    //then exit patient
//                                    ConsultantDAO.Consultant_Time_Audit_Update(task_id, staff_id);
//                                    consultant_exit_patient();
//                                    consultant_clear_values();
//                                    return "consultant";
//                                }
//                            }
//                            else {
//                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Contact The Administrator", "Failure"));
//                            }
                            //Else
                        }
                        else {
                            //check nulls
                            if (consultant_check_nulls()) {
                                if (forward_to_dentist == false && this.reception_info_view.isSkip_accounts() == false && extra_pay == false) {

                                    if (refer_out) {
//                                        if (consultant_refferal_out(staff_id)) {
//                                        }
//                                        else {
//                                            return null;
//                                        }
                                    }

//                                    if (consultant_complete_task(staff_id)) {
//                                        if (ConsultantDAO.Consultant_Dispensary_Pending(this.task_id, staff_id, "OPD")) {
//                                            ConsultantDAO.Consultant_Time_Audit_Update(task_id, staff_id);
////                                            DispensaryDAO.Dispensary_Time_Audit_Add(task_id, staff_id);
//                                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Patient Forwarded Sucessfully", "Successful"));
//                                            consultant_clear_values();
//                                            return "consultant";
//                                        }
//                                        else {
//                                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Contact The Administrator", "Failure"));
//                                            return null;
//                                        }
//                                    }
//                                    else {
//                                        return null;
//                                    }

                                }
                                else {

                                    if (forward_to_dentist) {
//                                        if (consultant_forward_to_dentist(staff_id)) {
//                                            ConsultantDAO.Consultant_Time_Audit_Update(task_id, staff_id);
////                                            DentistDAO.Dentist_Time_Audit_Add(task_id, staff_id);
//                                        }
//                                        else {
//                                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Contact The Administrator", "Failure"));
//                                            return null;
//                                        }
                                    }

                                    if (this.reception_info_view.isSkip_accounts()) {
                                        if (ConsultantDAO.Consultant_Accounts_Update_Pending(this.task_id, staff_id, "OPD")) {
                                            ConsultantDAO.Consultant_Time_Audit_Update(task_id, staff_id);
//                                            AccountsDAO.Accounts_Time_Audit_Add(task_id, staff_id);
                                        }
                                        else {
                                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Contact The Administrator", "Failure"));
                                            return null;
                                        }
                                    }

                                    if (extra_pay) {
                                        if (consultant_extra_payment(staff_id)) {
                                            ConsultantDAO.Consultant_Time_Audit_Update(task_id, staff_id);
//                                            AccountsDAO.Accounts_Time_Audit_Add(task_id, staff_id);
                                        }
                                        else {
                                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Contact The Administrator", "Failure"));
                                            return null;
                                        }
                                    }

                                    if (refer_out) {
//                                        if (consultant_refferal_out(staff_id)) {
//                                        }
//                                        else {
//                                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Contact The Administrator", "Failure"));
//                                            return null;
//                                        }
                                    }

//                                    if (consultant_complete_task(staff_id)) {
//                                        ConsultantDAO.Consultant_Time_Audit_Update(task_id, staff_id);
//                                        consultant_clear_values();
//                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Patient Forwarded Sucessfully", "Successful"));
//                                        return "consultant";
//                                    }
//                                    else {
//                                        return null;
//                                    }

                                }
                            }
                            else {
                                return null;
                            }
                        }
                    }
                    else {
                        //Return to Page
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Please record whether this is a 'New Attendance' or a 'Re-Attendance'", "Information"));
                        return "dispensary";
                    }
                }
                else {
                    //Return to Page
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Patient Has Not Been Screened. Please Screen Patient Before Forwarding To Dispensary.", "Information"));
//                return "consultant";
                    return "viewdetails";
                }
            }
        }
        catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error. Possible NULL Values", "Failure"));
            System.err.println("Error Message: X" + ex.getMessage());
        }
        return null;
    }

    public
            boolean consultant_check_nulls() {
        try {
            if ((this.prescription.isEmpty()) || (this.prescription == null)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Cant Forward Patient Before Specifying Diagnosis And Treatment", "Failure"));
                return false;
            }
            else {
                return true;
            }
        }
        catch (Exception ex) {
            System.err.println("Error Message: " + ex.getMessage());
            return false;
        }
    }

//    public
//            boolean consultant_forward_to_dentist(String user_id) {
//        try {
//            if ("".equalsIgnoreCase(this.dentist_forward_reason)) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Provide All Required Data", "Failure"));
//                return false;
//            }
//            else {
//
//                if (ConsultantDAO.Consultant_Dentist_Pending(this.task_id, user_id)) {
//                    if (ConsultantDAO.Consultant_Dentist_Tasks(this.task_id, user_id, this.dentist_forward_reason)) {
//                        this.dentist_forward_reason = "";
//                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tasks Forwarded To Dentist Section Sucessfully", "Successful"));
//                        return true;
//                    }
//                    else {
//                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Please Contact Your Administrator", "Failure"));
//                        return false;
//                    }
//                }
//                else {
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Please Contact Your Administrator", "Failure"));
//                    return false;
//                }
//
//            }
//
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//            return false;
//        }
//
//    }

    public
            boolean consultant_extra_payment(String user_id) {
        try {
            if (ConsultantDAO.Consultant_Accounts_Pending(this.task_id, this.extrapayment, this.extrapayment_reason, false, "Consultant", false, user_id)) {
                return true;
            }
            else {
                return false;
            }

        }
        catch (Exception ex) {
            System.err.println("Error Message: " + ex.getMessage());
            return false;
        }
    }

//    public
//            boolean consultant_refferal_out(String user_id) {
//        try {
//            if ((this.referal_note.getFirst_visit_date() == null) || ("".equalsIgnoreCase(this.referal_note.getReferal_reason())) || ("".equalsIgnoreCase(this.referal_note.getRefered_to()))) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Some Referal Values NOT specified", "Failure"));
//                return false;
//            }
//            else {
//                this.referal_note.setTask_id(this.task_id);
//                this.referal_note.setRefered_by(user_id);
//
//                if (ConsultantDAO.Consultant_Add_Referal_Note(this.referal_note)) {
//                    return true;
//                }
//                else {
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Contact The Administrator", "Failure"));
//                    return false;
//                }
//            }
//
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//            return false;
//        }
//    }
//
//    public
//            boolean consultant_complete_task(String staff_id) {
//        try {
//            if (ConsultantDAO.Consultant_Add_Task(this.task_id, staff_id)) {
//                ReceptionDAO.Reception_Update_Patient_Classification(this.task_id, pclassification);
//                if (follow_up_date == null) {
//                    follow_up_date = new Date();
//                }
//                if (ConsultantDAO.Consultant_Add_Followup_Date(this.task_id, this.follow_up_date)) {
//                    if (!"".equalsIgnoreCase(note)) {
//                        if (ConsultantDAO.Consultant_Dentist_Tasks(this.task_id, staff_id, this.note)) {
//                            if (ConsultantDAO.Consultant_Delete_Pending(this.task_id)) {
//                                return true;
//                            }
//                            else {
//                                return false;
//                            }
//                        }
//                        else {
//                            return false;
//                        }
//                    }
//                    else {
//                        if (ConsultantDAO.Consultant_Delete_Pending(this.task_id)) {
//                            return true;
//                        }
//                        else {
//                            return false;
//                        }
//                    }
//
//                }
//                else {
//                    return false;
//                }
//            }
//            else {
//                return false;
//            }
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//            return false;
//        }
//    }
//
//    public
//            void consultant_exit_patient() {
//        try {
//            if (DispensaryDAO.Dispensary_Exit_Patient(this.task_id)) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Patient Has Been Exited Successfully", "Success"));
//            }
//            else {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Try Again.If Persists, Contact Admin", "Failure"));
//            }
//        }
//        catch (Exception ex) {
//            Logger.getLogger(DrugstoreBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public
            void check_if_registered() {
        try {
            if (this.registered) {
                this.patient_id_extension = "MEMB";
            }
            else {
                this.patient_id_extension = "TEMP";
            }
        }
        catch (Exception ex) {
            System.err.println("Error Occurred: Method: check_if_registered " + ex.getMessage());
        }
    }

    public
            void consultant_clear_values() {
        try {
            show_investigations = false;
            show_previous_diagnoses = false;
            extra_pay = false;
            exit_without_medication = false;
            extrapayment_reason = "";
            extrapayment = 0;
            refer_out = false;
            forward_to_dentist = false;
            referal_note = new ReferalNote();
            prescription.clear();
        }
        catch (Exception ex) {
            System.out.println("Error Message: " + ex.getMessage());
        }
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Patient History">
//    public
//            void consultant_history_search_criteria() {
//        consultant_get_default_hospital_logo();
//        try {
//            if (history_search.equalsIgnoreCase("Nothing")) {
//                history_search_value = "n/a";
////                history_search_value = "";                
//            }
//            else if (history_search.equalsIgnoreCase("By Patient ID")) {
//                history_search_value = patient_view.getMemberId();
//            }
//            else if (history_search.equalsIgnoreCase("By Patient Name")) {
//                history_search_value = patient_view.getFullname();
//            }
//
//        }
//        catch (Exception ex) {
//            System.err.println("Error in Bean Meathod:consultant_history_search_criteria " + ex.getMessage());
//        }
//    }

//    public
//            void consultant_get_history() {
//        this.hideHistoryPannel = false;
//        try {
//
//            if (history_search.equalsIgnoreCase("Nothing")) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "There is NO Search Criteria Given.", "Information"));
//            }
//            else {
//                history_Visits = new ArrayList();
//
//                if (history_search.equalsIgnoreCase("By Patient ID")) {
//                    //Get list of atmost last five visits
//                    history_Visits = ConsultantDAO.Consultant_Get_Last_Visits(history_search_value, "Id");
//                }
//                else if (history_search.equalsIgnoreCase("By Patient Name")) {
//                    //Get list of atmost last five visits
//                    history_Visits = ConsultantDAO.Consultant_Get_Last_Visits(history_search_value.toUpperCase(), "Name");
//                }
//
//                history_patient_info = null;
//                history_prescription.clear();
//                history_diagnosis.clear();
//                history_clinical_notes.clear();
//                history_tests_results.clear();
//            }
//
//        }
//        catch (Exception ex) {
//            System.out.println("Error Occurred: " + ex.getMessage());
//        }
//    }

//    public
//            void consultant_get_genhistory() {
//        this.disability_data = null;
//        this.view_screening = null;
//        this.hideHistoryPannel = false;
//        try {
//
//            if (gen_history_search.equalsIgnoreCase("Nothing")) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "There is NO Search Criteria Given.", "Information"));
//            }
//            else {
//                gen_history_Visits = new ArrayList();
//
//                if (gen_history_search.equalsIgnoreCase("By Patient ID")) {
//                    //Get list of atmost last five visits
//                    gen_history_Visits = ConsultantDAO.Consultant_Get_Last_Visits(gen_history_search_value, "Id");
//                }
//                else if (gen_history_search.equalsIgnoreCase("By Patient Name")) {
//                    //Get list of atmost last five visits
//                    gen_history_Visits = ConsultantDAO.Consultant_Get_Last_Visits(gen_history_search_value.toUpperCase(), "Name");
//                }
//
//                gen_history_patient_info = null;
//                gen_history_prescription.clear();
//                gen_history_diagnosis.clear();
//                gen_history_clinical_notes.clear();
//                gen_history_tests_results.clear();
//            }
//
//        }
//        catch (Exception ex) {
//            System.out.println("Error Occurred: " + ex.getMessage());
//        }
//    }

//    public
//            void consultant_get_history_details(String Track_Id) {
//        this.hideHistoryPannel = true;
//        consultant_get_default_hospital_logo();
//        try {
//            history_patient_info = null;
//            history_prescription.clear();
//            history_diagnosis.clear();
//            history_clinical_notes.clear();
//            history_tests_results.clear();
//            this.view_screening = null;
//            this.disability_data = null;
//
//            //Get Patient Info
//            history_patient_info = ReceptionDAO.Reception_Get_Reception_Info(Track_Id);
//            //Get Diagnosis
//            history_diagnosis.addAll(ConsultantDAO.Consultant_Get_Diagnosis_History(Track_Id));
//            //Get Diagnosis and Prescriptions
//            history_prescription.addAll(ConsultantDAO.Consultant_Get_Prescription_History(Track_Id));
//            //Get Clinical Notes
//            history_clinical_notes.addAll(ConsultantDAO.Consultant_Get_Clinical_Notes_History(Track_Id));
//            //Get Test Results
//            history_tests_results.addAll(ConsultantDAO.Consultant_Get_Tests_History(Track_Id));
//            this.view_screening = WardDAO.Ward_View_Screening(Track_Id, "");
//            this.disability_data = WardDAO.getdisability(Track_Id, "");
//
//        }
//        catch (Exception ex) {
//            System.out.println("Error Occurred: Method: consultant_get_history_details Error:" + ex.getMessage());
//        }
//    }

//    public
//            void consultant_get_genhistory_details(String Track_Id) {
//        try {
//            this.view_screening = null;
//            this.disability_data = null;
//            this.hideHistoryPannel = true;
//            gen_history_patient_info = null;
//            gen_history_prescription.clear();
//            gen_history_diagnosis.clear();
//            gen_history_clinical_notes.clear();
//            gen_history_tests_results.clear();
//
//            //Get Patient Info
//            gen_history_patient_info = ReceptionDAO.Reception_Get_Reception_Info(Track_Id);
//            //Get Diagnosis
//            gen_history_diagnosis.addAll(ConsultantDAO.Consultant_Get_Diagnosis_History(Track_Id));
//            //Get Diagnosis and Prescriptions
//            gen_history_prescription.addAll(ConsultantDAO.Consultant_Get_Prescription_History(Track_Id));
//            //Get Clinical Notes
//            gen_history_clinical_notes.addAll(ConsultantDAO.Consultant_Get_Clinical_Notes_History(Track_Id));
//            //Get Test Results
//            gen_history_tests_results.addAll(ConsultantDAO.Consultant_Get_Tests_History(Track_Id));
//            this.view_screening = WardDAO.Ward_View_Screening(Track_Id, "");
//            this.disability_data = WardDAO.getdisability(Track_Id, "");
//
//        }
//        catch (Exception ex) {
//            System.out.println("Error Occurred: Method: consultant_get_history_details Error:" + ex.getMessage());
//        }
//    }

// Commented out 1/1/17 - for dropping if this causes no problems
//    public String consultant_history_done() {
//        try {
//                return "viewdetails";
//        } catch (Exception ex) {
//            System.err.println("Error Message::" + ex.getMessage());
//            return null;
//        }
//    }    
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Clinical notes">
//    public
//            void consultant_add_clinical_note(String user_id) {
//        try {
//            if (note_id != null) {
//                if (ConsultantDAO.Consultant_Dentist_Tasks(note_id, this.note)) {
//                    this.note = "";
//                    note_id = null;
//                    clinical_notes = ConsultantDAO.Consultant_Get_Clinical_Notes(task_id);
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Note Updated Successfully", "Success"));
//                }
//                else {
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Contact The Administrator", "Failure"));
//                }
//            }
//            else {
//                if (ConsultantDAO.Consultant_Dentist_Tasks(this.task_id, user_id, this.note)) {
//                    this.note = "";
//                    clinical_notes = ConsultantDAO.Consultant_Get_Clinical_Notes(task_id);
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Note Added Successfully", "Success"));
//                }
//                else {
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Contact The Administrator", "Failure"));
//                }
//            }
//
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//        }
//    }
//
//    public
//            void consultant_edit_clinical_note(Integer _note_id) {
//        try {
//            note = ConsultantDAO.Consultant_Get_Note(_note_id).getNote();
//            note_id = _note_id;
//        }
//        catch (Exception ex) {
//            System.out.println("Error Message: " + ex.getMessage());
//        }
//    }
//
//    public
//            void consultant_delete_clinical_note(Integer note_id) {
//        try {
//            if (ConsultantDAO.Consultant_Delete_Clinical_Note(note_id)) {
//                clinical_notes = ConsultantDAO.Consultant_Get_Clinical_Notes(task_id);
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Clinical Note Deleted Successfully", "Success"));
//            }
//            else {
//            }
//        }
//        catch (Exception ex) {
//            System.out.println("Error Message: " + ex.getMessage());
//        }
//    }
//
//    public
//            List<Notes> consultant_get_notes() {
//        try {
//            return ConsultantDAO.Consultant_Get_Notes(this.task_id);
//        }
//        catch (Exception ex) {
//            System.out.println("Error Message: " + ex.getMessage());
//            return null;
//        }
//    }
//
//    public
//            void consultant_get_clinical_notes() {
//        try {
//            clinical_notes = ConsultantDAO.Consultant_Get_Clinical_Notes(task_id);
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//        }
//    }

    // </editor-fold>
//    // <editor-fold defaultstate="collapsed" desc="Laboratory">        
//    public
//            List<TestCategory> consultant_get_test_categories() {
//        try {
//            return LaboratoryDAO.Laboratory_Get_Test_Categories();
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//            return null;
//        }
//    }
//
//    public
//            List<TestCategory> consultant_get_test_categoryz() {
//        try {
//            test_categories = LaboratoryDAO.Laboratory_Get_Test_Category();
//            return test_categories;
//        }
//        catch (Exception ex) {
//            System.out.println("Exception In Consultant Bean: " + ex.getMessage());
//            return null;
//        }
//    }
//
//    public
//            void consultant_get_tests() {
//        try {
//            this.tests_list = LaboratoryDAO.Laboratory_Get_Tests(this.test_category_id);
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//        }
//    }
//
//    public
//            List<TestName> consultant_get_test_names() {
//        try {
//            test_names = LaboratoryDAO.Laboratory_Get_Test_Names(test_category_id);
//            return test_names;
//        }
//        catch (Exception ex) {
//            System.out.println("Exception In Consultant Bean: " + ex.getMessage());
//            return null;
//        }
//    }
//
//    public
//            void consultant_add_test_request(String user_id) {
//        try {
//            if (trans_id == null) {
//                trans_id = UUID.randomUUID();
//            }
//
//            if (LaboratoryDAO.Laboratory_Add_Test_Request(this.test_name_id, this.task_id, user_id, "OPD", trans_id.toString())) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Test Request Added Succesfully", "Successful"));
//            }
//            else {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Please Contact Your Administrator", "Failure"));
//            }
//
//            this.tests_requested = LaboratoryDAO.Laboratory_Get_Tests_Requested(this.task_id);
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//        }
//    }
//
//    public
//            void consultant_laboratory_delete_pending(Integer request_id) {
//        try {
//            if (!LaboratoryDAO.Laboratory_Delete_Pending(request_id)) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Please Contact Your Administrator", "Failure"));
//            }
//            else {
//                this.tests_requested = LaboratoryDAO.Laboratory_Get_Tests_Requested(String.valueOf(this.task_id));
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Test Request Deleted", "Successful"));
//            }
//        }
//        catch (Exception ex) {
//            System.out.println("Error Message: " + ex.getMessage());
//        }
//    }
//
//    public
//            String consultant_laboratory_add_pending(String user_id) {
//        try {
//            if ((this.tests_requested.isEmpty()) || (this.tests_requested == null)) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "There Are No Tests Requested.You CANT forward to Laboratory!", "Failure"));
//                return null;
//            }
//            else {
//                if (LaboratoryDAO.Laboratory_Add_Pending(this.task_id, user_id, "Consultant", lab_urgency, consultant_generate_lab_id(this.task_id))) {
//                    if (ConsultantDAO.Consultant_Delete_Pending(this.task_id)) {
//                        ConsultantDAO.Consultant_Time_Audit_Update(task_id, user_id);
//                        LaboratoryDAO.Laboratory_Time_Audit_Add(task_id, user_id);
//                        //Adds HIV Test Option if Selected Yes
//                        LaboratoryDAO.Laboratory_Add_Hiv_Test_Option(this.task_id, hiv_tested_in_6months, hiv_test_today, user_id);
//                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Task Forwarded Successfully", "Successful"));
//                        return "consultant";
//                    }
//                    else {
//                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Please Contact Your Administrator", "Failure"));
//                        return null;
//                    }
//                }
//                else {
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Please Contact Your Administrator", "Failure"));
//                    return null;
//                }
//            }
//        }
//        catch (Exception ex) {
//            System.out.println("Error Message: " + ex.getMessage());
//            return null;
//        }
//
//    }
//
//    public
//            String consultant_generate_lab_id(String task_id) {
//        try {
//            Integer pending_count = LaboratoryDAO.Laboratory_Get_Pending_Count();
//            Integer completed_count = LaboratoryDAO.Laboratory_Get_Completed_Count();
//            String lab_id = LaboratoryDAO.Laboratory_Get_Lab_Id(task_id);
//            Calendar cal = Calendar.getInstance();
//            Integer year = cal.get(Calendar.YEAR);
//
//            if (lab_id == null) {
//                Integer total_count = pending_count + completed_count;
//                if (total_count < 10) {
//                    //format :: 000000-current_year
//                    return "00000" + total_count.toString() + "-" + year.toString();
//                }
//                else if ((total_count < 100) && (total_count > 9)) {
//                    return "0000" + total_count.toString() + "-" + year.toString();
//                }
//                else if ((total_count < 1000) && (total_count > 99)) {
//                    return "000" + total_count.toString() + "-" + year.toString();
//                }
//                else if (total_count < 10000 && total_count > 999) {
//                    return "00" + total_count.toString() + "-" + year.toString();
//                }
//                else if (total_count < 100000 && total_count > 9999) {
//                    return "0" + total_count.toString() + "-" + year.toString();
//                }
//                else if (total_count > 99999) {
//                    return total_count.toString() + "-" + year.toString();
//                }
//                return total_count.toString() + "-" + year.toString();
//            }
//            else {
//                return lab_id;
//            }
//
//        }
//        catch (Exception ex) {
//            System.err.println("ReceptionBean Error: Method: reception_get_non_registered_patient_id " + ex.getMessage());
//            return "";
//        }
//
//    }
//
//    public
//            void forwardTests() throws ClassNotFoundException {
//        try {
//            if (ConsultantDAO.Test_Request_Update(checkPreviousTaskID(), this.technicianId)) {
//                if (ConsultantDAO.Consultant_Lab_Pending(checkPreviousTaskID(), this.technicianId, this.consultantId, this.consultantId)) {
//                    if (ConsultantDAO.Consultant_Delete_Pending(checkPreviousTaskID())) {
//                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tests Request Submitted Successfully", "Success"));
//                    }
//                    else {
//                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error In Updating Some Values", "Failure"));
//                    }
//                }
//                else {
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error In Updating Some Values", "Failure"));
//                }
//            }
//            else {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Submission of Tests Failed", "Failure"));
//            }
//        }
//        catch (SQLException ex) {
//            Logger.getLogger(ConsultantBean.class.getName()).log(Level.SEVERE, null, ex);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Submission of Tests Failed", "Failure"));
//        }
//    }
//
//    public
//            String checkPreviousTaskID() {
//        for (Consultant task : this.forwadedJobs) {
//            if (task.getPatientID().equalsIgnoreCase(this.selectedTask.getPatientID())) {
//                return task.getTaskId();
//            }
//        }
//        return null;
//    }
//
//    public
//            List<Users> forwardToLab() {
//        try {
//            this.users = new ArrayList();
//            this.users = ConsultantDAO.Consultant_Foward_To_Lab();
//            return this.users;
//        }
//        catch (SQLException ex) {
//            Logger.getLogger(ConsultantBean.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }
//
//    public
//            List<LabTestResults> consultant_get_performed_tests() {
//        try {
//            return LaboratoryDAO.Laboratory_Get_Performed_Tests(this.task_id);
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//            return null;
//        }
//    }
//
//    public
//            void cosultant_get_lab_results() {
//        try {
//            this.results_for_tests = ConsultantDAO.Consultant_Tests_Results(this.task_id);
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//        }
//    }
//
//    // </editor-fold>    
//    // <editor-fold defaultstate="collapsed" desc="Diagnosis & Treatment">        
//    public
//            List<Diagnosis> consultant_get_diagnosis() {
//        try {
//            return ConsultantDAO.Consultant_Get_Diagnosis();
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//            return null;
//        }
//
//    }
//
//    public
//            List<Diagnosis> consultant_get_icd_diagnosis() {
//        try {
//            return ConsultantDAO.Consultant_Get_ICD_Diagnosis();
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//            return null;
//        }
//    }
//
//    public
//            List<Diagnosis> consultant_get_icd_diagnosis_level1() {
//        try {
////            this.diagnosis_id = 0;
//            this.diagnosis_icdl2_show = false;
//            this.diagnosis_icdl3_show = false;
//            this.diagnosis_icdl4_show = false;
//            return ConsultantDAO.Consultant_Get_ICD_Diagnosis_Level1();
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//            return null;
//        }
//    }
//
//    public
//            void consultant_get_icd_diagnosis_level2(String level1) {
//        try {
//            this.diagnosis_id = 0;
//            this.diagnosis_icdl2_list = ConsultantDAO.Consultant_Get_ICD_Diagnosis_Level2(level1);
//            this.diagnosis_icdl2 = "";
//            this.diagnosis_icdl3 = "";
//            this.diagnosis_icdl4 = "";
//            this.diagnosis_icdl2_show = true;
//            this.diagnosis_icdl3_show = false;
//            this.diagnosis_icdl4_show = false;
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//        }
//    }
//
//    public
//            void consultant_get_icd_diagnosis_level3(String level2) {
//        try {
//            this.diagnosis_id = 0;
//            this.diagnosis_icdl3_list = ConsultantDAO.Consultant_Get_ICD_Diagnosis_Level3(level2);
//            this.diagnosis_icdl3 = "";
//            this.diagnosis_icdl4 = "";
//            this.diagnosis_icdl3_show = true;
//            this.diagnosis_icdl4_show = false;
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//        }
//    }
//
//    public
//            void consultant_get_icd_diagnosis_level4(String level3) {
//        try {
//            this.diagnosis_id = 0;
//            if (level3.substring(0, 1).equals("0")) {
//                this.diagnosis_icdl4_list = ConsultantDAO.Consultant_Get_ICD_Diagnosis_Level4(level3);
//                this.diagnosis_icdl4 = "";
//                this.diagnosis_icdl4_show = true;
//            }
//            else {
//                this.diagnosis_id = ConsultantDAO.Consultant_Get_Diagnosis_RecordId(level3.substring(1));
//            }
//            System.out.println("consultantBean.consultant_select_icd_diagnosis_level4(): " + diagnosis_id + " " + diagnosis_hmis_id);
//
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//        }
//    }
//
//    public
//            void consultant_select_icd_diagnosis(String diagnosis) {
//        try {
////        System.out.println("consultantBean.consultant_select_icd_diagnosis(): " + diagnosis_id + " " + diagnosis_hmis_id);
//            this.diagnosis_id = ConsultantDAO.Consultant_Get_Diagnosis_RecordId(diagnosis.substring(1));
//            System.out.println("consultantBean.consultant_select_icd_diagnosis(): " + diagnosis_id + " " + diagnosis_hmis_id);
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//        }
//    }
//
//    public
//            void consultant_search_icd_diagnosis(String search_string) {
//        try {
//            diagnosis_icd_search_flag = true;
////            diagnosis_icd_search_list = ConsultantDAO.Consultant_Get_Search_ICD_List(diagnosis_icd_search_string);
//            diagnosis_icd_search_list = ConsultantDAO.Consultant_Get_Search_ICD_List(search_string);
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//        }
//    }
//
//    public
//            void consultant_select_searched_icd_diagnosis(Integer selected_diagnosis_id, String selected_diagnosis) {
//        try {
//            Diagnosis selected_icd_diagnosis_record = new Diagnosis();
//            selected_icd_diagnosis_record.setRecord_id(selected_diagnosis_id);
//            selected_icd_diagnosis_record.setDiagnosis(selected_diagnosis);
//            diagnosis_icd_list.add(selected_icd_diagnosis_record);
//            diagnosis_id = selected_diagnosis_id;
//            diagnosis_icd_search_string = null;
//            diagnosis_icd_search_flag = false;
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//        }
//    }
//
//    public
//            List<Diagnosis> consultant_get_hmis_diagnosis() {
//        try {
////        System.out.println("consultantBean.consultant_get_hmis_diagnosis(): " + diagnosis_id + " " + diagnosis_hmis_id);
//            return ConsultantDAO.Consultant_Get_HMIS_Diagnosis();
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//            return null;
//        }
//    }
//
//    public
//            List<Diagnosis> consultant_get_hmis_ward_diagnosis() {
//        try {
////        System.out.println("consultantBean.consultant_get_hmis_diagnosis(): " + diagnosis_id + " " + diagnosis_hmis_id);
//            return WardDAO.Ward_Get_HMIS_Diagnosis();
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//            return null;
//        }
//    }
//
//    public
//            void get_prescribe_unit() {
//        try {
//            this.prescribe_unit = DrugstoreDAO.retrieve_prescribe_unit(this.drugid);
//        }
//        catch (Exception ex) {
//            Logger.getLogger(WardBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public
//            void consultant_diagnosis_treatment() {
//        try {
//            if (exit_without_medication) {
//                if (diagnosis_id == 0 || diagnosis_id == null
//                        || diagnosis_hmis_id == 0 || diagnosis_hmis_id == null
//                        || diagnosis_category == null || diagnosis_category.equals("")) {
////        System.out.println("consultantBean.consultant_diagnosis_treatment(): " + diagnosis_id + " " + diagnosis_hmis_id);
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Missing Diagnosis Data", "Failure"));
//                }
//                else {
//                    if (ConsultantDAO.Consultant_Add_Treatment(this.task_id, this.diagnosis_id, "OPD", this.diagnosis_hmis_id, this.diagnosis_category, 1499, null, null, null, null, null, null)) {
//                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Diagnosis Added", "Successful"));
//                        get_all_prescribed_treatment();
//                        this.drugid = null;
//                        this.administer = "";
//                        this.loading_Dose = null;
//                        this.prescribe_unit = "";
//                        this.drug_dose_unit = null;
//                        this.times = null;
//                        this.times_unit = "";
//                        this.days = null;
//                    }
//                    else {
//                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Probably Due To Duplicates.If It Persists, Contact Your Administrator", "Failure"));
//                    }
//                }
//            }
//            else {
//                if (diagnosis_id == 0 || diagnosis_id == null
//                        || diagnosis_hmis_id == 0 || diagnosis_hmis_id == null
//                        || diagnosis_category == null || diagnosis_category.equals("")
//                        || drugid == 0 || drugid == null
//                        || (drugid != 1499
//                        && ("".equalsIgnoreCase(administer) || "".equalsIgnoreCase(drug_dose_unit)
//                        || "".equalsIgnoreCase(times) || "".equalsIgnoreCase(days)))) {
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Missing Prescription Data.Please Fill All Mandatory Fields.Thank You", "Failure"));
//                }
//                else {
//                    if (ConsultantDAO.Consultant_Add_Treatment(this.task_id, this.diagnosis_id, "OPD", this.diagnosis_hmis_id, this.diagnosis_category, this.drugid, this.loading_Dose, this.drug_dose_unit, this.times, this.times_unit, this.days, administer)) {
//                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Treatment Added", "Successful"));
//                        get_all_prescribed_treatment();
//                        this.drugid = null;
//                        this.administer = "";
//                        this.loading_Dose = null;
//                        this.prescribe_unit = "";
//                        this.drug_dose_unit = null;
//                        this.times = null;
//                        this.times_unit = "";
//                        this.days = null;
//                    }
//                    else {
//                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Probably Due To Duplicates.If It Persists, Contact Your Administrator", "Failure"));
//                    }
//                }
//            }
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message: " + ex.getMessage());
//        }
//    }
//
//    public
//            void consultant_delete_treatment(Integer treatment_id) {
//        try {
//            if (!ConsultantDAO.Consultant_Delete_Treatment(treatment_id)) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Please Contact Your Administrator", "Failure"));
//            }
//            else {
//                get_all_prescribed_treatment();
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Record Deleted", "Successful"));
//            }
//        }
//        catch (Exception ex) {
//            System.out.println("Error Message: " + ex.getMessage());
//        }
//    }
//
//    public
//            void get_all_prescribed_treatment() {
//        try {
//            this.prescription = ConsultantDAO.Consultant_Get_Treatment(this.task_id);
//        }
//        catch (Exception ex) {
//            Logger.getLogger(ConsultantBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public
//            void consultant_add_patient_diagnosis() {
//        try {
//
//        }
//        catch (Exception ex) {
//            System.err.println("System Error: " + this.getClass().getName() + " :: " + ex.getMessage());
//        }
//    }
//
//    public
//            void consultant_add_patient_treatment() {
//        try {
//
//        }
//        catch (Exception ex) {
//            System.err.println("System Error: " + this.getClass().getName() + " :: " + ex.getMessage());
//        }
//    }
//
//    public
//            void consultant_del_patient_treatment() {
//        try {
//
//        }
//        catch (Exception ex) {
//            System.err.println("System Error: " + this.getClass().getName() + " :: " + ex.getMessage());
//        }
//    }
//
//    // </editor-fold>
//    // <editor-fold defaultstate="collapsed" desc="Forward to Ward">
//    public
//            void consultant_add_provisional_diagnosis(String selected_diagnosis) {
//        try {
//            Diagnosis selected_provisional_diagnosis = new Diagnosis();
//            Integer converted_string = Integer.valueOf(selected_diagnosis.substring(0, selected_diagnosis.indexOf(59)));
//            selected_provisional_diagnosis.setRecord_id(converted_string);
//            selected_provisional_diagnosis.setDiagnosis(selected_diagnosis.substring(selected_diagnosis.indexOf(59) + 1));
//            provisional_diagnosis_list.add(selected_provisional_diagnosis);
//            provisional_diagnosis = "0";
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message::" + ex.getMessage());
//        }
//    }
//
//    public
//            void consultant_delete_provisional_diagnosis(Integer selected_record_id) {
//        try {
//            for (int i = 0; i < provisional_diagnosis_list.size(); i++) {
//                Diagnosis list_entry = provisional_diagnosis_list.get(i);
//                System.out.println(list_entry.getRecord_id() + " " + list_entry.getDiagnosis());
//                if (list_entry.getRecord_id().equals(selected_record_id)) {
//                    provisional_diagnosis_list.remove(i);
//                }
//            }
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message::" + ex.getMessage());
//        }
//    }
//
//    public
//            String consultant_view_admission_header_sheet() {
//        try {
////            cosultant_get_lab_results();
//            this.forward_to_ward_date = new Date();
//            return "admission_header_sheet_draft";
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message::" + ex.getMessage());
//            return null;
//        }
//    }
//
//    public
//            String consultant_back_from_view_admission_header_sheet() {
//        try {
//
//            return "/consultant/opd/ward.xhtml";
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message::" + ex.getMessage());
//            return null;
//        }
//    }
//
//    public
//            String consultant_forward_to_ipd(String user_id, String ward_name) {
//        try {
//            // Add record to the Ward Table & complete OPD recording
//            if (wnote.getHistory_symptoms().isEmpty() && wnote.getExamination_findings().isEmpty() && wnote.getImpressions().isEmpty() && wnote.getManagement_plan().isEmpty() && wnote.getOther_notes().isEmpty()) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "You have to Enter Information For Atleast One Clinical Notes Category.", "Failure"));
//                return null;
//            }
//            else {
//
//                if (WardDAO.Ward_Save_Summary(this.task_id, patient_view.getMemberId(), "not_seen", ward_name)) {
//
//                    UUID ward_note_id = UUID.randomUUID();
//                    trans_id = UUID.randomUUID();
//
//                    if (WardDAO.Ward_Add_Notes(ward_note_id.toString(), trans_id.toString(), this.task_id, wnote.getHistory_symptoms(), wnote.getExamination_findings(), wnote.getImpressions(), wnote.getManagement_plan(), wnote.getOther_notes(), user_id)) {
//                        if (WardDAO.Ward_Save_Transaction(trans_id.toString(), this.task_id, "Clinical Notes Added", user_id)) {
//                            WardDAO.Ward_Update_Transaction(trans_id.toString(), user_id);
//
//                            if (provisional_diagnosis_list.size() > 0) {
////                                System.out.println("Provisional diagnoses to save: " + provisional_diagnosis_list.size());
//                                trans_id = UUID.randomUUID();
//                                for (int i = 0; i < provisional_diagnosis_list.size(); i++) {
//                                    Diagnosis list_entry = provisional_diagnosis_list.get(i);
////                                    System.out.println(list_entry.getRecord_id() + " " + list_entry.getDiagnosis());
////                                    WardDAO.Ward_Consultant_Add_Treatment(this.trans_id.toString(),this.task_id,485, "IPD",list_entry.getRecord_id(),null,1499,null,null,null,null,null,null,null,null,null);
//                                    WardDAO.Ward_Consultant_Add_Treatment(this.trans_id.toString(), this.task_id, 485, "IPD", list_entry.getRecord_id(), null, null, 1499, null, null, null, null, null, null, null, null, null);
//                                }
//                                if (WardDAO.Ward_Save_Transaction(this.trans_id.toString(), task_id, "Provisional Diagnosis", user_id)) {
//                                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Provisional Diagnosis Added", "Successful"));
//                                    WardDAO.Ward_Update_Transaction(this.trans_id.toString(), user_id);
//                                }
//                            }
//
//                            if (ConsultantDAO.Consultant_Delete_Pending(this.task_id)) {
//                                if (ConsultantDAO.Consultant_Add_Task(task_id, user_id)) {
//                                    if (ConsultantDAO.Consultant_Time_Audit_Update(task_id, user_id)) {
//
//                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Clinical Notes For Ward Added", "Successful"));
//                                        wnote.setExamination_findings("");
//                                        wnote.setHistory_symptoms("");
//                                        wnote.setManagement_plan("");
//                                        wnote.setOther_notes("");
//
//                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Patient Forwarded Successfully", "Successful"));
//                                        ipd_comments = "";
//                                        return "consultant";
//                                    }
//                                    else {
//                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Please Contact Your Administrator", "Failure"));
//                                        return null;
//                                    }
//                                }
//                                else {
//                                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Please Contact Your Administrator", "Failure"));
//                                    return null;
//                                }
//                            }
//                            else {
//                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Please Contact Your Administrator", "Failure"));
//                                return null;
//                            }
//                        }
//                        else {
//                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An Error Occurred.", "Failure"));
//                            return null;
//                        }
//                    }
//                    else {
//                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An Error Occurred.", "Failure"));
//                        return null;
//                    }
//                }
//                else {
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error", "Failure"));
//                    return null;
//                }
//            }
//        }
//        catch (Exception ex) {
//            System.err.println("Error Message::" + ex.getMessage());
//            return null;
//        }
//    }
//
//    // </editor-fold>
//    public
//            void labsheetPresentation_of_Results() {
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        date = new Date();
//        this.resultPresentationDate = dateFormat.format(date);
//        consultant_get_default_hospital_logo();
//    }

//    public
//            String result_units(Integer sub_test_id, String result) {
//        try {
//            String agetype, units;
//            agetype = "";
//            units = "";
//            if (patient_view.getAge() >= 0 && patient_view.getAge_days() <= 14 && patient_view.getAge_months() == 0 && patient_view.getAge() == 0) {
//                agetype = "Neonate F/M";
//            }
//            else if (patient_view.getAge_days() > 14 || patient_view.getAge_months() > 0) {
//                agetype = "Baby F/M";
//            }
//            else if (patient_view.getAge() > 0) {
//                if (patient_view.getAge() <= 4) {
//                    agetype = "Toddler F/M";
//                }
//                else if (patient_view.getAge() > 4 && patient_view.getAge() <= 14) {
//                    agetype = "Child F/M";
//                }
//                else if (patient_view.getAge() > 14 && patient_view.getAge() <= 59) {
//                    if (patient_view.getMemberGender().equals("Male")) {
//                        agetype = "Adult Male";
//                    }
//                    else {
//                        agetype = "Adult Female";
//                    }
//                }
//                else if (patient_view.getAge() > 59) {
//
//                    if (patient_view.getMemberGender().equals("Male")) {
//                        agetype = "Elderly Male";
//                    }
//                    else {
//                        agetype = "Elderly Female";
//                    }
//                }
//            }
//            else {
//
//                agetype = "";
//            }
//            if (LaboratoryDAO.Laboratory_testresult(sub_test_id)) {
//                units = ConsultantDAO.get_result_units(sub_test_id, agetype, Float.valueOf(result));
//
//            }
//            else {
//                units = "";
//
//            }
//            return units;
//
//        }
//        catch (Exception ex) {
//            System.out.println("Exception In Admin Bean: laboratory_get_expected_result_range: " + ex.getMessage());
//            return null;
//        }
//    }
//
//    public
//            String expected_result_range(Integer sub_test_id) {
//        try {
//            String agetype;
//            agetype = "";
//            if (patient_view.getAge_days() >= 0 && patient_view.getAge_days() <= 14 && patient_view.getAge_months() == 0 && patient_view.getAge() == 0) {
//                agetype = "Neonate F/M";
//            }
//            else if (patient_view.getAge_days() > 14 || patient_view.getAge_months() > 0) {
//                agetype = "Baby F/M";
//            }
//            else if (patient_view.getAge() > 0) {
//                if (patient_view.getAge() <= 4) {
//                    agetype = "Toddler F/M";
//                }
//                else if (patient_view.getAge() > 4 && patient_view.getAge() <= 14) {
//                    agetype = "Child F/M";
//                }
//                else if (patient_view.getAge() > 14 && patient_view.getAge() <= 59) {
//                    if (patient_view.getMemberGender().equals("Male")) {
//                        agetype = "Adult Male";
//                    }
//                    else {
//                        agetype = "Adult Female";
//                    }
//                }
//                else if (patient_view.getAge() > 59) {
//
//                    if (patient_view.getMemberGender().equals("Male")) {
//                        agetype = "Elderly Male";
//                    }
//                    else {
//                        agetype = "Elderly Female";
//                    }
//                }
//            }
//            else {
//
//                agetype = "";
//            }
//            if (LaboratoryDAO.Laboratory_testresult(sub_test_id)) {
//                this.expected_result_range = LaboratoryDAO.Expected_result_range(sub_test_id, agetype);
//
//            }
//            else {
//                this.expected_result_range = "";
//            }
//            return expected_result_range;
//
//        }
//        catch (Exception ex) {
//            System.out.println("Exception In Admin Bean: laboratory_get_expected_result_range: " + ex.getMessage());
//            return null;
//        }
//    }
//
//    private
//            void consultant_get_default_hospital_logo() {
//        try {
//
//            File imagefile = new File("D:\\datastore\\images", "BCH_logo.jpg");
//
//            this.Hospital_logo = new DefaultStreamedContent(new FileInputStream(imagefile), "image/jpg");
//        }
//        catch (Exception ex) {
//            System.err.println("ReceptionBean Error: Method: consultant_get_default_hospital_logo " + ex.getMessage());
//        }
//    }

    public
            void Initial_assessment() {
        medication_prescribed = true;
        hideclinivisttable = true;
        examination_for_sign_of_target = true;
        cvd_risk = true;
        Screening_diabetic_neuropathy = true;
        Foot_examination_and_education = true;
        Current_antidiabetics = false;
        Current_antihypertensives = false;
        Other_prescribed_drugs = false;
        Advice_weight = true;
        note_medication_changes = false;
        Advice_diet = true;
        Adherence_Medication = false;
        Adherence_Footcare = false;
        Foot_examination_Adherence = false;
        Adherence_Weight = false;
        Adherence_Diet = false;
        Adherence_Appointment = false;
        visitheading = "Initial assessment";;

    }

    public
            void Medication_refill() {
        medication_prescribed = false;
        hideclinivisttable = true;
        examination_for_sign_of_target = false;
        cvd_risk = false;
        note_medication_changes = true;
        Screening_diabetic_neuropathy = false;
        Foot_examination_and_education = false;
        Current_antidiabetics = true;
        Current_antihypertensives = true;
        Other_prescribed_drugs = true;
        Advice_weight = false;
        Advice_diet = false;
        Adherence_Medication = true;
        Adherence_Footcare = true;
        Foot_examination_Adherence = false;
        Adherence_Weight = true;
        Adherence_Diet = true;
        Adherence_Appointment = true;
        visitheading = "Medication refill";

    }

    public
            void Annual_review() {
        medication_prescribed = false;
        note_medication_changes = true;
        hideclinivisttable = true;
        examination_for_sign_of_target = true;
        cvd_risk = true;
        Screening_diabetic_neuropathy = true;
        Foot_examination_and_education = false;
        Current_antidiabetics = true;
        Current_antihypertensives = true;
        Other_prescribed_drugs = true;
        Advice_weight = false;
        Advice_diet = false;
        Adherence_Medication = true;
        Adherence_Footcare = false;
        Foot_examination_Adherence = true;
        Adherence_Weight = true;
        Adherence_Diet = true;
        Adherence_Appointment = true;
        visitheading = "Annual review";

    }
    /*
     public Date consultant_get_next_date(Date report_date) {
     try {

     Calendar calendar = Calendar.getInstance();
     calendar.setTime(report_date);
     calendar.add(Calendar.DAY_OF_MONTH, 1);

     Date subscription_end_date = calendar.getTime();

     return subscription_end_date;

     } catch (Exception ex) {
     System.err.println("ReceptionBean Error: Method: consultant_get_next_date " + ex.getMessage());
     return null;
     }
     }
     */

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //----------xxxxxxxxx----End------------------------------
}
