package com.sopt.dive.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sopt.dive.core.compositionlocal.LocalInnerPadding

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

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "여기는 서치",
            fontSize = 24.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    SearchScreen()
}
