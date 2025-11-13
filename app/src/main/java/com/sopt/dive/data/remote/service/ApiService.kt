package com.sopt.dive.data.remote.service

import com.sopt.dive.core.network.model.BaseResponse
import com.sopt.dive.data.remote.dto.MemberDto
import com.sopt.dive.data.remote.dto.auth.PostLoginRequest
import com.sopt.dive.data.remote.dto.auth.PostLoginResponse
import com.sopt.dive.data.remote.dto.auth.PostSignUpRequest
import com.sopt.dive.data.remote.dto.profile.PatchProfileRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("/users")
    suspend fun postSignUp(
        @Body requestBody: PostSignUpRequest,
    ): BaseResponse<MemberDto>

    @POST("/auth/login")
    suspend fun postLogin(
        @Body requestBody: PostLoginRequest,
    ): BaseResponse<PostLoginResponse>

    @DELETE("/users/{id}")
    suspend fun deleteUser(
        @Path("id") userId: Long,
    ): BaseResponse<Unit>

    @GET("/users/{id}")
    suspend fun getUserProfile(
        @Path("id") userId: Long,
    ): BaseResponse<MemberDto>

    @PATCH("/users/{id}")
    suspend fun patchUserProfile(
        @Path("id") userId: Long,
        @Body requestBody: PatchProfileRequest,
    ): BaseResponse<MemberDto>
}
