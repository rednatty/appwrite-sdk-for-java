package lt.xnatty.sdk.appwrite.services;

import java.util.List;
import java.util.Map;
import lt.xnatty.sdk.appwrite.models.Execution;
import lt.xnatty.sdk.appwrite.models.Function;
import retrofit2.Call;
import retrofit2.http.*;

public interface FunctionService {

    @FormUrlEncoded
    @POST("functions")
    Call<Function> createFunction(
            @Field("functionId") String functionId,
            @Field("name") String name,
            @Field("runtime") String runtime,
            @Field("execute") List<String> execute,
            @Field("events") List<String> events,
            @Field("schedule") String schedule,
            @Field("timeout") Integer timeout,
            @Field("enabled") Boolean enabled);

    @GET("functions")
    Call<List<Function>> listFunctions();

    @GET("functions/{functionId}")
    Call<Function> getFunction(@Path("functionId") String functionId);

    @FormUrlEncoded
    @PUT("functions/{functionId}")
    Call<Function> updateFunction(
            @Path("functionId") String functionId,
            @Field("name") String name,
            @Field("execute") List<String> execute,
            @Field("events") List<String> events,
            @Field("schedule") String schedule,
            @Field("timeout") Integer timeout,
            @Field("enabled") Boolean enabled);

    @DELETE("functions/{functionId}")
    Call<Void> deleteFunction(@Path("functionId") String functionId);

    @FormUrlEncoded
    @POST("functions/{functionId}/executions")
    Call<Execution> createExecution(@Path("functionId") String functionId, @Field("data") String data);

    @GET("functions/{functionId}/executions")
    Call<List<Execution>> listExecutions(@Path("functionId") String functionId);

    @GET("functions/{functionId}/executions/{executionId}")
    Call<Execution> getExecution(@Path("functionId") String functionId, @Path("executionId") String executionId);

    @FormUrlEncoded
    @PUT("functions/{functionId}/variables")
    Call<Function> updateVariables(@Path("functionId") String functionId, @FieldMap Map<String, String> variables);
}
