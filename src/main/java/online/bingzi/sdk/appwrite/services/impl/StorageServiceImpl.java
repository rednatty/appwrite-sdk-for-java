package online.bingzi.sdk.appwrite.services.impl;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import online.bingzi.sdk.appwrite.Client;
import online.bingzi.sdk.appwrite.models.Bucket;
import online.bingzi.sdk.appwrite.models.File;
import online.bingzi.sdk.appwrite.services.StorageService;
import retrofit2.Call;

import java.util.List;

/**
 * 存储服务实现类
 */
public class StorageServiceImpl implements StorageService {
    private final StorageService storageService;

    public StorageServiceImpl(Client client) {
        this.storageService = client.createService(StorageService.class);
    }

    @Override
    public Call<Bucket> createBucket(String name, String id, List<String> permissions,
                                   Long maximumFileSize, List<String> allowedFileExtensions,
                                   String compression, Boolean encryption, Boolean antivirus) {
        return storageService.createBucket(name, id, permissions, maximumFileSize,
                allowedFileExtensions, compression, encryption, antivirus);
    }

    @Override
    public Call<List<Bucket>> listBuckets() {
        return storageService.listBuckets();
    }

    @Override
    public Call<Bucket> getBucket(String bucketId) {
        return storageService.getBucket(bucketId);
    }

    @Override
    public Call<Void> deleteBucket(String bucketId) {
        return storageService.deleteBucket(bucketId);
    }

    @Override
    public Call<File> createFile(String bucketId, String fileId, MultipartBody.Part file) {
        return storageService.createFile(bucketId, fileId, file);
    }

    /**
     * 创建文件的便捷方法
     *
     * @param bucketId 存储桶ID
     * @param fileId   文件ID（可选）
     * @param fileName 文件名
     * @param fileData 文件数据
     * @return 文件信息
     */
    public Call<File> createFile(String bucketId, String fileId, String fileName, byte[] fileData) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("application/octet-stream"), fileData);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", fileName, requestFile);
        return createFile(bucketId, fileId, filePart);
    }

    @Override
    public Call<List<File>> listFiles(String bucketId) {
        return storageService.listFiles(bucketId);
    }

    @Override
    public Call<File> getFile(String bucketId, String fileId) {
        return storageService.getFile(bucketId, fileId);
    }

    @Override
    public Call<ResponseBody> downloadFile(String bucketId, String fileId) {
        return storageService.downloadFile(bucketId, fileId);
    }

    @Override
    public Call<Void> deleteFile(String bucketId, String fileId) {
        return storageService.deleteFile(bucketId, fileId);
    }

    @Override
    public Call<ResponseBody> getFilePreview(String bucketId, String fileId, Integer width,
                                           Integer height, String gravity, Integer quality,
                                           Integer borderWidth, String borderColor,
                                           Integer borderRadius, Double opacity,
                                           Integer rotation, String background, String output) {
        return storageService.getFilePreview(bucketId, fileId, width, height, gravity,
                quality, borderWidth, borderColor, borderRadius, opacity,
                rotation, background, output);
    }
} 