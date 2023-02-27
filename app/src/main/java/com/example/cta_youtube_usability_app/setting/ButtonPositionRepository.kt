package com.example.cta_youtube_usability_app.setting

import androidx.annotation.WorkerThread
import com.example.cta_youtube_usability_app.setting.model.local_data.ButtonPositionEntity
import com.example.cta_youtube_usability_app.setting.model.local_data.ButtonPositionTableDao

class ButtonPositionRepository(private val buttonPositionDao: ButtonPositionTableDao) {

    /** ボタンポジション取得メソッド **/
    @WorkerThread
    suspend fun getAllButtonPositionData(): List<ButtonPositionEntity> {
        return buttonPositionDao.getAllButtonPosition()
    }

    /** ボタンポジション追加メソッド **/
    @WorkerThread//WorkerThreadで動くように明示してあげる
    suspend fun insertButtonPosition(buttonPosition: ButtonPositionEntity) {
        buttonPositionDao.insertButtonPosition(buttonPosition)
    }

    /** ボタンポジション更新メソッド **/
    @WorkerThread
    suspend fun updateButtonPosition(buttonPosition: ButtonPositionEntity) {
        buttonPositionDao.updateButtonPosition(buttonPosition)
    }

    /** ボタンポジション削除メソッド **/
    @WorkerThread
    suspend fun deleteButtonPosition(buttonPosition: ButtonPositionEntity) {
        buttonPositionDao.deleteButtonPosition(buttonPosition)
    }
}
