package com.sopt.dive.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.repository.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _sideEffect = MutableSharedFlow<SplashSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        viewModelScope.launch {
            val loginStatus = authRepository.getAutoLoginState()

            val effect = if (loginStatus != null && loginStatus) {
                SplashSideEffect.NavigateToHome
            } else {
                SplashSideEffect.NavigateToSignIn
            }

            delay(1000)

            _sideEffect.emit(effect)
        }
    }
}

sealed interface SplashSideEffect {
    data object NavigateToHome : SplashSideEffect
    data object NavigateToSignIn : SplashSideEffect
}
