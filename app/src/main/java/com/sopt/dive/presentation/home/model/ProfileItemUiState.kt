package com.sopt.dive.presentation.home.model

import androidx.compose.runtime.Immutable
import com.sopt.dive.presentation.home.type.ProfileStatus
import com.sopt.dive.presentation.home.type.ProfileTrailingType

@Immutable
data class ProfileItemUiState(
    val profileUrl: String = "",
    val name: String = "",
    val description: String? = null,
    val profileStatus: ProfileStatus = ProfileStatus.NONE,
    val trailingType: ProfileTrailingType? = null,
)
