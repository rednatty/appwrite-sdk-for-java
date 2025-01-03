package online.bingzi.sdk.appwrite;

import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import online.bingzi.sdk.appwrite.config.AppwriteConfig;

import java.io.IOException;

/**
 * 基础测试类，提供共享配置
 */
public abstract class BaseTest {
    protected MockWebServer mockWebServer;
    protected Client client;
    protected AppwriteConfig config;

    @BeforeEach
    void setUp() throws IOException {
        // 设置模拟服务器
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        // 创建配置
        config = new AppwriteConfig("test-project", "test-api-key") {
            @Override
            public String getEndpoint() {
                return mockWebServer.url("/v1").toString();
            }
        };

        // 创建客户端
        client = new Client(config);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    /**
     * 从资源文件加载JSON响应
     *
     * @param name 资源文件名
     * @return JSON字符串
     */
    protected String loadJsonFromResource(String name) {
        try {
            return new String(getClass().getResourceAsStream("/json/" + name + ".json").readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load JSON resource: " + name, e);
        }
    }
} 