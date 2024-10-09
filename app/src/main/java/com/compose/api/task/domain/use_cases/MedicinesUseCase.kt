package com.compose.api.task.domain.use_cases

import com.compose.api.task.domain.repository.MedicinesRepository
import javax.inject.Inject

class MedicinesUseCase @Inject constructor(private val medicinesRepository: MedicinesRepository) {

    fun getMedicines() = medicinesRepository.getMedicines()

    suspend fun clearLocalDb() = medicinesRepository.clearRoomDatabase()
}