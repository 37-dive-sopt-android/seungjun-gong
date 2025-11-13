package com.sopt.dive.data.remote.dto.profile

import kotlinx.serialization.SerialName

data class PatchProfileRequest(
    @SerialName("name")
    val name: String?,
    @SerialName("email")
    val email: String?,
    @SerialName("age")
    val age: Int?,
)
