package online.bingzi.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Appwrite 文件模型类
 * <p>
 * 该类表示 Appwrite 存储系统中的文件对象，包含文件的元数据和状态信息。
 * 每个文件都属于特定的存储桶，并且可以通过分块上传的方式处理大文件。
 * 通过 StorageService 的相关方法可以上传、下载、更新和删除文件。
 * </p>
 *
 * <h2>使用示例：</h2>
 * <pre>
 * // 通过 StorageService 上传文件
 * StorageService storageService = client.createService(StorageService.class);
 *
 * // 准备文件数据
 * java.io.File localFile = new java.io.File("example.jpg");
 * RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), localFile);
 *
 * // 上传文件
 * Call<File> uploadCall = storageService.createFile(
 *     "bucket_id",        // 存储桶ID
 *     "unique()",         // 文件ID（可选）
 *     requestFile,        // 文件数据
 *     ["user:read"],      // 读取权限
 *     ["user:write"]      // 写入权限
 * );
 *
 * uploadCall.enqueue(new Callback<File>() {
 *     @Override
 *     public void onResponse(Call<File> call, Response<File> response) {
 *         File file = response.body();
 *         if (file != null) {
 *             System.out.println("文件ID: " + file.getId());
 *             System.out.println("文件名: " + file.getName());
 *             System.out.println("MIME类型: " + file.getMimeType());
 *             System.out.println("文件大小: " + file.getSizeOriginal() + " bytes");
 *             System.out.println("上传进度: " + file.getChunksUploaded() + "/" + file.getChunksTotal());
 *
 *             // 获取文件的签名，用于验证文件完整性
 *             String signature = file.getSignature();
 *             System.out.println("文件签名: " + signature);
 *         }
 *     }
 * });
 * </pre>
 *
 * <h2>字段说明：</h2>
 * <ul>
 *   <li>id: 文件的唯一标识符</li>
 *   <li>bucketId: 所属存储桶的ID</li>
 *   <li>createdAt: 文件创建时间（UTC ISO 8601格式）</li>
 *   <li>updatedAt: 文件最后更新时间（UTC ISO 8601格式）</li>
 *   <li>permissions: 文件的访问权限列表</li>
 *   <li>name: 文件名称</li>
 *   <li>signature: 文件的数字签名，用于验证完整性</li>
 *   <li>mimeType: 文件的MIME类型</li>
 *   <li>sizeOriginal: 文件的原始大小（字节）</li>
 *   <li>chunksTotal: 文件被分割的总块数</li>
 *   <li>chunksUploaded: 已上传的块数</li>
 * </ul>
 *
 * <h2>注意事项：</h2>
 * <ul>
 *   <li>文件大小不能超过存储桶设置的最大限制</li>
 *   <li>文件类型必须在存储桶允许的类型列表中</li>
 *   <li>大文件会自动进行分块上传，可以通过chunks相关字段监控上传进度</li>
 *   <li>文件签名可用于验证文件是否被篡改</li>
 * </ul>
 */
public class File {
    /**
     * 文件的唯一标识符
     * 由系统自动生成或在上传时指定
     * 例如："6443f7eaef744"
     */
    @SerializedName("$id")
    private String id;

    /**
     * 所属存储桶的ID
     * 标识文件存储在哪个存储桶中
     */
    @SerializedName("bucketId")
    private String bucketId;

    /**
     * 文件创建时间
     * UTC ISO 8601格式的时间戳
     * 例如：2023-01-01T12:00:00.000Z
     */
    @SerializedName("$createdAt")
    private String createdAt;

    /**
     * 文件最后更新时间
     * UTC ISO 8601格式的时间戳
     * 例如：2023-01-01T12:00:00.000Z
     */
    @SerializedName("$updatedAt")
    private String updatedAt;

    /**
     * 文件的访问权限列表
     * 包含可以访问该文件的角色或用户ID
     * 例如：["user:read", "user:write", "user:123"]
     */
    @SerializedName("$permissions")
    private List<String> permissions;

    /**
     * 文件名称
     * 包含文件名和扩展名
     * 例如："example.jpg"
     */
    @SerializedName("name")
    private String name;

    /**
     * 文件的数字签名
     * 用于验证文件的完整性和真实性
     * 通常是文件内容的哈希值
     */
    @SerializedName("signature")
    private String signature;

    /**
     * 文件的MIME类型
     * 表示文件的媒体类型
     * 例如："image/jpeg", "application/pdf"
     */
    @SerializedName("mimeType")
    private String mimeType;

    /**
     * 文件的原始大小
     * 单位：字节
     * 例如：1048576（1MB）
     */
    @SerializedName("sizeOriginal")
    private long sizeOriginal;

    /**
     * 文件被分割的总块数
     * 用于大文件的分块上传
     * 如果文件较小，通常为1
     */
    @SerializedName("chunksTotal")
    private int chunksTotal;

    /**
     * 已上传的块数
     * 用于跟踪分块上传的进度
     * 当等于chunksTotal时表示上传完成
     */
    @SerializedName("chunksUploaded")
    private int chunksUploaded;

    /**
     * 获取文件ID
     *
     * @return 文件的唯一标识符
     */
    public String getId() {
        return id;
    }

    /**
     * 获取所属存储桶ID
     *
     * @return 存储桶的唯一标识符
     */
    public String getBucketId() {
        return bucketId;
    }

    /**
     * 获取文件创建时间
     *
     * @return UTC ISO 8601格式的创建时间
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 获取文件最后更新时间
     *
     * @return UTC ISO 8601格式的更新时间
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 获取文件的访问权限列表
     *
     * @return 包含权限定义的字符串列表
     */
    public List<String> getPermissions() {
        return permissions;
    }

    /**
     * 获取文件名称
     *
     * @return 文件的完整名称（包含扩展名）
     */
    public String getName() {
        return name;
    }

    /**
     * 获取文件的数字签名
     *
     * @return 文件的签名字符串
     */
    public String getSignature() {
        return signature;
    }

    /**
     * 获取文件的MIME类型
     *
     * @return 文件的媒体类型
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * 获取文件的原始大小
     *
     * @return 文件大小（字节）
     */
    public long getSizeOriginal() {
        return sizeOriginal;
    }

    /**
     * 获取文件的总块数
     *
     * @return 分块上传的总块数
     */
    public int getChunksTotal() {
        return chunksTotal;
    }

    /**
     * 获取已上传的块数
     *
     * @return 已完成上传的块数
     */
    public int getChunksUploaded() {
        return chunksUploaded;
    }
} 