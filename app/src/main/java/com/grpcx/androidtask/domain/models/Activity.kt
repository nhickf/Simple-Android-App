package com.grpcx.androidtask.domain.models

import com.grpcx.androidtask.data.dto.ActivityDto

data class Activity(
    val challengeId: String,
    val description: String,
    val descriptionB: String?,
    val icon: Icon,
    val id: String,
    val lockedIcon: Icon,
    val state: States,
    val title: String,
    val titleB: String?,
    val type: String
)


fun ActivityDto.toModel() = Activity(
    challengeId,
    description,
    descriptionB,
    icon.toModel(),
    id,
    lockedIcon.toModel(),
    States.valueOf(state),
    title,
    titleB,
    type
)
