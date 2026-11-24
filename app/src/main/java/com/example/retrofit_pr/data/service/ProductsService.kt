package com.example.retrofit_pr.data.service

import com.example.retrofit_pr.data.model.Product
import com.example.retrofit_pr.data.model.ProductResponse
import com.example.retrofit_pr.data.model.Todo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.PUT
import retrofit2.http.Path


interface ProductsService {

    @GET("products/records")
    suspend fun getProducts(@Header("Authorization") token: String): ProductResponse

    @GET("products/records/{id}")
    suspend fun getProductById(@Header("Authorization") token: String, @Path("id") productId: String): Product

    @PATCH("products/records/{id}")
    suspend fun updateProduct(@Header("Authorization") token: String, @Path("id") productId: String, @Body product: Product): Product
}