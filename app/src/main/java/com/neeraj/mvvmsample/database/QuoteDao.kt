package com.neeraj.mvvmsample.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quote_table")
    suspend fun getQuotes() : List<Quote>

    @Insert
    suspend fun insertQuote(quote: Quote)
}