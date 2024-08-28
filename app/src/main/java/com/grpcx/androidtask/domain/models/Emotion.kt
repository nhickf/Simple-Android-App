package com.grpcx.androidtask.domain.models

import com.grpcx.androidtask.data.dto.EmotionDto

data class Emotion(
    val levels: List<Level>
)

fun EmotionDto.toModel() = Emotion(
    level.map { it.toModel() }
)