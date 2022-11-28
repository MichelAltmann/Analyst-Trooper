package com.android.desafiofinalstarwars.ui.search.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.RepositoryInterface
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.NetworkResponse
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.StarshipResponse
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import okhttp3.internal.wait

class StarshipsSearchViewModel(private val repository: RepositoryInterface) : ViewModel() {
    private val _starshipResponse = MutableLiveData<StarshipResponse?>()
    val starshipResponse: LiveData<StarshipResponse?> = _starshipResponse
    private val _starshipError = MutableLiveData<Unit>()
    val starshipError = _starshipError as LiveData<Unit>
    var loadStateLiveData = MutableLiveData<State>()

    private var page = 1
    var filter = ""
        set(value) {
            field = value
            page = 1
            getApiStarshipsSearch()
        }

    private var searchStarshipsJob : Job? = null

    fun getApiStarshipsSearch() = viewModelScope.launch {
        searchStarshipsJob?.cancel()
        searchStarshipsJob = viewModelScope.launch {
            loadStateLiveData.value = State.LOADING
            when (val response = repository.getStarshipsSearch(filter = filter, page = page)) {
                is NetworkResponse.Success -> {
                    _starshipResponse.value = response.data
                    page++
                }
                is NetworkResponse.Failed -> {
                    _starshipError.value = Unit
                }
            }
            loadStateLiveData.value = State.LOADING_FINISHED
        }
    }

    enum class State {
        LOADING, LOADING_FINISHED
    }
}