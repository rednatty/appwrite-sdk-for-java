package online.bingzi.sdk.appwrite;

import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 测试基类
 */
public class BaseTest {
    protected MockWebServer mockWebServer;
    protected Client client;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        client = new Client()
                .setEndpoint(mockWebServer.url("v1/").toString())
                .setProject("test-project")
                .setKey("test-key");

    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    protected String loadJsonFromResource(String filename) throws IOException {
        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("json/" + filename + ".json");
        if (inputStream == null) {
            throw new FileNotFoundException("Resource not found: " + filename);
        }

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }
} 