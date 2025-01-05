package online.bingzi.sdk.appwrite;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Appwrite SDK 客户端类
 * <p>
 * 该类是 Appwrite SDK 的核心类，负责管理与 Appwrite 服务器的所有通信。
 * 它使用 Retrofit 和 OkHttp 来处理 HTTP 请求，使用 Gson 进行 JSON 序列化。
 * </p>
 *
 * <h2>使用示例：</h2>
 * <pre>
 * // 创建客户端实例并配置
 * Client client = new Client()
 *     .setEndpoint("https://your-appwrite-endpoint/v1")
 *     .setProject("your-project-id")
 *     .setKey("your-api-key");
 *
 * // 创建特定服务实例
 * AccountService accountService = client.createService(AccountService.class);
 * DatabaseService databaseService = client.createService(DatabaseService.class);
 *
 * // 使用服务进行API调用
 * Call<Account> accountCall = accountService.getAccount();
 * accountCall.enqueue(new Callback<Account>() {
 *     @Override
 *     public void onResponse(Call<Account> call, Response<Account> response) {
 *         Account account = response.body();
 *         // 处理账户信息
 *     }
 *
 *     @Override
 *     public void onFailure(Call<Account> call, Throwable t) {
 *         // 处理错误
 *     }
 * });
 * </pre>
 *
 * <h2>重要说明：</h2>
 * <ul>
 *   <li>在使用任何服务之前，必须先设置 endpoint、projectId 和 apiKey</li>
 *   <li>所有的服务实例都应该通过 createService 方法创建</li>
 *   <li>客户端实例是线程安全的，可以在多个线程中共享</li>
 *   <li>建议在应用程序中只创建一个客户端实例</li>
 * </ul>
 */
public class Client {
    /**
     * 默认的 Appwrite API 端点
     * 如果使用自托管的 Appwrite 实例，可以通过 setEndpoint 方法修改
     */
    private String endpoint = "https://appwrite.io/v1";

    /**
     * Appwrite 项目 ID
     * 用于标识请求来自哪个项目
     */
    private String projectId;

    /**
     * Appwrite API 密钥
     * 用于进行服务器端API认证
     */
    private String apiKey;

    /**
     * Retrofit 实例
     * 用于处理所有的 HTTP 请求
     */
    private Retrofit retrofit;

    /**
     * 设置 API 端点
     *
     * @param endpoint API端点URL，例如 "https://your-appwrite-endpoint/v1"
     * @return 当前客户端实例，支持链式调用
     * @throws IllegalArgumentException 如果endpoint为null或空
     */
    public Client setEndpoint(String endpoint) {
        if (endpoint == null || endpoint.trim().isEmpty()) {
            throw new IllegalArgumentException("Endpoint cannot be null or empty");
        }
        this.endpoint = endpoint;
        this.initRetrofit();
        return this;
    }

    /**
     * 设置项目 ID
     *
     * @param projectId Appwrite项目ID
     * @return 当前客户端实例，支持链式调用
     * @throws IllegalArgumentException 如果projectId为null或空
     */
    public Client setProject(String projectId) {
        if (projectId == null || projectId.trim().isEmpty()) {
            throw new IllegalArgumentException("Project ID cannot be null or empty");
        }
        this.projectId = projectId;
        this.initRetrofit();
        return this;
    }

    /**
     * 设置 API 密钥
     *
     * @param apiKey Appwrite API密钥
     * @return 当前客户端实例，支持链式调用
     * @throws IllegalArgumentException 如果apiKey为null或空
     */
    public Client setKey(String apiKey) {
        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalArgumentException("API key cannot be null or empty");
        }
        this.apiKey = apiKey;
        this.initRetrofit();
        return this;
    }

    /**
     * 初始化 Retrofit 实例
     * 配置 OkHttpClient 和 Gson，设置必要的请求头
     */
    private void initRetrofit() {
        if (projectId == null || apiKey == null) {
            return;
        }

        // 配置Gson，使用宽松模式以处理各种JSON格式
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        // 配置OkHttpClient，添加拦截器用于注入认证头
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request.Builder builder = original.newBuilder()
                            .header("X-Appwrite-Project", projectId)
                            .header("X-Appwrite-Key", apiKey)
                            .header("Content-Type", "application/json")
                            .method(original.method(), original.body());
                    return chain.proceed(builder.build());
                })
                .build();

        // 初始化Retrofit
        this.retrofit = new Retrofit.Builder()
                .baseUrl(endpoint)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    /**
     * 创建服务接口实例
     *
     * <p>使用示例：</p>
     * <pre>
     * AccountService accountService = client.createService(AccountService.class);
     * DatabaseService databaseService = client.createService(DatabaseService.class);
     * </pre>
     *
     * @param serviceClass 服务接口类
     * @param <T> 服务接口类型
     * @return 服务接口实例
     * @throws IllegalStateException 如果客户端未正确初始化（未设置endpoint、projectId或apiKey）
     */
    public <T> T createService(Class<T> serviceClass) {
        if (retrofit == null) {
            throw new IllegalStateException("Client not properly initialized. Please set endpoint, project ID and API key.");
        }
        return retrofit.create(serviceClass);
    }
} 