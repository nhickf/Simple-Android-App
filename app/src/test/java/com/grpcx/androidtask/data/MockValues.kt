package com.grpcx.androidtask.data

import com.grpcx.androidtask.data.dto.toDto
import com.grpcx.androidtask.data.network.response.EmotionResponse
import com.grpcx.androidtask.data.network.response.LevelResponse
import com.grpcx.androidtask.domain.models.Emotion
import com.grpcx.androidtask.domain.models.toModel
import com.grpcx.androidtask.presentation.main.MainUiState

object MockValues {

    fun fetchEmotionResponse(): EmotionResponse {
        return EmotionResponse(
            levels = (1..5).map {
                LevelResponse(
                    activities = emptyList(),
                    description = "",
                    level = it.toString(),
                    state = "AVAILABLE",
                    title = "",
                )
            }
        )
    }

    val mockkMainUiState = MainUiState(
        emotion =  EmotionResponse(
            levels = (1..5).map {
                LevelResponse(
                    activities = emptyList(),
                    description = "",
                    level = it.toString(),
                    state = "AVAILABLE",
                    title = "",
                )
            }
        ).toDto().toModel()
    )

    val emptyMainUiState = MainUiState()
}