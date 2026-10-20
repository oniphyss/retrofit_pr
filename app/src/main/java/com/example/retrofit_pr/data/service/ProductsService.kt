package com.example.retrofit_pr.data.service

import com.example.retrofit_pr.data.model.ProductResponse
import com.example.retrofit_pr.data.model.Todo
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path


interface ProductsService {

    @GET("products/records")
    suspend fun getProducts(@Header("Authorization") token: String): ProductResponse
}