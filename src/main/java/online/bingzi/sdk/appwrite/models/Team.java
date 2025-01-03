package online.bingzi.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * 团队模型类
 */
public class Team {
    @SerializedName("$id")
    private String id;

    @SerializedName("$createdAt")
    private String createdAt;

    @SerializedName("$updatedAt")
    private String updatedAt;

    @SerializedName("name")
    private String name;

    @SerializedName("total")
    private int total;

    @SerializedName("prefs")
    private TeamPreferences preferences;

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

    public int getTotal() {
        return total;
    }

    public TeamPreferences getPreferences() {
        return preferences;
    }

    /**
     * 团队偏好设置
     */
    public static class TeamPreferences {
        @SerializedName("roles")
        private List<String> roles;

        public List<String> getRoles() {
            return roles;
        }
    }
} 