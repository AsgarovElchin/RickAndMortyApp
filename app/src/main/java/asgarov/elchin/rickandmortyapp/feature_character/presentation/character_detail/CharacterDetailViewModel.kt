package asgarov.elchin.rickandmortyapp.feature_character.presentation.character_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import asgarov.elchin.rickandmortyapp.core.utils.Resource
import asgarov.elchin.rickandmortyapp.feature_character.domain.repository.CharacterRepository
import asgarov.elchin.rickandmortyapp.feature_episode.domain.repository.EpisodeRepository
import asgarov.elchin.rickandmortyapp.feature_episode.presentation.episode_detail.EpisodesDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val episodeRepository: EpisodeRepository,
    savedStateHandle: SavedStateHandle

):ViewModel() {



    private val _characterDetailState = mutableStateOf(CharacterDetailState())

    val characterDetailState: State<CharacterDetailState> = _characterDetailState

    private val _episodesDetailState = mutableStateOf(EpisodesDetailState())

    val episodesDetailState: State<EpisodesDetailState> = _episodesDetailState

    init {
        savedStateHandle.get<Int>("characterId")?.let { characterId->
            getCharacterDetailById(characterId)
        }
    }


    private fun getCharacterDetailById(id:Int){
        characterRepository. getCharacterById(id).onEach {result->
            when(result){
                is Resource.Success->{
                    _characterDetailState.value = CharacterDetailState(characterDetail = result.data)
                    characterDetailState.value.characterDetail?.let {
                        if(it.episodeNumbers.isNotEmpty()){
                            val idsString = it.episodeNumbers.toString()
                            getEpisodesDetailsByIds(idsString)
                        }
                    }
                }
                is Resource.Error->{
                    _characterDetailState.value = CharacterDetailState(error = result.message?: "An unexpected error occurred")
                }
                is Resource.Loading->{
                    _characterDetailState.value = CharacterDetailState(isLoading = true)

                }
            }

        }.launchIn(viewModelScope)
    }


    private fun getEpisodesDetailsByIds(episodeIds:String){
        episodeRepository.getEpisodesByIds(episodeIds).onEach {result->
            when(result){
                is Resource.Success->{
                    _episodesDetailState.value = EpisodesDetailState(episodesDetail = result.data)
                }
                is Resource.Error->{
                    _episodesDetailState.value = EpisodesDetailState(error = result.message?: "An unexpected error occurred")
                }
                is Resource.Loading->{
                    _episodesDetailState.value = EpisodesDetailState(isLoading = true)

                }
            }

        }.launchIn(viewModelScope)

    }


}