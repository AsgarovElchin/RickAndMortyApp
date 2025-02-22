package asgarov.elchin.rickandmortyapp.feature_character.presentation.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import asgarov.elchin.rickandmortyapp.feature_character.domain.model.Character
import asgarov.elchin.rickandmortyapp.feature_character.domain.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository

):ViewModel() {

    val charactersFlow: Flow<PagingData<Character>> =
        repository.getAllCharacters().cachedIn(viewModelScope)
}