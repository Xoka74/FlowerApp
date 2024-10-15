package com.shurdev.home.your_flowers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shurdev.domain.models.Flower

@Composable
fun YourFlowerItemDescription(
    modifier: Modifier = Modifier,
    flower: Flower
) {
    Column(
        modifier = modifier
            .padding(start = 12.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = flower.description,
        )
    }
}