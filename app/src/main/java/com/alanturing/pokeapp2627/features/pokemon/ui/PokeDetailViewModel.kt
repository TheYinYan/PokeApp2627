package com.alanturing.pokeapp2627.features.pokemon.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alanturing.pokeapp2627.features.pokemon.data.model.Pokemon
import com.alanturing.pokeapp2627.features.pokemon.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val _repository: PokemonRepository,
    savedStateHandle: SavedStateHandle
): ViewModel(){
    private val _state: MutableStateFlow<PokemonDetailUiState> =
        MutableStateFlow(value = PokemonDetailUiState.Loading)

    val state: StateFlow<PokemonDetailUiState>
        get() = _state.asStateFlow()

    private val id: Int = checkNotNull(savedStateHandle["id"])

    init {
        viewModelScope.launch {
           val pokemon: Pokemon? = _repository.readPokemonById(id);
           _state.value = PokemonDetailUiState.Success(pokemon)
        }
    }
}

sealed class PokemonDetailUiState {
    object Loading: PokemonDetailUiState()
    data class Success(val pokemon: Pokemon?) : PokemonDetailUiState()
    data class Error(val message: String) : PokemonDetailUiState()
}