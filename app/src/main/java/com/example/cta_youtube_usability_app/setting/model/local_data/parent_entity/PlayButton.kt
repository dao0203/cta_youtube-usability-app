package com.example.cta_youtube_usability_app.setting.model.local_data.parent_entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * データクラス：再生ボタンテーブル
 */
@Entity(tableName = "play_button")
data class PlayButton(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pb_id")
    val playButtonId: Int = 0,     //再生ボタンID
    @ColumnInfo(name = "pb_x")
    val playButtonX: Int,          //再生ボタンのX座標
    @ColumnInfo(name = "pb_y")
    val playButtonY: Int           //再生ボタンのY座標
)
