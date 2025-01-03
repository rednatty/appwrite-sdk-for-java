package online.bingzi.sdk.appwrite;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Appwrite客户端
 */
public class Client {
    private String endpoint = "https://appwrite.io/v1";
    private String projectId;
    private String apiKey;
    private Retrofit retrofit;

    public Client setEndpoint(String endpoint) {
        this.endpoint = endpoint;
        this.initRetrofit();
        return this;
    }

    public Client setProject(String projectId) {
        this.projectId = projectId;
        this.initRetrofit();
        return this;
    }

    public Client setKey(String apiKey) {
        this.apiKey = apiKey;
        this.initRetrofit();
        return this;
    }

    private void initRetrofit() {
        if (projectId == null || apiKey == null) {
            return;
        }

        // 配置Gson
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
     * @param serviceClass 服务接口类
     * @param <T> 服务接口类型
     * @return 服务接口实例
     */
    public <T> T createService(Class<T> serviceClass) {
        if (retrofit == null) {
            throw new IllegalStateException("Client not properly initialized. Please set endpoint, project ID and API key.");
        }
        return retrofit.create(serviceClass);
    }
} 