package com.sopt.dive.core.util

object FormFieldValidator {
    private val idRegex = "[^a-zA-Z0-9]".toRegex()
    private val passwordRegex = "[^a-zA-Z0-9~!@#\$%^&*]".toRegex()
    private val nicknameRegex = "[^가-힣a-zA-Z0-9]".toRegex()
    private val mbtiRegex = "^[E|I][N|S][F|T][J|P]$".toRegex()

    fun validateId(id: String): String = when {
        idRegex.containsMatchIn(id) -> "영문, 숫자 조합만 가능합니다."
        id.length !in 6..10 -> "ID는 6~10글자여야 합니다."
        else -> ""
    }

    fun validatePassword(password: String): String = when {
        passwordRegex.containsMatchIn(password) -> "영문, 숫자, 특수문자(~!@#$%^&*) 조합만 가능합니다."
        password.length !in 8..12 -> "비밀번호는 8~12글자여야 합니다."
        else -> ""
    }

    fun validateNickname(nickname: String): String = when {
        nicknameRegex.containsMatchIn(nickname) -> "한글, 영문, 숫자 조합만 가능합니다"
        nickname.isBlank() -> "닉네임을 입력해주세요."
        else -> ""
    }

    fun validateMbti(mbti: String): String = when {
        !mbtiRegex.matches(mbti.uppercase()) -> "정확한 MBTI(E|I N|S F|T J|P)를 입력해주세요"
        else -> ""
    }
}
