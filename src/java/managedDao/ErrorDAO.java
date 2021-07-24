package managedDao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import managedModal.Error;

public class ErrorDAO
        implements Serializable {

    private static final long serialVersionUID = 1L;

    public static void Error_Add(Error custom_error)
            throws SQLException {

        try {
            Connection con;

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            con=Apache_Connectionpool.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement("insert into error_log(Controller_Name,Method_Name,Error,Record_Date) values(?,?,?,?)");

            ps.setString(1, custom_error.getController_Name());
            ps.setString(2, custom_error.getMethod_Name());
            ps.setString(3, custom_error.getError());
            ps.setString(4, dateFormat.format(custom_error.getError_date()));
            
            ps.executeUpdate();
            
            con.close();
            ps.close();
            
        } catch (Exception ex) {
            System.out.println("Error in Error_Add() -->" + ex.getMessage());
        }
    }
}