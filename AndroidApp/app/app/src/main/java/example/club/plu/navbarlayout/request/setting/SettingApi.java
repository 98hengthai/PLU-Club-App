package example.club.plu.navbarlayout.request.setting;

import example.club.plu.navbarlayout.model.user.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SettingApi {

    //get user given email
    @GET("user/email/{email}")
    Call<User> getUserGivenEmail(
            @Path("email") String userEmail
    );
}
