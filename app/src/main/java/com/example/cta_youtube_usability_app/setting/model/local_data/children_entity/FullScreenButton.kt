package com.example.cta_youtube_usability_app.setting.model.local_data.children_entity

import androidx.room.ColumnInfo

/**
 * データクラス：全画面ボタンテーブル
 */

data class FullScreenButtonPosition(
    @ColumnInfo(name = "fsbp_x")
    val fullScreenButtonPositionX: Int,      //全画面ボタンのX座標
    @ColumnInfo(name = "fsbp_y")
    val fullScreenButtonPositionY: Int       //全画面ボタンのY座標
)

