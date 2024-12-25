package com.shurdev.my_plants.screens.create.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PlantImagePicker(
    onPickTap: () -> Unit,
    onRemoveClick: () -> Unit,
    model: Any?,
) {
    Box(
        contentAlignment = Alignment.BottomEnd,
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp))
                .clickable(onClick = onPickTap),
            model = model,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        IconButton(
            onClick = onRemoveClick,
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null
            )
        }
    }
}