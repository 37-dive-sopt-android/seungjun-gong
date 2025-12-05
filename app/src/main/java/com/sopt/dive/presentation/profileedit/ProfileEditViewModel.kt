package com.sopt.dive.presentation.profileedit

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.sopt.dive.core.util.FormFieldValidator
import com.sopt.dive.data.repository.profile.ProfileRepository
import com.sopt.dive.presentation.profileedit.ProfileEditContract.ProfileEditSideEffect
import com.sopt.dive.presentation.profileedit.ProfileEditContract.ProfileEditSideEffect.NavigateToMy
import com.sopt.dive.presentation.profileedit.ProfileEditContract.ProfileEditSideEffect.ToastMessage
import com.sopt.dive.presentation.profileedit.ProfileEditContract.ProfileEditState
import com.sopt.dive.presentation.profileedit.navigation.ProfileEdit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val profileRepository: ProfileRepository,
) : ViewModel() {
    private val savedProfile: ProfileEdit = savedStateHandle.toRoute<ProfileEdit>()

    // This is Flow.
    private val _uiState = MutableStateFlow(ProfileEditState())
    val uiState = _uiState.asStateFlow()

    // This is Flow.
    private val _sideEffect = MutableSharedFlow<ProfileEditSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        _uiState.update {
            it.copy(
                userId = savedProfile.userId,
                nickname = savedProfile.name,
                email = savedProfile.email,
                age = savedProfile.age.toString(),
            )
        }
    }

    fun onNicknameChange(nickname: String) {
        val error = if (nickname.isNotBlank()) FormFieldValidator.validateNickname(nickname) else ""
        _uiState.update {
            it.copy(
                nickname = nickname,
                nicknameError = error,
            )
        }
    }

    fun onEmailChange(email: String) {
        val error = if (email.isNotBlank()) FormFieldValidator.validateEmail(email) else ""
        _uiState.update {
            it.copy(
                email = email,
                emailError = error,
            )
        }
    }

    fun onAgeChange(age: String) {
        val error = if (age.isNotBlank()) FormFieldValidator.validateAge(age) else ""
        _uiState.update {
            it.copy(
                age = age,
                ageError = error,
            )
        }
    }

    fun patchProfile() {
        val state = _uiState.value

        if (!isValidateEdit())
            return@patchProfile

        viewModelScope.launch {
            profileRepository.patchUserProfile(
                userId = state.userId,
                name = state.nickname,
                email = state.email,
                age = state.age.toIntOrNull() ?: 0,
            )
                .onSuccess {
                    _sideEffect.emit(NavigateToMy)
                }.onFailure { error ->
                    Log.d("http", "${error.message}")
                    _sideEffect.emit(ToastMessage("프로필 수정 오류 ${error.message}"))
                }
        }
    }

    private fun isValidateEdit(): Boolean {
        val state = _uiState.value

        when {
            !state.editEnabled -> {
                viewModelScope.launch {
                    _sideEffect.emit(ToastMessage("프로필 정보를 입력해주세요."))
                }
                return false
            }

            !isProfileChanged() -> {
                viewModelScope.launch {
                    _sideEffect.emit(ToastMessage("프로필 정보가 기존과 일치합니다.\n수정해주세요."))
                }
                return false
            }
        }
        return true
    }

    private fun isProfileChanged(): Boolean {
        return _uiState.value.nickname != savedProfile.name ||
                _uiState.value.email != savedProfile.email ||
                _uiState.value.age != savedProfile.age.toString()
    }
}
