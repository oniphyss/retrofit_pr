package com.example.retrofit_pr.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit_pr.data.RetrofitClient
import kotlinx.coroutines.launch

class UsersViewModel: ViewModel() {

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
}