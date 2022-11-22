package com.android.desafiofinalstarwars.ui.right.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.RepositoryInterface
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.MovieResponse
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.NetworkResponse
import kotlinx.coroutines.launch

class MoviesViewModel(private val repository: RepositoryInterface) : ViewModel() {
    private val _movieResponse = MutableLiveData<MovieResponse?>()
    val movieResponse: LiveData<MovieResponse?> = _movieResponse
    private val _movieError = MutableLiveData<Unit>()
    val movieError = _movieError as LiveData<Unit>
    var loadStateLiveData = MutableLiveData<State>()


    fun getApiMovies() = viewModelScope.launch {
        loadStateLiveData.value = State.LOADING
        when (val response = repository.getMovies()) {
            is NetworkResponse.Success -> { _movieResponse.value = response.data }
            is NetworkResponse.Failed -> { _movieError.value = Unit }
        }
        loadStateLiveData.value = State.LOADING_FINISHED
    }

    enum class State {
        LOADING, LOADING_FINISHED
    }
}