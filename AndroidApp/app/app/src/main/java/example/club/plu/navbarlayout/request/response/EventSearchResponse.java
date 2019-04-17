package example.club.plu.navbarlayout.request.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import example.club.plu.navbarlayout.model.Club;
import example.club.plu.navbarlayout.model.Event;

public class EventSearchResponse {
    @Expose()
    private List<Event> events;

    public List<Event> getAllEvents() { return  events;}

    @Override
    public String toString() {
        return "EventSearchResponse{" +
                "events=" + events +
                '}';
    }
}
