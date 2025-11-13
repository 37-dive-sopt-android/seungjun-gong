package com.sopt.dive.presentation.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sopt.dive.core.compositionlocal.LocalInnerPadding
import com.sopt.dive.presentation.search.component.TransitionCardScreen

@Composable
fun SearchRoute(
) {
    // TODO: 추후 수정 예정
    SearchScreen(
    )
}

@Composable
private fun SearchScreen(
    modifier: Modifier = Modifier,
) {
    val innerPadding = LocalInnerPadding.current

    TransitionCardScreen(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding),
    )
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    SearchScreen()
}
