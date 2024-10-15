package com.shurdev.home.your_flowers

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shurdev.domain.models.Flower

@Composable
fun YourFlowers(
    modifier: Modifier = Modifier,
    flowers: List<Flower>,
    contentPadding: PaddingValues = PaddingValues(start = 16.dp),
    onFlowerClick: (Flower) -> Unit
) {
    LazyRow(
        modifier = modifier
            .scrollable(
                state = rememberScrollState(),
                enabled = false,
                orientation = Orientation.Horizontal
            ),
        contentPadding = contentPadding
    ) {
        items(flowers) { flower ->
            YourFlowerItem(
                flower = flower,
                onItemClick = onFlowerClick
            )
            Spacer(modifier = Modifier.width(5.dp))
        }
    }
}

@Composable
@Preview
fun YourFlowersPreview() {
    YourFlowers(
        flowers = (1..10)
            .map {
                Flower(
                    name = "Flower${it}",
                    description = "Description${it}",
                    imageLink = "${it}"
                )
            },
        onFlowerClick = {}
    )
}
