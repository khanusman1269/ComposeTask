package com.compose.api.task.data.repository

import com.compose.api.task.common.NetworkUtils
import com.compose.api.task.data.data_source.local.MedicineDao
import com.compose.api.task.data.data_source.remote.ApiService
import com.compose.api.task.data.mapper.MedicineMapper.toMedicine
import com.compose.api.task.data.mapper.MedicineMapper.toMedicineEntity
import com.compose.api.task.domain.models.Medicine
import com.compose.api.task.domain.repository.MedicinesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class MedicinesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val medicineDao: MedicineDao,
    private val networkUtils: NetworkUtils
) : MedicinesRepository {
    override fun getMedicines(): Flow<List<Medicine>> = flow {
        val localMedicines = medicineDao.getAllMedicines()
        localMedicines.collect { medicines ->
            if (medicines.isNotEmpty()) {
                emit(medicines.map { it.toMedicine() })
            } else {
                if (networkUtils.isConnected()) {
                    try {
                        val response = apiService.getMedicines()
                        if (response.isSuccessful) {
                            val remoteMedicines = response.body() ?: emptyList()
                            emit(remoteMedicines.map { it.toMedicine() })
                            medicineDao.nukeTable()
                            remoteMedicines.forEach { medicine ->
                                medicineDao.insert(medicine.toMedicineEntity())
                            }
                        } else {
                            throw Exception("Error fetching medicines from API \n ${response.message()}")
                        }
                    } catch (e: Exception) {
                        throw e
                    }
                } else {
                    throw IOException("Please connect to the internet")
                }
            }
        }
    }.flowOn(Dispatchers.IO)


    override suspend fun clearRoomDatabase(): Int {
        return medicineDao.nukeTable()
    }
}