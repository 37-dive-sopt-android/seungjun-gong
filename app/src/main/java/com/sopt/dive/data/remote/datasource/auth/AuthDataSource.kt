package com.sopt.dive.data.remote.datasource.auth

import com.sopt.dive.core.network.model.BaseResponse
import com.sopt.dive.data.remote.dto.MemberDto
import com.sopt.dive.data.remote.dto.auth.PostLoginRequest
import com.sopt.dive.data.remote.dto.auth.PostLoginResponse
import com.sopt.dive.data.remote.dto.auth.PostSignUpRequest

interface AuthDataSource {
    suspend fun postSignUp(
        request: PostSignUpRequest,
    ): BaseResponse<MemberDto>

    suspend fun postLogin(
        request: PostLoginRequest,
    ): BaseResponse<PostLoginResponse>

    suspend fun deleteUser(
        userId: Long,
    ): BaseResponse<Unit>
}
