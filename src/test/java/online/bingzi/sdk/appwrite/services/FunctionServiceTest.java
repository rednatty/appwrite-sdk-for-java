package online.bingzi.sdk.appwrite.services;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import online.bingzi.sdk.appwrite.models.Function;
import online.bingzi.sdk.appwrite.models.Execution;
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

class FunctionServiceTest {
    private MockWebServer mockWebServer;
    private FunctionService functionService;

    @BeforeEach
    void setUp() {
        mockWebServer = new MockWebServer();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/v1/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        functionService = retrofit.create(FunctionService.class);
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
    void createFunction() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody(loadJsonFromResource("function"))
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        Response<Function> response = functionService.createFunction(
                "test-function",
                "hello-world",
                "node-14.5",
                Arrays.asList("users"),
                Arrays.asList("users.*.create"),
                "0 0 * * *",
                15,
                true
        ).execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals("/v1/functions", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("test-function"));
        assertTrue(body.contains("hello-world"));
        assertTrue(body.contains("node-14.5"));
        assertTrue(body.contains("users"));
        assertTrue(body.contains("0 0 * * *"));
        assertTrue(body.contains("15"));
        assertTrue(body.contains("true"));

        // 验证响应
        assertTrue(response.isSuccessful());
        Function function = response.body();
        assertNotNull(function);
        assertEquals("5e5ea5c16897e", function.getId());
        assertEquals("hello-world", function.getName());
        assertTrue(function.isEnabled());
    }

    @Test
    void listFunctions() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("[" + loadJsonFromResource("function") + "]")
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        Response<List<Function>> response = functionService.listFunctions().execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/functions", request.getPath());

        // 验证响应
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
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("function"))
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        Response<Function> response = functionService.getFunction("test-function").execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/functions/test-function", request.getPath());

        // 验证响应
        assertTrue(response.isSuccessful());
        Function function = response.body();
        assertNotNull(function);
        assertEquals("5e5ea5c16897e", function.getId());
        assertEquals("hello-world", function.getName());
    }

    @Test
    void updateFunction() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("function"))
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        Response<Function> response = functionService.updateFunction(
                "test-function",
                "updated-function",
                Arrays.asList("users", "teams"),
                Arrays.asList("users.*.update"),
                "0 0 * * *",
                30,
                true
        ).execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("PUT", request.getMethod());
        assertEquals("/v1/functions/test-function", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("updated-function"));
        assertTrue(body.contains("users"));
        assertTrue(body.contains("teams"));
        assertTrue(body.contains("users.*.update"));
        assertTrue(body.contains("30"));
        assertTrue(body.contains("true"));

        // 验证响应
        assertTrue(response.isSuccessful());
        Function function = response.body();
        assertNotNull(function);
        assertEquals("5e5ea5c16897e", function.getId());
    }

    @Test
    void deleteFunction() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse().setResponseCode(204));

        // 执行请求
        Response<Void> response = functionService.deleteFunction("test-function").execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("DELETE", request.getMethod());
        assertEquals("/v1/functions/test-function", request.getPath());

        // 验证响应
        assertTrue(response.isSuccessful());
    }

    @Test
    void listExecutions() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("[" + loadJsonFromResource("execution") + "]")
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        Response<List<Execution>> response = functionService.listExecutions("test-function").execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/functions/test-function/executions", request.getPath());

        // 验证响应
        assertTrue(response.isSuccessful());
        List<Execution> executions = response.body();
        assertNotNull(executions);
        assertEquals(1, executions.size());
        Execution execution = executions.get(0);
        assertEquals("5e5ea5c16897f", execution.getId());
        assertEquals("completed", execution.getStatus());
    }

    @Test
    void createExecution() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody(loadJsonFromResource("execution"))
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        Map<String, Object> data = new HashMap<>();
        data.put("name", "John");
        data.put("age", 30);
        Response<Execution> response = functionService.createExecution(
                "test-function",
                data
        ).execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals("/v1/functions/test-function/executions", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("John"));
        assertTrue(body.contains("30"));

        // 验证响应
        assertTrue(response.isSuccessful());
        Execution execution = response.body();
        assertNotNull(execution);
        assertEquals("5e5ea5c16897f", execution.getId());
        assertEquals("completed", execution.getStatus());
    }

    @Test
    void getExecution() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("execution"))
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        Response<Execution> response = functionService.getExecution(
                "test-function",
                "test-execution"
        ).execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/functions/test-function/executions/test-execution", request.getPath());

        // 验证响应
        assertTrue(response.isSuccessful());
        Execution execution = response.body();
        assertNotNull(execution);
        assertEquals("5e5ea5c16897f", execution.getId());
        assertEquals("completed", execution.getStatus());
    }

    @Test
    void createDeployment() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody(loadJsonFromResource("function"))
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        java.io.File codeFile = new java.io.File("src/test/resources/test.txt");
        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(
                okhttp3.MediaType.parse("application/octet-stream"),
                codeFile
        );
        okhttp3.MultipartBody.Part codePart = okhttp3.MultipartBody.Part.createFormData(
                "code",
                codeFile.getName(),
                requestBody
        );

        Response<Function> response = functionService.createDeployment(
                "test-function",
                codePart,
                "src/index.js",
                "npm install"
        ).execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("PUT", request.getMethod());
        assertEquals("/v1/functions/test-function/deployments", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("src/index.js"));
        assertTrue(body.contains("npm install"));

        // 验证响应
        assertTrue(response.isSuccessful());
        Function function = response.body();
        assertNotNull(function);
        assertEquals("5e5ea5c16897e", function.getId());
    }

    @Test
    void updateVariables() throws Exception {
        // 准备模拟响应
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("function"))
                .addHeader("Content-Type", "application/json"));

        // 执行请求
        Map<String, String> variables = new HashMap<>();
        variables.put("API_KEY", "123456");
        variables.put("DB_NAME", "test");
        Response<Function> response = functionService.updateVariables(
                "test-function",
                variables
        ).execute();

        // 验证请求
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("PUT", request.getMethod());
        assertEquals("/v1/functions/test-function/variables", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("API_KEY"));
        assertTrue(body.contains("123456"));
        assertTrue(body.contains("DB_NAME"));
        assertTrue(body.contains("test"));

        // 验证响应
        assertTrue(response.isSuccessful());
        Function function = response.body();
        assertNotNull(function);
        assertEquals("5e5ea5c16897e", function.getId());
    }
} 