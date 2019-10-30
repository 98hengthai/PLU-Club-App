package example.club.plu.navbarlayout.request.club;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import example.club.plu.navbarlayout.model.club.Club;
import example.club.plu.navbarlayout.model.club.ClubUsers;
import example.club.plu.navbarlayout.model.club.Interests;
import example.club.plu.navbarlayout.ServiceGenerator;
import example.club.plu.navbarlayout.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClubApiClient {
    private static final String TAG = "ClubApiClient";
    private static ClubApiClient instance; //signleton
    private MutableLiveData<List<Club>> mClubs; //livedata
    private MutableLiveData<List<ClubUsers>> mClubUsers;
    private List<Interests> mInterests;
    private MutableLiveData<Club> mClub;
    private MutableLiveData<List<Club>> mClubofUserInterest;

    public static ClubApiClient getInstance() {
        if(instance == null) {
            instance = new ClubApiClient();
        }
        return instance;
    }

    private ClubApiClient(){
        mClubs = new MutableLiveData<>();
        mClubUsers = new MutableLiveData<>();
        mInterests = new ArrayList<>();
        mClub = new MutableLiveData<>();
        mClubofUserInterest = new MutableLiveData<>();
    }

    //getter for all clubs list
    public LiveData<List<Club>> getClubs() {
        return mClubs;
    }
    //getter for joined club list
    public LiveData<List<ClubUsers>> getJoinedClubs() { return mClubUsers; }
    //getter for interest list
    public List<Interests> getmInterests() {
        return mInterests;
    }
    //getter for club given name
    public LiveData<Club> getmClub(){
        return mClub;
    }
    //getter for clubs base on user interests
    public MutableLiveData<List<Club>> getmClubofUserInterest() {
        return mClubofUserInterest;
    }

    //set background thread to get livedata
    public void searchClubApi() {
        Call<List<Club>> call = ServiceGenerator.getClubApi().getClubs();
        call.enqueue(new Callback<List<Club>>() {
            @Override
            public void onResponse(Call<List<Club>> call, Response<List<Club>> response) {
                if(response.isSuccessful()){
                    List<Club> clubs = response.body();
                    mClubs.postValue(clubs);
                }
            }

            @Override
            public void onFailure(Call<List<Club>> call, Throwable t) {

            }
        });

    }

    //init joined club list
    public void searchJoinedClubApi(String userEmail) {
        Call<List<ClubUsers>> call = ServiceGenerator.getClubApi().getAllClubsGivenUserEmail(userEmail);
        call.enqueue(new Callback<List<ClubUsers>>() {
            @Override
            public void onResponse(Call<List<ClubUsers>> call, Response<List<ClubUsers>> response) {
                if(response.isSuccessful()){
                    List<ClubUsers> clubList = (List<ClubUsers>) response.body();
                    //set livedata on the background thread
                    mClubUsers.postValue(clubList);
                } else {
                    String error = response.errorBody().toString();
                    Log.e(TAG, "searchJoinedClubApi run: " + error);
                    mClubUsers.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<ClubUsers>> call, Throwable t) {
                Log.e(TAG, "searchJoinedClubApi run: " + t.getMessage());
                mClubUsers.postValue(null);
            }
        });
    }

    //init interest club list
    public void searchAllInterests(){
        Call<List<Interests>> call = ServiceGenerator.getClubApi().getAllInterests();
        call.enqueue(new Callback<List<Interests>>() {
            @Override
            public void onResponse(Call<List<Interests>> call, Response<List<Interests>> response) {
                if(response.isSuccessful()){
                    mInterests = response.body();
                } else {
                    Log.e(TAG, "searchAllInterests run: " + response.errorBody().toString());
                }
            }
            @Override
            public void onFailure(Call<List<Interests>> call, Throwable t) {
                Log.e(TAG, "searchAllInterests run: " + t.getMessage());
            }
        });
    }



    //search a club given its name
    public void searchClub(String clubName) {
        Call<Club> call = ServiceGenerator.getClubApi().getClubs(clubName);
        call.enqueue(new Callback<Club>() {
            @Override
            public void onResponse(Call<Club> call, Response<Club> response) {
                if(response.isSuccessful()){
                    Club club = response.body();
                    mClub.postValue(club);
                    Log.d(TAG, "onResponse: club given name - " + mClub.toString());
                } else {
                    Log.e(TAG, "onResponse: club given name : " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Club> call, Throwable t) {

            }
        });
    }



    //post joined club
    public static void postJoinedClub(ClubUsers clubUsers) {
        String body = "1st=" + clubUsers.getClubName() + "&2nd=" + clubUsers.getUserEmail() + "&3rd="+clubUsers.getRole();
        Log.d(TAG, "postJoinedClub: body - " + body);

        Call<ClubUsers> call = ServiceGenerator.getClubApi().postClubUsers(body);
        call.enqueue(new Callback<ClubUsers>() {
            @Override
            public void onResponse(Call<ClubUsers> call, Response<ClubUsers> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: post joined club" + response.code());
                    return;
                }
            }

            @Override
            public void onFailure(Call<ClubUsers> call, Throwable t) {
                Log.d(TAG, "onResponse: post joined club" + t.getMessage());
            }
        });
    }

    //post User interest
    public static void postUserInterest(String email, String interest) {
        String body = "1st=" + email + "&2nd=" + interest ;
        Call<Interests> call = ServiceGenerator.getClubApi().postUserInterest(body);
        call.enqueue(new Callback<Interests>() {
            @Override
            public void onResponse(Call<Interests> call, Response<Interests> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: post new interest" + response.code());
                    return;
                }
            }

            @Override
            public void onFailure(Call<Interests> call, Throwable t) {

            }
        });
    }



    //search clubs base on user interests
    public void searchClubRelatedToUserInterests(String email) {
        Call<List<Club>> call = ServiceGenerator.getClubApi().getClubsOfInterestOfUser(email);
        call.enqueue(new Callback<List<Club>>() {
            @Override
            public void onResponse(Call<List<Club>> call, Response<List<Club>> response) {
                if(response.isSuccessful()){
                    List<Club> clubs = response.body();
                    mClubofUserInterest.postValue(clubs);
                    Log.d(TAG, "onResponse: searchClubRelatedToUserInterests - " + clubs.size());
                } else {
                    Log.d(TAG, "onResponse: searchClubRelatedToUserInterests response error- " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Club>> call, Throwable t) {
                Log.d(TAG, "onResponse: searchClubRelatedToUserInterests onFailure- " + t.getMessage());
            }
        });
    }

    //re-do seach all club and joined club
    public void refreshClubSearch() {
        searchClubApi();
        searchJoinedClubApi(Constants.USER_EMAIL);
        searchClubRelatedToUserInterests(Constants.USER_EMAIL);
    }


}
