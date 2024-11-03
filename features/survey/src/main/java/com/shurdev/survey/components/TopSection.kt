package com.shurdev.survey.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shurdev.survey.R

@Composable
internal fun TopSection(
    onBackClick: () -> Unit,
    onSkipClick: () -> Unit,
    showBackButton: Boolean,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        if (showBackButton) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = onBackClick,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                    contentDescription = "Back"
                )
            }
        }

        TextButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = onSkipClick,
        ) {
            Text(
                text = stringResource(R.string.skip),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview
@Composable
internal fun TopSectionPreview() {
    TopSection(
        onSkipClick = {},
        onBackClick = {},
        showBackButton = true
    )
}