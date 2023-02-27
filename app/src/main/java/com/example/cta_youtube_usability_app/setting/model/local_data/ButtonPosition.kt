package com.example.cta_youtube_usability_app.setting.model.local_data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cta_youtube_usability_app.setting.model.local_data.children_entity.CloseVideoButtonPosition
import com.example.cta_youtube_usability_app.setting.model.local_data.children_entity.FastForwardButtonPosition
import com.example.cta_youtube_usability_app.setting.model.local_data.children_entity.FullScreenButtonPosition
import com.example.cta_youtube_usability_app.setting.model.local_data.children_entity.PlayButtonPosition
import com.example.cta_youtube_usability_app.setting.model.local_data.children_entity.RewindButtonPosition

enum class Orientation {
    PORTRAIT,
    LANDSCAPE//横向き
}

/**
 * データクラス：ボタン位置テーブル
 */
@Entity(
    tableName = "button_position_table",
)
data class ButtonPositionTable(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "layout_name")
    val layoutName: String,        //レイアウト名
    @ColumnInfo(name = "screen_orientation")
    val orientation: Orientation, //スマホの向き
    @Embedded
    val fullScreenButton: FullScreenButtonPosition,
    @Embedded
    val playButton: PlayButtonPosition,
    @Embedded
    val closeVideoButton: CloseVideoButtonPosition,
    @Embedded
    val fastForwardButtonPosition: FastForwardButtonPosition,
    @Embedded
    val rewindButtonPosition: RewindButtonPosition
)
