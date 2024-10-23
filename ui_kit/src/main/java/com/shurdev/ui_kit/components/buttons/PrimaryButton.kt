package com.shurdev.ui_kit.components.buttons

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shurdev.ui_kit.theme.Black
import com.shurdev.ui_kit.theme.Green

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = Green,
            contentColor = Black,
        )
    ) {
        Text(text, style = MaterialTheme.typography.titleMedium)
    }
}


@Preview
@Composable
fun PreviewButton() {
    PrimaryButton(
        onClick = {},
        text = ""
    )
}