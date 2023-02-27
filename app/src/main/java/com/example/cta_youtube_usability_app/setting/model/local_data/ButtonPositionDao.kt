package com.example.cta_youtube_usability_app.setting.model.local_data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ButtonPositionDao {

    @Query("SELECT * FROM button_position_table")
    suspend fun getAllButtonPosition(): List<ButtonPositionTable>

    @Insert
    suspend fun insertButtonPosition(buttonPosition: ButtonPositionTable)

    @Update
    suspend fun updateButtonPosition(buttonPosition: ButtonPositionTable)

    @Delete
    suspend fun deleteButtonPosition(buttonPosition: ButtonPositionTable)
}
