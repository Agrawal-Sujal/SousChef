package com.souschef.ui.theme

import androidx.compose.ui.graphics.Color

// Sealed class for batch color groups
sealed class ColorBatch(val colors: List<Color>) {
    // Primary brand colors - Golden yellows
    data object PrimaryYellows : ColorBatch(
        listOf(
            Color(0xFFFDB913), // GoldenYellow
            Color(0xFFF9A825), // AmberGold
            Color(0xFFFFB300), // HoneyYellow
            Color(0xFFFFD54F), // LightGolden
            Color(0xFFF57F17)  // DarkGolden
        )
    )

    // Warm accent colors - Oranges and earth tones
    data object WarmAccents : ColorBatch(
        listOf(
            Color(0xFFFF8A65), // WarmOrange
            Color(0xFFFF6F3C), // TerracottaOrange
            Color(0xFFFFCCBC), // LightPeach
            Color(0xFFBF360C)  // BurntSienna
        )
    )

    // Fresh greens - Vegetables and herbs
    data object FreshGreens : ColorBatch(
        listOf(
            Color(0xFF66BB6A), // FreshGreen
            Color(0xFF4CAF50), // HerbGreen
            Color(0xFFA5D6A7), // LightMint
            Color(0xFF2E7D32)  // DarkForest
        )
    )

    // Neutral UI colors - Grays and whites
    data object Neutrals : ColorBatch(
        listOf(
            Color(0xFF37474F), // CharcoalGray
            Color(0xFF546E7A), // SlateGray
            Color(0xFFECEFF1), // LightGray
            Color(0xFFB0BEC5), // MediumGray
            Color(0xFFFAFAFA), // SoftWhite
            Color(0xFFFFF8E1)  // CreamWhite
        )
    )

    // Food category colors - Recipe types
    data object FoodCategories : ColorBatch(
        listOf(
            Color(0xFFE57373), // MeatRed
            Color(0xFF81C784), // VeggieGreen
            Color(0xFF64B5F6), // DairyBlue
            Color(0xFFA1887F), // BakeryBrown
            Color(0xFF4DB6AC), // SeafoodTeal
            Color(0xFFF06292)  // DessertPink
        )
    )

    companion object {
        fun getAllBatches(): List<ColorBatch> = listOf(
            PrimaryYellows,
            WarmAccents,
            FreshGreens,
            Neutrals,
            FoodCategories
        )

        fun getAllColors(): List<Color> = getAllBatches().flatMap { it.colors }
    }
}

// Primary Colors - Golden/Yellow tones for premium recipe app
val GoldenYellow = Color(0xFFFDB913)
val AmberGold = Color(0xFFF9A825)
val HoneyYellow = Color(0xFFFFB300)
val LightGolden = Color(0xFFFFD54F)
val DarkGolden = Color(0xFFF57F17)

// Secondary Colors - Warm earth tones
val WarmOrange = Color(0xFFFF8A65)
val TerracottaOrange = Color(0xFFFF6F3C)
val LightPeach = Color(0xFFFFCCBC)
val BurntSienna = Color(0xFFBF360C)

// Accent Colors - Fresh greens
val FreshGreen = Color(0xFF66BB6A)
val HerbGreen = Color(0xFF4CAF50)
val LightMint = Color(0xFFA5D6A7)
val DarkForest = Color(0xFF2E7D32)

// Neutral Colors - Professional grays
val CharcoalGray = Color(0xFF37474F)
val SlateGray = Color(0xFF546E7A)
val LightGray = Color(0xFFECEFF1)
val MediumGray = Color(0xFFB0BEC5)
val SoftWhite = Color(0xFFFAFAFA)
val CreamWhite = Color(0xFFFFF8E1)

// Background Colors
val BackgroundLight = Color(0xFFFFFBF5)
val BackgroundDark = Color(0xFF1C1A16)
val SurfaceLight = Color(0xFFFFFFFF)
val SurfaceDark = Color(0xFF2C2A26)

// Status Colors
val ErrorRed = Color(0xFFD32F2F)
val SuccessGreen = Color(0xFF388E3C)
val WarningAmber = Color(0xFFFFA000)
val InfoBlue = Color(0xFF1976D2)

// Food Category Colors
val MeatRed = Color(0xFFE57373)
val VeggieGreen = Color(0xFF81C784)
val DairyBlue = Color(0xFF64B5F6)
val BakeryBrown = Color(0xFFA1887F)
val SeafoodTeal = Color(0xFF4DB6AC)
val DessertPink = Color(0xFFF06292)

// Light Theme Colors
val PrimaryLight = GoldenYellow
val OnPrimaryLight = CharcoalGray
val PrimaryContainerLight = LightGolden
val OnPrimaryContainerLight = DarkGolden

val SecondaryLight = WarmOrange
val OnSecondaryLight = Color.White
val SecondaryContainerLight = LightPeach
val OnSecondaryContainerLight = BurntSienna

val TertiaryLight = FreshGreen
val OnTertiaryLight = Color.White
val TertiaryContainerLight = LightMint
val OnTertiaryContainerLight = DarkForest

// Dark Theme Colors
val PrimaryDark = AmberGold
val OnPrimaryDark = CharcoalGray
val PrimaryContainerDark = DarkGolden
val OnPrimaryContainerDark = LightGolden

val SecondaryDark = TerracottaOrange
val OnSecondaryDark = Color.White
val SecondaryContainerDark = BurntSienna
val OnSecondaryContainerDark = LightPeach

val TertiaryDark = HerbGreen
val OnTertiaryDark = Color.White
val TertiaryContainerDark = DarkForest
val OnTertiaryContainerDark = LightMint
