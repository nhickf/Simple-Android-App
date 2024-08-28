package com.grpcx.androidtask.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("activity")
data class ActivityEntity(
    val levelId: Int,
    val challengeId: String,
    val description: String,
    val descriptionB: String?,
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val state: String,
    val title: String,
    val titleB: String?,
    val type: String
)