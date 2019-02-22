import junit.framework.TestCase;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.Assert.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class ClubJDBCRepoTest {
    ClubJDBCRepo repo;
    @Before
    public void setUp(){
        repo = new ClubJDBCRepo();
    }

    @Test
    public void createClub() {
        assertFalse(false);
    }

    @Test
    public void getClubs() {
        Collection<Club> clubs = new ArrayList<Club>();

        clubs = repo.getClubs();
        Iterator<Club> iterator = clubs.iterator();

        while(iterator.hasNext()){
            System.out.println("Club: " + iterator.next());
            assertTrue(true);
        }
    }

    @Test
    public void getClub() {
    }

    @Test
    public void editClub() {
    }

    @Test
    public void deleteClub() {
    }

    @Test
    public void clubExist() {
    }
}