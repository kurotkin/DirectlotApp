package com.kurotkin.directlotapp.di

import com.kurotkin.directlotapp.domain.LotsUserCaseImpl
import com.kurotkin.directlotapp.presentation.one_lot.InfoActivity
import com.kurotkin.directlotapp.presentation.list_of_lots.MainActivity
import com.kurotkin.directlotapp.model.LotsRepository
import com.kurotkin.directlotapp.presentation.one_lot.presenter.LotInfoPresenterImpl
import com.kurotkin.directlotapp.presentation.list_of_lots.presenter.LotListPresenterImpl
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class, MvpModule::class, DomainModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(infoActivity: InfoActivity)
    fun inject(lotsUserCaseImpl: LotsUserCaseImpl)
    fun inject(lotListPresenter : LotListPresenterImpl)
    fun inject(lotInfoPresenter : LotInfoPresenterImpl)
    fun inject(lotsRepository : LotsRepository)
}