/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedModal;

import java.io.Serializable;

/**
 *
 * @author PATRICK
 */
public class DynamicFunctionModel implements Serializable {

    private String dropdownId;
    private String dropdownValue;
    private String dropdownBaseId;
    
    public String getDropdownValue() {
        return dropdownValue;
    }

    public void setDropdownValue(String dropdownValue) {
        this.dropdownValue = dropdownValue;
    }

    public void setDropdownId(String dropdownId) {
        this.dropdownId = dropdownId;
    }

    public String getDropdownId() {
        return dropdownId;
    }

    public String getDropdownBaseId() {
        return dropdownBaseId;
    }

    public void setDropdownBaseId(String dropdownBaseId) {
        this.dropdownBaseId = dropdownBaseId;
    }

    public DynamicFunctionModel(){}
    
    public DynamicFunctionModel(String dropdownId, String dropdownBaseId , String dropdownValue){
    this.dropdownId=dropdownId;
    this.dropdownBaseId=dropdownBaseId;
    this.dropdownValue=dropdownValue;
    }
}
