package com.shurdev.ui_kit.errors

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shurdev.ui_kit.theme.FlowerAppTheme

@Composable
fun ErrorView(
    text: String? = null,
) {
    val colors = MaterialTheme.colorScheme

    Card(
        colors = CardDefaults.cardColors(
            contentColor = colors.onErrorContainer,
            containerColor = colors.errorContainer,
        )
    ) {
        Text(
            modifier = Modifier.padding(20.dp),
            text = text ?: "Произошла ошибка",
        )
    }
}

@Preview
@Composable
fun PreviewErrorView() {
    FlowerAppTheme {
        ErrorView()
    }
}