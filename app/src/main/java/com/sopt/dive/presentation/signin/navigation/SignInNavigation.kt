package com.sopt.dive.presentation.signin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.core.commom.navigation.Route
import com.sopt.dive.presentation.signin.SignInRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToSignIn(navOptions: NavOptions? = null) =
    navigate(SignIn, navOptions)

fun NavGraphBuilder.signInGraph(
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit,
) {
    composable<SignIn> {
        SignInRoute(
            navigateToSignUp = navigateToSignUp,
            navigateToHome = navigateToHome,
        )
    }
}

@Serializable
data object SignIn : Route
