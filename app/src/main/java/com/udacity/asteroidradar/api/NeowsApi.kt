package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.data.PictureOfTheDay
import com.udacity.asteroidradar.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NeowsApi {

    @GET(Constants.ASTEROID_OF_THE_DAY)
    suspend fun getPictureOfTheDay(@Query("api_key") apiKey: String): PictureOfTheDay

    @GET(Constants.ASTEROID_FEED)
    suspend fun getAsteroidsOfTheWeek(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String
    ): String


}