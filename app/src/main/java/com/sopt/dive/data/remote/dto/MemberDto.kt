package com.sopt.dive.data.remote.dto

import kotlinx.serialization.SerialName

data class MemberDto(
    @SerialName("id")
    val userId: Long,
    @SerialName("username")
    val username: String,
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val email: String,
    @SerialName("age")
    val age: Int,
    @SerialName("status")
    val status: String,
)
