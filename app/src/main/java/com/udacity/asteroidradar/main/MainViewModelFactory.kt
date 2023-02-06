package com.udacity.asteroidradar.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.asteroidradar.AsteroidApplication

class MainViewModelFactory: ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(AsteroidApplication.repository) as T
    }
}