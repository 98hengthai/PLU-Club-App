package Interfaces;

/**
 * This is the interface for the entities.Event entity in the database talking to the Server API.
 * The interface is responsible for creating, deleting, updating, and retrieving information
 *  for entities.Event entities.entities.
 *
 *  Created: Jimmy Nguyen on 3/20/2019
 */

public interface IEventRepo {
    String getEvents();

    String getEventGivenName(String eventName);

    String getEventGivenID(String idNum);

    String getEventGivenClub(String clubName);

    boolean createEvent(String idNum, String evName, String loc, String stTime, String endTime, String rep, String cName);

    String editEvent(String idNum, String evName, String loc, String stTime, String endTime, String rep, String cName);

    boolean deleteEvent(String idNum);

    boolean deleteEventGivenName(String evName);

    boolean eventExist(String idNum);

    boolean eventExistGivenName(String evName);
}
