package com.udacity.asteroidradar.data

data class AsteroidEntity(
    val id: String,
    val absoluteMagnitude: Double,
    val estimatedDiameterMaxInKM: Double,
    val isPotentiallyHazardous: Boolean,
    val closeApproachRelativeVelocityInKmPerSec: Double,
    val closeApproachMissDistanceAstronomical: Double,
)

data class ImageOfDayEntity(
    val id: String,
    val url: String,
    val mediaType: String,
    val title: String
)