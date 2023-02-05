package com.udacity.asteroidradar.main

import androidx.lifecycle.*
import com.udacity.asteroidradar.data.Asteroid
import com.udacity.asteroidradar.data.AsteroidRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val repository: AsteroidRepository = AsteroidRepository.getInstance()
) : ViewModel() {

    private val _asteroidsLiveData = MediatorLiveData<List<Asteroid>>()
    val asteroidsLiveData: LiveData<List<Asteroid>>
        get() = _asteroidsLiveData

    fun getAsteroidsOfTheDay() {
        viewModelScope.launch(Dispatchers.IO) {
            val asteroids = repository.getTodaysAsteroids()
            withContext(Dispatchers.Main) {
                _asteroidsLiveData.addSource(asteroids.asLiveData()) { value ->
                    _asteroidsLiveData.setValue(value)
                }
            }
        }
    }
}