package lt.xnatty.sdk.appwrite.services;

import java.util.List;
import java.util.Map;
import lt.xnatty.sdk.appwrite.models.Collection;
import lt.xnatty.sdk.appwrite.models.Database;
import lt.xnatty.sdk.appwrite.models.Document;
import retrofit2.Call;
import retrofit2.http.*;

public interface DatabaseService {

    @FormUrlEncoded
    @POST("databases")
    Call<Database> createDatabase(@Field("name") String name, @Field("databaseId") String databaseId);

    @GET("databases")
    Call<List<Database>> listDatabases();

    @GET("databases/{databaseId}")
    Call<Database> getDatabase(@Path("databaseId") String databaseId);

    @DELETE("databases/{databaseId}")
    Call<Void> deleteDatabase(@Path("databaseId") String databaseId);

    @FormUrlEncoded
    @POST("databases/{databaseId}/collections")
    Call<Collection> createCollection(
            @Path("databaseId") String databaseId,
            @Field("name") String name,
            @Field("collectionId") String collectionId,
            @Field("permissions") List<String> permissions);

    @GET("databases/{databaseId}/collections")
    Call<List<Collection>> listCollections(@Path("databaseId") String databaseId);

    @GET("databases/{databaseId}/collections/{collectionId}")
    Call<Collection> getCollection(@Path("databaseId") String databaseId, @Path("collectionId") String collectionId);

    @DELETE("databases/{databaseId}/collections/{collectionId}")
    Call<Void> deleteCollection(@Path("databaseId") String databaseId, @Path("collectionId") String collectionId);

    @FormUrlEncoded
    @POST("databases/{databaseId}/collections/{collectionId}/documents")
    Call<Document<Map<String, Object>>> createDocument(
            @Path("databaseId") String databaseId,
            @Path("collectionId") String collectionId,
            @Field("documentId") String documentId,
            @Field("data") Map<String, Object> data,
            @Field("permissions") List<String> permissions);

    @GET("databases/{databaseId}/collections/{collectionId}/documents")
    Call<List<Document<Map<String, Object>>>> listDocuments(
            @Path("databaseId") String databaseId, @Path("collectionId") String collectionId);

    @GET("databases/{databaseId}/collections/{collectionId}/documents/{documentId}")
    Call<Document<Map<String, Object>>> getDocument(
            @Path("databaseId") String databaseId,
            @Path("collectionId") String collectionId,
            @Path("documentId") String documentId);

    @FormUrlEncoded
    @PATCH("databases/{databaseId}/collections/{collectionId}/documents/{documentId}")
    Call<Document<Map<String, Object>>> updateDocument(
            @Path("databaseId") String databaseId,
            @Path("collectionId") String collectionId,
            @Path("documentId") String documentId,
            @Field("data") Map<String, Object> data,
            @Field("permissions") List<String> permissions);

    @DELETE("databases/{databaseId}/collections/{collectionId}/documents/{documentId}")
    Call<Void> deleteDocument(
            @Path("databaseId") String databaseId,
            @Path("collectionId") String collectionId,
            @Path("documentId") String documentId);
}
