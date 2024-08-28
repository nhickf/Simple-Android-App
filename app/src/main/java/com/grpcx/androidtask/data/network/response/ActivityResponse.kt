package com.grpcx.androidtask.data.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActivityResponse(
    @SerialName("challengeId")
    val challengeId: String,
    @SerialName("description")
    val description: String,
    @SerialName("descriptionB")
    val descriptionB: String?,
    @SerialName("icon")
    val icon: IconResponse,
    @SerialName("id")
    val id: String,
    @SerialName("lockedIcon")
    val lockedIcon: IconResponse,
    @SerialName("state")
    val state: String,
    @SerialName("title")
    val title: String,
    @SerialName("titleB")
    val titleB: String?,
    @SerialName("type")
    val type: String
)