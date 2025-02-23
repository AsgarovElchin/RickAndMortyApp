package asgarov.elchin.rickandmortyapp.feature_character.domain.use_case

import androidx.paging.PagingData
import asgarov.elchin.rickandmortyapp.feature_character.domain.model.Character
import asgarov.elchin.rickandmortyapp.feature_character.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(filter: CharacterFilter):Flow<PagingData<Character>>{
        return repository.getAllCharacters(filter)
    }
}