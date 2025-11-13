package com.sopt.dive.presentation.home

import androidx.lifecycle.ViewModel
import com.sopt.dive.presentation.home.model.HomeUiState
import com.sopt.dive.presentation.home.model.ProfileItemUiState
import com.sopt.dive.presentation.home.type.ProfileStatus
import com.sopt.dive.presentation.home.type.ProfileTrailingType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    // TODO: 삭제 예정
    private fun getProfiles() = persistentListOf(
        ProfileItemUiState(
            profileUrl = "https://avatars.githubusercontent.com/u/76648361?v=4",
            name = "공승준",
            description = "안드콩닥콩닥",
            profileStatus = ProfileStatus.BIRTHDAY,
            trailingType = ProfileTrailingType.GiftButton
        ),
        ProfileItemUiState(
            profileUrl = "https://avatars.githubusercontent.com/u/76648361?v=4",
            name = "침교동",
            description = "비린내",
            profileStatus = ProfileStatus.NONE,
            trailingType = ProfileTrailingType.MusicButton("Love Lee - AKMU")
        ),
        ProfileItemUiState(
            profileUrl = "https://avatars.githubusercontent.com/u/76648361?v=4",
            name = "침냥이",
            description = "고양이 카와이",
            profileStatus = ProfileStatus.NONE,
            trailingType = null
        ),
        ProfileItemUiState(
            profileUrl = "https://avatars.githubusercontent.com/u/76648361?v=4",
            name = "위험한 자식",
            description = null,
            profileStatus = ProfileStatus.BIRTHDAY,
            trailingType = ProfileTrailingType.GiftButton
        ),
        ProfileItemUiState(
            profileUrl = "https://avatars.githubusercontent.com/u/76648361?v=4",
            name = "침숭이",
            description = "M자 탈모",
            profileStatus = ProfileStatus.NONE,
            trailingType = ProfileTrailingType.MusicButton("Ditto - NewJeans")
        ),
        ProfileItemUiState(
            profileUrl = "https://avatars.githubusercontent.com/u/76648361?v=4",
            name = "위험한 자식",
            description = null,
            profileStatus = ProfileStatus.NONE,
            trailingType = ProfileTrailingType.GiftButton
        ),
        ProfileItemUiState(
            profileUrl = "https://avatars.githubusercontent.com/u/76648361?v=4",
            name = "침교동",
            description = "비린내",
            profileStatus = ProfileStatus.NONE,
            trailingType = ProfileTrailingType.MusicButton("Love Lee - AKMU")
        ),
        ProfileItemUiState(
            profileUrl = "https://avatars.githubusercontent.com/u/76648361?v=4",
            name = "침냥이",
            description = "고양이 카와이",
            profileStatus = ProfileStatus.NONE,
            trailingType = null
        ),
        ProfileItemUiState(
            profileUrl = "https://avatars.githubusercontent.com/u/76648361?v=4",
            name = "위험한 자식",
            description = null,
            profileStatus = ProfileStatus.BIRTHDAY,
            trailingType = ProfileTrailingType.GiftButton
        ),
        ProfileItemUiState(
            profileUrl = "https://avatars.githubusercontent.com/u/76648361?v=4",
            name = "침숭이",
            description = "M자 탈모",
            profileStatus = ProfileStatus.NONE,
            trailingType = ProfileTrailingType.MusicButton("Ditto - NewJeans")
        ),
        ProfileItemUiState(
            profileUrl = "https://avatars.githubusercontent.com/u/76648361?v=4",
            name = "위험한 자식",
            description = null,
            profileStatus = ProfileStatus.NONE,
            trailingType = ProfileTrailingType.GiftButton
        )
    )

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadProfiles()
    }

    private fun updateProfiles(profiles: ImmutableList<ProfileItemUiState>) =
        _uiState.update { currentState ->
            currentState.copy(
                profiles = profiles,
            )
        }

    private fun loadProfiles() {
        val dummyProfiles = getProfiles()
        updateProfiles(dummyProfiles)
    }

}
