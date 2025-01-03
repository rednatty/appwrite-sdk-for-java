package online.bingzi.sdk.appwrite;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import online.bingzi.sdk.appwrite.config.AppwriteConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Appwrite客户端
 */
public class Client {
    private final Retrofit retrofit;
    private final AppwriteConfig config;

    public Client(AppwriteConfig config) {
        this.config = config;
        
        // 配置Gson
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        // 配置OkHttpClient，添加拦截器用于注入认证头
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request.Builder builder = original.newBuilder()
                            .header("X-Appwrite-Project", config.getProjectId())
                            .header("X-Appwrite-Key", config.getApiKey())
                            .header("Content-Type", "application/json")
                            .method(original.method(), original.body());
                    return chain.proceed(builder.build());
                })
                .build();

        // 初始化Retrofit
        this.retrofit = new Retrofit.Builder()
                .baseUrl(AppwriteConfig.ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    /**
     * 获取Retrofit实例
     * @return Retrofit实例
     */
    public Retrofit getRetrofit() {
        return retrofit;
    }

    /**
     * 获取配置信息
     * @return Appwrite配置
     */
    public AppwriteConfig getConfig() {
        return config;
    }

    /**
     * 创建服务接口实例
     * @param serviceClass 服务接口类
     * @param <T> 服务接口类型
     * @return 服务接口实例
     */
    public <T> T createService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }
} 