package com.shurdev.uiKit.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.shurdev.ui_kit.R

val epilogueFamily = FontFamily(
    Font(R.font.epilogue_light, FontWeight.Light),
    Font(R.font.epilogue_regular, FontWeight.Normal),
    Font(R.font.epilogue_medium, FontWeight.Medium),
    Font(R.font.epilogue_bold, FontWeight.Bold),
    Font(R.font.epilogue_extra_bold, FontWeight.ExtraBold),
    Font(R.font.epilogue_extra_light, FontWeight.ExtraLight),
    Font(R.font.epilogue_black, FontWeight.Black),
    Font(R.font.epilogue_thin, FontWeight.Thin),
    Font(R.font.epilogue_semi_bold, FontWeight.SemiBold),

    Font(R.font.epilogue_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.epilogue_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.epilogue_medium_italic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.epilogue_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.epilogue_extra_bold_italic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.epilogue_extra_light_italic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.epilogue_black_italic, FontWeight.Black, FontStyle.Italic),
    Font(R.font.epilogue_thin_italic, FontWeight.Thin, FontStyle.Italic),
    Font(R.font.epilogue_semi_bold_italic, FontWeight.SemiBold, FontStyle.Italic),
)

val defaultTypography = Typography()

val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = epilogueFamily),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = epilogueFamily),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = epilogueFamily),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = epilogueFamily),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = epilogueFamily),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = epilogueFamily),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = epilogueFamily),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = epilogueFamily),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = epilogueFamily),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = epilogueFamily),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = epilogueFamily),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = epilogueFamily),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = epilogueFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = epilogueFamily),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = epilogueFamily),
)
