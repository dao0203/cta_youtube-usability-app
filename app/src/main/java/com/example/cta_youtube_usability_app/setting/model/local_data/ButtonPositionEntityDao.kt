package com.example.cta_youtube_usability_app.setting.model.local_data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ButtonPositionEntityDao {

    @Query("SELECT * FROM button_position_entity")
    suspend fun getAllButtonPosition(): List<ButtonPositionEntity>

    @Insert
    suspend fun insertButtonPosition(buttonPosition: ButtonPositionEntity)

    @Update
    suspend fun updateButtonPosition(buttonPosition: ButtonPositionEntity)

    @Delete
    suspend fun deleteButtonPosition(buttonPosition: ButtonPositionEntity)
}
