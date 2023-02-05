package com.udacity.asteroidradar.data

import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.api.NeowsApi
import com.udacity.asteroidradar.api.neowsApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class AsteroidRepository {
    val api: NeowsApi = neowsApi

    suspend fun getTodaysAsteroids(): Flow<List<Asteroid>> = flow {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat(
            Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())

        // get current day
        val currentTime = calendar.time
        val startDay = dateFormat.format(currentTime)
        // go to the 7th day
        calendar.add(Calendar.DAY_OF_YEAR, 6)
        val endDay = dateFormat.format(currentTime)


        // TODO get the week asteroid and save in db
        val asteroidsString = withContext(Dispatchers.IO) {
            api.getAsteroidsOfTheWeek(startDay, endDay, BuildConfig.API_KEY)
        }
        val asteroidsJson = JSONObject(asteroidsString)
        emit(parseAsteroidsJsonResult(asteroidsJson))
    }

    suspend fun getPictureOfTheDay(): Flow<PictureOfTheDay> = flow {
        val pic = api.getPictureOfTheDay(BuildConfig.API_KEY)
        emit(pic)
    }


    companion object {
        private lateinit var instance: AsteroidRepository

        fun getInstance(): AsteroidRepository {
            if (!::instance.isInitialized) {
                instance = AsteroidRepository()
            }
            return instance
        }
    }
}