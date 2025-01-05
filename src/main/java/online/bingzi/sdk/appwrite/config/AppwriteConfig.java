package online.bingzi.sdk.appwrite.config;

/**
 * Appwrite SDK 配置类
 * <p>
 * 该类用于配置 Appwrite SDK 的基本参数，包括项目ID和API密钥。
 * 所有与 Appwrite 服务的交互都需要通过此配置类来初始化。
 * </p>
 *
 * <h2>使用示例：</h2>
 * <pre>
 * // 创建配置实例
 * AppwriteConfig config = new AppwriteConfig(
 *     "your-project-id",  // 在 Appwrite 控制台创建的项目ID
 *     "your-api-key"      // 在项目设置中生成的API密钥
 * );
 *
 * // 在客户端中使用配置
 * Client client = new Client(config);
 * </pre>
 *
 * <h2>重要说明：</h2>
 * <ul>
 *   <li>API密钥应该妥善保管，不要在客户端代码中暴露</li>
 *   <li>建议使用环境变量或配置文件来存储这些敏感信息</li>
 *   <li>不同的API密钥可能有不同的权限范围，请根据需要选择合适的密钥</li>
 * </ul>
 */
public class AppwriteConfig {
    /**
     * Appwrite API端点
     * 默认使用 Appwrite 云服务的端点
     * 如果使用自托管的 Appwrite 实例，可以修改此常量
     */
    public static final String ENDPOINT = "https://cloud.appwrite.io/v1";

    /**
     * 项目ID
     * 可以在 Appwrite 控制台的项目设置中找到
     * 格式通常为一串唯一的字符串，例如："507f1f77bcf86cd799439011"
     */
    private final String projectId;

    /**
     * API密钥
     * 用于认证和授权SDK的请求
     * 可以在 Appwrite 控制台的 API Keys 部分生成
     * 注意：请妥善保管API密钥，不要将其暴露在公开场合
     */
    private final String apiKey;

    /**
     * 创建一个新的 AppwriteConfig 实例
     *
     * @param projectId 项目ID，不能为空
     * @param apiKey    API密钥，不能为空
     * @throws IllegalArgumentException 如果 projectId 或 apiKey 为空
     */
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

    /**
     * 获取项目ID
     *
     * @return 配置的项目ID
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * 获取API密钥
     *
     * @return 配置的API密钥
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * 获取API端点
     *
     * @return API端点URL
     */
    public String getEndpoint() {
        return ENDPOINT;
    }
} 