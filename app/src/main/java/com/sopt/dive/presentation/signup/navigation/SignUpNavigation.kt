package com.sopt.dive.presentation.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.core.commom.navigation.Route
import kotlinx.serialization.Serializable

fun NavController.navigateToSignUp(navOptions: NavOptions? = null) =
    navigate(SignUp, navOptions)

fun NavGraphBuilder.signUpGraph(
) {
    composable<SignUp> {

    }
}

@Serializable
data object SignUp : Route