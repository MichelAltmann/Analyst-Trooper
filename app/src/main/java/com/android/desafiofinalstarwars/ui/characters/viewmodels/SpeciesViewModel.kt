package com.android.desafiofinalstarwars.ui.characters.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.RepositoryInterface
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.SpecieResponse
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.NetworkResponse
import kotlinx.coroutines.launch

class SpeciesViewModel(private val repository: RepositoryInterface) : ViewModel() {

    private val _specieResponse = MutableLiveData<SpecieResponse?>()
    val specieResponse: LiveData<SpecieResponse?> = _specieResponse
    private val _specieError = MutableLiveData<Unit>()
    val specieError = _specieError as LiveData<Unit>
    var loadStateLiveData = MutableLiveData<State>()

    private var page = 1

    fun getApiSpecies() = viewModelScope.launch {
        loadStateLiveData.value = State.LOADING
        when (val response = repository.getSpecies(page = page)) {
            is NetworkResponse.Success -> { _specieResponse.value = response.data
            page++}
            is NetworkResponse.Failed -> { _specieError.value = Unit }
        }
        loadStateLiveData.value = State.LOADING_FINISHED
    }

    enum class State {
        LOADING, LOADING_FINISHED
    }
}