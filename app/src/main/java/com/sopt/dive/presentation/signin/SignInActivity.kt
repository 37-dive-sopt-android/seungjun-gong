package com.sopt.dive.presentation.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import android.graphics.Color as AndroidColor
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.presentation.main.MainActivity
import com.sopt.dive.presentation.signup.SignUpActivity

class SignInActivity : ComponentActivity() {
    private var receivedUserId: String = ""
    private var receivedPassword: String = ""

    private val signUpLauncher =
        registerForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                receivedUserId = data?.getStringExtra("USER_ID").orEmpty()
                receivedPassword = data?.getStringExtra("PASSWORD").orEmpty()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            SystemBarStyle.light(AndroidColor.TRANSPARENT, AndroidColor.TRANSPARENT),
            SystemBarStyle.light(AndroidColor.TRANSPARENT, AndroidColor.TRANSPARENT)
        )
        setContent {
            DiveTheme(darkTheme = false) {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    containerColor = Color.White,
                ) { innerPadding ->
                    SignInRoute(
                        receivedUserId = receivedUserId,
                        receivedPassword = receivedPassword,
                        navigateToSignUp = ::navigateToSignUp,
                        navigateToMain = ::navigateToMain,
                        modifier = Modifier
                            .padding(innerPadding)
                            .consumeWindowInsets(innerPadding)
                            .imePadding(),
                    )
                }
            }
        }
    }

    private fun navigateToSignUp() {
        Intent(this, SignUpActivity::class.java).also {
            signUpLauncher.launch(it)
        }
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }
}