package Interfaces;

public interface IClubUsersRepo {
    String getAllClubUsers();
    String getAllClubsGivenUser(String userEmail);
    String getAllUsersGivenClub(String clubName);
    String getRoleGivenNameAndClub(String clubName, String userEmail);

    String createClubUser(String clubName, String userEmail, String role);

    String deleteClubUser(String clubName, String userEmail);

    boolean clubUserExist(String clubName, String userEmail);

}
