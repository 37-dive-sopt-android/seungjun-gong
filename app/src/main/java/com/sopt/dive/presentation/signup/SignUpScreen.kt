package com.sopt.dive.presentation.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.core.designsystem.component.DiveButton
import com.sopt.dive.core.designsystem.component.LabelTextField
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.util.FormFieldValidator
import com.sopt.dive.core.util.showToast

@Composable
fun SignUpRoute(
    navigateToSignIn: (String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var userId by rememberSaveable { mutableStateOf("") }
    var userIdError by remember { mutableStateOf("") }

    var password by rememberSaveable { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    var nickname by rememberSaveable { mutableStateOf("") }
    var nicknameError by remember { mutableStateOf("") }

    var userMbti by rememberSaveable { mutableStateOf("") }
    var userMbtiError by remember { mutableStateOf("") }

    val signUpEnabled = userId.isNotBlank() && userIdError.isBlank() &&
            password.isNotBlank() && passwordError.isBlank() &&
            nickname.isNotBlank() && nicknameError.isBlank() &&
            userMbti.isNotBlank() && userMbtiError.isBlank()

    val context = LocalContext.current

    SignUpScreen(
        userId = userId,
        userIdError = userIdError,
        onUserIdChange = {
            userId = it
            userIdError = if (it.isNotBlank())
                FormFieldValidator.validateId(it) else ""
        },
        password = password,
        passwordError = passwordError,
        onPasswordChange = {
            password = it
            passwordError = if (it.isNotBlank())
                FormFieldValidator.validatePassword(it) else ""
        },
        nickname = nickname,
        nicknameError = nicknameError,
        onNicknameChange = {
            nickname = it
            nicknameError = if (it.isNotBlank())
                FormFieldValidator.validateNickname(it) else ""
        },
        userMbti = userMbti,
        userMbtiError = userMbtiError,
        onUserMbtiChange = {
            userMbti = it
            userMbtiError = if (it.isNotBlank())
                FormFieldValidator.validateMbti(it) else ""
        },
        onSignUpClick = {
            if (signUpEnabled) navigateToSignIn(userId, password)
            else with(context) { showToast(getString(R.string.error_text)) }
        },
        modifier = modifier,
    )
}

@Composable
private fun SignUpScreen(
    userId: String,
    userIdError: String,
    onUserIdChange: (String) -> Unit,
    password: String,
    passwordError: String,
    onPasswordChange: (String) -> Unit,
    nickname: String,
    nicknameError: String,
    onNicknameChange: (String) -> Unit,
    userMbti: String,
    userMbtiError: String,
    onUserMbtiChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp),
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
                    value = userId,
                    onValueChange = onUserIdChange,
                    placeholder = stringResource(R.string.id_text_field_placeholder),
                    errorText = userIdError,
                )

                FormTextField(
                    label = stringResource(R.string.password_label),
                    value = password,
                    onValueChange = onPasswordChange,
                    placeholder = stringResource(R.string.password_text_field_placeholder),
                    errorText = passwordError,
                )

                FormTextField(
                    label = stringResource(R.string.nickname_label),
                    value = nickname,
                    onValueChange = onNicknameChange,
                    placeholder = stringResource(R.string.nickname_text_field_placeholder),
                    errorText = nicknameError,
                )

                FormTextField(
                    label = stringResource(R.string.mbti_label),
                    value = userMbti,
                    onValueChange = onUserMbtiChange,
                    placeholder = stringResource(R.string.mbti_text_field_placeholder),
                    errorText = userMbtiError,
                )
            }
        }

        DiveButton(
            buttonText = stringResource(R.string.sign_up_button_text),
            onClick = onSignUpClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 20.dp,
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
            userId = "",
            userIdError = "",
            onUserIdChange = {},
            password = "",
            passwordError = "",
            onPasswordChange = {},
            nickname = "",
            nicknameError = "",
            onNicknameChange = {},
            userMbti = "",
            userMbtiError = "",
            onUserMbtiChange = {},
            onSignUpClick = {},
        )
    }
}