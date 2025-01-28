package lt.xnatty.sdk.appwrite.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lt.xnatty.sdk.appwrite.BaseTest;
import lt.xnatty.sdk.appwrite.models.Membership;
import lt.xnatty.sdk.appwrite.models.Team;
import lt.xnatty.sdk.appwrite.services.impl.TeamServiceImpl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

class TeamServiceTest extends BaseTest {
    private TeamService teamService;

    @BeforeEach
    void init() {
        teamService = new TeamServiceImpl(client);
    }

    @Test
    void createTeam() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody(loadJsonFromResource("team"))
                .addHeader("Content-Type", "application/json"));
        Response<Team> response = teamService
                .createTeam("test-team", "Development Team", Arrays.asList("admin", "developer"))
                .execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals("/v1/teams", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("test-team"));
        assertTrue(body.contains("Development%20Team"));
        assertTrue(body.contains("admin"));
        assertTrue(body.contains("developer"));
        assertTrue(response.isSuccessful());
        Team team = response.body();
        assertNotNull(team);
        assertEquals("5e5ea5c16897e", team.getId());
        assertEquals("Development Team", team.getName());
        assertEquals(10, team.getTotal());
    }

    @Test
    void listTeams() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("[" + loadJsonFromResource("team") + "]")
                .addHeader("Content-Type", "application/json"));
        Response<List<Team>> response = teamService.listTeams().execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/teams", request.getPath());
        assertTrue(response.isSuccessful());
        List<Team> teams = response.body();
        assertNotNull(teams);
        assertEquals(1, teams.size());
        Team team = teams.get(0);
        assertEquals("5e5ea5c16897e", team.getId());
        assertEquals("Development Team", team.getName());
    }

    @Test
    void getTeam() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("team"))
                .addHeader("Content-Type", "application/json"));
        Response<Team> response = teamService.getTeam("test-team").execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/teams/test-team", request.getPath());
        assertTrue(response.isSuccessful());
        Team team = response.body();
        assertNotNull(team);
        assertEquals("5e5ea5c16897e", team.getId());
        assertEquals("Development Team", team.getName());
    }

    @Test
    void updateTeam() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("team"))
                .addHeader("Content-Type", "application/json"));
        Response<Team> response =
                teamService.updateTeam("test-team", "Updated Team Name").execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("PUT", request.getMethod());
        assertEquals("/v1/teams/test-team", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("Updated%20Team%20Name"));
        assertTrue(response.isSuccessful());
        Team team = response.body();
        assertNotNull(team);
        assertEquals("5e5ea5c16897e", team.getId());
    }

    @Test
    void deleteTeam() throws Exception {
        mockWebServer.enqueue(new MockResponse().setResponseCode(204));
        Response<Void> response = teamService.deleteTeam("test-team").execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("DELETE", request.getMethod());
        assertEquals("/v1/teams/test-team", request.getPath());
        assertTrue(response.isSuccessful());
    }

    @Test
    void listMemberships() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("[" + loadJsonFromResource("membership") + "]")
                .addHeader("Content-Type", "application/json"));
        Response<List<Membership>> response =
                teamService.listMemberships("test-team").execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/teams/test-team/memberships", request.getPath());
        assertTrue(response.isSuccessful());
        List<Membership> memberships = response.body();
        assertNotNull(memberships);
        assertEquals(1, memberships.size());
        Membership membership = memberships.get(0);
        assertEquals("5e5ea5c16897f", membership.getId());
        assertEquals("John Doe", membership.getUserName());
    }

    @Test
    void createMembership() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody(loadJsonFromResource("membership"))
                .addHeader("Content-Type", "application/json"));
        Response<Membership> response = teamService
                .createMembership(
                        "test-team", "john@example.com", Arrays.asList("developer"), "https://example.com/invite")
                .execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals("/v1/teams/test-team/memberships", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("example.com"));
        assertTrue(body.contains("developer"));
        assertTrue(body.contains("invite"));
        assertTrue(response.isSuccessful());
        Membership membership = response.body();
        assertNotNull(membership);
        assertEquals("5e5ea5c16897f", membership.getId());
        assertEquals("John Doe", membership.getUserName());
    }

    @Test
    void getMembership() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("membership"))
                .addHeader("Content-Type", "application/json"));
        Response<Membership> response =
                teamService.getMembership("test-team", "test-membership").execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/teams/test-team/memberships/test-membership", request.getPath());
        assertTrue(response.isSuccessful());
        Membership membership = response.body();
        assertNotNull(membership);
        assertEquals("5e5ea5c16897f", membership.getId());
        assertEquals("John Doe", membership.getUserName());
    }

    @Test
    void updateMembership() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("membership"))
                .addHeader("Content-Type", "application/json"));
        Response<Membership> response = teamService
                .updateMembership("test-team", "test-membership", Arrays.asList("admin", "developer"))
                .execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("PATCH", request.getMethod());
        assertEquals("/v1/teams/test-team/memberships/test-membership", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("admin"));
        assertTrue(body.contains("developer"));
        assertTrue(response.isSuccessful());
        Membership membership = response.body();
        assertNotNull(membership);
        assertEquals("5e5ea5c16897f", membership.getId());
    }

    @Test
    void deleteMembership() throws Exception {
        mockWebServer.enqueue(new MockResponse().setResponseCode(204));
        Response<Void> response =
                teamService.deleteMembership("test-team", "test-membership").execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("DELETE", request.getMethod());
        assertEquals("/v1/teams/test-team/memberships/test-membership", request.getPath());
        assertTrue(response.isSuccessful());
    }

    @Test
    void updatePrefs() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"maxMembers\": 100, \"allowInvites\": true}")
                .addHeader("Content-Type", "application/json"));
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("maxMembers", 100);
        prefs.put("allowInvites", true);
        Response<Map<String, Object>> response =
                teamService.updatePrefs("test-team", prefs).execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("PUT", request.getMethod());
        assertEquals("/v1/teams/test-team/prefs", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("100"));
        assertTrue(body.contains("true"));
        assertTrue(response.isSuccessful());
        Map<String, Object> responsePrefs = response.body();
        assertNotNull(responsePrefs);
        assertEquals(100.0, responsePrefs.get("maxMembers"));
        assertEquals(true, responsePrefs.get("allowInvites"));
    }
}
