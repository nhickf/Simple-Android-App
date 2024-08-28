package com.grpcx.androidtask.di

import android.content.Context
import com.grpcx.androidtask.data.network.AppService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    fun providesAppService(@ApplicationContext context: Context): AppService {
        return AppService(context)
    }

}