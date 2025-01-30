# Appwrite SDK for Java

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

- Retrofit 2 (Network Requests)
- OkHttp (HTTP Client)
- Gson (JSON Processing)

## Quick Start
1. Create SDK client instance:

```java
AppwriteClient client = new AppwriteClient()
    .setEndpoint("https://[HOSTNAME_OR_IP]/v1")
    .setProject("your-project-id")
    .setKey("your-api-key");
```

2. Use services:

```java
// Account Service Example
AccountService accountService = client.getAccount();
accountService.get().enqueue(new Callback<Account>() {
    @Override
    public void onResponse(Call<Account> call, Response<Account> response) {
        Account account = response.body();
        System.out.println("Current user: " + account.getName());
    }@Override
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
    }@Override
    public void onFailure(Call<List<Document>> call, Throwable t) {
        System.err.println("Error: " + t.getMessage());
    }
});
```

## Contributing

Pull requests and issues are welcome. Before submitting code, please ensure:

1. Code follows project coding standards
2. Appropriate unit tests are added
3. All tests pass
4. Documentation is updated

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details. 