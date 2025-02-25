package asgarov.elchin.rickandmortyapp.feature_location.presentation.location_detail

import asgarov.elchin.rickandmortyapp.feature_location.domain.model.Location

class LocationDetailState(
    val isLoading:Boolean = false,
    val locationDetail: Location? = null,
    val error:String = "")