package com.example.cta_youtube_usability_app.setting.model.local_data.children_entity

import androidx.room.ColumnInfo

/**
 * データクラス：動画を閉じるボタン
 */

data class CloseVideoButtonPosition(
    @ColumnInfo(name = "cvbp_x")
    val closeVideoButtonPositionX: Int,   //動画閉じるボタンのX座標
    @ColumnInfo(name = "cvbp_y")
    val closeVideoButtonPositionY: Int    //動画閉じるボタンのY座標
)
