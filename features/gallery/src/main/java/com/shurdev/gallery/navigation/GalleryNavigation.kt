package com.shurdev.gallery.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.shurdev.domain.models.Plant
import com.shurdev.domain.models.PlantId
import com.shurdev.gallery.screens.details.GalleryPlantDetailsRoute
import com.shurdev.gallery.screens.gallery.GalleryRoute
import kotlinx.serialization.Serializable

@Serializable
object GalleryNavGraph

fun NavGraphBuilder.galleryNavGraph(
    onPlantClick: (Plant) -> Unit,
    onPop: () -> Unit,
) {
    navigation<GalleryNavGraph>(
        startDestination = GalleryRoute,
    ) {
        galleryScreen(
            onPlantClick = onPlantClick,
        )

        galleryPlantDetailsScreen(
            onPop = onPop,
        )
    }
}

@Serializable
object GalleryRoute

fun NavGraphBuilder.galleryScreen(onPlantClick: (Plant) -> Unit) {
    composable<GalleryRoute> {
        GalleryRoute(
            onPlantClick = onPlantClick,
        )
    }
}

@Serializable
data class GalleryPlantDetails(
    val plantId: PlantId,
)

fun NavController.navigateToGalleryPlantDetailsScreen(plantId: PlantId) = navigate(GalleryPlantDetails(plantId))

fun NavGraphBuilder.galleryPlantDetailsScreen(onPop: () -> Unit) {
    composable<GalleryPlantDetails> { backStackEntry ->
        val plantDetails: GalleryPlantDetails = backStackEntry.toRoute()

        GalleryPlantDetailsRoute(
            plantId = plantDetails.plantId,
            onPop = onPop,
        )
    }
}
