package com.shurdev.my_plants.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shurdev.domain.models.myPlant.MyPlant
import com.shurdev.domain.models.myPlant.PlantWatering
import com.shurdev.my_plants.R
import com.shurdev.ui_kit.theme.PlantCardBackgroundColor
import com.shurdev.ui_kit.theme.PlantCardContentColor
import com.shurdev.ui_kit.R as uiKitResource
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(0.25f)
                    .aspectRatio(1F)
                    .clip(RoundedCornerShape(12.dp)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://cdn.britannica.com/84/73184-050-05ED59CB/Sunflower-field-Fargo-North-Dakota.jpg")
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
                    fontSize = 22.sp,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(Modifier.height(16.dp))

                val watering = plant.plantWatering

                if (watering != null) {

                    val nextWatering = watering.lastWateringTime.format(
                        DateTimeFormatter.ofPattern("dd MMM uuuu")
                    )

                    val nextWateringString = stringResource(uiKitResource.string.next_watering, nextWatering)

                    Text(
                        text =nextWateringString ,
                    )
                }
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
            plantWatering = PlantWatering(
                lastWateringTime = LocalDateTime.now()
            )
        ),
        onItemClick = {}
    )
}
