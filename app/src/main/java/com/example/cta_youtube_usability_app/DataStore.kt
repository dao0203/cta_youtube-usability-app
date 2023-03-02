package com.example.cta_youtube_usability_app

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import java.util.prefs.Preferences

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "layout")

//横と縦のレイアウトIDを格納するKeyを宣言
val LAND_SELECTED_LAYOUT_ID = stringPreferencesKey("landscape_selected_layout_id")
val PORT_SELECTED_LAYOUT_ID = stringPreferencesKey("port_selected_layout_id")