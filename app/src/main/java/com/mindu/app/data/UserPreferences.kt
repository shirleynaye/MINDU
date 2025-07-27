package com.mindu.app.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Crear instancia de DataStore
val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        private val USERNAME_KEY = preferencesKey<String>("username")
        private val PIN_KEY = preferencesKey<String>("pin")
    }

    // Guardar usuario y PIN
    suspend fun saveUser(username: String, pin: String) {
        context.dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = username
            preferences[PIN_KEY] = pin
        }
    }

    // Obtener nombre de usuario
    fun getUsername(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[USERNAME_KEY] ?: ""
        }
    }

    // Obtener PIN
    fun getPin(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[PIN_KEY] ?: ""
        }
    }
}



