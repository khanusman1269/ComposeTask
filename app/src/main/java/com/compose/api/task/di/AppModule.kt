package com.compose.api.task.di

import android.content.Context
import androidx.room.Room
import com.compose.api.task.common.NetworkUtils
import com.compose.api.task.data.data_source.local.AppDatabase
import com.compose.api.task.data.data_source.local.MedicineDao
import com.compose.api.task.data.data_source.remote.ApiService
import com.compose.api.task.data.repository.LoginRepositoryImpl
import com.compose.api.task.data.repository.MedicinesRepositoryImpl
import com.compose.api.task.domain.repository.LoginRepository
import com.compose.api.task.domain.repository.MedicinesRepository
import com.compose.api.task.domain.use_cases.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideLoginRepository(): LoginRepository {
        return LoginRepositoryImpl()
    }

    @Provides
    fun provideLoginUseCase(loginRepository: LoginRepository): LoginUseCase {
        return LoginUseCase(loginRepository)
    }

    @Provides
    fun providesBaseUrl(): String = "https://run.mocky.io/v3/"

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl)
            .client(okHttpClient)

            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "Medicines_Db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesMedicinesDao(appDatabase: AppDatabase) = appDatabase.medicineDao()

    @Provides
    @Singleton
    fun providesNetworkUtils(@ApplicationContext context: Context) = NetworkUtils(context)

    @Provides
    @Singleton
    fun providesMedicinesRepository(apiService: ApiService, medicineDao: MedicineDao, networkUtils: NetworkUtils): MedicinesRepository {
        return MedicinesRepositoryImpl(apiService, medicineDao, networkUtils)
    }




}