package com.shurdev.gallery.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shurdev.domain.models.Flower
import com.shurdev.gallery.R
import com.shurdev.gallery.mocks.FLOWERS

@Composable
internal fun FlowerItem(
    onFlowerClick: (Flower) -> Unit,
    flower: Flower,
    modifier: Modifier = Modifier,
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val imageSize = (screenWidth * 0.2f).value.dp

    Card(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        onClick = { onFlowerClick(flower) },
        shape = RoundedCornerShape(
            topStart = 12.dp,
            topEnd = 0.dp,
            bottomStart = 12.dp,
            bottomEnd = 0.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,

        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(imageSize)
                    .clip(RoundedCornerShape(12.dp)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(flower.imageLink)
                    .placeholder(R.drawable.flower_placeholder_1)
                    .build(),
                contentDescription = "Your Flower",
                contentScale = ContentScale.Crop,
            )

            val paddingTop = 12.dp

            Column(
                modifier = Modifier
                    .padding(
                        start = 12.dp,
                        top = paddingTop,
                    )
                    .height(imageSize - paddingTop)
                    .background(Color.Transparent),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.Transparent),
                ) {
                    Text(
                        text = flower.name,
                        fontSize = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Text(
                        text = flower.description,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.LightGray),
                )
            }
        }
    }
}

@Preview
@Composable
internal fun FlowerItemPreview() {
    FlowerItem(
        flower = FLOWERS[0],
        onFlowerClick = {},
    )
}
