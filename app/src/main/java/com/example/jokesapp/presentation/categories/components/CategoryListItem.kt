package com.example.jokesapp.presentation.categories.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by berchoes on 20.01.2022.
 */


@Composable
fun CategoryListItem(
    onClick: (String) -> Unit,
    category: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(category) }
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.Center
    ) {

        Text(text = category.uppercase(), fontSize = 20.sp)

    }
    Divider()
}