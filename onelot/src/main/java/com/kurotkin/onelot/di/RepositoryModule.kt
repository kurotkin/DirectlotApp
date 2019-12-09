package com.kurotkin.directlotapp.di

import com.kurotkin.core.di.FeatureScope
import com.kurotkin.directlotapp.model.LotsRepository
import com.kurotkin.directlotapp.model.LotsRepositoryImpl
import com.kurotkin.directlotapp.model.net.DirectlotService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @FeatureScope
    fun provideRepository(): LotsRepository = LotsRepositoryImpl(provideDirectlotService())

    @Provides
    @FeatureScope
    fun provideDirectlotService(): DirectlotService = DirectlotService()
}