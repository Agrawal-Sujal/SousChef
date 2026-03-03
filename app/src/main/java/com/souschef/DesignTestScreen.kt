package com.souschef

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.LocalDining
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.souschef.ui.theme.AppColors
import com.souschef.ui.theme.CharcoalDeep
import com.souschef.ui.theme.CharcoalLight
import com.souschef.ui.theme.CharcoalMedium
import com.souschef.ui.theme.CustomShapes
import com.souschef.ui.theme.DeepBurgundy
import com.souschef.ui.theme.DeepOlive
import com.souschef.ui.theme.DividerLight
import com.souschef.ui.theme.ErrorLight
import com.souschef.ui.theme.GlassDark
import com.souschef.ui.theme.GlassWhite
import com.souschef.ui.theme.GoldLight
import com.souschef.ui.theme.GoldRich
import com.souschef.ui.theme.GoldVibrant
import com.souschef.ui.theme.GradientGold
import com.souschef.ui.theme.GradientImageOverlay
import com.souschef.ui.theme.Gray100
import com.souschef.ui.theme.Gray200
import com.souschef.ui.theme.Gray300
import com.souschef.ui.theme.Gray400
import com.souschef.ui.theme.Gray500
import com.souschef.ui.theme.InfoLight
import com.souschef.ui.theme.SageGreen
import com.souschef.ui.theme.SuccessLight
import com.souschef.ui.theme.TerracottaVibrant
import com.souschef.ui.theme.TextSecondaryLight
import com.souschef.ui.theme.TextTertiaryLight
import com.souschef.ui.theme.WarningLight
import com.souschef.ui.theme.BorderLight
import com.souschef.ui.theme.ChampagneGold

// Alias for GoldMuted - now points to the new vibrant gold
private val GoldMuted = GoldVibrant
// Alias for TerracottaMuted - now points to the new vibrant terracotta
private val TerracottaMuted = TerracottaVibrant

