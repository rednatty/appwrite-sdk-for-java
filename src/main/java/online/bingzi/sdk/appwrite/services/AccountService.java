package online.bingzi.sdk.appwrite.services;

import online.bingzi.sdk.appwrite.models.User;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

/**
 * 账户服务接口
 */
public interface AccountService {
    /**
     * 创建新用户
     */
    @FormUrlEncoded
    @POST("account")
    Call<User> create(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name
    );

    /**
     * 创建邮箱登录会话
     */
    @FormUrlEncoded
    @POST("account/sessions/email")
    Call<User> createEmailSession(
            @Field("email") String email,
            @Field("password") String password
    );

    /**
     * 获取当前账户信息
     */
    @GET("account")
    Call<User> get();

    /**
     * 获取账户偏好设置
     */
    @GET("account/prefs")
    Call<Map<String, Object>> getPrefs();

    /**
     * 更新账户偏好设置
     */
    @PATCH("account/prefs")
    Call<Map<String, Object>> updatePrefs(@Body Map<String, Object> prefs);

    /**
     * 创建密码重置请求
     */
    @FormUrlEncoded
    @POST("account/recovery")
    Call<Void> createRecovery(
            @Field("email") String email,
            @Field("url") String url
    );

    /**
     * 确认密码重置
     */
    @FormUrlEncoded
    @PUT("account/recovery")
    Call<Void> updateRecovery(
            @Field("userId") String userId,
            @Field("secret") String secret,
            @Field("password") String password,
            @Field("passwordAgain") String passwordAgain
    );

    /**
     * 删除指定会话
     */
    @DELETE("account/sessions/{sessionId}")
    Call<Void> deleteSession(@Path("sessionId") String sessionId);

    /**
     * 删除所有会话
     */
    @DELETE("account/sessions")
    Call<Void> deleteSessions();
} 