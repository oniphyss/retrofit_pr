package com.example.retrofit_pr.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit_pr.data.RetrofitClient
import com.example.retrofit_pr.data.TokenManager
import com.example.retrofit_pr.data.model.AuthRequest
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class LoginViewModel(val tokenManager: TokenManager,
                     val productsViewModel: ProductsViewModel): ViewModel() {


    fun login(authRequest: AuthRequest) {
        viewModelScope.launch {
            try {
                val authResponse = RetrofitClient.loginService.authWithPassword(authRequest)

                authResponse.token?.let {
                    tokenManager.saveToken(it)
                    productsViewModel.getProducts()
                }
            } catch (e: Exception) {
                Log.e("LoginViewModel", e.message.toString())
            }
        }
    }
}