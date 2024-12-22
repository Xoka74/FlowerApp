package com.shurdev.flowerapp.presentation.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shurdev.flowerapp.presentation.BottomNavigationItem

@Composable
fun AppBottomNavigation(
    items: List<BottomNavigationItem<out Any>>,
    selectedItem: BottomNavigationItem<out Any>,
    onItemClick: (BottomNavigationItem<out Any>) -> Unit,
) {
    NavigationBar {
        items.forEach { item ->
            val isSelected = item == selectedItem

            NavigationBarItem(
                icon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(
                            id = if (isSelected) item.selectedIconResId else item.unSelectedIconResId
                        ),
                        contentDescription = item.name
                    )
                },
                label = {
                    Text(item.name)
                },
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        onItemClick(item)
                    }
                }
            )
        }
    }
}