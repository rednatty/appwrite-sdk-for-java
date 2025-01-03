package online.bingzi.sdk.appwrite.services;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import online.bingzi.sdk.appwrite.models.Bucket;
import online.bingzi.sdk.appwrite.models.File;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * 存储服务接口
 */
public interface StorageService {
    /**
     * 创建存储桶
     *
     * @param name                  存储桶名称
     * @param id                    存储桶ID（可选）
     * @param permissions          权限列表（可选）
     * @param maximumFileSize      最大文件大小（可选）
     * @param allowedFileExtensions 允许的文件扩展名（可选）
     * @param compression          压缩方式（可选）
     * @param encryption          是否加密（可选）
     * @param antivirus           是否开启病毒扫描（可选）
     * @return 存储桶信息
     */
    @POST("storage/buckets")
    @FormUrlEncoded
    Call<Bucket> createBucket(
            @Field("name") String name,
            @Field("bucketId") String id,
            @Field("permissions") List<String> permissions,
            @Field("maximumFileSize") Long maximumFileSize,
            @Field("allowedFileExtensions") List<String> allowedFileExtensions,
            @Field("compression") String compression,
            @Field("encryption") Boolean encryption,
            @Field("antivirus") Boolean antivirus
    );

    /**
     * 获取存储桶列表
     *
     * @return 存储桶列表
     */
    @GET("storage/buckets")
    Call<List<Bucket>> listBuckets();

    /**
     * 获取存储桶信息
     *
     * @param bucketId 存储桶ID
     * @return 存储桶信息
     */
    @GET("storage/buckets/{bucketId}")
    Call<Bucket> getBucket(@Path("bucketId") String bucketId);

    /**
     * 删除存储桶
     *
     * @param bucketId 存储桶ID
     * @return 空响应
     */
    @DELETE("storage/buckets/{bucketId}")
    Call<Void> deleteBucket(@Path("bucketId") String bucketId);

    /**
     * 上传文件
     *
     * @param bucketId 存储桶ID
     * @param fileId   文件ID（可选）
     * @param file     文件数据
     * @return 文件信息
     */
    @POST("storage/buckets/{bucketId}/files")
    @Multipart
    Call<File> createFile(
            @Path("bucketId") String bucketId,
            @Query("fileId") String fileId,
            @Part MultipartBody.Part file
    );

    /**
     * 获取文件列表
     *
     * @param bucketId 存储桶ID
     * @return 文件列表
     */
    @GET("storage/buckets/{bucketId}/files")
    Call<List<File>> listFiles(@Path("bucketId") String bucketId);

    /**
     * 获取文件信息
     *
     * @param bucketId 存储桶ID
     * @param fileId   文件ID
     * @return 文件信息
     */
    @GET("storage/buckets/{bucketId}/files/{fileId}")
    Call<File> getFile(
            @Path("bucketId") String bucketId,
            @Path("fileId") String fileId
    );

    /**
     * 下载文件
     *
     * @param bucketId 存储桶ID
     * @param fileId   文件ID
     * @return 文件数据流
     */
    @GET("storage/buckets/{bucketId}/files/{fileId}/download")
    @Streaming
    Call<ResponseBody> downloadFile(
            @Path("bucketId") String bucketId,
            @Path("fileId") String fileId
    );

    /**
     * 删除文件
     *
     * @param bucketId 存储桶ID
     * @param fileId   文件ID
     * @return 空响应
     */
    @DELETE("storage/buckets/{bucketId}/files/{fileId}")
    Call<Void> deleteFile(
            @Path("bucketId") String bucketId,
            @Path("fileId") String fileId
    );

    /**
     * 获取文件预览
     *
     * @param bucketId 存储桶ID
     * @param fileId   文件ID
     * @param width    预览宽度（可选）
     * @param height   预览高度（可选）
     * @param gravity  裁剪重心（可选）
     * @param quality  图片质量（可选）
     * @param borderWidth 边框宽度（可选）
     * @param borderColor 边框颜色（可选）
     * @param borderRadius 边框圆角（可选）
     * @param opacity  透明度（可选）
     * @param rotation 旋转角度（可选）
     * @param background 背景颜色（可选）
     * @param output   输出格式（可选）
     * @return 预览数据流
     */
    @GET("storage/buckets/{bucketId}/files/{fileId}/preview")
    @Streaming
    Call<ResponseBody> getFilePreview(
            @Path("bucketId") String bucketId,
            @Path("fileId") String fileId,
            @Query("width") Integer width,
            @Query("height") Integer height,
            @Query("gravity") String gravity,
            @Query("quality") Integer quality,
            @Query("borderWidth") Integer borderWidth,
            @Query("borderColor") String borderColor,
            @Query("borderRadius") Integer borderRadius,
            @Query("opacity") Double opacity,
            @Query("rotation") Integer rotation,
            @Query("background") String background,
            @Query("output") String output
    );
} 