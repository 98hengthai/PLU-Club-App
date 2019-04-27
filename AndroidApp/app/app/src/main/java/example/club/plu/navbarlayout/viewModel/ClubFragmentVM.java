package example.club.plu.navbarlayout.viewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import example.club.plu.navbarlayout.model.club.Club;
import example.club.plu.navbarlayout.model.club.ClubUsers;
import example.club.plu.navbarlayout.model.club.Interests;
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

    public LiveData<List<ClubUsers>> getJoinedClubs(){
        return mClubRepo.getJoinedClubs() ;
    }

    public List<Interests> getAllInterests() { return  mClubRepo.getAllInterests();}


    //call chains
    public void searchClubsApi(){
        mClubRepo.searchClubsApi();
    }

    public void searchJoinedClubsApi(String userEmail) {
        mClubRepo.searchJoinedClubsApi(userEmail);
    }

    public void searchAllInterests(){ mClubRepo.searchAllInterests();}
}
