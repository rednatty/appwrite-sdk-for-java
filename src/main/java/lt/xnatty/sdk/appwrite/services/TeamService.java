package lt.xnatty.sdk.appwrite.services;

import java.util.List;
import java.util.Map;
import lt.xnatty.sdk.appwrite.models.Membership;
import lt.xnatty.sdk.appwrite.models.Team;
import retrofit2.Call;
import retrofit2.http.*;

public interface TeamService {

    @FormUrlEncoded
    @POST("teams")
    Call<Team> createTeam(
            @Field("teamId") String teamId, @Field("name") String name, @Field("roles") List<String> roles);

    @GET("teams")
    Call<List<Team>> listTeams();

    @GET("teams/{teamId}")
    Call<Team> getTeam(@Path("teamId") String teamId);

    @FormUrlEncoded
    @PUT("teams/{teamId}")
    Call<Team> updateTeam(@Path("teamId") String teamId, @Field("name") String name);

    @DELETE("teams/{teamId}")
    Call<Void> deleteTeam(@Path("teamId") String teamId);

    @GET("teams/{teamId}/memberships")
    Call<List<Membership>> listMemberships(@Path("teamId") String teamId);

    @FormUrlEncoded
    @POST("teams/{teamId}/memberships")
    Call<Membership> createMembership(
            @Path("teamId") String teamId,
            @Field("email") String email,
            @Field("roles") List<String> roles,
            @Field("url") String url);

    @GET("teams/{teamId}/memberships/{membershipId}")
    Call<Membership> getMembership(@Path("teamId") String teamId, @Path("membershipId") String membershipId);

    @FormUrlEncoded
    @PATCH("teams/{teamId}/memberships/{membershipId}")
    Call<Membership> updateMembership(
            @Path("teamId") String teamId,
            @Path("membershipId") String membershipId,
            @Field("roles") List<String> roles);

    @DELETE("teams/{teamId}/memberships/{membershipId}")
    Call<Void> deleteMembership(@Path("teamId") String teamId, @Path("membershipId") String membershipId);

    @FormUrlEncoded
    @PUT("teams/{teamId}/prefs")
    Call<Map<String, Object>> updatePrefs(@Path("teamId") String teamId, @FieldMap Map<String, Object> prefs);
}
