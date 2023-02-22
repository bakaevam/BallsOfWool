package com.game.ballsofwool.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.game.ballsofwool.R

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.opensans_extrabold, weight = FontWeight.Bold)),
        fontWeight = FontWeight(800),
        fontSize = 40.sp,
        lineHeight = 58.sp,
        color = PurpleC171BF,
    ),
    h2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.opensans_extrabold, weight = FontWeight.Bold)),
        fontWeight = FontWeight(800),
        fontSize = 14.sp,
        lineHeight = 19.sp,
        color = PurpleB561B7,
    ),
    button = TextStyle(
        fontFamily = FontFamily(Font(R.font.opensans_extrabold, weight = FontWeight.Bold)),
        fontWeight = FontWeight(800),
        fontSize = 16.sp,
        lineHeight = 19.07.sp,
        color = PurpleB561B7,
    ),
)

val fonts = FontFamily(
    Font(R.font.opensans_bold, weight = FontWeight.Bold)
)