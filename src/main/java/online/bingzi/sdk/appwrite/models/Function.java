package online.bingzi.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

/**
 * 函数模型类
 */
public class Function {
    @SerializedName("$id")
    private String id;

    @SerializedName("$createdAt")
    private String createdAt;

    @SerializedName("$updatedAt")
    private String updatedAt;

    @SerializedName("execute")
    private List<String> execute;

    @SerializedName("name")
    private String name;

    @SerializedName("enabled")
    private boolean enabled;

    @SerializedName("runtime")
    private String runtime;

    @SerializedName("deployment")
    private String deployment;

    @SerializedName("vars")
    private Map<String, String> variables;

    @SerializedName("events")
    private List<String> events;

    @SerializedName("schedule")
    private String schedule;

    @SerializedName("timeout")
    private int timeout;

    @SerializedName("entrypoint")
    private String entrypoint;

    @SerializedName("commands")
    private String commands;

    @SerializedName("version")
    private String version;

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public List<String> getExecute() {
        return execute;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getDeployment() {
        return deployment;
    }

    public Map<String, String> getVariables() {
        return variables;
    }

    public List<String> getEvents() {
        return events;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getTimeout() {
        return timeout;
    }

    public String getEntrypoint() {
        return entrypoint;
    }

    public String getCommands() {
        return commands;
    }

    public String getVersion() {
        return version;
    }
} 