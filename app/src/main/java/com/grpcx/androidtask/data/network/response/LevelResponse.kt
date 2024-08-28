package com.grpcx.androidtask.data.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LevelResponse(
    @SerialName("activities")
    val activities: List<ActivityResponse>,
    @SerialName("description")
    val description: String,
    @SerialName("level")
    val level: String,
    @SerialName("state")
    val state: String,
    @SerialName("title")
    val title: String
)