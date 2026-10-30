package com.example.retrofit_pr.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofit_pr.ui.component.ProductCard
import com.example.retrofit_pr.ui.viewModel.ProductsViewModel

@Composable
fun ProductScreen(modifier: Modifier = Modifier,
                  viewModel: ProductsViewModel = viewModel()
) {
    val productList by viewModel.productList.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getProducts()
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(21.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(productList) { product ->
            ProductCard(
                productName = "${product.title}",
                price = "${product.price}"
            )
        }
    }
}

@Preview
@Composable
private fun ProductScreenPreview() {
    ProductScreen()
}