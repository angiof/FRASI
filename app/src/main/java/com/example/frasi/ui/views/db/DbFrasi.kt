package com.example.frasi.ui.views.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(EntityFrase::class), version = 1, exportSchema = false)
public abstract class dbFrasi : RoomDatabase() {

    abstract fun wordDao(): DaoFrasi

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: dbFrasi? = null

        fun getDatabase(context: Context): dbFrasi {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    dbFrasi::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}