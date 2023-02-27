package com.example.cta_youtube_usability_app.setting

import androidx.annotation.WorkerThread
import com.example.cta_youtube_usability_app.setting.model.local_data.ButtonPosition
import com.example.cta_youtube_usability_app.setting.model.local_data.ButtonPositionDao
import kotlinx.coroutines.flow.Flow

class ButtonPositionRepository(private val buttonPositionDao: ButtonPositionDao) {

    /** ボタンポジション取得メソッド **/
    @WorkerThread
    suspend fun getAllButtonPositionData(): List<ButtonPosition> {
        return buttonPositionDao.getAllButtonPosition()
    }

    /** ボタンポジション追加メソッド **/
    //わざわざ、非同期関数じゃなくても良い、という警告を無視してくれる
    @Suppress("RedundantSuspendModifier")
    @WorkerThread//WorkerThreadで動くように明示してあげる
    suspend fun insertButtonPosition(buttonPosition: ButtonPosition) {
        buttonPositionDao.insertButtonPosition(buttonPosition)
    }

    /** ボタンポジション更新メソッド **/
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateButtonPosition(buttonPosition: ButtonPosition) {
        buttonPositionDao.updateButtonPosition(buttonPosition)
    }

    /** ボタンポジション削除メソッド **/
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteButtonPosition(buttonPosition: ButtonPosition) {
        buttonPositionDao.deleteButtonPosition(buttonPosition)
    }
}
