package com.sopt.dive.presentation.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.core.util.FormFieldValidator
import com.sopt.dive.data.repository.auth.AuthRepository
import com.sopt.dive.presentation.signup.SignUpContract.SignUpSideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpContract.SignUpState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SignUpSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    fun onUserIdChange(userId: String) {
        val error = if (userId.isNotBlank()) FormFieldValidator.validateId(userId) else ""
        _uiState.update {
            it.copy(
                userId = userId,
                userIdError = error,
            )
        }
    }

    fun onPasswordChange(password: String) {
        val error = if (password.isNotBlank()) FormFieldValidator.validatePassword(password) else ""
        _uiState.update {
            it.copy(
                password = password,
                passwordError = error,
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

    fun signUp() {
        val state = _uiState.value

        if (!state.signUpEnabled) {
            viewModelScope.launch {
                _sideEffect.emit(SignUpSideEffect.ToastMessage("회원 정보를 입력해주세요."))
            }
            return
        }

        viewModelScope.launch {
            authRepository.postSignUp(
                username = state.userId,
                password = state.password,
                name = state.nickname,
                email = state.email,
                age = state.age.toIntOrNull() ?: 0,
            ).onSuccess {
                _sideEffect.emit(SignUpSideEffect.SignUpSuccess)
            }.onFailure { error ->
                Log.d("http", "${error.message}")
                _sideEffect.emit(SignUpSideEffect.ToastMessage("회원가입 오류 ${error.message}"))
            }
        }
    }
}
