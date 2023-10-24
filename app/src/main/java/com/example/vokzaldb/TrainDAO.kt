package com.example.vokzaldb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Dao
interface TrainDAO{
    @Query("SELECT * FROM traintable ")
    fun getAllTrains(): Flow<List<Train>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(train: Train)

    @Query("DELETE FROM traintable")
    suspend fun deleteAll()
}