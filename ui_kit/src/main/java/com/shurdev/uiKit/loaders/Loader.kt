package com.shurdev.uiKit.loaders

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.shurdev.uiKit.components.Center

@Composable
fun Loader(modifier: Modifier = Modifier) {
    Center(modifier) {
        CircularProgressIndicator()
    }
}
