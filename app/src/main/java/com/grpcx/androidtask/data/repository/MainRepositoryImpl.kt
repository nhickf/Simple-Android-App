package com.grpcx.androidtask.data.repository

import com.grpcx.androidtask.data.dto.EmotionDto
import com.grpcx.androidtask.data.dto.toDto
import com.grpcx.androidtask.data.dto.toEntity
import com.grpcx.androidtask.data.dto.toIconEntity
import com.grpcx.androidtask.data.dto.toLockedIconEntity
import com.grpcx.androidtask.data.local.dao.AppDao
import com.grpcx.androidtask.data.local.entities.ActivityEntity
import com.grpcx.androidtask.data.local.entities.IconEntity
import com.grpcx.androidtask.data.local.entities.LevelActivities
import com.grpcx.androidtask.data.local.entities.LevelEntity
import com.grpcx.androidtask.data.local.entities.LockedIconEntity
import com.grpcx.androidtask.data.network.AppService
import com.grpcx.androidtask.domain.models.Emotion
import com.grpcx.androidtask.domain.models.toModel
import com.grpcx.androidtask.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import javax.inject.Inject


class MainRepositoryImpl @Inject constructor(
    private val appDao: AppDao,
    private val appService: AppService
) : MainRepository {

    override suspend fun fetchAndCacheLatestData() {

        val icons = mutableListOf<IconEntity>()
        val lockedIcons = mutableListOf<LockedIconEntity>()
        val activities = mutableListOf<ActivityEntity>()
        val levels = mutableListOf<LevelEntity>()

        val emotionDto = appService.fetchData().toDto()

        levels.addAll(emotionDto.level.mapIndexed { index, levelDto ->

            levelDto.activities.forEach { activityDto ->

                lockedIcons.add(activityDto.lockedIcon.toLockedIconEntity(activityDto.id))

                icons.add(activityDto.icon.toIconEntity(activityDto.id))

                activities.add(activityDto.toEntity(index))
            }

            levelDto.toEntity(index)
        })

        appDao.storeLatestData(
            levelEntity = levels,
            activities = activities,
            icons = icons,
            lockedIcon = lockedIcons
        )
    }

    override fun observeCacheData(): Flow<EmotionDto> {
        return appDao.fetchLatestData().map { cacheData: List<LevelActivities> ->
            EmotionDto(
                level = cacheData.map { levelActivities ->
                    levelActivities.levelEntity.toDto(
                        activities = levelActivities.activities.map {
                            it.activityEntity.toDto(
                                icon = it.icon.toDto(),
                                lockedIcon = it.lockedIcon.toDto()
                            )
                        }
                    )
                }
            )
        }
    }
}