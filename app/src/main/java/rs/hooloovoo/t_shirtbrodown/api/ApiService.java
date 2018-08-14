package rs.hooloovoo.t_shirtbrodown.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/vote")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<Vote> voteForColor(@Body Vote vote, @Header("Authorization") String authHeader);
}
