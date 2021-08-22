/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedModal;

import java.io.Serializable;

/**
 *
 * @author Programmer
 */
public class testresults implements Serializable{
   private Integer sub_test_id,expected_result_id;
   private float numerical_result;
    private String sub_test,requested_test_result,test_comment;

    public Integer getSub_test_id() {
        return sub_test_id;
    }

    public void setSub_test_id(Integer sub_test_id) {
        this.sub_test_id = sub_test_id;
    }

    public Integer getExpected_result_id() {
        return expected_result_id;
    }

    public void setExpected_result_id(Integer expected_result_id) {
        this.expected_result_id = expected_result_id;
    }

    public float getNumerical_result() {
        return numerical_result;
    }

    public void setNumerical_result(float numerical_result) {
        this.numerical_result = numerical_result;
    }

  
    public String getSub_test() {
        return sub_test;
    }

    public void setSub_test(String sub_test) {
        this.sub_test = sub_test;
    }

    public String getRequested_test_result() {
        return requested_test_result;
    }

    public void setRequested_test_result(String requested_test_result) {
        this.requested_test_result = requested_test_result;
    }

    public String getTest_comment() {
        return test_comment;
    }

    public void setTest_comment(String test_comment) {
        this.test_comment = test_comment;
    }
    
}
