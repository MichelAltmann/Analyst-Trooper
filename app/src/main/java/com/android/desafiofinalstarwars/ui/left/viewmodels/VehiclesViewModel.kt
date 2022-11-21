package com.android.desafiofinalstarwars.ui.left.viewmodels

import android.content.ContentValues
import android.util.Log
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
    private val _veiculoError = MutableLiveData<Unit>()
    val veiculoError = _veiculoError as LiveData<Unit>
    var loadStateLiveData = MutableLiveData<State>()

    fun getBuscaPlanetasApi() = viewModelScope.launch {
        loadStateLiveData.value = State.LOADING
        val response = repository.getVehicles()
        Log.i(ContentValues.TAG, "getBuscaNavesApi: ")
        when (response) {
            is NetworkResponse.Success -> { _vehicleResponse.value = response.data }
            is NetworkResponse.Failed -> { _veiculoError.value = Unit }
        }
        loadStateLiveData.value = State.LOADING_FINISHED
    }

    enum class State {
        LOADING, LOADING_FINISHED
    }
}