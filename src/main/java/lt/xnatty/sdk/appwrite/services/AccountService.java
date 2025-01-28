package lt.xnatty.sdk.appwrite.services;

import java.util.Map;
import lt.xnatty.sdk.appwrite.models.User;
import retrofit2.Call;
import retrofit2.http.*;

public interface AccountService {

    @FormUrlEncoded
    @POST("account")
    Call<User> create(@Field("email") String email, @Field("password") String password, @Field("name") String name);

    @FormUrlEncoded
    @POST("account/sessions/email")
    Call<User> createEmailSession(@Field("email") String email, @Field("password") String password);

    @GET("account")
    Call<User> get();

    @GET("account/prefs")
    Call<Map<String, Object>> getPrefs();

    @PATCH("account/prefs")
    Call<Map<String, Object>> updatePrefs(@Body Map<String, Object> prefs);

    @FormUrlEncoded
    @POST("account/recovery")
    Call<Void> createRecovery(@Field("email") String email, @Field("url") String url);

    @FormUrlEncoded
    @PUT("account/recovery")
    Call<Void> updateRecovery(
            @Field("userId") String userId,
            @Field("secret") String secret,
            @Field("password") String password,
            @Field("passwordAgain") String passwordAgain);

    @DELETE("account/sessions/{sessionId}")
    Call<Void> deleteSession(@Path("sessionId") String sessionId);

    @DELETE("account/sessions")
    Call<Void> deleteSessions();
}
