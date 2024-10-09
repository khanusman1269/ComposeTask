package com.compose.api.task.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicines")
data class MedicineEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "dose") val dose: String,
    @ColumnInfo(name = "strength") val strength: String,
    @ColumnInfo(name = "condition") val condition: String
)