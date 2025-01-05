package online.bingzi.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;

/**
 * Appwrite 函数部署模型类
 * <p>
 * 该类表示 Appwrite 平台中云函数的一次部署记录，包含部署的详细信息和构建状态。
 * 每次函数代码更新都会创建一个新的部署版本，记录构建过程和结果。
 * 通过 FunctionService 的相关方法可以创建、管理和激活部署。
 * </p>
 *
 * <h2>使用示例：</h2>
 * <pre>
 * // 通过 FunctionService 创建新部署
 * FunctionService functionService = client.createService(FunctionService.class);
 *
 * // 准备部署文件
 * java.io.File codeZip = new java.io.File("function.zip");
 * RequestBody requestFile = RequestBody.create(MediaType.parse("application/zip"), codeZip);
 *
 * // 创建部署
 * Call<Deployment> createCall = functionService.createDeployment(
 *     "function_id",     // 函数ID
 *     requestFile,       // 代码文件
 *     "index.js",        // 入口文件
 *     true              // 是否自动激活
 * );
 *
 * createCall.enqueue(new Callback<Deployment>() {
 *     @Override
 *     public void onResponse(Call<Deployment> call, Response<Deployment> response) {
 *         Deployment deployment = response.body();
 *         if (deployment != null) {
 *             System.out.println("部署ID: " + deployment.getId());
 *             System.out.println("入口文件: " + deployment.getEntrypoint());
 *             System.out.println("构建状态: " + deployment.getStatus());
 *             System.out.println("构建时间: " + deployment.getBuildTime() + "秒");
 *             System.out.println("部署大小: " + deployment.getSize() + "字节");
 *
 *             // 检查构建日志
 *             String buildLogs = deployment.getBuildLogs();
 *             if (deployment.getStatus().equals("ready")) {
 *                 System.out.println("部署成功，已" + 
 *                     (deployment.isActivate() ? "激活" : "待激活"));
 *             } else {
 *                 System.out.println("构建失败，错误日志:");
 *                 System.out.println(buildLogs);
 *             }
 *         }
 *     }
 * });
 * </pre>
 *
 * <h2>字段说明：</h2>
 * <ul>
 *   <li>id: 部署的唯一标识符</li>
 *   <li>createdAt: 部署创建时间（UTC ISO 8601格式）</li>
 *   <li>updatedAt: 部署最后更新时间（UTC ISO 8601格式）</li>
 *   <li>type: 部署类型（通常是"deployment"）</li>
 *   <li>resourceId: 关联的资源ID（函数ID）</li>
 *   <li>resourceType: 资源类型（通常是"functions"）</li>
 *   <li>entrypoint: 函数入口文件</li>
 *   <li>size: 部署包大小（字节）</li>
 *   <li>buildId: 构建任务ID</li>
 *   <li>activate: 是否自动激活</li>
 *   <li>status: 部署状态</li>
 *   <li>buildLogs: 构建过程日志</li>
 *   <li>buildTime: 构建耗时（秒）</li>
 * </ul>
 *
 * <h2>注意事项：</h2>
 * <ul>
 *   <li>部署包大小不能超过平台限制</li>
 *   <li>构建过程可能需要几分钟完成</li>
 *   <li>只有构建成功的部署才能被激活</li>
 *   <li>每个函数可以保留多个部署版本</li>
 *   <li>旧的部署版本会在一定时间后自动清理</li>
 * </ul>
 */
public class Deployment {
    /**
     * 部署的唯一标识符
     * 由系统自动生成，格式为字符串
     * 例如："6443f7eaef744"
     */
    @SerializedName("$id")
    private String id;

    /**
     * 部署创建时间
     * UTC ISO 8601格式的时间戳
     * 例如：2023-01-01T12:00:00.000Z
     */
    @SerializedName("$createdAt")
    private String createdAt;

    /**
     * 部署最后更新时间
     * UTC ISO 8601格式的时间戳
     * 例如：2023-01-01T12:00:10.000Z
     */
    @SerializedName("$updatedAt")
    private String updatedAt;

    /**
     * 部署类型
     * 标识资源的类型
     * 通常为"deployment"
     */
    @SerializedName("type")
    private String type;

    /**
     * 关联的资源ID
     * 通常是函数的ID
     */
    @SerializedName("resourceId")
    private String resourceId;

    /**
     * 资源类型
     * 标识部署关联的资源类型
     * 通常为"functions"
     */
    @SerializedName("resourceType")
    private String resourceType;

    /**
     * 函数入口文件
     * 指定函数的主文件路径
     * 例如："index.js", "main.py"
     */
    @SerializedName("entrypoint")
    private String entrypoint;

    /**
     * 部署包大小
     * 单位：字节
     * 包含所有代码和依赖文件
     */
    @SerializedName("size")
    private long size;

    /**
     * 构建任务ID
     * 用于标识构建过程
     */
    @SerializedName("buildId")
    private String buildId;

    /**
     * 是否自动激活
     * true: 构建成功后自动激活
     * false: 需要手动激活
     */
    @SerializedName("activate")
    private boolean activate;

    /**
     * 部署状态
     * 可能的值：
     * - "building": 正在构建
     * - "ready": 构建完成
     * - "failed": 构建失败
     */
    @SerializedName("status")
    private String status;

    /**
     * 构建过程日志
     * 包含构建过程的详细输出
     */
    @SerializedName("buildLogs")
    private String buildLogs;

    /**
     * 构建耗时
     * 单位：秒
     * 从开始构建到完成的时间
     */
    @SerializedName("buildTime")
    private int buildTime;

    /**
     * 获取部署ID
     *
     * @return 部署的唯一标识符
     */
    public String getId() {
        return id;
    }

    /**
     * 获取部署创建时间
     *
     * @return UTC ISO 8601格式的创建时间
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 获取部署最后更新时间
     *
     * @return UTC ISO 8601格式的更新时间
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 获取部署类型
     *
     * @return 部署的类型标识符
     */
    public String getType() {
        return type;
    }

    /**
     * 获取关联的资源ID
     *
     * @return 函数的ID
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * 获取资源类型
     *
     * @return 资源的类型标识符
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * 获取入口文件路径
     *
     * @return 函数的入口文件路径
     */
    public String getEntrypoint() {
        return entrypoint;
    }

    /**
     * 获取部署包大小
     *
     * @return 部署包大小（字节）
     */
    public long getSize() {
        return size;
    }

    /**
     * 获取构建任务ID
     *
     * @return 构建的唯一标识符
     */
    public String getBuildId() {
        return buildId;
    }

    /**
     * 获取自动激活状态
     *
     * @return true表示自动激活，false表示手动激活
     */
    public boolean isActivate() {
        return activate;
    }

    /**
     * 获取部署状态
     *
     * @return 当前的部署状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 获取构建日志
     *
     * @return 构建过程的详细日志
     */
    public String getBuildLogs() {
        return buildLogs;
    }

    /**
     * 获取构建耗时
     *
     * @return 构建时间（秒）
     */
    public int getBuildTime() {
        return buildTime;
    }
} 