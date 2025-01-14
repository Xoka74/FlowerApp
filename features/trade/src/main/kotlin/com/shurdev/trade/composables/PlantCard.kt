package com.shurdev.trade.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shurdev.domain.models.trade.PlantTrade
import com.shurdev.trade.R
import com.shurdev.ui_kit.utils.getImage

@Composable
fun PlantCard(
    modifier: Modifier = Modifier,
    plant: PlantTrade? = null,
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

        val imageModel = plant.imageData.getImage(
            defaultImageRes = R.drawable.flower_placeholder_1
        )

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            model = imageModel,
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

        /*Spacer(
            modifier = Modifier
                .height(8.dp)
        )

        Text(
            modifier = Modifier
                .padding(horizontal = 8.dp),
            text = plant.description,
            fontWeight = FontWeight.Light
        )*/

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
        plant = PlantTrade(
//            id = 1,
            name = "Роза",
            imageData = null,
//            description = "Потрясающая роза, " +
//                    "ежедневно радующая глаз " +
//                    "своей красотой",
//            imageLink = "https://cdn.britannica.com/84/73184-050-05ED59CB/Sunflower-field-Fargo-North-Dakota.jpg"
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