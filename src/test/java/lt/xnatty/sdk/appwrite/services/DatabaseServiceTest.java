package lt.xnatty.sdk.appwrite.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lt.xnatty.sdk.appwrite.BaseTest;
import lt.xnatty.sdk.appwrite.models.Collection;
import lt.xnatty.sdk.appwrite.models.Database;
import lt.xnatty.sdk.appwrite.models.Document;
import lt.xnatty.sdk.appwrite.services.impl.DatabaseServiceImpl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

class DatabaseServiceTest extends BaseTest {
    private DatabaseService databaseService;

    @BeforeEach
    void init() {
        databaseService = new DatabaseServiceImpl(client);
    }

    @Test
    void createDatabase() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody(loadJsonFromResource("database"))
                .addHeader("Content-Type", "application/json"));
        Response<Database> response =
                databaseService.createDatabase("Test Database", "test-db").execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals("/v1/databases", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("Test%20Database"));
        assertTrue(body.contains("test-db"));
        assertTrue(response.isSuccessful());
        Database database = response.body();
        assertNotNull(database);
        assertEquals("5e5ea5c16897e", database.getId());
        assertEquals("Test Database", database.getName());
        assertTrue(database.isEnabled());
    }

    @Test
    void listDatabases() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("[" + loadJsonFromResource("database") + "]")
                .addHeader("Content-Type", "application/json"));
        Response<List<Database>> response = databaseService.listDatabases().execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/databases", request.getPath());
        assertTrue(response.isSuccessful());
        List<Database> databases = response.body();
        assertNotNull(databases);
        assertEquals(1, databases.size());
        Database database = databases.get(0);
        assertEquals("5e5ea5c16897e", database.getId());
        assertEquals("Test Database", database.getName());
    }

    @Test
    void getDatabase() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("database"))
                .addHeader("Content-Type", "application/json"));
        Response<Database> response = databaseService.getDatabase("test-db").execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/databases/test-db", request.getPath());
        assertTrue(response.isSuccessful());
        Database database = response.body();
        assertNotNull(database);
        assertEquals("5e5ea5c16897e", database.getId());
        assertEquals("Test Database", database.getName());
    }

    @Test
    void deleteDatabase() throws Exception {
        mockWebServer.enqueue(new MockResponse().setResponseCode(204));
        Response<Void> response = databaseService.deleteDatabase("test-db").execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("DELETE", request.getMethod());
        assertEquals("/v1/databases/test-db", request.getPath());
        assertTrue(response.isSuccessful());
    }

    @Test
    void createCollection() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody(loadJsonFromResource("collection"))
                .addHeader("Content-Type", "application/json"));
        Response<Collection> response = databaseService
                .createCollection("test-db", "Test Collection", "test-collection", Arrays.asList("read", "write"))
                .execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals("/v1/databases/test-db/collections", request.getPath());
        String body = request.getBody().readUtf8();

        assertTrue(body.contains("test-collection"));
        assertTrue(body.contains("read"));
        assertTrue(body.contains("write"));
        assertTrue(response.isSuccessful());
        Collection collection = response.body();
        assertNotNull(collection);
        assertEquals("5e5ea5c16897e", collection.getId());
        assertEquals("Test Collection", collection.getName());
        assertTrue(collection.isEnabled());
        assertTrue(collection.isDocumentSecurity());
    }

    @Test
    void listCollections() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("[" + loadJsonFromResource("collection") + "]")
                .addHeader("Content-Type", "application/json"));
        Response<List<Collection>> response =
                databaseService.listCollections("test-db").execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/databases/test-db/collections", request.getPath());
        assertTrue(response.isSuccessful());
        List<Collection> collections = response.body();
        assertNotNull(collections);
        assertEquals(1, collections.size());
        Collection collection = collections.get(0);
        assertEquals("5e5ea5c16897e", collection.getId());
        assertEquals("Test Collection", collection.getName());
    }

    @Test
    void getCollection() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("collection"))
                .addHeader("Content-Type", "application/json"));
        Response<Collection> response =
                databaseService.getCollection("test-db", "test-collection").execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/databases/test-db/collections/test-collection", request.getPath());
        assertTrue(response.isSuccessful());
        Collection collection = response.body();
        assertNotNull(collection);
        assertEquals("5e5ea5c16897e", collection.getId());
        assertEquals("Test Collection", collection.getName());
    }

    @Test
    void deleteCollection() throws Exception {
        mockWebServer.enqueue(new MockResponse().setResponseCode(204));
        Response<Void> response =
                databaseService.deleteCollection("test-db", "test-collection").execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("DELETE", request.getMethod());
        assertEquals("/v1/databases/test-db/collections/test-collection", request.getPath());
        assertTrue(response.isSuccessful());
    }

    @Test
    void createDocument() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody(loadJsonFromResource("document"))
                .addHeader("Content-Type", "application/json"));
        Map<String, Object> data = new HashMap<>();
        data.put("name", "John Doe");
        data.put("age", 30);
        data.put("email", "john@example.com");
        Response<Document<Map<String, Object>>> response = databaseService
                .createDocument("test-db", "test-collection", "test-doc", data, Arrays.asList("read", "write"))
                .execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals("/v1/databases/test-db/collections/test-collection/documents", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("John%20Doe"));
        assertTrue(body.contains("30"));
        assertTrue(body.contains("example.com"));
        assertTrue(body.contains("read"));
        assertTrue(body.contains("write"));
        assertTrue(response.isSuccessful());
        Document<Map<String, Object>> document = response.body();
        assertNotNull(document);
        assertEquals("5e5ea5c16897e", document.getId());
        assertEquals("John Doe", document.getData().get("name"));
        assertEquals(30, ((Number) document.getData().get("age")).intValue());
    }

    @Test
    void listDocuments() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("[" + loadJsonFromResource("document") + "]")
                .addHeader("Content-Type", "application/json"));
        Response<List<Document<Map<String, Object>>>> response =
                databaseService.listDocuments("test-db", "test-collection").execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/databases/test-db/collections/test-collection/documents", request.getPath());
        assertTrue(response.isSuccessful());
        List<Document<Map<String, Object>>> documents = response.body();
        assertNotNull(documents);
        assertEquals(1, documents.size());
        Document<Map<String, Object>> document = documents.get(0);
        assertEquals("5e5ea5c16897e", document.getId());
        assertEquals("John Doe", document.getData().get("name"));
    }

    @Test
    void getDocument() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("document"))
                .addHeader("Content-Type", "application/json"));
        Response<Document<Map<String, Object>>> response = databaseService
                .getDocument("test-db", "test-collection", "test-doc")
                .execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/databases/test-db/collections/test-collection/documents/test-doc", request.getPath());
        assertTrue(response.isSuccessful());
        Document<Map<String, Object>> document = response.body();
        assertNotNull(document);
        assertEquals("5e5ea5c16897e", document.getId());
        assertEquals("John Doe", document.getData().get("name"));
    }

    @Test
    void updateDocument() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("document"))
                .addHeader("Content-Type", "application/json"));
        Map<String, Object> data = new HashMap<>();
        data.put("name", "Jane Doe");
        data.put("age", 25);
        Response<Document<Map<String, Object>>> response = databaseService
                .updateDocument("test-db", "test-collection", "test-doc", data, Arrays.asList("read", "write"))
                .execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("PATCH", request.getMethod());
        assertEquals("/v1/databases/test-db/collections/test-collection/documents/test-doc", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("Jane%20Doe"));
        assertTrue(body.contains("read"));
        assertTrue(body.contains("write"));
        assertTrue(response.isSuccessful());
        Document<Map<String, Object>> document = response.body();
        assertNotNull(document);
        assertEquals("5e5ea5c16897e", document.getId());
    }

    @Test
    void deleteDocument() throws Exception {
        mockWebServer.enqueue(new MockResponse().setResponseCode(204));
        Response<Void> response = databaseService
                .deleteDocument("test-db", "test-collection", "test-doc")
                .execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("DELETE", request.getMethod());
        assertEquals("/v1/databases/test-db/collections/test-collection/documents/test-doc", request.getPath());
        assertTrue(response.isSuccessful());
    }
}
