package com.example.cta_youtube_usability_app.setting.model.local_data.children_entity

import androidx.room.ColumnInfo

data class RewindButtonPosition(
    @ColumnInfo(name = "rbp_x")
    val rewindButtonPositionX: Int,
    @ColumnInfo(name = "rbp_y")
    val rewindButtonPositionY: Int
)
