package com.example.cta_youtube_usability_app.setting.model.local_data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ButtonPositionDao {
    @Query("SELECT * FROM button_positions")
    fun getAllButtonPosition(): Flow<List<ButtonPosition>>

    @Insert
    fun insertButtonPosition(buttonPosition: ButtonPosition)

    @Update
    fun updateButtonPosition(buttonPosition: ButtonPosition)

    @Delete
    fun deleteButtonPosition(buttonPosition: ButtonPosition)
}
