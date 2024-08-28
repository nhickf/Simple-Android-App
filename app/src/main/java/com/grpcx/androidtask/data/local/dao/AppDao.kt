package com.grpcx.androidtask.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.grpcx.androidtask.data.local.entities.ActivityEntity
import com.grpcx.androidtask.data.local.entities.IconEntity
import com.grpcx.androidtask.data.local.entities.LevelActivities
import com.grpcx.androidtask.data.local.entities.LevelEntity
import com.grpcx.androidtask.data.local.entities.LockedIconEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeLatestData(
        levelEntity: List<LevelEntity>,
        activities: List<ActivityEntity>,
        icons: List<IconEntity>,
        lockedIcon: List<LockedIconEntity>
    )

    @Transaction
    @Query("SELECT * FROM LEVEL")
    fun fetchLatestData(): Flow<List<LevelActivities>>

}