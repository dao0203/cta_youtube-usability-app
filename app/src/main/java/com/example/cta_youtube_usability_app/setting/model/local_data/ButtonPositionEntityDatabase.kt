package com.example.cta_youtube_usability_app.setting.model.local_data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ButtonPositionEntity::class], version = 1, exportSchema = false)
abstract class ButtonPositionEntityDatabase : RoomDatabase() {
    abstract fun buttonPositionDao(): ButtonPositionEntityDao

    companion object {
        @Volatile
        private var INSTANCE: ButtonPositionEntityDatabase? = null

        fun getDatabase(context: Context): ButtonPositionEntityDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ButtonPositionEntityDatabase::class.java,
                    "button_position_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}
