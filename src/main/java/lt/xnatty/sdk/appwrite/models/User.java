package lt.xnatty.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class User {

    @SerializedName("$id")
    private String id;

    @SerializedName("$createdAt")
    private String createdAt;

    @SerializedName("$updatedAt")
    private String updatedAt;

    @SerializedName("email")
    private String email;

    @SerializedName("emailVerification")
    private boolean emailVerification;

    @SerializedName("name")
    private String name;

    @SerializedName("status")
    private boolean status;

    @SerializedName("prefs")
    private Map<String, Object> preferences;

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEmailVerification() {
        return emailVerification;
    }

    public String getName() {
        return name;
    }

    public boolean isStatus() {
        return status;
    }

    public Map<String, Object> getPreferences() {
        return preferences;
    }
}
