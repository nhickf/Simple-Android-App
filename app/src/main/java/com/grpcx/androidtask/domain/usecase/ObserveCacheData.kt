package com.grpcx.androidtask.domain.usecase

import com.grpcx.androidtask.domain.models.Emotion
import com.grpcx.androidtask.domain.models.toModel
import com.grpcx.androidtask.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObserveCacheData(
    private val repository: MainRepository
) {

    operator fun invoke(): Flow<Emotion> {
        return repository.observeCacheData().map { it.toModel() }
    }
}