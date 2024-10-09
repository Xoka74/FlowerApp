package com.shurdev.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.shurdev.domain.models.Flower

@Composable
fun FlowerItem(
    flower: Flower,
    onFlowerClick: (Flower) -> Unit,
) {
    Column(
        Modifier.clickable { onFlowerClick(flower) }
    ) {
        AsyncImage(
            modifier = Modifier.clip(RoundedCornerShape(12.dp)),
            model = flower.imageLink,
            contentDescription = flower.name,
        )

        Spacer(Modifier.height(12.dp))

        Text(flower.name)
    }
}