package com.sopt.dive.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.core.compositionlocal.LocalInnerPadding

@Composable
fun HomeRoute(
    navigateToSignIn: () -> Unit,
) {
    HomeScreen(

    )
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    val innerPadding = LocalInnerPadding.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.home),
            fontSize = 24.sp,
        )

    }
}