/**
 * Design Test Screen
 *
 * A comprehensive demo screen showcasing all UI elements from the design guidelines.
 * This is intended for testing and validating the design system implementation.
 *
 * Sections included:
 * - Typography (all text styles)
 * - Colors (primary, secondary, accent)
 * - Buttons (primary, secondary, tertiary)
 * - Text Fields (all variants)
 * - Cards (standard, glass, image)
 * - Chips & Tags
 * - Lists
 * - Icons & FABs
 * - Switches & Toggles
 * - Dialogs
 * - Semantic Colors (success, error, warning, info)
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DesignTestScreen(
    onNavigateBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top App Bar
        TopAppBar(
            title = {
                Text(
                    text = "Design System",
                    style = MaterialTheme.typography.headlineMedium
                )
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // ==========================================
            // SECTION: Premium Glassmorphism Showcase
            // ==========================================
            SectionHeader(title = "✨ Premium Glassmorphism")

            PremiumGlassmorphismSection()

            // ==========================================
            // SECTION: Typography
            // ==========================================
            SectionHeader(title = "Typography")

            TypographySection()

            // ==========================================
            // SECTION: Colors
            // ==========================================
            SectionHeader(title = "Colors")

            ColorPaletteSection()

            // ==========================================
            // SECTION: Buttons
            // ==========================================
            SectionHeader(title = "Buttons")

            ButtonsSection()

            // ==========================================
            // SECTION: Text Fields
            // ==========================================
            SectionHeader(title = "Text Fields")

            TextFieldsSection()

            // ==========================================
            // SECTION: Cards
            // ==========================================
            SectionHeader(title = "Cards")

            CardsSection()

            // ==========================================
            // SECTION: Chips & Tags
            // ==========================================
            SectionHeader(title = "Chips & Tags")

            ChipsSection()

            // ==========================================
            // SECTION: Lists
            // ==========================================
            SectionHeader(title = "Lists")

            ListsSection()

            // ==========================================
            // SECTION: Icons & FABs
            // ==========================================
            SectionHeader(title = "Icons & FABs")

            IconsAndFabsSection()

            // ==========================================
            // SECTION: Switches & Toggles
            // ==========================================
            SectionHeader(title = "Switches & Toggles")

            SwitchesSection()

            // ==========================================
            // SECTION: Semantic Colors
            // ==========================================
            SectionHeader(title = "Semantic Colors")

            SemanticColorsSection()

            // ==========================================
            // SECTION: Dialogs
            // ==========================================
            SectionHeader(title = "Dialogs")

            Button(
                onClick = { showDialog = true },
                colors = ButtonDefaults.buttonColors(containerColor = GoldMuted),
                shape = MaterialTheme.shapes.small
            ) {
                Text("Show Dialog")
            }

            // ==========================================
            // SECTION: Dividers
            // ==========================================
            SectionHeader(title = "Dividers")

            DividerSection()

            // ==========================================
            // SECTION: Spacing Demo
            // ==========================================
            SectionHeader(title = "Spacing Scale")

            SpacingSection()

            Spacer(modifier = Modifier.height(64.dp))
        }
    }

    // Dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            shape = RoundedCornerShape(16.dp),
            title = {
                Text(
                    text = "Confirm Reservation",
                    style = MaterialTheme.typography.headlineSmall
                )
            },
            text = {
                Text(
                    text = "Reserve table for 2 at 7:00 PM? This action can be modified later in your reservations.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextSecondaryLight
                )
            },
            confirmButton = {
                Button(
                    onClick = { showDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = GoldMuted),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(
                        text = "Cancel",
                        color = TextSecondaryLight
                    )
                }
            }
        )
    }
}

// ==========================================
// SECTION COMPONENTS
// ==========================================

@Composable
private fun SectionHeader(title: String) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Composable
private fun SubsectionLabel(label: String) {
    Text(
        text = label,
        style = MaterialTheme.typography.labelMedium,
        color = AppColors.textTertiary(),
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

// ==========================================
// PREMIUM GLASSMORPHISM SECTION
// ==========================================

@Composable
private fun PremiumGlassmorphismSection() {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {

        // 1. Hero Card with Gradient Background
        SubsectionLabel("Hero Card with Gradient")
        PremiumHeroCard()

        // 2. Featured Restaurant Glass Card
        SubsectionLabel("Featured Restaurant Card")
        FeaturedRestaurantGlassCard()

        // 3. Premium Reservation Card
        SubsectionLabel("Reservation Confirmation")
        ReservationGlassCard()

        // 4. Chef's Special Card
        SubsectionLabel("Chef's Special")
        ChefsSpecialCard()

        // 5. Premium Stats Card
        SubsectionLabel("Premium Stats Display")
        PremiumStatsCard()

        // 6. Luxury Menu Item Card
        SubsectionLabel("Luxury Menu Item")
        LuxuryMenuItemCard()

        // 7. Glass Navigation Bar Preview
        SubsectionLabel("Glass Bottom Bar")
        GlassBottomBarPreview()

        // 8. Premium Badge Collection
        SubsectionLabel("Premium Badges")
        PremiumBadgesRow()

        // 9. Floating Glass Action Card
        SubsectionLabel("Floating Action Card")
        FloatingGlassActionCard()
    }
}

@Composable
private fun PremiumHeroCard() {
    val goldColor = AppColors.gold()
    val heroBackground = AppColors.heroBackground()
    val heroBackgroundAlt = AppColors.heroBackgroundAlt()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .clip(RoundedCornerShape(24.dp))
    ) {
        // Background gradient
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            heroBackground,
                            heroBackgroundAlt,
                            heroBackground
                        )
                    )
                )
        )

        // Decorative gold circles
        Box(
            modifier = Modifier
                .size(200.dp)
                .offset(x = (-50).dp, y = (-50).dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            goldColor.copy(alpha = 0.3f),
                            Color.Transparent
                        )
                    ),
                    CircleShape
                )
        )

        Box(
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 40.dp, y = 40.dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            goldColor.copy(alpha = 0.2f),
                            Color.Transparent
                        )
                    ),
                    CircleShape
                )
        )

        // Glass overlay card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.Center)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White.copy(alpha = 0.1f))
                .border(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(24.dp)
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = goldColor,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "MICHELIN GUIDE",
                        style = MaterialTheme.typography.labelMedium,
                        color = goldColor,
                        letterSpacing = 2.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Experience\nCulinary Excellence",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 40.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Discover world-class dining experiences curated just for you",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.7f)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = goldColor,
                        contentColor = heroBackground
                    ),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
                ) {
                    Text(
                        text = "Explore Now",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun FeaturedRestaurantGlassCard() {
    val isDark = isSystemInDarkTheme()
    val goldColor = AppColors.gold()
    val heroBackground = AppColors.heroBackground()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        // Background with warm gradient (simulating food photo)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF8B4513),
                            Color(0xFFD2691E),
                            Color(0xFFA0522D)
                        )
                    )
                )
        )

        // Gradient overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.7f)
                        )
                    )
                )
        )

        // Glass card overlay at bottom
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(12.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White.copy(alpha = 0.15f))
                .border(
                    width = 0.5.dp,
                    color = Color.White.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Le Petit Château",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Outlined.Verified,
                            contentDescription = "Verified",
                            tint = goldColor,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.Place,
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.7f),
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Paris, France • Fine Dining",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }
                }

                // Rating badge
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(goldColor)
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = heroBackground,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "4.9",
                            style = MaterialTheme.typography.labelMedium,
                            color = heroBackground,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // Premium badge
        Box(
            modifier = Modifier
                .padding(12.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White.copy(alpha = 0.2f))
                .border(
                    width = 0.5.dp,
                    color = goldColor.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 10.dp, vertical = 6.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = goldColor,
                    modifier = Modifier.size(12.dp)
                )
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = goldColor,
                    modifier = Modifier.size(12.dp)
                )
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = goldColor,
                    modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "3 Stars",
                    style = MaterialTheme.typography.labelSmall,
                    color = goldColor,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun ReservationGlassCard() {
    val isDark = isSystemInDarkTheme()
    val goldColor = AppColors.gold()
    val textPrimary = AppColors.textPrimary()
    val textTertiary = AppColors.textTertiary()
    val heroBackground = AppColors.heroBackground()
    val cardBackground = if (isDark) CharcoalMedium else Color.White
    val goldBackground = AppColors.goldBackground()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        goldColor.copy(alpha = 0.1f),
                        goldBackground,
                        goldColor.copy(alpha = 0.1f)
                    )
                )
            )
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        goldColor.copy(alpha = 0.5f),
                        goldColor.copy(alpha = 0.2f),
                        goldColor.copy(alpha = 0.5f)
                    )
                ),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(24.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Reservation Confirmed",
                        style = MaterialTheme.typography.titleMedium,
                        color = textPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Le Petit Château",
                        style = MaterialTheme.typography.headlineSmall,
                        color = GoldRich,
                        fontWeight = FontWeight.Bold
                    )
                }

                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(colors = GradientGold)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = heroBackground,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Divider with dots
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(30) {
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .background(goldColor.copy(alpha = 0.3f), CircleShape)
                    )
                    if (it < 29) Spacer(modifier = Modifier.width(6.dp))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ReservationDetail(
                    icon = Icons.Outlined.AccessTime,
                    label = "Time",
                    value = "7:30 PM",
                    goldColor = goldColor,
                    textPrimary = textPrimary,
                    textTertiary = textTertiary
                )
                ReservationDetail(
                    icon = Icons.Outlined.Person,
                    label = "Guests",
                    value = "2 People",
                    goldColor = goldColor,
                    textPrimary = textPrimary,
                    textTertiary = textTertiary
                )
                ReservationDetail(
                    icon = Icons.Outlined.LocalDining,
                    label = "Table",
                    value = "Window",
                    goldColor = goldColor,
                    textPrimary = textPrimary,
                    textTertiary = textTertiary
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Tonight, February 22",
                style = MaterialTheme.typography.titleMedium,
                color = textPrimary,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
private fun ReservationDetail(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String,
    goldColor: Color,
    textPrimary: Color,
    textTertiary: Color
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = goldColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = textTertiary
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.labelLarge,
            color = textPrimary,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun ChefsSpecialCard() {
    val isDark = isSystemInDarkTheme()
    val goldColor = AppColors.gold()
    val heroBackground = AppColors.heroBackground()
    val accentTerracotta = AppColors.accentTerracotta()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
    ) {
        // Dark elegant background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(heroBackground)
                .padding(20.dp)
        ) {
            Row {
                // Left side - Image placeholder
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF654321),
                                    Color(0xFF8B4513)
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Restaurant,
                        contentDescription = null,
                        tint = goldColor,
                        modifier = Modifier.size(40.dp)
                    )

                    // Glass overlay
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.3f)
                                    )
                                )
                            )
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "CHEF'S SPECIAL",
                            style = MaterialTheme.typography.labelSmall,
                            color = goldColor,
                            letterSpacing = 1.5.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(accentTerracotta.copy(alpha = 0.2f))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "LIMITED",
                                style = MaterialTheme.typography.labelSmall,
                                color = accentTerracotta,
                                fontSize = 9.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Truffle Wagyu\nRisotto",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 26.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "A5 Japanese wagyu with black truffle and aged parmesan",
                        style = MaterialTheme.typography.bodySmall,
                        color = Gray400,
                        maxLines = 2
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "$128",
                            style = MaterialTheme.typography.headlineSmall,
                            color = GoldLight,
                            fontWeight = FontWeight.Bold
                        )

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(goldColor)
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = "Order",
                                style = MaterialTheme.typography.labelMedium,
                                color = heroBackground,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PremiumStatsCard() {
    val isDark = isSystemInDarkTheme()
    val goldColor = AppColors.gold()
    val textPrimary = AppColors.textPrimary()
    val textTertiary = AppColors.textTertiary()
    val borderColor = AppColors.border()
    val cardBackground = AppColors.cardBackground()
    val goldBackgroundColor = AppColors.goldBackground()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        cardBackground,
                        goldBackgroundColor,
                        cardBackground
                    )
                )
            )
            .border(
                width = 0.5.dp,
                color = borderColor,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatItem(
                value = "127",
                label = "Reservations",
                icon = Icons.Outlined.LocalDining,
                goldColor = goldColor,
                textPrimary = textPrimary,
                textTertiary = textTertiary
            )

            // Divider
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(60.dp)
                    .background(borderColor)
            )

            StatItem(
                value = "4.9",
                label = "Avg Rating",
                icon = Icons.Default.Star,
                goldColor = goldColor,
                textPrimary = textPrimary,
                textTertiary = textTertiary
            )

            // Divider
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(60.dp)
                    .background(borderColor)
            )

            StatItem(
                value = "23",
                label = "Favorites",
                icon = Icons.Default.Favorite,
                goldColor = goldColor,
                textPrimary = textPrimary,
                textTertiary = textTertiary
            )
        }
    }
}

@Composable
private fun StatItem(
    value: String,
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    goldColor: Color,
    textPrimary: Color,
    textTertiary: Color
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = goldColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall,
            color = textPrimary,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = textTertiary
        )
    }
}

@Composable
private fun LuxuryMenuItemCard() {
    val isDark = isSystemInDarkTheme()
    val goldColor = AppColors.gold()
    val textPrimary = AppColors.textPrimary()
    val textSecondary = AppColors.textSecondary()
    val heroBackground = AppColors.heroBackground()
    val cardBackground = AppColors.cardBackground()
    val accentGreen = AppColors.accentGreen()
    val accentBurgundy = AppColors.accentBurgundy()

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column {
            // Image area with glass overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFD4A574),
                                Color(0xFFE8C39E),
                                Color(0xFFD4A574)
                            )
                        )
                    )
            ) {
                // Food icon placeholder
                Icon(
                    imageVector = Icons.Outlined.Restaurant,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.3f),
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.Center)
                )

                // Gradient overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.3f)
                                ),
                                startY = 100f
                            )
                        )
                )

                // Glass price tag
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isDark) GlassDark else GlassWhite.copy(alpha = 0.9f))
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "$89",
                        style = MaterialTheme.typography.titleMedium,
                        color = if (isDark) Color.White else heroBackground,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Favorite button
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp)
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(if (isDark) GlassDark else GlassWhite.copy(alpha = 0.8f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "Add to favorites",
                        tint = if (isDark) Color.White else heroBackground,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // Content
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Lobster Thermidor",
                        style = MaterialTheme.typography.titleLarge,
                        color = textPrimary,
                        fontWeight = FontWeight.SemiBold
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = goldColor,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "4.8",
                            style = MaterialTheme.typography.labelLarge,
                            color = textPrimary,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Classic French preparation with brandy cream sauce, gruyère, and fresh herbs. Served with seasonal vegetables.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = textSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SmallTag(text = "Seafood", color = accentGreen)
                    SmallTag(text = "French", color = accentBurgundy)
                    SmallTag(text = "Popular", color = goldColor)
                }
            }
        }
    }
}

@Composable
private fun SmallTag(text: String, color: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(color.copy(alpha = 0.1f))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = color,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun GlassBottomBarPreview() {
    val isDark = isSystemInDarkTheme()
    val goldColor = AppColors.gold()
    val glassBackground = AppColors.glassBackground()
    val outerBackground = if (isDark) listOf(CharcoalMedium, CharcoalLight, CharcoalMedium)
                          else listOf(Gray200, Gray100, Gray200)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.linearGradient(colors = outerBackground)
            )
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(glassBackground)
                .border(
                    width = 0.5.dp,
                    color = if (isDark) Color.White.copy(alpha = 0.1f) else Color.White.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(horizontal = 8.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(icon = Icons.Outlined.Restaurant, label = "Home", isSelected = true, goldColor = goldColor, isDark = isDark)
            BottomNavItem(icon = Icons.Outlined.Search, label = "Search", isSelected = false, goldColor = goldColor, isDark = isDark)
            BottomNavItem(icon = Icons.Outlined.FavoriteBorder, label = "Favorites", isSelected = false, goldColor = goldColor, isDark = isDark)
            BottomNavItem(icon = Icons.Outlined.Person, label = "Profile", isSelected = false, goldColor = goldColor, isDark = isDark)
        }
    }
}

@Composable
private fun BottomNavItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    isSelected: Boolean,
    goldColor: Color,
    isDark: Boolean
) {
    val unselectedColor = if (isDark) Gray400 else Gray500

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(if (isSelected) goldColor.copy(alpha = 0.15f) else Color.Transparent)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (isSelected) goldColor else unselectedColor,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = if (isSelected) goldColor else unselectedColor,
            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PremiumBadgesRow() {
    val isDark = isSystemInDarkTheme()
    val goldColor = AppColors.gold()
    val heroBackground = AppColors.heroBackground()
    val accentGreen = AppColors.accentGreen()
    val accentTerracotta = AppColors.accentTerracotta()
    val accentBurgundy = AppColors.accentBurgundy()

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        PremiumBadge(
            text = "Michelin Star",
            backgroundColor = Brush.linearGradient(colors = GradientGold),
            textColor = heroBackground
        )
        PremiumBadge(
            text = "Top Rated",
            backgroundColor = Brush.linearGradient(
                colors = listOf(heroBackground, CharcoalMedium)
            ),
            textColor = goldColor
        )
        PremiumBadge(
            text = "New",
            backgroundColor = Brush.linearGradient(
                colors = listOf(accentGreen, DeepOlive)
            ),
            textColor = Color.White
        )
        PremiumBadge(
            text = "Featured",
            backgroundColor = Brush.linearGradient(
                colors = listOf(accentTerracotta, accentBurgundy)
            ),
            textColor = Color.White
        )
    }
}

@Composable
private fun PremiumBadge(
    text: String,
    backgroundColor: Brush,
    textColor: Color
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = textColor,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun FloatingGlassActionCard() {
    val isDark = isSystemInDarkTheme()
    val goldColor = AppColors.gold()
    val heroBackground = AppColors.heroBackground()
    val textSecondary = if (isDark) Gray400 else Gray500

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        heroBackground,
                        CharcoalMedium
                    )
                )
            )
    ) {
        // Decorative elements
        Box(
            modifier = Modifier
                .size(100.dp)
                .offset(x = (-20).dp, y = (-20).dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            goldColor.copy(alpha = 0.15f),
                            Color.Transparent
                        )
                    ),
                    CircleShape
                )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Ready to dine?",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Find your perfect restaurant tonight",
                    style = MaterialTheme.typography.bodySmall,
                    color = textSecondary
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Glass button
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.linearGradient(colors = GradientGold)
                    )
                    .padding(horizontal = 20.dp, vertical = 14.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Book Now",
                        style = MaterialTheme.typography.labelLarge,
                        color = heroBackground,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = heroBackground,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

// ==========================================
// TYPOGRAPHY SECTION
// ==========================================

@Composable
private fun TypographySection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Display
            SubsectionLabel("Display Styles (Serif)")
            Text(
                text = "Display Large",
                style = MaterialTheme.typography.displayLarge.copy(fontSize = MaterialTheme.typography.displaySmall.fontSize)
            )
            Text(
                text = "Display Medium",
                style = MaterialTheme.typography.displayMedium.copy(fontSize = MaterialTheme.typography.headlineLarge.fontSize)
            )
            Text(
                text = "Display Small",
                style = MaterialTheme.typography.displaySmall.copy(fontSize = MaterialTheme.typography.headlineMedium.fontSize)
            )

            HorizontalDivider(color = DividerLight)

            // Headline
            SubsectionLabel("Headline Styles (Serif)")
            Text(
                text = "Headline Large",
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = "Headline Medium",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Headline Small",
                style = MaterialTheme.typography.headlineSmall
            )

            HorizontalDivider(color = DividerLight)

            // Title
            SubsectionLabel("Title Styles")
            Text(
                text = "Title Large (Serif)",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Title Medium (Sans)",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Title Small (Sans)",
                style = MaterialTheme.typography.titleSmall
            )

            HorizontalDivider(color = DividerLight)

            // Body
            SubsectionLabel("Body Styles (Sans)")
            Text(
                text = "Body Large - The quick brown fox jumps over the lazy dog.",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Body Medium - The quick brown fox jumps over the lazy dog.",
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondaryLight
            )
            Text(
                text = "Body Small - The quick brown fox jumps over the lazy dog.",
                style = MaterialTheme.typography.bodySmall,
                color = TextTertiaryLight
            )

            HorizontalDivider(color = DividerLight)

            // Label
            SubsectionLabel("Label Styles (Sans)")
            Text(
                text = "Label Large - Buttons",
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = "Label Medium - Tags, Chips",
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = "Label Small - Metadata",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

// ==========================================
// COLOR PALETTE SECTION
// ==========================================

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ColorPaletteSection() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        // Primary Colors
        SubsectionLabel("Primary - Gold Accents")
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ColorSwatch(color = GoldMuted, name = "Gold Muted")
            ColorSwatch(color = AppColors.goldBackground(), name = "Gold Background")
        }

        // Accent Colors
        SubsectionLabel("Accent Colors")
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ColorSwatch(color = SageGreen, name = "Sage Green")
            ColorSwatch(color = DeepOlive, name = "Deep Olive")
            ColorSwatch(color = TerracottaMuted, name = "Terracotta")
            ColorSwatch(color = DeepBurgundy, name = "Burgundy")
        }

        // Semantic Colors
        SubsectionLabel("Semantic Colors")
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ColorSwatch(color = SuccessLight, name = "Success")
            ColorSwatch(color = ErrorLight, name = "Error")
            ColorSwatch(color = WarningLight, name = "Warning")
            ColorSwatch(color = InfoLight, name = "Info")
        }
    }
}

@Composable
private fun ColorSwatch(
    color: Color,
    name: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color)
        )
        Text(
            text = name,
            style = MaterialTheme.typography.labelSmall,
            color = TextTertiaryLight,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

// ==========================================
// BUTTONS SECTION
// ==========================================

@Composable
private fun ButtonsSection() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        // Primary Button
        SubsectionLabel("Primary Button (Gold)")
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small,
            colors = ButtonDefaults.buttonColors(
                containerColor = GoldMuted,
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 0.dp,
                pressedElevation = 2.dp
            ),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Reserve Table",
                style = MaterialTheme.typography.labelLarge
            )
        }

        // Primary with Icon
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small,
            colors = ButtonDefaults.buttonColors(
                containerColor = GoldMuted,
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Add to Cart",
                style = MaterialTheme.typography.labelLarge
            )
        }

        // Disabled Primary
        SubsectionLabel("Disabled State")
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
            enabled = false,
            shape = MaterialTheme.shapes.small,
            colors = ButtonDefaults.buttonColors(
                containerColor = GoldMuted,
                disabledContainerColor = GoldMuted.copy(alpha = 0.38f)
            )
        ) {
            Text("Disabled Button")
        }

        // Secondary Button
        SubsectionLabel("Secondary Button (Outlined)")
        OutlinedButton(
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small,
            border = BorderStroke(1.dp, GoldMuted),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = GoldMuted
            )
        ) {
            Text(
                text = "View Menu",
                style = MaterialTheme.typography.labelLarge
            )
        }

        // Tertiary Button
        SubsectionLabel("Tertiary Button (Text)")
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextButton(onClick = { }) {
                Text(
                    text = "Cancel",
                    style = MaterialTheme.typography.labelLarge,
                    color = TextSecondaryLight
                )
            }
            TextButton(onClick = { }) {
                Text(
                    text = "Learn More",
                    style = MaterialTheme.typography.labelLarge,
                    color = GoldMuted
                )
            }
        }

        // Small Buttons
        SubsectionLabel("Button Sizes")
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { },
                shape = MaterialTheme.shapes.small,
                colors = ButtonDefaults.buttonColors(containerColor = GoldMuted),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text("Small", style = MaterialTheme.typography.labelSmall)
            }
            Button(
                onClick = { },
                shape = MaterialTheme.shapes.small,
                colors = ButtonDefaults.buttonColors(containerColor = GoldMuted),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text("Medium", style = MaterialTheme.typography.labelMedium)
            }
            Button(
                onClick = { },
                shape = MaterialTheme.shapes.small,
                colors = ButtonDefaults.buttonColors(containerColor = GoldMuted),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
            ) {
                Text("Large", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

// ==========================================
// TEXT FIELDS SECTION
// ==========================================

@Composable
private fun TextFieldsSection() {
    var text by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("user@example.com") }
    var password by remember { mutableStateOf("password123") }
    var errorText by remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        // Standard TextField
        SubsectionLabel("Standard Text Field")
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GoldMuted,
                unfocusedBorderColor = BorderLight,
                focusedLabelColor = GoldMuted,
                cursorColor = GoldMuted
            ),
            textStyle = MaterialTheme.typography.bodyLarge
        )

        // With Leading Icon
        SubsectionLabel("With Leading Icon")
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = null,
                    tint = Gray400
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GoldMuted,
                unfocusedBorderColor = BorderLight,
                focusedLabelColor = GoldMuted,
                cursorColor = GoldMuted
            )
        )

        // Password Field
        SubsectionLabel("Password Field")
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = null,
                    tint = Gray400
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GoldMuted,
                unfocusedBorderColor = BorderLight,
                focusedLabelColor = GoldMuted,
                cursorColor = GoldMuted
            )
        )

        // Error State
        SubsectionLabel("Error State")
        OutlinedTextField(
            value = errorText,
            onValueChange = { errorText = it },
            label = { Text("Phone Number") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = null,
                    tint = ErrorLight
                )
            },
            isError = true,
            supportingText = {
                Text(
                    text = "Please enter a valid phone number",
                    color = MaterialTheme.colorScheme.error
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GoldMuted,
                unfocusedBorderColor = BorderLight,
                focusedLabelColor = GoldMuted,
                cursorColor = GoldMuted,
                errorBorderColor = ErrorLight,
                errorLabelColor = ErrorLight
            )
        )

        // Search Field
        SubsectionLabel("Search Field")
        OutlinedTextField(
            value = "",
            onValueChange = { },
            placeholder = { Text("Search restaurants...") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    tint = Gray400
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GoldMuted,
                unfocusedBorderColor = BorderLight,
                focusedLabelColor = GoldMuted,
                cursorColor = GoldMuted
            )
        )

        // Disabled State
        SubsectionLabel("Disabled State")
        OutlinedTextField(
            value = "Cannot edit this",
            onValueChange = { },
            label = { Text("Disabled Field") },
            enabled = false,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small
        )
    }
}

// ==========================================
// CARDS SECTION
// ==========================================

@Composable
private fun CardsSection() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        // Standard Card
        SubsectionLabel("Standard Card (Flat)")
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Truffle Risotto",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Creamy arborio rice with black truffle shavings, parmesan, and a touch of white wine.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondaryLight
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "$42",
                    style = MaterialTheme.typography.titleMedium,
                    color = GoldMuted
                )
            }
        }

        // Card with Border
        SubsectionLabel("Card with Border")
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            border = BorderStroke(0.5.dp, BorderLight)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Premium Selection",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Our chef's special recommendations for the evening.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondaryLight
                )
            }
        }

        // Glass Card (Premium Effect)
        SubsectionLabel("Glass Card (Glassmorphism)")
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = CustomShapes.GlassCard,
            colors = CardDefaults.cardColors(
                containerColor = GlassWhite.copy(alpha = 0.8f)
            ),
            border = BorderStroke(0.5.dp, BorderLight)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = GoldMuted,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Featured Restaurant",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Experience culinary excellence at Le Petit Château, awarded 3 Michelin stars.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondaryLight
                )
            }
        }

        // Image Card (Food Photo)
        SubsectionLabel("Image Card (with Gradient Overlay)")
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4f / 3f),
            shape = CustomShapes.ImageCard
        ) {
            Box {
                // Placeholder for image (gray background)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Gray300),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Restaurant,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = Gray400
                    )
                }

                // Gradient overlay for text
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .align(Alignment.BottomCenter)
                        .background(
                            Brush.verticalGradient(
                                colors = GradientImageOverlay
                            )
                        )
                )

                // Text on gradient
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Wagyu Beef Steak",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "A5 Grade • Chef's Special",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }

                // Favorite button
                IconToggleButton(
                    checked = false,
                    onCheckedChange = { },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "Add to favorites",
                        tint = Color.White
                    )
                }
            }
        }

        // Elevated Card
        SubsectionLabel("Elevated Card")
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(AppColors.goldBackground()),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Restaurant,
                        contentDescription = null,
                        tint = GoldMuted
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Reservation Confirmed",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Table for 2 • Tonight at 7:00 PM",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondaryLight
                    )
                }
            }
        }
    }
}

// ==========================================
// CHIPS SECTION
// ==========================================

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ChipsSection() {
    var selectedChip by remember { mutableStateOf(0) }
    val chips = listOf("All", "Italian", "Japanese", "French", "Mexican", "Indian")

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        // Filter Chips
        SubsectionLabel("Filter Chips")
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            chips.forEachIndexed { index, chip ->
                FilterChip(
                    selected = selectedChip == index,
                    onClick = { selectedChip = index },
                    label = {
                        Text(
                            text = chip,
                            style = MaterialTheme.typography.labelMedium
                        )
                    },
                    shape = CustomShapes.Pill,
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = GoldMuted,
                        selectedLabelColor = Color.White,
                        containerColor = Gray100,
                        labelColor = MaterialTheme.colorScheme.onBackground
                    ),
                    border = null
                )
            }
        }

        // Accent Color Tags
        SubsectionLabel("Accent Tags")
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AccentTag(text = "Vegetarian", color = SageGreen)
            AccentTag(text = "Limited Time", color = TerracottaMuted)
            AccentTag(text = "Wine Pairing", color = DeepBurgundy)
            AccentTag(text = "Organic", color = DeepOlive)
        }

        // Status Tags
        SubsectionLabel("Status Tags")
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StatusTag(text = "Available", color = SuccessLight)
            StatusTag(text = "Sold Out", color = ErrorLight)
            StatusTag(text = "Few Left", color = WarningLight)
        }
    }
}

@Composable
private fun AccentTag(
    text: String,
    color: Color
) {
    Box(
        modifier = Modifier
            .clip(CustomShapes.Pill)
            .background(color.copy(alpha = 0.15f))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = color
        )
    }
}

@Composable
private fun StatusTag(
    text: String,
    color: Color
) {
    Row(
        modifier = Modifier
            .clip(CustomShapes.Pill)
            .background(color.copy(alpha = 0.15f))
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = color
        )
    }
}

// ==========================================
// LISTS SECTION
// ==========================================

@Composable
private fun ListsSection() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        SubsectionLabel("List Items")

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column {
                ListItem(
                    headlineContent = {
                        Text(
                            text = "Truffle Risotto",
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    supportingContent = {
                        Text(
                            text = "Creamy arborio rice with black truffle",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondaryLight,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    leadingContent = {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Gray300),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Restaurant,
                                contentDescription = null,
                                tint = Gray400
                            )
                        }
                    },
                    trailingContent = {
                        Text(
                            text = "$42",
                            style = MaterialTheme.typography.titleMedium,
                            color = GoldMuted
                        )
                    }
                )

                HorizontalDivider(color = DividerLight)

                ListItem(
                    headlineContent = {
                        Text(
                            text = "Wagyu Beef Steak",
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    supportingContent = {
                        Text(
                            text = "A5 grade Japanese wagyu with seasonal vegetables",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondaryLight,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    leadingContent = {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Gray300),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Restaurant,
                                contentDescription = null,
                                tint = Gray400
                            )
                        }
                    },
                    trailingContent = {
                        Text(
                            text = "$128",
                            style = MaterialTheme.typography.titleMedium,
                            color = GoldMuted
                        )
                    }
                )

                HorizontalDivider(color = DividerLight)

                ListItem(
                    headlineContent = {
                        Text(
                            text = "Lobster Thermidor",
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    supportingContent = {
                        Text(
                            text = "Classic French preparation with brandy cream",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondaryLight,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    leadingContent = {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Gray300),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Restaurant,
                                contentDescription = null,
                                tint = Gray400
                            )
                        }
                    },
                    trailingContent = {
                        Text(
                            text = "$89",
                            style = MaterialTheme.typography.titleMedium,
                            color = GoldMuted
                        )
                    }
                )
            }
        }
    }
}

// ==========================================
// ICONS & FABs SECTION
// ==========================================

@Composable
private fun IconsAndFabsSection() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        // Icon Buttons
        SubsectionLabel("Icon Buttons")
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = GoldMuted
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = TerracottaMuted
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "Profile"
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search"
                )
            }
        }

        // FABs
        SubsectionLabel("Floating Action Buttons")
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SmallFloatingActionButton(
                onClick = { },
                containerColor = GoldMuted,
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }

            FloatingActionButton(
                onClick = { },
                containerColor = GoldMuted,
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }

            FloatingActionButton(
                onClick = { },
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = GoldMuted,
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(
                    imageVector = Icons.Outlined.Restaurant,
                    contentDescription = "Restaurant"
                )
            }
        }
    }
}

// ==========================================
// SWITCHES SECTION
// ==========================================

@Composable
private fun SwitchesSection() {
    var switch1 by remember { mutableStateOf(true) }
    var switch2 by remember { mutableStateOf(false) }
    var toggle1 by remember { mutableStateOf(true) }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        SubsectionLabel("Switches")

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Push Notifications",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Receive updates about your orders",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondaryLight
                        )
                    }
                    Switch(
                        checked = switch1,
                        onCheckedChange = { switch1 = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = GoldMuted,
                            uncheckedThumbColor = Gray400,
                            uncheckedTrackColor = Gray100
                        )
                    )
                }

                HorizontalDivider(color = DividerLight)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Dark Mode",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Enable dark theme",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondaryLight
                        )
                    }
                    Switch(
                        checked = switch2,
                        onCheckedChange = { switch2 = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = GoldMuted,
                            uncheckedThumbColor = Gray400,
                            uncheckedTrackColor = Gray100
                        )
                    )
                }
            }
        }

        // Toggle Buttons
        SubsectionLabel("Toggle Buttons")
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IconToggleButton(
                checked = toggle1,
                onCheckedChange = { toggle1 = it }
            ) {
                Icon(
                    imageVector = if (toggle1) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (toggle1) TerracottaMuted else Gray400
                )
            }
        }
    }
}

// ==========================================
// SEMANTIC COLORS SECTION
// ==========================================

@Composable
private fun SemanticColorsSection() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        // Success Message
        SemanticCard(
            icon = Icons.Default.Check,
            title = "Success",
            message = "Your reservation has been confirmed!",
            color = SuccessLight
        )

        // Error Message
        SemanticCard(
            icon = Icons.Default.Warning,
            title = "Error",
            message = "Unable to process your payment. Please try again.",
            color = ErrorLight
        )

        // Warning Message
        SemanticCard(
            icon = Icons.Default.Warning,
            title = "Warning",
            message = "Your session will expire in 5 minutes.",
            color = WarningLight
        )

        // Info Message
        SemanticCard(
            icon = Icons.Default.Info,
            title = "Info",
            message = "Free delivery on orders over $50.",
            color = InfoLight
        )
    }
}

@Composable
private fun SemanticCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    message: String,
    color: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = 0.1f)
        ),
        border = BorderStroke(1.dp, color.copy(alpha = 0.3f))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = color,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

// ==========================================
// DIVIDER SECTION
// ==========================================

@Composable
private fun DividerSection() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        SubsectionLabel("Standard Divider")
        HorizontalDivider(color = DividerLight)

        SubsectionLabel("Divider with Padding")
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = DividerLight
        )

        SubsectionLabel("Thicker Divider")
        HorizontalDivider(
            thickness = 2.dp,
            color = BorderLight
        )
    }
}

// ==========================================
// SPACING SECTION
// ==========================================

@Composable
private fun SpacingSection() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        SpacingItem(size = 2, label = "xxxs = 2dp")
        SpacingItem(size = 4, label = "xxs = 4dp")
        SpacingItem(size = 8, label = "xs = 8dp")
        SpacingItem(size = 12, label = "sm = 12dp")
        SpacingItem(size = 16, label = "md = 16dp (standard)")
        SpacingItem(size = 24, label = "lg = 24dp")
        SpacingItem(size = 32, label = "xl = 32dp")
        SpacingItem(size = 48, label = "xxl = 48dp")
    }
}

@Composable
private fun SpacingItem(size: Int, label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .width(size.dp)
                .height(24.dp)
                .background(GoldMuted, RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondaryLight
        )
    }
}



