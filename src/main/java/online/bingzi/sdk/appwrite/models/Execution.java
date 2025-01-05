package online.bingzi.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;

/**
 * Appwrite 函数执行记录模型类
 * <p>
 * 该类表示 Appwrite 平台中云函数的一次执行记录，包含执行的详细信息和结果。
 * 每次函数执行都会生成一条执行记录，记录执行的触发方式、状态、输出等信息。
 * 通过 FunctionService 的相关方法可以获取和管理函数的执行记录。
 * </p>
 *
 * <h2>使用示例：</h2>
 * <pre>
 * // 通过 FunctionService 获取函数执行记录
 * FunctionService functionService = client.createService(FunctionService.class);
 *
 * // 获取特定执行记录
 * Call<Execution> getCall = functionService.getExecution(
 *     "function_id",    // 函数ID
 *     "execution_id"    // 执行记录ID
 * );
 *
 * getCall.enqueue(new Callback<Execution>() {
 *     @Override
 *     public void onResponse(Call<Execution> call, Response<Execution> response) {
 *         Execution execution = response.body();
 *         if (execution != null) {
 *             System.out.println("执行ID: " + execution.getId());
 *             System.out.println("函数ID: " + execution.getFunctionId());
 *             System.out.println("触发方式: " + execution.getTrigger());
 *             System.out.println("执行状态: " + execution.getStatus());
 *             System.out.println("状态码: " + execution.getStatusCode());
 *             System.out.println("执行时长: " + execution.getDuration() + "秒");
 *
 *             // 检查执行结果
 *             if (execution.getStatusCode() == 200) {
 *                 System.out.println("执行成功:");
 *                 System.out.println("返回值: " + execution.getResponse());
 *                 System.out.println("标准输出: " + execution.getStdout());
 *             } else {
 *                 System.out.println("执行失败:");
 *                 System.out.println("错误输出: " + execution.getStderr());
 *             }
 *         }
 *     }
 * });
 * </pre>
 *
 * <h2>字段说明：</h2>
 * <ul>
 *   <li>id: 执行记录的唯一标识符</li>
 *   <li>createdAt: 执行开始时间（UTC ISO 8601格式）</li>
 *   <li>updatedAt: 执行结束时间（UTC ISO 8601格式）</li>
 *   <li>functionId: 被执行的函数ID</li>
 *   <li>trigger: 触发执行的方式（http, schedule, event等）</li>
 *   <li>status: 执行状态（completed, failed等）</li>
 *   <li>statusCode: HTTP状态码</li>
 *   <li>response: 函数返回的数据</li>
 *   <li>stdout: 标准输出内容</li>
 *   <li>stderr: 标准错误输出</li>
 *   <li>duration: 执行持续时间（秒）</li>
 * </ul>
 *
 * <h2>注意事项：</h2>
 * <ul>
 *   <li>执行记录会保留一定时间，可用于调试和审计</li>
 *   <li>状态码200表示执行成功，其他状态码表示执行出现问题</li>
 *   <li>执行时长不能超过函数配置的超时时间</li>
 *   <li>标准输出和错误输出的内容长度可能会被截断</li>
 * </ul>
 */
public class Execution {
    /**
     * 执行记录的唯一标识符
     * 由系统自动生成，格式为字符串
     * 例如："6443f7eaef744"
     */
    @SerializedName("$id")
    private String id;

    /**
     * 执行开始时间
     * UTC ISO 8601格式的时间戳
     * 例如：2023-01-01T12:00:00.000Z
     */
    @SerializedName("$createdAt")
    private String createdAt;

    /**
     * 执行结束时间
     * UTC ISO 8601格式的时间戳
     * 例如：2023-01-01T12:00:10.000Z
     */
    @SerializedName("$updatedAt")
    private String updatedAt;

    /**
     * 被执行的函数ID
     * 标识是哪个函数的执行记录
     */
    @SerializedName("functionId")
    private String functionId;

    /**
     * 触发执行的方式
     * 可能的值：
     * - "http": HTTP调用触发
     * - "schedule": 定时任务触发
     * - "event": 系统事件触发
     */
    @SerializedName("trigger")
    private String trigger;

    /**
     * 执行状态
     * 可能的值：
     * - "processing": 正在执行
     * - "completed": 执行完成
     * - "failed": 执行失败
     */
    @SerializedName("status")
    private String status;

    /**
     * HTTP状态码
     * - 200: 执行成功
     * - 400: 请求错误
     * - 500: 执行出错
     */
    @SerializedName("statusCode")
    private int statusCode;

    /**
     * 函数返回的数据
     * 通常是JSON格式的字符串
     */
    @SerializedName("response")
    private String response;

    /**
     * 标准输出内容
     * 函数执行过程中的console.log输出
     */
    @SerializedName("stdout")
    private String stdout;

    /**
     * 标准错误输出
     * 函数执行过程中的错误信息
     */
    @SerializedName("stderr")
    private String stderr;

    /**
     * 执行持续时间
     * 单位：秒
     * 从开始到结束的实际执行时长
     */
    @SerializedName("duration")
    private double duration;

    /**
     * 获取执行记录ID
     *
     * @return 执行记录的唯一标识符
     */
    public String getId() {
        return id;
    }

    /**
     * 获取执行开始时间
     *
     * @return UTC ISO 8601格式的开始时间
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 获取执行结束时间
     *
     * @return UTC ISO 8601格式的结束时间
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 获取函数ID
     *
     * @return 被执行的函数ID
     */
    public String getFunctionId() {
        return functionId;
    }

    /**
     * 获取触发方式
     *
     * @return 触发执行的方式（http/schedule/event）
     */
    public String getTrigger() {
        return trigger;
    }

    /**
     * 获取执行状态
     *
     * @return 当前的执行状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 获取状态码
     *
     * @return HTTP状态码
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * 获取函数返回数据
     *
     * @return 函数执行的返回值
     */
    public String getResponse() {
        return response;
    }

    /**
     * 获取标准输出内容
     *
     * @return 执行过程的标准输出
     */
    public String getStdout() {
        return stdout;
    }

    /**
     * 获取标准错误输出
     *
     * @return 执行过程的错误输出
     */
    public String getStderr() {
        return stderr;
    }

    /**
     * 获取执行持续时间
     *
     * @return 执行时长（秒）
     */
    public double getDuration() {
        return duration;
    }
} 