package online.bingzi.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;

/**
 * 数据库模型类
 */
public class Database {
    @SerializedName("$id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("enabled")
    private boolean enabled;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }
} 