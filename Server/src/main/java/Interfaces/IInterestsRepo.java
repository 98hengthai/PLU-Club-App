package Interfaces;

public interface IInterestsRepo {
    String getInterests();
    boolean createInterest(String interest);
    boolean deleteInterest(String interest);
    String interestExist(String interest);
}
