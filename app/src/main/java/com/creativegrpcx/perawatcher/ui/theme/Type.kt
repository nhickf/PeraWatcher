package com.creativegrpcx.perawatcher.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TypographyTokens.DisplayLarge,
    displayMedium = TypographyTokens.DisplayMedium,
    displaySmall = TypographyTokens.DisplaySmall,
    headlineLarge = TypographyTokens.HeadlineLarge,
    headlineMedium = TypographyTokens.HeadlineMedium,
    headlineSmall = TypographyTokens.HeadlineSmall,
    titleLarge = TypographyTokens.TitleLarge,
    titleMedium = TypographyTokens.TitleMedium,
    titleSmall = TypographyTokens.TitleSmall,
    bodyLarge = TypographyTokens.BodyLarge,
    bodyMedium = TypographyTokens.BodyMedium,
    bodySmall = TypographyTokens.BodySmall,
    labelLarge = TypographyTokens.LabelLarge,
    labelMedium = TypographyTokens.LabelMedium,
    labelSmall = TypographyTokens.LabelSmall,
/* Other default text styles to override
button = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W500,
    fontSize = 14.sp
),
caption = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp
)
*/
)


object TypeFaceTokens {
    val Brand = fontFamily
    val Plain = fontFamily
    val WeightBold = FontWeight.Bold
    val WeightMedium = FontWeight.Medium
    val WeightRegular = FontWeight.Normal
}

object TypeScaleTokens {
    val BodyLargeFont = TypeFaceTokens.Plain
    val BodyLargeLineHeight = 24.0.sp
    val BodyLargeSize = 16.sp
    val BodyLargeTracking = 0.5.sp
    val BodyLargeWeight = TypeFaceTokens.WeightRegular
    val BodyMediumFont = TypeFaceTokens.Plain
    val BodyMediumLineHeight = 20.0.sp
    val BodyMediumSize = 14.sp
    val BodyMediumTracking = 0.2.sp
    val BodyMediumWeight = TypeFaceTokens.WeightRegular
    val BodySmallFont = TypeFaceTokens.Plain
    val BodySmallLineHeight = 16.0.sp
    val BodySmallSize = 12.sp
    val BodySmallTracking = 0.4.sp
    val BodySmallWeight = TypeFaceTokens.WeightRegular
    val DisplayLargeFont = TypeFaceTokens.Brand
    val DisplayLargeLineHeight = 64.0.sp
    val DisplayLargeSize = 57.sp
    val DisplayLargeTracking = -0.2.sp
    val DisplayLargeWeight = TypeFaceTokens.WeightRegular
    val DisplayMediumFont = TypeFaceTokens.Brand
    val DisplayMediumLineHeight = 52.0.sp
    val DisplayMediumSize = 45.sp
    val DisplayMediumTracking = 0.0.sp
    val DisplayMediumWeight = TypeFaceTokens.WeightRegular
    val DisplaySmallFont = TypeFaceTokens.Brand
    val DisplaySmallLineHeight = 44.0.sp
    val DisplaySmallSize = 36.sp
    val DisplaySmallTracking = 0.0.sp
    val DisplaySmallWeight = TypeFaceTokens.WeightRegular
    val HeadlineLargeFont = TypeFaceTokens.Brand
    val HeadlineLargeLineHeight = 40.0.sp
    val HeadlineLargeSize = 32.sp
    val HeadlineLargeTracking = 0.0.sp
    val HeadlineLargeWeight = TypeFaceTokens.WeightRegular
    val HeadlineMediumFont = TypeFaceTokens.Brand
    val HeadlineMediumLineHeight = 36.0.sp
    val HeadlineMediumSize = 28.sp
    val HeadlineMediumTracking = 0.0.sp
    val HeadlineMediumWeight = TypeFaceTokens.WeightRegular
    val HeadlineSmallFont = TypeFaceTokens.Brand
    val HeadlineSmallLineHeight = 32.0.sp
    val HeadlineSmallSize = 24.sp
    val HeadlineSmallTracking = 0.0.sp
    val HeadlineSmallWeight = TypeFaceTokens.WeightRegular
    val LabelLargeFont = TypeFaceTokens.Plain
    val LabelLargeLineHeight = 20.0.sp
    val LabelLargeSize = 14.sp
    val LabelLargeTracking = 0.1.sp
    val LabelLargeWeight = TypeFaceTokens.WeightMedium
    val LabelMediumFont = TypeFaceTokens.Plain
    val LabelMediumLineHeight = 16.0.sp
    val LabelMediumSize = 12.sp
    val LabelMediumTracking = 0.5.sp
    val LabelMediumWeight = TypeFaceTokens.WeightMedium
    val LabelSmallFont = TypeFaceTokens.Plain
    val LabelSmallLineHeight = 16.0.sp
    val LabelSmallSize = 11.sp
    val LabelSmallTracking = 0.5.sp
    val LabelSmallWeight = TypeFaceTokens.WeightMedium
    val TitleLargeFont = TypeFaceTokens.Brand
    val TitleLargeLineHeight = 28.0.sp
    val TitleLargeSize = 22.sp
    val TitleLargeTracking = 0.0.sp
    val TitleLargeWeight = TypeFaceTokens.WeightRegular
    val TitleMediumFont = TypeFaceTokens.Plain
    val TitleMediumLineHeight = 24.0.sp
    val TitleMediumSize = 16.sp
    val TitleMediumTracking = 0.2.sp
    val TitleMediumWeight = TypeFaceTokens.WeightMedium
    val TitleSmallFont = TypeFaceTokens.Plain
    val TitleSmallLineHeight = 20.0.sp
    val TitleSmallSize = 14.sp
    val TitleSmallTracking = 0.1.sp
    val TitleSmallWeight = TypeFaceTokens.WeightMedium
}

