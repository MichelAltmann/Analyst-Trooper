package com.android.desafiofinalstarwars.ui.search.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.desafiofinalstarwars.model.Character
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.RepositoryInterface
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.CharacterResponse
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.NetworkResponse
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CharactersSearchViewModel(private val repository: RepositoryInterface) : ViewModel() {
    private val _characterResponse = MutableLiveData<CharacterResponse?>()
    val characterResponse: LiveData<CharacterResponse?> = _characterResponse
    private val _characterError = MutableLiveData<Unit>()
    val characterError = _characterError as LiveData<Unit>
    var loadStateLiveData = MutableLiveData<State>()

    private var page = 1
    var filter = ""
        set(value) {
            field = value
            page = 1
            getApiCharactersSearch()
        }


    private var searchCharacterJob : Job? = null

    fun getApiCharactersSearch() = viewModelScope.launch {
        searchCharacterJob?.cancel()
        searchCharacterJob = viewModelScope.launch {
            loadStateLiveData.value = CharactersSearchViewModel.State.LOADING
            when (val response = repository.getCharactersSearch(filter = filter, page = page)) {
                is NetworkResponse.Success -> {
                    _characterResponse.value = response.data
                    page++
                }
                is NetworkResponse.Failed -> {
                    _characterError.value = Unit
                }
            }
            loadStateLiveData.value = CharactersSearchViewModel.State.LOADING_FINISHED
        }
    }

    enum class State {
        LOADING, LOADING_FINISHED
    }
}