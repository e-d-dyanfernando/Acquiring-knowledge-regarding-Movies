package com.example.w1839054_mad_cw_2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface moviesDAO {

        @Query("SELECT * FROM movies")
        fun getAll(): List<Movies>

        @Query("SELECT * FROM movies WHERE Title IN (:moviesIds)")
        fun loadMovieData(moviesIds: String): List<Movies>

        @Query("SELECT * FROM movies WHERE Actors LIKE '%' || :actor || '%' ")
        fun loadActorData(actor: String): List<Movies>

        @Insert
        fun insertAll(vararg movies: Movies)

        @Delete
        fun delete(movies: Movies)
    }