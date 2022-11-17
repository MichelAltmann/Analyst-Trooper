package com.android.desafiofinalstarwars.ui.home.viewmodels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.RepositoryInterface
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.EspecieResposta
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.NetworkResponse
import kotlinx.coroutines.launch

class EspeciesViewModel(private val repository: RepositoryInterface) : ViewModel() {

    private val _especieResposta = MutableLiveData<EspecieResposta?>()
    val especieResposta: LiveData<EspecieResposta?> = _especieResposta
    private val _especieError = MutableLiveData<Unit>()
    val especieError = _especieError as LiveData<Unit>
    var loadStateLiveData = MutableLiveData<State>()

    fun getBuscaEspeciesApi() = viewModelScope.launch {
        loadStateLiveData.value = State.LOADING
        val response = repository.buscaEspecies()
        Log.i(ContentValues.TAG, "getBuscaPersonagensApi: $response")
        when (response) {
            is NetworkResponse.Success -> { _especieResposta.value = response.data }
            is NetworkResponse.Failed -> { _especieError.value = Unit }
        }
        loadStateLiveData.value = State.LOADING_FINISHED
    }

    enum class State {
        LOADING, LOADING_FINISHED
    }
}