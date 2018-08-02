package rs.hooloovoo.t_shirtbrodown.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/vote")
    @FormUrlEncoded
    Call<Vote> voteForColor(@Field("color") String title, @Header("Authorization") String authHeader);
}
