package com.sopt.dive.data.repositoryImpl.profile

import com.sopt.dive.core.model.profile.UserProfile
import com.sopt.dive.data.mapper.toModel
import com.sopt.dive.data.remote.datasource.profile.ProfileDataSource
import com.sopt.dive.data.remote.dto.profile.PatchProfileRequest
import com.sopt.dive.data.repository.profile.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDataSource: ProfileDataSource,
) : ProfileRepository {
    override suspend fun getUserProfile(
        userId: Long
    ): Result<UserProfile> = runCatching {
        profileDataSource.getUserProfile(
            userId = userId,
        )
    }.mapCatching { response ->
        response.data.toModel()
    }

    override suspend fun patchUserProfile(
        userId: Long,
        name: String?,
        email: String?,
        age: Int?
    ): Result<UserProfile> = runCatching {
        val request = PatchProfileRequest(
            name = name,
            email = email,
            age = age,
        )

        profileDataSource.patchUserProfile(
            userId = userId,
            requestBody = request,
        )
    }.mapCatching { response ->
        response.data.toModel()
    }
}
