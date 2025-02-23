package asgarov.elchin.rickandmortyapp.core.di

import asgarov.elchin.rickandmortyapp.core.utils.Constants
import asgarov.elchin.rickandmortyapp.feature_character.data.remote.CharacterApi
import asgarov.elchin.rickandmortyapp.feature_character.data.repository.CharacterRepositoryImpl
import asgarov.elchin.rickandmortyapp.feature_character.domain.repository.CharacterRepository
import asgarov.elchin.rickandmortyapp.feature_episode.data.remote.EpisodeApi
import asgarov.elchin.rickandmortyapp.feature_episode.data.repository.EpisodeRepositoryImpl
import asgarov.elchin.rickandmortyapp.feature_episode.domain.repository.EpisodeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCharacterApi(retrofit: Retrofit): CharacterApi {
        return retrofit.create(CharacterApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(api: CharacterApi): CharacterRepository {
        return CharacterRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideEpisodeApi(retrofit: Retrofit): EpisodeApi {
        return retrofit.create(EpisodeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideEpisodeRepository(api: EpisodeApi): EpisodeRepository {
        return EpisodeRepositoryImpl(api)
    }
}
