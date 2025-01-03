# Appwrite SDK for Java

这是一个用于与Appwrite后端服务进行交互的Java SDK。该SDK提供了简单且直观的API，使开发者能够轻松地集成Appwrite服务到他们的Java应用程序中。

## 功能特性

- 账户管理
  - [x] 用户注册
  - [x] 邮箱登录
  - [x] 获取账户信息
  - [x] 获取/更新偏好设置
  - [x] 密码重置
  - [x] 会话管理

- 团队管理
  - [x] 创建团队
  - [x] 获取团队列表
  - [x] 获取/更新团队信息
  - [x] 删除团队
  - [x] 成员管理
  - [x] 团队偏好设置

- 数据库操作
  - [x] 创建/删除数据库
  - [x] 集合管理
  - [x] 文档CRUD操作
  - [x] 高级查询支持

- 存储服务
  - [x] 存储桶管理
  - [x] 文件上传/下载
  - [x] 文件预览
  - [x] 文件删除

- 函数服务
  - [x] 创建/删除函数
  - [x] 函数部署
  - [x] 函数执行
  - [x] 执行记录查询
  - [x] 环境变量管理

## 技术栈

- Java 8
- Retrofit 2（网络请求）
- OkHttp（HTTP客户端）
- Gson（JSON处理）

## 依赖项

```xml
<dependencies>
    <!-- Retrofit -->
    <dependency>
        <groupId>com.squareup.retrofit2</groupId>
        <artifactId>retrofit</artifactId>
        <version>2.9.0</version>
    </dependency>
    
    <!-- Gson Converter -->
    <dependency>
        <groupId>com.squareup.retrofit2</groupId>
        <artifactId>converter-gson</artifactId>
        <version>2.9.0</version>
    </dependency>
    
    <!-- OkHttp -->
    <dependency>
        <groupId>com.squareup.okhttp3</groupId>
        <artifactId>okhttp</artifactId>
        <version>4.9.1</version>
    </dependency>
</dependencies>
```

## 快速开始

1. 添加依赖项到你的项目中
2. 创建SDK客户端实例：

```java
AppwriteClient client = new AppwriteClient()
    .setEndpoint("https://[HOSTNAME_OR_IP]/v1")
    .setProject("your-project-id")
    .setKey("your-api-key");
```

3. 使用服务：

```java
// 账户服务示例
AccountService accountService = client.getAccount();
accountService.get().enqueue(new Callback<Account>() {
    @Override
    public void onResponse(Call<Account> call, Response<Account> response) {
        Account account = response.body();
        System.out.println("Current user: " + account.getName());
    }

    @Override
    public void onFailure(Call<Account> call, Throwable t) {
        System.err.println("Error: " + t.getMessage());
    }
});

// 数据库服务示例
DatabaseService databaseService = client.getDatabase();
databaseService.listDocuments("collection-id").enqueue(new Callback<List<Document>>() {
    @Override
    public void onResponse(Call<List<Document>> call, Response<List<Document>> response) {
        List<Document> documents = response.body();
        documents.forEach(doc -> System.out.println(doc.getId()));
    }

    @Override
    public void onFailure(Call<List<Document>> call, Throwable t) {
        System.err.println("Error: " + t.getMessage());
    }
});
```

## 测试覆盖

项目使用JUnit 5进行单元测试，使用JaCoCo进行代码覆盖率分析。

已完成的测试模块：
- [x] 账户服务测试
  - 用户注册/登录
  - 账户信息管理
  - 会话管理
  - 偏好设置

- [x] 团队服务测试
  - 团队CRUD操作
  - 成员管理
  - 偏好设置

- [x] 数据库服务测试
  - 数据库管理
  - 集合操作
  - 文档CRUD
  - 查询测试

- [x] 存储服务测试
  - 存储桶管理
  - 文件操作
  - 权限测试

- [x] 函数服务测试
  - 函数管理
  - 部署测试
  - 执行测试
  - 变量管理

运行测试：
```bash
mvn test
```

查看测试报告：
- 测试报告：`target/surefire-reports/`
- 覆盖率报告：`target/site/jacoco/`

## 开发规范

- 遵循SOLID原则
- 使用DRY（Don't Repeat Yourself）原则
- 遵循KISS（Keep It Simple, Stupid）原则
- 遵循YAGNI（You Aren't Gonna Need It）原则
- 遵循OWASP安全最佳实践

## 项目结构

```
sdk-for-java/
├── src/
│   ├── main/java/online/bingzi/sdk/appwrite/
│   │   ├── models/          # 数据模型
│   │   │   ├── Account.java
│   │   │   ├── Team.java
│   │   │   ├── Database.java
│   │   │   ├── Document.java
│   │   │   ├── File.java
│   │   │   └── Function.java
│   │   ├── services/        # 服务接口
│   │   │   ├── AccountService.java
│   │   │   ├── TeamService.java
│   │   │   ├── DatabaseService.java
│   │   │   ├── StorageService.java
│   │   │   └── FunctionService.java
│   │   └── Client.java      # SDK客户端
│   └── test/
│       ├── java/.../services/   # 服务测试
│       │   ├── AccountServiceTest.java
│       │   ├── TeamServiceTest.java
│       │   ├── DatabaseServiceTest.java
│       │   ├── StorageServiceTest.java
│       │   └── FunctionServiceTest.java
│       └── resources/
│           └── json/        # 测试数据
│               ├── account.json
│               ├── team.json
│               ├── database.json
│               └── function.json
└── pom.xml                  # 项目配置
```

## 贡献

欢迎提交Pull Request和Issue。在提交代码前，请确保：

1. 代码符合项目的编码规范
2. 添加了适当的单元测试
3. 所有测试都能通过
4. 更新了相关文档

## 许可证

本项目采用MIT许可证。详见[LICENSE](LICENSE)文件。 