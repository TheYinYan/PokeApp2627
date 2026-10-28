package com.alanturing.pokeapp2627.features.pokemon.ui

import com.alanturing.pokeapp2627.features.pokemon.data.model.Pokemon

sealed interface PokeListUiState {
    object Loading : PokeListUiState
    data class Success(val pokemonList: List<Pokemon>) : PokeListUiState
    data class Error(val message: String) : PokeListUiState
}