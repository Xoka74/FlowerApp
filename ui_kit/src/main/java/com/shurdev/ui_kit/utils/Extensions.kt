package com.shurdev.ui_kit.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext
import coil.request.ImageRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

@Composable
fun <T> T.useDebounce(
    delayMillis: Long,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    onChange: (T) -> Unit
): T {
    val state by rememberUpdatedState(this)

    DisposableEffect(state) {
        val job = coroutineScope.launch {
            delay(delayMillis)
            onChange(state)
        }
        onDispose {
            job.cancel()
        }
    }
    return state
}

fun Uri?.getByteArray(context: Context): ByteArray {
    val inputStream = this?.let { uri ->
        context
            .contentResolver
            .openInputStream(uri)
    }

    val bitmap = BitmapFactory.decodeStream(inputStream)

    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)

    val byteArray = byteArrayOutputStream.toByteArray()

    inputStream?.close()

    return byteArray
}

@Composable
fun ByteArray?.getImage(
    @DrawableRes defaultImageRes: Int
): ImageRequest {

    return if (this != null) {
        ImageRequest.Builder(LocalContext.current)
            .data(this)
            .placeholder(defaultImageRes)
            .build()
    } else {
        ImageRequest.Builder(LocalContext.current)
            .data(defaultImageRes)
            .build()
    }
}