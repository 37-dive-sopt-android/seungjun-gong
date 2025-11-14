package com.sopt.dive.data.repository.profile

import com.sopt.dive.core.model.profile.UserProfile

interface ProfileRepository {
    suspend fun getUserProfile(): Result<UserProfile>

    suspend fun patchUserProfile(
        userId: Long,
        name: String?,
        email: String?,
        age: Int?,
    ): Result<UserProfile>
}
