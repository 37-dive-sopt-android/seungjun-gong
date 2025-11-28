package com.sopt.dive.core.model.profile

data class UserProfile(
    val id: Long,
    val username: String,
    val name: String,
    val email: String,
    val age: Int,
    val status: String,
)
