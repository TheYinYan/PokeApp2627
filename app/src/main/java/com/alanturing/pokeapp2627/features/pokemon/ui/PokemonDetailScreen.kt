package com.alanturing.pokeapp2627.features.pokemon.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.alanturing.pokeapp2627.features.pokemon.data.model.Pokemon

@Composable
fun PokemonDetailScreen(
    viewModel: PokemonDetailViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    when (state) {
        is PokemonDetailUiState.Loading -> {
            Text("Loading")
        }
        is PokemonDetailUiState.Success -> {
            LazyColumn {
                val pokemon = (state as PokemonDetailUiState.Success).pokemon
                item {
                    Row(
                        modifier = Modifier
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = pokemon?.pokemonImageUrl,
                            contentDescription = pokemon?.name,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(end = 8.dp)
                        )
                        Column {
                            Text("ID: ${pokemon?.id}")
                            Text("Name: ${pokemon?.name}")
                        }
                    }
                }
            }
        }
        is PokemonDetailUiState.Error -> {
            val message = (state as PokemonDetailUiState.Error).message
            Text(message)
        }
    }
}