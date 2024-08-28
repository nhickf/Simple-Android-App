package com.grpcx.androidtask.data.dto

import com.grpcx.androidtask.data.network.response.FileResponse

data class FileDto(
    val contentType: String,
    val details: DetailDto,
    val fileName: String,
    val url: String
)

fun FileResponse.toDto() = FileDto(
    contentType, details.toDto(), fileName, url
)
