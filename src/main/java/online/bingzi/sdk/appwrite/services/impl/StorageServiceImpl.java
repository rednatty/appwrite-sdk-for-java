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
    public Call<Bucket> createBucket(String bucketId, String name, List<String> permissions,
                                   Long maximumFileSize, List<String> allowedFileExtensions,
                                   Boolean encryption, Boolean antivirus) {
        return storageService.createBucket(bucketId, name, permissions, maximumFileSize,
                allowedFileExtensions, encryption, antivirus);
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
    public Call<File> createFile(String bucketId, MultipartBody.Part file,
                               String fileId, List<String> permissions) {
        return storageService.createFile(bucketId, file, fileId, permissions);
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
    public Call<Void> deleteFile(String bucketId, String fileId) {
        return storageService.deleteFile(bucketId, fileId);
    }
} 