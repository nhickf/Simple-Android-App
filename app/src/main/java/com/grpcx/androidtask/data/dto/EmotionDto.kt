package com.grpcx.androidtask.data.dto

import com.grpcx.androidtask.data.network.response.EmotionResponse

data class EmotionDto(
    val level: List<LevelDto>
)

fun EmotionResponse.toDto() = EmotionDto(
    level = levels.map { it.toDto() }
)