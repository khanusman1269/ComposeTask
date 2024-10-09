package com.compose.api.task.data.data_source.remote

import com.compose.api.task.data.models.MedicineModel
import retrofit2.Response
import retrofit2.http.GET

fun interface ApiService {
    @GET("af97bbe1-92ed-449a-ac7f-30319abbb709")
    suspend fun getMedicines(): Response<List<MedicineModel>>
}