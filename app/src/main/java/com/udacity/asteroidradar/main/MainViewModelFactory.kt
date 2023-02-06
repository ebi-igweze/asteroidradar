package com.udacity.asteroidradar.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.asteroidradar.AsteroidApplication

class MainViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        val application = context.applicationContext as AsteroidApplication
        return MainViewModel(application.repository) as T
    }
}