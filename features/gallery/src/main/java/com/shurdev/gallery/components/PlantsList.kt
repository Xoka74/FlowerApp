package com.shurdev.gallery.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shurdev.domain.models.Plant
import com.shurdev.gallery.mocks.Plants

@Composable
internal fun PlantsList(
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
            PlantItem(
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
internal fun PlantsListPreview() {
    PlantsList(
        plants = Plants,
        onPlantClick = {}
    )
}