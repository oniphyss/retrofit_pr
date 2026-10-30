package com.example.retrofit_pr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.retrofit_pr.data.TokenManager
import com.example.retrofit_pr.data.model.AuthRequest
import com.example.retrofit_pr.data.model.Post
import com.example.retrofit_pr.ui.screen.ProductScreen
import com.example.retrofit_pr.ui.viewModel.LoginViewModel
import com.example.retrofit_pr.ui.viewModel.PostsViewModel
import com.example.retrofit_pr.ui.viewModel.ProductsViewModel
import com.example.retrofit_pr.ui.viewModel.TodosViewModel
import com.example.retrofit_pr.ui.viewModel.UsersViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val tokenManager = TokenManager(applicationContext)

        setContent {
//            val usersViewModel: UsersViewModel = viewModel()
//            usersViewModel.fetchUsers()

//            val post = Post(
//            title = "Уют в каждой детали: встречайте нашу новинку!",
//            body = "Мы знаем, как важно возвращаться туда, где тепло и спокойно. Наша новая коллекция ароматических свечей из соевого воска создана именно для таких моментов.",
//            tags = listOf("декор дома", "уют в доме", "аромасвечи", "подарок девушке", "ручная работа", "новинка"),
//            views = 0, userId = 1)
//
//            val postsViewModel: PostsViewModel = viewModel()
//            postsViewModel.addPost(post)

//            val todosViewModel: TodosViewModel = viewModel()
//            todosViewModel.updateTodo(145)

//            val usersViewModel: UsersViewModel = viewModel()
//            usersViewModel.deleteUser(18)

            val productsViewModel: ProductsViewModel by viewModels {
                viewModelFactory {
                    initializer {
                        ProductsViewModel(tokenManager)
                    }
                }
            }

            val loginViewModel: LoginViewModel by viewModels {
                viewModelFactory {
                    initializer {
                        LoginViewModel(tokenManager, productsViewModel)
                    }
                }
            }

            val authRequest = AuthRequest(
                    "schast@example.com",
                   "123456789"
            )
            loginViewModel.login(authRequest)
            ProductScreen()
        }
    }
}