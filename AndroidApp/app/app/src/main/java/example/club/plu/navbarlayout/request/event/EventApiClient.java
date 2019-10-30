package example.club.plu.navbarlayout.request.event;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

<<<<<<< HEAD
import example.club.plu.navbarlayout.utils.AppExecutors;
import example.club.plu.navbarlayout.model.event.Event;
import example.club.plu.navbarlayout.model.event.Home;
import example.club.plu.navbarlayout.model.event.UserEvent;
import example.club.plu.navbarlayout.ServiceGenerator;
=======
import example.club.plu.navbarlayout.AppExecutors;
import example.club.plu.navbarlayout.model.event.Event;
import example.club.plu.navbarlayout.model.event.Home;
import example.club.plu.navbarlayout.model.event.UserEvent;
import example.club.plu.navbarlayout.request.ServiceGenerator;
>>>>>>> d269bff646837bdb485999850b1097b0d0736da4
import example.club.plu.navbarlayout.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventApiClient {
    private static final String TAG = "EventApiClient";
    private static EventApiClient instance; //signleton
    private MutableLiveData<List<UserEvent>> mEvents; //livedata
    private MutableLiveData<List<Event>> aEvents; //all events
    private MutableLiveData<List<Home>> homeEvents; //home Events
    private EventApiClient.RetrieveEventsRunnable mRetrieveEventsRunnable; //runnable for events query
    private EventApiClient.RetrieveUserEventsRunnable mRetrieveUserEventsRunnable;
    private EventApiClient.RetrieveHomeRunnable mRetrieveHomeRunnable;

    //new
    private MutableLiveData<List<Event>> mUserEvents;
    private MutableLiveData<List<Event>> mUserTodayEvents;

    public static EventApiClient getInstance() {
        if(instance == null) {
            instance = new EventApiClient();
        }
        return instance;
    }

    public EventApiClient(){
        mEvents = new MutableLiveData<>();
        aEvents = new MutableLiveData<>();
        homeEvents = new MutableLiveData<>();
        //new
        mUserEvents = new MutableLiveData<>();
        mUserTodayEvents = new MutableLiveData<>();
    }

    public static Object createEvent(String toString) {
        instance.createEvent(toString);
        return null;
    }

    //getter for liveData
    public LiveData<List<UserEvent>> getEvents() {
        return mEvents;
    }
    public LiveData<List<Event>> getAllEvents(){
        return aEvents;
    }
    public LiveData<List<Home>> getHome() {
        return homeEvents;
    }

    //new
    public LiveData<List<Event>> getmUserEvents() {
        return mUserEvents;
    }
    public LiveData<List<Event>> getmUserTodayEvents() {
        return mUserTodayEvents;
    }


    //new
    //search all events given user email
    public void searchUserEvent(String email){
        Call<List<Event>> call = ServiceGenerator.getEventApi().getUserEvents(email);
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(response.isSuccessful()){
                   mUserEvents.postValue(response.body());
                    Log.d(TAG, "onResponse: searchUserEvent - " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

            }
        });
    }

    //seach today event given email
    public void searchTodayEvent(String email) {
        Call<List<Event>> call = ServiceGenerator.getEventApi().searchTodayEvent(email);
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(response.isSuccessful()){
                    List<Event> eventList = response.body();
                    mUserTodayEvents.postValue(eventList);
                    Log.d(TAG, "onResponse: searchTodayEvent - " + eventList.size());
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Log.e(TAG, "onFailure: searchTodayEvent - " + t.getMessage());

            }
        });

    }







    public void searchsHomeApi() {
        if(mRetrieveHomeRunnable != null){
            mRetrieveHomeRunnable = null;
        }
        mRetrieveHomeRunnable = new RetrieveHomeRunnable();
        final Future hHandler = AppExecutors.getInstance().networkIO().submit(mRetrieveHomeRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {

            @Override
            public void run() {
                hHandler.cancel(true);
            }
        }, Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
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

    public void searchsUserEventsApi() {
        if(mRetrieveUserEventsRunnable != null)
            mRetrieveUserEventsRunnable = null;

        mRetrieveUserEventsRunnable = new EventApiClient.RetrieveUserEventsRunnable();
        final Future uHandler = AppExecutors.getInstance().networkIO().submit(mRetrieveUserEventsRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable(){
            @Override
            public void run(){
                uHandler.cancel(true);
            }
        }, Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);

   }




    //Runnable class to query events from REST API
    private class RetrieveUserEventsRunnable implements Runnable{
        //to stop the request with timeout or user manually do so
        boolean cancelRequest;
        //constructor
        public RetrieveUserEventsRunnable() {
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
                    List<UserEvent> eventList = (List<UserEvent>) response.body();
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
        private Call<List<UserEvent>> getEvents(){
            return ServiceGenerator.getEventApi().getEvents();
        }


        //method to cancel request
        private void cancelRequest() {
            Log.d(TAG, "cancelRequest: canceling the search request.");
            cancelRequest = true;
        }

    }

    private class RetrieveEventsRunnable implements Runnable {
        boolean cancelRequest;

        public RetrieveEventsRunnable() {
            this.cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getAllEvents().execute();
                if (cancelRequest)
                    return;
                if (response.code() == 200) {
                    List<Event> eventList = (List<Event>) response.body();
                    //set livedata on the background thread
                    aEvents.postValue(eventList);
                } else {
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: " + error);
                    aEvents.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                aEvents.postValue(null);
            }

        }//run()

        //Server "events" query
        private Call<List<Event>> getAllEvents() {
            return ServiceGenerator.getEventApi().getAllEvents();
        }


        //method to cancel request
        private void cancelRequest() {
            Log.d(TAG, "cancelRequest: canceling the search request.");
            cancelRequest = true;
        }
    }

    private class RetrieveHomeRunnable implements Runnable{
            boolean cancelRequest;

            public RetrieveHomeRunnable(){ this.cancelRequest = false; }

        @Override
        public void run() {
            try{
                Response response = getHome().execute();
                if(cancelRequest)
                    return;
                if(response.code() == 200) {
                    List<Home> homeList = (List<Home>) response.body();
                    homeEvents.postValue(homeList);
                }else{
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: " + error);
                    homeEvents.postValue(null);
                }
            }catch(IOException e) {
                e.printStackTrace();
                homeEvents.postValue(null);
            }
        }

        private void cancelRequest() {
                Log.d(TAG, "cancelRequest: cancelling the search request.");
                cancelRequest = true;
        }

        private Call<List<Home>> getHome(){
                return ServiceGenerator.getEventApi().getHome();
        }


    }


}



