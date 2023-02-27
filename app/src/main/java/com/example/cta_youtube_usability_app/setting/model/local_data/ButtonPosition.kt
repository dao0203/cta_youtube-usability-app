package com.example.cta_youtube_usability_app.setting.model.local_data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cta_youtube_usability_app.setting.model.local_data.children_entity.CloseVideoButton
import com.example.cta_youtube_usability_app.setting.model.local_data.children_entity.FullScreenButton
import com.example.cta_youtube_usability_app.setting.model.local_data.children_entity.PlayButton

enum class Orientation {
    PORTRAIT,
    LANDSCAPE//横向き
}

/**
 * データクラス：ボタン位置テーブル
 */
@Entity(
    tableName = "button_position",
)
data class ButtonPosition(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "layout_name")
    val layoutName: String,        //レイアウト名
    @ColumnInfo(name = "screen_orientation")
    val orientation: Orientation, //スマホの向き
    @Embedded
    val fullScreenButton: FullScreenButton,
    @Embedded
    val playButton: PlayButton,
    @Embedded
    val closeVideoButton: CloseVideoButton
)
