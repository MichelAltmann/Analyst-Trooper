package com.android.desafiofinalstarwars.ui.right.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.RepositoryInterface
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.NetworkResponse
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.PlanetResponse
import kotlinx.coroutines.launch

class PlanetsViewModel(private val repository: RepositoryInterface) : ViewModel() {

    private val _planetResponse = MutableLiveData<PlanetResponse?>()
    val planetResponse: LiveData<PlanetResponse?> = _planetResponse
    private val _planetaError = MutableLiveData<Unit>()
    val planetaError = _planetaError as LiveData<Unit>
    var loadStateLiveData = MutableLiveData<State>()

    fun getApiPlanets() = viewModelScope.launch {
        loadStateLiveData.value = State.LOADING
        when (val response = repository.getPlanets()) {
            is NetworkResponse.Success -> { _planetResponse.value = response.data }
            is NetworkResponse.Failed -> { _planetaError.value = Unit }
        }
        loadStateLiveData.value = State.LOADING_FINISHED
    }

    enum class State {
        LOADING, LOADING_FINISHED
    }
    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}