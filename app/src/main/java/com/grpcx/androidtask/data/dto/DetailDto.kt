package com.grpcx.androidtask.data.dto

import com.grpcx.androidtask.data.network.response.DetailsResponse

data class DetailDto (
    val size: Int
)

fun DetailsResponse.toDto() = DetailDto (size)