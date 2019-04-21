package example.club.plu.navbarlayout.repository;
import android.arch.lifecycle.LiveData;
import java.util.List;
import example.club.plu.navbarlayout.model.Event;
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
    public LiveData<List<Event>> getEvents() {
        return mEventApiClient.getEvents();
    }

    //set ClubApiClient
    public void searchEventsApi(){
        mEventApiClient.searchsEventApi();
    }

}
