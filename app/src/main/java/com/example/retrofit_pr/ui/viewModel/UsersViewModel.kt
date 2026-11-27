package com.example.retrofit_pr.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit_pr.data.RetrofitClient
import com.example.retrofit_pr.data.TokenManager
import com.example.retrofit_pr.data.model.Product
import com.example.retrofit_pr.data.model.Profile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UsersViewModel(val tokenManager: TokenManager): ViewModel() {

    private val _user = MutableStateFlow(Profile(
        firstName = "",
        lastName = "",
        email = ""
    ))
    val user: StateFlow<Profile> = _user

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val usersResponse = RetrofitClient.usersService.getUsers()
                val users = usersResponse.users
                for (user in users) {
                    Log.d("UsersViewModel",
                        "Имя: ${user.firstName} ${user.lastName}" +
                                "\nВозраст: ${user.age}" +
                                "\nТелефон: ${user.phone}")
                }
            }
            catch (ex: Exception ) {
                Log.d("UsersViewModel", "${ex.message}")
            }
        }
    }

    fun deleteUser(userId: Int) {
        viewModelScope.launch {
            try {
                val deletedUser = RetrofitClient.usersService.deleteUser(userId)
                Log.d("UsersViewModel",
                    "id: ${deletedUser.id}\n" +
                            "name: ${deletedUser.firstName} ${deletedUser.lastName}\n" +
                            "isDeleted: ${deletedUser.isDeleted}\n" +
                            "deletedOn: ${deletedUser.deletedOn}")
            } catch (ex: Exception ) {
                Log.d("UsersViewModel", "${ex.message}")
            }
        }
    }

    fun getUser(userId: String) {
        viewModelScope.launch {
            try {
                val token = tokenManager.tokenFlow().first()
                token?.let {
                    _user.value.id?.let {
                        val user = RetrofitClient.usersService.getUser(token, userId)
                        _user.value = user
                        Log.d("UsersViewModel",
                            "name: ${user.firstName} ${user.lastName}\n" +
                                    "email: ${user.email}\n")
                    }
                }
            } catch (ex: Exception ) {
                Log.d("UsersViewModel", "${ex.message}")
            }
        }
    }
}