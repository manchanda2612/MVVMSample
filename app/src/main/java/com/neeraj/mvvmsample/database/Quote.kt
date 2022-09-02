package com.neeraj.mvvmsample.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote_table")
data class Quote(val author : String, val content : String) {

    @PrimaryKey(autoGenerate = true) var id : Int = 0
}

