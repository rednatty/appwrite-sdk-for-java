package online.bingzi.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;

/**
 * 函数部署模型类
 */
public class Deployment {
    @SerializedName("$id")
    private String id;

    @SerializedName("$createdAt")
    private String createdAt;

    @SerializedName("$updatedAt")
    private String updatedAt;

    @SerializedName("type")
    private String type;

    @SerializedName("resourceId")
    private String resourceId;

    @SerializedName("resourceType")
    private String resourceType;

    @SerializedName("entrypoint")
    private String entrypoint;

    @SerializedName("size")
    private long size;

    @SerializedName("buildId")
    private String buildId;

    @SerializedName("activate")
    private boolean activate;

    @SerializedName("status")
    private String status;

    @SerializedName("buildLogs")
    private String buildLogs;

    @SerializedName("buildTime")
    private int buildTime;

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getType() {
        return type;
    }

    public String getResourceId() {
        return resourceId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public String getEntrypoint() {
        return entrypoint;
    }

    public long getSize() {
        return size;
    }

    public String getBuildId() {
        return buildId;
    }

    public boolean isActivate() {
        return activate;
    }

    public String getStatus() {
        return status;
    }

    public String getBuildLogs() {
        return buildLogs;
    }

    public int getBuildTime() {
        return buildTime;
    }
} 