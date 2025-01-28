package lt.xnatty.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;

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
}
