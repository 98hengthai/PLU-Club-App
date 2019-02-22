import java.util.Collection;
/**
 * This is the interface for the Club Entity in the database talking to the Server API.
 * The interface is responsible for creating, deleting, updating, and retrieving information
 *  for club entities.
 */
public interface IClubRepo {
    public boolean createClub(Club club);

    public Collection<Club> getClubs();

    public Club getClub(String name);

    public Club editClub(Club edit) throws Exception;

    public boolean deleteClub(String name);

    public boolean clubExist(String name);
}
