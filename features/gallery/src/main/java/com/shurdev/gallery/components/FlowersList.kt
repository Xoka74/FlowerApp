package com.shurdev.gallery.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shurdev.domain.models.Flower
import com.shurdev.gallery.mocks.FLOWERS

@Composable
internal fun FlowersList(
    onFlowerClick: (Flower) -> Unit,
    flowers: List<Flower>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(top = 10.dp),
    ) {
        items(flowers) { flower ->
            FlowerItem(
                flower = flower,
                onFlowerClick = onFlowerClick,
            )

            Spacer(
                modifier = Modifier
                    .height(10.dp),
            )
        }
    }
}

@Preview
@Composable
internal fun FlowersListPreview() {
    FlowersList(
        flowers = FLOWERS,
        onFlowerClick = {},
    )
}
