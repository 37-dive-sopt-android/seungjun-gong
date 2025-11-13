package com.sopt.dive.presentation.search.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.core.util.noRippleClickable
import kotlinx.collections.immutable.persistentListOf

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TransitionCardScreen(
    modifier: Modifier = Modifier,
) {
    val cardList = persistentListOf("card1", "card2")
    var isShowDetail by remember { mutableStateOf(false) }
    var selectedKey by remember { mutableStateOf<String?>(null) }

    SharedTransitionLayout(
        modifier = modifier,
    ) {
        AnimatedContent(
            targetState = isShowDetail,
        ) { isShow ->
            with(this@SharedTransitionLayout) {
                if (!isShow) {
                    // 기본 상태
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.spacedBy(
                            space = 20.dp,
                            alignment = Alignment.CenterHorizontally,
                        ),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        cardList.forEach { card ->
                            TransitionCard(
                                key = card,
                                onClick = {
                                    isShowDetail = true
                                    selectedKey = card
                                },
                                sharedTransitionScope = this@SharedTransitionLayout,
                                animatedVisibilityScope = this@AnimatedContent,
                            )
                        }
                    }
                } else {
                    // 확장된 상태
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .noRippleClickable {
                                isShowDetail = false
                            },
                        contentAlignment = Alignment.Center,
                    ) {
                        selectedKey?.let { card ->
                            when (card) {
                                cardList[0] ->
                                    FlipCard(
                                        modifier = Modifier
                                            .sharedElement(
                                                rememberSharedContentState(key = card),
                                                animatedVisibilityScope = this@AnimatedContent,
                                            )
                                    )

                                cardList[1] ->
                                    SpringCard(
                                        modifier = Modifier
                                            .sharedElement(
                                                rememberSharedContentState(key = card),
                                                animatedVisibilityScope = this@AnimatedContent,
                                            )
                                    )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun TransitionCard(
    key: String,
    onClick: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
    size: Int = 200,
) {
    with(sharedTransitionScope) {
        Image(
            painter = painterResource(R.drawable.img_back_card),
            contentDescription = null,
            modifier = modifier
                .sharedElement(
                    rememberSharedContentState(key = key),
                    animatedVisibilityScope = animatedVisibilityScope,
                )
                .noRippleClickable(onClick = onClick)
                .size(size.dp)
        )
    }
}
