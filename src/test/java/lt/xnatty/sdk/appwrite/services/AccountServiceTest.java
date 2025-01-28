package lt.xnatty.sdk.appwrite.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import lt.xnatty.sdk.appwrite.BaseTest;
import lt.xnatty.sdk.appwrite.models.User;
import lt.xnatty.sdk.appwrite.services.impl.AccountServiceImpl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

class AccountServiceTest extends BaseTest {
    private AccountService accountService;

    @BeforeEach
    void init() {
        accountService = new AccountServiceImpl(client);
    }

    @Test
    void create() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody(loadJsonFromResource("user"))
                .addHeader("Content-Type", "application/json"));
        Response<User> response = accountService
                .create("john@example.com", "password123", "John Doe")
                .execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals("/v1/account", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("example.com"));
        assertTrue(body.contains("password123"));
        assertTrue(body.contains("John%20Doe"));
        assertTrue(response.isSuccessful());
        User user = response.body();
        assertNotNull(user);
        assertEquals("5e5ea5c16897e", user.getId());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("John Doe", user.getName());
    }

    @Test
    void createEmailSession() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody(loadJsonFromResource("user"))
                .addHeader("Content-Type", "application/json"));
        Response<User> response = accountService
                .createEmailSession("john@example.com", "password123")
                .execute();
        RecordedRequest request = mockWebServer.takeRequest();

        assertEquals("POST", request.getMethod());
        assertEquals("/v1/account/sessions/email", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("example.com"));
        assertTrue(body.contains("password123"));
        assertTrue(response.isSuccessful());
        User user = response.body();
        assertNotNull(user);
        assertEquals("5e5ea5c16897e", user.getId());
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    void get() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("user"))
                .addHeader("Content-Type", "application/json"));
        Response<User> response = accountService.get().execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/account", request.getPath());
        assertTrue(response.isSuccessful());
        User user = response.body();
        assertNotNull(user);
        assertEquals("5e5ea5c16897e", user.getId());
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    void getPrefs() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"theme\":\"dark\",\"notifications\":true}")
                .addHeader("Content-Type", "application/json"));
        Response<Map<String, Object>> response = accountService.getPrefs().execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/account/prefs", request.getPath());
        assertTrue(response.isSuccessful());
        Map<String, Object> prefs = response.body();
        assertNotNull(prefs);
        assertEquals("dark", prefs.get("theme"));
        assertEquals(true, prefs.get("notifications"));
    }

    @Test
    void updatePrefs() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"theme\":\"light\",\"notifications\":false}")
                .addHeader("Content-Type", "application/json"));
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("theme", "light");
        prefs.put("notifications", false);
        Response<Map<String, Object>> response =
                accountService.updatePrefs(prefs).execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("PATCH", request.getMethod());
        assertEquals("/v1/account/prefs", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("light"));
        assertTrue(body.contains("false"));
        assertTrue(response.isSuccessful());
        Map<String, Object> updatedPrefs = response.body();
        assertNotNull(updatedPrefs);
        assertEquals("light", updatedPrefs.get("theme"));
        assertEquals(false, updatedPrefs.get("notifications"));
    }

    @Test
    void createRecovery() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody("{\"success\":true}")
                .addHeader("Content-Type", "application/json"));
        Response<Void> response = accountService
                .createRecovery("john@example.com", "https://example.com/recovery")
                .execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals("/v1/account/recovery", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("example.com"));
        assertTrue(body.contains("recovery"));
        assertTrue(response.isSuccessful());
    }

    @Test
    void updateRecovery() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"success\":true}")
                .addHeader("Content-Type", "application/json"));
        Response<Void> response = accountService
                .updateRecovery("user-id", "recovery-secret", "newpassword123", "newpassword123")
                .execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("PUT", request.getMethod());
        assertEquals("/v1/account/recovery", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("user-id"));
        assertTrue(body.contains("recovery-secret"));
        assertTrue(body.contains("newpassword123"));
        assertTrue(response.isSuccessful());
    }

    @Test
    void deleteSession() throws Exception {
        mockWebServer.enqueue(new MockResponse().setResponseCode(204));
        Response<Void> response = accountService.deleteSession("current").execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("DELETE", request.getMethod());
        assertEquals("/v1/account/sessions/current", request.getPath());
        assertTrue(response.isSuccessful());
    }

    @Test
    void deleteSessions() throws Exception {
        mockWebServer.enqueue(new MockResponse().setResponseCode(204));
        Response<Void> response = accountService.deleteSessions().execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("DELETE", request.getMethod());
        assertEquals("/v1/account/sessions", request.getPath());
        assertTrue(response.isSuccessful());
    }
}
