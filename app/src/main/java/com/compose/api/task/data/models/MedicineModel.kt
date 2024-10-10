package com.compose.api.task.data.models

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("problems") val problems: List<Problem>
)

data class Problem(
    @SerializedName("Diabetes") val diabetes: List<ConditionDetails>?,
    @SerializedName("Asthma") val asthma: List<ConditionDetails>?
)

data class ConditionDetails(
    @SerializedName("medications") val medications: List<Medication>?,
    @SerializedName("labs") val labs: List<Lab>?
)

data class Medication(
    @SerializedName("medicationsClasses") val medicationsClasses: List<MedicationClass>
)

data class MedicationClass(
    @SerializedName("className") val className: List<ClassDetail>?,
    @SerializedName("className2") val className2: List<ClassDetail>?
)

data class ClassDetail(
    @SerializedName("associatedDrug") val associatedDrug: List<Drug>?,
    @SerializedName("associatedDrug#2") val associatedDrug2: List<Drug>?
)

data class Drug(
    @SerializedName("name") val name: String,
    @SerializedName("dose") val dose: String,
    @SerializedName("strength") val strength: String
)

data class Lab(
    @SerializedName("missing_field") val missingField: String
)
