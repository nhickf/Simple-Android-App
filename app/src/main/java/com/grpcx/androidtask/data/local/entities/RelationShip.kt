package com.grpcx.androidtask.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation


data class LevelActivities(
    @Embedded val levelEntity: LevelEntity,

    @Relation(
        parentColumn = "levelId",
        entityColumn = "levelId",
        entity = ActivityEntity::class
    )
    val activities: List<ActivitiesIcons>
)


data class ActivitiesIcons(
    @Embedded val activityEntity: ActivityEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "activityId",
    )
    val icon: IconEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "activityId",
    )
    val lockedIcon: LockedIconEntity
)
