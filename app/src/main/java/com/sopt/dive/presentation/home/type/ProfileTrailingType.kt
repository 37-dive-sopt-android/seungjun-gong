package com.sopt.dive.presentation.home.type

sealed interface ProfileTrailingType {
    data class MusicButton(val text: String) : ProfileTrailingType
    data object GiftButton : ProfileTrailingType
}
