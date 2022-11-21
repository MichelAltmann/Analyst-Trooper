package com.android.desafiofinalstarwars.ui.left.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.RepositoryInterface
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.StarshipResponse
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.NetworkResponse
import kotlinx.coroutines.launch

class StarshipsViewModel(private val repository: RepositoryInterface) : ViewModel() {

    private val _starshipResponse = MutableLiveData<StarshipResponse?>()
    val starshipResponse: LiveData<StarshipResponse?> = _starshipResponse
    private val _starshipError = MutableLiveData<Unit>()
    val starshipError = _starshipError as LiveData<Unit>
    var loadStateLiveData = MutableLiveData<State>()

    fun getApiStarships() = viewModelScope.launch {
        loadStateLiveData.value = State.LOADING
        when (val response = repository.getStarships()) {
            is NetworkResponse.Success -> { _starshipResponse.value = response.data }
            is NetworkResponse.Failed -> { _starshipError.value = Unit }
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