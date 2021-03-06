package example.club.plu.navbarlayout.repository;
import android.arch.lifecycle.LiveData;

import java.util.List;
import example.club.plu.navbarlayout.model.club.Club;
import example.club.plu.navbarlayout.model.club.ClubUsers;
import example.club.plu.navbarlayout.model.club.Interests;
import example.club.plu.navbarlayout.model.club.UserInterest;
import example.club.plu.navbarlayout.request.club.ClubApiClient;

public class ClubRepository {
    private static ClubRepository instance; //Singleton
    private ClubApiClient mClubApiClient;

    public  static  ClubRepository getInstance(){
        if(instance == null) {
            instance = new ClubRepository();
        }
        return  instance;
    }

    //Constructor
    private ClubRepository(){
        mClubApiClient = ClubApiClient.getInstance();
    }

    //getter for liveData
    public LiveData<List<Club>> getClubs() {
        return mClubApiClient.getClubs();
    }

    //getter methods
    public LiveData<List<ClubUsers>> getJoinedClubs() { return mClubApiClient.getJoinedClubs(); }

    public List<Interests> getAllInterests() { return mClubApiClient.getmInterests(); }

    public LiveData<Club> getClub() {
        return mClubApiClient.getmClub();
    }

    public LiveData<List<Club>> getClubsOfUserInterest() {
        return mClubApiClient.getmClubofUserInterest();
    }

    //set ClubApiClient
    public void searchClubsApi(){
       mClubApiClient.searchClubApi();
    }

    public void searchJoinedClubsApi(String userEmail) { mClubApiClient.searchJoinedClubApi(userEmail); }

    public void searchAllInterests() {mClubApiClient.searchAllInterests(); }

    public void searchClub(String email) {
        mClubApiClient.searchClub(email);
    }

    public void searchClubRelatedToUserInterests(String email) {
        mClubApiClient.searchClubRelatedToUserInterests(email);
    }


}
