package com.shurdev.gallery.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shurdev.domain.models.Flower
import com.shurdev.gallery.GalleryRoute
import kotlinx.serialization.Serializable
import com.shurdev.gallery.GalleryFlowerDetailsRoute


@Serializable
object GalleryNavGraph

fun NavController.navigateToGalleryGraph() = navigate(GalleryNavGraph)

fun NavGraphBuilder.galleryNavGraph(
    onFlowerClick: (Flower) -> Unit
) {
    navigation<GalleryNavGraph>(
        startDestination = GalleryRoute
    ) {
        galleryScreen(
            onFlowerClick = onFlowerClick,
        )

        galleryFlowerDetailsScreen()
    }
}

@Serializable
object GalleryRoute

fun NavController.navigateToGalleryScreen() = navigate(GalleryRoute)

fun NavGraphBuilder.galleryScreen(
    onFlowerClick: (Flower) -> Unit,
) {
    composable<GalleryRoute> {
        GalleryRoute(
            onFlowerClick = onFlowerClick,
        )
    }
}


@Serializable
object GalleryFlowerDetailsRoute

fun NavController.navigateToGalleryFlowerDetailsScreen() = navigate(GalleryFlowerDetailsRoute)

fun NavGraphBuilder.galleryFlowerDetailsScreen() {
    composable<GalleryFlowerDetailsRoute> {
        GalleryFlowerDetailsRoute()
    }
}