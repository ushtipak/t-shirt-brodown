package rs.hooloovoo.t_shirtbrodown.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @POST("/vote")
    @FormUrlEncoded
    Call<Post> voteForColor(@Field("color") String title);
}
