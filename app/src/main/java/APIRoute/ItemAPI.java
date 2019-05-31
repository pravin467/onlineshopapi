package APIRoute;

import java.util.List;

import Model.Items;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ItemAPI {

    @Multipart
    @POST("upload")
    Call <String> uploadImage(@Part MultipartBody.Part body);

    @POST("additem")
    Call<Void> additem(@Body Items items);

    @GET("showitems")
    Call<List<Items>> getallItems();
}
