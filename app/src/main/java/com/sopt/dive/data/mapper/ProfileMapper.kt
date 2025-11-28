package com.sopt.dive.data.mapper

import com.sopt.dive.core.model.profile.UserProfile
import com.sopt.dive.data.remote.dto.MemberDto

fun MemberDto.toModel() = UserProfile(
    id = userId,
    username = username,
    name = name,
    email = email,
    age = age,
    status = status,
)
