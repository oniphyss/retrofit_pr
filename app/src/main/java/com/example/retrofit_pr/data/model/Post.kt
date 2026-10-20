package com.example.retrofit_pr.data.model

data class Post(
    val id: Int? = null,
    val title: String,
    val body: String,
    val tags: List<String>,
    val views: Int,
    val userId: Int
)
