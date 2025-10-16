package com.sopt.dive.presentation.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.sopt.dive.core.designsystem.theme.DiveTheme
import android.graphics.Color as AndroidColor

class SignUpActivity : ComponentActivity() {
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
                    SignUpRoute(
                        navigateToSignIn = { id, pw ->
                            navigateToSignIn(id, pw)
                        },
                        modifier = Modifier
                            .padding(innerPadding)
                            .consumeWindowInsets(innerPadding)
                            .imePadding(),
                    )
                }
            }
        }
    }

    fun navigateToSignIn(userId: String, userPassword: String) {
        val resultIntent = Intent().apply {
            putExtra("USER_ID", userId)
            putExtra("PASSWORD", userPassword)
        }
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}
