package com.sopt.dive.presentation.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.sopt.dive.core.compositionlocal.LocalInnerPadding
import com.sopt.dive.presentation.home.navigation.homeGraph
import com.sopt.dive.presentation.home.navigation.navigateToHome
import com.sopt.dive.presentation.main.component.MainBottomBar
import com.sopt.dive.presentation.my.navigation.myGraph
import com.sopt.dive.presentation.search.navigation.searchGraph
import com.sopt.dive.presentation.signin.navigation.navigateToSignIn
import com.sopt.dive.presentation.signin.navigation.signInGraph
import com.sopt.dive.presentation.signup.navigation.navigateToSignUp
import com.sopt.dive.presentation.signup.navigation.signUpGraph
import kotlinx.collections.immutable.toImmutableList

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
) {
    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = navigator.showBottomBar(),
                enter = fadeIn() + slideIn { IntOffset(0, it.height) },
                exit = fadeOut() + slideOut { IntOffset(0, it.height) },
            ) {
                MainBottomBar(
                    tabs = MainTab.entries.toImmutableList(),
                    currentTab = navigator.currentTab,
                    onTabSelected = navigator::navigate,
                    modifier = Modifier
                        .navigationBarsPadding()
                        .padding(
                            start = 20.dp,
                            end = 20.dp,
                            bottom = 20.dp,
                        ),
                )
            }
        },
        containerColor = Color.White,
    ) { innerPadding ->
        CompositionLocalProvider(LocalInnerPadding provides innerPadding) {
            MainNavHost(
                navigator = navigator,
            )
        }
    }
}

@Composable
private fun MainNavHost(
    navigator: MainNavigator,
) {
    val clearBackStackNavOptions = navOptions {
        popUpTo(0) { inclusive = true }
        launchSingleTop = true
    }

    NavHost(
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
        navController = navigator.navController,
        startDestination = navigator.startDestination,
    ) {
        signInGraph(
            navigateToSignUp = navigator.navController::navigateToSignUp,
            navigateToHome = {
                navigator.navController.navigateToHome(
                    navOptions = clearBackStackNavOptions,
                )
            },
        )

        signUpGraph(
            navigateToSignIn = { id, pw ->
                navigator.navController.navigateToSignIn(
                    id = id,
                    pw = pw,
                    navOptions = clearBackStackNavOptions,
                )
            }
        )

        homeGraph(
            navigateToSignIn = {
                navigator.navController.navigateToSignIn(
                    navOptions = clearBackStackNavOptions,
                )
            },
        )

        searchGraph()

        myGraph(
            navigateToSignIn =  {
                navigator.navController.navigateToSignIn(
                    navOptions = clearBackStackNavOptions,
                )
            },
        )
    }
}
