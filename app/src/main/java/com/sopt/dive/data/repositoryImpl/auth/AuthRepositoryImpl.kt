package com.sopt.dive.data.repositoryImpl.auth

import com.sopt.dive.core.model.auth.SignInResult
import com.sopt.dive.core.model.profile.UserProfile
import com.sopt.dive.core.util.suspendRunCatching
import com.sopt.dive.data.local.datastore.UserLocalDataSource
import com.sopt.dive.data.mapper.toModel
import com.sopt.dive.data.remote.datasource.auth.AuthDataSource
import com.sopt.dive.data.remote.dto.auth.PostLoginRequest
import com.sopt.dive.data.remote.dto.auth.PostSignUpRequest
import com.sopt.dive.data.repository.auth.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val userLocalDataSource: UserLocalDataSource,
) : AuthRepository {
    override suspend fun postSignUp(
        username: String,
        password: String,
        name: String,
        email: String,
        age: Int
    ): Result<UserProfile> = suspendRunCatching {
        val request = PostSignUpRequest(
            userName = username,
            password = password,
            name = name,
            email = email,
            age = age,
        )

        val response = authDataSource.postSignUp(
            request = request,
        ).data

        requireNotNull(response).toModel()
    }

    override suspend fun postSignIn(
        username: String,
        password: String
    ): Result<SignInResult> = suspendRunCatching {
        val request = PostLoginRequest(
            userName = username,
            password = password,
        )

        val response = requireNotNull(
            authDataSource.postLogin(
                request = request,
            ).data
        )

        userLocalDataSource.setUserId(response.userId)
        userLocalDataSource.setAutoLoginStatus(true)

        response.toModel()
    }

    override suspend fun deleteUser(): Result<Unit> = suspendRunCatching {
        val userId = userLocalDataSource.getUserId() ?: throw Exception("User ID not found")

        authDataSource.deleteUser(
            userId = userId,
        )

        userLocalDataSource.clearUserId()
        userLocalDataSource.clearAutoLoginStatus()
    }

    override suspend fun logout(): Result<Unit> = suspendRunCatching {
        userLocalDataSource.clearUserId()
        userLocalDataSource.clearAutoLoginStatus()
    }

    override suspend fun getAutoLoginState(): Boolean = userLocalDataSource.getAutoLoginStatus()
}
