package example.club.plu.navbarlayout.request.club;

import java.util.List;

import example.club.plu.navbarlayout.model.club.Club;
import example.club.plu.navbarlayout.model.club.ClubUsers;
import example.club.plu.navbarlayout.model.club.Interests;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ClubApi {
    //get all clubs
    @GET("clubs")
    Call<List<Club>> getClubs();

    @GET("clubUsers/userEmail/{email}")
    Call<List<ClubUsers>> getAllClubsGivenUserEmail(
           @Path("email") String userEmail
    );

    @GET("interests")
    Call<List<Interests>> getAllInterests();

}
