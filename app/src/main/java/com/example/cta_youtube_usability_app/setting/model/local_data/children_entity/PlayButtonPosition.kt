package com.example.cta_youtube_usability_app.setting.model.local_data.children_entity

import androidx.room.ColumnInfo

/**
 * データクラス：再生ボタンテーブル
 */

data class PlayButtonPosition(
    @ColumnInfo(name = "pbp_x")
    val playButtonPositionX: Int,  //再生ボタンのX座標
    @ColumnInfo(name = "pbp_y")
    val playButtonPositionY: Int   //再生ボタンのY座標
)
