package com.sopt.dive.core.util

object FormFieldValidator {
    private val idRegex = Regex("^\\S{1,50}$")
    private val passwordRegex =  Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9])\\S{8,64}$")
    private val nicknameRegex = Regex("^\\S.{0,99}$")
    private val emailRegex = Regex("^(?=.{1,150}$)[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")

    private val ageRegex = Regex("^[0-9]+$")

    fun validateId(id: String): String = when {
        !idRegex.matches(id) -> "공백 제외 최대 50자"
        else -> ""
    }

    fun validatePassword(password: String): String = when {
        !passwordRegex.matches(password) ->
            "공백 제외 대소문자/숫자/특수문자 포함 8~64자"
        else -> ""
    }

    fun validateNickname(nickname: String): String = when {
        !nicknameRegex.matches(nickname) -> "공백 제외 최대 100자"
        else -> ""
    }

    fun validateEmail(email: String): String = when {
        !emailRegex.matches(email) -> "올바른 이메일 형식이 아닙니다."
        else -> ""
    }

    fun validateAge(age: String): String = when {
        !ageRegex.matches(age) -> "0 이상의 정수"
        else -> ""
    }
}
