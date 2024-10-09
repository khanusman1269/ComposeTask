package com.compose.api.task.data.models

import com.google.gson.annotations.SerializedName

data class MedicineModel(
    @SerializedName("name") var name: String,
    @SerializedName("dose") var dose: String,
    @SerializedName("strength") var strength: String,
    @SerializedName("condition") var condition: String
)
