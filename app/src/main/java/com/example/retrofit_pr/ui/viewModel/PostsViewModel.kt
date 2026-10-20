package com.example.retrofit_pr.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit_pr.data.RetrofitClient
import com.example.retrofit_pr.data.model.Post
import kotlinx.coroutines.launch

class PostsViewModel: ViewModel() {

    fun addPost(post: Post) {
        viewModelScope.launch {
            try {
                val newPost = Post(
                    title = post.title,
                    body = post.body,
                    tags = post.tags,
                    views = post.views,
                    userId = post.userId)

                val post = RetrofitClient.postsService.addPost(newPost)
                Log.d("PostsViewModel",
                    "id = ${post.id}" +
                            "\nНазвание: ${post.title}" +
                            "\nТекст: ${post.body}" +
                            "\nТеги: ${post.tags}" +
                            "\nПросмотры: ${post.views}")
            }
            catch (ex: Exception ) {
                Log.e("PostsViewModel", "${ex.message}")
            }
        }
    }
}