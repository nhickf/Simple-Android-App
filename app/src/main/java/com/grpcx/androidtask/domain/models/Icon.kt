package com.grpcx.androidtask.domain.models

import com.grpcx.androidtask.data.dto.IconDto

data class Icon(
    val description: String,
    val file: File,
    val title: String
)

fun IconDto.toModel() = Icon(
    description,
    file.toModel(),
    title
)