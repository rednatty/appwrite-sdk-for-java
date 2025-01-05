package online.bingzi.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Appwrite 团队模型类
 * <p>
 * 该类表示 Appwrite 系统中的团队实体，用于管理用户组和权限。
 * 团队可以包含多个成员，每个成员可以被分配不同的角色。
 * 通过 TeamService 的相关方法可以创建、管理和删除团队。
 * </p>
 *
 * <h2>使用示例：</h2>
 * <pre>
 * // 通过 TeamService 创建新团队
 * TeamService teamService = client.createService(TeamService.class);
 *
 * // 创建团队
 * Call<Team> createTeamCall = teamService.createTeam("开发团队", ["admin", "developer"]);
 * createTeamCall.enqueue(new Callback<Team>() {
 *     @Override
 *     public void onResponse(Call<Team> call, Response<Team> response) {
 *         Team team = response.body();
 *         if (team != null) {
 *             System.out.println("团队ID: " + team.getId());
 *             System.out.println("团队名称: " + team.getName());
 *             System.out.println("成员数量: " + team.getTotal());
 *
 *             // 获取团队角色
 *             List<String> roles = team.getPreferences().getRoles();
 *             System.out.println("团队角色: " + String.join(", ", roles));
 *         }
 *     }
 * });
 * </pre>
 *
 * <h2>字段说明：</h2>
 * <ul>
 *   <li>id: 团队的唯一标识符</li>
 *   <li>createdAt: 团队创建时间（UTC ISO 8601格式）</li>
 *   <li>updatedAt: 团队信息最后更新时间（UTC ISO 8601格式）</li>
 *   <li>name: 团队名称</li>
 *   <li>total: 团队成员总数</li>
 *   <li>preferences: 团队偏好设置，包含角色定义等信息</li>
 * </ul>
 */
public class Team {
    /**
     * 团队的唯一标识符
     * 由系统自动生成，格式为字符串
     */
    @SerializedName("$id")
    private String id;

    /**
     * 团队创建时间
     * UTC ISO 8601格式的时间戳
     * 例如：2023-01-01T12:00:00.000Z
     */
    @SerializedName("$createdAt")
    private String createdAt;

    /**
     * 团队信息最后更新时间
     * UTC ISO 8601格式的时间戳
     * 例如：2023-01-01T12:00:00.000Z
     */
    @SerializedName("$updatedAt")
    private String updatedAt;

    /**
     * 团队名称
     * 用于显示和识别团队的名称
     */
    @SerializedName("name")
    private String name;

    /**
     * 团队成员总数
     * 表示当前团队中的成员数量
     */
    @SerializedName("total")
    private int total;

    /**
     * 团队偏好设置
     * 包含团队的配置信息，如角色定义等
     */
    @SerializedName("prefs")
    private TeamPreferences preferences;

    /**
     * 获取团队ID
     *
     * @return 团队的唯一标识符
     */
    public String getId() {
        return id;
    }

    /**
     * 获取团队创建时间
     *
     * @return UTC ISO 8601格式的创建时间
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 获取团队信息最后更新时间
     *
     * @return UTC ISO 8601格式的更新时间
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 获取团队名称
     *
     * @return 团队的显示名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取团队成员总数
     *
     * @return 当前团队的成员数量
     */
    public int getTotal() {
        return total;
    }

    /**
     * 获取团队偏好设置
     *
     * @return 包含团队配置信息的TeamPreferences对象
     */
    public TeamPreferences getPreferences() {
        return preferences;
    }

    /**
     * 团队偏好设置类
     * <p>
     * 该内部类包含团队的配置信息，主要用于定义团队中可用的角色。
     * 角色用于控制团队成员的权限和访问级别。
     * </p>
     *
     * <h2>使用示例：</h2>
     * <pre>
     * TeamPreferences prefs = team.getPreferences();
     * List<String> roles = prefs.getRoles();
     * for (String role : roles) {
     *     System.out.println("可用角色: " + role);
     * }
     * </pre>
     */
    public static class TeamPreferences {
        /**
         * 团队中定义的角色列表
         * 例如：["admin", "developer", "viewer"]
         */
        @SerializedName("roles")
        private List<String> roles;

        /**
         * 获取团队中定义的所有角色
         *
         * @return 角色名称列表
         */
        public List<String> getRoles() {
            return roles;
        }
    }
} 