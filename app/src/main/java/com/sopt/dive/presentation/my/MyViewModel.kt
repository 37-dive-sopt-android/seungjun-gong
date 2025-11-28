package com.sopt.dive.presentation.my

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.core.util.stateInWhileSubscribed
import com.sopt.dive.data.repository.auth.AuthRepository
import com.sopt.dive.data.repository.profile.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

@HiltViewModel
class MyViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _uiState: StateFlow<MyContract.MyState> =
        flow {
            emit(MyContract.MyState())

            val result = profileRepository.getUserProfile()
            val newState = result.fold(
                onSuccess = { profile ->
                    MyContract.MyState(
                        loadUiState = MyUiState.Success(profile = profile),
                    )
                },
                onFailure = { throwable ->
                    MyContract.MyState(
                        loadUiState = MyUiState.Failure(
                            throwable.message ?: "프로필 정보를 불러오지 못했습니다."
                        ),
                    )
                }
            )

            emit(newState)
        }.stateInWhileSubscribed(
            scope = viewModelScope,
            initialValue = MyContract.MyState()
        )

    val uiState: StateFlow<MyContract.MyState> = _uiState

    private val _sideEffect = MutableSharedFlow<MyContract.MySideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()


    fun logout() = viewModelScope.launch {
        authRepository.logout()
            .onSuccess {
                _sideEffect.emit(MyContract.MySideEffect.LogoutSuccess)
            }
            .onFailure {
                _sideEffect.emit(MyContract.MySideEffect.ToastMessage(it.message.toString()))
            }
    }

    fun withDraw() = viewModelScope.launch {
        authRepository.deleteUser()
            .onSuccess {
                _sideEffect.emit(MyContract.MySideEffect.WithDrawSuccess)
            }.onFailure {
                Log.d("http", "${it.message}")
                _sideEffect.emit(MyContract.MySideEffect.ToastMessage(it.message.toString()))
            }
    }
}
