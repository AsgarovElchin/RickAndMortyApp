package asgarov.elchin.rickandmortyapp.feature_episode.presentation.episode_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import asgarov.elchin.rickandmortyapp.core.utils.Resource
import asgarov.elchin.rickandmortyapp.feature_character.domain.repository.CharacterRepository
import asgarov.elchin.rickandmortyapp.feature_character.presentation.character_detail.CharacterDetailState
import asgarov.elchin.rickandmortyapp.feature_character.presentation.character_detail.CharactersDetailState
import asgarov.elchin.rickandmortyapp.feature_episode.domain.repository.EpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val episodeRepository: EpisodeRepository,
    private val characterRepository: CharacterRepository,
    savedStateHandle: SavedStateHandle
):ViewModel() {

    private val _episodeState = mutableStateOf(EpisodeDetailState())

    val episodeState: State<EpisodeDetailState> = _episodeState

    private val _charactersDetailState = mutableStateOf(CharactersDetailState())

    val charactersDetailState: State<CharactersDetailState> = _charactersDetailState

    init {
        savedStateHandle.get<Int>("episodeId")?.let { episodeId->
            getEpisodeDetailById(episodeId)
        }
    }



    private fun getEpisodeDetailById(id:Int){
        episodeRepository.getEpisodeById(id).onEach {result->
            when(result){
                is Resource.Success->{
                    _episodeState.value = EpisodeDetailState(episodeDetail = result.data)

                    episodeState.value.episodeDetail?.let {
                        if(it.characters.isNotEmpty()){
                            getCharactersDetailByIds(it.characters)
                        }
                    }
                }
                is Resource.Error->{
                    _episodeState.value = EpisodeDetailState(error = result.message?: "An unexpected error occurred")
                }
                is Resource.Loading->{
                    _episodeState.value = EpisodeDetailState(isLoading = true)

                }
            }

        }.launchIn(viewModelScope)
    }

    private fun getCharactersDetailByIds(characterIds:List<Any>){
        val idsString = characterIds.joinToString(",") // Format: "1,2,3"
        characterRepository.getCharactersByIds(idsString).onEach {result->
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