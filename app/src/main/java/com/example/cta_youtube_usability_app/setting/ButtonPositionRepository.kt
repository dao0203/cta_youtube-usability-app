package com.example.cta_youtube_usability_app.setting

import androidx.annotation.WorkerThread
import com.example.cta_youtube_usability_app.setting.model.local_data.ButtonPositionEntity
import com.example.cta_youtube_usability_app.setting.model.local_data.ButtonPositionEntityDao

class ButtonPositionRepository(private val buttonPositionDao: ButtonPositionEntityDao) {

    /** ボタンポジション取得メソッド **/
    @WorkerThread
    suspend fun getAllButtonPositionData(): List<ButtonPositionEntity> {
        return buttonPositionDao.getAllButtonPosition()
    }

    /** ボタンポジション追加メソッド **/
    @WorkerThread//WorkerThreadで動くように明示してあげる
    suspend fun insertButtonPosition(buttonPositionEntity: ButtonPositionEntity) {
        buttonPositionDao.insertButtonPosition(buttonPositionEntity)
    }

    /** ボタンポジション更新メソッド **/
    @WorkerThread
    suspend fun updateButtonPosition(buttonPositionEntity: ButtonPositionEntity) {
        buttonPositionDao.updateButtonPosition(buttonPositionEntity)
    }

    /** ボタンポジション削除メソッド **/
    @WorkerThread
    suspend fun deleteButtonPosition(buttonPositionEntity: ButtonPositionEntity) {
        buttonPositionDao.deleteButtonPosition(buttonPositionEntity)
    }
}
