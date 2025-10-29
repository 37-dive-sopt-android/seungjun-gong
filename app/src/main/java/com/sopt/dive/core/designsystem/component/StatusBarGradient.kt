package com.sopt.dive.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity

@Composable
fun StatusBarGradient(
    modifier: Modifier = Modifier, color: Color = Color.White, intensity: Float = 1.2f
) {
    val density = LocalDensity.current
    val statusBarTopPx = WindowInsets.statusBars.getTop(density).toFloat()
    val heightPx = statusBarTopPx * intensity

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(with(density) { heightPx.toDp() })
            .drawWithCache {
                val brush = Brush.verticalGradient(
                    colors = listOf(
                        color.copy(alpha = 1f), color.copy(alpha = 0.8f), Color.Transparent
                    ),
                    startY = 0f,
                    endY = heightPx,
                )
                onDrawBehind {
                    drawRect(brush = brush, size = size)
                }
            },
    )
}
