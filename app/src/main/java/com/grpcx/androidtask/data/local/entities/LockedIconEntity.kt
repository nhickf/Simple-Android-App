package com.grpcx.androidtask.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LockedIconEntity(
    @PrimaryKey(autoGenerate = true)
    val iconId:Int,
    val activityId: String,
    val iconDescription: String,
    val contentType: String,
    val size: Int,
    val fileName: String,
    val url: String,
    val title: String
)