package com.sopt.dive.presentation.signin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.core.commom.navigation.Route
import kotlinx.serialization.Serializable

fun NavController.navigateToSignIn(navOptions: NavOptions? = null) =
    navigate(SignIn, navOptions)

fun NavGraphBuilder.signInGraph(
) {
    composable<SignIn> {

    }
}

@Serializable
data object SignIn : Route