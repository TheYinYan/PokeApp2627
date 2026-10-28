package com.alanturing.pokeapp2627.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alanturing.pokeapp2627.features.pokemon.ui.PokemonDetailScreen
import com.alanturing.pokeapp2627.features.pokemon.ui.PokemonListScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.PokemonList.route,
        modifier = modifier
    ) {
        composable(Routes.PokemonList.route) {
            PokemonListScreen()
        }
        composable(Routes.PokemonDetail.route) {
            PokemonDetailScreen()
        }
    }
}
