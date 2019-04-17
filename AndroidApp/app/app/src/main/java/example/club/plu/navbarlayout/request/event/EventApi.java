package example.club.plu.navbarlayout.request.event;

import java.util.List;

import example.club.plu.navbarlayout.model.Event;
import example.club.plu.navbarlayout.request.response.EventSearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface EventApi {
    //get all events
    @GET("event")
    Call<List<Event>> getEvents();

    @GET("event")
    Call<EventSearchResponse> searchEvents();
}
