package com.sopt.dive.data.local.datasourceimpl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import com.sopt.dive.data.local.datastore.UserLocalDataSource
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : UserLocalDataSource {
    override suspend fun getUserId(): Long? = dataStore.data.map { prefs ->
        prefs[USER_ID_KEY]
    }.firstOrNull()

    override suspend fun setUserId(userId: Long) {
        dataStore.edit { prefs ->
            prefs[USER_ID_KEY] = userId
        }
    }

    override suspend fun clearUserId() {
        dataStore.edit { prefs ->
            prefs.remove(USER_ID_KEY)
        }
    }

    override suspend fun setAutoLoginStatus(isChecked: Boolean) {
        dataStore.edit { prefs ->
            prefs[AUTO_LOGIN_KEY] = isChecked
        }
    }

    override suspend fun getAutoLoginStatus(): Boolean = dataStore.data.map { prefs ->
        prefs[AUTO_LOGIN_KEY]
    }.firstOrNull() ?: false

    override suspend fun clearAutoLoginStatus() {
        dataStore.edit { prefs ->
            prefs.remove(AUTO_LOGIN_KEY)
        }
    }

    companion object {
        private val USER_ID_KEY = longPreferencesKey("user_id")
        private val AUTO_LOGIN_KEY = booleanPreferencesKey("auto_login")
    }
}
