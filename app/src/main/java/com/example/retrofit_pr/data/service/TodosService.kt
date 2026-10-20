package com.example.retrofit_pr.data.service

import com.example.retrofit_pr.data.model.Todo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface TodosService {

    @GET("todos/{id}")
    suspend fun getTodoById(@Path("id") userId: Int): Todo

    @PUT("todos/{id}")
    suspend fun updateTodo(@Path("id") userId: Int, @Body todo: Todo): Todo
}