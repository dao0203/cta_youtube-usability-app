package com.example.cta_youtube_usability_app

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

//DataStoreをシングルトンとして扱えるようにする

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "layout")

//横と縦のレイアウトIDを格納するKeyを宣言
val LAND_SELECTED_LAYOUT_ID_KEY = stringPreferencesKey("land_selected_layout_id")
val PORT_SELECTED_LAYOUT_ID_KEY = stringPreferencesKey("port_selected_layout_id")