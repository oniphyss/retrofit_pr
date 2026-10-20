package com.example.retrofit_pr.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit_pr.data.RetrofitClient
import kotlinx.coroutines.launch

class TodosViewModel: ViewModel() {

    fun updateTodo(id: Int) {
        viewModelScope.launch {
            try {
                val todo = RetrofitClient.todosService.getTodoById(id)
                Log.d("TodosViewModel", "id = ${todo.id}\n" +
                                                    "todo = ${todo.todo}\n" +
                                                    "completed = ${todo.completed}\n" +
                                                    "userId = ${todo.userId}")

                val newTodo = todo.copy(
                    todo = "Пропылесосить ковер",
                    completed = false,
                    userId = 31
                )

                val updatedTodo = RetrofitClient.todosService.updateTodo(id, newTodo)
                Log.d("TodosViewModel", "id = ${updatedTodo.id}\n" +
                        "todo = ${updatedTodo.todo}\n" +
                        "completed = ${updatedTodo.completed}\n" +
                        "userId = ${updatedTodo.userId}")

            } catch (e: Exception) {
                Log.e("TodosViewModel", e.message.toString())
            }
        }
    }
}