package com.grpcx.androidtask.data.dto

import com.grpcx.androidtask.data.local.entities.LevelEntity
import com.grpcx.androidtask.data.network.response.LevelResponse

data class LevelDto(
    val activities: List<ActivityDto>,
    val description: String,
    val level: String,
    val state: String,
    val title: String
)


fun LevelResponse.toDto() = LevelDto(
    activities.map { it.toDto() }, description, level, state, title
)

fun LevelEntity.toDto(
    activities: List<ActivityDto>
) = LevelDto(
    activities, description, level, state, title
)

fun LevelDto.toEntity(levelId: Int) = LevelEntity(
    levelId,
    description,
    level,
    state,
    title
)