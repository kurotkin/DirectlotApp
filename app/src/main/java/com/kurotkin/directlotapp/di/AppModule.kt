package com.kurotkin.directlotapp.di

import android.content.Context
import com.kurotkin.directlotapp.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides
    @Singleton
    fun provideContext(): Context = app
}