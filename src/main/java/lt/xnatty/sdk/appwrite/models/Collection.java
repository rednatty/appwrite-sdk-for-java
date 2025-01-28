package lt.xnatty.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

public class Collection {

    @SerializedName("$id")
    private String id;

    @SerializedName("$createdAt")
    private String createdAt;

    @SerializedName("$updatedAt")
    private String updatedAt;

    @SerializedName("databaseId")
    private String databaseId;

    @SerializedName("name")
    private String name;

    @SerializedName("enabled")
    private boolean enabled;

    @SerializedName("documentSecurity")
    private boolean documentSecurity;

    @SerializedName("attributes")
    private List<Map<String, Object>> attributes;

    @SerializedName("indexes")
    private List<Map<String, Object>> indexes;

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
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

    public List<Map<String, Object>> getAttributes() {
        return attributes;
    }

    public List<Map<String, Object>> getIndexes() {
        return indexes;
    }
}
