package com.shurdev.gallery

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun GalleryFlowerDetailsRoute(){
    GalleryFlowerDetailsScreen()
}

@Composable
internal fun GalleryFlowerDetailsScreen(){
    Text(
        text = "Flower details"
    )
}

@Preview
@Composable
internal fun GalleryFlowerDetailsScreenPreview(){
    GalleryFlowerDetailsScreen()
}