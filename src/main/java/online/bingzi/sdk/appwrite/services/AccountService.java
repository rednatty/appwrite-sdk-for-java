package online.bingzi.sdk.appwrite.services;

import online.bingzi.sdk.appwrite.models.User;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

/**
 * Appwrite账户服务接口
 */
public interface AccountService {
    /**
     * 创建新账户
     *
     * @param email    邮箱
     * @param password 密码
     * @param name     用户名（可选）
     * @return 用户信息
     */
    @POST("account")
    Call<User> create(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name
    );

    /**
     * 使用邮箱和密码创建会话（登录）
     *
     * @param email    邮箱
     * @param password 密码
     * @return 用户信息
     */
    @POST("account/sessions/email")
    @FormUrlEncoded
    Call<User> createEmailSession(
            @Field("email") String email,
            @Field("password") String password
    );

    /**
     * 获取当前会话的账户信息
     *
     * @return 用户信息
     */
    @GET("account")
    Call<User> get();

    /**
     * 获取账户偏好设置
     *
     * @return 偏好设置
     */
    @GET("account/prefs")
    Call<Map<String, Object>> getPrefs();

    /**
     * 更新账户偏好设置
     *
     * @param prefs 新的偏好设置
     * @return 更新后的偏好设置
     */
    @PATCH("account/prefs")
    Call<Map<String, Object>> updatePrefs(@Body Map<String, Object> prefs);

    /**
     * 创建密码重置链接
     *
     * @param email 邮箱
     * @return 空响应
     */
    @POST("account/recovery")
    @FormUrlEncoded
    Call<Void> createRecovery(@Field("email") String email);

    /**
     * 确认密码重置
     *
     * @param userId          用户ID
     * @param secret         重置密钥
     * @param password       新密码
     * @param passwordAgain  确认新密码
     * @return 空响应
     */
    @PUT("account/recovery")
    @FormUrlEncoded
    Call<Void> updateRecovery(
            @Field("userId") String userId,
            @Field("secret") String secret,
            @Field("password") String password,
            @Field("passwordAgain") String passwordAgain
    );

    /**
     * 删除当前会话
     *
     * @return 空响应
     */
    @DELETE("account/sessions/current")
    Call<Void> deleteSession();

    /**
     * 删除所有会话
     *
     * @return 空响应
     */
    @DELETE("account/sessions")
    Call<Void> deleteSessions();
} 