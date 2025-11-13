package com.sopt.dive.data.remote.datasource.profile

import com.sopt.dive.core.network.model.BaseResponse
import com.sopt.dive.data.remote.dto.MemberDto
import com.sopt.dive.data.remote.dto.profile.PatchProfileRequest

interface ProfileDataSource {
    suspend fun getUserProfile(
        userId: Long,
    ): BaseResponse<MemberDto>

    suspend fun patchUserProfile(
        userId: Long,
        requestBody: PatchProfileRequest,
    ): BaseResponse<MemberDto>
}
