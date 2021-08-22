package managedModal;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;

public class LabTestResults
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer test_id;
    private String test_name;
    private String General_test;
    private String General_results;
    private String test_normal_values;
    private String test_results;
    private String test_carriedout_by;
    private String specific_test;
    private String sub_test;
    private String expected_result;
    private String other_result;
    private Integer request_id;
    private Integer result_id;
    private String start_time;
    private String end_time;
    private Integer sub_test_id;
    private Integer specific_test_id;
    private Integer expected_result_id;
    private String comment;
    private String comment_blacket;
    private boolean rendering_confirm;
    private boolean rendering_edit;
    private String sample_type;
    private Date resultdate;

    public String getSample_type() {
        return sample_type;
    }

    public void setSample_type(String sample_type) {
        this.sample_type = sample_type;
    }
    public Integer getExpected_result_id() {
        return expected_result_id;
    }

    public void setExpected_result_id(Integer expected_result_id) {
        this.expected_result_id = expected_result_id;
    }

    
    public Integer getSpecific_test_id() {
        return specific_test_id;
    }

    public void setSpecific_test_id(Integer specific_test_id) {
        this.specific_test_id = specific_test_id;
    }

    public Date getResultdate() {
        return resultdate;
    }

    public void setResultdate(Date resultdate) {
        this.resultdate = resultdate;
    }

    
    public Integer getTest_id() {
        return test_id;
    }

    public void setTest_id(Integer test_id) {
        this.test_id = test_id;
    }

    public boolean isRendering_confirm() {
        return rendering_confirm;
    }

    public boolean isRendering_edit() {
        return rendering_edit;
    }

    public void setRendering_confirm(boolean rendering_confirm) {
        this.rendering_confirm = rendering_confirm;
    }

    public void setRendering_edit(boolean rendering_edit) {
        this.rendering_edit = rendering_edit;
    }
    

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getSub_test_id() {
        return sub_test_id;
    }

    public void setSub_test_id(Integer sub_test_id) {
        this.sub_test_id = sub_test_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public Integer getResult_id() {
        return result_id;
    }

    public void setResult_id(Integer result_id) {
        this.result_id = result_id;
    }

    public Integer getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
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

    public String getExpected_result() {
        return expected_result;
    }

    public void setExpected_result(String expected_result) {
        this.expected_result = expected_result;
    }

    public String getOther_result() {
        return other_result;
    }

    public void setOther_result(String other_result) {
        this.other_result = other_result;
    }

    public String getTest_name() {
        return this.test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public String getTest_normal_values() {
        return this.test_normal_values;
    }

    public void setTest_normal_values(String test_normal_values) {
        this.test_normal_values = test_normal_values;
    }

    public String getTest_results() {
        return this.test_results;
    }

    public void setTest_results(String test_results) {
        this.test_results = test_results;
    }

    public String getTest_carriedout_by() {
        return this.test_carriedout_by;
    }

    public void setTest_carriedout_by(String test_carriedout_by) {
        this.test_carriedout_by = test_carriedout_by;
    }

    public String getGeneral_test() {
        return General_test;
    }

    public String getGeneral_results() {
        return General_results;
    }

    public void setGeneral_test(String General_test) {
        this.General_test = General_test;
    }

    public void setGeneral_results(String General_results) {
        this.General_results = General_results;
    }

    public String getComment_blacket() {
        return comment_blacket;
    }

    public void setComment_blacket(String comment_blacket) {
        this.comment_blacket = comment_blacket;
    }
    

}
