package com.shurdev.survey.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun BottomSection(
    isQuestionLast: Boolean,
    onNextClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        FloatingActionButton(
            onClick = onNextClick,
            containerColor = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
        ) {
            Icon(
                imageVector = if (isQuestionLast)
                    Icons.Outlined.Done
                else
                    Icons.AutoMirrored.Outlined.KeyboardArrowRight,

                tint = Color.White,
                contentDescription = "Localized description"
            )
        }
    }
}

@Preview
@Composable
internal fun BottomSectionPreview() {
    BottomSection(
        isQuestionLast = false,
        onNextClick = {}
    )
}