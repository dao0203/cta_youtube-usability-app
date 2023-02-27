package com.example.cta_youtube_usability_app.setting.model.local_data.children_entity

import androidx.room.ColumnInfo

/**
 * データクラス：全画面ボタンテーブル
 */

data class FullScreenButton(
    @ColumnInfo(name = "fsb_x")
    val fullScreenButtonX: Int,      //全画面ボタンのX座標
    @ColumnInfo(name = "fsb_y")
    val fullScreenButtonY: Int       //全画面ボタンのY座標
)

