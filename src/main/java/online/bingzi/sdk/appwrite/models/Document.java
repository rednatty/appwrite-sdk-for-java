package online.bingzi.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

/**
 * 文档模型类
 */
public class Document<T> {
    @SerializedName("$id")
    private String id;

    @SerializedName("$collectionId")
    private String collectionId;

    @SerializedName("$databaseId")
    private String databaseId;

    @SerializedName("$createdAt")
    private String createdAt;

    @SerializedName("$updatedAt")
    private String updatedAt;

    @SerializedName("$permissions")
    private List<String> permissions;

    @SerializedName("data")
    private T data;

    public String getId() {
        return id;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public String getDatabaseId() {
        return databaseId;
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

    public T getData() {
        return data;
    }
} 