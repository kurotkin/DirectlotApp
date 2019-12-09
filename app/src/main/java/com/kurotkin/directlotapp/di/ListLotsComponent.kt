package com.kurotkin.directlotapp.di

import com.kurotkin.core.di.CoreComponent
import com.kurotkin.core.di.FeatureScope
import com.kurotkin.directlotapp.domain.LotsUserCaseImpl
import com.kurotkin.directlotapp.model.LotsRepository
import com.kurotkin.directlotapp.presentation.MainActivity
import com.kurotkin.directlotapp.presentation.presenter.LotListPresenterImpl
import dagger.Component

@FeatureScope
@Component(modules = [RepositoryModule::class, MvpModule::class, DomainModule::class],
    dependencies = [CoreComponent::class])
interface ListLotsComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(lotsUserCaseImpl: LotsUserCaseImpl)
    fun inject(lotListPresenter : LotListPresenterImpl)
    fun inject(lotsRepository : LotsRepository)
}