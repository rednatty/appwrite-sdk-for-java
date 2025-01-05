package online.bingzi.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Appwrite 存储桶模型类
 * <p>
 * 该类表示 Appwrite 存储系统中的存储桶（Bucket），用于管理文件存储的容器。
 * 每个存储桶可以配置不同的安全策略、文件限制和处理选项。
 * 通过 StorageService 的相关方法可以创建、管理和删除存储桶。
 * </p>
 *
 * <h2>使用示例：</h2>
 * <pre>
 * // 通过 StorageService 创建新存储桶
 * StorageService storageService = client.createService(StorageService.class);
 *
 * // 创建存储桶
 * Call<Bucket> createCall = storageService.createBucket(
 *     "images",                    // 存储桶名称
 *     "图片存储",                   // 存储桶说明
 *     ["image/jpeg", "image/png"], // 允许的文件类型
 *     5242880L,                    // 最大文件大小（5MB）
 *     ["user:read"],               // 读取权限
 *     ["user:write"]               // 写入权限
 * );
 *
 * createCall.enqueue(new Callback<Bucket>() {
 *     @Override
 *     public void onResponse(Call<Bucket> call, Response<Bucket> response) {
 *         Bucket bucket = response.body();
 *         if (bucket != null) {
 *             System.out.println("存储桶ID: " + bucket.getId());
 *             System.out.println("存储桶名称: " + bucket.getName());
 *             System.out.println("最大文件大小: " + bucket.getMaximumFileSize() + " bytes");
 *             System.out.println("允许的文件类型: " + String.join(", ", bucket.getAllowedFileExtensions()));
 *             System.out.println("加密状态: " + (bucket.isEncryption() ? "启用" : "禁用"));
 *             System.out.println("防病毒扫描: " + (bucket.isAntivirus() ? "启用" : "禁用"));
 *         }
 *     }
 * });
 * </pre>
 *
 * <h2>字段说明：</h2>
 * <ul>
 *   <li>id: 存储桶的唯一标识符</li>
 *   <li>createdAt: 存储桶创建时间（UTC ISO 8601格式）</li>
 *   <li>updatedAt: 存储桶最后更新时间（UTC ISO 8601格式）</li>
 *   <li>permissions: 存储桶的访问权限列表</li>
 *   <li>name: 存储桶名称</li>
 *   <li>enabled: 存储桶是否启用</li>
 *   <li>maximumFileSize: 允许上传的最大文件大小（字节）</li>
 *   <li>allowedFileExtensions: 允许上传的文件类型列表</li>
 *   <li>compression: 文件压缩方式</li>
 *   <li>encryption: 是否启用文件加密</li>
 *   <li>antivirus: 是否启用防病毒扫描</li>
 * </ul>
 *
 * <h2>注意事项：</h2>
 * <ul>
 *   <li>存储桶名称在项目中必须唯一</li>
 *   <li>文件大小限制不能超过项目设置的最大值</li>
 *   <li>启用加密和防病毒扫描可能会影响文件上传和下载的性能</li>
 *   <li>建议根据实际需求配置允许的文件类型，以提高安全性</li>
 * </ul>
 */
public class Bucket {
    /**
     * 存储桶的唯一标识符
     * 由系统自动生成，格式为字符串
     * 例如："6443f7eaef744"
     */
    @SerializedName("$id")
    private String id;

    /**
     * 存储桶创建时间
     * UTC ISO 8601格式的时间戳
     * 例如：2023-01-01T12:00:00.000Z
     */
    @SerializedName("$createdAt")
    private String createdAt;

    /**
     * 存储桶最后更新时间
     * UTC ISO 8601格式的时间戳
     * 例如：2023-01-01T12:00:00.000Z
     */
    @SerializedName("$updatedAt")
    private String updatedAt;

    /**
     * 存储桶的访问权限列表
     * 包含可以访问该存储桶的角色或用户ID
     * 例如：["user:read", "user:write", "user:123"]
     */
    @SerializedName("$permissions")
    private List<String> permissions;

    /**
     * 存储桶名称
     * 用于标识和描述存储桶的用途
     * 在项目中必须唯一
     */
    @SerializedName("name")
    private String name;

    /**
     * 存储桶状态
     * true: 存储桶已启用，可以正常访问
     * false: 存储桶已禁用，无法访问其中的文件
     */
    @SerializedName("enabled")
    private boolean enabled;

    /**
     * 允许上传的最大文件大小
     * 单位：字节
     * 例如：5242880（5MB）
     */
    @SerializedName("maximumFileSize")
    private long maximumFileSize;

    /**
     * 允许上传的文件类型列表
     * 包含文件的MIME类型或扩展名
     * 例如：["image/jpeg", "image/png", ".pdf"]
     */
    @SerializedName("allowedFileExtensions")
    private List<String> allowedFileExtensions;

    /**
     * 文件压缩方式
     * 可选值：
     * - "none": 不压缩
     * - "gzip": GZIP压缩
     * - "zstd": Zstandard压缩
     */
    @SerializedName("compression")
    private String compression;

    /**
     * 文件加密状态
     * true: 启用文件加密
     * false: 禁用文件加密
     */
    @SerializedName("encryption")
    private boolean encryption;

    /**
     * 防病毒扫描状态
     * true: 启用防病毒扫描
     * false: 禁用防病毒扫描
     */
    @SerializedName("antivirus")
    private boolean antivirus;

    /**
     * 获取存储桶ID
     *
     * @return 存储桶的唯一标识符
     */
    public String getId() {
        return id;
    }

    /**
     * 获取存储桶创建时间
     *
     * @return UTC ISO 8601格式的创建时间
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 获取存储桶最后更新时间
     *
     * @return UTC ISO 8601格式的更新时间
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 获取存储桶的访问权限列表
     *
     * @return 包含权限定义的字符串列表
     */
    public List<String> getPermissions() {
        return permissions;
    }

    /**
     * 获取存储桶名称
     *
     * @return 存储桶的名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取存储桶状态
     *
     * @return true表示存储桶已启用，false表示已禁用
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * 获取允许上传的最大文件大小
     *
     * @return 最大文件大小（字节）
     */
    public long getMaximumFileSize() {
        return maximumFileSize;
    }

    /**
     * 获取允许上传的文件类型列表
     *
     * @return 包含允许的文件类型的列表
     */
    public List<String> getAllowedFileExtensions() {
        return allowedFileExtensions;
    }

    /**
     * 获取文件压缩方式
     *
     * @return 压缩方式（"none", "gzip", "zstd"）
     */
    public String getCompression() {
        return compression;
    }

    /**
     * 获取文件加密状态
     *
     * @return true表示启用加密，false表示禁用
     */
    public boolean isEncryption() {
        return encryption;
    }

    /**
     * 获取防病毒扫描状态
     *
     * @return true表示启用防病毒扫描，false表示禁用
     */
    public boolean isAntivirus() {
        return antivirus;
    }
} 