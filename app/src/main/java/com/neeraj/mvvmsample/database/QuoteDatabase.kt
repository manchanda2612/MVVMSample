package com.neeraj.mvvmsample.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Quote::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {

    abstract val quoteDao : QuoteDao

    companion object {

        @Volatile
        private var INSTANCE : QuoteDatabase? = null

        fun getDataInstance(context: Context) : QuoteDatabase {
            synchronized(this) {

                if(null == INSTANCE) {
                    INSTANCE = Room
                        .databaseBuilder(context, QuoteDatabase::class.java, "quote_db")
                        .build()
                }

            }
            return INSTANCE!!
        }

    }

}