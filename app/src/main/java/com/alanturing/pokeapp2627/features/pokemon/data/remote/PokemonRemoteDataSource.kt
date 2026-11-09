package com.alanturing.pokeapp2627.features.pokemon.data.remote

import com.alanturing.pokeapp2627.features.pokemon.data.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRemoteDataSource
    @Inject constructor(
        private val _api:PokeApi,
    ): PokemonDataSource {

    override val pokemonList: Flow<List<Pokemon>> = flow {
        val pokemon = readAllPokemon()
        emit(pokemon)
    }
    override suspend fun readAllPokemon(): List<Pokemon> {
        val response = _api.readAllPokemon()
        val pokemons = mutableListOf<Pokemon>()
        if (response.isSuccessful) {
            val body = response.body()!!
            return body.results.map { item ->
                val id = item.url.trimEnd('/').substringAfterLast('/').toInt()
                Pokemon(
                    id = id,
                    name = item.name,
                    pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
                )
            }
        }
        else {
            // Mostrar un errror!
        }
        return pokemons
    }

    override suspend fun readPokemonByName(name: String): Pokemon? {
        val response = _api.readPokemonByName(name)
        if (response.isSuccessful) {
            val body = response.body()!!
            return body.toModel()
        }
        else{
            return null
        }
    }

    override suspend fun readPokemonById(id: Int): Pokemon? {
        val response = _api.readPokemonById(id)
        if (response.isSuccessful) {
            val body = response.body()!!
            return body.toModel()
        }
        else{
            return null
        }
    }

}