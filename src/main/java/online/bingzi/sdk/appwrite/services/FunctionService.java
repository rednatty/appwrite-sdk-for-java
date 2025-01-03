package online.bingzi.sdk.appwrite.services;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import online.bingzi.sdk.appwrite.models.Deployment;
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
     *
     * @param name       函数名称
     * @param runtime    运行时环境
     * @param execute    执行权限
     * @param id         函数ID（可选）
     * @param events     事件触发器（可选）
     * @param schedule   定时触发器（可选）
     * @param timeout    超时时间（可选）
     * @param enabled    是否启用（可选）
     * @return 函数信息
     */
    @POST("functions")
    @FormUrlEncoded
    Call<Function> createFunction(
            @Field("name") String name,
            @Field("runtime") String runtime,
            @Field("execute") List<String> execute,
            @Field("functionId") String id,
            @Field("events") List<String> events,
            @Field("schedule") String schedule,
            @Field("timeout") Integer timeout,
            @Field("enabled") Boolean enabled
    );

    /**
     * 获取函数列表
     *
     * @return 函数列表
     */
    @GET("functions")
    Call<List<Function>> listFunctions();

    /**
     * 获取函数信息
     *
     * @param functionId 函数ID
     * @return 函数信息
     */
    @GET("functions/{functionId}")
    Call<Function> getFunction(@Path("functionId") String functionId);

    /**
     * 更新函数
     *
     * @param functionId 函数ID
     * @param name       函数名称
     * @param execute    执行权限
     * @param events     事件触发器（可选）
     * @param schedule   定时触发器（可选）
     * @param timeout    超时时间（可选）
     * @param enabled    是否启用（可选）
     * @return 函数信息
     */
    @PUT("functions/{functionId}")
    @FormUrlEncoded
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
     *
     * @param functionId 函数ID
     * @return 空响应
     */
    @DELETE("functions/{functionId}")
    Call<Void> deleteFunction(@Path("functionId") String functionId);

    /**
     * 创建部署
     *
     * @param functionId 函数ID
     * @param code       代码文件
     * @param activate   是否激活（可选）
     * @param entrypoint 入口点（可选）
     * @param commands   构建命令（可选）
     * @return 部署信息
     */
    @POST("functions/{functionId}/deployments")
    @Multipart
    Call<Deployment> createDeployment(
            @Path("functionId") String functionId,
            @Part MultipartBody.Part code,
            @Part("activate") Boolean activate,
            @Part("entrypoint") String entrypoint,
            @Part("commands") String commands
    );

    /**
     * 获取部署列表
     *
     * @param functionId 函数ID
     * @return 部署列表
     */
    @GET("functions/{functionId}/deployments")
    Call<List<Deployment>> listDeployments(@Path("functionId") String functionId);

    /**
     * 获取部署信息
     *
     * @param functionId   函数ID
     * @param deploymentId 部署ID
     * @return 部署信息
     */
    @GET("functions/{functionId}/deployments/{deploymentId}")
    Call<Deployment> getDeployment(
            @Path("functionId") String functionId,
            @Path("deploymentId") String deploymentId
    );

    /**
     * 删除部署
     *
     * @param functionId   函数ID
     * @param deploymentId 部署ID
     * @return 空响应
     */
    @DELETE("functions/{functionId}/deployments/{deploymentId}")
    Call<Void> deleteDeployment(
            @Path("functionId") String functionId,
            @Path("deploymentId") String deploymentId
    );

    /**
     * 执行函数
     *
     * @param functionId 函数ID
     * @param data       执行参数（可选）
     * @param async      是否异步执行（可选）
     * @return 执行结果
     */
    @POST("functions/{functionId}/executions")
    Call<Execution> createExecution(
            @Path("functionId") String functionId,
            @Body Map<String, Object> data,
            @Query("async") Boolean async
    );

    /**
     * 获取执行记录列表
     *
     * @param functionId 函数ID
     * @return 执行记录列表
     */
    @GET("functions/{functionId}/executions")
    Call<List<Execution>> listExecutions(@Path("functionId") String functionId);

    /**
     * 获取执行记录
     *
     * @param functionId   函数ID
     * @param executionId  执行ID
     * @return 执行记录
     */
    @GET("functions/{functionId}/executions/{executionId}")
    Call<Execution> getExecution(
            @Path("functionId") String functionId,
            @Path("executionId") String executionId
    );
} 