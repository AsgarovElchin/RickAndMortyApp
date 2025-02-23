package asgarov.elchin.rickandmortyapp.feature_character.presentation.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import asgarov.elchin.rickandmortyapp.feature_character.domain.use_case.CharacterFilter
import asgarov.elchin.rickandmortyapp.feature_character.domain.use_case.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase

):ViewModel() {

    private val _filterState = MutableStateFlow(CharacterFilter())
    val filterState = _filterState.asStateFlow()

    val charactersFlow = _filterState.flatMapLatest {
        filter->
        getCharactersUseCase(filter)
    }.cachedIn(viewModelScope)

    fun updateFilter(newFilter: CharacterFilter) {
        viewModelScope.launch {
            _filterState.emit(newFilter)
        }
}}