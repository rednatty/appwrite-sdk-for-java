package lt.xnatty.sdk.appwrite;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {

    private String endpoint = "https://appwrite.io/v1";
    private String projectId;
    private String apiKey;
    private Retrofit retrofit;

    public Client setEndpoint(String endpoint) {
        if (endpoint == null || endpoint.trim().isEmpty()) {
            throw new IllegalArgumentException("Endpoint cannot be null or empty");
        }
        this.endpoint = endpoint;
        this.initRetrofit();
        return this;
    }

    public Client setProject(String projectId) {
        if (projectId == null || projectId.trim().isEmpty()) {
            throw new IllegalArgumentException("Project ID cannot be null or empty");
        }
        this.projectId = projectId;
        this.initRetrofit();
        return this;
    }

    public Client setKey(String apiKey) {
        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalArgumentException("API key cannot be null or empty");
        }
        this.apiKey = apiKey;
        this.initRetrofit();
        return this;
    }

    private void initRetrofit() {
        if (projectId == null || apiKey == null) {
            return;
        }
        Gson gson = new GsonBuilder().setLenient().create();
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
        this.retrofit = new Retrofit.Builder()
                .baseUrl(endpoint)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public <T> T createService(Class<T> serviceClass) {
        if (retrofit == null) {
            throw new IllegalStateException(
                    "Client not properly initialized. Please set endpoint, project ID and API key.");
        }
        return retrofit.create(serviceClass);
    }
}
