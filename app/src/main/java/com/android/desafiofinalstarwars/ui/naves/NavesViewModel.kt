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
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.PersonagemResposta
import com.android.desafiofinalstarwars.ui.personagens.PersonagensViewModel
import kotlinx.coroutines.launch

class NavesViewModel(private val repository: RepositoryInterface) : ViewModel() {

    private val _naveResposta = MutableLiveData<NaveResposta?>()
    val naveResposta: LiveData<NaveResposta?> = _naveResposta
    private val _naveError = MutableLiveData<Unit>()
    val naveError = _naveError as LiveData<Unit>

    fun getBuscaNavesApi() = viewModelScope.launch {
        when (val response = repository.buscaNaves()) {
            is NetworkResponse.Success -> { _naveResposta.value = response.data }
            is NetworkResponse.Failed -> { _naveError.value = Unit }
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}