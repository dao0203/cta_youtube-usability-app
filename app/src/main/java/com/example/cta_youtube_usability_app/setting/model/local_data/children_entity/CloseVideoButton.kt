package com.example.cta_youtube_usability_app.setting.model.local_data.children_entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * データクラス：動画を閉じるボタン
 */
@Entity(tableName = "close_video_button")
data class CloseVideoButton(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cvb_id")
    val CloseVideoButtonId: Int,  //動画閉じるボタンID
    @ColumnInfo(name = "cvb_x")
    val CloseVideoButtonX: Int,   //動画閉じるボタンのX座標
    @ColumnInfo(name = "cvb_y")
    val CloseVideoButtonY: Int    //動画閉じるボタンのY座標
)
