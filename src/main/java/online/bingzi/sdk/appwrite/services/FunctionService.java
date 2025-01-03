package online.bingzi.sdk.appwrite.services;

import online.bingzi.sdk.appwrite.models.Execution;
import online.bingzi.sdk.appwrite.models.Function;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

/**
 * 函数服务接口
 */
public interface FunctionService {
    /**
     * 创建函数
     */
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
            @Field("enabled") Boolean enabled
    );

    /**
     * 获取函数列表
     */
    @GET("functions")
    Call<List<Function>> listFunctions();

    /**
     * 获取函数信息
     */
    @GET("functions/{functionId}")
    Call<Function> getFunction(@Path("functionId") String functionId);

    /**
     * 更新函数信息
     */
    @FormUrlEncoded
    @PUT("functions/{functionId}")
    Call<Function> updateFunction(
            @Path("functionId") String functionId,
            @Field("name") String name,
            @Field("execute") List<String> execute,
            @Field("events") List<String> events,
            @Field("schedule") String schedule,
            @Field("timeout") Integer timeout,
            @Field("enabled") Boolean enabled
    );

    /**
     * 删除函数
     */
    @DELETE("functions/{functionId}")
    Call<Void> deleteFunction(@Path("functionId") String functionId);

    /**
     * 创建函数执行
     */
    @FormUrlEncoded
    @POST("functions/{functionId}/executions")
    Call<Execution> createExecution(
            @Path("functionId") String functionId,
            @Field("data") String data
    );

    /**
     * 获取函数执行列表
     */
    @GET("functions/{functionId}/executions")
    Call<List<Execution>> listExecutions(@Path("functionId") String functionId);

    /**
     * 获取函数执行信息
     */
    @GET("functions/{functionId}/executions/{executionId}")
    Call<Execution> getExecution(
            @Path("functionId") String functionId,
            @Path("executionId") String executionId
    );

    /**
     * 更新函数变量
     */
    @FormUrlEncoded
    @PUT("functions/{functionId}/variables")
    Call<Function> updateVariables(
            @Path("functionId") String functionId,
            @FieldMap Map<String, String> variables
    );
} 