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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shurdev.trade.R
import com.shurdev.trade.models.MyPlantPresentation
import com.shurdev.ui_kit.utils.getImage

@Composable
fun MyPlantCard(
    modifier: Modifier = Modifier,
    myPlant: MyPlantPresentation? = null,
    onCardClick: () -> Unit = {},
    placeholder: @Composable () -> Unit = {},
) {

    OutlinedCard(
        modifier = modifier,
        onClick = onCardClick,
    ) {

        if (myPlant == null) {
            placeholder()
            return@OutlinedCard
        }

        println("myPlant.imageData ${myPlant.imageData}")

        val imageModel = myPlant.imageData.getImage(
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
            text = myPlant.name,
            fontSize = 22.sp
        )

        Spacer(
            modifier = Modifier
                .height(8.dp)
        )
    }
}