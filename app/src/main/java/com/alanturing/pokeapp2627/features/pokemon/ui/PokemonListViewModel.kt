package com.alanturing.pokeapp2627.features.pokemon.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alanturing.pokeapp2627.features.pokemon.data.model.Pokemon
import com.alanturing.pokeapp2627.features.pokemon.data.remote.PokemonDataSource
import com.alanturing.pokeapp2627.features.pokemon.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonDataSource
): ViewModel(){
    private val _state: MutableStateFlow<PokeListUiState> =
        MutableStateFlow(value = PokeListUiState.Loading)

    val state: StateFlow<PokeListUiState>
        get() = _state.asStateFlow()

    init {
        loadPokemons()
    }

    fun loadPokemons() {
        viewModelScope.launch {
            _state.value = PokeListUiState.Success(repository.readAllPokemon())
        }
    }
}

sealed interface PokeListUiState {
    object Loading: PokeListUiState
    data class Success(val pokemonList: List<Pokemon>) : PokeListUiState
    data class Error(val message: String) : PokeListUiState
}