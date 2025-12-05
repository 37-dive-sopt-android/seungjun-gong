package com.sopt.dive.presentation.profileedit

import androidx.compose.runtime.Immutable

interface ProfileEditContract {

    @Immutable
    data class ProfileEditState(
        val userId: Long = 0L,
        val nickname: String = "",
        val nicknameError: String = "",
        val email: String = "",
        val emailError: String = "",
        val age: String = "",
        val ageError: String = "",
    ) {
        val editEnabled: Boolean
            get() = nickname.isNotBlank() && nicknameError.isBlank() &&
                    email.isNotBlank() && emailError.isBlank() &&
                    age.isNotBlank() && ageError.isBlank()
    }

    sealed interface ProfileEditSideEffect {
        data class ToastMessage(val message: String) : ProfileEditSideEffect
        data object NavigateToMy : ProfileEditSideEffect
    }
}
