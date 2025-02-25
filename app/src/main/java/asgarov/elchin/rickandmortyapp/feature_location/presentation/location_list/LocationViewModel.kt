package asgarov.elchin.rickandmortyapp.feature_location.presentation.location_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import asgarov.elchin.rickandmortyapp.feature_location.domain.use_case.GetLocationsUseCase
import asgarov.elchin.rickandmortyapp.feature_location.domain.use_case.LocationFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val getLocationsUseCase: GetLocationsUseCase
):ViewModel() {

    private val _filterState = MutableStateFlow(LocationFilter())
    val filterState = _filterState.asStateFlow()

    val locationsFlow = _filterState.flatMapLatest {
            filter->
        getLocationsUseCase(filter)
    }.cachedIn(viewModelScope)

    fun updateFilter(newFilter: LocationFilter) {
        viewModelScope.launch {
            _filterState.emit(newFilter)
        }}
}