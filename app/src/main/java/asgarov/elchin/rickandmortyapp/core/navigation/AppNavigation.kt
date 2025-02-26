package asgarov.elchin.rickandmortyapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import asgarov.elchin.rickandmortyapp.feature_character.presentation.character_detail.CharacterDetailScreen
import asgarov.elchin.rickandmortyapp.feature_character.presentation.character_list.CharacterScreen
import asgarov.elchin.rickandmortyapp.feature_episode.presentation.episode_detail.EpisodeDetailScreen
import asgarov.elchin.rickandmortyapp.feature_episode.presentation.episode_list.EpisodeScreen
import asgarov.elchin.rickandmortyapp.feature_location.presentation.location_detail.LocationDetailScreen
import asgarov.elchin.rickandmortyapp.feature_location.presentation.location_list.components.LocationScreen

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppRoute.CharacterRoute
    ) {
        composable<AppRoute.CharacterRoute> {
            CharacterScreen(navController)
        }

        composable<AppRoute.CharacterDetailRoute> {
            CharacterDetailScreen(navController)
        }

        composable<AppRoute.EpisodeRoute> {
            EpisodeScreen(navController)
        }

        composable<AppRoute.EpisodeDetailRoute> {
            EpisodeDetailScreen(navController)
        }

        composable<AppRoute.LocationRoute> {
            LocationScreen(navController)
        }

        composable<AppRoute.LocationDetailRoute> {
            LocationDetailScreen(navController)
        }
    }
}