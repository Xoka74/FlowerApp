package com.shurdev.my_plants.screens.details.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.shurdev.domain.models.myPlant.MyPlant
import com.shurdev.my_plants.R
import com.shurdev.ui_kit.utils.getImage

@Composable
fun MyPlantDetailsContent(
    modifier: Modifier = Modifier,
    myPlant: MyPlant,
) {
    val typography = MaterialTheme.typography

    val imageModel = myPlant.imageData.getImage(
        defaultImageRes = R.drawable.flower_placeholder_1
    )

    Column(modifier) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp)),
            model = imageModel,
            contentDescription = "Your Plant",
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = myPlant.name,
            style = typography.titleMedium,
        )
    }
}

@Preview
@Composable
fun PreviewMyPlantDetailsContent() {
    Scaffold { padding ->
        MyPlantDetailsContent(
            modifier = Modifier.padding(padding),
            myPlant = MyPlant(
                id = 2,
                name = "Новый цветок",
                imageData = null
            )
        )
    }
}