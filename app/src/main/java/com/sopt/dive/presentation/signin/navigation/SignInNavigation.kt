package com.sopt.dive.presentation.signin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.core.common.navigation.Route
import com.sopt.dive.presentation.signin.SignInRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToSignIn(
    id: String = "",
    pw: String = "",
    navOptions: NavOptions? = null
) = navigate(SignIn(id, pw), navOptions)

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
data class SignIn(
    val id: String,
    val pw: String,
) : Route
