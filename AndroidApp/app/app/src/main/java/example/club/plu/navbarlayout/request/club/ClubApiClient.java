package example.club.plu.navbarlayout.request.club;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import example.club.plu.navbarlayout.AppExecutors;
import example.club.plu.navbarlayout.model.club.Club;
import example.club.plu.navbarlayout.model.club.ClubUsers;
import example.club.plu.navbarlayout.model.club.Interests;
import example.club.plu.navbarlayout.request.ServiceGenerator;
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
    private RetrieveClubsRunnable mRetrieveClubRunnable; //runnable for clubs query

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

    //set background thread to get livedata
    public void searchClubApi() {
        //check if this has created in the past.
        //if so, set to null and create a new runnable
        if(mRetrieveClubRunnable != null){
            mRetrieveClubRunnable = null;
        }
        mRetrieveClubRunnable = new RetrieveClubsRunnable();
        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveClubRunnable);

        //set timeout
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //let the user know its timed out
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
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



    //Runnable class to query clubs from REST API
    private class RetrieveClubsRunnable implements Runnable{
        //to stop the request with timeout or user manually do so
        boolean cancelRequest;
        //constructor
        public RetrieveClubsRunnable() {
            this.cancelRequest = false;
        }
        //override runnable
        @Override
        public void run() {
            try {
                Response response = getClubs().execute();
                if(cancelRequest)
                    return;
                if(response.code() == 200) {
//                   List<Club> clubList = new ArrayList<>(((ClubSearchResponse)response.body()).getAllClubs());
                    List<Club> clubList = (List<Club>) response.body();
                   //set livedata on the background thread
                   mClubs.postValue(clubList);
                } else {
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: " + error);
                    mClubs.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mClubs.postValue(null);
            }

        }//run()
        //Server "clubs" query
        private Call<List<Club>> getClubs(){
            return ServiceGenerator.getClubApi().getClubs();
        }
        //method to cancel request
        private void cancelRequest() {
            Log.d(TAG, "cancelRequest: canceling the search request.");
            cancelRequest = true;
        }
    }
}
