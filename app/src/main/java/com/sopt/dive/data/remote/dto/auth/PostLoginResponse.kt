package com.sopt.dive.data.remote.dto.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostLoginResponse(
    @SerialName("userId")
    val userId: Long,
    @SerialName("message")
    val message: String,
)
