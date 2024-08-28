package com.grpcx.androidtask.domain.models

import com.grpcx.androidtask.data.dto.LevelDto

data class Level(
    val activities: List<Activity>,
    val description: String,
    val level: String,
    val state: States,
    val title: String
)

fun LevelDto.toModel() = Level(
    activities.map { it.toModel() },
    description,
    level,
    States.valueOf(state),
    title
)

