package com.sopt.dive.data.remote.dto.auth

import kotlinx.serialization.SerialName

data class PostLoginResponse(
    @SerialName("userId")
    val userId: Long,
    @SerialName("message")
    val message: String,
)
