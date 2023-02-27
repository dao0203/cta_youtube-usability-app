package com.example.cta_youtube_usability_app.setting

import androidx.annotation.WorkerThread
import com.example.cta_youtube_usability_app.setting.model.local_data.ButtonPositionDao

import com.example.cta_youtube_usability_app.setting.model.local_data.ButtonPositionTable
class ButtonPositionRepository(private val buttonPositionDao: ButtonPositionDao) {

    /** ボタンポジション取得メソッド **/
    @WorkerThread
    suspend fun getAllButtonPositionData(): List<ButtonPosition> {
        return buttonPositionDao.getAllButtonPosition()
    }

    /** ボタンポジション追加メソッド **/
    @WorkerThread//WorkerThreadで動くように明示してあげる
    suspend fun insertButtonPosition(buttonPosition: ButtonPosition) {
        buttonPositionDao.insertButtonPosition(buttonPosition)
    }

    /** ボタンポジション更新メソッド **/
    @WorkerThread
    suspend fun updateButtonPosition(buttonPosition: ButtonPosition) {
        buttonPositionDao.updateButtonPosition(buttonPosition)
    }

    /** ボタンポジション削除メソッド **/
    @WorkerThread
    suspend fun deleteButtonPosition(buttonPosition: ButtonPosition) {
        buttonPositionDao.deleteButtonPosition(buttonPosition)
    }
}
