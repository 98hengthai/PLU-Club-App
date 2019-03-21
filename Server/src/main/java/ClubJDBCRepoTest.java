import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class ClubJDBCRepoTest {
    ClubJDBCRepo repo;
    @Before
    public void setUp(){
        repo = new ClubJDBCRepo(
                References.OFF_CAMPUS_DB_URL);
    }

    @Test
    public void createClub() {
        boolean result = repo.createClub("Board Games Club", "Rieke 121", "BoardGames@plu.edu", "We play board games at PLU!");
        Assert.assertEquals(true, result);
    }

    @Test
    public void getClubs() {
        String response = repo.getClubs();
        System.out.println(response);
    }

    @Test
    public void getClub() {
        String cInfo = repo.getClub("Capstone Club");
        String willFail = repo.getClub("Failed");
        System.out.println(cInfo);
        System.out.println(willFail);
    }

    @Test
    public void editClub() throws Exception {
        String temp = repo.editClub("Capstone Club", "Somewhere", "this@plu.edu", "A description");
        System.out.println(temp);
    }

    @Test
    public void deleteClub() {
        Boolean cInfo = repo.deleteClub("Test");
    }

    @Test
    public void clubExist() {
        Assert.assertTrue(repo.clubExist("Capstone Club"));
        Assert.assertEquals(false, repo.clubExist("Fake Club"));
        System.out.println("Passed");

    }
}