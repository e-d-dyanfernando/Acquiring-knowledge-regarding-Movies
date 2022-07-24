package com.example.w1839054_mad_cw_2

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movies (
    @PrimaryKey val Title : String,
    @ColumnInfo(name = "Year") val Year : String,
    @ColumnInfo(name = "Rated") val Rated : String,
    @ColumnInfo(name = "Released") val Released : String,
    @ColumnInfo(name = "Runtime") val Runtime : String,
    @ColumnInfo(name = "Genre") val Genre : String,
    @ColumnInfo(name = "Director") val Director : String,
    @ColumnInfo(name = "Writer") val Writer : String,
    @ColumnInfo(name = "Actors") val Actors : String,
    @ColumnInfo(name = "Plot") val Plot : String,
)