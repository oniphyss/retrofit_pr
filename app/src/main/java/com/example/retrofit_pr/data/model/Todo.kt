package com.example.retrofit_pr.data.model

data class Todo(
    val id: Int? = null,
    val todo: String,
    val completed: Boolean,
    val userId: Int
)
