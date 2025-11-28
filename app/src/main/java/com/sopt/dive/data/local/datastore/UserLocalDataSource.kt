package com.sopt.dive.data.local.datastore

interface UserLocalDataSource {
    suspend fun getUserId(): Long?
    suspend fun setUserId(userId: Long)
    suspend fun clearUserId()
    suspend fun setAutoLoginStatus(isChecked: Boolean)
    suspend fun getAutoLoginStatus(): Boolean
    suspend fun clearAutoLoginStatus()
}
