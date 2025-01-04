# Appwrite SDK for Java

English | [简体中文](README_zh.md)

A Java SDK for interacting with Appwrite backend services. This SDK provides a simple and intuitive API that enables developers to easily integrate Appwrite services into their Java applications.

## Features

- Account Management
  - [x] User Registration
  - [x] Email Login
  - [x] Account Information
  - [x] Preferences Management
  - [x] Password Reset
  - [x] Session Management

- Team Management
  - [x] Create Team
  - [x] List Teams
  - [x] Get/Update Team Info
  - [x] Delete Team
  - [x] Member Management
  - [x] Team Preferences

- Database Operations
  - [x] Create/Delete Database
  - [x] Collection Management
  - [x] Document CRUD
  - [x] Advanced Query Support

- Storage Service
  - [x] Bucket Management
  - [x] File Upload/Download
  - [x] File Preview
  - [x] File Deletion

- Function Service
  - [x] Create/Delete Functions
  - [x] Function Deployment
  - [x] Function Execution
  - [x] Execution Logs
  - [x] Environment Variables

## Tech Stack

- Java 8
- Retrofit 2 (Network Requests)
- OkHttp (HTTP Client)
- Gson (JSON Processing)

## Dependencies

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

## Quick Start

1. Add dependencies to your project

For Maven, add the following to your `pom.xml`:

```xml

<repositories>
  <repository>
    <id>github</id>
    <url>https://maven.pkg.github.com/BingZi-233/sdk-for-java</url>
  </repository>
</repositories>

<dependencies>
<dependency>
  <groupId>online.bingzi</groupId>
  <artifactId>sdk-for-java</artifactId>
  <version>{latest-version}</version>
</dependency>
</dependencies>
```

Also add authentication in your Maven `settings.xml`:

```xml

<servers>
  <server>
    <id>github</id>
    <username>YOUR_GITHUB_USERNAME</username>
    <password>YOUR_GITHUB_TOKEN</password>
  </server>
</servers>
```

Note: Generate a GitHub token with `read:packages` scope.

For Gradle, add the following to your `build.gradle`:

```groovy
repositories {
  maven {
    url = uri("https://maven.pkg.github.com/BingZi-233/sdk-for-java")
    credentials {
      username = project.findProperty("gpr.user") ?: System.getenv("USERNAME")
      password = project.findProperty("gpr.key") ?: System.getenv("TOKEN")
    }
  }
}

dependencies {
  implementation 'online.bingzi:sdk-for-java:{latest-version}'
}
```

2. Create SDK client instance:

```java
AppwriteClient client = new AppwriteClient()
    .setEndpoint("https://[HOSTNAME_OR_IP]/v1")
    .setProject("your-project-id")
    .setKey("your-api-key");
```

3. Use services:

```java
// Account Service Example
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

// Database Service Example
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

## Test Coverage

The project uses JUnit 5 for unit testing and JaCoCo for code coverage analysis.

Completed test modules:
- [x] Account Service Tests
  - User Registration/Login
  - Account Information Management
  - Session Management
  - Preferences Management

- [x] Team Service Tests
  - Team CRUD Operations
  - Member Management
  - Preferences Management

- [x] Database Service Tests
  - Database Management
  - Collection Operations
  - Document CRUD
  - Query Tests

- [x] Storage Service Tests
  - Bucket Management
  - File Operations
  - Permission Tests

- [x] Function Service Tests
  - Function Management
  - Deployment Tests
  - Execution Tests
  - Variable Management

Run tests:
```bash
mvn test
```

View reports:
- Test Reports: `target/surefire-reports/`
- Coverage Reports: `target/site/jacoco/`

## Development Guidelines

- Follow SOLID Principles
- Apply DRY (Don't Repeat Yourself)
- Follow KISS (Keep It Simple, Stupid)
- Follow YAGNI (You Aren't Gonna Need It)
- Follow OWASP Security Best Practices

## Project Structure

```
sdk-for-java/
├── src/
│   ├── main/java/online/bingzi/sdk/appwrite/
│   │   ├── models/          # Data Models
│   │   │   ├── Account.java
│   │   │   ├── Team.java
│   │   │   ├── Database.java
│   │   │   ├── Document.java
│   │   │   ├── File.java
│   │   │   └── Function.java
│   │   ├── services/        # Service Interfaces
│   │   │   ├── AccountService.java
│   │   │   ├── TeamService.java
│   │   │   ├── DatabaseService.java
│   │   │   ├── StorageService.java
│   │   │   └── FunctionService.java
│   │   └── Client.java      # SDK Client
│   └── test/
│       ├── java/.../services/   # Service Tests
│       │   ├── AccountServiceTest.java
│       │   ├── TeamServiceTest.java
│       │   ├── DatabaseServiceTest.java
│       │   ├── StorageServiceTest.java
│       │   └── FunctionServiceTest.java
│       └── resources/
│           └── json/        # Test Data
│               ├── account.json
│               ├── team.json
│               ├── database.json
│               └── function.json
└── pom.xml                  # Project Configuration
```

## Contributing

Pull requests and issues are welcome. Before submitting code, please ensure:

1. Code follows project coding standards
2. Appropriate unit tests are added
3. All tests pass
4. Documentation is updated

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details. 