object TypographyTokens {
    val BodyLarge = TextStyle(
        fontFamily = TypeScaleTokens.BodyLargeFont,
        fontWeight = TypeScaleTokens.BodyLargeWeight,
        fontSize = TypeScaleTokens.BodyLargeSize,
        lineHeight = TypeScaleTokens.BodyLargeLineHeight,
        letterSpacing = TypeScaleTokens.BodyLargeTracking,
    )
    val BodyMedium = TextStyle(
        fontFamily = TypeScaleTokens.BodyMediumFont,
        fontWeight = TypeScaleTokens.BodyMediumWeight,
        fontSize = TypeScaleTokens.BodyMediumSize,
        lineHeight = TypeScaleTokens.BodyMediumLineHeight,
        letterSpacing = TypeScaleTokens.BodyMediumTracking,
    )
    val BodySmall = TextStyle(
        fontFamily = TypeScaleTokens.BodySmallFont,
        fontWeight = TypeScaleTokens.BodySmallWeight,
        fontSize = TypeScaleTokens.BodySmallSize,
        lineHeight = TypeScaleTokens.BodySmallLineHeight,
        letterSpacing = TypeScaleTokens.BodySmallTracking,
    )
    val DisplayLarge = TextStyle(
        fontFamily = TypeScaleTokens.DisplayLargeFont,
        fontWeight = TypeScaleTokens.DisplayLargeWeight,
        fontSize = TypeScaleTokens.DisplayLargeSize,
        lineHeight = TypeScaleTokens.DisplayLargeLineHeight,
        letterSpacing = TypeScaleTokens.DisplayLargeTracking,
    )
    val DisplayMedium = TextStyle(
        fontFamily = TypeScaleTokens.DisplayMediumFont,
        fontWeight = TypeScaleTokens.DisplayMediumWeight,
        fontSize = TypeScaleTokens.DisplayMediumSize,
        lineHeight = TypeScaleTokens.DisplayMediumLineHeight,
        letterSpacing = TypeScaleTokens.DisplayMediumTracking,
    )
    val DisplaySmall = TextStyle(
        fontFamily = TypeScaleTokens.DisplaySmallFont,
        fontWeight = TypeScaleTokens.DisplaySmallWeight,
        fontSize = TypeScaleTokens.DisplaySmallSize,
        lineHeight = TypeScaleTokens.DisplaySmallLineHeight,
        letterSpacing = TypeScaleTokens.DisplaySmallTracking,
    )
    val HeadlineLarge = TextStyle(
        fontFamily = TypeScaleTokens.HeadlineLargeFont,
        fontWeight = TypeScaleTokens.HeadlineLargeWeight,
        fontSize = TypeScaleTokens.HeadlineLargeSize,
        lineHeight = TypeScaleTokens.HeadlineLargeLineHeight,
        letterSpacing = TypeScaleTokens.HeadlineLargeTracking,
    )
    val HeadlineMedium = TextStyle(
        fontFamily = TypeScaleTokens.HeadlineMediumFont,
        fontWeight = TypeScaleTokens.HeadlineMediumWeight,
        fontSize = TypeScaleTokens.HeadlineMediumSize,
        lineHeight = TypeScaleTokens.HeadlineMediumLineHeight,
        letterSpacing = TypeScaleTokens.HeadlineMediumTracking,
    )
    val HeadlineSmall = TextStyle(
        fontFamily = TypeScaleTokens.HeadlineSmallFont,
        fontWeight = TypeScaleTokens.HeadlineSmallWeight,
        fontSize = TypeScaleTokens.HeadlineSmallSize,
        lineHeight = TypeScaleTokens.HeadlineSmallLineHeight,
        letterSpacing = TypeScaleTokens.HeadlineSmallTracking,
    )
    val LabelLarge = TextStyle(
        fontFamily = TypeScaleTokens.LabelLargeFont,
        fontWeight = TypeScaleTokens.LabelLargeWeight,
        fontSize = TypeScaleTokens.LabelLargeSize,
        lineHeight = TypeScaleTokens.LabelLargeLineHeight,
        letterSpacing = TypeScaleTokens.LabelLargeTracking,
    )
    val LabelMedium = TextStyle(
        fontFamily = TypeScaleTokens.LabelMediumFont,
        fontWeight = TypeScaleTokens.LabelMediumWeight,
        fontSize = TypeScaleTokens.LabelMediumSize,
        lineHeight = TypeScaleTokens.LabelMediumLineHeight,
        letterSpacing = TypeScaleTokens.LabelMediumTracking,
    )
    val LabelSmall = TextStyle(
        fontFamily = TypeScaleTokens.LabelSmallFont,
        fontWeight = TypeScaleTokens.LabelSmallWeight,
        fontSize = TypeScaleTokens.LabelSmallSize,
        lineHeight = TypeScaleTokens.LabelSmallLineHeight,
        letterSpacing = TypeScaleTokens.LabelSmallTracking,
    )
    val TitleLarge = TextStyle(
        fontFamily = TypeScaleTokens.TitleLargeFont,
        fontWeight = TypeScaleTokens.TitleLargeWeight,
        fontSize = TypeScaleTokens.TitleLargeSize,
        lineHeight = TypeScaleTokens.TitleLargeLineHeight,
        letterSpacing = TypeScaleTokens.TitleLargeTracking,
    )
    val TitleMedium = TextStyle(
        fontFamily = TypeScaleTokens.TitleMediumFont,
        fontWeight = TypeScaleTokens.TitleMediumWeight,
        fontSize = TypeScaleTokens.TitleMediumSize,
        lineHeight = TypeScaleTokens.TitleMediumLineHeight,
        letterSpacing = TypeScaleTokens.TitleMediumTracking,
    )
    val TitleSmall = TextStyle(
        fontFamily = TypeScaleTokens.TitleSmallFont,
        fontWeight = TypeScaleTokens.TitleSmallWeight,
        fontSize = TypeScaleTokens.TitleSmallSize,
        lineHeight = TypeScaleTokens.TitleSmallLineHeight,
        letterSpacing = TypeScaleTokens.TitleSmallTracking,
    )
}

object CustomTypography {
    val displayLarge = TypographyTokens.DisplayLarge
    val displayMedium = TypographyTokens.DisplayMedium
    val displaySmall = TypographyTokens.DisplaySmall
    val headlineLarge = TypographyTokens.HeadlineLarge
    val headlineMedium = TypographyTokens.HeadlineMedium
    val headlineSmall = TypographyTokens.HeadlineSmall
    val titleLarge = TypographyTokens.TitleLarge
    val titleMedium = TypographyTokens.TitleMedium
    val titleSmall = TypographyTokens.TitleSmall
    val bodyLarge = TypographyTokens.BodyLarge
    val bodyMedium = TypographyTokens.BodyMedium
    val bodySmall = TypographyTokens.BodySmall
    val labelLarge = TypographyTokens.LabelLarge
    val labelMedium = TypographyTokens.LabelMedium
    val labelSmall = TypographyTokens.LabelSmall
}
