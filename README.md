# Appwrite Java SDK

这是一个用于与Appwrite后端服务交互的Java SDK。该SDK提供了简单且强大的方式来集成Appwrite服务到您的Java应用程序中。

## 技术栈

- 框架：Java 8
- 网络库：Retrofit 2
- 依赖项：
  - Gson（用于JSON处理）
  - OkHttp（作为HTTP客户端）
- 包路径: online.bingzi.sdk.appwrite

## 项目结构

```
src/main/java/online/bingzi/sdk/appwrite/
├── Client.java                 # 客户端初始化类
├── config/
│   └── AppwriteConfig.java    # 配置类
├── models/                     # 数据模型
│   ├── User.java              # 用户模型
│   ├── Database.java          # 数据库模型
│   ├── Collection.java        # 集合模型
│   ├── Document.java          # 文档模型
│   ├── File.java              # 文件模型
│   ├── Bucket.java            # 存储桶模型
│   ├── Function.java          # 函数模型
│   ├── Deployment.java        # 部署模型
│   ├── Execution.java         # 执行结果模型
│   ├── Team.java              # 团队模型
│   └── Membership.java        # 团队成员模型
└── services/                   # 服务接口
    ├── AccountService.java     # 账户服务接口
    ├── DatabaseService.java    # 数据库服务接口
    ├── StorageService.java     # 存储服务接口
    ├── FunctionService.java    # 函数服务接口
    ├── TeamService.java        # 团队服务接口
    └── impl/                   # 服务实现
        ├── AccountServiceImpl.java
        ├── DatabaseServiceImpl.java
        ├── StorageServiceImpl.java
        ├── FunctionServiceImpl.java
        └── TeamServiceImpl.java
```

## 已完成功能

### 1. 核心功能
- [x] 客户端初始化
- [x] 配置管理
- [x] HTTP请求拦截器（认证头等）

### 2. 账户模块 (Account)
- [x] 用户注册
- [x] 邮箱密码登录
- [x] 获取账户信息
- [x] 更新账户偏好设置
- [x] 密码重置
- [x] 会话管理

### 3. 数据库模块 (Database)
- [x] 数据库管理（CRUD）
- [x] 集合管理（CRUD）
- [x] 文档管理（CRUD）
- [x] 泛型支持的文档数据处理

### 4. 存储模块 (Storage)
- [x] 存储桶管理（创建、列表、获取、删除）
- [x] 文件上传（支持分块上传）
- [x] 文件下载（支持流式下载）
- [x] 文件预览（支持图片处理）
- [x] 文件删除
- [x] 文件权限管理

### 5. 函数模块 (Functions)
- [x] 函数管理（创建、更新、删除）
- [x] 函数部署（代码上传、版本管理）
- [x] 函数执行（同步/异步调用）
- [x] 执行记录查看
- [x] 部署日志查看
- [x] 触发器管理（事件、定时）

### 6. 团队模块 (Teams)
- [x] 团队管理（创建、更新、删除）
- [x] 成员管理（邀请、更新、移除）
- [x] 角色管理（分配、更新）
- [x] 成员状态管理
- [x] 团队偏好设置
- [x] 权限控制

## 待完成功能

### 1. 其他计划
- [ ] 单元测试
- [ ] 集成测试
- [ ] 示例代码
- [ ] API文档生成
- [ ] 发布到Maven中央仓库

## 使用示例

### 初始化客户端

```java
// 创建配置
AppwriteConfig config = new AppwriteConfig("your-project-id", "your-api-key");

// 初始化客户端
Client client = new Client(config);
```

### 团队操作

```java
// 创建团队服务
TeamService teamService = new TeamServiceImpl(client);

// 创建团队
List<String> roles = Arrays.asList("admin", "developer");
teamService.createTeam("My Team", null, roles)
    .enqueue(new Callback<Team>() {
        @Override
        public void onResponse(Call<Team> call, Response<Team> response) {
            if (response.isSuccessful()) {
                Team team = response.body();
                // 处理团队信息
            }
        }

        @Override
        public void onFailure(Call<Team> call, Throwable t) {
            // 处理错误
        }
    });

// 邀请成员
teamService.createMembership("team-id", "member@example.com", 
        Arrays.asList("developer"), "https://your-domain.com", "John Doe")
    .enqueue(new Callback<Membership>() {
        @Override
        public void onResponse(Call<Membership> call, Response<Membership> response) {
            if (response.isSuccessful()) {
                Membership membership = response.body();
                // 处理成员信息
            }
        }

        @Override
        public void onFailure(Call<Membership> call, Throwable t) {
            // 处理错误
        }
    });
```

## 下一步计划

1. 补充单元测试
   - 为所有模块添加单元测试
   - 设置测试覆盖率目标
   - 添加集成测试

2. 完善文档
   - 生成API文档
   - 添加更多使用示例
   - 编写贡献指南

3. 发布准备
   - 配置Maven发布设置
   - 准备发布到Maven中央仓库
   - 创建发布脚本

## 贡献指南

欢迎提交问题和改进建议！

## 许可证

[MIT License](LICENSE) 