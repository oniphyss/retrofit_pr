package com.example.retrofit_pr.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofit_pr.ui.component.AlertMessage
import com.example.retrofit_pr.ui.component.BigButton
import com.example.retrofit_pr.ui.component.ProductCard
import com.example.retrofit_pr.ui.viewModel.ProductsViewModel

@Composable
fun EditScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductsViewModel = viewModel(),
    productId: String,
    onNavigateBack: () -> Unit
) {
    val product by viewModel.product.collectAsStateWithLifecycle()
    var showDialog by remember { mutableStateOf(false) }
    var isEdited by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.fetchProductById(productId)
    }

    Column(
        modifier = modifier
            .padding(start = 23.dp, end = 23.dp, top = 70.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
            ProductCard(
                modifier = Modifier
                    .fillMaxWidth(),
                productName = product.title ?: "",

                price = product.price?.toString() ?: "",
                isEdit = true,
                onPriceChange = {
                    isEdited = true
                    viewModel.updateProductState(product.copy(price = it.toDoubleOrNull()))
                }
            )
            Spacer(modifier = Modifier.height(26.dp))
            BigButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.updateProduct(productId)
                    showDialog = true
                },
                text = "Save",
                enabled = isEdited && product.price != null
            )
            if (showDialog) {
                AlertMessage(
                    onDismissRequest = {
                        showDialog = false
                        onNavigateBack()},
                    onConfirmClick = {
                        showDialog = false
                        onNavigateBack()},
                    dialogTitle = "Готово",
                    dialogText = "Данные обновлены"
                )
            }
        }
    }

@Preview
@Composable
private fun EditScreenPrev() {
    //EditScreen()
}