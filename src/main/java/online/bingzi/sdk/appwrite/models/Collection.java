package online.bingzi.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Appwrite 集合模型类
 * <p>
 * 该类表示 Appwrite 数据库中的集合（Collection），用于组织和存储特定类型的文档。
 * 集合类似于传统数据库中的表，可以定义属性（字段）和索引。
 * 通过 DatabaseService 的相关方法可以创建、管理和删除集合。
 * </p>
 *
 * <h2>使用示例：</h2>
 * <pre>
 * // 通过 DatabaseService 创建新集合
 * DatabaseService databaseService = client.createService(DatabaseService.class);
 *
 * // 创建集合
 * Call<Collection> createCall = databaseService.createCollection(
 *     "database_id",     // 数据库ID
 *     "users",          // 集合名称
 *     "用户信息集合",    // 集合说明
 *     ["user:read"],    // 读取权限
 *     ["user:write"]    // 写入权限
 * );
 *
 * createCall.enqueue(new Callback<Collection>() {
 *     @Override
 *     public void onResponse(Call<Collection> call, Response<Collection> response) {
 *         Collection collection = response.body();
 *         if (collection != null) {
 *             System.out.println("集合ID: " + collection.getId());
 *             System.out.println("集合名称: " + collection.getName());
 *             System.out.println("所属数据库: " + collection.getDatabaseId());
 *             System.out.println("状态: " + (collection.isEnabled() ? "启用" : "禁用"));
 *
 *             // 获取集合的属性定义
 *             List<Map<String, Object>> attributes = collection.getAttributes();
 *             for (Map<String, Object> attribute : attributes) {
 *                 System.out.println("属性名: " + attribute.get("key"));
 *                 System.out.println("属性类型: " + attribute.get("type"));
 *                 System.out.println("是否必填: " + attribute.get("required"));
 *             }
 *
 *             // 获取集合的索引定义
 *             List<Map<String, Object>> indexes = collection.getIndexes();
 *             for (Map<String, Object> index : indexes) {
 *                 System.out.println("索引名: " + index.get("key"));
 *                 System.out.println("索引类型: " + index.get("type"));
 *                 System.out.println("索引字段: " + index.get("attributes"));
 *             }
 *         }
 *     }
 * });
 * </pre>
 *
 * <h2>字段说明：</h2>
 * <ul>
 *   <li>id: 集合的唯一标识符</li>
 *   <li>createdAt: 集合创建时间（UTC ISO 8601格式）</li>
 *   <li>updatedAt: 集合最后更新时间（UTC ISO 8601格式）</li>
 *   <li>databaseId: 所属数据库的ID</li>
 *   <li>name: 集合名称</li>
 *   <li>enabled: 集合是否启用</li>
 *   <li>documentSecurity: 是否启用文档级安全控制</li>
 *   <li>attributes: 集合的属性（字段）定义列表</li>
 *   <li>indexes: 集合的索引定义列表</li>
 * </ul>
 *
 * <h2>注意事项：</h2>
 * <ul>
 *   <li>集合名称在数据库中必须唯一</li>
 *   <li>创建集合后，可以添加或修改属性，但某些修改可能会受到限制</li>
 *   <li>索引可以提高查询性能，但会占用额外存储空间</li>
 *   <li>启用文档级安全控制后，可以为每个文档设置独立的访问权限</li>
 * </ul>
 */
public class Collection {
    /**
     * 集合的唯一标识符
     * 由系统自动生成，格式为字符串
     * 例如："6443f7eaef744"
     */
    @SerializedName("$id")
    private String id;

    /**
     * 集合创建时间
     * UTC ISO 8601格式的时间戳
     * 例如：2023-01-01T12:00:00.000Z
     */
    @SerializedName("$createdAt")
    private String createdAt;

    /**
     * 集合最后更新时间
     * UTC ISO 8601格式的时间戳
     * 例如：2023-01-01T12:00:00.000Z
     */
    @SerializedName("$updatedAt")
    private String updatedAt;

    /**
     * 所属数据库的ID
     * 标识该集合属于哪个数据库
     */
    @SerializedName("databaseId")
    private String databaseId;

    /**
     * 集合名称
     * 用于标识和描述集合的用途
     * 在数据库中必须唯一
     */
    @SerializedName("name")
    private String name;

    /**
     * 集合状态
     * true: 集合已启用，可以正常访问
     * false: 集合已禁用，无法访问其中的数据
     */
    @SerializedName("enabled")
    private boolean enabled;

    /**
     * 文档级安全控制
     * true: 启用文档级安全控制，可以为每个文档设置独立的访问权限
     * false: 禁用文档级安全控制，使用集合级权限控制
     */
    @SerializedName("documentSecurity")
    private boolean documentSecurity;

    /**
     * 集合的属性（字段）定义列表
     * 每个属性是一个Map，包含以下键：
     * - key: 属性名称
     * - type: 属性类型（string, number, boolean等）
     * - required: 是否必填
     * - default: 默认值
     * - array: 是否是数组类型
     */
    @SerializedName("attributes")
    private List<Map<String, Object>> attributes;

    /**
     * 集合的索引定义列表
     * 每个索引是一个Map，包含以下键：
     * - key: 索引名称
     * - type: 索引类型（key, unique, fulltext）
     * - attributes: 索引包含的字段列表
     * - orders: 排序方式（ASC/DESC）
     */
    @SerializedName("indexes")
    private List<Map<String, Object>> indexes;

    /**
     * 获取集合ID
     *
     * @return 集合的唯一标识符
     */
    public String getId() {
        return id;
    }

    /**
     * 获取集合创建时间
     *
     * @return UTC ISO 8601格式的创建时间
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 获取集合最后更新时间
     *
     * @return UTC ISO 8601格式的更新时间
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 获取所属数据库ID
     *
     * @return 数据库的唯一标识符
     */
    public String getDatabaseId() {
        return databaseId;
    }

    /**
     * 获取集合名称
     *
     * @return 集合的名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取集合状态
     *
     * @return true表示集合已启用，false表示已禁用
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * 获取文档级安全控制状态
     *
     * @return true表示启用文档级安全控制，false表示禁用
     */
    public boolean isDocumentSecurity() {
        return documentSecurity;
    }

    /**
     * 获取集合的属性定义列表
     *
     * @return 包含所有属性定义的列表，每个属性是一个Map
     */
    public List<Map<String, Object>> getAttributes() {
        return attributes;
    }

    /**
     * 获取集合的索引定义列表
     *
     * @return 包含所有索引定义的列表，每个索引是一个Map
     */
    public List<Map<String, Object>> getIndexes() {
        return indexes;
    }
} 