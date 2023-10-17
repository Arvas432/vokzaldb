package com.example.vokzaldb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "traintable")
data class Train(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "origin") val origin: String,
    @ColumnInfo(name = "destination") val destination: String,
    @ColumnInfo(name = "time") val time: String,
    @ColumnInfo(name = "way") val way: Int
)
