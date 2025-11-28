package com.sopt.dive.presentation.signup

import androidx.compose.runtime.Immutable

interface SignUpContract {

    @Immutable
    data class SignUpState(
        val userId: String = "",
        val userIdError: String = "",
        val password: String = "",
        val passwordError: String = "",
        val nickname: String = "",
        val nicknameError: String = "",
        val email: String = "",
        val emailError: String = "",
        val age: String = "",
        val ageError: String = "",
    ) {
        val signUpEnabled: Boolean
            get() = userId.isNotBlank() && userIdError.isBlank() &&
                    password.isNotBlank() && passwordError.isBlank() &&
                    nickname.isNotBlank() && nicknameError.isBlank() &&
                    email.isNotBlank() && emailError.isBlank() &&
                    age.isNotBlank() && ageError.isBlank()
    }

    sealed interface SignUpSideEffect {
        data object SignUpSuccess : SignUpSideEffect
        data class ToastMessage(val message: String) : SignUpSideEffect
    }
}
