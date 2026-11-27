package com.example.retrofit_pr.utils

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun rememberStandardCameraLauncher(
    onPhotoResult: (Boolean) -> Unit
): (Uri) -> Unit {
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) {
        success: Boolean ->
        onPhotoResult(success)
    }

    return { targetUri ->
        cameraLauncher.launch(targetUri)
    }
}