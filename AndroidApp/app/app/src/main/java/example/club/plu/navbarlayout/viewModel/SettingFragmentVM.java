package example.club.plu.navbarlayout.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import example.club.plu.navbarlayout.model.user.User;
import example.club.plu.navbarlayout.request.setting.SettingApiClient;

public class SettingFragmentVM extends ViewModel {

    private SettingApiClient mSettingApiClient;

    public SettingFragmentVM() {
        mSettingApiClient = SettingApiClient.getInstance();
    }

    //get method
    public MutableLiveData<User> getUser() {
        return mSettingApiClient.getmUser();
    }

    //call
    public void getUserGivenEmail(String email){
        mSettingApiClient.getUserInfoGivenEmail(email);
    }





}
