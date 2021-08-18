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
public class HistoryTestResults implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer request_id;
    private String test_name;
    private String sub_test;
    private Integer subtest_id;
    private String specific_test;
    private String expected_result;
    private String other_result;
    private String test_carriedout_by;
    private String general_tests;
    private String general_results;
    private String comment;
    private String comment_blackets;
    private String sample_type;
    private String test_reviewed_by;

    public String getTest_reviewed_by() {
        return test_reviewed_by;
    }

    public void setTest_reviewed_by(String test_reviewed_by) {
        this.test_reviewed_by = test_reviewed_by;
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

    public void setSubtest_id(Integer subtest_id) {
        this.subtest_id = subtest_id;
    }

    public Integer getSubtest_id() {
        return subtest_id;
    }

    public String getGeneral_results() {
        return general_results;
    }

    public void setGeneral_results(String general_results) {
        this.general_results = general_results;
    }

    public String getGeneral_tests() {
        return general_tests;
    }

    public void setGeneral_tests(String general_tests) {
        this.general_tests = general_tests;
    }

    public Integer getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public String getSub_test() {
        return sub_test;
    }

    public void setSub_test(String sub_test) {
        this.sub_test = sub_test;
    }

    public String getSpecific_test() {
        return specific_test;
    }

    public void setSpecific_test(String specific_test) {
        this.specific_test = specific_test;
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

    public String getTest_carriedout_by() {
        return test_carriedout_by;
    }

    public void setTest_carriedout_by(String test_carriedout_by) {
        this.test_carriedout_by = test_carriedout_by;
    }

    public String getComment_blackets() {
        return comment_blackets;
    }

    public void setComment_blackets(String comment_blackets) {
        this.comment_blackets = comment_blackets;
    }
    

}
