package com.kurotkin.directlotapp.di

import com.kurotkin.directlotapp.domain.LotsUserCaseImpl
import com.kurotkin.directlotapp.model.LotsRepository
import com.kurotkin.directlotapp.presentation.one_lot.presenter.LotInfoPresenterImpl
import com.kurotkin.onelot.presentation.InfoActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, MvpModule::class, DomainModule::class])
interface AppComponent {
    fun inject(infoActivity: InfoActivity)
    fun inject(lotsUserCaseImpl: LotsUserCaseImpl)
    fun inject(lotInfoPresenter : LotInfoPresenterImpl)
    fun inject(lotsRepository : LotsRepository)
}