package com.example.cta_youtube_usability_app.setting

import androidx.annotation.WorkerThread
import com.example.cta_youtube_usability_app.setting.model.local_data.ButtonPositionTable
import com.example.cta_youtube_usability_app.setting.model.local_data.ButtonPositionTableDao

class ButtonPositionRepository(private val buttonPositionDao: ButtonPositionTableDao) {

    /** ボタンポジション取得メソッド **/
    @WorkerThread
    suspend fun getAllButtonPositionData(): List<ButtonPositionTable> {
        return buttonPositionDao.getAllButtonPosition()
    }

    /** ボタンポジション追加メソッド **/
    @WorkerThread//WorkerThreadで動くように明示してあげる
    suspend fun insertButtonPosition(buttonPosition: ButtonPositionTable) {
        buttonPositionDao.insertButtonPosition(buttonPosition)
    }

    /** ボタンポジション更新メソッド **/
    @WorkerThread
    suspend fun updateButtonPosition(buttonPosition: ButtonPositionTable) {
        buttonPositionDao.updateButtonPosition(buttonPosition)
    }

    /** ボタンポジション削除メソッド **/
    @WorkerThread
    suspend fun deleteButtonPosition(buttonPosition: ButtonPositionTable) {
        buttonPositionDao.deleteButtonPosition(buttonPosition)
    }
}
