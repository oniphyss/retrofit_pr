package com.example.retrofit_pr.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrofit_pr.R
import com.example.retrofit_pr.ui.theme.BlackRegular
import com.example.retrofit_pr.ui.theme.DisabledButton
import com.example.retrofit_pr.ui.theme.Primary
import com.example.retrofit_pr.ui.theme.White

@Composable
fun BigButton(modifier: Modifier = Modifier,
              onClick: () -> Unit,
              text: String) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonColors(
            containerColor = Primary,
            contentColor = White,
            disabledContainerColor = DisabledButton,
            disabledContentColor = White
        )
    ) {
        Text(
            text = text,
            fontFamily = FontFamily(Font(R.font.nunito_sans_semibold, FontWeight.SemiBold)),
            fontSize = 18.sp,
        )
    }
}

@Preview
@Composable
private fun BigButtonPreview() {
    BigButton(
        onClick = {},
        text = ""
    )
}