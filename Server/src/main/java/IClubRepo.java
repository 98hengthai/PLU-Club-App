/**
 * This is the interface for the Club Entity in the database talking to the Server API.
 * The interface is responsible for creating, deleting, updating, and retrieving information
 *  for club entities.
 */
public interface IClubRepo {
    public boolean createClub(String cName, String cLoc, String cEmail, String cDesc);

    public String getClubs();

    public String getClub(String name);

    public String editClub(String cName, String cLoc, String cEmail, String cDesc) throws Exception;

    public boolean deleteClub(String name);

    public boolean clubExist(String name);
}
