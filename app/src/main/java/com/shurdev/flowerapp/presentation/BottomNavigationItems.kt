package com.shurdev.flowerapp.presentation

import com.shurdev.flowerapp.R
import com.shurdev.gallery.navigation.GalleryNavGraph
import com.shurdev.my_plants.navigation.MyPlantsNavGraph
import com.shurdev.profile.navigation.ProfileNavGraph
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

    @Serializable
    data object Profile : BottomNavigationItems<ProfileNavGraph>(
        name = "Профиль",
        selectedIconResId = R.drawable.icon_person_filled,
        unSelectedIconResId = R.drawable.icon_person_unfilled,
        route = ProfileNavGraph
    )
}