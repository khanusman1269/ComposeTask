package com.compose.api.task.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.compose.api.task.data.models.MedicineEntity

@Database(entities = [MedicineEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun medicineDao(): MedicineDao
}