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
     *
     * @param name    数据库名称
     * @param id      数据库ID（可选）
     * @return 数据库信息
     */
    @POST("databases")
    @FormUrlEncoded
    Call<Database> createDatabase(
            @Field("name") String name,
            @Field("databaseId") String id
    );

    /**
     * 获取数据库列表
     *
     * @return 数据库列表
     */
    @GET("databases")
    Call<List<Database>> listDatabases();

    /**
     * 获取数据库信息
     *
     * @param databaseId 数据库ID
     * @return 数据库信息
     */
    @GET("databases/{databaseId}")
    Call<Database> getDatabase(@Path("databaseId") String databaseId);

    /**
     * 删除数据库
     *
     * @param databaseId 数据库ID
     * @return 空响应
     */
    @DELETE("databases/{databaseId}")
    Call<Void> deleteDatabase(@Path("databaseId") String databaseId);

    /**
     * 创建集合
     *
     * @param databaseId 数据库ID
     * @param name       集合名称
     * @param id         集合ID（可选）
     * @return 集合信息
     */
    @POST("databases/{databaseId}/collections")
    @FormUrlEncoded
    Call<Collection> createCollection(
            @Path("databaseId") String databaseId,
            @Field("name") String name,
            @Field("collectionId") String id
    );

    /**
     * 获取集合列表
     *
     * @param databaseId 数据库ID
     * @return 集合列表
     */
    @GET("databases/{databaseId}/collections")
    Call<List<Collection>> listCollections(@Path("databaseId") String databaseId);

    /**
     * 获取集合信息
     *
     * @param databaseId   数据库ID
     * @param collectionId 集合ID
     * @return 集合信息
     */
    @GET("databases/{databaseId}/collections/{collectionId}")
    Call<Collection> getCollection(
            @Path("databaseId") String databaseId,
            @Path("collectionId") String collectionId
    );

    /**
     * 删除集合
     *
     * @param databaseId   数据库ID
     * @param collectionId 集合ID
     * @return 空响应
     */
    @DELETE("databases/{databaseId}/collections/{collectionId}")
    Call<Void> deleteCollection(
            @Path("databaseId") String databaseId,
            @Path("collectionId") String collectionId
    );

    /**
     * 创建文档
     *
     * @param databaseId   数据库ID
     * @param collectionId 集合ID
     * @param data         文档数据
     * @param id           文档ID（可选）
     * @return 文档信息
     */
    @POST("databases/{databaseId}/collections/{collectionId}/documents")
    Call<Document<Map<String, Object>>> createDocument(
            @Path("databaseId") String databaseId,
            @Path("collectionId") String collectionId,
            @Body Map<String, Object> data,
            @Query("documentId") String id
    );

    /**
     * 获取文档列表
     *
     * @param databaseId   数据库ID
     * @param collectionId 集合ID
     * @return 文档列表
     */
    @GET("databases/{databaseId}/collections/{collectionId}/documents")
    Call<List<Document<Map<String, Object>>>> listDocuments(
            @Path("databaseId") String databaseId,
            @Path("collectionId") String collectionId
    );

    /**
     * 获取文档信息
     *
     * @param databaseId   数据库ID
     * @param collectionId 集合ID
     * @param documentId   文档ID
     * @return 文档信息
     */
    @GET("databases/{databaseId}/collections/{collectionId}/documents/{documentId}")
    Call<Document<Map<String, Object>>> getDocument(
            @Path("databaseId") String databaseId,
            @Path("collectionId") String collectionId,
            @Path("documentId") String documentId
    );

    /**
     * 更新文档
     *
     * @param databaseId   数据库ID
     * @param collectionId 集合ID
     * @param documentId   文档ID
     * @param data         更新的数据
     * @return 更新后的文档信息
     */
    @PATCH("databases/{databaseId}/collections/{collectionId}/documents/{documentId}")
    Call<Document<Map<String, Object>>> updateDocument(
            @Path("databaseId") String databaseId,
            @Path("collectionId") String collectionId,
            @Path("documentId") String documentId,
            @Body Map<String, Object> data
    );

    /**
     * 删除文档
     *
     * @param databaseId   数据库ID
     * @param collectionId 集合ID
     * @param documentId   文档ID
     * @return 空响应
     */
    @DELETE("databases/{databaseId}/collections/{collectionId}/documents/{documentId}")
    Call<Void> deleteDocument(
            @Path("databaseId") String databaseId,
            @Path("collectionId") String collectionId,
            @Path("documentId") String documentId
    );
} 