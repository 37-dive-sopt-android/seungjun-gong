package com.sopt.dive.presentation.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.core.designsystem.component.DiveButton
import com.sopt.dive.core.designsystem.component.LabelTextField
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.local.datastore.UserData
import com.sopt.dive.core.util.FormFieldValidator
import com.sopt.dive.core.util.showToast
import com.sopt.dive.presentation.DiveApplication
import kotlinx.coroutines.launch

@Composable
fun SignUpRoute(
    navigateToSignIn: (String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var userId by rememberSaveable { mutableStateOf("") }
    val userIdError = if (userId.isNotBlank())
        FormFieldValidator.validateId(userId) else ""

    var password by rememberSaveable { mutableStateOf("") }
    val passwordError = if (password.isNotBlank())
        FormFieldValidator.validatePassword(password) else ""

    var nickname by rememberSaveable { mutableStateOf("") }
    val nicknameError = if (nickname.isNotBlank())
        FormFieldValidator.validateNickname(nickname) else ""

    var userMbti by rememberSaveable { mutableStateOf("") }
    val userMbtiError = if (userMbti.isNotBlank())
        FormFieldValidator.validateMbti(userMbti) else ""

    val signUpEnabled = userId.isNotBlank() && userIdError.isBlank() &&
            password.isNotBlank() && passwordError.isBlank() &&
            nickname.isNotBlank() && nicknameError.isBlank() &&
            userMbti.isNotBlank() && userMbtiError.isBlank()

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val userDataStore = (context.applicationContext as DiveApplication).userDataStore

    SignUpScreen(
        userId = userId,
        userIdError = userIdError,
        onUserIdChange = { userId = it },
        password = password,
        passwordError = passwordError,
        onPasswordChange = { password = it },
        nickname = nickname,
        nicknameError = nicknameError,
        onNicknameChange = { nickname = it },
        userMbti = userMbti,
        userMbtiError = userMbtiError,
        onUserMbtiChange = { userMbti = it },
        onSignUpClick = {
            if (signUpEnabled)
                coroutineScope.launch {
                    val newUser = UserData(
                        userId = userId,
                        password = password,
                        nickname = nickname,
                        mbti = userMbti.uppercase(),
                    )
                    userDataStore.setUserData(newUser)
                    navigateToSignIn(userId, password)
                }
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
    val scrollState = rememberScrollState()
    val keyboardHeight = WindowInsets.ime.getBottom(LocalDensity.current)

    LaunchedEffect(key1 = keyboardHeight) {
        scrollState.scrollBy(keyboardHeight.toFloat())
    }

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
