package com.kurotkin.directlotapp.di

import com.kurotkin.directlotapp.model.LotsRepository
import com.kurotkin.directlotapp.model.LotsRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(): LotsRepository = LotsRepositoryImpl()
}