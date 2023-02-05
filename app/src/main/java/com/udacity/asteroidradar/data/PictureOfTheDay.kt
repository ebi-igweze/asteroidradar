package com.udacity.asteroidradar.data

import com.squareup.moshi.Json

data class PictureOfTheDay(
    @field:Json(name = "media_type")
    val mediaType: String,
    val title: String,
    val url: String)