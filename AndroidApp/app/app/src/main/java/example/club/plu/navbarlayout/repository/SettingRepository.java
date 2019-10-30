package example.club.plu.navbarlayout.repository;

import example.club.plu.navbarlayout.model.user.User;
import example.club.plu.navbarlayout.request.setting.SettingApiClient;

public class SettingRepository {
    private static SettingRepository instance;
    private SettingApiClient mSettingApiClient;

    public static SettingRepository getInstance(){
        if(instance == null)
            instance = new SettingRepository();
        return instance;
    }

    private SettingRepository() { mSettingApiClient = SettingApiClient.getInstance();
    }

    public void getUserGivenEmail(String email){
        mSettingApiClient.getUserInfoGivenEmail(email);
    }
}
