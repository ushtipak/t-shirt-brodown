package rs.hooloovoo.t_shirtbrodown.api;

public class ApiUtils {
    private static final String BASE_URL = "http://pijupiju.com:7835/";

    private ApiUtils() {
    }

    public static ApiService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
