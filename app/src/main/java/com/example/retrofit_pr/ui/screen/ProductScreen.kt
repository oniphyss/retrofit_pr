package com.example.retrofit_pr.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofit_pr.ui.component.ProductCard
import com.example.retrofit_pr.ui.viewModel.ProductsViewModel

@Composable
fun ProductScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductsViewModel = viewModel()
) {
    val productList by viewModel.productList.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getProducts()
    }

    LazyColumn(
        contentPadding = PaddingValues(top = 64.dp, bottom = 20.dp, start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = modifier
    ) {
        items(productList) { product ->
            ProductCard(
                productName = product.title ?: "",
                price = product.price.toString()
            )
        }
    }
}

@Preview
@Composable
private fun ProductScreenPreview() {
    ProductScreen()
}