package com.example.w1839054_mad_cw_2

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Movies::class], version = 1)
abstract class moviesDatabase:RoomDatabase() {
    abstract fun moviesDAO(): moviesDAO
}