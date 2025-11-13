package com.sopt.dive.presentation.home.model

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class HomeUiState(
    val profiles: ImmutableList<ProfileItemUiState> = persistentListOf(),
)
