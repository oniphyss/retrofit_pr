package com.example.retrofit_pr.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.retrofit_pr.R
import com.example.retrofit_pr.ui.theme.Black
import com.example.retrofit_pr.ui.theme.BlackRegular

@Composable
fun ProductCard(modifier: Modifier = Modifier,
                productName: String,
                price: String) {
    Column(modifier = modifier) {
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.product_image),
            contentDescription = "Product image",
            contentScale = ContentScale.FillWidth,
            modifier = modifier.fillMaxWidth()
        )
        Text(
            text = productName,
            fontFamily = FontFamily(Font(R.font.nunito_sans, FontWeight.Normal)),
            fontSize = 14.sp,
            color = BlackRegular
        )
        Text(
            text = price,
            fontFamily = FontFamily(Font(R.font.nunito_sans_bold, FontWeight.Bold)),
            fontSize = 14.sp,
            color = Black
        )
    }

}

@Preview
@Composable
private fun ProductCardPrev() {
    ProductCard(
        productName = "fff",
        price = "$ 20"
    )
}