package com.example.retrofit_pr.data.model

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val phone: String,
    val isDeleted: Boolean? = null,
    val deletedOn: String? = null
)
