package com.sopt.dive.presentation.profileedit.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.core.common.navigation.Route
import com.sopt.dive.presentation.profileedit.ProfileEditRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToProfileEdit(
    userId: Long,
    name: String,
    email: String,
    age: String,
    navOptions: NavOptions? = null,
) = navigate(
    route = ProfileEdit(
        userId = userId,
        name = name,
        email = email,
        age = age
    ),
    navOptions = navOptions
)

fun NavGraphBuilder.profileEditGraph(
    navigateUp: () -> Unit,
) {
    composable<ProfileEdit> {
        ProfileEditRoute(
            navigateUp = navigateUp,
        )
    }
}

@Serializable
data class ProfileEdit(
    val userId: Long,
    val name: String,
    val email: String,
    val age: String,
) : Route
