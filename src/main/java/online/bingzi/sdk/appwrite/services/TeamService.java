package online.bingzi.sdk.appwrite.services;

import online.bingzi.sdk.appwrite.models.Team;
import online.bingzi.sdk.appwrite.models.Membership;
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
     */
    @FormUrlEncoded
    @POST("teams")
    Call<Team> createTeam(
            @Field("teamId") String teamId,
            @Field("name") String name,
            @Field("roles") List<String> roles
    );

    /**
     * 获取团队列表
     */
    @GET("teams")
    Call<List<Team>> listTeams();

    /**
     * 获取团队信息
     */
    @GET("teams/{teamId}")
    Call<Team> getTeam(@Path("teamId") String teamId);

    /**
     * 更新团队信息
     */
    @FormUrlEncoded
    @PUT("teams/{teamId}")
    Call<Team> updateTeam(
            @Path("teamId") String teamId,
            @Field("name") String name
    );

    /**
     * 删除团队
     */
    @DELETE("teams/{teamId}")
    Call<Void> deleteTeam(@Path("teamId") String teamId);

    /**
     * 获取团队成员列表
     */
    @GET("teams/{teamId}/memberships")
    Call<List<Membership>> listMemberships(@Path("teamId") String teamId);

    /**
     * 创建团队成员
     */
    @FormUrlEncoded
    @POST("teams/{teamId}/memberships")
    Call<Membership> createMembership(
            @Path("teamId") String teamId,
            @Field("email") String email,
            @Field("roles") List<String> roles,
            @Field("url") String url
    );

    /**
     * 获取团队成员信息
     */
    @GET("teams/{teamId}/memberships/{membershipId}")
    Call<Membership> getMembership(
            @Path("teamId") String teamId,
            @Path("membershipId") String membershipId
    );

    /**
     * 更新团队成员信息
     */
    @FormUrlEncoded
    @PATCH("teams/{teamId}/memberships/{membershipId}")
    Call<Membership> updateMembership(
            @Path("teamId") String teamId,
            @Path("membershipId") String membershipId,
            @Field("roles") List<String> roles
    );

    /**
     * 删除团队成员
     */
    @DELETE("teams/{teamId}/memberships/{membershipId}")
    Call<Void> deleteMembership(
            @Path("teamId") String teamId,
            @Path("membershipId") String membershipId
    );

    /**
     * 更新团队偏好设置
     */
    @FormUrlEncoded
    @PUT("teams/{teamId}/prefs")
    Call<Map<String, Object>> updatePrefs(
            @Path("teamId") String teamId,
            @FieldMap Map<String, Object> prefs
    );
} 