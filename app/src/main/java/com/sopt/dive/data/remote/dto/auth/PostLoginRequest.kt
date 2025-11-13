package com.sopt.dive.data.remote.dto.auth

import kotlinx.serialization.SerialName

data class PostLoginRequest(
    @SerialName("username")
    val userName: String,
    @SerialName("password")
    val password: String,
)
