package com.udacity.asteroidradar.main

import androidx.lifecycle.*
import com.udacity.asteroidradar.data.Asteroid
import com.udacity.asteroidradar.data.AsteroidRepository
import com.udacity.asteroidradar.data.PictureOfTheDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val repository: AsteroidRepository
) : ViewModel() {

    private val _asteroidsLiveData = MediatorLiveData<List<Asteroid>>()
    val asteroidsLiveData: LiveData<List<Asteroid>>
        get() = _asteroidsLiveData

    private val _picOfTheDay = MediatorLiveData<PictureOfTheDay>()
    val picOfTheDay: LiveData<PictureOfTheDay>
        get() = _picOfTheDay

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading:  LiveData<Boolean>
        get() = _isLoading

    fun getAsteroidsOfTheDay() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val asteroids = repository.getTodaysAsteroids()
            withContext(Dispatchers.Main) {
                _asteroidsLiveData.addSource(asteroids) { value ->
                    _asteroidsLiveData.value = value
                    _isLoading.value = value.isEmpty()
                }
            }
        }
    }

    fun getPictureOfTheDay() {
        viewModelScope.launch(Dispatchers.IO)  {
            val pictureOfTheDay = repository.getPictureOfTheDay()
            withContext(Dispatchers.Main) {
                _picOfTheDay.addSource(pictureOfTheDay) { value ->
                    _picOfTheDay.value = value
                }
            }
        }
    }
}