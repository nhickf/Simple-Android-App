package com.grpcx.androidtask.data.local.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity("level")
data class LevelEntity(
    @PrimaryKey(autoGenerate = false)
    val levelId: Int,
    val description: String,
    val level: String,
    val state: String,
    val title: String
)