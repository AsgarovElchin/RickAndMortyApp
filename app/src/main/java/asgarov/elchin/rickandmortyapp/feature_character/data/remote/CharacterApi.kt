package asgarov.elchin.rickandmortyapp.feature_character.data.remote

import asgarov.elchin.rickandmortyapp.feature_character.data.remote.dto.CharacterListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET("/character")
    suspend fun getAllCharacters(@Query("page") page: Int): CharacterListDto
}