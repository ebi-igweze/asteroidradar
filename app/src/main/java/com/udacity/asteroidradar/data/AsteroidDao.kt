package com.udacity.asteroidradar.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AsteroidDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(asteroids: List<AsteroidEntity>)

    @Query("SELECT * FROM asteroid WHERE closeApproachDate >= :today ORDER BY closeApproachDate ASC")
    fun getAsteroidsFromToday(today: Long): LiveData<List<AsteroidEntity>>

    @Query("DELETE FROM asteroid WHERE closeApproachDate < :today")
    fun removeOldAsteroids(today: Long): Int
}