package com.shurdev.flowerapp.presentation

import com.shurdev.flowerapp.R
import com.shurdev.gallery.navigation.GalleryRoute
import com.shurdev.my_plants.navigation.MyPlantsRoute
import com.shurdev.profile.navigation.ProfileRoute
import kotlinx.serialization.Serializable

@Serializable
sealed class BottomNavigationItem<T>(
    val name: String,
    val selectedIconResId: Int,
    val unSelectedIconResId: Int,
    val route: T
) {

    @Serializable
    data object MyPlants : BottomNavigationItem<MyPlantsRoute>(
        name = "Мои растения",
        selectedIconResId = R.drawable.icon_home_filled,
        unSelectedIconResId = R.drawable.icon_home_unfilled,
        route = MyPlantsRoute
    )

    @Serializable
    data object Gallery : BottomNavigationItem<GalleryRoute>(
        name = "Галерея",
        selectedIconResId = R.drawable.icon_flower_filled,
        unSelectedIconResId = R.drawable.icon_flower_unfilled,
        route = GalleryRoute
    )

    @Serializable
    data object Profile : BottomNavigationItem<ProfileRoute>(
        name = "Профиль",
        selectedIconResId = R.drawable.icon_person_filled,
        unSelectedIconResId = R.drawable.icon_person_unfilled,
        route = ProfileRoute
    )
}