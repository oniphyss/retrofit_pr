package com.example.retrofit_pr.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit_pr.data.RetrofitClient
import com.example.retrofit_pr.data.TokenManager
import com.example.retrofit_pr.data.model.AuthRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class LoginViewModel(val tokenManager: TokenManager): ViewModel() {

    private val _isSuccessful = MutableStateFlow(false)
    val isSuccessful: StateFlow<Boolean> = _isSuccessful

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun login(authRequest: AuthRequest) {
        _isSuccessful.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            try {
                val authResponse = RetrofitClient.loginService.authWithPassword(authRequest)

                authResponse.token?.let {
                    tokenManager.saveToken(it)
                }
            } catch (e: Exception) {
                Log.e("LoginViewModel", e.message.toString())
            }
        }
    }
}