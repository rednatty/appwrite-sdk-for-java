package online.bingzi.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * 团队成员模型类
 */
public class Membership {
    @SerializedName("$id")
    private String id;

    @SerializedName("$createdAt")
    private String createdAt;

    @SerializedName("$updatedAt")
    private String updatedAt;

    @SerializedName("userId")
    private String userId;

    @SerializedName("userName")
    private String userName;

    @SerializedName("userEmail")
    private String userEmail;

    @SerializedName("teamId")
    private String teamId;

    @SerializedName("teamName")
    private String teamName;

    @SerializedName("invited")
    private String invited;

    @SerializedName("joined")
    private String joined;

    @SerializedName("confirm")
    private boolean confirm;

    @SerializedName("roles")
    private List<String> roles;

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getInvited() {
        return invited;
    }

    public String getJoined() {
        return joined;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public List<String> getRoles() {
        return roles;
    }
} 