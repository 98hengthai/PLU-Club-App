import org.json.JSONArray;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserJDBCRepo implements IUsersRepo {
    private DatabaseCommands dbCmd;

    public UserJDBCRepo(DatabaseCommands cmd ){
        dbCmd = cmd;
    }

    @Override
    public JSONArray getAllUserEmails() {
        ResultSet rs = dbCmd.getAllUserEmails();
        ArrayList<String> emails = new ArrayList<>();
        try {
            while (rs.next()) {
                emails.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new JSONArray(emails);
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
