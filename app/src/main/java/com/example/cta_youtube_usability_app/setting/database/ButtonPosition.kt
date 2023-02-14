package com.example.cta_youtube_usability_app.setting.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * データクラス：ボタン位置テーブル
 */
@Entity(tableName = "button_positions")
data class ButtonPosition(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "layout_name")
    val layoutName: String,        //レイアウト名
    @ColumnInfo(name = "full_screen_x")
    val fullScreenX: Int,          //全画面ボタンのX座標

    @ColumnInfo(name = "full_screen_y")
    val fullScreenY: Int,          //全画面ボタンのY座標

    @ColumnInfo(name = "play_button_x")
    val playButtonX: Int,          //再生ボタンのX座標

    @ColumnInfo(name = "play_button_y")
    val playButtonY: Int,          //再生ボタンのY座標

    @ColumnInfo(name = "close_button_x")
    val closeButtonX: Int,         //動画閉じるボタンのX座標

    @ColumnInfo(name = "close_button_y")
    val closeButtonY: Int,         //動画閉じるボタンのY座標

    @ColumnInfo(name = "screen_orientation")
    val screenOrientation: Boolean //スマホの向き　false->横　true->縦
)
