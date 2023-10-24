package com.example.vokzaldb

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TrainRepository(private val trainDAO: TrainDAO) {
    val allTrains: Flow<List<Train>> = trainDAO.getAllTrains()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(train: Train) {
        trainDAO.insert(train)
    }

}