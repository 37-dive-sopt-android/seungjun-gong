package com.sopt.dive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.core.designsystem.component.LabelTextField
import com.sopt.dive.core.designsystem.theme.DiveTheme

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiveTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                ) { innerPadding ->
                    innerPadding
                }
            }
        }
    }
}

@Composable
private fun SignInRoute(
    modifier: Modifier = Modifier,
) {
    var userId by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    SignInScreen(
        userId = userId,
        onUserIdChange = { userId = it },
        password = password,
        onPasswordChange = { password = it },
        modifier = modifier,
    )

}

@Composable
private fun SignInScreen(
    userId: String,
    onUserIdChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Welcome To SOPT",
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
        ) {
            LabelTextField(
                label = "ID",
                value = userId,
                onValueChange = onUserIdChange,
                placeholder = "아이디를 입력해주세요",
                modifier = Modifier
                    .padding(bottom = 30.dp),
            )

            LabelTextField(
                label = "PW",
                value = password,
                onValueChange = onPasswordChange,
                placeholder = "비밀번호를 입력해주세요",
            )
        }
    }


}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    DiveTheme {
        SignInScreen(
            userId = "",
            onUserIdChange = {},
            password = "",
            onPasswordChange = {},
        )
    }
}