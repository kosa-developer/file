/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedModal;

import java.io.Serializable;

/**
 *
 * @author romugabi
 */
public class TestReport implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer request_id;
    private String lab_Id;
    private String test_desc;
    private String normal_values;
    private String results;
    private String sample_type;
    private String comment;
    private String performed_by;
    private String record_date;

    public String getLab_Id() {
        return lab_Id;
    }

    public void setLab_Id(String lab_Id) {
        this.lab_Id = lab_Id;
    }

    public Integer getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
    }

    public String getTest_desc() {
        return test_desc;
    }

    public void setTest_desc(String test_desc) {
        this.test_desc = test_desc;
    }

    public String getNormal_values() {
        return normal_values;
    }

    public void setNormal_values(String normal_values) {
        this.normal_values = normal_values;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getSample_type() {
        return sample_type;
    }

    public void setSample_type(String sample_type) {
        this.sample_type = sample_type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPerformed_by() {
        return performed_by;
    }

    public void setPerformed_by(String performed_by) {
        this.performed_by = performed_by;
    }

    public String getRecord_date() {
        return record_date;
    }

    public void setRecord_date(String record_date) {
        this.record_date = record_date;
    }

    public TestReport() {
    }
}
