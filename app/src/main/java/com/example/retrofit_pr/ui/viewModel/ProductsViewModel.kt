package com.example.retrofit_pr.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit_pr.data.RetrofitClient
import com.example.retrofit_pr.data.TokenManager
import com.example.retrofit_pr.data.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProductsViewModel(val tokenManager: TokenManager): ViewModel() {

    private val _productList = MutableStateFlow<List<Product>>(emptyList())
    private val _product = MutableStateFlow(Product(
        title = "",
        price = null
    ))
    val product: StateFlow<Product> = _product

    val productList: StateFlow<List<Product>> = _productList

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
                        _productList.value = productResponse.items
                    }
                }
            } catch (e: Exception) {
                Log.e("ProductsViewModel", e.message.toString())
            }
        }
    }

    fun updateProductState(product: Product) {
        _product.value = product
    }

    fun fetchProductById(productId: String) {
        viewModelScope.launch {
            try {
                val token = tokenManager.tokenFlow().first()
                token?.let {
                    val product = RetrofitClient.productsService.getProductById(token, productId)
                    _product.value = product
                    Log.d("ProductsViewModel", "id: ${product.id}" +
                            "title: ${product.title}" +
                            "price: ${product.price}")
                }
            } catch (e: Exception) {
                Log.e("ProductsViewModel", e.message.toString())
            }
        }
    }

    fun updateProduct(id: String) {
        viewModelScope.launch {
            try {
                val token = tokenManager.tokenFlow().first()
                token?.let {
                    _product.value.id?.let {
                        val updatedProduct = RetrofitClient.productsService.updateProduct(token,id, _product.value)
                        Log.d("ProductsViewModel", "id: ${updatedProduct.id}" +
                                "title: ${updatedProduct.title}" +
                                "price: ${updatedProduct.price}")
                        _product.value = updatedProduct
                    }
                }
            } catch (e: Exception) {
                Log.e("ProductsViewModel", e.message.toString())
            }
        }
    }
}