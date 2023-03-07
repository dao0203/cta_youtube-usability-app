package com.example.cta_youtube_usability_app.setting

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

//DataStoreをシングルトンとして扱えるようにする
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "layout")

//シングルトンとして扱えるようにするため、拡張関数を引数として持ってくる
class SelectedLayoutIdRepository(private val context: Context) {

    //dataStoreのデータのやり取りを行うために宣言
    private val dataStore = context.dataStore

    //横と縦のレイアウトIDを格納するKeyを宣言
    private val LAND_SELECTED_LAYOUT_ID_KEY = stringPreferencesKey("land_selected_layout_id")
    private val PORT_SELECTED_LAYOUT_ID_KEY = stringPreferencesKey("port_selected_layout_id")

    //リアルタイムで値を取得するようにFlow型で取得するようにした
    // 横レイアウトIDをリアルタイムで受け取る変数
    val landSelectedLayoutIdFlow: Flow<LandSelectedLayoutId> = dataStore.data
        .map { value: Preferences ->
            LandSelectedLayoutId(value[LAND_SELECTED_LAYOUT_ID_KEY] ?: LayoutId.YOUTUBE.name)
        }

    // 縦レイアウトIDをリアルタイムで受け取る変数
    val portSelectedLayoutId: Flow<PortSelectedLayoutId> = dataStore.data
        .map { value: Preferences ->
            PortSelectedLayoutId(value[PORT_SELECTED_LAYOUT_ID_KEY] ?: LayoutId.YOUTUBE.name)
        }

    //横レイアウトID updateメソッド
    @WorkerThread
    suspend fun updateLandSelectedLayoutId(landLayoutId: LandSelectedLayoutId) {
        withContext(Dispatchers.IO) {
            dataStore.edit { layout ->
                layout[LAND_SELECTED_LAYOUT_ID_KEY] = landLayoutId.landSelectedLayoutId
            }
        }
    }

    //縦レイアウトID updateメソッド
    @WorkerThread
    suspend fun updatePortSelectedLayoutId(portSelectedLayoutId: PortSelectedLayoutId) {
        withContext(Dispatchers.IO) {
            dataStore.edit { layout ->
                layout[PORT_SELECTED_LAYOUT_ID_KEY] = portSelectedLayoutId.portSelectedLayoutId
            }
        }
    }
}
