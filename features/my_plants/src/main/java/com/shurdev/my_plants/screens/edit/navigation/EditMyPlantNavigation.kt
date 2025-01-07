package com.shurdev.my_plants.screens.edit.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.shurdev.domain.models.myPlant.MyPlantId
import com.shurdev.my_plants.screens.edit.EditMyPlantRoute
import kotlinx.serialization.Serializable

@Serializable
data class EditMyPlantRoute(val id: MyPlantId)

fun NavController.navigateToEditMyPlantScreen(id: MyPlantId) = navigate(EditMyPlantRoute(id))

fun NavGraphBuilder.myPlantEditScreen(
    onBackInvoked: () -> Unit,
) {
    composable<EditMyPlantRoute> {
        EditMyPlantRoute(
            onBackInvoked = onBackInvoked,
        )
    }
}