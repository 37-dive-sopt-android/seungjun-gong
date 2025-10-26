package com.sopt.dive.presentation.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.core.commom.navigation.MainTabRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToHome(navOptions: NavOptions? = null) =
    navigate(Home, navOptions)

fun NavGraphBuilder.homeGraph(
) {
    composable<Home> {

    }
}

@Serializable
data object Home : MainTabRoute