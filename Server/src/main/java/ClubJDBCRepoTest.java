import org.junit.Assert;
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
        String cInfo = repo.getClub("Capstone Club");
        System.out.println(cInfo);
    }

    @Test
    public void editClub() throws Exception {
        String temp = repo.editClub("Capstone Club", "Somewhere", "this@plu.edu", "A description");
        System.out.println(temp);
    }

    @Test
    public void deleteClub() {
    }

    @Test
    public void clubExist() {
        Assert.assertTrue(repo.clubExist("Capstone Club"));
        Assert.assertEquals(false, repo.clubExist("Fake Club"));
        System.out.println("Passed");

    }
}