package com.kurotkin.directlotapp.di

import com.kurotkin.directlotapp.presentation.one_lot.presenter.LotInfoPresenter
import com.kurotkin.directlotapp.presentation.one_lot.presenter.LotInfoPresenterImpl
import com.kurotkin.directlotapp.presentation.list_of_lots.presenter.LotListPresenter
import com.kurotkin.directlotapp.presentation.list_of_lots.presenter.LotListPresenterImpl
import com.kurotkin.directlotapp.presentation.one_lot.view.ViewInfo
import com.kurotkin.directlotapp.presentation.one_lot.view.ViewInfoImpl
import com.kurotkin.directlotapp.presentation.list_of_lots.view.ViewList
import com.kurotkin.directlotapp.presentation.list_of_lots.view.ViewListImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MvpModule {

    @Provides
    @Singleton
    fun lotListPresenter() : LotListPresenter =
        LotListPresenterImpl()

    @Provides
    @Singleton
    fun lotInfoPresenter() : LotInfoPresenter = LotInfoPresenterImpl()

    @Provides
    @Singleton
    fun lotListView() : ViewList =
        ViewListImpl()

    @Provides
    @Singleton
    fun lotInfoView() : ViewInfo = ViewInfoImpl()
}