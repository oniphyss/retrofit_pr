package com.example.retrofit_pr.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofit_pr.ui.component.BigButton
import com.example.retrofit_pr.ui.component.ProductCard
import com.example.retrofit_pr.ui.viewModel.ProductsViewModel

@Composable
fun EditScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductsViewModel = viewModel(),
    productId: String
) {
    val product by viewModel.product.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchProductById(productId)
    }

    Column(
        modifier = modifier
    ) {
            ProductCard(
                modifier = modifier
                    .fillMaxWidth(),
                productName = product.title ?: "",
                price = product.price.toString(),
                isEdit = true,
                onPriceChange = {
                    viewModel.updateProductState(product.copy(price = it.toIntOrNull() ?: product.price))
                }
            )
            BigButton(
                modifier = modifier.fillMaxWidth(),
                onClick = {
                    viewModel.updateProduct(productId)
                },
                text = "Save",
                enabled = true
            )
        }
    }

@Preview
@Composable
private fun EditScreenPrev() {
    //EditScreen()
}