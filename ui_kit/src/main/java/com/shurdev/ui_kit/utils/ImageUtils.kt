package com.shurdev.ui_kit.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.request.ImageRequest
import java.io.ByteArrayOutputStream

fun decodeBase64ToBitmap(base64String: String): Bitmap? {
    val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
}

fun ByteArray.toBase64(): String {
    return Base64.encodeToString(this, Base64.NO_WRAP)
}

fun ByteArray.compressImage(quality: Int = 50): ByteArray {
    val bitmap = BitmapFactory.decodeByteArray(this, 0, size)
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream)
    return stream.toByteArray()
}

fun Bitmap.toByteArray(): ByteArray {
    val stream = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.PNG, 100, stream)
    return stream.toByteArray()
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