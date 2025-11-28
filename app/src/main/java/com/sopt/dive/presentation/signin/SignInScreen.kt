package com.sopt.dive.presentation.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.sopt.dive.R
import com.sopt.dive.core.designsystem.component.DiveButton
import com.sopt.dive.core.designsystem.component.LabelTextField
import com.sopt.dive.core.designsystem.component.PasswordTextField
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.util.noRippleClickable
import com.sopt.dive.core.util.showToast
import com.sopt.dive.presentation.signin.SignInContract.SignInSideEffect.SignInSuccess
import com.sopt.dive.presentation.signin.SignInContract.SignInSideEffect.ToastMessage

@Composable
fun SignInRoute(
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: SignInViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SignInSuccess -> {
                        context.showToast("로그인에 성공하였습니다")
                        navigateToHome()
                    }

                    is ToastMessage -> {
                        context.showToast(sideEffect.message)
                    }
                }
            }
    }

    SignInScreen(
        userId = uiState.userId,
        onUserIdChange = viewModel::updateUserId,
        password = uiState.password,
        onPasswordChange = viewModel::updatePassword,
        onSignInClick = viewModel::signIn,
        onSignUpClick = navigateToSignUp,
    )
}

@Composable
private fun SignInScreen(
    userId: String,
    onUserIdChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
            .systemBarsPadding()
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.sign_in_title),
            modifier = Modifier
                .padding(
                    top = 40.dp,
                    bottom = 40.dp,
                ),
            fontSize = 28.sp,
            fontWeight = FontWeight.W900,
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(30.dp),
        ) {
            LabelTextField(
                label = stringResource(R.string.id_label),
                value = userId,
                onValueChange = onUserIdChange,
                placeholder = stringResource(R.string.id_text_field_placeholder),
            )

            LabelPasswordTextField(
                label = stringResource(R.string.password_label),
                password = password,
                onPasswordChange = onPasswordChange,
                placeholder = stringResource(R.string.password_text_field_placeholder),
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        DiveButton(
            buttonText = stringResource(R.string.sign_in_button_text),
            onClick = onSignInClick,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = stringResource(R.string.sign_up_button_text),
            modifier = Modifier
                .padding(
                    top = 5.dp,
                    bottom = 40.dp
                )
                .noRippleClickable(onClick = onSignUpClick),
            color = Color.LightGray,
            fontSize = 14.sp,
        )
    }
}

@Composable
private fun LabelPasswordTextField(
    label: String,
    password: String,
    onPasswordChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = label,
            fontSize = 24.sp,
        )

        PasswordTextField(
            value = password,
            onValueChange = onPasswordChange,
            placeholder = placeholder,
            modifier = Modifier
                .padding(
                    top = 5.dp,
                    bottom = 3.dp,
                )
        )

        HorizontalDivider(
            thickness = 1.dp,
            color = Color.Black,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    DiveTheme {
        SignInScreen(
            userId = "",
            onUserIdChange = {},
            password = "",
            onPasswordChange = {},
            onSignInClick = {},
            onSignUpClick = {},
        )
    }
}
