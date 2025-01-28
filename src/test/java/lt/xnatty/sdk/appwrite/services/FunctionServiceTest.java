package lt.xnatty.sdk.appwrite.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lt.xnatty.sdk.appwrite.BaseTest;
import lt.xnatty.sdk.appwrite.models.Execution;
import lt.xnatty.sdk.appwrite.models.Function;
import lt.xnatty.sdk.appwrite.services.impl.FunctionServiceImpl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

class FunctionServiceTest extends BaseTest {
    private FunctionService functionService;

    @BeforeEach
    void init() {
        functionService = new FunctionServiceImpl(client);
    }

    @Test
    void createFunction() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody(loadJsonFromResource("function"))
                .addHeader("Content-Type", "application/json"));
        Response<Function> response = functionService
                .createFunction(
                        "test-function",
                        "Test Function",
                        "node-14.5",
                        Arrays.asList("users"),
                        Arrays.asList("users.*.create"),
                        "0 0 * * *",
                        15,
                        true)
                .execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals("/v1/functions", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("test-function"));
        assertTrue(body.contains("Test%20Function"));
        assertTrue(body.contains("node-14.5"));
        assertTrue(body.contains("users"));
        assertTrue(body.contains("users.*.create"));
        assertTrue(body.contains("0%200%20*%20*%20*"));
        assertTrue(body.contains("15"));
        assertTrue(body.contains("true"));
        assertTrue(response.isSuccessful());
        Function function = response.body();
        assertNotNull(function);
        assertEquals("5e5ea5c16897e", function.getId());
        assertEquals("hello-world", function.getName());
    }

    @Test
    void listFunctions() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("[" + loadJsonFromResource("function") + "]")
                .addHeader("Content-Type", "application/json"));
        Response<List<Function>> response = functionService.listFunctions().execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/functions", request.getPath());
        assertTrue(response.isSuccessful());
        List<Function> functions = response.body();
        assertNotNull(functions);
        assertEquals(1, functions.size());
        Function function = functions.get(0);
        assertEquals("5e5ea5c16897e", function.getId());
        assertEquals("hello-world", function.getName());
    }

    @Test
    void getFunction() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("function"))
                .addHeader("Content-Type", "application/json"));
        Response<Function> response =
                functionService.getFunction("test-function").execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/functions/test-function", request.getPath());
        assertTrue(response.isSuccessful());
        Function function = response.body();
        assertNotNull(function);
        assertEquals("5e5ea5c16897e", function.getId());
        assertEquals("hello-world", function.getName());
    }

    @Test
    void updateFunction() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("function"))
                .addHeader("Content-Type", "application/json"));
        Response<Function> response = functionService
                .updateFunction(
                        "test-function",
                        "Updated Function",
                        Arrays.asList("users"),
                        Arrays.asList("users.*.update"),
                        "0 0 * * *",
                        30,
                        true)
                .execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("PUT", request.getMethod());
        assertEquals("/v1/functions/test-function", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("Updated%20Function"));
        assertTrue(body.contains("users"));
        assertTrue(body.contains("users.*.update"));
        assertTrue(body.contains("0%200%20*%20*%20*"));
        assertTrue(body.contains("30"));
        assertTrue(body.contains("true"));
        assertTrue(response.isSuccessful());
        Function function = response.body();
        assertNotNull(function);
        assertEquals("5e5ea5c16897e", function.getId());
    }

    @Test
    void deleteFunction() throws Exception {
        mockWebServer.enqueue(new MockResponse().setResponseCode(204));
        Response<Void> response =
                functionService.deleteFunction("test-function").execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("DELETE", request.getMethod());
        assertEquals("/v1/functions/test-function", request.getPath());
        assertTrue(response.isSuccessful());
    }

    @Test
    void createExecution() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody(loadJsonFromResource("execution"))
                .addHeader("Content-Type", "application/json"));
        String data = "{\"key\": \"value\"}";
        Response<Execution> response =
                functionService.createExecution("test-function", data).execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals("/v1/functions/test-function/executions", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("data="));
        assertTrue(response.isSuccessful());
        Execution execution = response.body();
        assertNotNull(execution);
        assertEquals("5e5ea5c16897f", execution.getId());
        assertEquals("completed", execution.getStatus());
    }

    @Test
    void listExecutions() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("[" + loadJsonFromResource("execution") + "]")
                .addHeader("Content-Type", "application/json"));
        Response<List<Execution>> response =
                functionService.listExecutions("test-function").execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/functions/test-function/executions", request.getPath());
        assertTrue(response.isSuccessful());
        List<Execution> executions = response.body();
        assertNotNull(executions);
        assertEquals(1, executions.size());
        Execution execution = executions.get(0);
        assertEquals("5e5ea5c16897f", execution.getId());
        assertEquals("completed", execution.getStatus());
    }

    @Test
    void getExecution() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("execution"))
                .addHeader("Content-Type", "application/json"));
        Response<Execution> response =
                functionService.getExecution("test-function", "test-execution").execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/functions/test-function/executions/test-execution", request.getPath());
        assertTrue(response.isSuccessful());
        Execution execution = response.body();
        assertNotNull(execution);
        assertEquals("5e5ea5c16897f", execution.getId());
        assertEquals("completed", execution.getStatus());
    }

    @Test
    void updateVariables() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("function"))
                .addHeader("Content-Type", "application/json"));
        Map<String, String> variables = new HashMap<>();
        variables.put("API_KEY", "123456");
        variables.put("DB_NAME", "test");
        Response<Function> response =
                functionService.updateVariables("test-function", variables).execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("PUT", request.getMethod());
        assertEquals("/v1/functions/test-function/variables", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("API_KEY=123456"));
        assertTrue(body.contains("DB_NAME=test"));
        assertTrue(response.isSuccessful());
        Function function = response.body();
        assertNotNull(function);
        assertEquals("5e5ea5c16897e", function.getId());
    }
}
