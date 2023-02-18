package com.example.cta_youtube_usability_app.setting.model.local_data

import androidx.room.Embedded
import androidx.room.Relation
import com.example.cta_youtube_usability_app.setting.model.local_data.children_entity.CloseVideoButton
import com.example.cta_youtube_usability_app.setting.model.local_data.children_entity.FullScreenButton
import com.example.cta_youtube_usability_app.setting.model.local_data.children_entity.PlayButton

/**
 * データクラス：親エンティティと子エンティティの対応するインスタンスを保持
 */
data class ButtonPositionAndOtherButtons(
    @Embedded
    val buttonPosition: ButtonPosition,
    @Relation(
        parentColumn = "bp_fsb_id",
        entityColumn = "fsb_id"
    )
    val fullScreenButton: FullScreenButton,
    @Relation(
        parentColumn = "bp_pb_id",
        entityColumn = "pb_id"
    )
    val playButton: PlayButton,
    @Relation(
        parentColumn = "bp_cvb_id",
        entityColumn = "cvb_iv"
    )
    val closeVideoButton: CloseVideoButton
)
