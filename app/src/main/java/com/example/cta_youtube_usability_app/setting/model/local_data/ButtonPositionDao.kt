package com.example.cta_youtube_usability_app.setting.model.local_data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ButtonPositionDao {

    @Query("SELECT * FROM button_position")
    suspend fun getAllButtonPosition(): List<ButtonPosition>

    @Insert
    suspend fun insertButtonPosition(buttonPosition: ButtonPosition)

    @Update
    suspend fun updateButtonPosition(buttonPosition: ButtonPosition)

    @Delete
    suspend fun deleteButtonPosition(buttonPosition: ButtonPosition)
}
