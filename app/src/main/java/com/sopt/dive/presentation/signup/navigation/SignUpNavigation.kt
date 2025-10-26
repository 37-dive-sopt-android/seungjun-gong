package com.sopt.dive.presentation.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.core.commom.navigation.Route
import com.sopt.dive.presentation.signup.SignUpRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToSignUp(navOptions: NavOptions? = null) =
    navigate(SignUp, navOptions)

fun NavGraphBuilder.signUpGraph(
    navigateToSignIn: () -> Unit,
) {
    composable<SignUp> {
        SignUpRoute(
            navigateToSignIn = navigateToSignIn,
        )
    }
}

@Serializable
data object SignUp : Route