package com.compose.api.task.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.compose.api.task.R


val White18SemiBold = TextStyle(
    fontWeight = FontWeight.W600,
    fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
    fontSize = 18.sp,
    color = Color.White
)
val White16Medium = TextStyle(
    fontWeight = FontWeight.Medium,
    fontFamily = FontFamily(Font(R.font.poppins_medium)),
    fontSize = 16.sp,
    color = Color.White
)
val White14Regular = TextStyle(
    fontWeight = FontWeight.Normal,
    fontFamily = FontFamily(Font(R.font.poppins_regular)),
    fontSize = 14.sp,
    color = Color.White
)
val Dark14SemiBold = TextStyle(
    fontWeight = FontWeight.W400,
    fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
    fontSize = 14.sp,
    color = Dark
)
val Dark14Medium = TextStyle(
    fontWeight = FontWeight.Medium,
    fontFamily = FontFamily(Font(R.font.poppins_medium)),
    fontSize = 14.sp,
    color = Dark
)
val Dark13Regular = TextStyle(
    fontWeight = FontWeight.Normal,
    fontFamily = FontFamily(Font(R.font.poppins_regular)),
    fontSize = 13.sp,
    color = Dark
)
val Grey13Regular = TextStyle(
    fontWeight = FontWeight.Normal,
    fontFamily = FontFamily(Font(R.font.poppins_regular)),
    fontSize = 13.sp,
    color = Grey
)