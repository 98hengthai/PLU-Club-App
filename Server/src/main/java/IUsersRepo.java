public interface IUsersRepo {
    public String getAllUserEmails();
    public String[] getAllUserInformation();
    public boolean deleteUser(String email);
    public boolean updateUserInformation(String keyAttrb, String info);
}
