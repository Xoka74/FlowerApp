package com.shurdev.recommended_plants.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shurdev.domain.models.plant.Plant

@Composable
internal fun RecommendedPlantsList(
    modifier: Modifier = Modifier,
    plants: List<Plant>,
    onPlantClick: (Plant) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(top = 10.dp),
    ) {
        items(plants) { plant ->
            RecommendedPlantItem(
                plant = plant,
                onPlantClick = onPlantClick
            )

            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
        }
    }
}

@Preview
@Composable
internal fun RecommendedPlantsListPreview() {
    RecommendedPlantsList(
        plants = (1..10).map {
            Plant(
                id = it,
                name = "Name$it",
                description = "Description$it",
                imageLink = ""
            )
        },
        onPlantClick = {}
    )
}