package example.club.plu.navbarlayout.request.response;

import com.google.gson.annotations.Expose;

import java.util.List;

import example.club.plu.navbarlayout.model.Club;

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
