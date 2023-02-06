package com.udacity.asteroidradar.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.api.toFormattedString
import java.util.*


@Entity(tableName = "asteroid")
data class AsteroidEntity(
    @PrimaryKey
    val id: Long,
    val codename: String,
    val closeApproachDate: Long,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
) {

    fun toAsteroid() = Asteroid(
        id,
        codename,
        Date(closeApproachDate).toFormattedString(),
        absoluteMagnitude,
        estimatedDiameter,
        relativeVelocity,
        distanceFromEarth,
        isPotentiallyHazardous
    )
}