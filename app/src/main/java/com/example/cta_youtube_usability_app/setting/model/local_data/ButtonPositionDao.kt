package com.example.cta_youtube_usability_app.setting.model.local_data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ButtonPositionDao {

    @Transaction
    @Query("SELECT * FROM button_position")
    suspend fun getAllButtonPosition(): List<ButtonPositionAndOtherButtons>

    @Transaction
    @Insert
    suspend fun insertButtonPosition(buttonPosition: ButtonPositionAndOtherButtons)

    @Transaction
    @Update
    suspend fun updateButtonPosition(buttonPosition: ButtonPositionAndOtherButtons)

    @Transaction
    @Delete
    suspend fun deleteButtonPosition(buttonPosition: ButtonPositionAndOtherButtons)
}
