package com.example.cta_youtube_usability_app.setting

import android.content.Context
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
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

    //横レイアウトID GETメソッド（nullだったら初期値はYouTubeレイアウトID）
    @WorkerThread
    suspend fun getLandSelectedLayoutId(): LandSelectedLayoutId = LandSelectedLayoutId(
        dataStore.data.first()[LAND_SELECTED_LAYOUT_ID_KEY] ?: "youtube_layout"
    )

    //縦レイアウトID GETメソッド（nullだったら初期値はYouTubeレイアウトID）
    @WorkerThread
    suspend fun getPortSelectedLayoutId(): PortSelectedLayoutId = PortSelectedLayoutId(
        dataStore.data.first()[PORT_SELECTED_LAYOUT_ID_KEY] ?: "youtube_layout"
    )

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
