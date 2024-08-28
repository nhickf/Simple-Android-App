package com.grpcx.androidtask.data.dto

import com.grpcx.androidtask.data.local.entities.ActivityEntity
import com.grpcx.androidtask.data.network.response.ActivityResponse
import com.grpcx.androidtask.domain.models.Activity

data class ActivityDto(
    val challengeId: String,
    val description: String,
    val descriptionB: String?,
    val icon: IconDto,
    val id: String,
    val lockedIcon: IconDto,
    val state: String,
    val title: String,
    val titleB: String?,
    val type: String
)

fun ActivityResponse.toDto() = ActivityDto(
    challengeId,
    description,
    descriptionB,
    icon.toDto(),
    id,
    lockedIcon.toDto(),
    state,
    title,
    titleB,
    type
)

fun ActivityEntity.toDto(
    icon: IconDto,
    lockedIcon: IconDto,
) = ActivityDto(
    challengeId,
    description,
    descriptionB,
    icon,
    id,
    lockedIcon,
    state,
    title,
    titleB,
    type
)

fun ActivityDto.toEntity(
    levelId: Int,
) = ActivityEntity(
    levelId,
    challengeId,
    description,
    descriptionB,
    id,
    state,
    title,
    titleB,
    type
)