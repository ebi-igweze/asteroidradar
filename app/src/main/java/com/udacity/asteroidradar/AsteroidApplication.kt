package com.udacity.asteroidradar

import android.app.Application
import androidx.room.Room
import com.udacity.asteroidradar.api.NeowsApi
import com.udacity.asteroidradar.data.AsteroidDatabase
import com.udacity.asteroidradar.data.AsteroidRepository
import com.udacity.asteroidradar.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class AsteroidApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    private lateinit var instance: AsteroidApplication

    val database: AsteroidDatabase by lazy {
        Room.databaseBuilder(
            instance,
            AsteroidDatabase::class.java,
            AsteroidDatabase.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    val repository: AsteroidRepository by lazy {
        AsteroidRepository(database, neowsApi)
    }

    val neowsApi: NeowsApi by lazy {
        val serviceBuilder = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        serviceBuilder.create(NeowsApi::class.java)
    }
}