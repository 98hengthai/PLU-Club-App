package example.club.plu.navbarlayout.viewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import example.club.plu.navbarlayout.model.club.Club;
import example.club.plu.navbarlayout.model.club.ClubUsers;
import example.club.plu.navbarlayout.model.club.Interests;
import example.club.plu.navbarlayout.model.club.UserInterest;
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

    //getter methods
    public LiveData<List<ClubUsers>> getJoinedClubs(){
        return mClubRepo.getJoinedClubs() ;
    }

    public List<Interests> getAllInterests() { return  mClubRepo.getAllInterests();}

    public LiveData<Club> getClub() {
        return mClubRepo.getClub();
    }

    public LiveData<List<Club>> getClubsOfUserInterest() {
        return mClubRepo.getClubsOfUserInterest();
    }

    //call chains
    public void searchClubsApi(){
        mClubRepo.searchClubsApi();
    }

    public void searchJoinedClubsApi(String userEmail) {
        mClubRepo.searchJoinedClubsApi(userEmail);
    }

    public void searchAllInterests(){ mClubRepo.searchAllInterests();}

    public void searchClub(String email){
        mClubRepo.searchClub(email);
    }

    public void searchClubRelatedToUserInterests(String email) {
        mClubRepo.searchClubRelatedToUserInterests(email);
    }


}
