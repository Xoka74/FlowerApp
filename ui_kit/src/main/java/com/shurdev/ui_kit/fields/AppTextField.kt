package com.shurdev.ui_kit.fields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shurdev.ui_kit.layouts.Center

@Composable
fun AppTextField(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "",
    singleLine: Boolean = false,
    label: String? = null,
    error: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        if (label != null) {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = label,
            )
        }

        TextField(
            modifier = modifier,
            value = text,
            onValueChange = onTextChange,
            singleLine = singleLine,
            placeholder = {
                Text(text = hint)
            },
            leadingIcon = leadingIcon,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp),
        )

        if (error != null) {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = error,
                color = Color.Red
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    Scaffold { padding ->
        Center {
            AppTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding),
                label = "Text field",
                text = "New plant",
                error = "Some error",
                onTextChange = {}
            )
        }
    }
}