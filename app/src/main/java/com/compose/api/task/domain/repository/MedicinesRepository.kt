package com.compose.api.task.domain.repository

import com.compose.api.task.domain.models.Medicine
import kotlinx.coroutines.flow.Flow

interface MedicinesRepository {
    fun getMedicines(): Flow<List<Medicine>>
    suspend fun clearRoomDatabase(): Int
}