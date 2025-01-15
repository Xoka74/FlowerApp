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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.shurdev.domain.models.myPlant.MyPlant
import com.shurdev.domain.models.myPlant.PlantWatering
import com.shurdev.my_plants.R
import com.shurdev.ui_kit.utils.getImage
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import com.shurdev.ui_kit.R as uiKitResource

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
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
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
                    style = MaterialTheme.typography.titleLarge,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(Modifier.height(16.dp))

                val watering = plant.plantWatering

                if (watering != null) {

                    val nextWatering = watering.lastWateringTime.format(
                        DateTimeFormatter
                            .ofPattern("dd MMM uuuu")
                            .withLocale(Locale("RU"))
                    )

                    val nextWateringString =
                        stringResource(uiKitResource.string.next_watering, nextWatering)

                    Text(
                        text = nextWateringString,
                        style = MaterialTheme.typography.bodyMedium,
                        overflow = TextOverflow.Ellipsis
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
            imageData = null,
            plantWatering = PlantWatering(
                lastWateringTime = LocalDateTime.now()
            )
        ),
        onItemClick = {}
    )
}
