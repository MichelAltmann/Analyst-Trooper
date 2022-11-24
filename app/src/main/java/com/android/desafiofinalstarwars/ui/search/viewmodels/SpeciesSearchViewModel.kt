package com.android.desafiofinalstarwars.ui.search.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.RepositoryInterface
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.CharacterResponse
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.NetworkResponse
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.SpecieResponse
import kotlinx.coroutines.launch

class SpeciesSearchViewModel(private val repository: RepositoryInterface) : ViewModel() {
    private val _specieResponse = MutableLiveData<SpecieResponse?>()
    val specieResponse: LiveData<SpecieResponse?> = _specieResponse
    private val _specieError = MutableLiveData<Unit>()
    val specieError = _specieError as LiveData<Unit>
    var loadStateLiveData = MutableLiveData<State>()


    private var page = 1
    var filter = ""
        set(value) {
            field = value
            page = 1
            getApiSpeciesSearch()
        }


    fun getApiSpeciesSearch() = viewModelScope.launch {
        loadStateLiveData.value = State.LOADING
        when (val response = repository.getSpeciesSearch(filter, page)) {
            is NetworkResponse.Success -> {
                _specieResponse.value = response.data
                page++}
            is NetworkResponse.Failed -> { _specieError.value = Unit }
        }
        loadStateLiveData.value = State.LOADING_FINISHED
    }

    enum class State {
        LOADING, LOADING_FINISHED
    }
}