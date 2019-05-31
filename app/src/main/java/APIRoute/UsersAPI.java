package APIRoute;

import Model.Users;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UsersAPI {
    @POST("adduser")
    Call<Void> addUsers(@Body Users users);

    @FormUrlEncoded
    @POST("login")
    Call<String> checkUSer(@Field("username") String username,@Field("password") String password);



}
