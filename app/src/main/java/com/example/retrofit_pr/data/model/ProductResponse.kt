package com.example.retrofit_pr.data.model

data class ProductResponse(
    val items: List<Product>? = null,
    val page: Int? = null,
    val perPage: Int? = null,
    val totalItems: Int? = null,
    val totalPages: Int? = null
)
