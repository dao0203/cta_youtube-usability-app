package com.example.cta_youtube_usability_app.setting.model.local_data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/** 画面の向きEnumクラス **/
enum class ScreenOrientation {
    VERTICAL,
    HORIZONTAL;
}

/** どちらの向きを指しているかを決めるメソッド **/
fun isOriented(screenOrientation: ScreenOrientation): Boolean {
    return when (screenOrientation) {
        ScreenOrientation.VERTICAL -> false
        ScreenOrientation.HORIZONTAL -> true

    }
}

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
    val screenOrientation: Boolean = isOriented(ScreenOrientation.VERTICAL) //スマホの向き　
)
