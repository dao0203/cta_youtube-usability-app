package com.example.cta_youtube_usability_app.setting.model.local_data.children_entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * データクラス：全画面ボタンテーブル
 */
@Entity(tableName = "full_screen_button")
data class FullScreenButton(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "fsb_id")
    val fullScreenButtonId: Int = 0, //全画面ボタンID
    @ColumnInfo(name = "fsb_x")
    val fullScreenButtonX: Int,      //全画面ボタンのX座標
    @ColumnInfo(name = "fsb_y")
    val fullScreenButtonY: Int       //全画面ボタンのY座標
)

