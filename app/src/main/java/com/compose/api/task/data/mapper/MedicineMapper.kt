package com.compose.api.task.data.mapper

import com.compose.api.task.data.models.MedicineEntity
import com.compose.api.task.data.models.MedicineModel
import com.compose.api.task.domain.models.Medicine

object MedicineMapper {

    fun MedicineModel.toMedicineEntity(): MedicineEntity {
        return MedicineEntity(
            name = name,
            dose = dose,
            strength = strength,
            condition = condition
        )
    }

    fun MedicineModel.toMedicine(): Medicine {
        return Medicine(
            name = name,
            dose = dose,
            strength = strength,
            condition = condition
        )
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