package com.grpcx.androidtask.data.repository

import com.grpcx.androidtask.data.MockValues
import com.grpcx.androidtask.data.dto.EmotionDto
import com.grpcx.androidtask.data.dto.toDto
import com.grpcx.androidtask.data.network.response.EmotionResponse
import com.grpcx.androidtask.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepositoryImpl : MainRepository {

    private var mockData: EmotionResponse = EmotionResponse(emptyList())

    override suspend fun fetchAndCacheLatestData() {
        mockData = MockValues.fetchEmotionResponse()
    }

    override fun observeCacheData(): Flow<EmotionDto> {
        return flow {
            emit(
                mockData.toDto()
            )
        }
    }
}