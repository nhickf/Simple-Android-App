package com.grpcx.androidtask.data.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmotionResponse(
    @SerialName("levels")
    val levels: List<LevelResponse>
)