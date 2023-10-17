package com.example.vokzaldb

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class TrainsViewModel(private val repository: TrainRepository): ViewModel() {
    val allTrains: LiveData<List<Train>> = repository.allTrains.asLiveData()
    fun insert(train: Train) = viewModelScope.launch {
        repository.insert(train)
    }
}
class TrainsViewModelFactory(private val repository: TrainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrainsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TrainsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}