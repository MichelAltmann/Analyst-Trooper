package com.android.desafiofinalstarwars.ui.right

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.RepositoryInterface
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.NetworkResponse
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.PlanetaResposta
import kotlinx.coroutines.launch

class PlanetasViewModel(private val repository: RepositoryInterface) : ViewModel() {

    private val _planetaResposta = MutableLiveData<PlanetaResposta?>()
    val planetaResposta: LiveData<PlanetaResposta?> = _planetaResposta
    private val _planetaError = MutableLiveData<Unit>()
    val planetaError = _planetaError as LiveData<Unit>
    var loadStateLiveData = MutableLiveData<State>()

    fun getBuscaPlanetasApi() = viewModelScope.launch {
        loadStateLiveData.value = State.LOADING
        val response = repository.buscaPlanetas()
        Log.i(ContentValues.TAG, "getBuscaNavesApi: ")
        when (response) {
            is NetworkResponse.Success -> { _planetaResposta.value = response.data }
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