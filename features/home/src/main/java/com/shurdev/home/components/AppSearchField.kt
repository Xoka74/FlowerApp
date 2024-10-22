package com.shurdev.home.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.shurdev.home.R
import com.shurdev.home.SearchBackgroundColor
import com.shurdev.home.SearchContentColor

@Composable
fun AppSearchField(
    modifier: Modifier = Modifier,
    value: String = "",
    hint: String? = "",
    onValueChange: (String) -> Unit = {},
) {
    TextField(
        modifier = modifier,
        placeholder = {
            hint?.let {
                Text(
                    text = it,
//                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    color = SearchContentColor,
                )
            }
        },
        colors = TextFieldDefaults.colors(
//            focusedContainerColor = MaterialTheme.colorScheme.onTertiaryContainer,
            focusedContainerColor = SearchBackgroundColor,
//            unfocusedContainerColor = MaterialTheme.colorScheme.onTertiaryContainer,
            unfocusedContainerColor = SearchBackgroundColor,
//            disabledContainerColor = MaterialTheme.colorScheme.onTertiaryContainer,
            disabledContainerColor = SearchBackgroundColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        maxLines = 1,
        textStyle = TextStyle(
//            color = MaterialTheme.colorScheme.tertiaryContainer
            color = SearchContentColor
        ),
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
//                tint = MaterialTheme.colorScheme.tertiaryContainer,
                tint = SearchContentColor,
                painter = painterResource(R.drawable.search_gray_24),
                contentDescription = null
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
    )
}