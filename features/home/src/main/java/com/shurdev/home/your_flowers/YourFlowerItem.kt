package com.shurdev.home.your_flowers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shurdev.domain.models.Flower
import com.shurdev.home.FlowerCardBackgroundColor
import com.shurdev.home.FlowerCardContentColor
import com.shurdev.home.R

@Composable
fun YourFlowerItem(
    modifier: Modifier = Modifier,
    flower: Flower,
    onItemClick: (Flower) -> Unit
) {

    Card(
        modifier = modifier
            .width(340.dp)
            .height(221.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = FlowerCardBackgroundColor,
            contentColor = FlowerCardContentColor
        ),
        onClick = { onItemClick(flower) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 17.dp)
                    .padding(top = 12.dp),
                text = flower.name,
                fontSize = 22.sp
            )

            Row(
                modifier = Modifier
                    .padding(12.dp),
                verticalAlignment = Alignment.Top,
            ) {
                AsyncImage(
                    modifier = Modifier
                        .width(173.dp)
                        .height(161.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(flower.imageLink)
                        .placeholder(R.drawable.flower_placeholder_1)
                        .build(),
                    contentDescription = "Your Flower",
                    contentScale = ContentScale.Crop
                )

                YourFlowerItemDescription(
                    flower = flower
                )
            }
        }
    }
}

@Composable
@Preview
fun YourFlowerItemPreview() {
    YourFlowerItem(
        flower = Flower(
            name = "Пахира Акватика",
            description = "Дата следующего полива",
            imageLink = ""
        ),
        onItemClick = {}
    )
}
