package com.example.retrofit_pr.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofit_pr.R
import com.example.retrofit_pr.data.model.AuthRequest
import com.example.retrofit_pr.ui.component.BigButton
import com.example.retrofit_pr.ui.component.Input
import com.example.retrofit_pr.ui.theme.Black
import com.example.retrofit_pr.ui.theme.DisabledButton
import com.example.retrofit_pr.ui.theme.Grey
import com.example.retrofit_pr.ui.viewModel.LoginViewModel

@Composable
fun LoginScreen(modifier: Modifier = Modifier,
                viewModel: LoginViewModel = viewModel(),
                onProductScreen: () -> Unit = {}
 ) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val isSuccessful by viewModel.isSuccessful.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()

    if (isSuccessful) {
        onProductScreen()
    }

    Column(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.height(66.dp))
        Row(modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(painter = painterResource(R.drawable.line), contentDescription = "line")
            Image(painter = painterResource(R.drawable.shop_icon), contentDescription = "icon")
            Image(painter = painterResource(R.drawable.line), contentDescription = "line")
        }
        Spacer(modifier = modifier.height(30.dp))
        Text(
            modifier = modifier.align(Alignment.Start),
            text = "Hello !",
            fontFamily = FontFamily(Font(R.font.merriweather, FontWeight.Normal)),
            fontWeight = FontWeight.Normal,
            fontSize = 30.sp,
            lineHeight = 45.sp,
            color = Grey
        )
        Text(
            modifier = modifier.align(Alignment.Start),
            text = "WELCOME BACK",
            fontFamily = FontFamily(Font(R.font.merriweather_bold, FontWeight.Bold)),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            lineHeight = 45.sp,
            color = Black
        )
        Spacer(modifier = modifier.height(53.dp))
        Input(
            modifier = modifier.fillMaxWidth(),
            value = email,
            onValueChange = { email = it },
            label = "Email"
        )
        Spacer(modifier = modifier.height(20.dp))
        Input(
            modifier = modifier.fillMaxWidth(),
            value = password,
            onValueChange = { password = it },
            label = "Password"
        )
        Spacer(modifier = modifier.height(20.dp))
        errorMessage?.let {
            Text(text = it)
        }
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