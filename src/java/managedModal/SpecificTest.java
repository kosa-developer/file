/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedModal;

/**
 *
 * @author romugabi
 */
import java.io.Serializable;

public class SpecificTest implements Serializable {
    private Integer test_name_id;
    private Integer specific_test_id;
    private String specific_test;
    private String test_category;
    private String test_name;

    public Integer getTest_name_id() {
        return test_name_id;
    }

    public void setTest_name_id(Integer test_name_id) {
        this.test_name_id = test_name_id;
    }

    public String getSpecific_test() {
        return specific_test;
    }

    public void setSpecific_test(String specific_test) {
        this.specific_test = specific_test;
    }

    public Integer getSpecific_test_id() {
        return specific_test_id;
    }

    public void setSpecific_test_id(Integer specific_test_id) {
        this.specific_test_id = specific_test_id;
    }

    public String getTest_category() {
        return test_category;
    }

    public void setTest_category(String test_category) {
        this.test_category = test_category;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }
    
    
    
    
    
}
