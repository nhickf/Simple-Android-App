package com.grpcx.androidtask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.grpcx.androidtask.data.local.dao.AppDao
import com.grpcx.androidtask.data.local.entities.ActivityEntity
import com.grpcx.androidtask.data.local.entities.IconEntity
import com.grpcx.androidtask.data.local.entities.LevelEntity
import com.grpcx.androidtask.data.local.entities.LockedIconEntity

@Database(
    entities = [ActivityEntity::class, IconEntity::class, LockedIconEntity::class, LevelEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}