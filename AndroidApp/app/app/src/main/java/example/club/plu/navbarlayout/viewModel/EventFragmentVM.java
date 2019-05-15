package example.club.plu.navbarlayout.viewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import example.club.plu.navbarlayout.model.event.Event;
import example.club.plu.navbarlayout.model.event.UserEvent;
import example.club.plu.navbarlayout.repository.EventRepository;


public class EventFragmentVM extends ViewModel {
    private EventRepository mEventRepo;

    public EventFragmentVM() {
        mEventRepo = EventRepository.getInstance();
    }

    //getter for liveData
    public LiveData<List<UserEvent>> getEvents(){return mEventRepo.getEvents();  }

    public LiveData<List<Event>> getAllEvents(){
        return mEventRepo.getAllEvents();
    }

    public LiveData<List<Event>> getTodayEvent() {
        return mEventRepo.getTodayEvent();
    }

    public void searchEventsApi(){
        mEventRepo.searchEventsApi();
    }

    public void searchUserEventsApi() {
        mEventRepo.searchUserEventsApi();
    }

    public LiveData<List<Event>> getUserEvents() {
        return mEventRepo.getUserEvents();
    }
    //new
    public void searchUserEvent(String email) {
        mEventRepo.searchUserEvent(email);
    }

    public void searchTodayEvent(String email) {
        mEventRepo.searchTodayEvent(email);
    }


}
