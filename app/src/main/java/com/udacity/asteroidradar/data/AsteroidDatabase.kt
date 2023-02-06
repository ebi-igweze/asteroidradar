package com.udacity.asteroidradar.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AsteroidEntity::class], exportSchema = false, version = 1)
abstract class AsteroidDatabase: RoomDatabase() {

    abstract fun getAsteroidDao(): AsteroidDao

    companion object {
        const val DB_NAME = "asteroid_table"
    }
}