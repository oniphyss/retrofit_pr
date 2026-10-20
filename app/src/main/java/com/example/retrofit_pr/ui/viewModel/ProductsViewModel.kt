package com.example.retrofit_pr.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit_pr.data.RetrofitClient
import com.example.retrofit_pr.data.TokenManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProductsViewModel(val tokenManager: TokenManager): ViewModel() {
    fun getProducts() {
        viewModelScope.launch {
            try {
                val token = tokenManager.tokenFlow().first()
                token?.let {
                    val productResponse = RetrofitClient.productsService.getProducts(token)
                    productResponse.items?.let {
                        for (item in productResponse.items) {
                            Log.d("ProductsViewModel", "id = ${item.id}\n" +
                                    "title = ${item.title}\n" +
                                    "price = ${item.price}")
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("ProductsViewModel", e.message.toString())
            }
        }
    }
}