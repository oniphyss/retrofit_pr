package com.example.retrofit_pr.ui.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AlertMessage(modifier: Modifier = Modifier,
                 onDismissRequest: () -> Unit,
                 onConfirmClick: () -> Unit,
                 dialogTitle: String,
                 dialogText: String) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = dialogTitle) },
        text = { Text(text = dialogText) },
        confirmButton = {
            Button(
                onClick = onConfirmClick
            ) {
                Text(text = "OK")
            }
        }
    )
}