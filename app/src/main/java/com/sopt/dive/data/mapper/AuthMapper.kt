package com.sopt.dive.data.mapper

import com.sopt.dive.core.model.auth.SignInResult
import com.sopt.dive.data.remote.dto.auth.PostLoginResponse

fun PostLoginResponse.toModel() = SignInResult(
    userId = userId,
    message = message,
)
