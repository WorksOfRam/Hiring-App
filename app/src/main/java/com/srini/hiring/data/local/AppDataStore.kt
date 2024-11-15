package com.srini.hiring.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("hiring_preferences")

object AppPreferences {
    val SHOW_WELCOME_DIALOG = booleanPreferencesKey("show_welcome_dialog")
}

suspend fun Context.setWelcomeDialogShown() {
    dataStore.edit { preferences ->
        preferences[AppPreferences.SHOW_WELCOME_DIALOG] = false
    }
}

fun Context.shouldShowWelcomeDialog(): Flow<Boolean> {
    return dataStore.data.map { preferences ->
        preferences[AppPreferences.SHOW_WELCOME_DIALOG] ?: true
    }
}
