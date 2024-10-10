package com.compose.api.task.data.mapper

import android.util.Log
import com.compose.api.task.data.models.ApiResponse
import com.compose.api.task.data.models.Drug
import com.compose.api.task.data.models.MedicineEntity
import com.compose.api.task.domain.models.Medicine

object MedicineMapper {

    fun ApiResponse.toMedicineEntityList(): List<MedicineEntity> {
        val medicines = mutableListOf<MedicineEntity>()

        fun addDrugs(
            drugs: List<Drug>?,
            condition: String
        ) {
            drugs?.forEach { drug ->
                medicines.add(
                    MedicineEntity(
                        name = drug.name,
                        dose = drug.dose.ifEmpty { "N/A" },
                        strength = drug.strength,
                        condition = condition
                    )
                )
            }
        }
        problems.forEach { problem ->
            problem.diabetes?.forEach { condtionDetails ->
                condtionDetails.medications?.forEach { medication ->
                    medication.medicationsClasses.forEach { medicationClass ->
                        Log.e("TAG", "toMedicineEntityList:1 ${medicationClass.className?.size}", )
                        Log.e("TAG", "toMedicineEntityList:2 ${medicationClass.className2?.size}", )
                        medicationClass.className?.forEach{classDetail ->
                            addDrugs(classDetail.associatedDrug, "Diabetes")
                            addDrugs(classDetail.associatedDrug2, "Diabetes")
                        }
                        medicationClass.className2?.forEach{classDetail ->
                            addDrugs(classDetail.associatedDrug, "Diabetes")
                            addDrugs(classDetail.associatedDrug2, "Diabetes")
                        }
                    }
                }
            }
            problem.asthma?.forEach { condtionDetails ->
                condtionDetails.medications?.forEach { medication ->
                    medication.medicationsClasses.forEach { medicationClass ->
                        medicationClass.className?.forEach{classDetail ->
                            addDrugs(classDetail.associatedDrug, "Asthma")
                            addDrugs(classDetail.associatedDrug2, "Asthma")
                        }
                        medicationClass.className2?.forEach{classDetail ->
                            addDrugs(classDetail.associatedDrug, "Asthma")
                            addDrugs(classDetail.associatedDrug2, "Asthma")
                        }
                    }
                }
            }
        }
        return medicines
    }


    fun MedicineEntity.toMedicine(): Medicine {
        return Medicine(
            name = name,
            dose = dose,
            strength = strength,
            condition = condition
        )
    }
}