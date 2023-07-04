package com.fajar.myapplication.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Yugi::class], version = 1)
abstract class YugiDatabase: RoomDatabase() {

    abstract fun favoriteDao(): YugiDao

    companion object {
        @Volatile
        private var INSTANCE: YugiDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): YugiDatabase {
            if (INSTANCE == null) {
                synchronized(YugiDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        YugiDatabase::class.java,
                        "yugi_db"
                    ).build()
                }
            }
            return INSTANCE as YugiDatabase
        }
    }
}