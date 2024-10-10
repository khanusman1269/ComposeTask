package com.compose.api.task.data.data_source.remote

import com.compose.api.task.data.models.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

fun interface ApiService {
    @GET("d39ea7a0-fec1-4a43-b08c-f98011ececc3")
    suspend fun getMedicines(): Response<ApiResponse>
}