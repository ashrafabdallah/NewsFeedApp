package com.example.feedapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.feedapplication.data.model.Result

@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class NewsRoomDataBase:RoomDatabase() {
    abstract fun getNewsDao(): NewsDao

}