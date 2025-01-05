package online.bingzi.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Appwrite 文档模型类
 * <p>
 * 该类表示 Appwrite 数据库集合中的文档，是实际存储数据的容器。
 * 文档是一个通用类型，可以存储任意类型的数据结构。
 * 通过 DatabaseService 的相关方法可以创建、查询、更新和删除文档。
 * </p>
 *
 * <h2>使用示例：</h2>
 * <pre>
 * // 定义用户数据结构
 * public class UserData {
 *     private String name;
 *     private String email;
 *     private int age;
 *
 *     // getters and setters
 * }
 *
 * // 通过 DatabaseService 创建新文档
 * DatabaseService databaseService = client.createService(DatabaseService.class);
 *
 * // 创建文档
 * UserData userData = new UserData();
 * userData.setName("张三");
 * userData.setEmail("zhangsan@example.com");
 * userData.setAge(25);
 *
 * Call<Document<UserData>> createCall = databaseService.createDocument(
 *     "database_id",     // 数据库ID
 *     "collection_id",   // 集合ID
 *     "unique()",        // 文档ID（可选）
 *     userData,          // 文档数据
 *     ["user:read"],     // 读取权限
 *     ["user:write"]     // 写入权限
 * );
 *
 * createCall.enqueue(new Callback<Document<UserData>>() {
 *     @Override
 *     public void onResponse(Call<Document<UserData>> call, Response<Document<UserData>> response) {
 *         Document<UserData> document = response.body();
 *         if (document != null) {
 *             System.out.println("文档ID: " + document.getId());
 *             System.out.println("创建时间: " + document.getCreatedAt());
 *
 *             // 获取文档数据
 *             UserData data = document.getData();
 *             System.out.println("用户名: " + data.getName());
 *             System.out.println("邮箱: " + data.getEmail());
 *             System.out.println("年龄: " + data.getAge());
 *
 *             // 获取文档权限
 *             List<String> permissions = document.getPermissions();
 *             System.out.println("权限: " + String.join(", ", permissions));
 *         }
 *     }
 * });
 * </pre>
 *
 * <h2>字段说明：</h2>
 * <ul>
 *   <li>id: 文档的唯一标识符</li>
 *   <li>createdAt: 文档创建时间（UTC ISO 8601格式）</li>
 *   <li>updatedAt: 文档最后更新时间（UTC ISO 8601格式）</li>
 *   <li>collectionId: 所属集合的ID</li>
 *   <li>databaseId: 所属数据库的ID</li>
 *   <li>permissions: 文档的访问权限列表</li>
 *   <li>data: 文档的实际数据内容，类型由泛型T决定</li>
 * </ul>
 *
 * <h2>注意事项：</h2>
 * <ul>
 *   <li>文档ID可以自动生成，也可以在创建时指定</li>
 *   <li>文档的数据结构必须符合集合中定义的属性规则</li>
 *   <li>权限可以在文档级别控制，实现细粒度的访问控制</li>
 *   <li>更新文档时，只会修改提供的字段，其他字段保持不变</li>
 * </ul>
 *
 * @param <T> 文档数据的类型，必须是一个可序列化的类型
 */
public class Document<T> {
    /**
     * 文档的唯一标识符
     * 由系统自动生成或在创建时指定
     * 例如："6443f7eaef744"
     */
    @SerializedName("$id")
    private String id;

    /**
     * 文档创建时间
     * UTC ISO 8601格式的时间戳
     * 例如：2023-01-01T12:00:00.000Z
     */
    @SerializedName("$createdAt")
    private String createdAt;

    /**
     * 文档最后更新时间
     * UTC ISO 8601格式的时间戳
     * 例如：2023-01-01T12:00:00.000Z
     */
    @SerializedName("$updatedAt")
    private String updatedAt;

    /**
     * 所属集合的ID
     * 标识该文档属于哪个集合
     */
    @SerializedName("$collectionId")
    private String collectionId;

    /**
     * 所属数据库的ID
     * 标识该文档属于哪个数据库
     */
    @SerializedName("$databaseId")
    private String databaseId;

    /**
     * 文档的访问权限列表
     * 包含可以访问该文档的角色或用户ID
     * 例如：["user:read", "user:write", "user:123"]
     */
    @SerializedName("$permissions")
    private List<String> permissions;

    /**
     * 文档的实际数据内容
     * 类型由泛型T决定，必须符合集合的属性定义
     */
    @SerializedName("data")
    private T data;

    /**
     * 获取文档ID
     *
     * @return 文档的唯一标识符
     */
    public String getId() {
        return id;
    }

    /**
     * 获取文档创建时间
     *
     * @return UTC ISO 8601格式的创建时间
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 获取文档最后更新时间
     *
     * @return UTC ISO 8601格式的更新时间
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 获取所属集合ID
     *
     * @return 集合的唯一标识符
     */
    public String getCollectionId() {
        return collectionId;
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
     * 获取文档的访问权限列表
     *
     * @return 包含权限定义的字符串列表
     */
    public List<String> getPermissions() {
        return permissions;
    }

    /**
     * 获取文档的实际数据内容
     *
     * @return 类型为T的文档数据对象
     */
    public T getData() {
        return data;
    }
} 