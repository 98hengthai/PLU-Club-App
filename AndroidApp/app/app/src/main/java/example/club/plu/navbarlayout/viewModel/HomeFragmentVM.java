package example.club.plu.navbarlayout.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import example.club.plu.navbarlayout.model.event.Event;
import example.club.plu.navbarlayout.model.event.Home;
import example.club.plu.navbarlayout.repository.EventRepository;
import example.club.plu.navbarlayout.repository.HomeRepository;

public class HomeFragmentVM extends ViewModel {
    private HomeRepository mHomeRepo;


    public HomeFragmentVM() {
        mHomeRepo = HomeRepository.getInstance();
    }

    public LiveData<List<Home>> getHome() {
        return mHomeRepo.getHome();
    }

    public void searchHomeApi() {
        mHomeRepo.searchHomeApi();
    }
}
