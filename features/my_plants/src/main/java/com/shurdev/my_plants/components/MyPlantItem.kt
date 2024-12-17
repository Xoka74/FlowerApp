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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shurdev.domain.models.MyPlant
import com.shurdev.my_plants.R
import com.shurdev.ui_kit.theme.PlantCardBackgroundColor
import com.shurdev.ui_kit.theme.PlantCardContentColor
import com.shurdev.ui_kit.utils.getImage

@Composable
fun MyPlantItem(
    modifier: Modifier = Modifier,
    plant: MyPlant,
    onItemClick: (MyPlant) -> Unit,
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

            val imageModel = plant.imageData.getImage(
                defaultImageRes = R.drawable.flower_placeholder_1
            )

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(0.25f)
                    .aspectRatio(1F)
                    .clip(RoundedCornerShape(12.dp)),
                model = imageModel,
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
            }
        }
    }
}

@Composable
@Preview
fun MyPlantItemPreview() {
    MyPlantItem(
        plant = MyPlant(
            id = 1,
            name = "Пахира Акватика",
            imageData = null
        ),
        onItemClick = {}
    )
}
