package online.bingzi.sdk.appwrite.services;

import online.bingzi.sdk.appwrite.models.File;
import online.bingzi.sdk.appwrite.models.Bucket;
import retrofit2.Call;
import retrofit2.http.*;
import java.util.List;

/**
 * 存储服务接口
 */
public interface StorageService {
    /**
     * 创建存储桶
     */
    @FormUrlEncoded
    @POST("storage/buckets")
    Call<Bucket> createBucket(
            @Field("bucketId") String bucketId,
            @Field("name") String name,
            @Field("permissions") List<String> permissions,
            @Field("maximumFileSize") Long maximumFileSize,
            @Field("allowedFileExtensions") List<String> allowedFileExtensions,
            @Field("encryption") Boolean encryption,
            @Field("antivirus") Boolean antivirus
    );

    /**
     * 获取存储桶列表
     */
    @GET("storage/buckets")
    Call<List<Bucket>> listBuckets();

    /**
     * 获取存储桶信息
     */
    @GET("storage/buckets/{bucketId}")
    Call<Bucket> getBucket(@Path("bucketId") String bucketId);

    /**
     * 删除存储桶
     */
    @DELETE("storage/buckets/{bucketId}")
    Call<Void> deleteBucket(@Path("bucketId") String bucketId);

    /**
     * 上传文件
     */
    @Multipart
    @POST("storage/buckets/{bucketId}/files")
    Call<File> createFile(
            @Path("bucketId") String bucketId,
            @Part okhttp3.MultipartBody.Part file,
            @Part("fileId") String fileId,
            @Part("permissions") List<String> permissions
    );

    /**
     * 获取文件列表
     */
    @GET("storage/buckets/{bucketId}/files")
    Call<List<File>> listFiles(@Path("bucketId") String bucketId);

    /**
     * 获取文件信息
     */
    @GET("storage/buckets/{bucketId}/files/{fileId}")
    Call<File> getFile(
            @Path("bucketId") String bucketId,
            @Path("fileId") String fileId
    );

    /**
     * 删除文件
     */
    @DELETE("storage/buckets/{bucketId}/files/{fileId}")
    Call<Void> deleteFile(
            @Path("bucketId") String bucketId,
            @Path("fileId") String fileId
    );
} 