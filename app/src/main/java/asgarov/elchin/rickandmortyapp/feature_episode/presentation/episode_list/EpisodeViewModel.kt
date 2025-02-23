package asgarov.elchin.rickandmortyapp.feature_episode.presentation.episode_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import asgarov.elchin.rickandmortyapp.feature_episode.domain.use_case.EpisodeFilter
import asgarov.elchin.rickandmortyapp.feature_episode.domain.use_case.GetEpisodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val getEpisodesUseCase: GetEpisodesUseCase
):ViewModel() {


    private val _filterState = MutableStateFlow(EpisodeFilter())
    val filterState = _filterState.asStateFlow()

    val episodesFlow = _filterState.flatMapLatest {
            filter->
        getEpisodesUseCase(filter)
    }.cachedIn(viewModelScope)

    fun updateFilter(newFilter: EpisodeFilter) {
        viewModelScope.launch {
            _filterState.emit(newFilter)
        }}
}