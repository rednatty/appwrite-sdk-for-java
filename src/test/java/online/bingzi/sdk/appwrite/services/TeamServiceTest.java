package online.bingzi.sdk.appwrite.services;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import online.bingzi.sdk.appwrite.models.Team;
import online.bingzi.sdk.appwrite.models.Membership;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TeamServiceTest {
    private MockWebServer mockWebServer;
    private TeamService teamService;

    @BeforeEach
    void setUp() {
        mockWebServer = new MockWebServer();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/v1/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        teamService = retrofit.create(TeamService.class);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    private String loadJsonFromResource(String filename) throws IOException {
        return new String(Files.readAllBytes(
                Paths.get("src/test/resources/json/" + filename + ".json")));
    }

    @Test
    void createTeam() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody(loadJsonFromResource("team"))
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        Response<Team> response = teamService.createTeam(
                "test-team",
                "Development Team",
                Arrays.asList("admin", "developer")
        ).execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals("/v1/teams", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("test-team"));
        assertTrue(body.contains("Development Team"));
        assertTrue(body.contains("admin"));
        assertTrue(body.contains("developer"));

        // 验证响应
        assertTrue(response.isSuccessful());
        Team team = response.body();
        assertNotNull(team);
        assertEquals("5e5ea5c16897e", team.getId());
        assertEquals("Development Team", team.getName());
        assertEquals(10, team.getTotal());
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
        assertEquals("Development Team", team.getName());
    }

    @Test
    void getTeam() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("team"))
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        Response<Team> response = teamService.getTeam("test-team").execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/teams/test-team", request.getPath());

        // 验证响应
        assertTrue(response.isSuccessful());
        Team team = response.body();
        assertNotNull(team);
        assertEquals("5e5ea5c16897e", team.getId());
        assertEquals("Development Team", team.getName());
    }

    @Test
    void updateTeam() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("team"))
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        Response<Team> response = teamService.updateTeam(
                "test-team",
                "Updated Team Name"
        ).execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("PUT", request.getMethod());
        assertEquals("/v1/teams/test-team", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("Updated Team Name"));

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
        Response<Void> response = teamService.deleteTeam("test-team").execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("DELETE", request.getMethod());
        assertEquals("/v1/teams/test-team", request.getPath());

        // 验证响应
        assertTrue(response.isSuccessful());
    }

    @Test
    void listMemberships() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("[" + loadJsonFromResource("membership") + "]")
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        Response<List<Membership>> response = teamService.listMemberships("test-team").execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/teams/test-team/memberships", request.getPath());

        // 验证响应
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
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody(loadJsonFromResource("membership"))
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        Response<Membership> response = teamService.createMembership(
                "test-team",
                "john@example.com",
                Arrays.asList("developer"),
                "https://example.com/invite"
        ).execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals("/v1/teams/test-team/memberships", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("john@example.com"));
        assertTrue(body.contains("developer"));
        assertTrue(body.contains("https://example.com/invite"));

        // 验证响应
        assertTrue(response.isSuccessful());
        Membership membership = response.body();
        assertNotNull(membership);
        assertEquals("5e5ea5c16897f", membership.getId());
        assertEquals("John Doe", membership.getUserName());
    }

    @Test
    void getMembership() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("membership"))
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        Response<Membership> response = teamService.getMembership(
                "test-team",
                "test-membership"
        ).execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/teams/test-team/memberships/test-membership", request.getPath());

        // 验证响应
        assertTrue(response.isSuccessful());
        Membership membership = response.body();
        assertNotNull(membership);
        assertEquals("5e5ea5c16897f", membership.getId());
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
        Response<Membership> response = teamService.updateMembership(
                "test-team",
                "test-membership",
                Arrays.asList("admin", "developer")
        ).execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("PATCH", request.getMethod());
        assertEquals("/v1/teams/test-team/memberships/test-membership", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("admin"));
        assertTrue(body.contains("developer"));

        // 验证响应
        assertTrue(response.isSuccessful());
        Membership membership = response.body();
        assertNotNull(membership);
        assertEquals("5e5ea5c16897f", membership.getId());
    }

    @Test
    void deleteMembership() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse().setResponseCode(204));

        // 执行请求
        Response<Void> response = teamService.deleteMembership(
                "test-team",
                "test-membership"
        ).execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("DELETE", request.getMethod());
        assertEquals("/v1/teams/test-team/memberships/test-membership", request.getPath());

        // 验证响应
        assertTrue(response.isSuccessful());
    }

    @Test
    void updatePrefs() throws Exception {
        // 准备模拟响应
        Map<String, Object> prefsResponse = new HashMap<>();
        prefsResponse.put("maxMembers", 100);
        prefsResponse.put("allowInvites", true);
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"maxMembers\": 100, \"allowInvites\": true}")
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("maxMembers", 100);
        prefs.put("allowInvites", true);
        Response<Map<String, Object>> response = teamService.updatePrefs("test-team", prefs).execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("PUT", request.getMethod());
        assertEquals("/v1/teams/test-team/prefs", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("100"));
        assertTrue(body.contains("true"));

        // 验证响应
        assertTrue(response.isSuccessful());
        Map<String, Object> responsePrefs = response.body();
        assertNotNull(responsePrefs);
        assertEquals(100, responsePrefs.get("maxMembers"));
        assertEquals(true, responsePrefs.get("allowInvites"));
    }
} 