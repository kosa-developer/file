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

public class ExpectedResult implements Serializable {
    private Integer test_name_id;
    private Integer specific_test_id;
    private Integer sub_test_id;
    private String expected_result;
    private Integer expected_result_id;
    private String test_category;
    private String test_name;
    private String specific_test;
    private String sub_test;

    public Integer getTest_name_id() {
        return test_name_id;
    }

    public void setTest_name_id(Integer test_name_id) {
        this.test_name_id = test_name_id;
    }

    public Integer getSpecific_test_id() {
        return specific_test_id;
    }

    public void setSpecific_test_id(Integer specific_test_id) {
        this.specific_test_id = specific_test_id;
    }

    public Integer getSub_test_id() {
        return sub_test_id;
    }

    public void setSub_test_id(Integer sub_test_id) {
        this.sub_test_id = sub_test_id;
    }

    public String getExpected_result() {
        return expected_result;
    }

    public void setExpected_result(String expected_result) {
        this.expected_result = expected_result;
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

    public String getSpecific_test() {
        return specific_test;
    }

    public void setSpecific_test(String specific_test) {
        this.specific_test = specific_test;
    }

    public String getSub_test() {
        return sub_test;
    }

    public void setSub_test(String sub_test) {
        this.sub_test = sub_test;
    }

    public Integer getExpected_result_id() {
        return expected_result_id;
    }

    public void setExpected_result_id(Integer expected_result_id) {
        this.expected_result_id = expected_result_id;
    }
    
    
    
    
}
