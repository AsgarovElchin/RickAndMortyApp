package asgarov.elchin.rickandmortyapp.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AppRoute {
    @Serializable
    data object CharacterRoute : AppRoute()

    @Serializable
    data class CharacterDetailRoute(val characterId: Int) : AppRoute()

    @Serializable
    data object EpisodeRoute : AppRoute()

    @Serializable
    data class EpisodeDetailRoute(val episodeId: Int) : AppRoute()

    @Serializable
    data object LocationRoute : AppRoute()

    @Serializable
    data class LocationDetailRoute(val locationId: Int) : AppRoute()
}


