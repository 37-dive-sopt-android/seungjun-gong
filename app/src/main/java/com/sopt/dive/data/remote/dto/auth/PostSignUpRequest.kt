package com.sopt.dive.data.remote.dto.auth

import kotlinx.serialization.SerialName

data class PostSignUpRequest(
    @SerialName("username")
    val userName: String,
    @SerialName("password")
    val password: String,
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val email: String,
    @SerialName("age")
    val age: Int,
)
