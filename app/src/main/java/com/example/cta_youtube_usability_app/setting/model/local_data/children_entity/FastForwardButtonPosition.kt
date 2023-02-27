package com.example.cta_youtube_usability_app.setting.model.local_data.children_entity

import androidx.room.ColumnInfo

/**
 * データクラス：早送りボタンテーブル
 */
data class FastForwardButtonPosition(
    @ColumnInfo(name = "ffbp_x")
    val fastForwardButtonPositionX: Int,
    @ColumnInfo(name = "ffbp_y")
    val fastForwardButtonPositionY: Int
)
