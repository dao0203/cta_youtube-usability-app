package com.example.cta_youtube_usability_app.setting.model.local_data.children_entity

import androidx.room.ColumnInfo

/**
 * データクラス：再生ボタンテーブル
 */

data class PlayButton(

    @ColumnInfo(name = "pb_x")
    val playButtonX: Int,          //再生ボタンのX座標
    @ColumnInfo(name = "pb_y")
    val playButtonY: Int           //再生ボタンのY座標
)
