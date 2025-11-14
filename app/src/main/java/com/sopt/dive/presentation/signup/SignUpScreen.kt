package com.sopt.dive.presentation.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.util.showToast
import com.sopt.dive.presentation.signup.SignUpContract.SignUpSideEffect.SignUpSuccess
import com.sopt.dive.presentation.signup.SignUpContract.SignUpSideEffect.ToastMessage

@Composable
fun SignUpRoute(
    navigateToSignIn: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SignUpSuccess -> {
                        context.showToast("회원가입에 성공하였습니다")
                        navigateToSignIn(uiState.userId, uiState.password)
                    }

                    is ToastMessage -> {
                        context.showToast(sideEffect.message)
                    }
                }
            }
    }

    SignUpScreen(
        uiState = uiState,
        onUserIdChange = viewModel::onUserIdChange,
        onPasswordChange = viewModel::onPasswordChange,
        onNicknameChange = viewModel::onNicknameChange,
        onEmailChange = viewModel::onEmailChange,
        onAgeChange = viewModel::onAgeChange,
        onSignUpClick = viewModel::signUp,
        modifier = modifier,
    )
}

@Composable
private fun SignUpScreen(
    uiState: SignUpContract.SignUpState,
    onUserIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNicknameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onAgeChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp)
            .systemBarsPadding()
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.sign_up_title),
                modifier = Modifier.padding(top = 40.dp, bottom = 40.dp),
                fontSize = 28.sp,
                fontWeight = FontWeight.W900,
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                FormTextField(
                    label = stringResource(R.string.id_label),
                    value = uiState.userId,
                    onValueChange = onUserIdChange,
                    placeholder = stringResource(R.string.id_text_field_placeholder),
                    errorText = uiState.userIdError,
                )

                FormTextField(
                    label = stringResource(R.string.password_label),
                    value = uiState.password,
                    onValueChange = onPasswordChange,
                    placeholder = stringResource(R.string.password_text_field_placeholder),
                    errorText = uiState.passwordError,
                )

                FormTextField(
                    label = stringResource(R.string.nickname_label),
                    value = uiState.nickname,
                    onValueChange = onNicknameChange,
                    placeholder = stringResource(R.string.nickname_text_field_placeholder),
                    errorText = uiState.nicknameError,
                )

                FormTextField(
                    label = "EMAIL",
                    value = uiState.email,
                    onValueChange = onEmailChange,
                    placeholder = "이메일을 입력해주세요",
                    errorText = uiState.emailError,
                )

                FormTextField(
                    label = "AGE",
                    value = uiState.age,
                    onValueChange = onAgeChange,
                    placeholder = "나이를 입력해주세요",
                    errorText = uiState.ageError,
                )
            }
        }

        DiveButton(
            buttonText = stringResource(R.string.sign_up_button_text),
            onClick = onSignUpClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 40.dp,
                ),
        )
    }
}

@Composable
private fun FormTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    errorText: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        LabelTextField(
            label = label,
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
        )

        Text(
            text = errorText,
            color = Color.Red,
            fontSize = 12.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpPreview() {
    DiveTheme {
        SignUpScreen(
            uiState = SignUpContract.SignUpState(),
            onUserIdChange = {},
            onPasswordChange = {},
            onNicknameChange = {},
            onEmailChange = {},
            onAgeChange = {},
            onSignUpClick = {},
        )
    }
}
