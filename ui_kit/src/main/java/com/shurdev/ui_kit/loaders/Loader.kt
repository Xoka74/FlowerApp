package com.shurdev.ui_kit.loaders

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.shurdev.ui_kit.components.Center

@Composable
fun Loader() {
    Center {
        CircularProgressIndicator()
    }
}