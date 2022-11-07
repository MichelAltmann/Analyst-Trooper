package com.android.desafiofinalstarwars.ui.personagens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.PersonagensWebClient
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.PersonagemResposta
import kotlinx.coroutines.launch
import java.lang.Exception

class PersonagensViewModel : ViewModel() {

    private val _personagemResposta = MutableLiveData<PersonagemResposta>()
    val personagemResposta: LiveData<PersonagemResposta>
        get() = _personagemResposta

    fun getBuscaPersonagemsApi() = viewModelScope.launch {
        try {
            val response = PersonagensWebClient().buscaPersonagens()
            response?.apply {
                _personagemResposta.postValue(this)
            }
        } catch (e : Exception){
            Log.e(TAG, "getBuscaPersonagemsApi: ", e)
        }
    }



    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}