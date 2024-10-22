package com.shurdev.gallery.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.shurdev.domain.models.Flower
import com.shurdev.gallery.GalleryRoute
import kotlinx.serialization.Serializable

@Serializable
object GalleryRoute

fun NavController.navigateToGalleryScreen() = navigate(GalleryRoute)

fun NavGraphBuilder.galleryScreen(
    onFlowerClick: (Flower) -> Unit,
) {
    composable<GalleryRoute> {
        GalleryRoute(onFlowerClick = onFlowerClick)
    }
}