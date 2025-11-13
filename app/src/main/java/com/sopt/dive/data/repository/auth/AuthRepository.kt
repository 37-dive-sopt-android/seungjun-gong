package com.sopt.dive.data.repository.auth

import com.sopt.dive.core.model.auth.SignInResult
import com.sopt.dive.core.model.profile.UserProfile

interface AuthRepository {
    suspend fun postSignUp(
        username: String,
        password: String,
        name: String,
        email: String,
        age: Int,
    ): Result<UserProfile>

    suspend fun postSignIn(
        username: String,
        password: String,
    ): Result<SignInResult>

    suspend fun deleteUser(
        userId: Long,
    ): Result<Unit>
}
