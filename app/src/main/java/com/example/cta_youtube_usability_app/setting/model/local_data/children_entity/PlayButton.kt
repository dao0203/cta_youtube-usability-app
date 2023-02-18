package com.example.cta_youtube_usability_app.setting.model.local_data.children_entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.cta_youtube_usability_app.setting.model.local_data.ButtonPosition

/**
 * データクラス：再生ボタンテーブル
 */
@Entity(
    tableName = "play_button",
    foreignKeys = [
        //playButtonIdを外部キーに設定
        ForeignKey(
            entity = ButtonPosition::class,
            parentColumns = arrayOf("bp_pb_id"),
            childColumns = arrayOf("pb_id"),
            onDelete = ForeignKey.CASCADE  //親も消えれば子も消える
        )
    ]
)
data class PlayButton(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pb_id")
    val playButtonId: Int = 0,     //再生ボタンID
    @ColumnInfo(name = "pb_x")
    val playButtonX: Int,          //再生ボタンのX座標
    @ColumnInfo(name = "pb_y")
    val playButtonY: Int           //再生ボタンのY座標
)
