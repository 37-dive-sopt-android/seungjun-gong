package com.sopt.dive.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sopt.dive.core.designsystem.theme.DiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiveTheme {
//                MainRoute()
            }
        }
    }
}

@Composable
private fun MainRoute(
    name: String, modifier: Modifier = Modifier
) {

}

@Composable
private fun MainScreen(

) {

}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    DiveTheme {
        MainScreen()
    }
}
