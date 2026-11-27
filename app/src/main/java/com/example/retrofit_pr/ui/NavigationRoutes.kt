package com.example.retrofit_pr.ui

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object Products

@Serializable
data class EditProduct(val productId: String? = null)

@Serializable
data class Profile(val userId: String? = null)