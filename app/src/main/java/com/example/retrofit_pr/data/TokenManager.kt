package com.example.retrofit_pr.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

class TokenManager(val context: Context) {
    companion object {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
    }

    suspend fun saveToken(token: String) {
        context.dataStore.updateData {
            it.toMutablePreferences().also { preferences ->
                preferences[ACCESS_TOKEN] = token
            }
        }
    }

    fun tokenFlow(): Flow<String?> =
        context.dataStore.data.map { preferences -> preferences[ACCESS_TOKEN] }
}