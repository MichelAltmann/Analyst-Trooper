package com.android.desafiofinalstarwars.ui.vehicles.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.RepositoryInterface
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.NetworkResponse
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.VehicleResponse
import kotlinx.coroutines.launch

class VehiclesViewModel(private val repository: RepositoryInterface) : ViewModel() {
    private val _vehicleResponse = MutableLiveData<VehicleResponse?>()
    val vehicleResponse: LiveData<VehicleResponse?> = _vehicleResponse
    private val _vehicleError = MutableLiveData<Unit>()
    val vehicleError = _vehicleError as LiveData<Unit>
    var loadStateLiveData = MutableLiveData<State>()

    private var page = 1

    fun getApiVehicles() = viewModelScope.launch {
        loadStateLiveData.value = State.LOADING
        when (val response = repository.getVehicles(page = page)) {
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