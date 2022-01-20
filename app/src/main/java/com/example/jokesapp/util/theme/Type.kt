package com.example.jokesapp.util.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.jokesapp.R

// Set of Material typography styles to start with
val fonts = FontFamily(
    Font(R.font.poppins_italic, weight = FontWeight.Normal,style = FontStyle.Italic),
    Font(R.font.poppins_bold,weight = FontWeight.Bold),
    Font(R.font.poppins_regular,weight = FontWeight.Normal)
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    h1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    body2 = TextStyle(
        fontFamily = fonts,
        fontStyle = FontStyle.Italic,
        fontSize = 14.sp
    )
)