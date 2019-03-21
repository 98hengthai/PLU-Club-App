import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EventJDBCRepoTest {
    EventJDBCRepo repo;
    @Before
    public void setUp(){ repo = new EventJDBCRepo(References.OFF_CAMPUS_DB_URL); }

    @Test
    public void getEvents() {
        String response = repo.getEvents();
        System.out.println(response);
    }

    @Test
    public void getEventGivenName(){
        String response = repo.getEventGivenName("Capstone Study Party");
        System.out.println(response);
    }

    @Test
    public void getEventGivenID(){
        String response = repo.getEventGivenID("1");
        System.out.println(response);
    }

    @Test
    public void getEventGivenClub(){
        String response = repo.getEventGivenClub("Computer Science Club");
        System.out.println(response);
    }
}
