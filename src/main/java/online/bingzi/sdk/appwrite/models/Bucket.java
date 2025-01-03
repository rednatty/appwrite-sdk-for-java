package online.bingzi.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * 存储桶模型类
 */
public class Bucket {
    @SerializedName("$id")
    private String id;

    @SerializedName("$createdAt")
    private String createdAt;

    @SerializedName("$updatedAt")
    private String updatedAt;

    @SerializedName("$permissions")
    private List<String> permissions;

    @SerializedName("name")
    private String name;

    @SerializedName("enabled")
    private boolean enabled;

    @SerializedName("maximumFileSize")
    private long maximumFileSize;

    @SerializedName("allowedFileExtensions")
    private List<String> allowedFileExtensions;

    @SerializedName("compression")
    private String compression;

    @SerializedName("encryption")
    private boolean encryption;

    @SerializedName("antivirus")
    private boolean antivirus;

    public String getId() {
        return id;
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

    public boolean isEnabled() {
        return enabled;
    }

    public long getMaximumFileSize() {
        return maximumFileSize;
    }

    public List<String> getAllowedFileExtensions() {
        return allowedFileExtensions;
    }

    public String getCompression() {
        return compression;
    }

    public boolean isEncryption() {
        return encryption;
    }

    public boolean isAntivirus() {
        return antivirus;
    }
} 