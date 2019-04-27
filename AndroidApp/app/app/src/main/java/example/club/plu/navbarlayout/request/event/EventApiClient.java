package example.club.plu.navbarlayout.request.event;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import example.club.plu.navbarlayout.AppExecutors;
import example.club.plu.navbarlayout.model.event.Event;
import example.club.plu.navbarlayout.request.ServiceGenerator;
import example.club.plu.navbarlayout.utils.Constants;
import retrofit2.Call;
import retrofit2.Response;

public class EventApiClient {
    private static final String TAG = "EventApiClient";
    private static EventApiClient instance; //signleton
    private MutableLiveData<List<Event>> mEvents; //livedata
    private EventApiClient.RetrieveEventsRunnable mRetrieveEventsRunnable; //runnable for events query

    public static EventApiClient getInstance() {
        if(instance == null) {
            instance = new EventApiClient();
        }
        return instance;
    }

    private EventApiClient(){
        mEvents = new MutableLiveData<>();
    }
    //getter for liveData
    public LiveData<List<Event>> getEvents() {
        return mEvents;
    }

    //set background thread to get livedata
    public void searchsEventApi() {
        //check if this has created in the past.
        //if so, set to null and create a new runnable
        if(mRetrieveEventsRunnable != null){
            mRetrieveEventsRunnable = null;
        }
        mRetrieveEventsRunnable = new EventApiClient.RetrieveEventsRunnable();
        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveEventsRunnable);

        //set timeout
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //let the user know its timed out
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    //Runnable class to query events from REST API
    private class RetrieveEventsRunnable implements Runnable{
        //to stop the request with timeout or user manually do so
        boolean cancelRequest;
        //constructor
        public RetrieveEventsRunnable() {
            this.cancelRequest = false;
        }
        //override runnable
        @Override
        public void run() {
            try {
                Response response = getEvents().execute();
                if(cancelRequest)
                    return;
                if(response.code() == 200) {
//                   List<Event> eventList = new ArrayList<>(((EventSearchResponse)response.body()).getAllEvents());
                    List<Event> eventList = (List<Event>) response.body();
                    //set livedata on the background thread
                    mEvents.postValue(eventList);
                } else {
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: " + error);
                    mEvents.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mEvents.postValue(null);
            }

        }//run()
        //Server "events" query
        private Call<List<Event>> getEvents(){
            return ServiceGenerator.getEventApi().getEvents();
        }
        //method to cancel request
        private void cancelRequest() {
            Log.d(TAG, "cancelRequest: canceling the search request.");
            cancelRequest = true;
        }
    }
}

