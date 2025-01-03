package online.bingzi.sdk.appwrite.services;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import online.bingzi.sdk.appwrite.BaseTest;
import online.bingzi.sdk.appwrite.models.Membership;
import online.bingzi.sdk.appwrite.models.Team;
import online.bingzi.sdk.appwrite.services.impl.TeamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 团队服务测试类
 */
class TeamServiceTest extends BaseTest {
    private TeamService teamService;

    @BeforeEach
    void init() {
        teamService = new TeamServiceImpl(client);
    }

    @Test
    void createTeam() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody(loadJsonFromResource("team"))
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        List<String> roles = Arrays.asList("admin", "developer");
        Response<Team> response = teamService.createTeam("Test Team", "test-id", roles)
                .execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals("/v1/teams", request.getPath());
        assertTrue(request.getBody().readUtf8().contains("Test Team"));

        // 验证响应
        assertTrue(response.isSuccessful());
        Team team = response.body();
        assertNotNull(team);
        assertEquals("5e5ea5c16897e", team.getId());
        assertEquals("Test Team", team.getName());
        assertEquals(10, team.getTotal());
        assertNotNull(team.getPreferences());
        assertEquals(Arrays.asList("admin", "developer"), team.getPreferences().getRoles());
    }

    @Test
    void listTeams() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("[" + loadJsonFromResource("team") + "]")
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        Response<List<Team>> response = teamService.listTeams().execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/teams", request.getPath());

        // 验证响应
        assertTrue(response.isSuccessful());
        List<Team> teams = response.body();
        assertNotNull(teams);
        assertEquals(1, teams.size());
        Team team = teams.get(0);
        assertEquals("5e5ea5c16897e", team.getId());
        assertEquals("Test Team", team.getName());
    }

    @Test
    void getTeam() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("team"))
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        Response<Team> response = teamService.getTeam("test-id").execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/teams/test-id", request.getPath());

        // 验证响应
        assertTrue(response.isSuccessful());
        Team team = response.body();
        assertNotNull(team);
        assertEquals("5e5ea5c16897e", team.getId());
        assertEquals("Test Team", team.getName());
    }

    @Test
    void updateTeam() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("team"))
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        Response<Team> response = teamService.updateTeam("test-id", "Updated Team").execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("PUT", request.getMethod());
        assertEquals("/v1/teams/test-id", request.getPath());
        assertTrue(request.getBody().readUtf8().contains("Updated Team"));

        // 验证响应
        assertTrue(response.isSuccessful());
        Team team = response.body();
        assertNotNull(team);
        assertEquals("5e5ea5c16897e", team.getId());
    }

    @Test
    void deleteTeam() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse().setResponseCode(204));

        // 执行请求
        Response<Void> response = teamService.deleteTeam("test-id").execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("DELETE", request.getMethod());
        assertEquals("/v1/teams/test-id", request.getPath());

        // 验证响应
        assertTrue(response.isSuccessful());
    }

    @Test
    void createMembership() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody(loadJsonFromResource("membership"))
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        List<String> roles = Arrays.asList("developer");
        Response<Membership> response = teamService.createMembership(
                "test-team", "john@example.com", roles,
                "https://example.com", "John Doe").execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals("/v1/teams/test-team/memberships", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("john@example.com"));
        assertTrue(body.contains("developer"));

        // 验证响应
        assertTrue(response.isSuccessful());
        Membership membership = response.body();
        assertNotNull(membership);
        assertEquals("5e5ea5c16897e", membership.getId());
        assertEquals("John Doe", membership.getUserName());
        assertEquals("john@example.com", membership.getUserEmail());
        assertEquals(Arrays.asList("developer"), membership.getRoles());
    }

    @Test
    void getMembership() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("membership"))
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        Response<Membership> response = teamService.getMembership("test-team", "test-membership")
                .execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/teams/test-team/memberships/test-membership", request.getPath());

        // 验证响应
        assertTrue(response.isSuccessful());
        Membership membership = response.body();
        assertNotNull(membership);
        assertEquals("5e5ea5c16897e", membership.getId());
        assertEquals("John Doe", membership.getUserName());
    }

    @Test
    void updateMembership() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("membership"))
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        List<String> roles = Arrays.asList("admin");
        Response<Membership> response = teamService.updateMembership(
                "test-team", "test-membership", roles).execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("PATCH", request.getMethod());
        assertEquals("/v1/teams/test-team/memberships/test-membership", request.getPath());
        assertTrue(request.getBody().readUtf8().contains("admin"));

        // 验证响应
        assertTrue(response.isSuccessful());
        Membership membership = response.body();
        assertNotNull(membership);
        assertEquals("5e5ea5c16897e", membership.getId());
    }

    @Test
    void deleteMembership() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse().setResponseCode(204));

        // 执行请求
        Response<Void> response = teamService.deleteMembership("test-team", "test-membership")
                .execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("DELETE", request.getMethod());
        assertEquals("/v1/teams/test-team/memberships/test-membership", request.getPath());

        // 验证响应
        assertTrue(response.isSuccessful());
    }

    @Test
    void updateMembershipStatus() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("membership"))
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        Response<Membership> response = teamService.updateMembershipStatus(
                "test-team", "test-membership", "user-id", "secret").execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("PATCH", request.getMethod());
        assertEquals("/v1/teams/test-team/memberships/test-membership/status", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("user-id"));
        assertTrue(body.contains("secret"));

        // 验证响应
        assertTrue(response.isSuccessful());
        Membership membership = response.body();
        assertNotNull(membership);
        assertEquals("5e5ea5c16897e", membership.getId());
    }

    @Test
    void getPrefs() throws Exception {
        // 准备模拟响应
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("key", "value");
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"key\":\"value\"}")
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        Response<Map<String, Object>> response = teamService.getPrefs("test-team").execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/teams/test-team/prefs", request.getPath());

        // 验证响应
        assertTrue(response.isSuccessful());
        Map<String, Object> result = response.body();
        assertNotNull(result);
        assertEquals("value", result.get("key"));
    }

    @Test
    void updatePrefs() throws Exception {
        // 准备模拟响应
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("key", "value");
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"key\":\"value\"}")
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        Response<Map<String, Object>> response = teamService.updatePrefs("test-team", prefs)
                .execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("PUT", request.getMethod());
        assertEquals("/v1/teams/test-team/prefs", request.getPath());
        assertTrue(request.getBody().readUtf8().contains("value"));

        // 验证响应
        assertTrue(response.isSuccessful());
        Map<String, Object> result = response.body();
        assertNotNull(result);
        assertEquals("value", result.get("key"));
    }
} 