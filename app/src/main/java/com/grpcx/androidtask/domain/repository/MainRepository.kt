package com.grpcx.androidtask.domain.repository

import com.grpcx.androidtask.data.dto.EmotionDto
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun fetchAndCacheLatestData()

    fun observeCacheData() : Flow<EmotionDto>

}