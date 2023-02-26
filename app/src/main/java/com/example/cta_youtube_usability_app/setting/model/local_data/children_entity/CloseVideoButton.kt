package com.example.cta_youtube_usability_app.setting.model.local_data.children_entity

import androidx.room.ColumnInfo

/**
 * データクラス：動画を閉じるボタン
 */

data class CloseVideoButton(
    @ColumnInfo(name = "cvb_x")
    val closeVideoButtonX: Int,   //動画閉じるボタンのX座標
    @ColumnInfo(name = "cvb_y")
    val closeVideoButtonY: Int    //動画閉じるボタンのY座標
)
