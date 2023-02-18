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
@Entity(tableName = "button_position",)
data class ButtonPosition(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "layout_name")
    val layoutName: String,        //レイアウト名
    @ColumnInfo(name = "screen_orientation")
    val screenOrientation: Boolean = isOriented(ScreenOrientation.VERTICAL), //スマホの向き
    @ColumnInfo(name = "bp_fsb_id")
    val fullScreenButtonId : Int,  //全画面ボタンID
    @ColumnInfo(name = "bp_pb_id")
    val playButtonId: Int,         //再生ボタンID
    @ColumnInfo(name = "bp_cvb_id")
    val closeButtonId: Int         //動画閉じるボタンID
)
