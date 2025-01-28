package lt.xnatty.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class File {

    @SerializedName("$id")
    private String id;

    @SerializedName("bucketId")
    private String bucketId;

    @SerializedName("$createdAt")
    private String createdAt;

    @SerializedName("$updatedAt")
    private String updatedAt;

    @SerializedName("$permissions")
    private List<String> permissions;

    @SerializedName("name")
    private String name;

    @SerializedName("signature")
    private String signature;

    @SerializedName("mimeType")
    private String mimeType;

    @SerializedName("sizeOriginal")
    private long sizeOriginal;

    @SerializedName("chunksTotal")
    private int chunksTotal;

    @SerializedName("chunksUploaded")
    private int chunksUploaded;

    public String getId() {
        return id;
    }

    public String getBucketId() {
        return bucketId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public String getName() {
        return name;
    }

    public String getSignature() {
        return signature;
    }

    public String getMimeType() {
        return mimeType;
    }

    public long getSizeOriginal() {
        return sizeOriginal;
    }

    public int getChunksTotal() {
        return chunksTotal;
    }

    public int getChunksUploaded() {
        return chunksUploaded;
    }
}
