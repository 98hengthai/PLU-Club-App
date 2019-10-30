package example.club.plu.navbarlayout.request.event;

import java.util.List;

import example.club.plu.navbarlayout.model.event.Event;
import example.club.plu.navbarlayout.model.event.Home;
import example.club.plu.navbarlayout.model.event.UserEvent;
import example.club.plu.navbarlayout.utils.Constants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EventApi {
    @GET("event/user/{email}")
    Call<List<Event>> getUserEvents(
            @Path("email") String userEmail
    );


    //get all events
    @GET("userEvents/userEmail/" + Constants.testEmail)
    Call<List<UserEvent>> getEvents();

    @GET("event")
    Call<List<Event>> getAllEvents();

    @POST("event")
    Call<Event> createEvent(@Body String body);

    @POST("userEvents")
    Call<UserEvent> eventResponse(@Body String body);

    @GET("homePageEvents/" + Constants.testEmail)
    Call<List<Home>> getHome();

    @GET("/homePageEvents/{email}")
    Call<List<Event>> searchTodayEvent(
            @Path("email") String email
    );
}
