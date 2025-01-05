package online.bingzi.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Appwrite 团队成员关系模型类
 * <p>
 * 该类表示用户与团队之间的成员关系，包含成员的详细信息、角色和状态。
 * 通过 TeamService 的相关方法可以管理团队成员关系，如邀请新成员、更新角色等。
 * </p>
 *
 * <h2>使用示例：</h2>
 * <pre>
 * // 通过 TeamService 邀请新成员加入团队
 * TeamService teamService = client.createService(TeamService.class);
 *
 * // 邀请用户加入团队
 * Call<Membership> inviteCall = teamService.createMembership(
 *     "team_id",           // 团队ID
 *     "user@example.com",  // 用户邮箱
 *     ["developer"],       // 分配的角色
 *     "欢迎加入开发团队"    // 邀请消息
 * );
 *
 * inviteCall.enqueue(new Callback<Membership>() {
 *     @Override
 *     public void onResponse(Call<Membership> call, Response<Membership> response) {
 *         Membership membership = response.body();
 *         if (membership != null) {
 *             System.out.println("成员ID: " + membership.getId());
 *             System.out.println("用户名: " + membership.getUserName());
 *             System.out.println("团队名: " + membership.getTeamName());
 *             System.out.println("角色: " + String.join(", ", membership.getRoles()));
 *             System.out.println("邀请状态: " + (membership.isConfirm() ? "已接受" : "待确认"));
 *         }
 *     }
 * });
 * </pre>
 *
 * <h2>字段说明：</h2>
 * <ul>
 *   <li>id: 成员关系的唯一标识符</li>
 *   <li>createdAt: 成员关系创建时间（UTC ISO 8601格式）</li>
 *   <li>updatedAt: 成员关系最后更新时间（UTC ISO 8601格式）</li>
 *   <li>userId: 成员的用户ID</li>
 *   <li>userName: 成员的用户名</li>
 *   <li>userEmail: 成员的电子邮件地址</li>
 *   <li>teamId: 所属团队的ID</li>
 *   <li>teamName: 所属团队的名称</li>
 *   <li>invited: 邀请时间（UTC ISO 8601格式）</li>
 *   <li>joined: 加入时间（UTC ISO 8601格式）</li>
 *   <li>confirm: 是否已确认加入团队</li>
 *   <li>roles: 成员在团队中的角色列表</li>
 * </ul>
 */
public class Membership {
    /**
     * 成员关系的唯一标识符
     * 由系统自动生成，格式为字符串
     */
    @SerializedName("$id")
    private String id;

    /**
     * 成员关系创建时间
     * UTC ISO 8601格式的时间戳
     * 例如：2023-01-01T12:00:00.000Z
     */
    @SerializedName("$createdAt")
    private String createdAt;

    /**
     * 成员关系最后更新时间
     * UTC ISO 8601格式的时间戳
     * 例如：2023-01-01T12:00:00.000Z
     */
    @SerializedName("$updatedAt")
    private String updatedAt;

    /**
     * 成员的用户ID
     * 对应用户在系统中的唯一标识符
     */
    @SerializedName("userId")
    private String userId;

    /**
     * 成员的用户名
     * 用户在系统中的显示名称
     */
    @SerializedName("userName")
    private String userName;

    /**
     * 成员的电子邮件地址
     * 用于接收团队相关的通知和邀请
     */
    @SerializedName("userEmail")
    private String userEmail;

    /**
     * 所属团队的ID
     * 对应团队在系统中的唯一标识符
     */
    @SerializedName("teamId")
    private String teamId;

    /**
     * 所属团队的名称
     * 团队的显示名称
     */
    @SerializedName("teamName")
    private String teamName;

    /**
     * 邀请时间
     * UTC ISO 8601格式的时间戳
     * 表示用户被邀请加入团队的时间
     */
    @SerializedName("invited")
    private String invited;

    /**
     * 加入时间
     * UTC ISO 8601格式的时间戳
     * 表示用户实际加入团队的时间
     */
    @SerializedName("joined")
    private String joined;

    /**
     * 确认状态
     * true: 用户已接受邀请并加入团队
     * false: 用户尚未接受邀请
     */
    @SerializedName("confirm")
    private boolean confirm;

    /**
     * 成员角色列表
     * 定义成员在团队中的权限和职责
     * 例如：["admin", "developer", "viewer"]
     */
    @SerializedName("roles")
    private List<String> roles;

    /**
     * 获取成员关系ID
     *
     * @return 成员关系的唯一标识符
     */
    public String getId() {
        return id;
    }

    /**
     * 获取创建时间
     *
     * @return UTC ISO 8601格式的创建时间
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 获取最后更新时间
     *
     * @return UTC ISO 8601格式的更新时间
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 获取用户ID
     *
     * @return 成员的用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 获取用户名
     *
     * @return 成员的显示名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 获取用户邮箱
     *
     * @return 成员的电子邮件地址
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * 获取团队ID
     *
     * @return 所属团队的ID
     */
    public String getTeamId() {
        return teamId;
    }

    /**
     * 获取团队名称
     *
     * @return 所属团队的显示名称
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * 获取邀请时间
     *
     * @return UTC ISO 8601格式的邀请时间
     */
    public String getInvited() {
        return invited;
    }

    /**
     * 获取加入时间
     *
     * @return UTC ISO 8601格式的加入时间
     */
    public String getJoined() {
        return joined;
    }

    /**
     * 获取确认状态
     *
     * @return true表示已接受邀请，false表示未接受
     */
    public boolean isConfirm() {
        return confirm;
    }

    /**
     * 获取成员角色列表
     *
     * @return 成员在团队中的角色列表
     */
    public List<String> getRoles() {
        return roles;
    }
} 