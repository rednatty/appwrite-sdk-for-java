package online.bingzi.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Appwrite 云函数模型类
 * <p>
 * 该类表示 Appwrite 平台中的云函数，用于执行自定义的服务器端代码。
 * 每个函数可以配置不同的运行时环境、触发条件和执行参数。
 * 通过 FunctionService 的相关方法可以创建、部署和管理云函数。
 * </p>
 *
 * <h2>使用示例：</h2>
 * <pre>
 * // 通过 FunctionService 创建新函数
 * FunctionService functionService = client.createService(FunctionService.class);
 *
 * // 创建函数
 * Map<String, String> vars = new HashMap<>();
 * vars.put("API_KEY", "your-api-key");
 * vars.put("ENV", "production");
 *
 * Call<Function> createCall = functionService.createFunction(
 *     "image-resize",           // 函数名称
 *     ["node-18.0"],           // 运行时环境
 *     "index.js",              // 入口文件
 *     "npm install sharp",      // 安装依赖命令
 *     vars,                    // 环境变量
 *     ["storage.files.create"], // 触发事件
 *     "0 * * * *",            // 定时任务（每小时执行）
 *     30,                      // 超时时间（秒）
 *     ["user:read"]            // 执行权限
 * );
 *
 * createCall.enqueue(new Callback<Function>() {
 *     @Override
 *     public void onResponse(Call<Function> call, Response<Function> response) {
 *         Function function = response.body();
 *         if (function != null) {
 *             System.out.println("函数ID: " + function.getId());
 *             System.out.println("函数名称: " + function.getName());
 *             System.out.println("运行时环境: " + function.getRuntime());
 *             System.out.println("入口文件: " + function.getEntrypoint());
 *             System.out.println("当前版本: " + function.getVersion());
 *             System.out.println("状态: " + (function.isEnabled() ? "启用" : "禁用"));
 *
 *             // 获取环境变量
 *             Map<String, String> variables = function.getVariables();
 *             for (Map.Entry<String, String> entry : variables.entrySet()) {
 *                 System.out.println("环境变量: " + entry.getKey() + "=" + entry.getValue());
 *             }
 *         }
 *     }
 * });
 * </pre>
 *
 * <h2>字段说明：</h2>
 * <ul>
 *   <li>id: 函数的唯一标识符</li>
 *   <li>createdAt: 函数创建时间（UTC ISO 8601格式）</li>
 *   <li>updatedAt: 函数最后更新时间（UTC ISO 8601格式）</li>
 *   <li>execute: 函数的执行权限列表</li>
 *   <li>name: 函数名称</li>
 *   <li>enabled: 函数是否启用</li>
 *   <li>runtime: 运行时环境</li>
 *   <li>deployment: 当前部署的版本ID</li>
 *   <li>variables: 环境变量映射</li>
 *   <li>events: 触发函数的事件列表</li>
 *   <li>schedule: 定时任务表达式（Cron格式）</li>
 *   <li>timeout: 函数执行超时时间（秒）</li>
 *   <li>entrypoint: 函数入口文件</li>
 *   <li>commands: 安装依赖的命令</li>
 *   <li>version: 函数版本号</li>
 * </ul>
 *
 * <h2>注意事项：</h2>
 * <ul>
 *   <li>函数名称在项目中必须唯一</li>
 *   <li>运行时环境必须是平台支持的版本</li>
 *   <li>环境变量可以用于存储敏感信息，会被安全加密</li>
 *   <li>定时任务使用标准的Cron表达式格式</li>
 *   <li>超时时间不能超过平台限制（通常是900秒）</li>
 * </ul>
 */
public class Function {
    /**
     * 函数的唯一标识符
     * 由系统自动生成，格式为字符串
     * 例如："6443f7eaef744"
     */
    @SerializedName("$id")
    private String id;

    /**
     * 函数创建时间
     * UTC ISO 8601格式的时间戳
     * 例如：2023-01-01T12:00:00.000Z
     */
    @SerializedName("$createdAt")
    private String createdAt;

