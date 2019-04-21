package example.club.plu.navbarlayout.repository;
import android.arch.lifecycle.LiveData;
import java.util.List;
import example.club.plu.navbarlayout.model.Club;
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

    //set ClubApiClient
    public void searchClubsApi(){
       mClubApiClient.searchsClubApi();
    }

}
