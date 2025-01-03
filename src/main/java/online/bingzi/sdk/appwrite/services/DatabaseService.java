package online.bingzi.sdk.appwrite.services;

import online.bingzi.sdk.appwrite.models.Collection;
import online.bingzi.sdk.appwrite.models.Database;
import online.bingzi.sdk.appwrite.models.Document;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

/**
 * 数据库服务接口
 */
public interface DatabaseService {
    /**
     * 创建数据库
     */
    @FormUrlEncoded
    @POST("databases")
    Call<Database> createDatabase(
            @Field("name") String name,
            @Field("databaseId") String databaseId
    );

    /**
     * 获取数据库列表
     */
    @GET("databases")
    Call<List<Database>> listDatabases();

    /**
     * 获取数据库信息
     */
    @GET("databases/{databaseId}")
    Call<Database> getDatabase(@Path("databaseId") String databaseId);

    /**
     * 删除数据库
     */
    @DELETE("databases/{databaseId}")
    Call<Void> deleteDatabase(@Path("databaseId") String databaseId);

    /**
     * 创建集合
     */
    @FormUrlEncoded
    @POST("databases/{databaseId}/collections")
    Call<Collection> createCollection(
            @Path("databaseId") String databaseId,
            @Field("name") String name,
            @Field("collectionId") String collectionId,
            @Field("permissions") List<String> permissions
    );

    /**
     * 获取集合列表
     */
    @GET("databases/{databaseId}/collections")
    Call<List<Collection>> listCollections(@Path("databaseId") String databaseId);

    /**
     * 获取集合信息
     */
    @GET("databases/{databaseId}/collections/{collectionId}")
    Call<Collection> getCollection(
            @Path("databaseId") String databaseId,
            @Path("collectionId") String collectionId
    );

    /**
     * 删除集合
     */
    @DELETE("databases/{databaseId}/collections/{collectionId}")
    Call<Void> deleteCollection(
            @Path("databaseId") String databaseId,
            @Path("collectionId") String collectionId
    );

    /**
     * 创建文档
     */
    @POST("databases/{databaseId}/collections/{collectionId}/documents")
    Call<Document<Map<String, Object>>> createDocument(
            @Path("databaseId") String databaseId,
            @Path("collectionId") String collectionId,
            @Field("documentId") String documentId,
            @Body Map<String, Object> data,
            @Field("permissions") List<String> permissions
    );

    /**
     * 获取文档列表
     */
    @GET("databases/{databaseId}/collections/{collectionId}/documents")
    Call<List<Document<Map<String, Object>>>> listDocuments(
            @Path("databaseId") String databaseId,
            @Path("collectionId") String collectionId
    );

    /**
     * 获取文档信息
     */
    @GET("databases/{databaseId}/collections/{collectionId}/documents/{documentId}")
    Call<Document<Map<String, Object>>> getDocument(
            @Path("databaseId") String databaseId,
            @Path("collectionId") String collectionId,
            @Path("documentId") String documentId
    );

    /**
     * 更新文档
     */
    @PATCH("databases/{databaseId}/collections/{collectionId}/documents/{documentId}")
    Call<Document<Map<String, Object>>> updateDocument(
            @Path("databaseId") String databaseId,
            @Path("collectionId") String collectionId,
            @Path("documentId") String documentId,
            @Body Map<String, Object> data,
            @Field("permissions") List<String> permissions
    );

    /**
     * 删除文档
     */
    @DELETE("databases/{databaseId}/collections/{collectionId}/documents/{documentId}")
    Call<Void> deleteDocument(
            @Path("databaseId") String databaseId,
            @Path("collectionId") String collectionId,
            @Path("documentId") String documentId
    );
} 