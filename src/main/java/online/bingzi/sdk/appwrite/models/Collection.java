package online.bingzi.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

/**
 * 集合模型类
 */
public class Collection {
    @SerializedName("$id")
    private String id;

    @SerializedName("$databaseId")
    private String databaseId;

    @SerializedName("name")
    private String name;

    @SerializedName("enabled")
    private boolean enabled;

    @SerializedName("documentSecurity")
    private boolean documentSecurity;

    @SerializedName("permissions")
    private List<String> permissions;

    @SerializedName("attributes")
    private List<Map<String, Object>> attributes;

    @SerializedName("indexes")
    private List<Map<String, Object>> indexes;

    public String getId() {
        return id;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isDocumentSecurity() {
        return documentSecurity;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public List<Map<String, Object>> getAttributes() {
        return attributes;
    }

    public List<Map<String, Object>> getIndexes() {
        return indexes;
    }
} 