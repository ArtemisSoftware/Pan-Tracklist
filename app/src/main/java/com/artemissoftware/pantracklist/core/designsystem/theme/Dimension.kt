package com.artemissoftware.pantracklist.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

data class Dimension(
    val cardImage: DpSize,
    val iconSizeBig: DpSize,
)

val dimensionPortrait = Dimension(
    cardImage = DpSize(width = 120.dp, height = 120.dp),
    iconSizeBig = DpSize(width = 120.dp, height = 120.dp),
)


val dimensionLandScape = Dimension(
    cardImage = DpSize(width = 60.dp, height = 60.dp),
    iconSizeBig = DpSize(width = 120.dp, height = 120.dp),
)

internal val localDimension = staticCompositionLocalOf<Dimension> { throw IllegalStateException("No Dimension installed") }

val MaterialTheme.dimension: Dimension
    @Composable
    get() = localDimension.current