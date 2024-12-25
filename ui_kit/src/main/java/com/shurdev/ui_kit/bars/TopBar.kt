package com.shurdev.ui_kit.bars

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.shurdev.ui_kit.actions.SettingsAction

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    actions: @Composable RowScope.() -> Unit = {},
) {
    Row(
        modifier = modifier,
    ) {
        Text(
            modifier = Modifier,
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.weight(1f))

        actions()
    }
}

@Preview
@Composable
fun TopBarPreview() {
    TopBar(
        title = "asdasd",
        actions = {
            SettingsAction(
                onClick = {}
            )
        }
    )
}