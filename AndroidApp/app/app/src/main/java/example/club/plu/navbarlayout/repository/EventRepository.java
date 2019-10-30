package example.club.plu.navbarlayout.repository;
import android.arch.lifecycle.LiveData;
import java.util.List;
import example.club.plu.navbarlayout.model.event.Event;
import example.club.plu.navbarlayout.model.event.Home;
import example.club.plu.navbarlayout.model.event.UserEvent;
import example.club.plu.navbarlayout.request.event.*;

public class EventRepository {
    private static EventRepository instance; //Singleton
    private EventApiClient mEventApiClient;

    public  static  EventRepository getInstance(){
        if(instance == null) {
            instance = new EventRepository();
        }
        return  instance;
    }

    //Constructor
    private EventRepository(){
        mEventApiClient = EventApiClient.getInstance();
    }

    //getter for liveData
    public LiveData<List<UserEvent>> getEvents() {
        return mEventApiClient.getEvents();
    }

    public LiveData<List<Event>> getAllEvents() {
        return mEventApiClient.getAllEvents();
    }
    //set ClubApiClient
    public void searchEventsApi(){
        mEventApiClient.searchsEventApi();
    }


    public void searchUserEventsApi() {
        mEventApiClient.searchsUserEventsApi();
    }


    public void searchUserEvent(String email) {
        mEventApiClient.searchUserEvent(email);
    }

    public LiveData<List<Event>> getUserEvents() {
        return mEventApiClient.getmUserEvents();
    }

    public void searchTodayEvent(String email) {
        mEventApiClient.searchTodayEvent(email);
    }

    public LiveData<List<Event>> getTodayEvent() {
        return mEventApiClient.getmUserTodayEvents();
    }
}
