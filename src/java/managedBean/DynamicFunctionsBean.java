package managedBean;

import managedDao.DynamicFunctionsDAO;
import java.util.List;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import managedModal.DynamicFunctionModel;

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
public
        class DynamicFunctionsBean {

//    public DynamicFunctionsBean() {
//              returnDatatables();
//    }
    private
            Integer firstnumber, secondnumber;
    private
            List dropdownlist;
    private
            String dropdownId;
    private
            List datatables;
 

    public
            List getDatatables() {
        return datatables;
    }

    public
            void setDatatables(List datatables) {
        this.datatables = datatables;
    }

    private
            List<DynamicFunctionModel> specialdropdownlist;

    public
            List<DynamicFunctionModel> getSpecialdropdownlist() {
        return this.specialdropdownlist;
    }

    public
            void setSpecialdropdownlist(List<DynamicFunctionModel> specialdropdownlist) {
        this.specialdropdownlist = specialdropdownlist;
    }

    public
            String getDropdownId() {
        return this.dropdownId;
    }

    public
            void setDropdownId(String dropdownId) {
        this.dropdownId = dropdownId;
    }

    public
            List getDropdownlist() {
        return dropdownlist;
    }

    public
            void setDropdownlist(List dropdownlist) {
        this.dropdownlist = dropdownlist;
    }

    public
            List<DynamicFunctionModel> dropDowns(String tableName, String id, String name) {

        try {
            dropdownlist = DynamicFunctionsDAO.dropDowns(tableName, id, name);
            return dropdownlist;
        }
        catch (Exception ex) {
            System.out.println("Exception In Admin Bean: admin_get_test_categories: " + ex.getMessage());
            return null;
        }
    }
   public
            List<DynamicFunctionModel> dropDownsDruglist(String tableName, String id, String name) {

        try {
            dropdownlist = DynamicFunctionsDAO.dropDownsDruglist(tableName, id, name);
            return dropdownlist;
        }
        catch (Exception ex) {
            System.out.println("Exception In Admin Bean: admin_get_test_categories: " + ex.getMessage());
            return null;
        }
    }
    public
            List<DynamicFunctionModel> ReturnSpecialdropdownList(String tableName, String id, String fieldName, String Baseidname, String BaseIdValue) {
        try {
            this.specialdropdownlist = DynamicFunctionsDAO.dropDowns(tableName, id, fieldName, Baseidname, BaseIdValue);
            return specialdropdownlist;
        }
        catch (Exception ex) {
            System.out.println("Exception In Admin Bean: admin_get_test_categories: " + ex.getMessage());
            return null;
        }
    }

    public
            boolean filterByName(Object value, Object filter, Locale locale) {
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
        }
        else {
            return false;
        }
    }

     public boolean Check_if_exsistsNOTNULL(String table, String column1,String value, String column2) {
      try{
     
     return DynamicFunctionsDAO.Check_if_exsistsandnotnull(table, column1,value,column2);
     }
     catch(Exception e){
return false;
}
     
     }
}
