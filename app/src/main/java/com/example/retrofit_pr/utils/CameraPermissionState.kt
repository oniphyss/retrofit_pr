package com.example.retrofit_pr.utils

import android.Manifest
import androidx.activity.result.ActivityResultLauncher

class CameraPermissionState(
    private val launcher: ActivityResultLauncher<String>,
    val hasPermission: Boolean
) {
    fun launchPermissionRequest() {
        if (!hasPermission) {
            launcher.launch(Manifest.permission.CAMERA)
        }
    }
}