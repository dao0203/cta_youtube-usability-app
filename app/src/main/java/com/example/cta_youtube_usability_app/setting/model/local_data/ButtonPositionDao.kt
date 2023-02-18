package com.example.cta_youtube_usability_app.setting.model.local_data

import androidx.room.*

@Dao
interface ButtonPositionDao {

    @Transaction
    @Query("SELECT * FROM button_position")
    suspend fun getAllButtonPosition(): List<ButtonPosition>

    @Transaction
    @Insert
    suspend fun insertButtonPosition(buttonPosition: ButtonPosition)

    @Transaction
    @Update
    suspend fun updateButtonPosition(buttonPosition: ButtonPosition)

    @Transaction
    @Delete
    suspend fun deleteButtonPosition(buttonPosition: ButtonPosition)
}
