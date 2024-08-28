package com.grpcx.androidtask.domain.models

import com.grpcx.androidtask.data.dto.DetailDto

data class Details(
    val size: Int
)

fun DetailDto.toModel() = Details(
    size
)