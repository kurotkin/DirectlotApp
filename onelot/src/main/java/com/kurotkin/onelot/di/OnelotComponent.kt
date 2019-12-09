package com.kurotkin.directlotapp.di

import com.kurotkin.core.di.CoreComponent
import com.kurotkin.core.di.FeatureScope
import com.kurotkin.directlotapp.domain.LotsUserCaseImpl
import com.kurotkin.directlotapp.model.LotsRepository
import com.kurotkin.directlotapp.presentation.one_lot.presenter.LotInfoPresenterImpl
import com.kurotkin.onelot.presentation.InfoActivity
import dagger.Component
import javax.inject.Singleton

@FeatureScope
@Component(modules = [RepositoryModule::class, MvpModule::class, DomainModule::class],
    dependencies = [CoreComponent::class])
interface OnelotComponent {
    fun inject(infoActivity: InfoActivity)
    fun inject(lotsUserCaseImpl: LotsUserCaseImpl)
    fun inject(lotInfoPresenter : LotInfoPresenterImpl)
    fun inject(lotsRepository : LotsRepository)
}