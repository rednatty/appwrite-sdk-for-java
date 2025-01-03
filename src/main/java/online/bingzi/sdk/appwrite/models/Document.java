package online.bingzi.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * 文档模型类
 * @param <T> 文档数据类型
 */
public class Document<T> {
    @SerializedName("$id")
    private String id;

    @SerializedName("$createdAt")
    private String createdAt;

    @SerializedName("$updatedAt")
    private String updatedAt;

    @SerializedName("$collectionId")
    private String collectionId;

    @SerializedName("$databaseId")
    private String databaseId;

    @SerializedName("$permissions")
    private List<String> permissions;

    @SerializedName("data")
    private T data;

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public T getData() {
        return data;
    }
} 