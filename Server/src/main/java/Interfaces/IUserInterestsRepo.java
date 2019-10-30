package Interfaces;

public interface IUserInterestsRepo {
    String getAllUserInterests();
    String getUserInterestsGivenUser(String userEmail);
    String getUserInterestsGivenInterest(String interestName);
    boolean createUserInterest(String userEmail, String interestName);
    boolean deleteUserInterest(String userEmail, String interestName);
    boolean userInterestExist(String userEmail, String interestName);
}
