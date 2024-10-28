package com.shurdev.my_plants

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyPlantDetailsRoute() {
    MyPlantDetailsScreen()
}

@Composable
fun MyPlantDetailsScreen() {
    Text(
        text = "My plant details"
    )
}

@Preview
@Composable
fun MyPlantDetailsScreenPreview() {
    MyPlantDetailsScreen()
}
