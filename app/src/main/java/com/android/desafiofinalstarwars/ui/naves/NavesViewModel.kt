package com.android.desafiofinalstarwars.ui.naves

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.RepositoryInterface
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.NaveResposta
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.NetworkResponse
import kotlinx.coroutines.launch

class NavesViewModel(private val repository: RepositoryInterface) : ViewModel() {

    private val _naveResposta = MutableLiveData<NaveResposta?>()
    val naveResposta: LiveData<NaveResposta?> = _naveResposta
    private val _naveError = MutableLiveData<Unit>()
    val naveError = _naveError as LiveData<Unit>
    var loadStateLiveData = MutableLiveData<State>()

    fun getBuscaNavesApi() = viewModelScope.launch {
        loadStateLiveData.value = State.LOADING
        val response = repository.buscaNaves()
        Log.i(TAG, "getBuscaNavesApi: ")
        when (response) {
            is NetworkResponse.Success -> { _naveResposta.value = response.data }
            is NetworkResponse.Failed -> { _naveError.value = Unit }
        }
        loadStateLiveData.value = State.LOADING_FINISHED
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    enum class State {
        LOADING, LOADING_FINISHED
    }
}