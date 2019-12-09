package com.kurotkin.directlotapp.di

import com.kurotkin.core.di.FeatureScope
import com.kurotkin.directlotapp.domain.LotsUserCase
import com.kurotkin.directlotapp.domain.LotsUserCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @FeatureScope
    fun provideDomain(): LotsUserCase = LotsUserCaseImpl()

}