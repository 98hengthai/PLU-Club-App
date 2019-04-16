package example.club.plu.navbarlayout.request;

import example.club.plu.navbarlayout.model.Club;
import example.club.plu.navbarlayout.utils.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    //create a connection to the server using Gson converter by retrofit
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(Constants.SERVER_URL).addConverterFactory(GsonConverterFactory.create());
    //instantiation Retrofit
    private static Retrofit retrofit = retrofitBuilder.build();

    //Instantiation of ClubApi
    private static ClubApi clubApi = retrofit.create(ClubApi.class);
    //return the ClubApi object
    public static  ClubApi getClubApi() { return clubApi; }

<<<<<<< HEAD
    //instantiation of EventApi
    private static EventApi eventApi = retrofit.create(EventApi.class);
    //return the EventApi object
    public static EventApi getEventApi(){
        return eventApi;
    }
=======
//    //instantiation of EventApi
//    private static EventApi eventApi = retrofit.create(EventApi.class);
//    //return the EventApi object
//    public static EventApi getEventApi(){
//        return eventApi;
//    }
>>>>>>> cb198f05b50fced86c34d98e20dd279187f8455d

}
