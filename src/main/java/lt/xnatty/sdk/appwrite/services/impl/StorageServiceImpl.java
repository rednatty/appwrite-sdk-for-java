package lt.xnatty.sdk.appwrite.services.impl;

import java.util.List;
import lt.xnatty.sdk.appwrite.Client;
import lt.xnatty.sdk.appwrite.models.Bucket;
import lt.xnatty.sdk.appwrite.models.File;
import lt.xnatty.sdk.appwrite.services.StorageService;
import okhttp3.MultipartBody;
import retrofit2.Call;

public class StorageServiceImpl implements StorageService {
    private final StorageService storageService;

    public StorageServiceImpl(Client client) {
        this.storageService = client.createService(StorageService.class);
    }

    @Override
    public Call<Bucket> createBucket(
            String bucketId,
            String name,
            List<String> permissions,
            Long maximumFileSize,
            List<String> allowedFileExtensions,
            Boolean encryption,
            Boolean antivirus) {
        return storageService.createBucket(
                bucketId, name, permissions, maximumFileSize, allowedFileExtensions, encryption, antivirus);
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
    public Call<File> createFile(String bucketId, MultipartBody.Part file, String fileId, List<String> permissions) {
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
