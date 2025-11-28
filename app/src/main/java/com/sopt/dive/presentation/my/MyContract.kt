package com.sopt.dive.presentation.my

import androidx.compose.runtime.Immutable
import com.sopt.dive.core.model.profile.UserProfile

interface MyContract {

    @Immutable
    data class MyState(
        val loadUiState: MyUiState = MyUiState.Loading,
    )

    sealed interface MySideEffect : MyContract {
        data class ToastMessage(val message: String) : MySideEffect
        data object LogoutSuccess : MySideEffect
        data object WithDrawSuccess : MySideEffect
    }
}

sealed interface MyUiState {
    data object Loading : MyUiState
    data class Success(val profile: UserProfile) : MyUiState
    data class Failure(val message: String) : MyUiState
}
