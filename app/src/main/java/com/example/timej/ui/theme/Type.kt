package com.example.timej.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

val Rubik = FontFamily(
    Font(com.example.timej.R.font.rubik_light, FontWeight.Light),
    Font(com.example.timej.R.font.rubik_regular, FontWeight.Normal),
    Font(com.example.timej.R.font.rubik_medium, FontWeight.Medium),
    Font(com.example.timej.R.font.rubik_semi_bold, FontWeight.SemiBold)
)

val welcome = TextStyle(
    fontFamily = Rubik,
    fontWeight = FontWeight.Normal,
    fontSize = 20.sp,
    lineHeight = 23.7.sp,
)

val sign = TextStyle(
    fontFamily = Rubik,
    fontWeight = FontWeight.Normal,
    fontSize = 15.sp,
    lineHeight = 17.78.sp,
)

val Placeholder = TextStyle(
    fontFamily = Rubik,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 28.sp
)

val Underline = TextStyle(
    fontFamily = Rubik,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 28.sp,
    textDecoration = TextDecoration.Underline
)

val Weekdays = TextStyle(
    fontFamily = Rubik,
    fontWeight = FontWeight.Light,
    fontSize = 8.sp,
    lineHeight = 9.48.sp
)

val WeekdaysEnlarged = TextStyle(
    fontFamily = Rubik,
    fontWeight = FontWeight.Light,
    fontSize = 10.sp,
    lineHeight = 11.85.sp
)

val Shedule = TextStyle(
    fontFamily = Rubik,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    lineHeight = 13.04.sp
)

val SheduleEnlarged = TextStyle(
    fontFamily = Rubik,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    lineHeight = 16.59.sp
)

val SheduleDate = TextStyle(
    fontFamily = Rubik,
    fontWeight = FontWeight.Light,
    fontSize = 11.sp,
    lineHeight = 13.04.sp
)

val Subject = TextStyle(
    fontFamily = Rubik,
    fontWeight = FontWeight.SemiBold,
    fontSize = 12.sp,
    lineHeight = 15.6.sp
)

val TypeLesson = TextStyle(
    fontFamily = Rubik,
    fontWeight = FontWeight.Medium,
    fontSize = 10.sp,
    lineHeight = 13.sp
)

val InfoLesson = TextStyle(
    fontFamily = Rubik,
    fontWeight = FontWeight.Light,
    fontSize = 10.sp,
    lineHeight = 13.sp
)

val TimeLesson = TextStyle(
    fontFamily = Rubik,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp,
    lineHeight = 15.6.sp
)

val TimeWindow = TextStyle(
    fontFamily = Rubik,
    fontWeight = FontWeight.Light,
    fontSize = 14.sp,
    lineHeight = 16.59.sp
)

val NumberLesson = TextStyle(
    fontFamily = Rubik,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    lineHeight = 14.3.sp
)

val Choice = TextStyle(
    fontFamily = Rubik,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    lineHeight = 28.sp
)

val SearchTitle = TextStyle(
    fontFamily = Rubik,
    fontWeight = FontWeight.SemiBold,
    fontSize = 18.sp,
    lineHeight = 21.33.sp
)

val SearchVariant = TextStyle(
    fontFamily = Rubik,
    fontWeight = FontWeight.Light,
    fontSize = 16.sp,
    lineHeight = 28.sp
)

val SearchFaculty = TextStyle(
    fontFamily = Rubik,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    lineHeight = 28.sp
)

val ChoiceTitle = TextStyle(
    fontFamily = Rubik,
    fontWeight = FontWeight.SemiBold,
    fontSize = 24.sp,
    lineHeight = 28.sp
)

val UserName = TextStyle(
    fontFamily = Rubik,
    fontWeight = FontWeight.Medium,
    fontSize = 18.sp,
    lineHeight = 21.33.sp
)

val ProfileBlocks = TextStyle(
    fontFamily = Rubik,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 16.59.sp
)

val NavBarItem = TextStyle(
    fontFamily = Rubik,
    fontWeight = FontWeight.Normal,
    fontSize = 8.sp,
    lineHeight = 9.48.sp
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)