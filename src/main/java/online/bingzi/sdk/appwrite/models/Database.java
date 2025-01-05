package online.bingzi.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;

/**
 * Appwrite 数据库模型类
 * <p>
 * 该类表示 Appwrite 系统中的数据库实例，用于管理数据库的基本信息和状态。
 * 通过 DatabaseService 的相关方法可以创建、管理和删除数据库。
 * 每个数据库可以包含多个集合（Collection），用于组织和存储不同类型的数据。
 * </p>
 *
 * <h2>使用示例：</h2>
 * <pre>
 * // 通过 DatabaseService 创建新数据库
 * DatabaseService databaseService = client.createService(DatabaseService.class);
 *
 * // 创建数据库
 * Call<Database> createCall = databaseService.createDatabase(
 *     "user_data",    // 数据库名称
 *     "用户数据库"     // 数据库说明
 * );
 *
 * createCall.enqueue(new Callback<Database>() {
 *     @Override
 *     public void onResponse(Call<Database> call, Response<Database> response) {
 *         Database database = response.body();
 *         if (database != null) {
 *             System.out.println("数据库ID: " + database.getId());
 *             System.out.println("数据库名称: " + database.getName());
 *             System.out.println("创建时间: " + database.getCreatedAt());
 *             System.out.println("状态: " + (database.isEnabled() ? "启用" : "禁用"));
 *         }
 *     }
 * });
 *
 * // 获取数据库信息
 * Call<Database> getCall = databaseService.getDatabase("database_id");
 * getCall.enqueue(new Callback<Database>() {
 *     @Override
 *     public void onResponse(Call<Database> call, Response<Database> response) {
 *         Database database = response.body();
 *         // 处理数据库信息
 *     }
 * });
 * </pre>
 *
 * <h2>字段说明：</h2>
 * <ul>
 *   <li>id: 数据库的唯一标识符</li>
 *   <li>createdAt: 数据库创建时间（UTC ISO 8601格式）</li>
 *   <li>updatedAt: 数据库最后更新时间（UTC ISO 8601格式）</li>
 *   <li>name: 数据库名称</li>
 *   <li>enabled: 数据库是否启用</li>
 * </ul>
 *
 * <h2>注意事项：</h2>
 * <ul>
 *   <li>数据库名称在项目中必须唯一</li>
 *   <li>禁用数据库后，其中的数据仍然保留，但无法访问</li>
 *   <li>建议在创建数据库时使用有意义的名称，便于管理和维护</li>
 * </ul>
 */
public class Database {
    /**
     * 数据库的唯一标识符
     * 由系统自动生成，格式为字符串
     * 例如："6443f7eaef744"
     */
    @SerializedName("$id")
    private String id;

    /**
     * 数据库创建时间
     * UTC ISO 8601格式的时间戳
     * 例如：2023-01-01T12:00:00.000Z
     */
    @SerializedName("$createdAt")
    private String createdAt;

    /**
     * 数据库最后更新时间
     * UTC ISO 8601格式的时间戳
     * 例如：2023-01-01T12:00:00.000Z
     */
    @SerializedName("$updatedAt")
    private String updatedAt;

    /**
     * 数据库名称
     * 用于标识和描述数据库的用途
     * 在项目中必须唯一
     */
    @SerializedName("name")
    private String name;

    /**
     * 数据库状态
     * true: 数据库已启用，可以正常访问
     * false: 数据库已禁用，无法访问其中的数据
     */
    @SerializedName("enabled")
    private boolean enabled;

    /**
     * 获取数据库ID
     *
     * @return 数据库的唯一标识符
     */
    public String getId() {
        return id;
    }

    /**
     * 获取数据库创建时间
     *
     * @return UTC ISO 8601格式的创建时间
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 获取数据库最后更新时间
     *
     * @return UTC ISO 8601格式的更新时间
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 获取数据库名称
     *
     * @return 数据库的名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取数据库状态
     *
     * @return true表示数据库已启用，false表示已禁用
     */
    public boolean isEnabled() {
        return enabled;
    }
} 