package com.kurotkin.directlotapp.di

import com.kurotkin.directlotapp.InfoActivity
import com.kurotkin.directlotapp.MainActivity
import com.kurotkin.directlotapp.model.LotsRepository
import com.kurotkin.directlotapp.presenter.LotInfoPresenter
import com.kurotkin.directlotapp.presenter.LotInfoPresenterImpl
import com.kurotkin.directlotapp.presenter.LotListPresenter
import com.kurotkin.directlotapp.presenter.LotListPresenterImpl
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class, MvpModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(infoActivity: InfoActivity)
    fun inject(lotListPresenter : LotListPresenterImpl)
    fun inject(lotInfoPresenter : LotInfoPresenterImpl)
    fun inject(lotsRepository : LotsRepository)
}