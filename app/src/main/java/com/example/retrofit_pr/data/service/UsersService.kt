package com.example.retrofit_pr.data.service

import com.example.retrofit_pr.data.model.Post
import com.example.retrofit_pr.data.model.User
import com.example.retrofit_pr.data.model.UsersResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UsersService {

    @GET("users")
    suspend fun getUsers(): UsersResponse

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") userId: Int): User
}