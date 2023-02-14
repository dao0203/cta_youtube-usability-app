package com.example.cta_youtube_usability_app.setting.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ButtonPositionDao {
    /**
     * ボタンポジションのデータを取得
     */
    @Query("SELECT *FROM button_positions")
    fun getAllButtonPosition(): List<ButtonPosition>

    /**
     * ボタンポジションのデータを追加
     */
    @Insert
    fun insertButtonPosition(vararg buttonPosition: ButtonPosition)

    /**
     * ボタンポジションを更新
     */
    @Query(
        "UPDATE button_positions SET" +
                "full_screen_x = :fullScreenX, " +   //全画面ボタンX座標
                "full_screen_y = :fullScreenY, " +   //全画面ボタンY座標
                "play_button_x = :playButtonX, " +   //再生ボタンのX座標
                "play_button_y = :playButtonY, " +   //再生ボタンのY座標
                "close_button_x = :closeButtonX, " + //動画閉じるボタンのX座標
                "close_button_y = :closeButtonY, " + //動画閉じるボタンのY座標
                "where id = :id"                     //更新したいレコードID
    )
    fun updateButtonPosition(
        fullScreenX: Int,
        fullScreenY: Int,
        playButtonX: Int,
        playButtonY: Int,
        closeButtonX: Int,
        closeButtonY: Int,
        id: Int
    )

    /**
     * ボタンポジションのデータを削除
     */
    @Query("DELETE FROM button_positions where id = :id")
    fun deleteButtonPosition(id: Int)

}