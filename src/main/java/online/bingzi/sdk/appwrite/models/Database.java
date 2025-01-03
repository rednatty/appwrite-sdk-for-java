package online.bingzi.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;

/**
 * 数据库模型类
 */
public class Database {
    @SerializedName("$id")
    private String id;

    @SerializedName("$createdAt")
    private String createdAt;

    @SerializedName("$updatedAt")
    private String updatedAt;

    @SerializedName("name")
    private String name;

    @SerializedName("enabled")
    private boolean enabled;

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }
} 