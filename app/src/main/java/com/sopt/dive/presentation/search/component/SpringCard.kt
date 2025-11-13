package com.sopt.dive.presentation.search.component

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.sopt.dive.R
import com.sopt.dive.core.util.noRippleClickable

@Composable
fun SpringCard(
    modifier: Modifier = Modifier,
) {
    var isCardClicked by remember { mutableStateOf(false) }

    // Figma Spring (Mass=1, Stiffness=177.8, Damping≈20)
    val springSpecFloat = spring<Float>(
        stiffness = 177.8f, dampingRatio = 0.75f
    )

    val density = LocalDensity.current

    val transition = updateTransition(targetState = isCardClicked, label = "card-transition")

    val rotateCardY by transition.animateFloat(
        transitionSpec = { springSpecFloat },
        label = "rotate-card-y",
    ) { isClicked -> if (isClicked) 180f else 0f }

    val move by transition.animateFloat(
        transitionSpec = { springSpecFloat },
        label = "move",
    ) { isClicked -> if (isClicked) with(density) { 56.dp.toPx() } else 0f }

    val backTextAlpha by transition.animateFloat(
        transitionSpec = { springSpecFloat },
        label = "back-alpha",
    ) { isClicked ->
        if (isClicked) 1f else 0f
    }

    val frontShape = RoundedCornerShape(
        topStart = 20.dp,
        topEnd = 80.dp,
        bottomStart = 80.dp,
        bottomEnd = 20.dp,
    )

    val backShape = RoundedCornerShape(
        topStart = 80.dp,
        topEnd = 20.dp,
        bottomStart = 20.dp,
        bottomEnd = 80.dp,
    )

    Box(
        modifier = modifier.noRippleClickable { isCardClicked = !isCardClicked },
        contentAlignment = Alignment.Center,
    ) {
        BackCard(
            textAlpha = backTextAlpha,
            modifier = Modifier
                .zIndex(if (isCardClicked) 1f else 0f)
                .shadow(
                    elevation = if (rotateCardY <= 20f) 0.dp else 10.dp,
                    shape = backShape,
                )
                .clip(backShape),
        )

        Box(
            modifier = Modifier
                .zIndex(if (isCardClicked) 0f else 1f)
                .shadow(
                    elevation = if (rotateCardY <= 20f) 10.dp else 0.dp,
                    shape = frontShape
                )
                .graphicsLayer {
                    rotationY = rotateCardY
                    cameraDistance = 12f * this.density
                    translationX = move
                    translationY = move
                }
                .clip(frontShape),
        ) {
            Image(
                painter = painterResource(R.drawable.img_front_card),
                contentDescription = null,
            )
        }
    }
}

@Composable
private fun BackCard(
    textAlpha: Float,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_card_background),
            contentDescription = null,
        )

        Box(
            modifier = Modifier
                .matchParentSize(),
        ) {
            Text(
                text = "침착맨팝업가고싶다".repeat(50),
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .graphicsLayer { alpha = textAlpha },
            )
        }
    }
}
