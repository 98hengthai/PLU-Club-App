package example.club.plu.navbarlayout.request;

import java.util.List;

import example.club.plu.navbarlayout.model.Club;
import example.club.plu.navbarlayout.request.response.ClubSearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ClubApi {
    //get all clubs
    @GET("clubs")
    Call<List<Club>> getClubs();

    @GET("clubs")
    Call<ClubSearchResponse> searchClubs();
}
