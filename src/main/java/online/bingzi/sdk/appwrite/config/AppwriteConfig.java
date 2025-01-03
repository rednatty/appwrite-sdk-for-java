package online.bingzi.sdk.appwrite.config;

/**
 * Appwrite配置类
 */
public class AppwriteConfig {
    /**
     * Appwrite API端点
     */
    public static final String ENDPOINT = "https://cloud.appwrite.io/v1";

    /**
     * 项目ID
     */
    private final String projectId;

    /**
     * API密钥
     */
    private final String apiKey;

    public AppwriteConfig(String projectId, String apiKey) {
        this.projectId = projectId;
        this.apiKey = apiKey;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getApiKey() {
        return apiKey;
    }
} 