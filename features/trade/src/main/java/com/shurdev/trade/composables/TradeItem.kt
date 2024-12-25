package com.shurdev.trade.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shurdev.domain.models.plant.Plant
import com.shurdev.domain.models.trade.Trade
import com.shurdev.trade.R

@Composable
fun TradeItem(
    modifier: Modifier = Modifier,
    trade: Trade,
    onItemClick: (Trade) -> Unit = {},
) {

    val imageWidth = 128.dp
    val cornerRadius = 12.dp

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        ),
        onClick = { onItemClick(trade) }
    ) {

        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {
                    AsyncImage(
                        modifier = Modifier
                            .width(imageWidth)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(cornerRadius)),
                        model = trade.plantToGet.imageLink,
                        contentDescription = "",
                        placeholder = painterResource(R.drawable.flower_placeholder_1),
                        fallback = painterResource(R.drawable.flower_placeholder_1),
                        contentScale = ContentScale.Crop,
                    )

                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        text = trade.plantToGet.name,
                    )
                }

                Image(
                    painter = painterResource(R.drawable.icon_exchange),
                    contentDescription = ""
                )

                Column {
                    AsyncImage(
                        modifier = Modifier
                            .width(imageWidth)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(cornerRadius)),
                        model = trade.plantToGive.imageLink,
                        contentDescription = "",
                        placeholder = painterResource(R.drawable.flower_placeholder_1),
                        fallback = painterResource(R.drawable.flower_placeholder_1),
                        contentScale = ContentScale.Crop,
                    )

                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        text = trade.plantToGive.name,
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            val colorGray = Color.Gray

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(20.dp),
                    imageVector = Icons.Outlined.Place,
                    contentDescription = "",
                    tint = colorGray
                )

                Text(
                    text = "Москва",
                    color = colorGray,
                    fontSize = 12.sp
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(20.dp),
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "",
                    tint = colorGray,
                )

                Text(
                    text = trade.authorName,
                    color = colorGray,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TradeItemPreview() {
    TradeItem(
        trade = Trade(
            plantToGet = Plant(
                name = "Роза",
                description = "Колючая",
                imageLink = "https://cdn.britannica.com/84/73184-050-05ED59CB/Sunflower-field-Fargo-North-Dakota.jpg",
            ),
            plantToGive = Plant(
                name = "Тюльпан",
                description = "Большой",
                imageLink = "https://cdn.britannica.com/84/73184-050-05ED59CB/Sunflower-field-Fargo-North-Dakota.jpg"
            ),
            authorName = "Юрий"
        )
    )
}