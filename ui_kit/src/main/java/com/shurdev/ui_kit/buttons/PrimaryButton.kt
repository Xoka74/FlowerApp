package com.shurdev.ui_kit.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shurdev.ui_kit.theme.Black
import com.shurdev.ui_kit.theme.Green

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = Green,
            contentColor = Black,
        )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier=Modifier.size(24.dp),
                color = Color.White,
                strokeWidth = 2.dp,
            )
        } else {
            Text(text, style = MaterialTheme.typography.titleMedium)
        }
    }
}


@Preview
@Composable
fun PreviewButton() {
    Column {
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            text = "Some button"
        )

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            text = "Some button",
            isLoading = true,
        )

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            text = "Some button",
            enabled = false,
        )
    }
}