package com.shurdev.my_plants.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shurdev.domain.models.Plant
import com.shurdev.my_plants.R
import com.shurdev.ui_kit.theme.PlantCardBackgroundColor
import com.shurdev.ui_kit.theme.PlantCardContentColor

@Composable
fun MyPlantItem(
    modifier: Modifier = Modifier,
    plant: Plant,
    onItemClick: (Plant) -> Unit,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = PlantCardBackgroundColor,
            contentColor = PlantCardContentColor
        ),
        onClick = { onItemClick(plant) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.Top,
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(0.25f)
                    .aspectRatio(1F)
                    .clip(RoundedCornerShape(12.dp)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(plant.imageLink)
                    .placeholder(R.drawable.flower_placeholder_1)
                    .build(),
                contentDescription = "Your Plant",
                contentScale = ContentScale.Crop
            )

            Column(
                Modifier
                    .padding(horizontal = 17.dp)
                    .padding(top = 12.dp)
            ) {
                Text(
                    text = plant.name,
                    fontSize = 22.sp
                )

                Text(
                    text = plant.description,
                )
            }
        }
    }
}

@Composable
@Preview
fun MyPlantItemPreview() {
    MyPlantItem(
        plant = Plant(
            name = "Пахира Акватика",
            description = "Дата следующего полива",
            imageLink = ""
        ),
        onItemClick = {}
    )
}
