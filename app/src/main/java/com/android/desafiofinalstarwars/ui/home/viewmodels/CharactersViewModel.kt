package com.android.desafiofinalstarwars.ui.home.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.RepositoryInterface
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.NetworkResponse
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.CharacterResponse
import kotlinx.coroutines.launch

class CharactersViewModel(private val repository: RepositoryInterface) : ViewModel() {

    private val _characterResponse = MutableLiveData<CharacterResponse?>()
    val characterResponse: LiveData<CharacterResponse?> = _characterResponse
    private val _characterError = MutableLiveData<Unit>()
    val characterError = _characterError as LiveData<Unit>
    var loadStateLiveData = MutableLiveData<State>()

    fun getApiCharacters() = viewModelScope.launch {
        loadStateLiveData.value = State.LOADING
        when (val response = repository.getCharacters()) {
            is NetworkResponse.Success -> { _characterResponse.value = response.data }
            is NetworkResponse.Failed -> { _characterError.value = Unit }
        }
        loadStateLiveData.value = State.LOADING_FINISHED
    }

    enum class State {
        LOADING, LOADING_FINISHED
    }

}