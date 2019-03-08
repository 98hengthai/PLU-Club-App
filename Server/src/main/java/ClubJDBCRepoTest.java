import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class ClubJDBCRepoTest {
    ClubJDBCRepo repo;
    @Before
    public void setUp(){
        repo = new ClubJDBCRepo("jdbc:sqlite:/D:/SQLServer/ClubDatabase");
    }

    @Test
    public void createClub() {
        assertFalse(false);
    }

    @Test
    public void getClubs() {
        String response = repo.getClubs();
        System.out.println(response);
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