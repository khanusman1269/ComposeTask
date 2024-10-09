package com.compose.api.task

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.compose.api.task.data.data_source.local.AppDatabase
import com.compose.api.task.data.data_source.local.MedicineDao
import com.compose.api.task.data.models.MedicineEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class MedicineLocalTestCase {

    private lateinit var database: AppDatabase
    private lateinit var medicineDao: MedicineDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
        medicineDao = database.medicineDao()
    }

    @After
    fun close() {
        database.close()
    }

    @Test
    fun testInsertMedicine() = runBlocking{
        val medicine = MedicineEntity(id = 1, name = "Aspirin", dose = "500mg", strength = "Regular", condition = "Asthma")
        medicineDao.insert(medicine)

        val fetchedMedicines = medicineDao.getAllMedicines().first()
        assertEquals(1, fetchedMedicines.size)
    }

    @Test
    fun testFetchMedicines() = runBlocking {
        val medicine = MedicineEntity(id = 1, name = "Aspirin", dose = "500mg", strength = "Regular", condition = "Diabetes")
        medicineDao.insert(medicine)

        val fetchedMedicines = medicineDao.getAllMedicines()
        val medicinesList = fetchedMedicines.first()

        assertEquals(1, medicinesList.size)
        assertEquals(medicine.name, medicinesList[0].name)
        assertEquals(medicine.dose, medicinesList[0].dose)
        assertEquals(medicine.strength, medicinesList[0].strength)
    }

    @Test
    fun testFetchMedicinesEmpty() = runBlocking {
        val fetchedMedicines = medicineDao.getAllMedicines()

        val medicineList = fetchedMedicines.first()
        assertEquals(0, medicineList.size)
    }
}