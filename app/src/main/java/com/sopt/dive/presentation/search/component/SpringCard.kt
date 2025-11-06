package com.sopt.dive.presentation.search.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.core.util.noRippleClickable

@Composable
fun SpringCard(
    modifier: Modifier = Modifier,
) {
    var isCardFlipped by remember { mutableStateOf(false) }

    // Figma Spring (Mass=1, Stiffness=177.8, Damping≈20)
    val springSpec = spring<Float>(
        stiffness = 177.8f, dampingRatio = 0.85f
    )
    val density = LocalDensity.current

    val transition = updateTransition(targetState = isCardFlipped, label = "flip")

    val rotateCardY by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing,
            )
        },
        label = "rotateCardY",
    ) { flipped -> if (flipped) 180f else 0f }

    val move by transition.animateFloat(
        transitionSpec = { springSpec }, label = "move"
    ) { state -> if (state) with(density) { 56.dp.toPx() } else 0f }

    val frontElevation by transition.animateDp(
        transitionSpec = { tween(250, easing = FastOutSlowInEasing) }, label = "front-elev"
    ) { flipped ->
        if (!flipped) 8.dp else 0.dp
    }

    val backElevation by transition.animateDp(
        transitionSpec = { tween(250, easing = FastOutSlowInEasing) }, label = "back-elev"
    ) { flipped ->
        if (flipped) 8.dp else 0.dp
    }

    val backTextAlpha by transition.animateFloat(
        transitionSpec = { tween(1000, easing = FastOutSlowInEasing) }, label = "back-alpha"
    ) { flipped ->
        if (flipped) 1f else 0f
    }

    Box(
        modifier = modifier.noRippleClickable { isCardFlipped = !isCardFlipped },
        contentAlignment = Alignment.Center,
    ) {
        if (isCardFlipped) {
            BackCard(
                textAlpha = backTextAlpha,
                backElevation = backElevation,
                frontElevation = frontElevation,
                modifier = Modifier.graphicsLayer {
                    rotationY = rotateCardY - 180f
                    cameraDistance = 12f * this.density
                    translationX = move
                    translationY = move
                },
            )
        } else {
            FrontCard(
                textAlpha = backTextAlpha,
                backElevation = backElevation,
                frontElevation = frontElevation,
                modifier = Modifier.graphicsLayer {
                    rotationY = rotateCardY
                    cameraDistance = 12f * this.density
                },
            )
        }
    }
}

@Composable
private fun BoxScope.FrontCard(
    textAlpha: Float,
    backElevation: Dp,
    frontElevation: Dp,
    modifier: Modifier,
) {
    Surface(
        shape = RoundedCornerShape(
            topStart = 80.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 80.dp
        ),
        shadowElevation = backElevation,
        modifier = Modifier.wrapContentSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_card_background),
            contentDescription = null,
        )
        Box(
            modifier = Modifier.matchParentSize(),
        ) {
            Text(
                text = "침착맨 팝업 가고싶다".repeat(50),
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.graphicsLayer { alpha = textAlpha },
            )
        }
    }

    Surface(
        shape = RoundedCornerShape(
            topStart = 20.dp, topEnd = 80.dp, bottomStart = 80.dp, bottomEnd = 20.dp
        ), shadowElevation = frontElevation, modifier = modifier.wrapContentSize()
    ) {
        Image(
            painter = painterResource(R.drawable.img_front_card), contentDescription = null
        )
    }
}

@Composable
private fun BoxScope.BackCard(
    textAlpha: Float,
    backElevation: Dp,
    frontElevation: Dp,
    modifier: Modifier,
) {
    Surface(
        shape = RoundedCornerShape(
            topStart = 80.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 80.dp
        ),
        shadowElevation = frontElevation,
        modifier = modifier.wrapContentSize(),
    ) {
        Image(
            painter = painterResource(R.drawable.img_front_card),
            contentDescription = null,
        )
    }

    Surface(
        shape = RoundedCornerShape(
            topStart = 80.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 80.dp
        ),
        shadowElevation = backElevation,
        modifier = Modifier.wrapContentSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_card_background),
            contentDescription = null,
        )
        Box(
            modifier = Modifier.matchParentSize(),
        ) {
            Text(
                text = "침착맨 팝업 가고싶다".repeat(50),
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.graphicsLayer { alpha = textAlpha },
            )
        }
    }
}
