package com.sopt.dive.presentation.my

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.core.compositionlocal.LocalInnerPadding
import com.sopt.dive.core.designsystem.component.DiveButton
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.local.datastore.UserData
import com.sopt.dive.presentation.DiveApplication
import kotlinx.coroutines.launch


@Composable
fun MyRoute(
    navigateToSignIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    var savedUserData by remember { mutableStateOf<UserData?>(null) }
    val coroutineScope = rememberCoroutineScope()

    val applicationContext = LocalContext.current.applicationContext
    val userDataStore = (applicationContext as DiveApplication).userDataStore

    LaunchedEffect(Unit) {
        savedUserData = userDataStore.getUserData()
    }

    savedUserData?.run {
        MyScreen(
            userId = userId,
            password = password,
            nickname = nickname,
            mbti = mbti,
            onLogoutClick = {
                coroutineScope.launch {
                    userDataStore.clearAutoLoginStatus()
                    navigateToSignIn()
                }
            },
            modifier = modifier,
        )
    }
}

@Composable
private fun MyScreen(
    userId: String,
    password: String,
    nickname: String,
    mbti: String,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val innerPadding = LocalInnerPadding.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
            ,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_foreground),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Green),
            )
            Text(
                text = "공승준"
            )
        }

        Text(
            text = "안녕하세요 공승준입니다",
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
                text = userId,
            )

            LabelText(
                label = stringResource(R.string.password_label),
                text = password,
            )

            LabelText(
                label = stringResource(R.string.nickname_label),
                text = nickname,
            )

            LabelText(
                label = stringResource(R.string.mbti_label),
                text = mbti,
            )
        }

        Spacer(Modifier.weight(1f))

        DiveButton(
            buttonText = "로그아웃",
            onClick = onLogoutClick,
            modifier = Modifier.fillMaxWidth()
        )
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
            userId = "",
            password = "",
            nickname = "",
            mbti = "",
            onLogoutClick = {},
        )
    }
}
