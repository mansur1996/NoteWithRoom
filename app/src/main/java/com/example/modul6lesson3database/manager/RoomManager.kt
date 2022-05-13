package com.example.modul6lesson3database.manager

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.modul6lesson3database.model.Note
import com.example.modul6lesson3database.database.UserDao

@Database(entities = [Note::class], version = 1)
abstract class RoomManager : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: RoomManager? = null

        @Synchronized
        fun getDatabase(context: Context): RoomManager {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    RoomManager::class.java,
                    "app_db"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
    }
}