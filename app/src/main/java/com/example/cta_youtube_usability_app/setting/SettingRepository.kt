package com.example.cta_youtube_usability_app.setting

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "cta_youtube_usability_app_preferences")

class SettingRepository(private val dataStore: DataStore<Preferences>) {
    //ボタンポジションの参照キー
    private val buttonPositionKey = intPreferencesKey("button_position_key")

    //ボタンポジションのデータを読み取る
    val buttonPositionFlow: Flow<Int> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[buttonPositionKey]?: 0
        }
}
