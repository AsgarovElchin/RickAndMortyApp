package asgarov.elchin.rickandmortyapp.feature_location.domain.model

import asgarov.elchin.rickandmortyapp.feature_character.domain.model.Info

data class LocationList(
    val info:Info,
    val results:List<Location>
)
