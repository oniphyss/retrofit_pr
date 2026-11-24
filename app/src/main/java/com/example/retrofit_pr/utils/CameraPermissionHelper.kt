package com.example.retrofit_pr.utils

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun rememberCameraPermissionHelper(
    onGranted: () -> Unit,
    onDenied: () -> Unit = {}
): CameraPermissionState {
    val context = LocalContext.current

    val hasPermission = remember(context) {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) {
        isGranted -> if (isGranted) onGranted() else onDenied()
    }

    return remember(hasPermission, launcher) {
        CameraPermissionState(
            launcher,
            hasPermission
        )
    }
}