package com.grpcx.androidtask.domain.models

import com.grpcx.androidtask.data.dto.FileDto

data class File(
    val contentType: String,
    val details: Details,
    val fileName: String,
    val url: String
)

fun FileDto.toModel() = File (
    contentType,
    details.toModel(),
    fileName,
    url
)