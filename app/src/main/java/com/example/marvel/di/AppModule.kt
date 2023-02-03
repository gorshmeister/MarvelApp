package com.example.marvel.di

import com.example.marvel.data.network.MarvelService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import com.example.marvel.data.repository.MarvelRepository
import com.example.marvel.data.repository.IMarvelRepository
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Singleton
    @Provides
    fun provideRepository(
        api: MarvelService,
        executionDispatcher: CoroutineDispatcher
    ): IMarvelRepository {
        return MarvelRepository(api, executionDispatcher)
    }
}