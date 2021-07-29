/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import managedModal.DynamicFunctionModel;

/**
 *
 * @author PATRICK
 */
@ManagedBean(name = "dynamicFunctionsDAO")
@SessionScoped
public class DynamicFunctionsDAO {
static Date date = new Date();
    //populating data in the dropdowns on the interface which do not base on any other dropdowns
    public static List<DynamicFunctionModel> dropDowns(String tableName, String id, String name) throws SQLException {

        try {
            Connection con;
            DynamicFunctionModel list;

            con = Apache_Connectionpool.getInstance().getConnection();
           

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM " + tableName + " order by " + id + " ASC");

            ResultSet rs = stmt.executeQuery();
            List dropdownlist = new ArrayList();

            while (rs.next()) {
                list = new DynamicFunctionModel();
                list.setDropdownId(rs.getString(id));
                list.setDropdownValue(rs.getString(name));
                dropdownlist.add(list);
            }
            con.close();
            rs.close();
            return dropdownlist;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new managedModal.Error("DynamicFunctions DAO", "dropDowns retreiving parish", " Message: " + ex.getMessage(), date));

            return null;
        }
    }

    public static List<DynamicFunctionModel> dropDownsDruglist(String tableName, String id, String name) throws SQLException {

        try {
            Connection con;
            DynamicFunctionModel list;

            con = Apache_Connectionpool.getInstance().getConnection();

           

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM " + tableName + " WHERE status='TRUE' order by " + id + " ASC");

            ResultSet rs = stmt.executeQuery();
            List dropdownlist = new ArrayList();

            while (rs.next()) {
                list = new DynamicFunctionModel();
                list.setDropdownId(rs.getString(id));
                list.setDropdownValue(rs.getString(name));
                dropdownlist.add(list);
            }
            con.close();
            rs.close();
            return dropdownlist;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new managedModal.Error("DynamicFunctions DAO", "dropDowns retreiving parish", " Message: " + ex.getMessage(), date));

            return null;
        }
    }

    //populating data in the dropdown list which bases on the id of the  previous dropdown
    public static List<DynamicFunctionModel> dropDowns(String tableName, String id, String fieldName, String Baseidname, String BaseidValue) throws SQLException {

        try {
            Connection con;
            DynamicFunctionModel list;

            con = Apache_Connectionpool.getInstance().getConnection();
           

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM " + tableName + " where " + Baseidname + "=? order by " + id + " ASC");
            stmt.setString(1, BaseidValue);
            ResultSet rs = stmt.executeQuery();
            List dropdownlist = new ArrayList();

            while (rs.next()) {
                list = new DynamicFunctionModel();
                list.setDropdownId(rs.getString(id));
                list.setDropdownValue(rs.getString(fieldName));
                dropdownlist.add(list);
            }
            con.close();
            rs.close();
            return dropdownlist;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new managedModal.Error("DynamicFunctions DAO", "dropDowns retreiving " + tableName, " Message: " + ex.getMessage(), date));

            return null;
        }
    }

    //checking if data already exisit in the table before submiting in the table
    public static boolean Check_if_exsists(String value, String table_name, String fieldname)
            throws SQLException {

        try {
            Connection con;
            con = Apache_Connectionpool.getInstance().getConnection();
           

            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) as Count FROM " + table_name + " WHERE " + fieldname + "=?");
            ps.setString(1, value);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Integer temp = rs.getInt("Count");
                con.close();
                rs.close();
                if (temp > 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                con.close();

                rs.close();
                return false;
            }

        } catch (Exception ex) {
            ErrorDAO.Error_Add(new managedModal.Error("Check_if_exsists", "Check if " + fieldname + " exisits in " + table_name + "", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

//inserting data in any table depending on columns specified
    public static boolean Insert(String table, String[][] fields) throws SQLException {
        if (fields.length > 0) {
            String key, value, data;
            data = "";
            value = "";
            key = "";
            for (int i = 0; i < fields.length; i++) {
                for (int j = 0; j < fields[i].length; j++) {

                    if (i < fields.length - 1) {

                        key += fields[i][j];
                        if (j < fields[i].length - 1) {
                            key += ", ";
                        }
                    } else {
                        data += fields[i][j];
                        value += "?";
                        if (j < fields[i].length - 1) {
                            value += ", ";
                        }
                    }
                }
            }

            try {
                Connection con;
                con = Apache_Connectionpool.getInstance().getConnection();
               

                PreparedStatement ps = con.prepareStatement("INSERT INTO " + table + "(" + key + ") VALUES(" + value + ")");

                for (int i = 0; i < fields.length; i++) {
                    for (int j = 0; j < fields[i].length; j++) {

                        if (i < fields.length - 1) {

                        } else {
                            data += fields[i][j];
                            ps.setString(j + 1, fields[i][j]);

                        }
                    }
                }
                ps.executeUpdate();
                con.close();
                ps.close();
                return true;

            } catch (Exception ex) {
                ErrorDAO.Error_Add(new managedModal.Error("Insert method", "inserting data into table " + table, " Message: " + ex.getMessage(), date));
                return false;
            }

        }
        return false;
    }

    //editing data in the table basing on fields specified and the ID to base on while editing
    public static boolean Edit(String table, String[][] fields, String field_id, String field_idValue) throws SQLException {
        if (fields.length > 0) {
            String key, value, data;
            data = "";
            value = "";
            key = "";
            for (int i = 0; i < fields.length; i++) {
                for (int j = 0; j < fields[i].length; j++) {

                    if (i < fields.length - 1) {

                        key += fields[i][j] + "=?";
                        if (j < fields[i].length - 1) {
                            key += ", ";
                        }
                    }
                }
            }

            try {
                Connection con;
                con = Apache_Connectionpool.getInstance().getConnection();
               

                PreparedStatement ps = con.prepareStatement("UPDATE " + table + " SET " + key + " WHERE " + field_id + "=?");
                int i = 0;
                int j = 0;
                for (i = 0; i < fields.length; i++) {
                    for (j = 0; j < fields[i].length; j++) {

                        if (i < fields.length - 1) {

                        } else {
                            data += fields[i][j];
                            ps.setString(j + 1, fields[i][j]);

                        }
                    }
                }

                ps.setString(j + 1, field_idValue);

                ps.executeUpdate();
                con.close();
                ps.close();
                return true;

            } catch (Exception ex) {
                ErrorDAO.Error_Add(new managedModal.Error("edit method", "editing data in table " + table, " Message: " + ex.getMessage(), date));
                return false;
            }

        }
        return false;
    }

    //generates id for any table that uses string ids
    public static String autoID(String table, String field, Integer substring) throws SQLException, ClassNotFoundException {
        String id = "";
        String string_id = "";
        try {
            Connection con;

            con = Apache_Connectionpool.getInstance().getConnection();

            String query = "SELECT " + field + " FROM " + table + " WHERE SUBSTRING(" + field + "," + substring + ",6)<? order by SUBSTRING(" + field + "," + substring + ",6) DESC limit 1";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, 999);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int sub_id, new_sub_id;
                string_id = rs.getString(field).substring(substring - 1);
                id = rs.getString(field).substring(0, substring - 1);
                sub_id = Integer.parseInt(string_id);
                new_sub_id = sub_id + 1;

                id += (new_sub_id <= 9) ? "00" + new_sub_id : ((new_sub_id >= 10 && new_sub_id <= 99) ? "0" + new_sub_id : new_sub_id);

            }
            con.close();
            rs.close();
            return id;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }

    }

    //delete data from any table basing on  specified ID of the very table
    
    public static  boolean delete_dataTablestatic(String table, String Field_Id, String Id) throws SQLException {
        try {
            Connection con;

            con = Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("delete from " + table + " where " + Field_Id + "=?");
            ps.setString(1, Id);

            ps.executeUpdate();

            con.close();
            ps.close();

            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new managedModal.Error("delete_dataTable", "deleting from " + table, " Message: " + ex.getMessage(), date));
            return false;
        }
    }
    public  boolean delete_dataTable(String table, String Field_Id, String Id) throws SQLException {
        try {
            Connection con;

            con = Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("delete from " + table + " where " + Field_Id + "=?");
            ps.setString(1, Id);

            ps.executeUpdate();

            con.close();
            ps.close();

            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new managedModal.Error("delete_dataTable", "deleting from " + table, " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    //inserting data in any table depending on columns specified
    public static boolean Check_if_exsists(String table, String[][] fields) throws SQLException {
        if (fields.length > 0) {
            String key, value, data;
            data = "";
            value = "";
            key = "";
            for (int i = 0; i < fields.length; i++) {
                for (int j = 0; j < fields[i].length; j++) {

                    if (i < fields.length - 1) {

                        key += fields[i][j] + "=?";
                        if (j < fields[i].length - 1) {
                            key += " AND ";
                        }
                    } else {
                        data += fields[i][j];
                        value += "?";
                        if (j < fields[i].length - 1) {
                            value += ", ";
                        }
                    }
                }
            }

            try {
                Connection con;
                con = Apache_Connectionpool.getInstance().getConnection();
               

//                PreparedStatement ps = con.prepareStatement("INSERT INTO " + table + "(" + key + ") VALUES(" + value + ")");
                PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) as Count FROM " + table + " WHERE " + key);
                for (int i = 0; i < fields.length; i++) {
                    for (int j = 0; j < fields[i].length; j++) {

                        if (i < fields.length - 1) {

                        } else {
                            data += fields[i][j];
                            ps.setString(j + 1, fields[i][j]);

                        }
                    }
                }

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Integer temp = rs.getInt("Count");
                    con.close();
                    rs.close();
                    if (temp > 0) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    con.close();
                    rs.close();
                    return false;
                }

            } catch (Exception ex) {
                ErrorDAO.Error_Add(new managedModal.Error("Check if data for multiple fields exisit method", "inserting data into table " + table, " Message: " + ex.getMessage(), date));
                return false;
            }

        }
        return false;
    }

    public static boolean Check_if_exsists(String[] table, String[][] fields, String[] concatenatedFields) throws SQLException {
        if (fields.length > 0) {

            String key, value, data, table_names, concatefields;
            data = "";
            value = "";
            key = "";
            table_names = "";
            concatefields = "";

            if (table.length > 0) {
                for (int n = 0; n < table.length; n++) {
                    table_names += table[n];
                    if (n < table.length - 1) {
                        table_names += ", ";
                    }

                }
            }

            if (concatenatedFields.length > 0) {
                for (int k = 0; k < concatenatedFields.length; k++) {
                    concatefields += concatenatedFields[k];
                    concatefields += " AND ";

                }
            }

            for (int i = 0; i < fields.length; i++) {
                for (int j = 0; j < fields[i].length; j++) {

                    if (i < fields.length - 1) {

                        key += fields[i][j] + "=?";
                        if (j < fields[i].length - 1) {
                            key += " AND ";
                        }
                    } else {
                        data += fields[i][j];
                        value += "?";
                        if (j < fields[i].length - 1) {
                            value += ", ";
                        }
                    }
                }
            }

            try {
                Connection con;
                con = Apache_Connectionpool.getInstance().getConnection();
               

//                PreparedStatement ps = con.prepareStatement("INSERT INTO " + table + "(" + key + ") VALUES(" + value + ")");
                PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) as Count FROM " + table_names + " WHERE " + concatefields + " " + key);
                for (int i = 0; i < fields.length; i++) {
                    for (int j = 0; j < fields[i].length; j++) {

                        if (i < fields.length - 1) {

                        } else {
                            data += fields[i][j];
                            ps.setString(j + 1, fields[i][j]);

                        }
                    }
                }

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Integer temp = rs.getInt("Count");
                    con.close();
                    rs.close();
                    if (temp > 0) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    con.close();
                    rs.close();
                    return false;
                }

            } catch (Exception ex) {
                ErrorDAO.Error_Add(new managedModal.Error("Check if data for  multiple fiields and table exisit method", "inserting data into table " + table, " Message: " + ex.getMessage(), date));
                return false;
            }

        }
        return false;
    }

    public static String dynamicgetsingle_variable(String data, String tablename, String column_name, String column_id)
            throws SQLException {
        try {
            Connection con;
            String ret_val = null;

            con = Apache_Connectionpool.getInstance().getConnection();
           

            PreparedStatement stmt = con.prepareStatement("SELECT " + data + " FROM " + tablename + " WHERE " + column_name + "=?");
            stmt.setString(1, column_id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ret_val = rs.getString(data);
            }

            stmt.close();
            con.close();
            rs.close();
            return ret_val;
        } catch (Exception e) {
            ErrorDAO.Error_Add(new managedModal.Error("Dynamic DAO", "dynamicgetsingle_variable", " Message: " + e.getMessage(), date));
            return null;
        }

    }

    public static String dynamicgetsingle_variable_open(String data, String tablename, String column_name)
            throws SQLException {
        try {
            Connection con;
            String ret_val = null;

            con = Apache_Connectionpool.getInstance().getConnection();
           

            PreparedStatement stmt = con.prepareStatement("SELECT " + data + " FROM " + tablename + " WHERE " + data + " IS NOT NULL ORDER BY " + column_name + " DESC");

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ret_val = rs.getString(data);
            }

            stmt.close();
            con.close();
            rs.close();
            return ret_val;
        } catch (Exception e) {
            ErrorDAO.Error_Add(new managedModal.Error("Dynamic DAO", "dynamicgetsingle_variable", " Message: " + e.getMessage(), date));
            return null;
        }

    }

    public static Integer dynamicgetsingle_date_diff(String data, String tablename, String column_name)
            throws SQLException {
        try {
            Connection con;
            Integer ret_val = 0;

            con = Apache_Connectionpool.getInstance().getConnection();
           

            PreparedStatement stmt = con.prepareStatement("SELECT DATEDIFF(NOW(), " + data + ") AS DateDiff FROM " + tablename + " ORDER BY " + column_name + " DESC");

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ret_val = rs.getInt("DateDiff");
            }

            stmt.close();
            con.close();
            rs.close();
            
            return ret_val;
        } catch (Exception e) {
            ErrorDAO.Error_Add(new managedModal.Error("Dynamic DAO", "dynamicgetsingle_variable", " Message: " + e.getMessage(), date));
            return null;
        }

    }

    public static boolean Check_if_notdataexsists_inDB(String table_name)
            throws SQLException {

        try {
            Connection con;
            con = Apache_Connectionpool.getInstance().getConnection();
           

            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) as Count FROM " + table_name + " ");

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Integer temp = rs.getInt("Count");
                con.close();
                rs.close();
                if (temp > 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                con.close();
                rs.close();
                return false;
            }

        } catch (Exception ex) {
            ErrorDAO.Error_Add(new managedModal.Error("Check_if_exsists", "Check if data exisits in " + table_name + "", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static String dynamicgetsingle_valiable(String variable_name, String[] table, String[][] fields, String[] concatenatedFields) throws SQLException {
        String ret_val = null;
        if (fields.length > 0) {

            String key, table_names, concatefields;

            key = "";
            table_names = "";
            concatefields = "";

            if (table.length > 0) {
                for (int n = 0; n < table.length; n++) {
                    table_names += table[n];
                    if (n < table.length - 1) {
                        table_names += ", ";
                    }

                }
            }

            if (concatenatedFields.length > 0) {
                for (int k = 0; k < concatenatedFields.length; k++) {
                    concatefields += concatenatedFields[k];
                    concatefields += " AND ";

                }
            }

            for (int i = 0; i < fields.length; i++) {
                for (int j = 0; j < fields[i].length; j++) {

                    if (i < fields.length - 1) {

                        key += fields[i][j] + "=?";
                        if (j < fields[i].length - 1) {
                            key += " AND ";
                        }
                    }
                }
            }

            try {
                Connection con;
                con = Apache_Connectionpool.getInstance().getConnection();
               

                PreparedStatement ps = con.prepareStatement("SELECT " + variable_name + "  FROM " + table_names + " WHERE " + concatefields + " " + key);
                for (int i = 0; i < fields.length; i++) {
                    for (int j = 0; j < fields[i].length; j++) {

                        if (i < fields.length - 1) {

                        } else {
                            ps.setString(j + 1, fields[i][j]);

                        }
                    }
                }

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    ret_val = rs.getString(variable_name);
                }
                return ret_val;

            } catch (Exception ex) {
                ErrorDAO.Error_Add(new managedModal.Error("Check if data for  multiple fiields and table exisit method", "inserting data into table " + table, " Message: " + ex.getMessage(), date));
                return null;
            }

        }
        return null;
    }

    public static String returnexisitDataID(String table, String field, String data) {
        try {
            String id = "";

            Connection con;
            con = Apache_Connectionpool.getInstance().getConnection();

            String query = "SELECT " + field + " FROM " + table + " WHERE " + field + " ='" + data + "'";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                id = rs.getString(field);

            }
            con.close();
            rs.close();
            return id;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }

    }

    public static String returnlastID(String table, String field) {
        try {
            String id = "";

            Connection con;
            con = Apache_Connectionpool.getInstance().getConnection();

            String query = "SELECT " + field + " FROM " + table + " WHERE " + field + " !='9999' ORDER BY " + field + " DESC";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                id = rs.getString(field);

            }
            con.close();
            rs.close();
            return id;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }

    }

    public static Integer countcolums(String table, String[][] fields) throws SQLException {
        if (fields.length > 0) {
            String key, value, data;
            data = "";
            value = "";
            key = "";
            for (int i = 0; i < fields.length; i++) {
                for (int j = 0; j < fields[i].length; j++) {

                    if (i < fields.length - 1) {

                        key += fields[i][j] + "=?";
                        if (j < fields[i].length - 1) {
                            key += " AND ";
                        }
                    } else {
                        data += fields[i][j];
                        value += "?";
                        if (j < fields[i].length - 1) {
                            value += ", ";
                        }
                    }
                }
            }

            try {
                Connection con;
                con = Apache_Connectionpool.getInstance().getConnection();
               

//                PreparedStatement ps = con.prepareStatement("INSERT INTO " + table + "(" + key + ") VALUES(" + value + ")");
                PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) as Count FROM " + table + " WHERE " + key);
                for (int i = 0; i < fields.length; i++) {
                    for (int j = 0; j < fields[i].length; j++) {

                        if (i < fields.length - 1) {

                        } else {
                            data += fields[i][j];
                            ps.setString(j + 1, fields[i][j]);

                        }
                    }
                }

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Integer temp = rs.getInt("Count");
                    con.close();
                    rs.close();
                    if (temp > 0) {
                        return temp;
                    } else {
                        return 0;
                    }
                } else {
                    con.close();
                    rs.close();
                    return 0;
                }

            } catch (Exception ex) {
                ErrorDAO.Error_Add(new managedModal.Error("Check if data for multiple fields exisit method", "inserting data into table " + table, " Message: " + ex.getMessage(), date));
                return 0;
            }

        }
        return 0;
    }

    public static Integer calculateSum(String table, String calculatedfield, String[][] fields) throws SQLException {
        if (fields.length > 0) {
            String key, value, data;
            data = "";
            value = "";
            key = "";
            for (int i = 0; i < fields.length; i++) {
                for (int j = 0; j < fields[i].length; j++) {

                    if (i < fields.length - 1) {

                        key += fields[i][j] + "=?";
                        if (j < fields[i].length - 1) {
                            key += " AND ";
                        }
                    } else {
                        data += fields[i][j];
                        value += "?";
                        if (j < fields[i].length - 1) {
                            value += ", ";
                        }
                    }
                }
            }

            try {
                Connection con;
                con = Apache_Connectionpool.getInstance().getConnection();
               

//                PreparedStatement ps = con.prepareStatement("INSERT INTO " + table + "(" + key + ") VALUES(" + value + ")");
                PreparedStatement ps = con.prepareStatement("SELECT SUM(" + calculatedfield + ") as sum FROM " + table + " WHERE " + key);
                for (int i = 0; i < fields.length; i++) {
                    for (int j = 0; j < fields[i].length; j++) {

                        if (i < fields.length - 1) {

                        } else {
                            data += fields[i][j];
                            ps.setString(j + 1, fields[i][j]);

                        }
                    }
                }

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Integer temp = rs.getInt("sum");
                    con.close();
                    rs.close();
                    if (temp > 0) {
                        return temp;
                    } else {
                        return 0;
                    }
                } else {
                    con.close();
                    rs.close();
                    return 0;
                }

            } catch (Exception ex) {
                ErrorDAO.Error_Add(new managedModal.Error("calculating sum", "calculating sum " + table, " Message: " + ex.getMessage(), date));
                return 0;
            }

        }
        return 0;
    }

    public static Integer doublecalculateSum(String table, String calculatedfield1, String calculatedfield2, String[][] fields) throws SQLException {
        if (fields.length > 0) {
            String key, value, data;
            data = "";
            value = "";
            key = "";
            for (int i = 0; i < fields.length; i++) {
                for (int j = 0; j < fields[i].length; j++) {

                    if (i < fields.length - 1) {

                        key += fields[i][j] + "=?";
                        if (j < fields[i].length - 1) {
                            key += " AND ";
                        }
                    } else {
                        data += fields[i][j];
                        value += "?";
                        if (j < fields[i].length - 1) {
                            value += ", ";
                        }
                    }
                }
            }

            try {
                Connection con;
                con = Apache_Connectionpool.getInstance().getConnection();

//                PreparedStatement ps = con.prepareStatement("INSERT INTO " + table + "(" + key + ") VALUES(" + value + ")");
                PreparedStatement ps = con.prepareStatement("SELECT SUM(" + calculatedfield1 + "*" + calculatedfield2 + ") as sum FROM " + table + " WHERE " + key);
                for (int i = 0; i < fields.length; i++) {
                    for (int j = 0; j < fields[i].length; j++) {

                        if (i < fields.length - 1) {

                        } else {
                            data += fields[i][j];
                            ps.setString(j + 1, fields[i][j]);

                        }
                    }
                }

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Integer temp = rs.getInt("sum");
                    con.close();
                    rs.close();
                    
                    if (temp > 0) {
                        return temp;
                    } else {
                        return 0;
                    }
                } else {
                    con.close();
                    rs.close();
                    return 0;
                }

            } catch (Exception ex) {
                ErrorDAO.Error_Add(new managedModal.Error("calculating sum", "calculating sum " + table, " Message: " + ex.getMessage(), date));
                return 0;
            }

        }
        return 0;
    }

    public static boolean Check_if_exsistsandnotnull(String table, String column1, String value, String column2) throws SQLException {

        try {
            Connection con;
            con = Apache_Connectionpool.getInstance().getConnection();
           

//                PreparedStatement ps = con.prepareStatement("INSERT INTO " + table + "(" + key + ") VALUES(" + value + ")");
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) as Count FROM " + table + " WHERE " + column1 + " = '" + value + "' AND " + column2 + " IS NOT NULL AND " + column2 + " !=''");

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Integer temp = rs.getInt("Count");
                con.close();
                rs.close();
                
                if (temp > 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                con.close();
                rs.close();
                return false;
            }

        } catch (Exception ex) {
            ErrorDAO.Error_Add(new managedModal.Error("Check if data for multiple fields exisit method", "inserting data into table " + table, " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public boolean Check_if_exsistz(String value, String table_name, String fieldname)
            throws SQLException {

        try {
            Connection con;
            con = Apache_Connectionpool.getInstance().getConnection();
           

            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) as Count FROM " + table_name + " WHERE " + fieldname + "=?");
            ps.setString(1, value);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Integer temp = rs.getInt("Count");
                con.close();
                rs.close();
                if (temp > 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                con.close();
                rs.close();
                
                return false;
            }

        } catch (Exception ex) {
            ErrorDAO.Error_Add(new managedModal.Error("Check_if_exsists", "Check if " + fieldname + " exisits in " + table_name + "", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

}
