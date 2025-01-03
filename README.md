# Appwrite SDK for Java

这是一个用于与Appwrite后端服务进行交互的Java SDK。该SDK提供了简单且直观的API，使开发者能够轻松地集成Appwrite服务到他们的Java应用程序中。

## 功能特性

- 账户管理
- 数据库操作
- 存储服务
- 完整的类型支持
- 异步请求处理
- 简单的API设计

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
    
    <!-- JUnit Jupiter -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.8.1</version>
        <scope>test</scope>
    </dependency>
    
    <!-- Mockito -->
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <version>4.0.0</version>
        <scope>test</scope>
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

## 开发规范

- 遵循SOLID原则
- 使用DRY（Don't Repeat Yourself）原则
- 遵循KISS（Keep It Simple, Stupid）原则
- 遵循YAGNI（You Aren't Gonna Need It）原则
- 遵循OWASP安全最佳实践

## 测试

项目使用JUnit 5进行单元测试。测试覆盖：

- 账户服务
- 数据库服务
- 存储服务
- 模型类
- 工具类

运行测试：

```bash
mvn test
```

## 贡献

欢迎提交Pull Request和Issue。在提交代码前，请确保：

1. 代码符合项目的编码规范
2. 添加了适当的单元测试
3. 所有测试都能通过
4. 更新了相关文档

## 许可证

本项目采用MIT许可证。详见[LICENSE](LICENSE)文件。 