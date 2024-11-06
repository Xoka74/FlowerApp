package com.shurdev.profile.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shurdev.domain.models.user.User

@Composable
fun ProfileHeader(
    user: User,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(user.imageLink)
                .build(),
            contentDescription = "Profile",
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(8.dp))

        Text(user.name)
    }
}