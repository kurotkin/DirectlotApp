package com.kurotkin.directlotapp.di

import com.kurotkin.directlotapp.MainActivity
import com.kurotkin.directlotapp.model.LotsRepository
import com.kurotkin.directlotapp.presenter.LotListPresenter
import com.kurotkin.directlotapp.presenter.LotListPresenterImpl
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class, MvpModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(LotsRepositoryImpl : LotListPresenterImpl)
    fun inject(LotListPresenterImpl : LotListPresenter)
    fun inject(LotsRepository : LotsRepository)
}