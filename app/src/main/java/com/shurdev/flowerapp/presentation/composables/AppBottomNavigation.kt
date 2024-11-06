package com.shurdev.flowerapp.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.shurdev.flowerapp.presentation.BottomNavigationItems

@Composable
fun AppBottomNavigation(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItems.MyPlants,
            BottomNavigationItems.Gallery,
        )
    }

    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .size(20.dp)
            .size(20.dp),
    ) {
        val currentDestination = navController.currentBackStackEntryAsState().value?.destination

        bottomNavigationItems.forEach { item ->

            val isSelected =
                currentDestination?.hierarchy?.any {
                    it.hasRoute(item.route::class)
                } ?: false

            NavigationBarItem(
                icon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter =
                            painterResource(
                                id = if (isSelected) item.selectedIconResId else item.unSelectedIconResId,
                            ),
                        contentDescription = item.name,
                    )
                },
                label = {
                    Text(item.name)
                },
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
//                        restoreState = true
                    }
                },
            )
        }
    }
}
