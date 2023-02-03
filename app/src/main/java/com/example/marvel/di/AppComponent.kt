package com.example.marvel.di

import android.content.Context
import com.example.marvel.ui.character_info.CharacterInfoFragment
import com.example.marvel.ui.characters.CharactersFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(fragment: CharactersFragment)
    fun inject(fragment: CharacterInfoFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

}