package com.android.desafiofinalstarwars.ui.home.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.RepositoryInterface
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.NetworkResponse
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.PersonagemResposta
import kotlinx.coroutines.launch

class PersonagensViewModel(private val repository: RepositoryInterface) : ViewModel() {

    private val _personagemResposta = MutableLiveData<PersonagemResposta?>()
    val personagemResposta: LiveData<PersonagemResposta?> = _personagemResposta
    private val _personagemError = MutableLiveData<Unit>()
    val personagemError = _personagemError as LiveData<Unit>
    var loadStateLiveData = MutableLiveData<State>()

    fun getBuscaPersonagemsApi() = viewModelScope.launch {
        loadStateLiveData.value = State.LOADING
        val response = repository.buscaPersonagens()
        Log.i(TAG, "getBuscaPersonagensApi: $response")
        when (response) {
            is NetworkResponse.Success -> { _personagemResposta.value = response.data }
            is NetworkResponse.Failed -> { _personagemError.value = Unit }
        }
        loadStateLiveData.value = State.LOADING_FINISHED
    }

    enum class State {
        LOADING, LOADING_FINISHED
    }
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    val text: LiveData<String> = _text

}