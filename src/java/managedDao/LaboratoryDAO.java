package managedDao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import managedModal.Error;
import managedModal.LabTest;
import managedModal.ReceptionTask;
import managedModal.TestCategory;
import managedModal.TestReport;
import managedModal.TestName;
import managedModal.SpecificTest;
import managedModal.SubTest;
import managedModal.ExpectedResult;
import managedModal.LabTestResults;

public
        class LaboratoryDAO implements Serializable {

    private static final
            long serialVersionUID = 1L;
    private static
            Date date;

    public static
            List<TestCategory> Laboratory_Get_Test_Categories() throws SQLException {

        try {
            Connection con;
            TestCategory test_category;

            con=Apache_Connectionpool.getInstance().getConnection();

            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM lab_tests_categories order by Category_Desc ASC");

            ResultSet rs = stmt.executeQuery();
            List test_categories = new ArrayList();

            while (rs.next()) {
                test_category = new TestCategory();
                test_category.setCategory_id(Integer.valueOf(rs.getInt("Category_Id")));
                test_category.setCategory_desc(rs.getString("Category_Desc"));
                test_categories.add(test_category);
            }
            con.close();
            rs.close();
            return test_categories;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Get_Test_Categories", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            boolean Laboratory_Add_Test(Integer category_id, String test_name, String test_normal_values) throws SQLException {

        try {
            Connection con;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("INSERT INTO lab_tests(Category_Id,Test_Desc,Test_Normal_Values) VALUES(?,?,?)");

            ps.setInt(1, category_id.intValue());
            ps.setString(2, test_name);
            ps.setString(3, test_normal_values);

            ps.executeUpdate();
            con.close();
            ps.close();

            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Add_Test", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            List<LabTest> Laboratory_Get_Tests()
            throws SQLException {

        try {
            Connection con;

            LabTest test;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT l.Test_Id,c.Category_Id,c.Category_Desc,l.Test_Desc,l.Test_Normal_Values FROM lab_tests l INNER JOIN lab_tests_categories c ON l.Category_Id=c.Category_Id order by c.Category_Desc ASC");

            ResultSet rs = stmt.executeQuery();
            List tests_list = new ArrayList();

            while (rs.next()) {
                test = new LabTest();
                test.setTest_id(Integer.valueOf(rs.getInt("Test_Id")));
                test.setCategory_id(Integer.valueOf(rs.getInt("Category_Id")));
                test.setCategory_desc(rs.getString("Category_Desc"));
                test.setTest_name(rs.getString("Test_Desc"));
                test.setTest_normal_values(rs.getString("Test_Normal_Values"));

                tests_list.add(test);
            }
            con.close();
            rs.close();
            return tests_list;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Get_Tests", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            List<LabTest> Laboratory_Get_Tests(Integer test_category_id) throws SQLException {

        try {
            Connection con;

            LabTest test;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT l.Test_Id,c.Category_Id,c.Category_Desc,l.Test_Desc,l.Test_Normal_Values FROM lab_tests l INNER JOIN lab_tests_categories c ON l.Category_Id=c.Category_Id WHERE l.Category_Id=? order by l.Test_Desc ASC");
            stmt.setInt(1, test_category_id.intValue());

            ResultSet rs = stmt.executeQuery();
            List tests_list = new ArrayList();

            while (rs.next()) {
                test = new LabTest();
                test.setTest_id(Integer.valueOf(rs.getInt("Test_Id")));
                test.setCategory_id(Integer.valueOf(rs.getInt("Category_Id")));
                test.setCategory_desc(rs.getString("Category_Desc"));
                test.setTest_name(rs.getString("Test_Desc"));
                test.setTest_normal_values(rs.getString("Test_Normal_Values"));

                tests_list.add(test);
            }
            con.close();
            rs.close();
            return tests_list;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Get_Tests", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            boolean Laboratory_Add_Test_Request(Integer test_id, String task_id, String user_id, String requesting_department, String trans_id) throws SQLException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con=Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO lab_tests_requests(Track_Id,test_name_id,Requested_By,Request_Time,Request_Department,Trans_Id)"
                    + " VALUES(?,?,?,?,?,?)");

            ps.setString(1, task_id);
            ps.setInt(2, test_id);
            ps.setString(3, user_id);
            ps.setString(4, dateFormat.format(date));
            ps.setString(5, requesting_department);
            ps.setString(6, trans_id);

            ps.executeUpdate();
            con.close();
            ps.close();

            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Add_Test_Request", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            boolean Laboratory_Add_Ward_Test_Done(Integer sub_test_id, String task_id, String user_id, String requesting_department, String trans_id) throws SQLException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con=Apache_Connectionpool.getInstance().getConnection();

// SQL still needs changing!!
            PreparedStatement ps = con.prepareStatement("INSERT INTO lab_tests_requests(Track_Id,test_name_id,Requested_By,Request_Time,Request_Department,Trans_Id)"
                    + " VALUES(?,(Select specific_tests.test_id From sub_tests,specific_tests Where sub_tests.specific_test_id=specific_tests.specific_test_id and sub_test_id=?),?,?,?,?)");

            ps.setString(1, task_id);
            ps.setInt(2, sub_test_id);
            ps.setString(3, user_id);
            ps.setString(4, dateFormat.format(date));
            ps.setString(5, requesting_department);
            ps.setString(6, trans_id);

            ps.executeUpdate();
            con.close();
            ps.close();

            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Add_Test_Request", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    /*
     * DAO Method to add Patient HIV Test Options Log
     */
    public static
            boolean Laboratory_Add_Hiv_Test_Option(String task_id, String hiv_tested, String hiv_test_today, String user_id) throws SQLException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con=Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO lab_hiv_tests(Task_Id,Test_In_6Months,Test_Today,Staff_Id,Record_Date) VALUES(?,?,?,?,?)");

            ps.setString(1, task_id);
            ps.setString(2, hiv_tested);
            ps.setString(3, hiv_test_today);
            ps.setString(4, user_id);
            ps.setString(5, dateFormat.format(date));

            ps.executeUpdate();
            con.close();
            ps.close();

            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Add_Hiv_Test_Option", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            boolean Laboratory_Hiv_Test_Option_Exists(String task_id) throws SQLException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con=Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement stmt = con.prepareStatement("Select * From lab_hiv_tests Where Task_Id=?");
            stmt.setString(1, task_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                con.close();
                return true;
            }
            con.close();
            rs.close();
            return false;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Hiv_Test_Option_Exists", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            boolean Laboratory_Update_Hiv_Test_Option(String task_id, String hiv_tested, String hiv_test_today, String user_id) throws SQLException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con=Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("UPDATE lab_hiv_tests SET Test_In_6Months=?,Test_Today=?,Staff_Id=?,Record_Date=? WHERE Task_Id=?");

            ps.setString(1, hiv_tested);
            ps.setString(2, hiv_test_today);
            ps.setString(3, user_id);
            ps.setString(4, dateFormat.format(date));
            ps.setString(5, task_id);

            ps.executeUpdate();
            con.close();
            ps.close();

            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Update_Hiv_Test_Option", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            boolean Laboratory_Pending_Task_Exists(String task_id)
            throws SQLException {

        try {
            Connection con;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM lab_pending WHERE Track_Id=? order by Record_Date ASC");
            stmt.setString(1, task_id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                con.close();
                return true;
            }
            con.close();
            rs.close();
            return false;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Consultant_Dentist_Task_Exists", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            boolean Laboratory_Add_Pending(String track_id, String staff_id, String forward_to, String urgency, String lab_id) throws SQLException {

        try {
            Connection con;

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con=Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO lab_pending(Track_Id,From_Staff,Forward_To,Record_Date,Locked,Urgency,Lab_Id) VALUES(?,?,?,?,?,?,?)");
            ps.setString(1, track_id);
            ps.setString(2, staff_id);
            ps.setString(3, forward_to);
            ps.setString(4, dateFormat.format(date));
            ps.setBoolean(5, false);
            ps.setString(6, urgency);
            ps.setString(7, lab_id);

            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Add_Pending", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static
            List<LabTest> Laboratory_Get_Tests_Requested(String task_id)
            throws SQLException {

        try {
            Connection con;
            LabTest test;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT l.Request_Id,tc.category,t.test_id,t.test_name,u.FullName FROM lab_tests_requests l INNER JOIN test_names t ON t.test_id=l.test_name_id INNER JOIN test_categories tc ON tc.category_id=t.category_id INNER JOIN users u ON l.Requested_By=u.UID WHERE l.`Status`='Pending' AND l.Track_Id=? order by l.Request_Id ASC");
            stmt.setString(1, task_id);

            ResultSet rs = stmt.executeQuery();
            List tests_list = new ArrayList();

            while (rs.next()) {
                test = new LabTest();

                test.setRequest_id(rs.getInt("Request_Id"));
                test.setTest_id(rs.getInt("Test_Id"));
                test.setCategory_desc(rs.getString("category"));
                test.setTest_name(rs.getString("test_name"));
                test.setRequested_by(rs.getString("FullName"));

                tests_list.add(test);
            }
            con.close();
            rs.close();
            return tests_list;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Get_Tests_Requested", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            List<LabTest> Laboratory_Get_Tests_Requested_Ward(String task_id, String trans_id)
            throws SQLException {

        try {
            Connection con;
            LabTest test;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT l.Request_Id,tc.category,t.test_id,t.test_name,u.FullName FROM lab_tests_requests l INNER JOIN test_names t ON t.test_id=l.test_name_id INNER JOIN test_categories tc ON tc.category_id=t.category_id INNER JOIN users u ON l.Requested_By=u.UID WHERE l.`Status`='Pending' AND l.Track_Id=? AND l.Trans_Id=? order by l.Request_Id ASC");
            stmt.setString(1, task_id);
            stmt.setString(2, trans_id);

            ResultSet rs = stmt.executeQuery();
            List tests_list = new ArrayList();

            while (rs.next()) {
                test = new LabTest();

                test.setRequest_id(rs.getInt("Request_Id"));
                test.setTest_id(rs.getInt("Test_Id"));
                test.setCategory_desc(rs.getString("category"));
                test.setTest_name(rs.getString("test_name"));
                test.setRequested_by(rs.getString("FullName"));

                tests_list.add(test);
            }
            con.close();
            rs.close();
            return tests_list;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Get_Tests_Requested", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            LabTest Laboratory_Get_Current_Ward_Test_ID(String task_id, String trans_id)
            throws SQLException {

        try {
            Connection con;
            LabTest test;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT l.Request_Id,tc.category,t.test_id,t.test_name,u.FullName FROM lab_tests_requests l INNER JOIN test_names t ON t.test_id=l.test_name_id INNER JOIN test_categories tc ON tc.category_id=t.category_id INNER JOIN users u ON l.Requested_By=u.UID WHERE l.Track_Id=? AND l.Trans_Id=? and l.Request_Id Not In (Select Request_Id From lab_tests_results)");
            stmt.setString(1, task_id);
            stmt.setString(2, trans_id);

            ResultSet rs = stmt.executeQuery();
// Ought to add a check on whether there is no, or more than 1, record returned, and if there is to generate an error message

            test = new LabTest();

            while (rs.next()) {
                test = new LabTest();

                test.setRequest_id(rs.getInt("Request_Id"));
                test.setTest_id(rs.getInt("Test_Id"));
                test.setCategory_desc(rs.getString("category"));
                test.setTest_name(rs.getString("test_name"));
                test.setRequested_by(rs.getString("FullName"));
            }

            con.close();
            rs.close();
            return test;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Get_Tests_Requested", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    //This is a redudant method to be used when lab techs are sumitting final results
    //Whoever,this should be worked ON to allow better polymophism AND better code
    // - 2014-04-09
    public static
            List<LabTest> Laboratory_Get_Tests_Requested(String task_id, String status)
            throws SQLException {

        try {
            Connection con;
            LabTest test;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT l.Request_Id,tc.category,t.test_id,t.test_name,u.FullName FROM lab_tests_requests l INNER JOIN test_names t ON t.test_id=l.test_name_id INNER JOIN test_categories tc ON tc.category_id=t.category_id INNER JOIN users u ON l.Requested_By=u.UID WHERE l.`Status`!='Completed' AND l.Track_Id=? order by l.Request_Id ASC");
            stmt.setString(1, task_id);

            ResultSet rs = stmt.executeQuery();
            List tests_list = new ArrayList();

            while (rs.next()) {
                test = new LabTest();

                test.setRequest_id(Integer.valueOf(rs.getInt("Request_Id")));
                test.setTest_id(Integer.valueOf(rs.getInt("Test_Id")));
                test.setCategory_desc(rs.getString("category"));
                test.setTest_name(rs.getString("test_name"));
                test.setRequested_by(rs.getString("FullName"));

                tests_list.add(test);
            }
            con.close();
            rs.close();
            return tests_list;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Get_Tests_Requested", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            List<LabTest> Laboratory_Get_Tests_Samples_Taken(String task_id)
            throws SQLException {

        try {
            Connection con;
            LabTest test;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

//            PreparedStatement stmt = con.prepareStatement("SELECT l.Request_Id,tc.category,t.test_id,t.test_name,u.FullName FROM lab_tests_requests l INNER JOIN test_names t ON t.test_id=l.test_name_id INNER JOIN test_categories tc ON tc.category_id=t.category_id INNER JOIN users u ON l.Requested_By=u.UID WHERE l.Status='Sample Taken' AND l.Track_Id=? order by l.Request_Id ASC");
//            PreparedStatement stmt = con.prepareStatement("SELECT l.Request_Id,tc.category,t.test_id,t.test_name,u.FullName FROM lab_tests_requests l INNER JOIN test_names t ON t.test_id=l.test_name_id INNER JOIN test_categories tc ON tc.category_id=t.category_id INNER JOIN users u ON l.Requested_By=u.UID WHERE (l.Status='Sample Taken' or l.Status='Completed') AND l.Track_Id=? order by l.Request_Id ASC");
//            PreparedStatement stmt = con.prepareStatement("SELECT l.Request_Id,tc.category,t.test_id,t.test_name,u.FullName FROM lab_tests_requests l INNER JOIN test_names t ON t.test_id=l.test_name_id INNER JOIN test_categories tc ON tc.category_id=t.category_id INNER JOIN users u ON l.Requested_By=u.UID WHERE (l.Status='Sample Taken' or l.Status='Completed') AND l.Track_Id=? AND NOT EXISTS (Select Record_Date From lab_tasks lt Where lt.Track_Id=l.Track_Id and lt.Record_Date>l.Request_Time) order by l.Request_Id ASC");
            PreparedStatement stmt = con.prepareStatement("SELECT l.Request_Id,tc.category,t.test_id,t.test_name,u.FullName FROM lab_tests_requests l INNER JOIN test_names t ON t.test_id=l.test_name_id INNER JOIN test_categories tc ON tc.category_id=t.category_id INNER JOIN users u ON l.Requested_By=u.UID WHERE (l.Status='Sample Taken' or l.Status='Completed') AND l.Track_Id=? AND NOT EXISTS (Select Record_Date From lab_tasks lt Where lt.Track_Id=l.Track_Id and lt.Record_Date>l.Request_Time) AND NOT EXISTS (Select trans_id From ward_transactions wt Where wt.trans_id=l.Trans_Id And trans_name='Laboratory Test Done On Ward') order by l.Request_Id ASC");
            stmt.setString(1, task_id);

            ResultSet rs = stmt.executeQuery();
            List tests_list = new ArrayList();

            while (rs.next()) {
                test = new LabTest();

                test.setRequest_id(rs.getInt("Request_Id"));
                test.setTest_id(rs.getInt("Test_Id"));
                test.setCategory_desc(rs.getString("category"));
                test.setTest_name(rs.getString("test_name"));
                test.setRequested_by(rs.getString("FullName"));

                tests_list.add(test);
            }
            con.close();
            rs.close();
            return tests_list;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Get_Tests_Samples_Taken", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            List<LabTestResults> Laboratory_Get_Performed_Tests(String task_id)
            throws SQLException {

        try {
//            task_id="a5170207-7e87-4472-96e9-6b0668b8946a";
            Connection con;
            LabTestResults test_result;

           con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();
            String query = "SELECT q.Request_Id,t.test_name,t.test_id, case when l.Status=1 Then (select sp.specific_test from specific_tests sp where sp.specific_test_id=l.specific_test_id) else (select sp.specific_test from specific_tests sp where sp.specific_test_id=l.specific_test_id_c) End As specific_test_name, case when l.Status=1 Then (select sp.specific_test_id from specific_tests sp where sp.specific_test_id=l.specific_test_id) else (select sp.specific_test_id from specific_tests sp where sp.specific_test_id=l.specific_test_id_c) End As specific_test_id,case when l.Status=1 then (select s.sub_test from sub_tests s where s.sub_test_id=l.sub_test_id) else (select s.sub_test from sub_tests s where s.sub_test_id=l.sub_test_id_c) end as sub_test,case when l.Status=1 then (select s.sub_test_id from sub_tests s where s.sub_test_id=l.sub_test_id) else (select s.sub_test_id from sub_tests s where s.sub_test_id=l.sub_test_id_c) end as sub_test_id, case when l.Status=1 then (select e.result_id from expected_results e where e.result_id=l.expected_result_id) else (select e.result_id from expected_results e where e.result_id=l.expected_result_id_c) end as expected_result_id,case when l.Status=1 then (select e.result from expected_results e where e.result_id=l.expected_result_id) else (select e.result from expected_results e where e.result_id=l.expected_result_id_c) end as expected_result, l.other_results,l.other_results_c,l.Comment,l.Comment_c,l.Sample_Type,l.Sample_Type_c,u.FullName,l.Result_Id,l.Status,ld.Start_Time,ld.End_Time FROM lab_tests_results l,specific_tests sp,test_names t,lab_tests_duration ld,users u,lab_tests_requests q,specific_tests s where sp.specific_test_id=s.specific_test_id and t.test_id=sp.test_id and l.Request_Id=ld.Request_Id and u.UID=l.Performed_By_c and q.Request_Id=l.Request_Id and q.test_name_id=t.test_id and q.Track_Id=? group by l.Result_Id";
            PreparedStatement stmt = con.prepareStatement(query);
//            PreparedStatement stmt = con.prepareStatement("SELECT q.Request_Id,t.test_name,sp.specific_test,s.sub_test,e.result,l.other_results,u.FullName,l.Result_Id,ld.Start_Time,ld.End_Time FROM lab_tests_results l INNER JOIN lab_tests_requests q ON q.Request_Id=l.Request_Id INNER JOIN expected_results e ON e.result_id=l.expected_result_id INNER JOIN sub_tests s ON s.sub_test_id=l.sub_test_id INNER JOIN specific_tests sp ON sp.specific_test_id=s.specific_test_id INNER JOIN test_names t ON t.test_id=sp.test_id INNER JOIN lab_tests_duration ld ON l.Request_Id=ld.Request_Id INNER JOIN users u ON u.UID=l.Performed_By WHERE q.Track_Id=? AND NOT EXISTS (Select Record_Date From lab_tasks lt Where lt.Track_Id=q.Track_Id and lt.Record_Date>q.Request_Time)");
            stmt.setString(1, task_id);

            ResultSet rs = stmt.executeQuery();
            List results = new ArrayList();

            while (rs.next()) {
                String general_tests = "";
                String general_results = "";
                String testresults = (rs.getString("expected_result").contains("write")) ? "" : rs.getString("expected_result");
                String comment_c = (rs.getString("Comment_c") == null || rs.getString("Comment_c") == "") ? "" : rs.getString("Comment_c");
                String comment_ = (rs.getString("Comment") == null || rs.getString("Comment") == "") ? "" : rs.getString("Comment");
                String comment = (rs.getInt("Status") == 1) ? comment_ : comment_c;
                comment = (comment.equals("") || comment == null) ? "" : "( " + comment + " )";
                String commentz = (rs.getInt("Status") == 1) ? comment_ : comment_c;

                String sample_type = (rs.getInt("Status") == 1) ? rs.getString("Sample_Type") : rs.getString("Sample_Type_c");
                String other_results = (rs.getInt("Status") == 1) ? rs.getString("other_results") : rs.getString("other_results_c");
                String specific = (rs.getString("specific_test_name").equalsIgnoreCase("None")) ? "" : " : " + rs.getString("specific_test_name");
                String subtest = (rs.getString("sub_test").equalsIgnoreCase("None")) ? "" : " / " + rs.getString("sub_test");

                general_tests = general_tests + "" + rs.getString("test_name") + "" + specific + "" + subtest;
                general_results = testresults + "  " + other_results;
                boolean rendering_edit = (rs.getInt("Status") == 1) ? true : false;
                boolean rendering_confirm = (rs.getInt("Status") == 1) ? false : true;
                test_result = new LabTestResults();
                test_result.setResult_id(Integer.valueOf(rs.getInt("Result_Id")));
                test_result.setRequest_id(Integer.valueOf(rs.getInt("Request_Id")));
                test_result.setTest_name(rs.getString("test_name"));
                test_result.setSpecific_test(rs.getString("specific_test_name"));
                test_result.setSub_test(rs.getString("sub_test"));
                test_result.setExpected_result(testresults);
                test_result.setOther_result(other_results);
                test_result.setTest_carriedout_by(rs.getString("FullName"));
                test_result.setStart_time(rs.getString("Start_Time").substring(0, 16));
                test_result.setEnd_time(rs.getString("End_Time").substring(0, 16));
                test_result.setSub_test_id(Integer.valueOf(rs.getInt("sub_test_id")));
                test_result.setGeneral_test(general_tests);
                test_result.setComment(commentz);
                test_result.setComment_blacket(comment);
                test_result.setRendering_confirm(rendering_confirm);
                test_result.setRendering_edit(rendering_edit);
                test_result.setTest_id(Integer.valueOf(rs.getInt("test_id")));
                test_result.setSpecific_test_id(Integer.valueOf(rs.getInt("specific_test_id")));
                test_result.setExpected_result_id(Integer.valueOf(rs.getInt("expected_result_id")));
                test_result.setExpected_result(rs.getString("expected_result"));
                test_result.setSample_type(sample_type);
                test_result.setGeneral_results(general_results);

                results.add(test_result);
                general_tests = "";
            }
            con.close();
            rs.close();
            return results;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Get_Performed_Tests", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            boolean Laboratory_Delete_Pending(Integer request_id) throws SQLException {

        try {
            Connection con;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("DELETE FROM lab_tests_requests WHERE Request_Id=?");
            ps.setInt(1, request_id);

            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Delete_Pending", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static
            boolean Laboratory_Delete_Result(Integer result_id) throws SQLException {

        try {
            Connection con;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("DELETE FROM lab_tests_results WHERE Result_Id=?");
            ps.setInt(1, result_id);

            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Delete_Result", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static
            boolean Laboratory_Update_Request_Status(Integer result_id) throws SQLException {

        try {
            Connection con;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("UPDATE lab_tests_requests l SET l.`Status`='Sample taken' WHERE l.Request_Id =(SELECT lr.Request_Id FROM lab_tests_results lr WHERE lr.Result_Id=?)");
            ps.setInt(1, result_id);

            ps.executeUpdate();
            con.close();
            ps.close();
            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Update_Request_Status", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static
            List<ReceptionTask> Laboratory_Get_Pending(String staff_id)
            throws SQLException {

        try {
            Connection con;
            ReceptionTask receptiontask;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();
            String query = "SELECT f.Track_Id,f.Patient_Id,f.Patient_Name,f.Age,f.Gender,f.VisitReason,f.Problem,f.Triage_Category,d.DepartmentName,c.Locked,c.Staff_Id,f.Room_No,c.Record_Date,c.Urgency,c.Lab_Id,c.Forward_To FROM lab_pending c INNER JOIN frontdesk_tasks f ON c.Track_Id=f.Track_Id INNER JOIN users u ON c.From_Staff=u.UID INNER JOIN department d ON u.DID=d.DID Where (Select Count(*) From lab_tests_requests ltr Where ltr.Track_Id=f.Track_Id and Countersign_status='Pending')>=(Case When (Select Level From Users Where UID=?)='SUPERVISOR' Then 0 Else 1 End) and (Select Count(*) From lab_tests_requests ltr Where ltr.Track_Id=f.Track_Id and Countersign_status='Pending')<=(Case When (Select Level From Users Where UID=?)='SUPERVISOR' Then 0 Else 1000000 End) order by c.Urgency,c.Record_Date ASC";
            // PreparedStatement stmt = con.prepareStatement("SELECT f.Track_Id,f.Patient_Id,f.Patient_Name,f.Age,f.Gender,f.VisitReason,f.Problem,f.Triage_Category,d.DepartmentName,c.Locked,c.Staff_Id,f.Room_No,c.Record_Date,c.Urgency,c.Lab_Id,c.Forward_To FROM lab_pending c INNER JOIN frontdesk_tasks f ON c.Track_Id=f.Track_Id INNER JOIN users u ON c.From_Staff=u.UID INNER JOIN department d ON u.DID=d.DID order by c.Urgency,c.Record_Date ASC");
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, staff_id);
            stmt.setString(2, staff_id);
            ResultSet rs = stmt.executeQuery();
            List pending_list = new ArrayList();
            Integer color_code;
            while (rs.next()) {
                color_code = Integer.valueOf(-1);

                if (!rs.getBoolean("Locked")) {
                    if ("Laboratory".equals(rs.getString("DepartmentName"))) {
                        color_code = Integer.valueOf(3);
                    }
                    else if ("Gray".equals(rs.getString("Triage_Category"))) {
                        color_code = Integer.valueOf(0);
                    }
                    else if ("Green".equals(rs.getString("Triage_Category"))) {
                        color_code = Integer.valueOf(1);
                    }
                    else if ("Red".equals(rs.getString("Triage_Category"))) {
                        color_code = Integer.valueOf(2);
                    }
                    else if ("Yellow".equals(rs.getString("Triage_Category"))) {
                        color_code = Integer.valueOf(3);
                    }
                    else if ("Blue".equals(rs.getString("Triage_Category"))) {
                        color_code = Integer.valueOf(4);
                    }
                    else if ("Orange".equals(rs.getString("Triage_Category"))) {
                        color_code = Integer.valueOf(5);
                    }

                    receptiontask = new ReceptionTask(rs.getString("Track_Id"), rs.getString("Patient_Id"), rs.getString("Patient_Name"), Integer.valueOf(rs.getInt("Age")), rs.getString("Gender"), rs.getString("VisitReason"), rs.getString("Problem"), color_code, rs.getString("Room_No"), rs.getString("Record_Date"), rs.getString("Urgency"), rs.getString("Lab_Id"), rs.getString("Forward_To"));
//                    receptiontask = new ReceptionTask(rs.getString("Track_Id"), rs.getString("Patient_Id"), rs.getString("Patient_Name"), Integer.valueOf(rs.getInt("Age")), rs.getString("Gender"), rs.getString("VisitReason"), rs.getString("Problem"), color_code, rs.getString("Room_No"), rs.getString("Record_Date"), rs.getString("Urgency"), rs.getString("Lab_Id"), rs.getString("DepartmentName"));

                    pending_list.add(receptiontask);
                }
                else if (staff_id.equals(rs.getString("Staff_Id"))) {
                    if ("Laboratory".equals(rs.getString("DepartmentName"))) {
                        color_code = Integer.valueOf(3);
                    }
                    else if ("Gray".equals(rs.getString("Triage_Category"))) {
                        color_code = Integer.valueOf(0);
                    }
                    else if ("Green".equals(rs.getString("Triage_Category"))) {
                        color_code = Integer.valueOf(1);
                    }
                    else if ("Red".equals(rs.getString("Triage_Category"))) {
                        color_code = Integer.valueOf(2);
                    }
                    else if ("Yellow".equals(rs.getString("Triage_Category"))) {
                        color_code = Integer.valueOf(3);
                    }
                    else if ("Blue".equals(rs.getString("Triage_Category"))) {
                        color_code = Integer.valueOf(4);
                    }
                    else if ("Orange".equals(rs.getString("Triage_Category"))) {
                        color_code = Integer.valueOf(5);
                    }

                    receptiontask = new ReceptionTask(rs.getString("Track_Id"), rs.getString("Patient_Id"), rs.getString("Patient_Name"), Integer.valueOf(rs.getInt("Age")), rs.getString("Gender"), rs.getString("VisitReason"), rs.getString("Problem"), color_code, rs.getString("Room_No"), rs.getString("Record_Date"), rs.getString("Urgency"), rs.getString("Lab_Id"), rs.getString("Forward_To"));
//                    receptiontask = new ReceptionTask(rs.getString("Track_Id"), rs.getString("Patient_Id"), rs.getString("Patient_Name"), Integer.valueOf(rs.getInt("Age")), rs.getString("Gender"), rs.getString("VisitReason"), rs.getString("Problem"), color_code, rs.getString("Room_No"), rs.getString("Record_Date"), rs.getString("Urgency"), rs.getString("Lab_Id"), rs.getString("DepartmentName"));

                    pending_list.add(receptiontask);
                }

            }
            con.close();
            rs.close();
            return pending_list;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Get_Pending", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            boolean Laboratory_Task_Lock(String task_id, boolean lock, String staff_id)
            throws SQLException {

        try {
            Connection con;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("UPDATE lab_pending SET Locked=?,Staff_Id=? WHERE Track_Id=?");
            stmt.setBoolean(1, lock);
            stmt.setString(2, staff_id);
            stmt.setString(3, task_id);

            stmt.executeUpdate();
            con.close();
            stmt.close();
            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Task_Lock", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            void Laboratory_Task_Update_Color_Code(String task_id, String color)
            throws SQLException {

        try {
            Connection con;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("UPDATE frontdesk_tasks SET Triage_Category=? WHERE Track_Id=?");
            stmt.setString(1, color);
            stmt.setString(2, task_id);

            stmt.executeUpdate();
            con.close();
            stmt.close();
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Task_Update_Color_Code", " Message: " + ex.getMessage(), date));
        }
    }

    public static
            String Laboratory_Task_Get_Urgency(String task_id)
            throws SQLException {

        try {
            Connection con;
           con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT Urgency FROM lab_pending WHERE Track_Id=?");
            stmt.setString(1, task_id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String temp = rs.getString("Urgency");
                con.close();
                rs.close();
                return temp;
            }
            else {
                con.close();
                rs.close();
                return null;
            }

        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Task_Get_Urgency", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            boolean Laboratory_Update_Requested_Test_Status(String request_status, Integer test_request_id) throws SQLException {

        try {
            Connection con;
            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("UPDATE lab_tests_requests SET Status=? WHERE Request_Id=?");
            ps.setString(1, request_status);
            ps.setInt(2, test_request_id);

            ps.executeUpdate();
            con.close();
            ps.close();
            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Update_Requested_Test_Status", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static
            boolean Laboratory_Time_Audit_Exists(String task_id) throws SQLException {

        try {
            Connection con;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM lab_time_audit WHERE Task_Id=?");
            stmt.setString(1, task_id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                con.close();
                return true;
            }
            con.close();
            rs.close();
            return false;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Time_Audit_Exists", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            boolean Laboratory_Time_Audit_Add(String track_id, String staff_from)
            throws SQLException, ClassNotFoundException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con=Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO lab_time_audit(Task_Id,Start_Time,Staff_From) VALUES(?,?,?)");

            ps.setString(1, track_id);
            ps.setString(2, dateFormat.format(date));
            ps.setString(3, staff_from);

            ps.executeUpdate();

            con.close();
            ps.close();
            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Time_Audit_Add", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static
            boolean Laboratory_Time_Audit_Update(String track_id, String forwarded_to) throws SQLException, ClassNotFoundException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con=Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("UPDATE lab_time_audit SET End_Time=?,Forwarded_To=? WHERE Task_Id=? AND Forwarded_To Is NULL");

            ps.setString(1, dateFormat.format(date));
            ps.setString(2, forwarded_to);
            ps.setString(3, track_id);

            ps.executeUpdate();

            con.close();
            ps.close();

            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Time_Audit_Update", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static
            boolean Laboratory_Add_Requeted_Test_Result(Integer request_id, String staff_id, String requested_test_result, String sample, String comment, Integer specific_test_id, Integer sub_test_id, Integer expected_result_id)
            throws SQLException {

        try {
            Connection con;
            con=Apache_Connectionpool.getInstance().getConnection();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();


            PreparedStatement ps = con.prepareStatement("INSERT INTO lab_tests_results(Request_Id,other_results_c,Performed_By_c,Sample_Type_c,Comment_c,specific_test_id_c,sub_test_id_c,expected_result_id_c,Result_Date_c,Status) VALUES(?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, request_id.intValue());
            ps.setString(2, requested_test_result);
            ps.setString(3, staff_id);
            ps.setString(4, sample);
            ps.setString(5, comment);
            ps.setInt(6, specific_test_id);
            ps.setInt(7, sub_test_id);
            ps.setInt(8, expected_result_id);
            ps.setString(9, dateFormat.format(date));
            ps.setInt(10, 0);

            ps.executeUpdate();
            con.close();
            ps.close();
            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Add_Requeted_Test_Result", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static
            boolean Laboratory_Add_Ward_Test_Result(Integer request_id, String staff_id, String requested_test_result, String sample, String comment, Integer sub_test_id, Integer expected_result_id)
            throws SQLException {

        try {
            Connection con;
            con=Apache_Connectionpool.getInstance().getConnection();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

// SQL still needs changing
            PreparedStatement ps = con.prepareStatement("INSERT INTO lab_tests_results(Request_Id,other_results,Performed_By,Sample_Type,Comment,specific_test_id,sub_test_id,expected_result_id,Result_Date) VALUES(?,?,?,?,?,(Select specific_test_id From sub_tests Where sub_test_id=?),?,?,?)");
            ps.setInt(1, request_id.intValue());
            ps.setString(2, requested_test_result);
            ps.setString(3, staff_id);
            ps.setString(4, sample);
            ps.setString(5, comment);
            ps.setInt(6, sub_test_id);
            ps.setInt(7, sub_test_id);
            ps.setInt(8, expected_result_id);
            ps.setString(9, dateFormat.format(date));

            ps.executeUpdate();
            con.close();
            ps.close();
            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Add_Requeted_Test_Result", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static
            boolean Laboratory_Check_Requeted_Test_Result(Integer request_id, Integer specific_test_id, Integer sub_test_id)
            throws SQLException {

        try {
            Connection con;
            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM lab_tests_results WHERE Request_Id=? AND specific_test_id_c=? AND sub_test_id_c=?");
            stmt.setInt(1, request_id);
            stmt.setInt(2, specific_test_id);
            stmt.setInt(3, sub_test_id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                con.close();
                rs.close();
                return true;
            }
            else {
                con.close();
                rs.close();
                return false;
            }

        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Check_Requeted_Test_Result", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            boolean Laboratory_Delete_Pending(String track_id)
            throws SQLException {

        try {
            Connection con;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("DELETE FROM lab_pending WHERE Track_Id=?");
            ps.setString(1, track_id);

            ps.executeUpdate();
            con.close();
            
            ps.close();
            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Delete_Pending", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static
            boolean Laboratory_Consultant_Pending(String track_id, String staff_id, boolean locked)
            throws SQLException {

        try {
            Connection con;

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con=Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO consultant_pending(Track_Id,From_Staff,Record_Date,Locked) VALUES(?,?,?,?)");
            ps.setString(1, track_id);
            ps.setString(2, staff_id);
            ps.setString(3, dateFormat.format(date));
            ps.setBoolean(4, locked);

            ps.executeUpdate();
            con.close();
            ps.close();
            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Consultant_Pending", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static
            List<String> Laboratory_Get_Ward_Trans_Id(String track_id) throws SQLException {

        try {
            Connection con;
            String ret_val;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT distinct(Trans_Id) FROM bwindihospital.lab_tests_requests WHERE Track_Id=?");
            stmt.setString(1, track_id);

            ResultSet rs = stmt.executeQuery();
            List trans_id_list = new ArrayList();
            ret_val = "";
            while (rs.next()) {
                ret_val = rs.getString("Trans_Id");
                trans_id_list.add(ret_val);
            }

            stmt.close();
            con.close();
            stmt.close();

            return trans_id_list;

        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Reception DAO", "Reception_Get_Forwarded_To", " Message: " + ex.getMessage(), date));
            return null;
        }

    }

    public static
            boolean Laboratory_Add_Completed_Task(String task_id, String lab_id)
            throws SQLException {

        try {
            Connection con;

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con=Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO lab_tasks(Track_Id,Record_Date,Lab_Id) VALUES(?,?,?)");

            ps.setString(1, task_id);
            ps.setString(2, dateFormat.format(date));
            ps.setString(3, lab_id);

            ps.executeUpdate();
            con.close();
            ps.close();

            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Add_Completed_Task", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static
            String Laboratory_Get_Test_Urgency(String task_id) throws SQLException {

        try {
            Connection con;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT l.Urgency FROM lab_pending l WHERE l.Track_Id=?");
            stmt.setString(1, task_id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String temp = rs.getString("Urgency");
                con.close();
                rs.close();
                return temp;
            }
            else {
                con.close();
                rs.close();
                return null;
            }

        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Reception DAO", "Reception_Get_Forwarded_To", " Message: " + ex.getMessage(), date));
            return null;
        }

    }

    public static
            List<TestReport> Laboratory_Get_Completed_Tasks(String staff_id, Date specified_date, Date end_date) throws SQLException {

        try {
            Connection con;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = new Date();

            TestReport testreport;

            con=Apache_Connectionpool.getInstance().getConnection();

// The following query hasn't been changed since the tables for recording lab tests & results changed in early 2014, and so doesn't work!!
            PreparedStatement stmt = con.prepareStatement("SELECT r.Request_Id,l.Track_Id,l.Lab_Id,lt.Test_Desc,lt.Test_Normal_Values,t.Results,t.Sample_Type,t.Comment,t.Performed_By,l.Record_Date FROM lab_tasks l INNER JOIN lab_tests_requests r ON l.Track_Id=r.Track_Id INNER JOIN lab_tests_results t ON t.Request_Id=r.Request_Id INNER JOIN lab_tests lt ON r.Test_Id=lt.Test_Id WHERE l.Record_Date between ? AND ? AND t.Performed_By=? AND r.Status='Completed'");
            stmt.setString(1, dateFormat.format(specified_date));
            stmt.setString(2, dateFormat.format(end_date));
            stmt.setString(3, staff_id);

            ResultSet rs = stmt.executeQuery();
            List completed_list = new ArrayList();

            while (rs.next()) {

                testreport = new TestReport();
                testreport.setRequest_id(rs.getInt("Request_Id"));
                testreport.setLab_Id(rs.getString("Lab_Id"));
                testreport.setTest_desc(rs.getString("Test_Desc"));
                testreport.setNormal_values(rs.getString("Test_Normal_Values"));
                testreport.setResults(rs.getString("Results"));
                testreport.setSample_type(rs.getString("Sample_Type"));
                testreport.setComment(rs.getString("Comment"));

                completed_list.add(testreport);
            }
            con.close();
            rs.close();
            return completed_list;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Get_Completed_Tasks", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            boolean Laboratory_Add_Test_Category(String category) throws SQLException {

        try {
            Connection con;

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con=Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO test_categories(category,record_date) VALUES(?,?)");

            ps.setString(1, category);
            ps.setString(2, dateFormat.format(date));

            ps.executeUpdate();
            con.close();
            ps.close();

            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Add_Test_Category", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            boolean Laboratory_Add_Test_Name(Integer category_id, String test_name) throws SQLException {

        try {
            Connection con;

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con=Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO test_names(category_id,test_name,record_date) VALUES(?,?,?)");

            ps.setInt(1, category_id);
            ps.setString(2, test_name);
            ps.setString(3, dateFormat.format(date));

            ps.executeUpdate();
            con.close();
            ps.close();

            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Add_Test_Name", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            boolean Laboratory_Add_Specific_Test(Integer test_id, String specific_test) throws SQLException {

        try {
            Connection con;

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con=Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO specific_tests(test_id,specific_test,record_date) VALUES(?,?,?)");

            ps.setInt(1, test_id);
            ps.setString(2, specific_test);
            ps.setString(3, dateFormat.format(date));

            ps.executeUpdate();
            con.close();
            ps.close();

            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Add_Specific_Test", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            boolean Laboratory_Add_Sub_Test(Integer specific_test_id, String sub_test) throws SQLException {

        try {
            Connection con;

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con=Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO sub_tests(specific_test_id,sub_test,record_date) VALUES(?,?,?)");

            ps.setInt(1, specific_test_id);
            ps.setString(2, sub_test);
            ps.setString(3, dateFormat.format(date));

            ps.executeUpdate();
            con.close();
            ps.close();
            

            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Add_Sub_Test", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            boolean Laboratory_Add_Expected_Result(Integer sub_test_id, String expected_result) throws SQLException {

        try {
            Connection con;

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con=Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO expected_results(sub_test_id,result,record_date) VALUES(?,?,?)");

            ps.setInt(1, sub_test_id);
            ps.setString(2, expected_result);
            ps.setString(3, dateFormat.format(date));

            ps.executeUpdate();
            con.close();
            ps.close();

            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Add_Expected_Result", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            List<TestCategory> Laboratory_Get_Test_Category() throws SQLException {

        try {
            Connection con;
            TestCategory test_category;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

//            PreparedStatement stmt = con.prepareStatement("SELECT * FROM test_categories order by category ASC");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM test_categories WHERE Status='TRUE' order by category ASC");

            ResultSet rs = stmt.executeQuery();
            List test_categories = new ArrayList();

            while (rs.next()) {
                test_category = new TestCategory();
                test_category.setCategory_id(Integer.valueOf(rs.getInt("category_id")));
                test_category.setCategory_desc(rs.getString("category"));
                test_categories.add(test_category);
            }
            con.close();
            rs.close();
            return test_categories;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Get_Test_Category", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            List<TestName> Laboratory_Get_Test_Names(Integer test_category_id) throws SQLException {

        try {
            Connection con;
            TestName test_name;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

//            PreparedStatement stmt = con.prepareStatement("SELECT t.test_id,tc.category,tc.category_id,t.test_name FROM test_names t INNER JOIN test_categories tc ON tc.category_id=t.category_id WHERE t.category_id=? order by t.test_name ASC");
            PreparedStatement stmt = con.prepareStatement("SELECT t.test_id,tc.category,tc.category_id,t.test_name FROM test_names t INNER JOIN test_categories tc ON tc.category_id=t.category_id WHERE t.category_id=? AND t.Status='TRUE' order by t.test_name ASC");
            stmt.setInt(1, test_category_id);

            ResultSet rs = stmt.executeQuery();
            List test_names = new ArrayList();

            while (rs.next()) {
                test_name = new TestName();
                test_name.setTest_name_id(rs.getInt("test_id"));
                test_name.setTest_category_id(rs.getInt("category_id"));
                test_name.setTest_category(rs.getString("category"));
                test_name.setTest_name(rs.getString("test_name"));
                test_names.add(test_name);
            }
            con.close();
            rs.close();
            return test_names;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Get_Test_Names", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            List<SpecificTest> Laboratory_Get_Specific_Tests(Integer test_name_id) throws SQLException {

        try {
            Connection con;
            SpecificTest specific_test;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

//            PreparedStatement stmt = con.prepareStatement("SELECT s.specific_test_id,tc.category,t.test_name,s.specific_test FROM specific_tests s INNER JOIN test_names t ON t.test_id=s.test_id INNER JOIN test_categories tc ON tc.category_id=t.category_id WHERE t.test_id=? order by s.specific_test ASC");
            PreparedStatement stmt = con.prepareStatement("SELECT s.specific_test_id,tc.category,t.test_name,s.specific_test FROM specific_tests s INNER JOIN test_names t ON t.test_id=s.test_id INNER JOIN test_categories tc ON tc.category_id=t.category_id WHERE t.test_id=? AND s.Status='TRUE' order by s.specific_test ASC");

//            
            stmt.setInt(1, test_name_id);

            ResultSet rs = stmt.executeQuery();
            List specific_tests = new ArrayList();

            while (rs.next()) {
                specific_test = new SpecificTest();
                specific_test.setSpecific_test_id(Integer.valueOf(rs.getInt("specific_test_id")));
//                specific_test.setSpecific_test_id(rs.getInt("specific_test_id"));                
                specific_test.setSpecific_test(rs.getString("specific_test"));
                specific_test.setTest_category(rs.getString("category"));
                specific_test.setTest_name(rs.getString("test_name"));
                specific_tests.add(specific_test);
            }
            con.close();
            rs.close();
            return specific_tests;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Get_Specific_Tests", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            List<SubTest> Laboratory_Get_Sub_Tests(Integer specific_test_id) throws SQLException {

        try {
            Connection con;
            SubTest sub_test;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

//            PreparedStatement stmt = con.prepareStatement("SELECT s.sub_test_id,tc.category,t.test_name,sp.specific_test,s.sub_test FROM sub_tests s INNER JOIN specific_tests sp ON sp.specific_test_id=s.specific_test_id INNER JOIN test_names t ON t.test_id=sp.test_id INNER JOIN test_categories tc ON tc.category_id=t.category_id WHERE s.specific_test_id=? order by s.sub_test ASC");

            PreparedStatement stmt = con.prepareStatement(" SELECT s.sub_test_id,tc.category,t.test_name,sp.specific_test,s.sub_test FROM sub_tests s INNER JOIN specific_tests sp ON sp.specific_test_id=s.specific_test_id INNER JOIN test_names t ON t.test_id=sp.test_id INNER JOIN test_categories tc ON tc.category_id=t.category_id WHERE s.specific_test_id=? AND s.Status='TRUE' order by s.sub_test ASC");
//           
            stmt.setInt(1, specific_test_id);

            ResultSet rs = stmt.executeQuery();
            List sub_tests = new ArrayList();

            while (rs.next()) {
                sub_test = new SubTest();
                sub_test.setSub_test_id(Integer.valueOf(rs.getInt("sub_test_id")));
                sub_test.setTest_category(rs.getString("category"));
                sub_test.setTest_name(rs.getString("test_name"));
                sub_test.setSpecific_test(rs.getString("specific_test"));
                sub_test.setSub_test(rs.getString("sub_test"));
                sub_tests.add(sub_test);
            }
            con.close();
            rs.close();
            return sub_tests;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Get_Sub_Tests", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            List<ExpectedResult> Laboratory_Get_Expected_Results() throws SQLException {

        try {
            Connection con;
            ExpectedResult expected_result;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT e.result_id,tc.category,t.test_name,st.specific_test,s.sub_test,e.result FROM expected_results e INNER JOIN sub_tests s ON e.sub_test_id=s.sub_test_id INNER JOIN specific_tests st ON st.specific_test_id=s.specific_test_id INNER JOIN test_names t ON t.test_id=st.test_id INNER JOIN test_categories tc ON tc.category_id=t.category_id order by tc.category ASC");

            ResultSet rs = stmt.executeQuery();
            List expected_results = new ArrayList();

            while (rs.next()) {
                expected_result = new ExpectedResult();
                expected_result.setExpected_result_id(Integer.valueOf(rs.getInt("result_id")));
                expected_result.setTest_category(rs.getString("category"));
                expected_result.setTest_name(rs.getString("test_name"));
                expected_result.setSpecific_test(rs.getString("specific_test"));
                expected_result.setSub_test(rs.getString("sub_test"));
                expected_result.setExpected_result(rs.getString("result"));
                expected_results.add(expected_result);
            }
            con.close();
            rs.close();
            return expected_results;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Get_Expected_Results", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            List<ExpectedResult>[] Laboratory_Get_Expected_Results(Integer sub_test_id) throws SQLException {

        try {
            Connection con;
            ExpectedResult expected_result;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

//            PreparedStatement stmt = con.prepareStatement("SELECT e.result_id,tc.category,t.test_name,st.specific_test,s.sub_test,e.result FROM expected_results e INNER JOIN sub_tests s ON e.sub_test_id=s.sub_test_id INNER JOIN specific_tests st ON st.specific_test_id=s.specific_test_id INNER JOIN test_names t ON t.test_id=st.test_id INNER JOIN test_categories tc ON tc.category_id=t.category_id WHERE e.sub_test_id=? order by tc.category ASC");
            PreparedStatement stmt = con.prepareStatement("SELECT e.result_id,tc.category,t.test_name,st.specific_test,s.sub_test,e.result FROM expected_results e INNER JOIN sub_tests s ON e.sub_test_id=s.sub_test_id INNER JOIN specific_tests st ON st.specific_test_id=s.specific_test_id INNER JOIN test_names t ON t.test_id=st.test_id INNER JOIN test_categories tc ON tc.category_id=t.category_id WHERE e.sub_test_id=? AND e.Status='TRUE' order by tc.category ASC");
          
            stmt.setInt(1, sub_test_id);

            ResultSet rs = stmt.executeQuery();
            List expected_results = new ArrayList();
            List expected_results_range = new ArrayList();

            while (rs.next()) {
                expected_result = new ExpectedResult();
                expected_result.setExpected_result_id(Integer.valueOf(rs.getInt("result_id")));
                expected_result.setTest_category(rs.getString("category"));
                expected_result.setTest_name(rs.getString("test_name"));
                expected_result.setSpecific_test(rs.getString("specific_test"));
                expected_result.setSub_test(rs.getString("sub_test"));
                expected_result.setExpected_result(rs.getString("result"));
                expected_results.add(expected_result);
                expected_results_range.add(rs.getString("result"));
            }
            con.close();
            
            rs.close();
            return new List[]{expected_results, expected_results_range};
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Get_Expected_Results", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            Integer Laboratory_Get_Pending_Count()
            throws SQLException {

        try {
            Connection con;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);

            PreparedStatement stmt = con.prepareStatement("SELECT Count(*) as Count FROM lab_pending WHERE year(Record_Date)=?");
            stmt.setInt(1, year);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Integer retval = rs.getInt("Count");
                con.close();
                rs.close();
                return retval;
            }
            else {
                con.close();
                rs.close();
                return 0;
            }

        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Get_Pending_Count", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            Integer Laboratory_Get_Completed_Count()
            throws SQLException {

        try {
            Connection con;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);

            PreparedStatement stmt = con.prepareStatement("SELECT Count(*) as Count FROM lab_tasks WHERE year(Record_Date)=?");
            stmt.setInt(1, year);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Integer retval = rs.getInt("Count");
                con.close();
                rs.close();
                return retval;
            }
            else {
                con.close();
                rs.close();
                return 0;
            }

        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Get_Completed_Count", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            String Laboratory_Get_Lab_Id(String task_id)
            throws SQLException {

        try {
            Connection con;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT Lab_Id FROM lab_pending l WHERE l.Track_Id=?");
            stmt.setString(1, task_id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String retval = rs.getString("Lab_Id");
                con.close();
                rs.close();
                return retval;
            }
            else {
                con.close();
                rs.close();
                return null;
            }

        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Get_Lab_Id", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            String Laboratory_Get_Test_Name(Integer test_id)
            throws SQLException {

        try {
            Connection con;
            String ret_val = null;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT test_name FROM bwindihospital.test_names WHERE Test_Id=?");
            stmt.setInt(1, test_id);

            System.out.println("test_id :: " + test_id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ret_val = rs.getString("test_name");
                System.out.println("ret_val :: " + ret_val);
            }

            stmt.close();
            con.close();
                rs.close();
            return ret_val;

        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Get_Test_Name", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            String Laboratory_Get_Expected_Result(Integer expected_result_id)
            throws SQLException {

        try {
            Connection con;
            String ret_val = null;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT result FROM bwindihospital.expected_results WHERE result_id=?");
            stmt.setInt(1, expected_result_id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ret_val = rs.getString("result");
            }

            stmt.close();
            con.close();
            rs.close();
            return ret_val;

        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Get_Expected_Result", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            String Laboratory_Get_Specific_Test(Integer specific_test_id)
            throws SQLException {

        try {
            Connection con;
            String ret_val = null;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT specific_test FROM bwindihospital.specific_tests WHERE specific_test_id=?");
            stmt.setInt(1, specific_test_id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ret_val = rs.getString("specific_test");
            }

            stmt.close();
            con.close();
            rs.close();
            return ret_val;

        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Get_Specific_Test", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            String Laboratory_Get_Sub_Test(Integer sub_test_id)
            throws SQLException {

        try {
            Connection con;
            String ret_val = null;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT sub_test FROM bwindihospital.sub_tests WHERE sub_test_id=?");
            stmt.setInt(1, sub_test_id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ret_val = rs.getString("sub_test");
            }

            stmt.close();
            con.close();
            rs.close();
            return ret_val;

        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Get_Sub_Test", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            boolean Laboratory_Add_Test_Duration_Start(String task_id, Integer request_id)
            throws SQLException {

        try {
            Connection con;

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con=Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO lab_tests_duration(Task_Id,Request_Id,Start_Time) VALUES(?,?,?)");
            ps.setString(1, task_id);
            ps.setInt(2, request_id);
            ps.setString(3, dateFormat.format(date));

            ps.executeUpdate();
            con.close();
            ps.close();
            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Add_Test_Duration_Start", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static
            boolean Laboratory_Add_Test_Duration_End(String task_id, Integer request_id)
            throws SQLException {

        try {
            Connection con;

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            con=Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("UPDATE lab_tests_duration SET End_Time=? WHERE Task_Id=? AND Request_Id=?");
            ps.setString(1, dateFormat.format(date));
            ps.setString(2, task_id);
            ps.setInt(3, request_id);

            ps.executeUpdate();
            con.close();
            ps.close();
            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Add_Test_Duration_End", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static
            boolean Laboratory_Add_Test_Duration_Delete(String task_id, Integer request_id)
            throws SQLException {

        try {
            Connection con;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement ps = con.prepareStatement("DELETE FROM lab_tests_duration WHERE Task_Id=? AND Request_Id=?");

            ps.setString(1, task_id);
            ps.setInt(2, request_id);

            ps.executeUpdate();
            con.close();
            ps.close();
            return true;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_Add_Test_Duration_End", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static
            String Expected_result_range(Integer sub_test_id, String agetype)
            throws SQLException {

        try {
            Connection con;
            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT L,U,Units FROM tests_normal_ranges WHERE sub_test_id=? and Age_Type=?");
            stmt.setInt(1, sub_test_id);
            stmt.setString(2, agetype);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String Bound = "[Normal range: " + rs.getString("L") + "-" + rs.getString("U") + " " + rs.getString("Units") + "]";
                con.close();
                rs.close();
                return Bound;
            }
            else {
                con.close();
                rs.close();
                return null;
            }

        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Expected_result_range", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            String get_result_units(Integer sub_test_id, String agetype, float results)
            throws SQLException {

        try {
            Connection con;
            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("SELECT L,U,Units FROM tests_normal_ranges WHERE sub_test_id=? and Age_Type=?");
            stmt.setInt(1, sub_test_id);
            stmt.setString(2, agetype);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String result_in_range = (results < Float.valueOf(rs.getString("L")) ? "Low" : (results > Float.valueOf(rs.getString("U"))) ? "High" : "Normal");

                String Units = " " + rs.getString("Units") + "  [" + result_in_range + "] -->";
              con.close();
                rs.close();
                return Units;
            }
            else {
               con.close();
                rs.close();
                return null;
            }

        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Expected_result_range", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            boolean Laboratory_testresult(Integer sub_test_id) throws SQLException {
        try {
            Connection con;

            con=Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement stmt = con.prepareStatement("select * from tests_normal_ranges where sub_test_id=?");
            stmt.setInt(1, sub_test_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
               con.close();
                rs.close();
                return true;
            }
           con.close();
                rs.close();
            return false;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Laboratory DAO", "Laboratory_testresult", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    /*
     * 
     * 
     *  ENDS HERE ##MUGABI ROBERT - 2014 - 03 - 25
     * 
     * 
     * 
     * 
     */
}
