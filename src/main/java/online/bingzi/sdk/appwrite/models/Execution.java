package online.bingzi.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;

/**
 * 函数执行结果模型类
 */
public class Execution {
    @SerializedName("$id")
    private String id;

    @SerializedName("$createdAt")
    private String createdAt;

    @SerializedName("$updatedAt")
    private String updatedAt;

    @SerializedName("functionId")
    private String functionId;

    @SerializedName("trigger")
    private String trigger;

    @SerializedName("status")
    private String status;

    @SerializedName("statusCode")
    private int statusCode;

    @SerializedName("response")
    private String response;

    @SerializedName("stdout")
    private String stdout;

    @SerializedName("stderr")
    private String stderr;

    @SerializedName("duration")
    private double duration;

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getFunctionId() {
        return functionId;
    }

    public String getTrigger() {
        return trigger;
    }

    public String getStatus() {
        return status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponse() {
        return response;
    }

    public String getStdout() {
        return stdout;
    }

    public String getStderr() {
        return stderr;
    }

    public double getDuration() {
        return duration;
    }
} 