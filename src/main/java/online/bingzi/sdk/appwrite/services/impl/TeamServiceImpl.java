package online.bingzi.sdk.appwrite.services.impl;

import online.bingzi.sdk.appwrite.Client;
import online.bingzi.sdk.appwrite.models.Membership;
import online.bingzi.sdk.appwrite.models.Team;
import online.bingzi.sdk.appwrite.services.TeamService;
import retrofit2.Call;

import java.util.List;
import java.util.Map;

/**
 * 团队服务实现类
 */
public class TeamServiceImpl implements TeamService {
    private final TeamService teamService;

    public TeamServiceImpl(Client client) {
        this.teamService = client.createService(TeamService.class);
    }

    @Override
    public Call<Team> createTeam(String name, String id, List<String> roles) {
        return teamService.createTeam(name, id, roles);
    }

    @Override
    public Call<List<Team>> listTeams() {
        return teamService.listTeams();
    }

    @Override
    public Call<Team> getTeam(String teamId) {
        return teamService.getTeam(teamId);
    }

    @Override
    public Call<Team> updateTeam(String teamId, String name) {
        return teamService.updateTeam(teamId, name);
    }

    @Override
    public Call<Void> deleteTeam(String teamId) {
        return teamService.deleteTeam(teamId);
    }

    @Override
    public Call<List<Membership>> listMemberships(String teamId) {
        return teamService.listMemberships(teamId);
    }

    @Override
    public Call<Membership> createMembership(String teamId, String email,
                                           List<String> roles, String url, String name) {
        return teamService.createMembership(teamId, email, roles, url, name);
    }

    @Override
    public Call<Membership> getMembership(String teamId, String membershipId) {
        return teamService.getMembership(teamId, membershipId);
    }

    @Override
    public Call<Membership> updateMembership(String teamId, String membershipId,
                                           List<String> roles) {
        return teamService.updateMembership(teamId, membershipId, roles);
    }

    @Override
    public Call<Void> deleteMembership(String teamId, String membershipId) {
        return teamService.deleteMembership(teamId, membershipId);
    }

    @Override
    public Call<Membership> updateMembershipStatus(String teamId, String membershipId,
                                                 String userId, String secret) {
        return teamService.updateMembershipStatus(teamId, membershipId, userId, secret);
    }

    @Override
    public Call<Map<String, Object>> getPrefs(String teamId) {
        return teamService.getPrefs(teamId);
    }

    @Override
    public Call<Map<String, Object>> updatePrefs(String teamId, Map<String, Object> prefs) {
        return teamService.updatePrefs(teamId, prefs);
    }
} 