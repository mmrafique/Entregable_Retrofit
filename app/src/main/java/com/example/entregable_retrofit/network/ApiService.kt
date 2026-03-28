package com.example.entregable_retrofit.network

import com.example.entregable_retrofit.models.Comment
import com.example.entregable_retrofit.models.Post
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("posts")
    suspend fun getAllPosts(): List<Post>

    @GET("posts")
    suspend fun getPostsByLimit(@Query("_limit") limit: Int): List<Post>

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): Post

    @GET("posts/{id}/comments")
    suspend fun getCommentsByPostId(@Path("id") postId: Int): List<Comment>
}
