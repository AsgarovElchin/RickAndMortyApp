package asgarov.elchin.rickandmortyapp.feature_character.data.remote

import asgarov.elchin.rickandmortyapp.feature_character.data.remote.dto.CharacterDto
import retrofit2.http.GET

interface CharacterApi {

    @GET("/character")
    suspend fun getAllCharacters():List<CharacterDto>
}