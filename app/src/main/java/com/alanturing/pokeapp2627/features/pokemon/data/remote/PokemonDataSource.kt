package com.alanturing.pokeapp2627.features.pokemon.data.remote

import com.alanturing.pokeapp2627.features.pokemon.data.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonDataSource {
    val pokemonList: Flow<List<Pokemon>>
    suspend fun readAllPokemon():List<Pokemon>
    suspend fun readPokemonByName(name:String): Pokemon?
    suspend fun readPokemonById(id:Int): Pokemon?
}