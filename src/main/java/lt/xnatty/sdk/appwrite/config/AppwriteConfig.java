package lt.xnatty.sdk.appwrite.config;

public class AppwriteConfig {
    public static final String ENDPOINT = "https://cloud.appwrite.io/v1";
    private final String projectId;
    private final String apiKey;

    public AppwriteConfig(String projectId, String apiKey) {
        if (projectId == null || projectId.trim().isEmpty()) {
            throw new IllegalArgumentException("Project ID cannot be null or empty");
        }
        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalArgumentException("API key cannot be null or empty");
        }
        this.projectId = projectId;
        this.apiKey = apiKey;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getEndpoint() {
        return ENDPOINT;
    }
}
