package com.example.timej.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    fun getAccessToken(): Flow<String?> = dataStore.data
        .map {
            it[ACCESS_TOKEN_KEY] ?: ""
        }

    suspend fun saveAccessToken(accessToken: String) {
        dataStore.edit {
            it[ACCESS_TOKEN_KEY] = accessToken
        }
    }

    fun getEmail(): Flow<String> = dataStore.data
        .map {
            it[EMAIL_KEY] ?: ""
        }

    suspend fun saveEmail(email: String) {
        dataStore.edit {
            it[EMAIL_KEY] = email
        }
    }

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")
        private val SHARED_PREFS = stringPreferencesKey("APP_SHARED_PREFS")
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN_KEY = stringPreferencesKey("access_token")
        private val EMAIL_KEY = stringPreferencesKey("email")
    }
}
