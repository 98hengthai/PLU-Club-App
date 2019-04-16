package example.club.plu.navbarlayout.request.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import example.club.plu.navbarlayout.model.Club;
import example.club.plu.navbarlayout.model.Event;

public class ClubSearchResponse {
    @Expose()
    private List<Club> clubs;

    public List<Club> getAllClubs() { return  clubs;}

    @Override
    public String toString() {
        return "ClubSearchResponse{" +
                "clubs=" + clubs +
                '}';
    }
}
