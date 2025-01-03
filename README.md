# Appwrite Java SDK

这是一个用于与Appwrite后端服务交互的Java SDK。

## 技术栈

- Java 8
- Retrofit 2 (网络请求)
- Gson (JSON处理)
- OkHttp (HTTP客户端)
- JUnit 5 (单元测试)
- Mockito (测试模拟)
- JaCoCo (测试覆盖率)

## 项目结构

```
sdk-for-java/
├── src/
│   ├── main/java/online/bingzi/sdk/appwrite/
│   │   ├── config/
│   │   │   └── AppwriteConfig.java
│   │   ├── models/
│   │   │   ├── User.java
│   │   │   ├── Team.java
│   │   │   ├── Membership.java
│   │   │   ├── Database.java
│   │   │   ├── Collection.java
│   │   │   ├── Document.java
│   │   │   ├── File.java
│   │   │   ├── Bucket.java
│   │   │   └── Function.java
│   │   ├── services/
│   │   │   ├── AccountService.java
│   │   │   ├── TeamService.java
│   │   │   ├── DatabaseService.java
│   │   │   ├── StorageService.java
│   │   │   ├── FunctionService.java
│   │   │   └── impl/
│   │   │       ├── AccountServiceImpl.java
│   │   │       ├── TeamServiceImpl.java
│   │   │       ├── DatabaseServiceImpl.java
│   │   │       ├── StorageServiceImpl.java
│   │   │       └── FunctionServiceImpl.java
│   │   └── Client.java
│   └── test/
│       ├── java/online/bingzi/sdk/appwrite/
│       │   ├── BaseTest.java
│       │   └── services/
│       │       ├── AccountServiceTest.java
│       │       └── TeamServiceTest.java
│       └── resources/
│           └── json/
│               ├── user.json
│               ├── team.json
│               └── membership.json
└── pom.xml
```

## 已完成功能

### 核心功能
- [x] 客户端初始化
- [x] 配置管理
- [x] API请求拦截器

### 账户模块
- [x] 用户注册
- [x] 邮箱登录
- [x] 获取账户信息
- [x] 获取/更新偏好设置
- [x] 密码重置
- [x] 会话管理

### 团队模块
- [x] 创建团队
- [x] 获取团队列表
- [x] 获取/更新团队信息
- [x] 删除团队
- [x] 成员管理
- [x] 团队偏好设置

### 数据库模块
- [x] 创建/删除数据库
- [x] 集合管理
- [x] 文档CRUD操作

### 存储模块
- [x] 存储桶管理
- [x] 文件上传/下载
- [x] 文件预览
- [x] 文件删除

### 函数模块
- [x] 创建/删除函数
- [x] 函数部署
- [x] 函数执行
- [x] 执行记录查询

## 测试覆盖
- [x] 团队模块单元测试
- [x] 账户模块单元测试
- [ ] 数据库模块单元测试 (进行中)
- [ ] 存储模块单元测试
- [ ] 函数模块单元测试
- [ ] 集成测试

## 使用示例

### 初始化客户端
```java
AppwriteConfig config = new AppwriteConfig("your-project-id", "your-api-key");
Client client = new Client(config);
```

### 账户操作
```java
AccountService accountService = new AccountServiceImpl(client);

// 创建用户
User user = accountService.create("email@example.com", "password", "User Name")
    .execute()
    .body();

// 登录
User loggedInUser = accountService.createEmailSession("email@example.com", "password")
    .execute()
    .body();
```

### 团队操作
```java
TeamService teamService = new TeamServiceImpl(client);

// 创建团队
Team team = teamService.createTeam("Team Name")
    .execute()
    .body();

// 添加成员
Membership membership = teamService.createMembership(
    team.getId(),
    "member@example.com",
    Arrays.asList("developer")
).execute().body();
```

## 下一步计划

1. 完成账户模块单元测试
   - 修复当前的编译错误
   - 补充边界条件测试
   - 添加错误处理测试

2. 实现数据库模块单元测试
   - 创建测试JSON文件
   - 编写基础CRUD测试
   - 添加高级查询测试

3. 实现存储模块单元测试
   - 创建测试JSON文件
   - 编写文件操作测试
   - 测试大文件上传下载

4. 实现函数模块单元测试
   - 创建测试JSON文件
   - 编写函数生命周期测试
   - 测试函数执行和日志

5. 添加集成测试
   - 设置测试环境
   - 编写端到端测试
   - 测试模块间交互

## 贡献指南

1. Fork 项目
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

## 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情 