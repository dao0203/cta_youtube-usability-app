package com.example.cta_youtube_usability_app.setting.model.local_data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ButtonPositionTable::class], version = 1, exportSchema = false)
abstract class ButtonPositionDatabase : RoomDatabase() {
    abstract fun buttonPositionDao(): ButtonPositionDao

    companion object {
        @Volatile
        private var INSTANCE: ButtonPositionDatabase? = null

        fun getDatabase(context: Context): ButtonPositionDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ButtonPositionDatabase::class.java,
                    "button_position_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}
