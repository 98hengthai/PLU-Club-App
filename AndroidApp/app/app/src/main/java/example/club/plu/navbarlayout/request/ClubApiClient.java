package example.club.plu.navbarlayout.request;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import example.club.plu.navbarlayout.AppExecutors;
import example.club.plu.navbarlayout.model.Club;
import example.club.plu.navbarlayout.utils.Constants;
import retrofit2.Call;
import retrofit2.Response;

public class ClubApiClient {
    private static final String TAG = "ClubApiClient";
    private static ClubApiClient instance; //signleton
    private MutableLiveData<List<Club>> mClubs; //livedata
    private RetrieveClubsRunnable mRetrieveClubRunnable; //runnable for clubs query

    public static ClubApiClient getInstance() {
        if(instance == null) {
            instance = new ClubApiClient();
        }
        return instance;
    }

    private ClubApiClient(){
        mClubs = new MutableLiveData<>();
    }
    //getter for liveData
    public LiveData<List<Club>> getClubs() {
        return mClubs;
    }

    //set background thread to get livedata
    public void searchsClubApi() {
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
