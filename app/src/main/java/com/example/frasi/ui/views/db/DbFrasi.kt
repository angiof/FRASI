package com.example.frasi.ui.views.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EntityFrase::class], version = 1, exportSchema = false)
abstract class DbFrasi : RoomDatabase() {

    abstract fun wordDao(): DaoFrasi

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: DbFrasi? = null

        fun getDatabase(context: Context): DbFrasi {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DbFrasi::class.java,
                    "frasi"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}