import org.json.JSONArray;

public interface IUsersRepo {
    public JSONArray getAllUserEmails();
    public String[] getAllUserInformation();
    public boolean deleteUser(String email);
    public boolean updateUserInformation(String keyAttrb, String info);
}
