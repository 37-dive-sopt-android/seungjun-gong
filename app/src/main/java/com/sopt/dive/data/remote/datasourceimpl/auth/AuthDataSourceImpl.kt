package com.sopt.dive.data.remote.datasourceimpl.auth

import com.sopt.dive.core.network.model.BaseResponse
import com.sopt.dive.data.remote.datasource.auth.AuthDataSource
import com.sopt.dive.data.remote.dto.MemberDto
import com.sopt.dive.data.remote.dto.auth.PostLoginRequest
import com.sopt.dive.data.remote.dto.auth.PostLoginResponse
import com.sopt.dive.data.remote.dto.auth.PostSignUpRequest
import com.sopt.dive.data.remote.service.ApiService
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : AuthDataSource {
    override suspend fun postSignUp(request: PostSignUpRequest)
            : BaseResponse<MemberDto> = apiService.postSignUp(request)

    override suspend fun postLogin(request: PostLoginRequest)
            : BaseResponse<PostLoginResponse> = apiService.postLogin(request)

    override suspend fun deleteUser(userId: Long)
            : BaseResponse<Unit> = apiService.deleteUser(userId)
}
