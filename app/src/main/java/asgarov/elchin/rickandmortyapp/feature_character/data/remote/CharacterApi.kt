package asgarov.elchin.rickandmortyapp.feature_character.data.remote

import asgarov.elchin.rickandmortyapp.feature_character.data.remote.dto.CharacterDto
import asgarov.elchin.rickandmortyapp.feature_character.data.remote.dto.CharacterListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int,
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,
        @Query("species") species: String? = null,
        @Query("gender") gender: String? = null
    ): CharacterListDto

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id")id:Int):CharacterDto
}