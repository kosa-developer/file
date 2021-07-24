package managedDao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import managedModal.Error;
import managedModal.Roles;

public class RolesDAO
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Date date;

    public static List<Roles> listAll()
            throws SQLException {

        try {
            Connection con;
            
            Roles roles;

            con=Apache_Connectionpool.getInstance().getConnection();
            date = new Date();

            PreparedStatement stmt = con.prepareStatement("select rid,name,description from roles");

            ResultSet rs = stmt.executeQuery();

            List roleList = new ArrayList();

            while (rs.next()) {
                roles = new Roles(Integer.valueOf(rs.getInt("rid")), rs.getString("name"), rs.getString("description"));
                roleList.add(roles);
            }
           con.close();
                rs.close();
            return roleList;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new Error("Roles DAO", "listAll", " Message: " + ex.getMessage(), date));
            return null;
        }
    }
}