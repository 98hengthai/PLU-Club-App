package Tests;

import common.References;
import JDBCRepo.EventJDBCRepo;
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

    @Test
    public void eventExist(){
        Assert.assertTrue(repo.eventExist("1"));
    }

    @Test
    public void eventExistGivenName(){
        Assert.assertTrue(repo.eventExistGivenName("Computer Science Club"));
    }

    @Test
    public void editEvent(){
        String temp = repo.editEvent("3", "TestEvent", "Admin 101", "2018-10-22 00:00:00", "2018-10-22 01:00:00", "Daily", "Netflix Club");
        System.out.println(temp);
    }
}
