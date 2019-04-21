package Interfaces;

public interface IUserEventsRepo {
    String getAllUserEvents();

    String getUserEventsGivenIDNum(String eventIDNum);
    String getUserEventsGivenUserEmail(String userEmail);

    String getReplyGivenUserAndEvent(String eventIDNum, String userEmail);

    String createUserEvents(String eventIDNum, String clubName, String userEmail, String reply);

    String editUserEvents(String eventIDNum, String clubName, String userEmail, String reply);

    String deleteUserEvents(String eventIDNum, String userEmail);

    boolean userEventsExist(String eventIDNum, String userEmail);
}
