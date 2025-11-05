package com.sopt.dive.presentation.search.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import com.sopt.dive.R
import com.sopt.dive.core.util.noRippleClickable

@Composable
fun FlipCard(
    modifier: Modifier = Modifier,
) {
    var isCardFlipped by remember { mutableStateOf(false) }

    val rotateCardY by animateFloatAsState(
        targetValue = if (isCardFlipped) 0f else 180f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        ),
        label = "rotateCardY"
    )

    Box(
        modifier = modifier
            .graphicsLayer {
                rotationY = rotateCardY
                cameraDistance = 12f * density
            }
            .noRippleClickable(onClick = { isCardFlipped = !isCardFlipped }),
        contentAlignment = Alignment.Center,
    ) {
        if (rotateCardY <= 90f) {
            Image(
                painter = painterResource(R.drawable.img_front_card),
                contentDescription = null,
            )
        } else {
            Image(
                painter = painterResource(R.drawable.img_back_card),
                contentDescription = null,
                modifier = Modifier.graphicsLayer { scaleX = -1f },
            )
        }
    }
}
