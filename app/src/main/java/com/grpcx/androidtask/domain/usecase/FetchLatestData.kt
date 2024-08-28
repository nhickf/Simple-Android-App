package com.grpcx.androidtask.domain.usecase

import com.grpcx.androidtask.domain.repository.MainRepository

class FetchLatestData(
    private val repository: MainRepository
) {

    suspend operator fun invoke() {
        try {
            repository.fetchAndCacheLatestData()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}