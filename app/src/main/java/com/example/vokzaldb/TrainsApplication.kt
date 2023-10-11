package com.example.vokzaldb

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TrainsApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { TrainsDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { TrainRepository(database.trainDao()) }
}