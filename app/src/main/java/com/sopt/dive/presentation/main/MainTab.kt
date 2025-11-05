package com.sopt.dive.presentation.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.sopt.dive.R
import com.sopt.dive.core.common.navigation.MainTabRoute
import com.sopt.dive.core.common.navigation.Route
import com.sopt.dive.presentation.home.navigation.Home
import com.sopt.dive.presentation.my.navigation.My
import com.sopt.dive.presentation.search.navigation.Search

enum class MainTab(
    @DrawableRes val iconRes: Int,
    @StringRes val label: Int,
    val route: MainTabRoute,
) {
    HOME(
        iconRes = R.drawable.ic_home_24,
        label = R.string.home,
        route = Home,
    ),

    SEARCH(
        iconRes = R.drawable.ic_search_24,
        label = R.string.search,
        route = Search,
    ),

    MY(
        iconRes = R.drawable.ic_profile_24,
        label = R.string.my,
        route = My,
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? =
            entries.find { predicate(it.route) }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean =
            entries.map { it.route }.any { predicate(it) }
    }
}
