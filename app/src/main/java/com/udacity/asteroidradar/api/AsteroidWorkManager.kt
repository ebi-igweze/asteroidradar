package com.udacity.asteroidradar.api

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.AsteroidApplication
import java.util.Calendar


class AsteroidCacheWorker(
    private val appContext: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(appContext, workerParameters) {

    override suspend fun doWork(): Result {
        val application = appContext.applicationContext as AsteroidApplication
        application.repository.saveThisWeekAsteroids()
        val today = todayDate().time

        application.database.getAsteroidDao().removeOldAsteroids(today)
        return Result.success()
    }

    companion object {
        const val WORKER_NAME = "AsteroidCacheWorker"
    }
}