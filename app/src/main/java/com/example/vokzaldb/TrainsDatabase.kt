package com.example.vokzaldb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Train::class), version = 1, exportSchema = false)
public abstract class TrainsDatabase : RoomDatabase(){
    abstract fun trainDao(): TrainDAO
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: TrainsDatabase? = null

        fun getDatabase( context: Context,
                         scope: CoroutineScope
        ): TrainsDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TrainsDatabase::class.java,
                    "traindatabase"
                )
                    .addCallback(TrainDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
    private class TrainDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.trainDao())
                }
            }
        }

        suspend fun populateDatabase(trainDAO: TrainDAO) {
            trainDAO.deleteAll()
            trainDAO.insert(Train(1,"Москва", "Можайск", "15:40",1))
            trainDAO.insert(Train(1,"Москва", "Подольск", "15:50",3))
            trainDAO.insert(Train(1,"Подольск", "Москва", "16:10",6))
            trainDAO.insert(Train(1,"Балашиха", "Москва", "16:23",4))
            trainDAO.insert(Train(1,"Зеленоград", "Москва", "16:34",3))
            trainDAO.insert(Train(1,"Одинцово", "Можайск", "16:40",6))
            trainDAO.insert(Train(1,"Мытищи", "Одинцово", "17:20",2))
            trainDAO.insert(Train(1,"Лобня", "Москва", "17:55",1))
        }
    }

}