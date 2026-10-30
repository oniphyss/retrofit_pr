package com.example.retrofit_pr.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrofit_pr.R
import com.example.retrofit_pr.ui.theme.Border
import com.example.retrofit_pr.ui.theme.DisabledField
import com.example.retrofit_pr.ui.theme.Primary
import com.example.retrofit_pr.ui.theme.PrimaryUnfocused
import com.example.retrofit_pr.ui.theme.White

@Composable
fun Input(modifier: Modifier = Modifier,
          value: String,
          onValueChange: (String) -> Unit) {

    TextField(
        modifier =  modifier
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(4.dp),
                color = Border
            ),
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Primary,
            unfocusedTextColor = PrimaryUnfocused,
            focusedContainerColor = White,
            unfocusedContainerColor = DisabledField
        ),
        textStyle = TextStyle(
            fontFamily = FontFamily(Font(R.font.nunito_sans_semibold, weight = FontWeight.SemiBold)),
            fontSize = 16.sp,
        ),
        shape = RoundedCornerShape(4.dp)
    )
}

@Preview
@Composable
private fun InputPreview() {
}