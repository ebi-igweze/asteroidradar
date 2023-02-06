package com.udacity.asteroidradar.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.api.*
import com.udacity.asteroidradar.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class AsteroidRepository(
    private val db: AsteroidDatabase,
    private val api:  NeowsApi
) {

    suspend fun getTodaysAsteroids(): LiveData<List<Asteroid>> = liveData {

        // get saved asteroids
        val today = todayDate().time
        val savedAsteroids = db.getAsteroidDao().getAsteroidsFromToday(today).map {
               it.map { asteroidEntity -> asteroidEntity.toAsteroid() }
        }

        // return saved asteroids
        emitSource(savedAsteroids)

        // update current week's asteroids
        withContext(Dispatchers.IO) {
            saveThisWeekAsteroids()
        }
    }


    suspend fun saveThisWeekAsteroids() {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat(
            Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())

        // get current day
        val currentTime = calendar.time
        val startDay = dateFormat.format(currentTime)
        // go to the 7th day
        calendar.add(Calendar.DAY_OF_YEAR, 6)
        val endDay = dateFormat.format(calendar.time)

        // get the week asteroid and save in db
        val asteroidsString = withContext(Dispatchers.IO) {
            api.getAsteroidsOfTheWeek(startDay, endDay, BuildConfig.API_KEY)
        }
        val asteroidsJson = JSONObject(asteroidsString)
        val asteroids = parseAsteroidsJsonResult(asteroidsJson).map { it.toEntity() }
        db.getAsteroidDao().addAll(asteroids)
    }

    suspend fun getPictureOfTheDay(): LiveData<PictureOfTheDay> = liveData {
        val pic = api.getPictureOfTheDay(BuildConfig.API_KEY)
        emit(pic)
    }
}