    /**
     * 函数最后更新时间
     * UTC ISO 8601格式的时间戳
     * 例如：2023-01-01T12:00:00.000Z
     */
    @SerializedName("$updatedAt")
    private String updatedAt;

    /**
     * 函数的执行权限列表
     * 包含可以执行该函数的角色或用户ID
     * 例如：["user:read", "user:123"]
     */
    @SerializedName("execute")
    private List<String> execute;

    /**
     * 函数名称
     * 用于标识和描述函数的用途
     * 在项目中必须唯一
     */
    @SerializedName("name")
    private String name;

    /**
     * 函数状态
     * true: 函数已启用，可以被触发执行
     * false: 函数已禁用，无法被触发
     */
    @SerializedName("enabled")
    private boolean enabled;

    /**
     * 运行时环境
     * 指定函数的执行环境
     * 例如："node-18.0", "python-3.9", "php-8.0"
     */
    @SerializedName("runtime")
    private String runtime;

    /**
     * 当前部署的版本ID
     * 标识函数当前运行的代码版本
     */
    @SerializedName("deployment")
    private String deployment;

    /**
     * 环境变量映射
     * 存储函数运行时可访问的环境变量
     * 键值对格式，值会被加密存储
     */
    @SerializedName("vars")
    private Map<String, String> variables;

    /**
     * 触发函数的事件列表
     * 定义什么事件会触发函数执行
     * 例如：["storage.files.create", "users.create"]
     */
    @SerializedName("events")
    private List<String> events;

    /**
     * 定时任务表达式
     * 使用Cron格式定义函数的定时执行规则
     * 例如："0 * * * *"（每小时执行）
     */
    @SerializedName("schedule")
    private String schedule;

    /**
     * 函数执行超时时间
     * 单位：秒
     * 超过此时间函数会被强制终止
     */
    @SerializedName("timeout")
    private int timeout;

    /**
     * 函数入口文件
     * 指定函数的主文件路径
     * 例如："index.js", "main.py"
     */
    @SerializedName("entrypoint")
    private String entrypoint;

    /**
     * 安装依赖的命令
     * 在部署时执行的命令，用于安装依赖
     * 例如："npm install", "pip install -r requirements.txt"
     */
    @SerializedName("commands")
    private String commands;

    /**
     * 函数版本号
     * 标识函数代码的版本
     * 例如："1.0.0"
     */
    @SerializedName("version")
    private String version;

    /**
     * 获取函数ID
     *
     * @return 函数的唯一标识符
     */
    public String getId() {
        return id;
    }

    /**
     * 获取函数创建时间
     *
     * @return UTC ISO 8601格式的创建时间
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 获取函数最后更新时间
     *
     * @return UTC ISO 8601格式的更新时间
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 获取函数的执行权限列表
     *
     * @return 包含权限定义的字符串列表
     */
    public List<String> getExecute() {
        return execute;
    }

    /**
     * 获取函数名称
     *
     * @return 函数的名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取函数状态
     *
     * @return true表示函数已启用，false表示已禁用
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * 获取运行时环境
     *
     * @return 函数的运行时环境标识符
     */
    public String getRuntime() {
        return runtime;
    }

    /**
     * 获取当前部署版本ID
     *
     * @return 当前运行的代码版本ID
     */
    public String getDeployment() {
        return deployment;
    }

    /**
     * 获取环境变量映射
     *
     * @return 包含环境变量的键值对Map
     */
    public Map<String, String> getVariables() {
        return variables;
    }

    /**
     * 获取触发事件列表
     *
     * @return 包含触发事件的字符串列表
     */
    public List<String> getEvents() {
        return events;
    }

    /**
     * 获取定时任务表达式
     *
     * @return Cron格式的定时任务表达式
     */
    public String getSchedule() {
        return schedule;
    }

    /**
     * 获取执行超时时间
     *
     * @return 超时时间（秒）
     */
    public int getTimeout() {
        return timeout;
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
     * 获取安装依赖命令
     *
     * @return 用于安装依赖的命令字符串
     */
    public String getCommands() {
        return commands;
    }

    /**
     * 获取函数版本号
     *
     * @return 函数的版本号
     */
    public String getVersion() {
        return version;
    }
} 