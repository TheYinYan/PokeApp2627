package com.alanturing.pokeapp2627.features.pokemon.data.repository

import com.alanturing.pokeapp2627.features.pokemon.data.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    val pokemons: Flow<List<Pokemon>>
    suspend fun readAllPokemon():List<Pokemon>
    suspend fun readPokemonById(id:Int): Pokemon?
}