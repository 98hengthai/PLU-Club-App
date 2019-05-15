package example.club.plu.navbarlayout.repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import example.club.plu.navbarlayout.model.event.Home;
import example.club.plu.navbarlayout.request.event.EventApiClient;

public class HomeRepository {
    private static HomeRepository instance;
    private EventApiClient mEventApiClient;

    public static HomeRepository getInstance(){
        if(instance == null){
            instance = new HomeRepository();
        }
        return instance;
    }

    private HomeRepository() {
        mEventApiClient = EventApiClient.getInstance();
    }

    public LiveData<List<Home>> getHome() {
        return mEventApiClient.getHome();
    }

    public void searchHomeApi() {
        mEventApiClient.searchsHomeApi();
    }
}
