package com.sopt.dive.data.remote.datasourceimpl.profile

import com.sopt.dive.core.network.model.BaseResponse
import com.sopt.dive.data.remote.datasource.profile.ProfileDataSource
import com.sopt.dive.data.remote.dto.MemberDto
import com.sopt.dive.data.remote.dto.profile.PatchProfileRequest
import com.sopt.dive.data.remote.service.ApiService
import javax.inject.Inject

class ProfileDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : ProfileDataSource {
    override suspend fun getUserProfile(userId: Long)
            : BaseResponse<MemberDto> = apiService.getUserProfile(userId)

    override suspend fun patchUserProfile(userId: Long, requestBody: PatchProfileRequest)
            : BaseResponse<MemberDto> = apiService.patchUserProfile(
        userId = userId,
        requestBody = requestBody
    )
}
