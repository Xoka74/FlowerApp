package com.shurdev.flowerapp.presentation.composables

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NotificationPermissionHandler(
    content: @Composable () -> Unit,
) {

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
        return content()
    }

    val notificationPermissionState =
        rememberPermissionState(Manifest.permission.POST_NOTIFICATIONS)


    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted) {
            // TODO: Handle permission denial
        }
    }

    LaunchedEffect(notificationPermissionState) {
        if (!notificationPermissionState.status.isGranted) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    return content()
}