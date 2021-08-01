/*
 * The MIT License
 *
 * Copyright (c) 2009-2021 PrimeTek
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package menu;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import managedBean.SessionUtils;
import managedDao.DynamicFunctionsDAO;
import managedModal.DynamicFunctionModel;

@ManagedBean(name = "appMenu")
@SessionScoped
public class AppMenu {

    private boolean admin, triage, clinician, account, lab, dispens, pharm, ward, xray, surgery;
    private String userid, department;
    private List<MenuCategory> menuCategories;
    private List<MenuItem> menuItems;
    private List<DynamicFunctionModel> privillage_list;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isTriage() {
        return triage;
    }

    public void setTriage(boolean triage) {
        this.triage = triage;
    }

    public boolean isClinician() {
        return clinician;
    }

    public void setClinician(boolean clinician) {
        this.clinician = clinician;
    }

    public boolean isAccount() {
        return account;
    }

    public void setAccount(boolean account) {
        this.account = account;
    }

    public boolean isLab() {
        return lab;
    }

    public void setLab(boolean lab) {
        this.lab = lab;
    }

    public boolean isDispens() {
        return dispens;
    }

    public void setDispens(boolean dispens) {
        this.dispens = dispens;
    }

    public boolean isPharm() {
        return pharm;
    }

    public void setPharm(boolean pharm) {
        this.pharm = pharm;
    }

    public boolean isWard() {
        return ward;
    }

    public void setWard(boolean ward) {
        this.ward = ward;
    }

    public boolean isXray() {
        return xray;
    }

    public void setXray(boolean xray) {
        this.xray = xray;
    }

    public boolean isSurgery() {
        return surgery;
    }

    public void setSurgery(boolean surgery) {
        this.surgery = surgery;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<DynamicFunctionModel> getPrivillage_list() {
        return privillage_list;
    }

    public void setPrivillage_list(List<DynamicFunctionModel> privillage_list) {
        this.privillage_list = privillage_list;
    }

    // CHECKSTYLE:OFF
    @PostConstruct
    public void init() {
        this.admin = false;
        this.triage = false;
        this.clinician = false;
        this.account = false;
        this.lab = false;
        this.dispens = false;
        this.pharm = false;
        this.ward = false;
        this.xray = false;
        this.surgery = false;

     

        try {
            HttpSession session = SessionUtils.getSession();
            this.department = session.getAttribute("department").toString();
            this.userid = session.getAttribute("userid").toString();
            privillage_list = DynamicFunctionsDAO.dropDowns("privillages", "Id", "Privillage", "UID", this.userid);
        } catch (Exception e) {

        }

           if (this.department.equals("Systems Developer")) {
            this.admin = true;
            this.triage = true;
            this.clinician = true;
            this.account = true;
            this.lab = true;
            this.dispens = true;
            this.pharm = true;
            this.ward = true;
            this.xray = true;
            this.surgery = true;
        }else{
        for (int i = 0; i < privillage_list.size(); i++) {
            this.admin = privillage_list.get(i).getDropdownValue().equals("Administration") ? true : this.admin;
            this.triage = privillage_list.get(i).getDropdownValue().equals("Triage") ? true : this.triage;
            this.clinician = privillage_list.get(i).getDropdownValue().equals("Clinician") ? true : this.clinician;
            this.account = privillage_list.get(i).getDropdownValue().equals("Account") ? true : this.account;
            this.lab = privillage_list.get(i).getDropdownValue().equals("Laboratory") ? true : this.lab;
            this.dispens = privillage_list.get(i).getDropdownValue().equals("Dispensary") ? true : this.dispens;
            this.pharm = privillage_list.get(i).getDropdownValue().equals("Pharmacy") ? true : this.pharm;
            this.ward = privillage_list.get(i).getDropdownValue().equals("Ward") ? true : this.ward;
            this.xray = privillage_list.get(i).getDropdownValue().equals("Xray") ? true : this.xray;
            this.surgery = privillage_list.get(i).getDropdownValue().equals("Surgery") ? true : this.surgery;

        }}
        menuCategories = new ArrayList<>();
        menuItems = new ArrayList<>();

        if (this.admin) {
            //Administration
            List<MenuItem> AdminMenuItems = new ArrayList<>();
            //system users
            List<MenuItem> system_users = new ArrayList<>();
            system_users.add(new MenuItem("New user", "/pages/admin/users"));
            system_users.add(new MenuItem("Edit users ", "/pages/admin/users_list"));
            AdminMenuItems.add(new MenuItem("Users Settings", system_users));
            List<MenuItem> departments = new ArrayList<>();
            //department
            departments.add(new MenuItem("New department ", "/pages/admin/new_department"));
            departments.add(new MenuItem("Edit departments ", "/pages/admin/department_list"));
            AdminMenuItems.add(new MenuItem("Department Settings", departments));
            //laboratory
            List<MenuItem> lab_setting = new ArrayList<>();
            lab_setting.add(new MenuItem("Test Categories ", "/pages/admin/test_category"));
            lab_setting.add(new MenuItem("Lab Tests ", "/pages/admin/lab_test"));
            lab_setting.add(new MenuItem("Lab Spesfic Test ", "/pages/admin/spesific_test"));
            lab_setting.add(new MenuItem("Lab Subtest Test ", "/pages/admin/subtest"));
            lab_setting.add(new MenuItem("Lab Expected results ", "/pages/admin/expected_resullts"));
            AdminMenuItems.add(new MenuItem("Laboratory settings", lab_setting));

            menuCategories.add(new MenuCategory("Administrator", AdminMenuItems));
        }
        //Triage
        if (this.triage) {
            List<MenuItem> TriageMenuItems = new ArrayList<>();
            TriageMenuItems.add(new MenuItem("Reception", "/pages/triage/reception"));
            TriageMenuItems.add(new MenuItem("Recent Patients", "/pages/triage/incoming"));
            menuCategories.add(new MenuCategory("Triage", TriageMenuItems));
        }
        //Clinician
        if (this.clinician) {
            List<MenuItem> ClinicianMenuItems = new ArrayList<>();
            List<MenuItem> Manage_Patients = new ArrayList<>();
            Manage_Patients.add(new MenuItem("Incoming Patient", "/pages/clinician/incoming"));
            Manage_Patients.add(new MenuItem("HMIS Register", "/pages/clinician/register"));
            ClinicianMenuItems.add(new MenuItem("Manage Patients", Manage_Patients));

            List<MenuItem> clinical_reports = new ArrayList<>();
            clinical_reports.add(new MenuItem("patient history", "/pages/clinician/patient_history"));
            clinical_reports.add(new MenuItem("Past visits", "/pages/clinician/past_visits"));
            ClinicianMenuItems.add(new MenuItem("Clinical reports", clinical_reports));
            menuCategories.add(new MenuCategory("Consultation", ClinicianMenuItems));
        }

        //accounts
        if (this.account) {
            List<MenuItem> AccountsMenuItems = new ArrayList<>();
            AccountsMenuItems.add(new MenuItem("Incoming patients", "/pages/accounts/incoming"));
            AccountsMenuItems.add(new MenuItem("Receipting", "/pages/accounts/receipting"));
            List<MenuItem> account_reports = new ArrayList<>();
            account_reports.add(new MenuItem("previous receipts", "/pages/accounts/previous_receipt"));
            account_reports.add(new MenuItem("Payment Reports ", "/pages/accounts/payment_reports"));
            AccountsMenuItems.add(new MenuItem("Account reports", account_reports));
            menuCategories.add(new MenuCategory("Accounts", AccountsMenuItems));
        }
        //Laboratory & Diagnostics
        if (this.lab) {
            List<MenuItem> labMenuItems = new ArrayList<>();
            labMenuItems.add(new MenuItem("Reception", "/pages/lab/reception"));
            labMenuItems.add(new MenuItem("Incoming patients", "/pages/lab/incoming"));
            List<MenuItem> sandries = new ArrayList<>();
            sandries.add(new MenuItem("Request sandries", "/pages/lab/requests"));
            sandries.add(new MenuItem("Stock at hand ", "/pages/lab/stock"));
            labMenuItems.add(new MenuItem("Sandries", sandries));
            List<MenuItem> lab_reports = new ArrayList<>();
            lab_reports.add(new MenuItem("Laboratory Attendance ", "/pages/lab/attendance"));
            lab_reports.add(new MenuItem("Laboratory Register ", "/pages/lab/register"));
            labMenuItems.add(new MenuItem("Reports", lab_reports));
            menuCategories.add(new MenuCategory("Laboratory & Diagnostics", labMenuItems));
        }

//        Dispensary
        if (this.dispens) {
            List<MenuItem> DispensaryMenuItems = new ArrayList<>();
            DispensaryMenuItems.add(new MenuItem("Incoming patients", "/pages/dispensary/incoming"));
            DispensaryMenuItems.add(new MenuItem("Recent discharges", "/pages/dispensary/discharges"));
            List<MenuItem> dispensary_drug = new ArrayList<>();
            dispensary_drug.add(new MenuItem("Request drugs/items ", "/pages/dispensary/requests"));
            dispensary_drug.add(new MenuItem("Stock at hand ", "/pages/dispensary/stock"));
            DispensaryMenuItems.add(new MenuItem("Drugs", dispensary_drug));
            menuCategories.add(new MenuCategory("Dispensary", DispensaryMenuItems));
        }

//        Pharmacy
        if (this.pharm) {
            List<MenuItem> pharmacyMenuItems = new ArrayList<>();

//        settings
            List<MenuItem> settings = new ArrayList<>();
            settings.add(new MenuItem("Drug list", "/pages/pharmacy/settings/list"));
            settings.add(new MenuItem("Suppliers", "/pages/pharmacy/settings/supplier"));
            settings.add(new MenuItem("Demanders", "/pages/pharmacy/settings/demander"));
            settings.add(new MenuItem("sister hospital", "/pages/pharmacy/settings/hospital"));
            settings.add(new MenuItem("ordering type", "/pages/pharmacy/settings/order"));
            settings.add(new MenuItem("User Profile ", "/pages/pharmacy/settings/user"));
            pharmacyMenuItems.add(new MenuItem("Settings", settings));

//        Drug management
            List<MenuItem> drug_management = new ArrayList<>();
            drug_management.add(new MenuItem("Purchase Order ", "/pages/pharmacy/drug_management/order"));
            drug_management.add(new MenuItem("Receipt ", "/pages/pharmacy/drug_management/receipt"));
            drug_management.add(new MenuItem("Demander Requests ", "/pages/pharmacy/drug_management/requests"));
            drug_management.add(new MenuItem("Returns from demander ", "/pages/pharmacy/drug_management/d_returns"));
            drug_management.add(new MenuItem("Returns to Supplier ", "/pages/pharmacy/drug_management/s_return"));
            drug_management.add(new MenuItem("Disposing off ", "/pages/pharmacy/drug_management/dispose"));
            drug_management.add(new MenuItem("Adjustments ", "/pages/pharmacy/drug_management/adjustment"));
            drug_management.add(new MenuItem("Stock taking ", "/pages/pharmacy/drug_management/stocktaking"));
            pharmacyMenuItems.add(new MenuItem("Drug management", drug_management));

//        Reports
            List<MenuItem> drug_reports = new ArrayList<>();
            drug_reports.add(new MenuItem("Current Stock ", "/pages/pharmacy/drug_reports/stock"));
            drug_reports.add(new MenuItem("Expiries ", "/pages/pharmacy/drug_reports/expires"));
            drug_reports.add(new MenuItem("issued Drugs/Items ", "/pages/pharmacy/drug_reports/issues"));
            drug_reports.add(new MenuItem("Disposed Drugs/Items ", "/pages/pharmacy/drug_reports/disposes"));
            drug_reports.add(new MenuItem("Adjustments ", "/pages/pharmacy/drug_reports/adjustments"));
            drug_reports.add(new MenuItem("Returns from demander ", "/pages/pharmacy/drug_reports/d_returns"));
            drug_reports.add(new MenuItem("Returns to supplier ", "/pages/pharmacy/drug_reports/s_returns"));
            drug_reports.add(new MenuItem("Days out of stock ", "/pages/pharmacy/drug_reports/stock_out"));
            drug_reports.add(new MenuItem("Stock cards", "/pages/pharmacy/drug_reports/stockcard"));
            drug_reports.add(new MenuItem("Stock Book ", "/pages/pharmacy/drug_reports/stockbook"));
            pharmacyMenuItems.add(new MenuItem("Reports", drug_reports));
            menuCategories.add(new MenuCategory("Pharmacy", pharmacyMenuItems));
        }
        //ward

        if (this.ward) {
            List<MenuItem> WardMenuItems = new ArrayList<>();
            //manage patients
            List<MenuItem> manage_patients = new ArrayList<>();
            manage_patients.add(new MenuItem("Night Reception", "/pages/ward/incoming"));
            manage_patients.add(new MenuItem("Patients to be admitted", "/pages/ward/admission"));
            manage_patients.add(new MenuItem("Patients on ward", "/pages/ward/onward"));
            manage_patients.add(new MenuItem("Discharge patients", "/pages/ward/discharges"));
            manage_patients.add(new MenuItem("HMIS Reports", "/pages/ward/hmis"));
            WardMenuItems.add(new MenuItem("Manage Patients", manage_patients));
            List<MenuItem> drugmngt = new ArrayList<>();
            //drug/sandries managent
            drugmngt.add(new MenuItem("Request Drugs/Sandries", "/pages/ward/requests"));
            drugmngt.add(new MenuItem("Stock at hand ", "/pages/ward/stock"));
            WardMenuItems.add(new MenuItem("Drug/Sandries managent", drugmngt));
            //reports
            List<MenuItem> wardreports = new ArrayList<>();
            wardreports.add(new MenuItem("Patient history ", "/pages/ward/history"));
            wardreports.add(new MenuItem("Ward Monitoring Report  ", "/pages/ward/monitoring"));
            wardreports.add(new MenuItem("Ward Census  ", "/pages/ward/census"));
            wardreports.add(new MenuItem("Ward Diagnoses ", "/pages/ward/diagnosis"));
            WardMenuItems.add(new MenuItem("Reports", wardreports));
            menuCategories.add(new MenuCategory("Ward", WardMenuItems));
        }
//         Xray
        //Triage
        if (this.xray) {
            List<MenuItem> XrayMenuItems = new ArrayList<>();
            XrayMenuItems.add(new MenuItem("Incoming Patient", "/pages/xray/incoming"));
            XrayMenuItems.add(new MenuItem("patient report", "/pages/xray/patient_report"));
            XrayMenuItems.add(new MenuItem("Request Drugs/Sandries", "/pages/xray/request"));
            XrayMenuItems.add(new MenuItem("Stock at hand", "/pages/xray/stock"));
            menuCategories.add(new MenuCategory("Xray & Scan", XrayMenuItems));
        }
//          Surgery
        if (this.surgery) {
            List<MenuItem> surgeryMenuItems = new ArrayList<>();
            surgeryMenuItems.add(new MenuItem("Incoming Patient", "/pages/surgery/incoming"));
            surgeryMenuItems.add(new MenuItem("patient report", "/pages/surgery/patient_report"));
            surgeryMenuItems.add(new MenuItem("patient appointments", "/pages/surgery/appointment"));
            surgeryMenuItems.add(new MenuItem("Request Drugs/Sandries", "/pages/surgery/request"));
            surgeryMenuItems.add(new MenuItem("Stock at hand", "/pages/surgery/stock"));
            menuCategories.add(new MenuCategory("Surgery", surgeryMenuItems));
        }

        //MISC CATEGORY END
        for (MenuCategory category : menuCategories) {
            for (MenuItem menuItem : category.getMenuItems()) {
                menuItem.setParentLabel(category.getLabel());
                if (menuItem.getUrl() != null) {
                    menuItems.add(menuItem);
                }
                if (menuItem.getMenuItems() != null) {
                    for (MenuItem item : menuItem.getMenuItems()) {
                        item.setParentLabel(menuItem.getLabel());
                        if (item.getUrl() != null) {
                            menuItems.add(item);
                        }
                    }
                }
            }
        }
    }

    public List<MenuItem> completeMenuItem(String query) {
        String queryLowerCase = query.toLowerCase();
        List<MenuItem> filteredItems = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.getUrl() != null && (item.getLabel().toLowerCase().contains(queryLowerCase)
                    || item.getParentLabel().toLowerCase().contains(queryLowerCase))) {
                filteredItems.add(item);
            }
            if (item.getBadge() != null) {
                if (item.getBadge().toLowerCase().contains(queryLowerCase)) {
                    filteredItems.add(item);
                }
            }
        }
        filteredItems.sort(Comparator.comparing(MenuItem::getParentLabel));
        return filteredItems;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public List<MenuCategory> getMenuCategories() {
        return menuCategories;
    }
}
