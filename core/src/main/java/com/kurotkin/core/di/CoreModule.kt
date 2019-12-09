package com.kurotkin.core.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {
    @Provides
    @Singleton
    fun provideExpensiveObject(): String = ""
}