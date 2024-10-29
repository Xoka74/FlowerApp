package com.shurdev.uiKit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shurdev.uiKit.utils.useDebounce

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    hint: String = "",
    searchText: String = "",
    debounceTimeMillis: Long,
    onSearchTextChange: (String) -> Unit,
) {
    var currentSearchText by remember { mutableStateOf(searchText) }

    currentSearchText.useDebounce(
        delayMillis = debounceTimeMillis,
        coroutineScope = rememberCoroutineScope(),
        onChange = onSearchTextChange
    )

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray, RoundedCornerShape(16.dp)),
        value = currentSearchText,
        onValueChange = {
            currentSearchText = it
        },
        singleLine = true,
        placeholder = {
            Text(text = hint)
        },
        leadingIcon = {
            Icon(Icons.Filled.Search, contentDescription = "Search Icon")
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(12.dp),
    )
}

@Preview
@Composable
fun SearchFieldPreview() {
    SearchField(
        hint = "Поиск",
        searchText = "",
        debounceTimeMillis = 1000L,
        onSearchTextChange = {},
    )
}
