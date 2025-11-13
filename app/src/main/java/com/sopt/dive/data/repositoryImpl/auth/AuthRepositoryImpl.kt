package com.sopt.dive.data.repositoryImpl.auth

import com.sopt.dive.core.model.auth.SignInResult
import com.sopt.dive.core.model.profile.UserProfile
import com.sopt.dive.data.mapper.toModel
import com.sopt.dive.data.remote.datasource.auth.AuthDataSource
import com.sopt.dive.data.remote.dto.auth.PostLoginRequest
import com.sopt.dive.data.remote.dto.auth.PostSignUpRequest
import com.sopt.dive.data.repository.auth.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
) : AuthRepository {
    override suspend fun postSignUp(
        username: String,
        password: String,
        name: String,
        email: String,
        age: Int
    ): Result<UserProfile> = runCatching {
        val request = PostSignUpRequest(
            userName = username,
            password = password,
            name = name,
            email = email,
            age = age,
        )

        authDataSource.postSignUp(
            request = request,
        )
    }.mapCatching { response ->
        response.data.toModel()
    }

    override suspend fun postSignIn(
        username: String,
        password: String
    ): Result<SignInResult> = runCatching {
        val request = PostLoginRequest(
            userName = username,
            password = password,
        )

        authDataSource.postLogin(
            request = request,
        )
    }.mapCatching { response ->
        response.data.toModel()
    }


    override suspend fun deleteUser(userId: Long): Result<Unit> = runCatching {
        authDataSource.deleteUser(
            userId = userId,
        )
    }
}
