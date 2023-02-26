package com.example.cta_youtube_usability_app.setting.model.local_data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cta_youtube_usability_app.setting.model.local_data.children_entity.CloseVideoButton
import com.example.cta_youtube_usability_app.setting.model.local_data.children_entity.FullScreenButton
import com.example.cta_youtube_usability_app.setting.model.local_data.children_entity.PlayButton



/**
 * データクラス：ボタン位置テーブル
 */
@Entity(
    tableName = "button_position",
//    foreignKeys = [
//        ForeignKey(
//            entity = FullScreenButton::class,
//            parentColumns = arrayOf("fsb_id"),
//            childColumns = arrayOf("bp_fsb_id"),
//            onDelete = ForeignKey.CASCADE  //親も消えると子も消える
//        ),
//        ForeignKey(
//            entity = PlayButton::class,
//            parentColumns = arrayOf("pb_id"),
//            childColumns = arrayOf("bp_pb_id"),
//            onDelete = ForeignKey.CASCADE  //親も消えれば子も消える
//        ),
//        ForeignKey(
//            //closeVideoButtonIdを外部キーに設定
//            entity = CloseVideoButton::class,
//            parentColumns = arrayOf("cvb_id"),
//            childColumns = arrayOf("bp_cvb_id"),
//            onDelete = ForeignKey.CASCADE  // 親も消えれば子も消えr
//        )
//
//    ]
)
data class ButtonPosition(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "layout_name")
    val layoutName: String,        //レイアウト名
    @ColumnInfo(name = "screen_orientation")
    val horizontal: Boolean = true, //スマホの向き
    @Embedded
    val fullScreenButton: FullScreenButton,
    @Embedded
    val playButton: PlayButton,
    @Embedded
    val closeVideoButton: CloseVideoButton
//    @ColumnInfo(name = "bp_fsb_id")
//    val fullScreenButtonId: Int,  //全画面ボタンID
//    @ColumnInfo(name = "bp_pb_id")
//    val playButtonId: Int,         //再生ボタンID
//    @ColumnInfo(name = "bp_cvb_id")
//    val closeButtonId: Int         //動画閉じるボタンID
)
