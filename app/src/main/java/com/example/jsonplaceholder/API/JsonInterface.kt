package com.example.jsonplaceholder.API

import com.example.jsonplaceholder.Get.CommentApiResponce
import com.example.jsonplaceholder.Get.PostApiResponce
import retrofit2.Call
import retrofit2.http.*

interface JsonInterface {
    @GET("posts")
    fun getPost() : Call<List<PostApiResponce>>

    @GET("comments")
    fun getComments() : Call<List<CommentApiResponce>>

    @GET("posts/{id}")
    fun getPostById(@Path("id") id: Int) : Call<PostApiResponce>

    @GET("posts")
    fun getPostByUserId(
        @Query("userId") userId: Int,
//        @QueryMap options: HashMap<String, String>
        @Query("_sort") sort : String,
        @Query("_order") order : String
    ) : Call<List<PostApiResponce>>

    @GET("posts/{id}/comments")
    fun getCommentsById(@Path("id") id: Int) : Call<List<CommentApiResponce>>

    @POST("posts")
    fun postData(
        @Body posts : PostApiResponce
    ) : Call<PostApiResponce>

    @PATCH("posts/{id}")
    fun patchData(@Path("id") id: Int, @Body postApiResponce: PostApiResponce):Call<PostApiResponce>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id:Int) : Call<PostApiResponce>
}