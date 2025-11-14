package com.sopt.dive.presentation.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sopt.dive.core.common.navigation.Route
import com.sopt.dive.presentation.splash.SplashRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.splashNavGraph(
    navigateToSignIn: () -> Unit,
    navigateToHome: () -> Unit,
) {
    composable<Splash> {
        SplashRoute(
            navigateToSignIn = navigateToSignIn,
            navigateToHome = navigateToHome,
        )
    }
}

@Serializable
data object Splash: Route
