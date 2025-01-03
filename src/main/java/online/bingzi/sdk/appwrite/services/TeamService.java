package online.bingzi.sdk.appwrite.services;

import online.bingzi.sdk.appwrite.models.Membership;
import online.bingzi.sdk.appwrite.models.Team;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

/**
 * 团队服务接口
 */
public interface TeamService {
    /**
     * 创建团队
     *
     * @param name       团队名称
     * @param id         团队ID（可选）
     * @param roles      角色列表（可选）
     * @return 团队信息
     */
    @POST("teams")
    @FormUrlEncoded
    Call<Team> createTeam(
            @Field("name") String name,
            @Field("teamId") String id,
            @Field("roles") List<String> roles
    );

    /**
     * 获取团队列表
     *
     * @return 团队列表
     */
    @GET("teams")
    Call<List<Team>> listTeams();

    /**
     * 获取团队信息
     *
     * @param teamId 团队ID
     * @return 团队信息
     */
    @GET("teams/{teamId}")
    Call<Team> getTeam(@Path("teamId") String teamId);

    /**
     * 更新团队
     *
     * @param teamId 团队ID
     * @param name   团队名称
     * @return 团队信息
     */
    @PUT("teams/{teamId}")
    @FormUrlEncoded
    Call<Team> updateTeam(
            @Path("teamId") String teamId,
            @Field("name") String name
    );

    /**
     * 删除团队
     *
     * @param teamId 团队ID
     * @return 空响应
     */
    @DELETE("teams/{teamId}")
    Call<Void> deleteTeam(@Path("teamId") String teamId);

    /**
     * 获取团队成员列表
     *
     * @param teamId 团队ID
     * @return 成员列表
     */
    @GET("teams/{teamId}/memberships")
    Call<List<Membership>> listMemberships(@Path("teamId") String teamId);

    /**
     * 创建团队成员
     *
     * @param teamId    团队ID
     * @param email     成员邮箱
     * @param roles     角色列表
     * @param url       邀请链接（可选）
     * @param name      成员名称（可选）
     * @return 成员信息
     */
    @POST("teams/{teamId}/memberships")
    @FormUrlEncoded
    Call<Membership> createMembership(
            @Path("teamId") String teamId,
            @Field("email") String email,
            @Field("roles") List<String> roles,
            @Field("url") String url,
            @Field("name") String name
    );

    /**
     * 获取团队成员信息
     *
     * @param teamId       团队ID
     * @param membershipId 成员ID
     * @return 成员信息
     */
    @GET("teams/{teamId}/memberships/{membershipId}")
    Call<Membership> getMembership(
            @Path("teamId") String teamId,
            @Path("membershipId") String membershipId
    );

    /**
     * 更新团队成员
     *
     * @param teamId       团队ID
     * @param membershipId 成员ID
     * @param roles        角色列表
     * @return 成员信息
     */
    @PATCH("teams/{teamId}/memberships/{membershipId}")
    @FormUrlEncoded
    Call<Membership> updateMembership(
            @Path("teamId") String teamId,
            @Path("membershipId") String membershipId,
            @Field("roles") List<String> roles
    );

    /**
     * 删除团队成员
     *
     * @param teamId       团队ID
     * @param membershipId 成员ID
     * @return 空响应
     */
    @DELETE("teams/{teamId}/memberships/{membershipId}")
    Call<Void> deleteMembership(
            @Path("teamId") String teamId,
            @Path("membershipId") String membershipId
    );

    /**
     * 更新团队成员资格状态
     *
     * @param teamId       团队ID
     * @param membershipId 成员ID
     * @param userId       用户ID
     * @param secret      密钥
     * @return 成员信息
     */
    @PATCH("teams/{teamId}/memberships/{membershipId}/status")
    @FormUrlEncoded
    Call<Membership> updateMembershipStatus(
            @Path("teamId") String teamId,
            @Path("membershipId") String membershipId,
            @Field("userId") String userId,
            @Field("secret") String secret
    );

    /**
     * 获取团队偏好设置
     *
     * @param teamId 团队ID
     * @return 偏好设置
     */
    @GET("teams/{teamId}/prefs")
    Call<Map<String, Object>> getPrefs(@Path("teamId") String teamId);

    /**
     * 更新团队偏好设置
     *
     * @param teamId 团队ID
     * @param prefs  偏好设置
     * @return 偏好设置
     */
    @PUT("teams/{teamId}/prefs")
    Call<Map<String, Object>> updatePrefs(
            @Path("teamId") String teamId,
            @Body Map<String, Object> prefs
    );
} 