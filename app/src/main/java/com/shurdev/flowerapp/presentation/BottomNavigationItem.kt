package com.shurdev.flowerapp.presentation

import com.shurdev.flowerapp.R
import com.shurdev.gallery.navigation.GalleryRoute
import com.shurdev.my_plants.navigation.MyPlantsRoute
import com.shurdev.profile.navigation.ProfileRoute
import kotlinx.serialization.Serializable

@Serializable
sealed class BottomNavigationItem<T>(
    val nameResId: Int,
    val selectedIconResId: Int,
    val unSelectedIconResId: Int,
    val route: T,
) {

    @Serializable
    data object MyPlants : BottomNavigationItem<MyPlantsRoute>(
        nameResId = com.shurdev.my_plants.R.string.my_plants,
        selectedIconResId = R.drawable.icon_home_filled,
        unSelectedIconResId = R.drawable.icon_home_unfilled,
        route = MyPlantsRoute
    )

    @Serializable
    data object Gallery : BottomNavigationItem<GalleryRoute>(
        nameResId = com.shurdev.gallery.R.string.gallery,
        selectedIconResId = R.drawable.icon_flower_filled,
        unSelectedIconResId = R.drawable.icon_flower_unfilled,
        route = GalleryRoute
    )

    @Serializable
    data object Profile : BottomNavigationItem<ProfileRoute>(
        nameResId =  com.shurdev.profile.R.string.profile,
        selectedIconResId = R.drawable.icon_person_filled,
        unSelectedIconResId = R.drawable.icon_person_unfilled,
        route = ProfileRoute
    )
}