package example.club.plu.navbarlayout.request.setting;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;


import retrofit2.*;
import example.club.plu.navbarlayout.model.user.User;
import example.club.plu.navbarlayout.request.ServiceGenerator;

public class SettingApiClient {
    private static final String TAG = "SettingApiClient";
    private static  SettingApiClient instance;
    private MutableLiveData<User> mUser;

    public static SettingApiClient getInstance() {
        if(instance==null){
            instance = new SettingApiClient();
        }
        return instance;
    }

    private SettingApiClient(){
        mUser = new MutableLiveData<>();
    }

    public MutableLiveData<User> getmUser() {
        return mUser;
    }


    @Override
    public String toString() {
        return "SettingApiClient{" +
                "mUser=" + mUser +
                '}';
    }

    public void getUserInfoGivenEmail(String userEmail){
        Call<User> call = ServiceGenerator.getSettingApi().getUserGivenEmail(userEmail);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    User user = response.body();
                    mUser.postValue(user);
                    Log.d(TAG, "onResponse: user - " + mUser.toString());
                } else {
                    Log.e(TAG, "onResponse: " + response.errorBody().toString() );
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage() );
            }
        });

    }
}

