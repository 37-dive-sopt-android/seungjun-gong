package com.sopt.dive.presentation.signin

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.sopt.dive.data.repository.auth.AuthRepository
import com.sopt.dive.presentation.signin.SignInContract.SignInSideEffect
import com.sopt.dive.presentation.signin.navigation.SignIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val initAuthInfo: SignIn? = runCatching {
        savedStateHandle.toRoute<SignIn>()
    }.getOrNull()

    private val _uiState = MutableStateFlow(SignInContract.SignInState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SignInSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        initAuthInfo?.let {
            _uiState.update {
                it.copy(userId = initAuthInfo.id, password = initAuthInfo.pw)
            }
        }
    }

    fun signIn() {
        with(_uiState.value) {
            if (userId.isBlank() || password.isBlank()) {
                viewModelScope.launch {
                    _sideEffect.emit(SignInSideEffect.ToastMessage("ID와 비밀번호를 입력해주세요"))
                }
                return
            }
            viewModelScope.launch {
                authRepository.postSignIn(
                    username = userId,
                    password = password,
                ).onSuccess {
                    _sideEffect.emit(SignInSideEffect.SignInSuccess)
                }.onFailure { error ->
                    _sideEffect.emit(SignInSideEffect.ToastMessage("로그인 오류 ${error.message}"))
                }
            }
        }
    }

    fun updateUserId(userId: String) = _uiState.update {
        it.copy(userId = userId)
    }

    fun updatePassword(password: String) = _uiState.update {
        it.copy(password = password)
    }
}
