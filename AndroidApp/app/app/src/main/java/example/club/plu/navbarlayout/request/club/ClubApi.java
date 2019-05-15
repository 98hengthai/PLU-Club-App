package example.club.plu.navbarlayout.request.club;

import java.util.List;

import example.club.plu.navbarlayout.model.club.Club;
import example.club.plu.navbarlayout.model.club.ClubInterest;
import example.club.plu.navbarlayout.model.club.ClubUsers;
import example.club.plu.navbarlayout.model.club.Interests;
import example.club.plu.navbarlayout.model.club.UserInterest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @GET("clubs/{name}")
    Call<Club> getClubs(
            @Path("name") String clubName);


    @POST("/clubUsers")
    Call<ClubUsers> postClubUsers(
            @Body String body);

    @POST("/userInterests")
    Call<Interests> postUserInterest(
            @Body String body
    );


    @GET("/clubs/interestEmail/{email}")
    Call<List<Club>> getClubsOfInterestOfUser(
            @Path("email") String userEmail
    );
}
