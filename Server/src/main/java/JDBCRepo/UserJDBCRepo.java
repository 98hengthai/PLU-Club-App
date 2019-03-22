package JDBCRepo;

import prototypes.Interfaces.IUsersRepo;
import dbConnections.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserJDBCRepo implements IUsersRepo {
    private Connection dbConn;

    public UserJDBCRepo(String dbURL ){
        this.dbConn = new DatabaseConnection(dbURL).getConnection();
    }

    @Override
    public String getAllUserEmails() {
        ResultSet rs;
        ArrayList<String> emails = new ArrayList<>();
//        try {
//            while (rs.next()) {
//                emails.add(rs.getString(1));
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
        //return new JSONArray(emails);
        return "emails";
    }

    @Override
    public String[] getAllUserInformation() {
        return new String[0];
    }

    @Override
    public boolean deleteUser(String email) {
        return false;
    }

    @Override
    public boolean updateUserInformation(String keyAttrb, String info) {
        return false;
    }
}
