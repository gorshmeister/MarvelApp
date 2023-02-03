package com.example.marvel.ui.character_info

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel.data.repository.IMarvelRepository
import com.example.marvel.ui.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterInfoViewModel @Inject constructor(
    private val repository: IMarvelRepository
) : ViewModel() {

    private val _states: MutableStateFlow<State> = MutableStateFlow(State.Loading)

    val states: Flow<State> = _states

    fun getComics(id: Int) {
        viewModelScope.launch {
            try {
                val data = repository.getCharacterComics(id).data.results.map { it.toUI() }
                _states.emit(State.Result(data))
            } catch (e: Throwable) {
                Log.d("TAG", "getComics: $e")
                _states.emit(State.Error)
            }
        }
    }

}