package com.sopt.dive.core.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class UserDataStore(private val context: Context) {
    suspend fun getUserData(): UserData? = context.dataStore.data.map { prefs ->
        val userId = prefs[USER_ID_KEY]
        val password = prefs[PASSWORD_KEY]
        val nickname = prefs[NICKNAME_KEY]
        val mbti = prefs[MBTI_KEY]

        if (userId != null && password != null && nickname != null && mbti != null) {
            UserData(userId, password, nickname, mbti)
        } else {
            null
        }
    }.firstOrNull()

    suspend fun setUserData(userData: UserData) = context.dataStore.edit { prefs ->
        prefs[USER_ID_KEY] = userData.userId
        prefs[PASSWORD_KEY] = userData.password
        prefs[NICKNAME_KEY] = userData.nickname
        prefs[MBTI_KEY] = userData.mbti
    }

    suspend fun clearUserData() = context.dataStore.edit { prefs ->
        prefs.remove(USER_ID_KEY)
        prefs.remove(PASSWORD_KEY)
        prefs.remove(NICKNAME_KEY)
        prefs.remove(MBTI_KEY)
    }

    suspend fun setAutoLoginStatus(isChecked: Boolean) = context.dataStore.edit { prefs ->
        prefs[AUTO_LOGIN_KEY] = isChecked
    }

    suspend fun getAutoLoginStatus(): Boolean = context.dataStore.data.map { prefs ->
        prefs[AUTO_LOGIN_KEY]
    }.firstOrNull() ?: false

    suspend fun clearAutoLoginStatus() = context.dataStore.edit { prefs ->
        prefs.remove(AUTO_LOGIN_KEY)
    }

    companion object {
        private val USER_ID_KEY = stringPreferencesKey("user_id")
        private val PASSWORD_KEY = stringPreferencesKey("password")
        private val NICKNAME_KEY = stringPreferencesKey("nickname")
        private val MBTI_KEY = stringPreferencesKey("mbti")
        private val AUTO_LOGIN_KEY = booleanPreferencesKey("auto_login")
    }
}

data class UserData(
    val userId: String,
    val password: String,
    val nickname: String,
    val mbti: String
)