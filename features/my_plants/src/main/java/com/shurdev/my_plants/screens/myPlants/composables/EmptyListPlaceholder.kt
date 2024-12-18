package com.shurdev.my_plants.screens.myPlants.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shurdev.my_plants.R
import com.shurdev.ui_kit.layouts.Center

@Composable
fun EmptyListPlaceholder() {
    val typography = MaterialTheme.typography

    val emptyPlaceholder = stringResource(R.string.empty_my_plants_placeholder)

    Center {
        Image(
            modifier = Modifier.size(84.dp),
            imageVector = ImageVector.vectorResource(R.drawable.flower_pot),
            contentDescription = null,
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = emptyPlaceholder,
            textAlign = TextAlign.Center,
            style = typography.titleMedium,
        )
    }
}