package asgarov.elchin.rickandmortyapp.feature_location.presentation.location_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import asgarov.elchin.rickandmortyapp.core.utils.Resource
import asgarov.elchin.rickandmortyapp.feature_character.domain.repository.CharacterRepository
import asgarov.elchin.rickandmortyapp.feature_character.presentation.character_detail.CharactersDetailState
import asgarov.elchin.rickandmortyapp.feature_location.domain.repository.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val characterRepository: CharacterRepository,
    savedStateHandle: SavedStateHandle
):ViewModel() {

    private val _locationState = mutableStateOf(LocationDetailState())

    val locationState: State<LocationDetailState> = _locationState

    private val _charactersDetailState = mutableStateOf(CharactersDetailState())

    val charactersDetailState: State<CharactersDetailState> = _charactersDetailState

    init {
        savedStateHandle.get<Int>("locationId")?.let { locationId->
            getLocationDetailById(locationId)
        }
    }



    private fun getLocationDetailById(id:Int){
        locationRepository.getLocationById(id).onEach {result->
            when(result){
                is Resource.Success->{
                    _locationState.value = LocationDetailState(locationDetail = result.data)

                    locationState.value.locationDetail?.let {
                        if(it.residents.isNotEmpty()){
                            val idsString = it.residents.toString()
                            getCharactersDetailByIds(idsString)
                        }
                    }
                }
                is Resource.Error->{
                    _locationState.value = LocationDetailState(error = result.message?: "An unexpected error occurred")
                }
                is Resource.Loading->{
                    _locationState.value = LocationDetailState(isLoading = true)

                }
            }

        }.launchIn(viewModelScope)
    }

    private fun getCharactersDetailByIds(characterIds:String){
        characterRepository.getCharactersByIds(characterIds).onEach {result->
            when(result){
                is Resource.Success->{
                    _charactersDetailState.value = CharactersDetailState(charactersDetail = result.data)
                }
                is Resource.Error->{
                    _charactersDetailState.value = CharactersDetailState(error = result.message?: "An unexpected error occurred")
                }
                is Resource.Loading->{
                    _charactersDetailState.value = CharactersDetailState(isLoading = true)

                }
            }

        }.launchIn(viewModelScope)

    }

}