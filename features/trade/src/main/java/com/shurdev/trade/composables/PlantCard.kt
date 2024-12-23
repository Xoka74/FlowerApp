package com.shurdev.trade.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shurdev.domain.models.Plant

@Composable
fun PlantCard(
    modifier: Modifier = Modifier,
    plant: Plant? = null,
    onCardClick: () -> Unit = {},
    placeholder: @Composable () -> Unit = {},
) {

    OutlinedCard(
        modifier = modifier,
        onClick = onCardClick,
    ) {

        if (plant == null) {
            placeholder()
            return@OutlinedCard
        }

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth(),
            model = ImageRequest
                .Builder(LocalContext.current)
                .placeholder(com.shurdev.trade.R.drawable.flower_placeholder_1)
                .data(plant.imageLink)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )

        Spacer(
            modifier = Modifier
                .height(8.dp)
        )

        Text(
            modifier = Modifier
                .padding(horizontal = 8.dp),
            text = plant.name,
            fontSize = 22.sp
        )

        Spacer(
            modifier = Modifier
                .height(8.dp)
        )

        Text(
            modifier = Modifier
                .padding(horizontal = 8.dp),
            text = plant.description,
            fontWeight = FontWeight.Light
        )

        Spacer(
            modifier = Modifier
                .height(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlantCardPreview() {
    PlantCard(
        plant = Plant(
            id = 1,
            name = "Роза",
            description = "Потрясающая роза, " +
                    "ежедневно радующая глаз " +
                    "своей красотой",
            imageLink = "https://cdn.britannica.com/84/73184-050-05ED59CB/Sunflower-field-Fargo-North-Dakota.jpg"
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PlantCardPlaceholderPreview() {
    PlantCard(
        placeholder = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                text = "Выберите желаемое растение",
                textAlign = TextAlign.Center,
            )
        }
    )
}