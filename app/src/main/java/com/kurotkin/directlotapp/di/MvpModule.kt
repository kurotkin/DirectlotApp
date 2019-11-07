package com.kurotkin.directlotapp.di

import com.kurotkin.directlotapp.presenter.LotListPresenter
import com.kurotkin.directlotapp.presenter.LotListPresenterImpl
import com.kurotkin.directlotapp.view.ViewList
import com.kurotkin.directlotapp.view.ViewListImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MvpModule {

    @Provides
    @Singleton
    fun lotListPresenter() : LotListPresenter = LotListPresenterImpl()

    @Provides
    @Singleton
    fun lotListView() : ViewList = ViewListImpl()
}