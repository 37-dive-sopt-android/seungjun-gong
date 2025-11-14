package com.sopt.dive.data.repositoryImpl.profile

import com.sopt.dive.core.model.profile.UserProfile
import com.sopt.dive.core.util.suspendRunCatching
import com.sopt.dive.data.local.datastore.UserLocalDataSource
import com.sopt.dive.data.mapper.toModel
import com.sopt.dive.data.remote.datasource.profile.ProfileDataSource
import com.sopt.dive.data.remote.dto.profile.PatchProfileRequest
import com.sopt.dive.data.repository.profile.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDataSource: ProfileDataSource,
    private val userLocalDataSource: UserLocalDataSource,
) : ProfileRepository {
    override suspend fun getUserProfile(): Result<UserProfile> = suspendRunCatching {

        val userId = userLocalDataSource.getUserId() ?: throw Exception("User ID not found")

        val response = profileDataSource.getUserProfile(
            userId = userId,
        ).data

        requireNotNull(response).toModel()
    }

    override suspend fun patchUserProfile(
        userId: Long,
        name: String?,
        email: String?,
        age: Int?
    ): Result<UserProfile> = suspendRunCatching {
        val request = PatchProfileRequest(
            name = name,
            email = email,
            age = age,
        )

        val response = requireNotNull(
            profileDataSource.patchUserProfile(
                userId = userId,
                requestBody = request,
            ).data
        )

        userLocalDataSource.setUserId(response.userId)

        response.toModel()
    }
}
