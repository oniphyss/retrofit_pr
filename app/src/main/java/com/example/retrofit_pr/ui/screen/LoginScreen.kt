package com.example.retrofit_pr.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofit_pr.data.model.AuthRequest
import com.example.retrofit_pr.ui.component.BigButton
import com.example.retrofit_pr.ui.component.Input
import com.example.retrofit_pr.ui.viewModel.LoginViewModel

@Composable
fun LoginScreen(modifier: Modifier = Modifier,
                viewModel: LoginViewModel = viewModel(),
                onProductScreen: () -> Unit = {}
 ) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val isSuccessful by viewModel.isSuccessful.collectAsStateWithLifecycle()
    val errorMessage by viewModel.isSuccessful.collectAsStateWithLifecycle()

    if (isSuccessful) {
        onProductScreen()
    }

    Column(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Input(
            value = email,
            onValueChange = { email = it },
            label = "Email"
        )
        Spacer(modifier = modifier.height(20.dp))
        Input(
            value = password,
            onValueChange = { password = it },
            label = "Password"
        )
        Spacer(modifier = modifier.height(20.dp))

        BigButton(
            modifier = modifier.fillMaxWidth(),
            onClick = {
                val authRequest = AuthRequest(
                    identity = email,
                    password = password
                )
                viewModel.login(authRequest)
            },
            text = "Log in"
        )
    }

}

@Preview
@Composable
private fun LoginScreenPrev() {
    LoginScreen()
}