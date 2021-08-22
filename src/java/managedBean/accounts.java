/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import managedDao.AccountsDAO;
import managedDao.DynamicFunctionsDAO;
import managedDao.ReceptionDAO;
import managedModal.AccountsPayments;
import managedModal.AccountsPaymentsView;
import managedModal.AccountsTasks;
import managedModal.Patient;
import managedModal.ReceptionInfo;

@ManagedBean(name = "accountsBean")
@SessionScoped
/**
 *
 * @author Programmer
 */
public class accounts {

    private List<AccountsPayments> payment_list;
    private boolean payment_flag;
    private Patient patient_view;
    private AccountsPayments payment;
    private ReceptionInfo reception_info_view;

    public List<AccountsPayments> getPayment_list() {
        return payment_list;
    }

    public void setPayment_list(List<AccountsPayments> payment_list) {
        this.payment_list = payment_list;
    }

    public boolean isPayment_flag() {
        return payment_flag;
    }

    public void setPayment_flag(boolean payment_flag) {
        this.payment_flag = payment_flag;
    }

    public Patient getPatient_view() {
        return patient_view;
    }

    public ReceptionInfo getReception_info_view() {
        return reception_info_view;
    }

    public void setReception_info_view(ReceptionInfo reception_info_view) {
        this.reception_info_view = reception_info_view;
    }

    public AccountsPayments getPayment() {
        return payment;
    }

    public void setPayment(AccountsPayments payment) {
        this.payment = payment;
    }

    public void setPatient_view(Patient patient_view) {
        this.patient_view = patient_view;
    }

    public List<AccountsTasks> accounts_get_forwaded_jobs(String user_id) {
        try {
            return AccountsDAO.Accounts_Get_Pending(user_id);
        } catch (SQLException ex) {
            System.err.println("AccountsBean Error Message: " + ex.getMessage());
            return null;
        }
    }

    public String accounts_get_selected_task(String task_id, String user_id) {
        try {
            this.patient_view = ReceptionDAO.Reception_Retrieve_Patient_Details(task_id);
            this.reception_info_view = ReceptionDAO.Reception_Get_Reception_Info(task_id);
            payment_list = AccountsDAO.Accounts_Get_Payments(this.patient_view.getTrack_id());
            if (payment_list.size() > 1) {
                payment_flag = false;
            }

            return "/pages/accounts/makePayments.xhtml";
        } catch (Exception ex) {
            System.out.println(" AccountsBean Error Message:" + ex.getMessage());
            return null;
        }
    }

    public List<AccountsPaymentsView> accounts_get_payments_view() {
        try {
            return AccountsDAO.Accounts_Get_Payments_All(this.patient_view.getTrack_id());
        } catch (Exception ex) {
            System.out.println(" AccountsBean Error Message:" + ex.getMessage());
            return null;
        }
    }

    public String accounts_forward_task(String user_id) {
        try {
            if (!"".equals(this.patient_view.getTrack_id())) {
                String alredyexits = "", error = "";
                Integer submitted = 0;
                for (int i = 0; i < payment_list.size(); i++) {
                    String[][] fields={{"Request_id"},{payment_list.get(i).getRequest_id().toString()}};
                    
                    if(DynamicFunctionsDAO.Check_if_exsists("accounts_tasks", fields)){
                          alredyexits += "," + payment_list.get(i).getOperation();
                    }else{
                    if (AccountsDAO.Accounts_Add_Task(this.patient_view.getTrack_id(), payment_list.get(i).getOperation(), payment_list.get(i).getAmount(), payment_list.get(i).getPaid(), payment_list.get(i).getRequest_id(),user_id)) {
                        submitted++;
                    } else {
                        error += payment_list.get(i).getOperation() + ",";
                    }}
                }
                 payment_list = AccountsDAO.Accounts_Get_Payments(this.patient_view.getTrack_id());
                if (submitted > 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, submitted + " Services have been paid for. Thank You", "Success"));

                }
                 if (!error.equals("")) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, error+" are missing Amounts, please enter Payments and try again", "Failure"));

                    }
                   if (!alredyexits.equals("")) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "You Have Already Paid  For " + alredyexits, "Failure"));
                    }

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Error.Contact Administrator", "Failure"));
                return null;
            }

        } catch (Exception ex) {
            System.out.println("AccountsBean Error Message: " + ex.getMessage());
        }
        return null;
    }

    public String back() {
        return "/pages/accounts/incoming.xhtml";
    }
    
    public String dynamicname(String data, String tablename, String column_name, String column_id){
    try{
    return DynamicFunctionsDAO.dynamicgetsingle_variable(data, tablename, column_name, column_id);
    }catch(Exception e){
    return null;
    }
    
    }
}
