package com.sopt.dive.presentation.my.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.core.common.navigation.MainTabRoute
import com.sopt.dive.presentation.my.MyRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToMy(navOptions: NavOptions? = null) =
    navigate(My, navOptions)

fun NavGraphBuilder.myGraph(
    navigateToSignIn: () -> Unit,
) {
    composable<My> {
        MyRoute(
            navigateToSignIn = navigateToSignIn,
        )
    }
}

@Serializable
data object My : MainTabRoute
