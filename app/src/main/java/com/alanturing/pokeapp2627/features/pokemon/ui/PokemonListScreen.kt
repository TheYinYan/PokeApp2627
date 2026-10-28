package com.alanturing.pokeapp2627.features.pokemon.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp


@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsState()
    Column() {
        Text("PokeList")
        when (state) {
            PokeListUiState.Initial -> {
                Text("Initial")
            }
            PokeListUiState.Loading -> {
                Text("Loading")
            }
            is PokeListUiState.Success -> {
                val pokemonList = (state as PokeListUiState.Success).pokemonList
                for (pokemon in pokemonList) {
                    Row(modifier = Modifier.padding(8.dp)) {
                        Text("ID: " + pokemon.id)
                        Text("Name: " +
                            pokemon.name,
                            modifier = Modifier.padding(start = 8.dp)
                        )

                    }
                }
            }
            is PokeListUiState.Error -> {
                val message = (state as PokeListUiState.Error).message
                Text(message)
            }
        }
    }
}
