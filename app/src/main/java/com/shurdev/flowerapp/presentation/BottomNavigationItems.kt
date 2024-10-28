package com.shurdev.flowerapp.presentation

import com.shurdev.flowerapp.R
import com.shurdev.gallery.navigation.GalleryNavGraph
import com.shurdev.my_plants.navigation.MyPlantsNavGraph
import kotlinx.serialization.Serializable

@Serializable
sealed class BottomNavigationItems<T>(
    val name: String,
    val selectedIconResId: Int,
    val unSelectedIconResId: Int,
    val route: T
) {

    @Serializable
    data object MyPlants : BottomNavigationItems<MyPlantsNavGraph>(
        name = "Мои растения",
        selectedIconResId = R.drawable.icon_home_filled,
        unSelectedIconResId = R.drawable.icon_home_unfilled,
        route = MyPlantsNavGraph
    )

    @Serializable
    data object Gallery : BottomNavigationItems<GalleryNavGraph>(
        name = "Галерея",
        selectedIconResId = R.drawable.icon_flower_filled,
        unSelectedIconResId = R.drawable.icon_flower_unfilled,
        route = GalleryNavGraph
    )

    // TODO
    /*
    @Serializable
    data object Settings : BottomNavigationItems<SettingsGraph>{

    }*/
}