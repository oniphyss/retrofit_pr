package com.example.retrofit_pr.data.service

import com.example.retrofit_pr.data.model.AuthRequest
import com.example.retrofit_pr.data.model.AuthResponse
import com.example.retrofit_pr.data.model.Post
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("users/auth-with-password")
    suspend fun authWithPassword(@Body authRequest: AuthRequest): AuthResponse
}