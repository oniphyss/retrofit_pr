package com.example.retrofit_pr.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrofit_pr.R
import com.example.retrofit_pr.ui.theme.Black
import com.example.retrofit_pr.ui.theme.BlackRegular
import com.example.retrofit_pr.ui.theme.DisabledButton
import com.example.retrofit_pr.ui.theme.White

@Composable
fun ProductCard(modifier: Modifier = Modifier,
                productName: String,
                price: String) {
    Column(modifier = modifier
        .background(color = White, shape = RoundedCornerShape(4.dp))
        .padding(8.dp)
        .fillMaxWidth()
        ) {
        Text(
            text = productName,
            fontFamily = FontFamily(Font(R.font.nunito_sans_semibold, FontWeight.SemiBold)),
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = DisabledButton
        )
        Spacer(modifier = modifier.height(6.dp))
        Text(
            text = "$ $price",
            fontFamily = FontFamily(Font(R.font.nunito_sans_bold, FontWeight.Bold)),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Black
        )
    }

}

@Preview
@Composable
private fun ProductCardPrev() {
    ProductCard(
        productName = "fff",
        price = "20"
    )
}