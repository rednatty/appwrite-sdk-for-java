package online.bingzi.sdk.appwrite.models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Appwrite 用户模型类
 * <p>
 * 该类表示 Appwrite 系统中的用户实体，包含用户的基本信息和状态。
 * 通过 AccountService 的相关方法可以获取和管理用户信息。
 * </p>
 *
 * <h2>使用示例：</h2>
 * <pre>
 * // 通过 AccountService 获取当前用户信息
 * AccountService accountService = client.createService(AccountService.class);
 * Call<User> userCall = accountService.getAccount();
 * userCall.enqueue(new Callback<User>() {
 *     @Override
 *     public void onResponse(Call<User> call, Response<User> response) {
 *         User user = response.body();
 *         if (user != null) {
 *             System.out.println("用户ID: " + user.getId());
 *             System.out.println("用户名: " + user.getName());
 *             System.out.println("邮箱: " + user.getEmail());
 *             System.out.println("邮箱验证状态: " + user.getEmailVerification());
 *         }
 *     }
 * });
 * </pre>
 *
 * <h2>字段说明：</h2>
 * <ul>
 *   <li>id: 用户的唯一标识符</li>
 *   <li>createdAt: 用户创建时间（UTC ISO 8601格式）</li>
 *   <li>updatedAt: 用户信息最后更新时间（UTC ISO 8601格式）</li>
 *   <li>email: 用户的电子邮件地址</li>
 *   <li>emailVerification: 邮箱是否已验证</li>
 *   <li>name: 用户的显示名称</li>
 *   <li>status: 用户状态（true表示活跃，false表示禁用）</li>
 *   <li>preferences: 用户偏好设置，可以存储自定义键值对</li>
 * </ul>
 */
public class User {
    /**
     * 用户的唯一标识符
     * 由系统自动生成，格式为字符串
     */
    @SerializedName("$id")
    private String id;

    /**
     * 用户创建时间
     * UTC ISO 8601格式的时间戳
     * 例如：2023-01-01T12:00:00.000Z
     */
    @SerializedName("$createdAt")
    private String createdAt;

    /**
     * 用户信息最后更新时间
     * UTC ISO 8601格式的时间戳
     * 例如：2023-01-01T12:00:00.000Z
     */
    @SerializedName("$updatedAt")
    private String updatedAt;

    /**
     * 用户的电子邮件地址
     * 用于登录和接收系统通知
     */
    @SerializedName("email")
    private String email;

    /**
     * 邮箱验证状态
     * true: 邮箱已验证
     * false: 邮箱未验证
     */
    @SerializedName("emailVerification")
    private boolean emailVerification;

    /**
     * 用户的显示名称
     * 可以是用户的真实姓名或昵称
     */
    @SerializedName("name")
    private String name;

    /**
     * 用户状态
     * true: 用户处于活跃状态
     * false: 用户被禁用
     */
    @SerializedName("status")
    private boolean status;

    /**
     * 用户偏好设置
     * 可以存储任意的键值对数据
     * 例如：主题设置、通知偏好等
     */
    @SerializedName("prefs")
    private Map<String, Object> preferences;

    /**
     * 获取用户ID
     *
     * @return 用户的唯一标识符
     */
    public String getId() {
        return id;
    }

    /**
     * 获取用户创建时间
     *
     * @return UTC ISO 8601格式的创建时间
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 获取用户信息最后更新时间
     *
     * @return UTC ISO 8601格式的更新时间
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 获取用户邮箱地址
     *
     * @return 用户的电子邮件地址
     */
    public String getEmail() {
        return email;
    }

    /**
     * 获取邮箱验证状态
     *
     * @return true表示邮箱已验证，false表示未验证
     */
    public boolean getEmailVerification() {
        return emailVerification;
    }

    /**
     * 获取用户名称
     *
     * @return 用户的显示名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取用户状态
     *
     * @return true表示用户活跃，false表示用户被禁用
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * 获取用户偏好设置
     *
     * @return 包含用户偏好设置的Map对象
     */
    public Map<String, Object> getPreferences() {
        return preferences;
    }
} 