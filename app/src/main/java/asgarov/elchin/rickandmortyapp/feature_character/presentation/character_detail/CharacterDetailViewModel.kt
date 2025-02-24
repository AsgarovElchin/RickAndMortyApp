package asgarov.elchin.rickandmortyapp.feature_character.presentation.character_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import asgarov.elchin.rickandmortyapp.core.utils.Resource
import asgarov.elchin.rickandmortyapp.feature_character.domain.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: CharacterRepository,
    savedStateHandle: SavedStateHandle

):ViewModel() {

    private val _state = mutableStateOf(CharacterDetailState())

    val state: State<CharacterDetailState> = _state


    private val _characterDetailState = mutableStateOf(CharactersDetailState())

    val characterDetailState: State<CharactersDetailState> = _characterDetailState
    init {
        savedStateHandle.get<Int>("characterId")?.let { characterId->
            getCharacterDetailById(characterId)
        }
    }


    private fun getCharacterDetailById(id:Int){
        repository. getCharacterById(id).onEach {result->
            when(result){
                is Resource.Success->{
                    _state.value = CharacterDetailState(characterDetail = result.data)
                }
                is Resource.Error->{
                    _state.value = CharacterDetailState(error = result.message?: "An unexpected error occurred")
                }
                is Resource.Loading->{
                    _state.value = CharacterDetailState(isLoading = true)

                }
            }

        }.launchIn(viewModelScope)
    }

}