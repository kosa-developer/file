package managedBean;

import java.sql.SQLException;
import managedDao.DynamicFunctionsDAO;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import managedModal.DynamicFunctionModel;
import managedModal.TestName;
import org.primefaces.event.RowEditEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author PATRICK
 */
@ManagedBean(name = "dynamicFunctionsBean")
@SessionScoped
public class DynamicFunctionsBean {

//    public DynamicFunctionsBean() {
//              returnDatatables();
//    }
    private Integer firstnumber, secondnumber;
    private List dropdownlist;
    private String dropdownId;
    private List datatables;

    public List getDatatables() {
        return datatables;
    }

    public void setDatatables(List datatables) {
        this.datatables = datatables;
    }

    private List<DynamicFunctionModel> specialdropdownlist;

    public List<DynamicFunctionModel> getSpecialdropdownlist() {
        return this.specialdropdownlist;
    }

    public void setSpecialdropdownlist(List<DynamicFunctionModel> specialdropdownlist) {
        this.specialdropdownlist = specialdropdownlist;
    }

    public String getDropdownId() {
        return this.dropdownId;
    }

    public void setDropdownId(String dropdownId) {
        this.dropdownId = dropdownId;
    }

    public List getDropdownlist() {
        return dropdownlist;
    }

    public void setDropdownlist(List dropdownlist) {
        this.dropdownlist = dropdownlist;
    }

    public List<DynamicFunctionModel> dropDowns(String tableName, String id, String name) {

        try {
            dropdownlist = DynamicFunctionsDAO.dropDowns(tableName, id, name);
            return dropdownlist;
        } catch (Exception ex) {
            System.out.println("Exception In Admin Bean: admin_get_test_categories: " + ex.getMessage());
            return null;
        }
    }

    public List<DynamicFunctionModel> dropDownsDruglist(String tableName, String id, String name) {

        try {
            dropdownlist = DynamicFunctionsDAO.dropDownsDruglist(tableName, id, name);
            return dropdownlist;
        } catch (Exception ex) {
            System.out.println("Exception In Admin Bean: admin_get_test_categories: " + ex.getMessage());
            return null;
        }
    }

    public List<DynamicFunctionModel> ReturnSpecialdropdownList(String tableName, String id, String fieldName, String Baseidname, String BaseIdValue) {
        try {
            this.specialdropdownlist = DynamicFunctionsDAO.dropDowns(tableName, id, fieldName, Baseidname, BaseIdValue);
            return specialdropdownlist;
        } catch (Exception ex) {
            System.out.println("Exception In Admin Bean: admin_get_test_categories: " + ex.getMessage());
            return null;
        }
    }

    public boolean filterByName(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            return true;
        }

        if (value == null) {
            return false;
        }

        String filtedValue = value.toString().toUpperCase();
        filterText = filterText.toUpperCase();

        if (filtedValue.contains(filterText)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean Check_if_exsistsNOTNULL(String table, String column1, String value, String column2) {
        try {

            return DynamicFunctionsDAO.Check_if_exsistsandnotnull(table, column1, value, column2);
        } catch (Exception e) {
            return false;
        }

    }

    public void admin_edit_table_data(String table_name, String field_name, String field_id_name, String field_id, String fieldValue) {

        if (fieldValue.equals("")) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Please enter data and try again", "Failure"));

        } else {

            try {
                if (DynamicFunctionsDAO.Check_if_exsists(fieldValue, table_name, field_name)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, fieldValue + " already exisits", "Failure"));
                } else {

                    String[][] field = {{field_name}, {fieldValue}};

                    if (DynamicFunctionsDAO.Edit(table_name, field, field_id_name, field_id)) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data has been edited to " + fieldValue, "Success"));

                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data editing Failed", "Failure"));

                    }
                }

            } catch (SQLException ex) {

            }

        }
    }

    public void onRowCancel(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Event cancelled", "Failure"));
    }

    public void admin_edit_dataTableDoublecolumns(String table_name, String field_name, String field_id_name, String field_id, String fieldValue, String field_name2, String fieldValue2) {

        if (fieldValue.equals("") && fieldValue2.equals("")) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Please enter data and try again", "Failure"));

        } else {

            try {
                    if (fieldValue.equals("")||fieldValue2.equals("")) {
                        if (fieldValue2.equals("")) {
                        String[][] field = {{field_name}, {fieldValue}};
                          if (DynamicFunctionsDAO.Edit(table_name, field, field_id_name, field_id)) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data has been edited to " + fieldValue, "Success"));

                    }
                    }else{
                         String[][] field = {{field_name2}, {fieldValue2}};
                           if (DynamicFunctionsDAO.Edit(table_name, field, field_id_name, field_id)) {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data has been edited to " + fieldValue2, "Success"));

                        }
                        }
                    }
                    else {
                        String[][] field = {{field_name, field_name2}, {fieldValue, fieldValue2}};
                          if (DynamicFunctionsDAO.Edit(table_name, field, field_id_name, field_id)) {
                          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data has been edited to "+fieldValue+","+fieldValue2, "Success"));

                   
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data editing Failed", "Failure"));

                    }
                    }

                  

            } catch (SQLException ex) {

            }

        }
    }

    public void onRowEditTestname(RowEditEvent<TestName> event) {

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, " " + String.valueOf(event.getObject().getTest_name()), "Failure"));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ", " + String.valueOf(event.getObject().getTest_name_id()), "Failure"));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ", " + String.valueOf(event.getObject().getTestprice()), "Failure"));
    }

    public void onRowCancelTestname(RowEditEvent<TestName> event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, " " + String.valueOf(event.getObject().getTest_name()), "Failure"));

    }

}
