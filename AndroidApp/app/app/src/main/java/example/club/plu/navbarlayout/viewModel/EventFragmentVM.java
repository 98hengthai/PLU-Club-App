package example.club.plu.navbarlayout.viewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import example.club.plu.navbarlayout.model.Event;
import example.club.plu.navbarlayout.repository.EventRepository;


public class EventFragmentVM extends ViewModel {
    private EventRepository mEventRepo;

    public EventFragmentVM() {
        mEventRepo = EventRepository.getInstance();
    }

    //getter for liveData
    public LiveData<List<Event>> getEvents(){
        return mEventRepo.getEvents() ;
    }

    public void searchEventsApi(){
        mEventRepo.searchEventsApi();
    }
}
