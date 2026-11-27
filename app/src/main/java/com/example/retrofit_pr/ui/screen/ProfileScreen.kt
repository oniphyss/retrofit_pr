package com.example.retrofit_pr.ui.screen

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.retrofit_pr.ui.viewModel.UsersViewModel
import com.example.retrofit_pr.utils.rememberCameraPermissionHelper
import com.example.retrofit_pr.utils.rememberStandardCameraLauncher
import java.io.File

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: UsersViewModel = viewModel(),
    userId: String
 ) {
    val context = LocalContext.current

    val user by viewModel.user.collectAsStateWithLifecycle()
    var photoUri by remember { mutableStateOf<Uri?>(null) }
    var isPhotoCaptured by remember { mutableStateOf(false) }

    val launchStandardCamera = rememberStandardCameraLauncher(
        onPhotoResult = { success ->
            if (success) {
                Toast.makeText(context, "Фото сохранено: $photoUri", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Съемка отменена или произошла ошибка", Toast.LENGTH_SHORT).show()
            }
        }
    )

    val cameraPermission = rememberCameraPermissionHelper(
        onGranted = {
            val uri = createPhotoUri(context)
            photoUri = uri
            launchStandardCamera(uri)
        },
        onDenied = {
            Toast.makeText(context, "Доступ к камере отклонен", Toast.LENGTH_SHORT).show()
        }
    )

    LaunchedEffect(userId) {
        if(cameraPermission.hasPermission){
            viewModel.getUser(userId)
        }
    }

    Row(modifier = modifier
        .fillMaxSize(),
        horizontalArrangement = Arrangement.Start) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .clickable {
                    if (cameraPermission.hasPermission) {
                        isPhotoCaptured = false
                        val uri = createPhotoUri(context)
                        photoUri = uri
                        launchStandardCamera(uri)
                    } else {
                        cameraPermission.launchPermissionRequest()
                    }
                }
        ) {
            if (isPhotoCaptured && photoUri != null) {
                AsyncImage(
                    model = photoUri,
                    contentDescription = "Сделанное фото",
                    contentScale = ContentScale.Crop,
                    onError = { error ->
                        android.util.Log.e("CameraError", "Ошибка загрузки фото: ${error.result.throwable}")
                    },
                    modifier = Modifier
                        .size(250.dp)
                        .padding(bottom = 16.dp)
                )
            } else {
                Icon(
                    painter = painterResource(android.R.drawable.ic_menu_camera),
                    contentDescription = "Сделать фото"
                )
            }
        }
        Text(
            text = "${user.firstName} ${user.lastName}"
        )
        Text(
            text = user.email ?: ""
        )
    }
}

private fun createPhotoUri(context: android.content.Context): Uri {
    val directory = File(context.cacheDir, "camera_photos").apply { mkdirs() }
    val file = File.createTempFile("captured_photo_", ".jpg", directory)

    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        file
    )
}