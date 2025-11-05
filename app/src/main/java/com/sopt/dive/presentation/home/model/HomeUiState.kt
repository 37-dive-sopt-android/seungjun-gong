package com.sopt.dive.presentation.home.model

import androidx.compose.runtime.Immutable
import com.sopt.dive.presentation.home.type.ProfileStatus
import com.sopt.dive.presentation.home.type.ProfileTrailingType
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class HomeUiState(
    val profiles: ImmutableList<ProfileItemUiState>,
)
