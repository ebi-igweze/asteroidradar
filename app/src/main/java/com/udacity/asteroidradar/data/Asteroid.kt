package com.udacity.asteroidradar.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Asteroid(
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
): Parcelable

val asteroidList: List<Asteroid> = listOf(
    Asteroid(id = 3726710, codename = "(2015 RC)",
        closeApproachDate = "2002-09-08", estimatedDiameter = 0.0820427065,
        absoluteMagnitude = 24.3, relativeVelocity = 19.4850295284,
        distanceFromEarth = 0.0269230459, isPotentiallyHazardous = false
    ),
    Asteroid(id = 3726720, codename = "(2010 ABC)",
        closeApproachDate = "2010-04-08", estimatedDiameter = 0.0820427065,
        absoluteMagnitude = 24.3, relativeVelocity = 19.4850295284,
        distanceFromEarth = 0.0269230459, isPotentiallyHazardous = true
    ),
    Asteroid(id = 3726730, codename = "(2001 GMK)",
        closeApproachDate = "2001-05-08", estimatedDiameter = 0.0820427065,
        absoluteMagnitude = 24.3, relativeVelocity = 19.4850295284,
        distanceFromEarth = 0.0269230459, isPotentiallyHazardous = true
    ),
    Asteroid(id = 3726740, codename = "(2012 DRC)",
        closeApproachDate = "2012-09-08", estimatedDiameter = 0.0820427065,
        absoluteMagnitude = 24.3, relativeVelocity = 19.4850295284,
        distanceFromEarth = 0.0269230459, isPotentiallyHazardous = false
    ),
    Asteroid(id = 3726430, codename = "(2016 MMXKC)",
        closeApproachDate = "2016-01-05", estimatedDiameter = 0.0820427065,
        absoluteMagnitude = 24.3, relativeVelocity = 19.4850295284,
        distanceFromEarth = 0.0269230459, isPotentiallyHazardous = true
    ),
    Asteroid(id = 3725710, codename = "(2013 VXMYC)",
        closeApproachDate = "2013-11-02", estimatedDiameter = 0.0820427065,
        absoluteMagnitude = 24.3, relativeVelocity = 19.4850295284,
        distanceFromEarth = 0.0269230459, isPotentiallyHazardous = false
    )
)