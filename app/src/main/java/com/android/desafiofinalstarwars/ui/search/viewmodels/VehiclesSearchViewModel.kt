package com.android.desafiofinalstarwars.ui.search.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.RepositoryInterface
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.NetworkResponse
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.VehicleResponse
import kotlinx.coroutines.launch

class VehiclesSearchViewModel(private val repository: RepositoryInterface) : ViewModel() {
    private val _vehicleResponse = MutableLiveData<VehicleResponse?>()
    val vehicleResponse: LiveData<VehicleResponse?> = _vehicleResponse
    private val _vehicleError = MutableLiveData<Unit>()
    val vehicleError = _vehicleError as LiveData<Unit>
    var loadStateLiveData = MutableLiveData<State>()

    var filter = ""
        set(value) {
            field = value
            page = 1
            getApiVehiclesSearch()
        }

    private var page = 1

    fun getApiVehiclesSearch() = viewModelScope.launch {
        loadStateLiveData.value = State.LOADING
        when (val response = repository.getVehiclesSearch(filter = filter, page = page)) {
            is NetworkResponse.Success -> { _vehicleResponse.value = response.data
                page++}
            is NetworkResponse.Failed -> { _vehicleError.value = Unit }
        }
        loadStateLiveData.value = State.LOADING_FINISHED
    }

    enum class State {
        LOADING, LOADING_FINISHED
    }
}