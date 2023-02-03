package com.example.marvel.ui.characters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel.data.repository.IMarvelRepository
import com.example.marvel.ui.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val OFFSET = 20

class CharactersViewModel @Inject constructor(
    private val repository: IMarvelRepository
) : ViewModel() {

    private var offset: Int = 0

    private val _states: MutableStateFlow<State> = MutableStateFlow(State.Loading)

    val states: Flow<State> = _states

    init {
        getCharacters()
    }

     fun getCharacters() {
        viewModelScope.launch {
            try {
                _states.emit(State.Loading)
                val data = repository.getCharacters(offset).data.results.map { it.toUI() }
                offset += OFFSET
                _states.emit(State.Result(data))
            } catch (e: Throwable) {
                Log.d("TAG", "getCharacters: $e")
                _states.emit(State.Error)
            }
        }
    }

}