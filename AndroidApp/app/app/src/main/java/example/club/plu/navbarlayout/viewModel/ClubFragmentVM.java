package example.club.plu.navbarlayout.viewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import example.club.plu.navbarlayout.model.Club;
import example.club.plu.navbarlayout.repository.ClubRepository;


public class ClubFragmentVM extends ViewModel {
    private ClubRepository mClubRepo;

    public ClubFragmentVM() {
        mClubRepo = ClubRepository.getInstance();
    }

    //getter for liveData
    public LiveData<List<Club>> getClubs(){
        return mClubRepo.getClubs() ;
    }

    public void searchClubsApi(){
        mClubRepo.searchClubsApi();
    }
}
