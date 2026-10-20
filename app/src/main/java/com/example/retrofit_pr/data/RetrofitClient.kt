package com.example.retrofit_pr.data

import com.example.retrofit_pr.data.service.LoginService
import com.example.retrofit_pr.data.service.PostsService
import com.example.retrofit_pr.data.service.ProductsService
import com.example.retrofit_pr.data.service.TodosService
import com.example.retrofit_pr.data.service.UsersService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.net.InetSocketAddress
import java.net.Proxy

object RetrofitClient {

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("10.207.106.59", 3128))

    val httpClientOk = OkHttpClient.Builder()
        .proxy(proxy)
        .addInterceptor(loggingInterceptor)
        .build()
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .client(httpClientOk)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: Retrofit = Retrofit.Builder()
        .baseUrl("http://10.207.106.59:8090/api/collections/")
        .client(httpClientOk)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val usersService: UsersService = retrofit.create(UsersService::class.java)

    val postsService: PostsService = retrofit.create(PostsService::class.java)

    val todosService: TodosService = retrofit.create(TodosService::class.java)

    val loginService: LoginService = api.create(LoginService::class.java)

    val productsService: ProductsService = api.create(ProductsService::class.java)
}