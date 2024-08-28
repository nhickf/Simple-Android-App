package com.grpcx.androidtask.domain.usecase

data class MainUseCase(
    val fetchLatestData: FetchLatestData,
    val observeCacheData: ObserveCacheData
)