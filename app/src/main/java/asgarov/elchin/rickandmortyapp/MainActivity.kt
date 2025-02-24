package asgarov.elchin.rickandmortyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import asgarov.elchin.rickandmortyapp.feature_character.presentation.character_detail.CharacterDetailScreen
import asgarov.elchin.rickandmortyapp.feature_character.presentation.character_list.CharacterScreen
import asgarov.elchin.rickandmortyapp.feature_episode.presentation.episode_detail.EpisodeDetailScreen
import asgarov.elchin.rickandmortyapp.feature_episode.presentation.episode_list.EpisodeScreen
import asgarov.elchin.rickandmortyapp.ui.theme.RickAndMortyAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = CharacterRoute
    ) {
        composable<CharacterRoute> {
            CharacterScreen(navController)
        }
        composable<CharacterDetailRoute>{
            CharacterDetailScreen(navController)
        }
        composable<EpisodeRoute> {
            EpisodeScreen(navController)
        }
        composable<EpisodeDetailRoute> {
            EpisodeDetailScreen(navController)
        }
    }
}



@Serializable
object CharacterRoute

@Serializable
data class CharacterDetailRoute(val characterId:Int)

@Serializable
object EpisodeRoute

@Serializable
data class EpisodeDetailRoute(val episodeId:Int)





