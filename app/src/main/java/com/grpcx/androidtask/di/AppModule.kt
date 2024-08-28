package com.grpcx.androidtask.di

import com.grpcx.androidtask.data.local.dao.AppDao
import com.grpcx.androidtask.data.network.AppService
import com.grpcx.androidtask.data.repository.MainRepositoryImpl
import com.grpcx.androidtask.domain.repository.MainRepository
import com.grpcx.androidtask.domain.usecase.FetchLatestData
import com.grpcx.androidtask.domain.usecase.MainUseCase
import com.grpcx.androidtask.domain.usecase.ObserveCacheData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesMainRepository(appDao: AppDao, appService: AppService): MainRepository {
        return MainRepositoryImpl(
            appDao = appDao,
            appService = appService
        )
    }

    @Provides
    fun providesMainUseCases(appRepository: MainRepository): MainUseCase {
        return MainUseCase(
            fetchLatestData = FetchLatestData(appRepository),
            observeCacheData = ObserveCacheData(appRepository)
        )
    }
}