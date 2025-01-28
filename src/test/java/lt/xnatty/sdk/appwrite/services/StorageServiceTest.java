package lt.xnatty.sdk.appwrite.services;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import lt.xnatty.sdk.appwrite.models.Bucket;
import lt.xnatty.sdk.appwrite.models.File;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class StorageServiceTest {
    private MockWebServer mockWebServer;
    private StorageService storageService;

    @BeforeEach
    void setUp() {
        mockWebServer = new MockWebServer();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/v1/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        storageService = retrofit.create(StorageService.class);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    private String loadJsonFromResource(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/json/" + filename + ".json")));
    }

    @Test
    void createBucket() throws Exception {

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody(loadJsonFromResource("storage_bucket"))
                .addHeader("Content-Type", "application/json"));
        Response<Bucket> response = storageService
                .createBucket(
                        "test-bucket",
                        "Test Bucket",
                        Arrays.asList("read", "write"),
                        30000000L,
                        Arrays.asList("jpg", "png", "pdf"),
                        true,
                        true)
                .execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals("/v1/storage/buckets", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("test-bucket"));
        assertTrue(body.contains("Test%20Bucket"));
        assertTrue(body.contains("read"));
        assertTrue(body.contains("write"));
        assertTrue(body.contains("30000000"));
        assertTrue(body.contains("jpg"));
        assertTrue(body.contains("true"));
        assertTrue(response.isSuccessful());
        Bucket bucket = response.body();
        assertNotNull(bucket);
        assertEquals("default", bucket.getId());
        assertEquals("Default", bucket.getName());
        assertTrue(bucket.isEnabled());
    }

    @Test
    void listBuckets() throws Exception {

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("[" + loadJsonFromResource("storage_bucket") + "]")
                .addHeader("Content-Type", "application/json"));
        Response<List<Bucket>> response = storageService.listBuckets().execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/storage/buckets", request.getPath());
        assertTrue(response.isSuccessful());
        List<Bucket> buckets = response.body();
        assertNotNull(buckets);
        assertEquals(1, buckets.size());
        Bucket bucket = buckets.getFirst();
        assertEquals("default", bucket.getId());
        assertEquals("Default", bucket.getName());
    }

    @Test
    void getBucket() throws Exception {

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("storage_bucket"))
                .addHeader("Content-Type", "application/json"));
        Response<Bucket> response = storageService.getBucket("test-bucket").execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/storage/buckets/test-bucket", request.getPath());
        assertTrue(response.isSuccessful());
        Bucket bucket = response.body();
        assertNotNull(bucket);
        assertEquals("default", bucket.getId());
        assertEquals("Default", bucket.getName());
    }

    @Test
    void deleteBucket() throws Exception {

        mockWebServer.enqueue(new MockResponse().setResponseCode(204));
        Response<Void> response = storageService.deleteBucket("test-bucket").execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("DELETE", request.getMethod());
        assertEquals("/v1/storage/buckets/test-bucket", request.getPath());
        assertTrue(response.isSuccessful());
    }

    @Test
    void createFile() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody(loadJsonFromResource("storage_file"))
                .addHeader("Content-Type", "application/json"));
        java.io.File testFile = new java.io.File("src/test/resources/test.txt");

        RequestBody requestBody = RequestBody.create(testFile, MediaType.parse("text/plain"));
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", testFile.getName(), requestBody);
        Response<File> response = storageService
                .createFile("test-bucket", filePart, "test-file", Arrays.asList("read", "write"))
                .execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals("/v1/storage/buckets/test-bucket/files", request.getPath());
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("test-file"));
        assertTrue(body.contains("read"));
        assertTrue(body.contains("write"));
        assertTrue(response.isSuccessful());
        File file = response.body();
        assertNotNull(file);
        assertEquals("5e5ea5c16897e", file.getId());
        assertEquals("example.jpg", file.getName());
    }

    @Test
    void listFiles() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("[" + loadJsonFromResource("storage_file") + "]")
                .addHeader("Content-Type", "application/json"));
        Response<List<File>> response = storageService.listFiles("test-bucket").execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/storage/buckets/test-bucket/files", request.getPath());
        assertTrue(response.isSuccessful());
        List<File> files = response.body();
        assertNotNull(files);
        assertEquals(1, files.size());
        File file = files.getFirst();
        assertEquals("5e5ea5c16897e", file.getId());
        assertEquals("example.jpg", file.getName());
    }

    @Test
    void getFile() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(loadJsonFromResource("storage_file"))
                .addHeader("Content-Type", "application/json"));
        Response<File> response =
                storageService.getFile("test-bucket", "test-file").execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/storage/buckets/test-bucket/files/test-file", request.getPath());
        assertTrue(response.isSuccessful());
        File file = response.body();
        assertNotNull(file);
        assertEquals("5e5ea5c16897e", file.getId());
        assertEquals("example.jpg", file.getName());
    }

    @Test
    void deleteFile() throws Exception {
        mockWebServer.enqueue(new MockResponse().setResponseCode(204));
        Response<Void> response =
                storageService.deleteFile("test-bucket", "test-file").execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("DELETE", request.getMethod());
        assertEquals("/v1/storage/buckets/test-bucket/files/test-file", request.getPath());
        assertTrue(response.isSuccessful());
    }
}
