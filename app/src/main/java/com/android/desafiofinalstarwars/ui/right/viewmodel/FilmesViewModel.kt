package com.android.desafiofinalstarwars.ui.right.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.RepositoryInterface
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.FilmeResposta
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.NetworkResponse
import kotlinx.coroutines.launch

class FilmesViewModel(private val repository: RepositoryInterface) : ViewModel() {
    private val _filmeResposta = MutableLiveData<FilmeResposta?>()
    val filmeResposta: LiveData<FilmeResposta?> = _filmeResposta
    private val _filmeError = MutableLiveData<Unit>()
    val filmeError = _filmeError as LiveData<Unit>
    var loadStateLiveData = MutableLiveData<State>()

    fun getBuscaPlanetasApi() = viewModelScope.launch {
        loadStateLiveData.value = State.LOADING
        val response = repository.buscaFilmes()
        Log.i(ContentValues.TAG, "getBuscaNavesApi: ")
        when (response) {
            is NetworkResponse.Success -> { _filmeResposta.value = response.data }
            is NetworkResponse.Failed -> { _filmeError.value = Unit }
        }
        loadStateLiveData.value = State.LOADING_FINISHED
    }

    enum class State {
        LOADING, LOADING_FINISHED
    }
}