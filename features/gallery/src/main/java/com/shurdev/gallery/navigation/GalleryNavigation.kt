package com.shurdev.gallery.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.shurdev.domain.models.Flower
import com.shurdev.gallery.GalleryFlowerDetailsRoute
import com.shurdev.gallery.GalleryRoute
import kotlinx.serialization.Serializable


@Serializable
object GalleryNavGraph

fun NavController.navigateToGalleryGraph() = navigate(GalleryNavGraph)

fun NavGraphBuilder.galleryNavGraph(
    onFlowerClick: (Flower) -> Unit
) {
    navigation<GalleryNavGraph>(
        startDestination = GalleryRoute,
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
data class GalleryFlowerDetails(
    val flowerId: Int
)

fun NavController.navigateToGalleryFlowerDetailsScreen(flowerId: Int) =
    navigate(GalleryFlowerDetails(flowerId))

fun NavGraphBuilder.galleryFlowerDetailsScreen() {

    composable<GalleryFlowerDetails> { backStackEntry ->
        val flowerDetails: GalleryFlowerDetails = backStackEntry.toRoute()

        GalleryFlowerDetailsRoute(
            flowerId = flowerDetails.flowerId
        )
    }
}