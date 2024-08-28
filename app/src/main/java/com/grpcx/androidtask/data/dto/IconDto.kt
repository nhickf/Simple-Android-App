package com.grpcx.androidtask.data.dto

import com.grpcx.androidtask.data.local.entities.IconEntity
import com.grpcx.androidtask.data.local.entities.LockedIconEntity
import com.grpcx.androidtask.data.network.response.IconResponse

data class IconDto(
    val description: String,
    val file: FileDto,
    val title: String
)

fun IconResponse.toDto() = IconDto(
    description, file.toDto(), title
)

fun IconEntity.toDto() = IconDto(
    iconDescription,
    FileDto(
        contentType,
        DetailDto(size),
        fileName,
        url
    ),
    title
)

fun LockedIconEntity.toDto() = IconDto(
    iconDescription,
    FileDto(
        contentType,
        DetailDto(size),
        fileName,
        url
    ),
    title
)


fun IconDto.toIconEntity(activityId: String) = IconEntity(
    0,
    activityId,
    description,
    file.contentType,
    file.details.size,
    file.fileName,
    file.url,
    title
)

fun IconDto.toLockedIconEntity(activityId: String) = LockedIconEntity(
    0,
    activityId,
    description,
    file.contentType,
    file.details.size,
    file.fileName,
    file.url,
    title
)
