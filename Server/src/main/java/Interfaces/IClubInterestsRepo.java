package Interfaces;

public interface IClubInterestsRepo {
    String getClubInterestsGivenClub(String clubName);
    String getClubInterestsGivenInterest(String interestName);
    boolean createClubInterest(String clubName, String interestName);
    boolean deleteClubInterest(String clubName, String interestName);
}
