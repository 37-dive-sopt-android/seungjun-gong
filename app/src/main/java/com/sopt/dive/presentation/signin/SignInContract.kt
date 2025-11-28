package com.sopt.dive.presentation.signin

import androidx.compose.runtime.Immutable

interface SignInContract {

    @Immutable
    data class SignInState(
        val userId: String = "",
        val password: String = "",
    ) : SignInContract

    sealed interface SignInSideEffect : SignInContract {
        data object SignInSuccess : SignInSideEffect
        data class ToastMessage(val message: String) : SignInSideEffect
    }
}
