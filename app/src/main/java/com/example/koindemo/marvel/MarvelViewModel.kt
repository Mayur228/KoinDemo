package com.example.koindemo.marvel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koindemo.repository.MarvelRepository
import com.example.koindemo.constants.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MarvelViewModel(private val marvelRepository: MarvelRepository) : ViewModel() {
    private val _marvelCharacters = MutableStateFlow<Result<List<MarvelCharacter>>>(Result.Loading)
    val marvelCharacters: StateFlow<Result<List<MarvelCharacter>>> get() = _marvelCharacters

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> get() = _loadingState

    init {
        fetchMarvelCharacters()
    }

    private fun fetchMarvelCharacters() {
        _loadingState.value = true
        viewModelScope.launch {
            try {
                val result = marvelRepository.getMarvelCharacters()
                _marvelCharacters.value = result
            } catch (e: Exception) {
                _marvelCharacters.value = Result.Error(e)
            }
        }
    }
}
