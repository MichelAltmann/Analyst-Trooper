package com.android.desafiofinalstarwars.ui.search.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.RepositoryInterface
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.NetworkResponse
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.PlanetResponse
import kotlinx.coroutines.launch

class PlanetsSearchViewModel(private val repository: RepositoryInterface) : ViewModel() {
    private val _planetResponse = MutableLiveData<PlanetResponse?>()
    val planetResponse: LiveData<PlanetResponse?> = _planetResponse
    private val _planetError = MutableLiveData<Unit>()
    val planetError = _planetError as LiveData<Unit>
    var loadStateLiveData = MutableLiveData<State>()

    private var page = 1

    var filter = ""
        set(value) {
            field = value
            page = 1
            getApiPlanetsSearch()
        }

    fun getApiPlanetsSearch() = viewModelScope.launch {
        loadStateLiveData.value = State.LOADING
        when (val response = repository.getPlanetsSearch(filter = filter, page = page)) {
            is NetworkResponse.Success -> { _planetResponse.value = response.data
                page++}
            is NetworkResponse.Failed -> { _planetError.value = Unit }
        }
        loadStateLiveData.value = State.LOADING_FINISHED
    }

    enum class State {
        LOADING, LOADING_FINISHED
    }
}