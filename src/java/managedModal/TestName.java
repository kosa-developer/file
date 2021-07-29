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

public class TestName implements Serializable {
    private Integer test_category_id;
    private Integer test_name_id;
    private String test_name;
    private String test_category;
    private Integer testprice;

    public Integer getTest_category_id() {
        return test_category_id;
    }

    public Integer getTestprice() {
        return testprice;
    }

    public void setTestprice(Integer testprice) {
        this.testprice = testprice;
    }

    public void setTest_category_id(Integer test_category_id) {
        this.test_category_id = test_category_id;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public Integer getTest_name_id() {
        return test_name_id;
    }

    public void setTest_name_id(Integer test_name_id) {
        this.test_name_id = test_name_id;
    }

    public String getTest_category() {
        return test_category;
    }

    public void setTest_category(String test_category) {
        this.test_category = test_category;
    }
    
    public TestName(Integer test_category_id,Integer test_name_id,String test_name,String test_category,Integer testprice){
    this.test_category_id=test_category_id;
    this.test_name_id=test_name_id;
    this.test_name=test_name;
    this.test_category=test_category;
    this.testprice=testprice;
    }
    
    
}
