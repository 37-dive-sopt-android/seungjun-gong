package com.sopt.dive.presentation.home

import androidx.compose.runtime.Immutable
import com.sopt.dive.presentation.home.type.ProfileStatus
import com.sopt.dive.presentation.home.type.ProfileTrailingType
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class HomeUiState(
    val profileUrl: String = "",
    val name: String = "",
    val description: String? = null,
    val profileStatus: ProfileStatus = ProfileStatus.NONE,
    val trailingType: ProfileTrailingType? = null,
) {
    // TODO: 삭제 예정
    companion object {
        val dummyProfiles = persistentListOf(
            HomeUiState(
                profileUrl = "https://avatars.githubusercontent.com/u/76648361?v=4",
                name = "공승준",
                description = "안드콩닥콩닥",
                profileStatus = ProfileStatus.BIRTHDAY,
                trailingType = ProfileTrailingType.GiftButton
            ),
            HomeUiState(
                profileUrl = "https://avatars.githubusercontent.com/u/76648361?v=4",
                name = "침교동",
                description = "비린내",
                profileStatus = ProfileStatus.NONE,
                trailingType = ProfileTrailingType.MusicButton("Love Lee - AKMU")
            ),
            HomeUiState(
                profileUrl = "https://avatars.githubusercontent.com/u/76648361?v=4",
                name = "침냥이",
                description = "고양이 카와이",
                profileStatus = ProfileStatus.NONE,
                trailingType = null
            ),
            HomeUiState(
                profileUrl = "https://avatars.githubusercontent.com/u/76648361?v=4",
                name = "위험한 자식",
                description = null,
                profileStatus = ProfileStatus.BIRTHDAY,
                trailingType = ProfileTrailingType.GiftButton
            ),
            HomeUiState(
                profileUrl = "https://avatars.githubusercontent.com/u/76648361?v=4",
                name = "침숭이",
                description = "M자 탈모",
                profileStatus = ProfileStatus.NONE,
                trailingType = ProfileTrailingType.MusicButton("Ditto - NewJeans")
            ),
            HomeUiState(
                profileUrl = "https://avatars.githubusercontent.com/u/76648361?v=4",
                name = "위험한 자식",
                description = null,
                profileStatus = ProfileStatus.NONE,
                trailingType = ProfileTrailingType.GiftButton
            ),
            HomeUiState(
                profileUrl = "https://avatars.githubusercontent.com/u/76648361?v=4",
                name = "침교동",
                description = "비린내",
                profileStatus = ProfileStatus.NONE,
                trailingType = ProfileTrailingType.MusicButton("Love Lee - AKMU")
            ),
            HomeUiState(
                profileUrl = "https://avatars.githubusercontent.com/u/76648361?v=4",
                name = "침냥이",
                description = "고양이 카와이",
                profileStatus = ProfileStatus.NONE,
                trailingType = null
            ),
            HomeUiState(
                profileUrl = "https://avatars.githubusercontent.com/u/76648361?v=4",
                name = "위험한 자식",
                description = null,
                profileStatus = ProfileStatus.BIRTHDAY,
                trailingType = ProfileTrailingType.GiftButton
            ),
            HomeUiState(
                profileUrl = "https://avatars.githubusercontent.com/u/76648361?v=4",
                name = "침숭이",
                description = "M자 탈모",
                profileStatus = ProfileStatus.NONE,
                trailingType = ProfileTrailingType.MusicButton("Ditto - NewJeans")
            ),
            HomeUiState(
                profileUrl = "https://avatars.githubusercontent.com/u/76648361?v=4",
                name = "위험한 자식",
                description = null,
                profileStatus = ProfileStatus.NONE,
                trailingType = ProfileTrailingType.GiftButton
            ),
        )
    }
}