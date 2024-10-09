package com.compose.api.task.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.compose.api.task.data.models.MedicineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(medicine: MedicineEntity)

    @Query("SELECT * FROM medicines")
    fun getAllMedicines(): Flow<List<MedicineEntity>>

    @Query("DELETE FROM medicines")
    suspend fun nukeTable(): Int

}