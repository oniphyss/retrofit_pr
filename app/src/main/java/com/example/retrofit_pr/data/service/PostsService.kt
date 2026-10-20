package com.example.retrofit_pr.data.service

import com.example.retrofit_pr.data.model.Post
import retrofit2.http.Body
import retrofit2.http.POST

interface PostsService {

    @POST("posts/add")
    suspend fun addPost(@Body post: Post): Post
}