package com.example.jokesapp.presentation.search.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

/**
 * Created by berchoes on 21.01.2022.
 */

@Composable
fun SearchBar(
    text: String,
    onCloseClicked: () -> Unit,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = {
            onTextChange(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        placeholder = {
            Text(
                text = "Search jokes...",
                color = Color.White,
                modifier = Modifier.alpha(ContentAlpha.medium)
            )
        },
        trailingIcon = {
            if (text.isNotEmpty()) {
                IconButton(onClick = {
                    onCloseClicked()
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "CloseIcon"
                    )
                }
            } else {
                IconButton(
                    onClick = {},
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "SearchIcon",
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearchClicked(text) }),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = MaterialTheme.colors.primary.copy(alpha = ContentAlpha.medium),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}