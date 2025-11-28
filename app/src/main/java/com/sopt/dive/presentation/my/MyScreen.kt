@file:Suppress("USELESS_IS_CHECK")

package com.sopt.dive.presentation.my

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.sopt.dive.R
import com.sopt.dive.core.compositionlocal.LocalInnerPadding
import com.sopt.dive.core.designsystem.component.DiveButton
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.model.profile.UserProfile
import com.sopt.dive.core.util.showToast
import com.sopt.dive.presentation.my.MyContract.MySideEffect.LogoutSuccess
import com.sopt.dive.presentation.my.MyContract.MySideEffect.ToastMessage
import com.sopt.dive.presentation.my.MyContract.MySideEffect.WithDrawSuccess

@Composable
fun MyRoute(
    navigateToSignIn: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MyViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is LogoutSuccess -> {
                        context.showToast("로그아웃 성공")
                        navigateToSignIn()
                    }

                    is WithDrawSuccess -> {
                        context.showToast("회원탈퇴 성공")
                        navigateToSignIn()
                    }

                    is ToastMessage -> {
                        context.showToast(sideEffect.message)
                    }
                }
            }
    }

    when (val state = uiState.loadUiState) {
        is MyUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text("로딩 중..")
            }
        }

        is MyUiState.Failure -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(state.message)
            }
        }

        is MyUiState.Success -> {
            MyScreen(
                profile = state.profile,
                onLogoutClick = viewModel::logout,
                onWithDrawClick = viewModel::withDraw,
                modifier = modifier,
            )
        }
    }
}

@Composable
private fun MyScreen(
    profile: UserProfile,
    onLogoutClick: () -> Unit,
    onWithDrawClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val innerPadding = LocalInnerPadding.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
            .padding(innerPadding),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Image(
                painter = painterResource(R.drawable.img_profile),
                contentDescription = null,
                modifier = modifier
                    .size(56.dp)
                    .clip(CircleShape),
            )

            Text(
                text = profile.name
            )
        }

        Text(
            text = "안녕하세요 ${profile.name}입니다",
            modifier = Modifier
                .padding(
                    top = 10.dp,
                    bottom = 30.dp,
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(30.dp),
        ) {
            LabelText(
                label = stringResource(R.string.id_label),
                text = profile.id.toString(),
            )

            LabelText(
                label = "USERNAME",
                text = profile.username,
            )

            LabelText(
                label = stringResource(R.string.nickname_label),
                text = profile.name,
            )

            LabelText(
                label = "EMAIL",
                text = profile.email,
            )

            LabelText(
                label = "AGE",
                text = profile.age.toString(),
            )

            DiveButton(
                buttonText = "로그아웃",
                onClick = onLogoutClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
            )

            DiveButton(
                buttonText = "회원탈퇴",
                onClick = onWithDrawClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
            )
        }
    }
}

@Composable
private fun LabelText(
    label: String,
    text: String,
) {
    Column {
        Text(
            text = label,
            modifier = Modifier
                .padding(
                    bottom = 10.dp,
                ),
            fontSize = 24.sp,
        )

        Text(
            text = text,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyScreenPreview() {
    DiveTheme {
        MyScreen(
            profile = UserProfile(
                id = 1,
                username = "",
                name = "홍길동",
                email = "john.c.calhoun@examplepetstore.com",
                age = 22,
                status = "",
            ),
            onLogoutClick = {},
            onWithDrawClick = {},
        )
    }
}
