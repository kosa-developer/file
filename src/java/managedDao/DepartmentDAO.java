package managedDao;

import java.beans.PropertyVetoException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import managedModal.Department;
import managedModal.Error;

public
        class DepartmentDAO
        implements Serializable {

    private static final
            long serialVersionUID = 1L;
    private static
            Date date;

    public static
            List<Department> getAllDepartments() throws SQLException {

        try {
            Connection con;
            Department department;

            con=Apache_Connectionpool.getInstance().getConnection();

            date = new Date();

            PreparedStatement stmt = con.prepareStatement("select * from department order by DepartmentName ASC");

            ResultSet rs = stmt.executeQuery();

            List departmentList = new ArrayList();

            while (rs.next()) {
                department = new Department(Integer.valueOf(rs.getInt("did")), rs.getString("departmentName"), rs.getString("description"));
                departmentList.add(department);
            }
            con.close();
            rs.close();
            return departmentList;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Department DAO", "getAllDepartments", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            Department getDepartments(Integer _id) throws SQLException {

        try {
            Connection con;
            Department department;

            con=Apache_Connectionpool.getInstance().getConnection();

            date = new Date();

            PreparedStatement stmt = con.prepareStatement("select * from department where did=?");
            stmt.setInt(1, _id);

            ResultSet rs = stmt.executeQuery();

            department = null;

            while (rs.next()) {
                department = new Department(rs.getInt("did"), rs.getString("departmentName"), rs.getString("description"));
            }
            con.close();
            rs.close();
            return department;
        }
        catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Department DAO", "getDepartments", " Message: " + ex.getMessage(), date));
            return null;
        }
    }

    public static
            boolean Department_Find(String DepartmentName)
            throws SQLException {

        try {
            Connection con;
            con=Apache_Connectionpool.getInstance().getConnection();

            date = new Date();

            PreparedStatement stmt = con.prepareStatement("select * from department where departmentName =?");
            stmt.setString(1, DepartmentName);

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
            ErrorDAO.Error_Add(new Error("Department DAO", "Department_Find", " Message: " + ex.getMessage(), date));
            return false;
        }
    }

    public static
            boolean Department_Add(String DepartmentName, String Description)
            throws SQLException, ClassNotFoundException, PropertyVetoException, Exception {

        try {
            Connection con;
           con=Apache_Connectionpool.getInstance().getConnection();

            date = new Date();

            PreparedStatement ps = con.prepareStatement("insert into department(DepartmentName,Description)                                values(?,?)");

            ps.setString(1, DepartmentName);
            ps.setString(2, Description);

            ps.executeUpdate();

            con.close();
            ps.close();

            return true;
        }
        catch (SQLException ex) {
            ErrorDAO.Error_Add(new Error("Department DAO", "Department_Add", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static
            boolean Department_Delete(Integer department_id)
            throws SQLException, ClassNotFoundException, PropertyVetoException, Exception {

        try {
            Connection con;
            con=Apache_Connectionpool.getInstance().getConnection();

            date = new Date();

            PreparedStatement ps = con.prepareStatement("Delete from department where DID=?");

            ps.setInt(1, department_id.intValue());

            ps.executeUpdate();

            con.close();
            ps.close();

            return true;
        }
        catch (SQLException ex) {
            ErrorDAO.Error_Add(new Error("Department DAO", "Department_Delete", " Message: " + ex.getMessage(), date));
            return false;
        }

    }

    public static
            boolean Department_Update(Integer department_id, String DepartmentName, String Description)
            throws SQLException, ClassNotFoundException, PropertyVetoException, Exception {

        try {
            Connection con;
            con=Apache_Connectionpool.getInstance().getConnection();

            date = new Date();

            PreparedStatement ps = con.prepareStatement("update department d set d.DepartmentName=? , d.Description=? where d.DID=?");

            ps.setString(1, DepartmentName);
            ps.setString(2, Description);
            ps.setInt(3, department_id.intValue());

            ps.executeUpdate();

            con.close();
            ps.close();

            return true;
        }
        catch (SQLException ex) {
            ErrorDAO.Error_Add(new Error("Department DAO", "Department_Update", " Message: " + ex.getMessage(), date));
            return false;
        }

    }
}
