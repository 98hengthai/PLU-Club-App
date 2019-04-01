package Interfaces;

public interface IUsersRepo {
    String getUsers();
    String getUserGivenEmail(String email);
    String getUserGivenName(String name);
    boolean createUser(String email, String name, String gradYear, String studentBool);
    String editUser(String email, String name, String gradYear, String studentBool);
    String editUserGivenName(String email, String name, String gradYear, String studentBool);
    boolean deleteUser(String email);
    boolean deleteUserGivenName(String name);
    boolean userExist(String email);
    boolean userExistGivenName(String name);
}
