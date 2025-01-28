package lt.xnatty.sdk.appwrite.services;

import java.util.List;
import lt.xnatty.sdk.appwrite.models.Bucket;
import lt.xnatty.sdk.appwrite.models.File;
import retrofit2.Call;
import retrofit2.http.*;

public interface StorageService {

    @FormUrlEncoded
    @POST("storage/buckets")
    Call<Bucket> createBucket(
            @Field("bucketId") String bucketId,
            @Field("name") String name,
            @Field("permissions") List<String> permissions,
            @Field("maximumFileSize") Long maximumFileSize,
            @Field("allowedFileExtensions") List<String> allowedFileExtensions,
            @Field("encryption") Boolean encryption,
            @Field("antivirus") Boolean antivirus);

    @GET("storage/buckets")
    Call<List<Bucket>> listBuckets();

    @GET("storage/buckets/{bucketId}")
    Call<Bucket> getBucket(@Path("bucketId") String bucketId);

    @DELETE("storage/buckets/{bucketId}")
    Call<Void> deleteBucket(@Path("bucketId") String bucketId);

    @Multipart
    @POST("storage/buckets/{bucketId}/files")
    Call<File> createFile(
            @Path("bucketId") String bucketId,
            @Part okhttp3.MultipartBody.Part file,
            @Part("fileId") String fileId,
            @Part("permissions") List<String> permissions);

    @GET("storage/buckets/{bucketId}/files")
    Call<List<File>> listFiles(@Path("bucketId") String bucketId);

    @GET("storage/buckets/{bucketId}/files/{fileId}")
    Call<File> getFile(@Path("bucketId") String bucketId, @Path("fileId") String fileId);

    @DELETE("storage/buckets/{bucketId}/files/{fileId}")
    Call<Void> deleteFile(@Path("bucketId") String bucketId, @Path("fileId") String fileId);
}
