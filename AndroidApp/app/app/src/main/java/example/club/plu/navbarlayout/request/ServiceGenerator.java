package example.club.plu.navbarlayout.request;

import example.club.plu.navbarlayout.request.club.ClubApi;
import example.club.plu.navbarlayout.request.event.EventApi;
import example.club.plu.navbarlayout.request.setting.SettingApi;
import example.club.plu.navbarlayout.utils.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceGenerator {
    //create a connection to the server using Gson converter by retrofit
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(Constants.Phone_SERVER_URL).addConverterFactory(GsonConverterFactory.create());
    //instantiation Retrofit
    private static Retrofit retrofit = retrofitBuilder.build();

    //Instantiation of ClubApi
    private static ClubApi clubApi = retrofit.create(ClubApi.class);
    //return the ClubApi object
    public static  ClubApi getClubApi() { return clubApi; }

    //instantiation of EventApi
    private static EventApi eventApi = retrofit.create(EventApi.class);
    //return the EventApi object
    public static EventApi getEventApi(){
        return eventApi;
    }

    //instantiation of Setting
    private static SettingApi settingApi = retrofit.create(SettingApi.class);
    //return the EventApi object
    public static SettingApi getSettingApi(){
        return settingApi;
    }



}
