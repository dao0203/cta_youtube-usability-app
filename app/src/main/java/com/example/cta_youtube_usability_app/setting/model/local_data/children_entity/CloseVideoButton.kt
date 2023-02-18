package com.example.cta_youtube_usability_app.setting.model.local_data.children_entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.cta_youtube_usability_app.setting.model.local_data.ButtonPosition

/**
 * データクラス：動画を閉じるボタン
 */
@Entity(
    tableName = "close_video_button",
    foreignKeys = [
        ForeignKey(
            //closeVideoButtonIdを外部キーに設定
            entity = ButtonPosition::class,
            parentColumns = arrayOf("bp_cvb_id"),
            childColumns = arrayOf("cvb_id"),
            onDelete = ForeignKey.CASCADE  // 親も消えれば子も消えr
        )
    ]
)
data class CloseVideoButton(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cvb_id")
    val closeVideoButtonId: Int,  //動画閉じるボタンID
    @ColumnInfo(name = "cvb_x")
    val closeVideoButtonX: Int,   //動画閉じるボタンのX座標
    @ColumnInfo(name = "cvb_y")
    val closeVideoButtonY: Int    //動画閉じるボタンのY座標
)
